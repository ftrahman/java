import java.util.*;
import java.io.*;

public class StudentNetworkSimulator extends NetworkSimulator
{
    /*
     * Predefined Constants (static member variables):
     *
     *   int MAXDATASIZE : the maximum size of the Message data and
     *                     Packet payload
     *
     *   int A           : a predefined integer that represents entity A
     *   int B           : a predefined integer that represents entity B 
     *
     * Predefined Member Methods:
     *
     *  void stopTimer(int entity): 
     *       Stops the timer running at "entity" [A or B]
     *  void startTimer(int entity, double increment): 
     *       Starts a timer running at "entity" [A or B], which will expire in
     *       "increment" time units, causing the interrupt handler to be
     *       called.  You should only call this with A.
     *  void toLayer3(int callingEntity, Packet p)
     *       Puts the packet "p" into the network from "callingEntity" [A or B]
     *  void toLayer5(String dataSent)
     *       Passes "dataSent" up to layer 5
     *  double getTime()
     *       Returns the current time in the simulator.  Might be useful for
     *       debugging.
     *  int getTraceLevel()
     *       Returns TraceLevel
     *  void printEventList()
     *       Prints the current event list to stdout.  Might be useful for
     *       debugging, but probably not.
     *
     *
     *  Predefined Classes:
     *
     *  Message: Used to encapsulate a message coming from layer 5
     *    Constructor:
     *      Message(String inputData): 
     *          creates a new Message containing "inputData"
     *    Methods:
     *      boolean setData(String inputData):
     *          sets an existing Message's data to "inputData"
     *          returns true on success, false otherwise
     *      String getData():
     *          returns the data contained in the message
     *  Packet: Used to encapsulate a packet
     *    Constructors:
     *      Packet (Packet p):
     *          creates a new Packet that is a copy of "p"
     *      Packet (int seq, int ack, int check, String newPayload)
     *          creates a new Packet with a sequence field of "seq", an
     *          ack field of "ack", a checksum field of "check", and a
     *          payload of "newPayload"
     *      Packet (int seq, int ack, int check)
     *          chreate a new Packet with a sequence field of "seq", an
     *          ack field of "ack", a checksum field of "check", and
     *          an empty payload
     *    Methods:
     *      boolean setSeqnum(int n)
     *          sets the Packet's sequence field to "n"
     *          returns true on success, false otherwise
     *      boolean setAcknum(int n)
     *          sets the Packet's ack field to "n"
     *          returns true on success, false otherwise
     *      boolean setChecksum(int n)
     *          sets the Packet's checksum to "n"
     *          returns true on success, false otherwise
     *      boolean setPayload(String newPayload)
     *          sets the Packet's payload to "newPayload"
     *          returns true on success, false otherwise
     *      int getSeqnum()
     *          returns the contents of the Packet's sequence field
     *      int getAcknum()
     *          returns the contents of the Packet's ack field
     *      int getChecksum()
     *          returns the checksum of the Packet
     *      int getPayload()
     *          returns the Packet's payload
     *
     */

    /*   Please use the following variables in your routines.
     *   int WindowSize  : the window size
     *   double RxmtInterval   : the retransmission timeout
     *   int LimitSeqNo  : when sequence number reaches this value, it wraps around
     */

    public static final int FirstSeqNo = 0;
    private int WindowSize;
    private double RxmtInterval;
    private int LimitSeqNo;
    
    // Add any necessary class variables here.  Remember, you cannot use
    // these variables to send messages error free!  They can only hold
    // state information for A or B.
    // Also add any necessary methods (e.g. checksum of a String)
    
    // Variables to maintain state A
    private int curSeqNum;                      // Current sequence number
    private int lastSeqNum;                     // Last sequence number
    private int baseSeqNum;                     // Base sequence number
    private int maxSeqNum;                      // Max sequence number
    private LinkedList<Packet> sentPckts;       // List of sent packets
    
    // Variables to maintain state B
    private int expectedSeqNum;                 // Expected sequence number
    
    // Variables for stats
    private int total;
    private int corrupted;
    private int ACKs; 
    private int dupACKs;
    private int delivered;
    private int l5;
    private int retransmissions;
    private int original;
    private LinkedList<Double> RTT;             // Round trip time
    private LinkedList<Double> CT;              // Communication time

    // This is the constructor.  Don't touch!
    public StudentNetworkSimulator(int numMessages,
                                   double loss,
                                   double corrupt,
                                   double avgDelay,
                                   int trace,
                                   int seed,
                                   int winsize,
                                   double delay)
    {
        super(numMessages, loss, corrupt, avgDelay, trace, seed);
	WindowSize = winsize;
	LimitSeqNo = winsize; // set appropriately; assumes SR here!
	RxmtInterval = delay;
    }
    
    // Function to determine the checksum of a packet
    protected int checksum(Packet pkt) {
      String message = pkt.getPayload();
      int checksum = pkt.getAcknum() + pkt.getSeqnum();
      for(int i = 0; i < message.length(); i++) {
        checksum += (int) message.charAt(i);
      }
      return checksum;
    }
    
    // This routine will be called whenever the upper layer at the sender [A]
    // has a message to send.  It is the job of your protocol to insure that
    // the data in such a message is delivered in-order, and correctly, to
    // the receiving upper layer.
    protected void aOutput(Message message){
      String payload = message.getData();
      Packet pkt = new Packet(curSeqNum, 0, -1, payload);
      pkt.setChecksum(checksum(pkt)); 
      sentPckts.add(pkt); curSeqNum++; // might have to edit
      if(curSeqNum == LimitSeqNo) {
        curSeqNum = FirstSeqNo;
      }
      if(sentPckts.indexOf(pkt) <= maxSeqNum) {
        toLayer3(A, pkt);
        System.out.println("Sending packet: " + pkt.toString() + " to B.");
        original++;
        RTT.add(sentPckts.indexOf(pkt), getTime()); total++;
        CT.add(sentPckts.indexOf(pkt), getTime());
        if(baseSeqNum == lastSeqNum) {
          startTimer(A, RxmtInterval); 
        }
        lastSeqNum++;
      }
    }
    
    // This routine will be called whenever a packet sent from the B-side 
    // (i.e. as a result of a toLayer3() being done by a B-side procedure)
    // arrives at the A-side.  "packet" is the (possibly corrupted) packet
    // sent from the B-side.
    protected void aInput(Packet packet){
      double time = getTime();
      if(checksum(packet) != packet.getChecksum()) {
        //corrupted++;
        System.out.println("A received a corrupted ACK: " + packet.toString());
        return;
      }
      System.out.println("A received a successful ACK: " + packet.toString());
      int ackNum = packet.getAcknum(); double preWindowShift = baseSeqNum;
      while(baseSeqNum < sentPckts.size()) {
        if(sentPckts.get(baseSeqNum).getSeqnum() == ackNum) {
          break;
        }
        Double updateRTT = RTT.get(baseSeqNum);
        RTT.add(baseSeqNum, 0.0); CT.add(baseSeqNum, time-updateRTT);
        baseSeqNum++; maxSeqNum++;
      }
      int shiftVal = (int)(baseSeqNum-preWindowShift);
      if(shiftVal != 0) {
        System.out.println("Case 2: Shifted sender window more than once.");
      }
      else {
        System.out.println();
      }
      Double updateRTT = RTT.get(baseSeqNum);
      RTT.add(baseSeqNum, time-updateRTT); CT.add(baseSeqNum, time-updateRTT);
      baseSeqNum++; maxSeqNum++;
      if(baseSeqNum == lastSeqNum) {
        stopTimer(A);
        if((lastSeqNum <= maxSeqNum) && (lastSeqNum < sentPckts.size())) {
          double pktSent = getTime();
          toLayer3(A, sentPckts.get(lastSeqNum));
          if(baseSeqNum == lastSeqNum) {
            startTimer(A, RxmtInterval); 
          }
          lastSeqNum++; total++; original++;
          CT.add(lastSeqNum, pktSent); //CT.add(lastSeqNum, pktSent);
        }
      }
      else {
        stopTimer(A); startTimer(A, RxmtInterval);
      }
    }
    
    // This routine will be called when A's timer expires (thus generating a 
    // timer interrupt). You'll probably want to use this routine to control 
    // the retransmission of packets. See startTimer() and stopTimer(), above,
    // for how the timer is started and stopped. 
    protected void aTimerInterrupt(){
      System.out.println("\nTimer interrupted!");
      startTimer(A, RxmtInterval);
      for(int i = baseSeqNum; ((i <= maxSeqNum) && (i < sentPckts.size())); i++) {
        Packet pkt = sentPckts.get(i); toLayer3(A, pkt); total++;
        if(i == lastSeqNum) {
          lastSeqNum++; RTT.add(i, getTime()); CT.add(i, getTime());
          System.out.println("Sending packet: " + pkt.toString() + " to B.");
          original++;
        }
        else {
          RTT.add(i, 0.0);
          retransmissions++;
          System.out.println("Case 3: Resending packet after RTO: " + pkt.toString() + " to B.");
        }
      }
    }
    
    // This routine will be called once, before any of your other A-side 
    // routines are called. It can be used to do any required
    // initialization (e.g. of member variables you add to control the state
    // of entity A).
    protected void aInit() {
       curSeqNum = FirstSeqNo; baseSeqNum = FirstSeqNo; 
       lastSeqNum = FirstSeqNo; maxSeqNum = baseSeqNum + WindowSize - 1;
       total = 0; corrupted = 0; ACKs = 0; dupACKs = 0; retransmissions = 0;
       sentPckts = new LinkedList<Packet>(); 
       RTT = new LinkedList<Double>(); 
       CT = new LinkedList<Double>(); 
       
    }
    
    // This routine will be called whenever a packet sent from the B-side 
    // (i.e. as a result of a toLayer3() being done by an A-side procedure)
    // arrives at the B-side.  "packet" is the (possibly corrupted) packet
    // sent from the A-side.
    protected void bInput(Packet packet){
      delivered++;
      if(checksum(packet) != packet.getChecksum()) {
        corrupted++;
        System.out.println("B received a corrupted packet: " + packet.toString());
        return;
      }
      if(packet.getSeqnum() == expectedSeqNum) {
        Packet ACK = new Packet(packet.getSeqnum(), expectedSeqNum, -1, "");
        ACK.setChecksum(checksum(ACK)); toLayer3(B, ACK); ACKs++; dupACKs++;
        System.out.println("Sending ACK for packet :" + ACK.getAcknum());
        expectedSeqNum++; 
        if(expectedSeqNum == LimitSeqNo) {
          expectedSeqNum = FirstSeqNo;
        }
        toLayer5(packet.getPayload()); l5++;
      }
      else {
        System.out.println("Case 4: Received a duplicate ACK. Expected packet " + expectedSeqNum + " but received " + packet.getSeqnum());
        dupACKs++;
      }
      
    }
    
    // This routine will be called once, before any of your other B-side 
    // routines are called. It can be used to do any required
    // initialization (e.g. of member variables you add to control the state
    // of entity B).
    protected void bInit(){
      expectedSeqNum = FirstSeqNo; delivered = 0;
    }
    
    // Calculates the average communication time
    protected String avgCT(LinkedList<Double> data) {
      double avg = 0; int index;
      for(index = 0; index < baseSeqNum; index++) {
        avg += data.get(index);
      }
      return (avg/index + " ms");
    }
    
    // Calculates the average RTT
    protected String avgRTT(LinkedList<Double> data) {
      double avg = 0; int index = 0; double totalRTT = 0; 
      while(index < baseSeqNum){
        if(RTT.get(index) > 0) {
          avg += RTT.get(index);
          totalRTT++;
          }
        index++;
      }
       return (avg/totalRTT + "ms");
    }

    // Use to print final statistics
    protected void Simulation_done()
    {
        
        //double retransmissions2 = total - lastSeqNum;
        //double original = lastSeqNum;
        double loss = (retransmissions - corrupted) / (double) ((original + retransmissions) + dupACKs);
        double corrupt= (corrupted) / (double) ((original + retransmissions) + dupACKs - (retransmissions - corrupted));
    	
        //System.out.println("original retrans " + retransmissions2);
        //System.out.println("now retrans " + retransmissions);
        
        // TO PRINT THE STATISTICS, FILL IN THE DETAILS BY PUTTING VARIBALE NAMES. DO NOT CHANGE THE FORMAT OF PRINTED OUTPUT
    	System.out.println("\n\n===============STATISTICS=======================");
    	System.out.println("Number of original packets transmitted by A: " + original);
    	System.out.println("Number of retransmissions by A: " + retransmissions);
    	System.out.println("Number of data packets delivered to layer 5 at B: " + l5);
    	System.out.println("Number of ACK packets sent by B: " + ACKs);
    	System.out.println("Number of corrupted packets:" + corrupted);
    	System.out.println("Ratio of lost packets: " + loss);
    	System.out.println("Ratio of corrupted packets: " + corrupt);
    	System.out.println("Average RTT: " + avgRTT(RTT));
    	System.out.println("Average communication time: " + avgCT(CT));
    	System.out.println("==================================================");

    	// PRINT YOUR OWN STATISTIC HERE TO CHECK THE CORRECTNESS OF YOUR PROGRAM
    	System.out.println("\nEXTRA:");
    	// EXAMPLE GIVEN BELOW
    	System.out.println("Total number of packets sent: " + total); 
    }	

}
