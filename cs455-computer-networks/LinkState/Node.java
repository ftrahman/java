import java.io.*;

/**
 * This is the class that students need to implement. The code skeleton is provided.
 * Students need to implement rtinit(), rtupdate() and linkhandler().
 * printdt() is provided to pretty print a table of the current costs for reaching
 * other nodes in the network.
 */ 
public class Node extends ShortestPath{

	public static final int INFINITY = 9999;

	int[] lkcost;				/*The link cost between node 0 and other nodes*/
	int nodename;           	/*Name of this node*/
	int[][] costs;				/*forwarding table, where index is destination node, [i][0] is cost to destination node and
  	  							  [i][1] is the next hop towards the destination node */

	int[][] graph;				/*Adjacency metric for the network, where (i,j) is cost to go from node i to j */
	ShortestPath t;             /*Have Dijkstra's implementation */
	/* Class constructor */
	public Node() {
		this.graph = new int[4][4];
		for(int i = 0; i< graph.length; i++){
			for(int j=0; j<graph.length; j++){
				this.graph[i][j] = INFINITY;
			}
		}
		this.costs = new int[4][2];
		for(int i=0; i<4; i++){
			for(int j=0; j<2; j++){
				this.costs[i][j] = INFINITY;
			}
		}
	}

	/* students to write the following two routines, and maybe some others */
	void rtinit(int nodename, int[] initial_lkcost) {
		this.nodename = nodename;
		this.lkcost = initial_lkcost;
		for(int i=0; i<this.lkcost.length; i++){
			this.graph[this.nodename][i] = this.lkcost[i];
			for(int j=0; j<this.graph[0].length; j++){
				this.graph[j][this.nodename] = this.lkcost[i];
			}
		}
		for(int i=0; i<4; i++){
			this.costs[i][0] = lkcost[i];
			this.costs[i][1] = i;
		}

		for(int i=0; i<4;i+){
			if(this.costs[i][1] != nodename && i != nodename){
				Packet p = new Packet(this.nodename, i, this.nodeame, this.lkcost, -1);
				NetworkSimulator.toLayer2(p);
			}
		}
		printdt();

		//loop through nodes in graph and updating distances
//		for(int i=0; i<initial_lkcost[0].ini(); i++){
//			for(int j=0; j<initial_lkcost[0]; j++){
//
//
//			}
//		}

	}

	void rtupdate(Packet rcvdpkt) { 
		int[] mincost = rcvdpkt.mincost;
		int neighbor = rcvdpkt.nodename;

		for(int i=0; i<mincost.length; i++){
			this.graph[neighbor][i] = mincost[i];
			for(int j=0; j<graph[0].length; j++){
				this.graph[j][neighbor] = mincost[i];
			}
		}
		
		int[][] output= dijksta(this.graph, this.nodename);
		int[][] newCosts = new int[4][2];
		for(int i=0; i<4; i++){
			this.costs[i][1] = nextHop(output, this.nodename);
			this.costs[i][0] = output[i][0];
		}

		for(int i = 0; i<4; i++){
			if(this.costs[i][1] != nodename && i != rcvdpkt.sourceid  && i != nodename){
				Packet p = new Packet(this.nodename, i, this.nodename, mincost, 0);
				NetworkSimulator.tolayer2(p);
			}
		}
		

	}

	/* called when cost from the node to linkid changes from current value to newcost*/
	void linkhandler(int linkid, int newcost) { }  

	/* Prints the current costs to reaching other nodes in the network */
	void printdt() {

		System.out.printf("                    \n");
		System.out.printf("   D%d |   cost  next-hop \n", nodename);
		System.out.printf("  ----|-----------------------\n");
		System.out.printf("     0|  %3d   %3d\n",costs[0][0],costs[0][1]);
		System.out.printf("dest 1|  %3d   %3d\n",costs[1][0],costs[1][1]);
		System.out.printf("     2|  %3d   %3d\n",costs[2][0],costs[2][1]);
		System.out.printf("     3|  %3d   %3d\n",costs[3][0],costs[3][1]);
		System.out.printf("                    \n");
	}

}
