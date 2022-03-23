//package broadcast;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class User extends Thread {

	// The user socket
	private static Socket userSocket = null;
	// The output stream
	private static PrintStream output_stream = null;
	// The input stream
	private static BufferedReader input_stream = null;

	private static BufferedReader inputLine = null;
	private static boolean closed = false;

	public static void main(String[] args) {

		// The default port.
		int portNumber = 8000;
		// The default host.
		String host = "localhost";

		if (args.length < 2) {
			System.out
			.println("Usage: java User <host> <portNumber>\n"
					+ "Now using host=" + host + ", portNumber=" + portNumber);
		} else {
			host = args[0];
			portNumber = Integer.valueOf(args[1]).intValue();
		}

		/*
		 * Open a socket on a given host and port. Open input and output streams.
		 */
		try {
			userSocket = new Socket(host, portNumber);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			output_stream = new PrintStream(userSocket.getOutputStream());
			input_stream = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "
					+ host);
		}

		/*
		 * If everything has been initialized then we want to write some data to the
		 * socket we have opened a connection to on port portNumber.
		 */
		if (userSocket != null && output_stream != null && input_stream != null) {
			try {                
				/* Create a thread to read from the server. */
				new Thread(new User()).start();

				// Get user name and join the social net
				System.out.println("Welcome to the Social Media app!\nType Exit at anytime to leave the server.\nPlease enter your username:");
				output_stream.println("#join " + inputLine.readLine());
	
				while (!closed) {
					String userMessage = new String();
					String userInput = inputLine.readLine().trim();
					
					if(userInput.compareTo("Exit") == 0) {
					  output_stream.println("#Bye");
					}
					else {
					  output_stream.println("#newStatus " + userInput);
					}
					// Read user input and send protocol message to server

				}
				/*
				 * Close the output stream, close the input stream, close the socket.
				 */
				closed = true;
				output_stream.close();
				input_stream.close();
				userSocket.close();
				return;
			} catch (IOException e) {
				System.err.println(" ");
			}
		}
	}

	/*
	 * Create a thread to read from the server.
	 */
	public void run() {
		/*
		 * Keep on reading from the socket till we receive a Bye from the
		 * server. Once we received that then we want to break.
		 */
		
		String responseLine;
		
		try {
			while ((responseLine = input_stream.readLine()) != null) {
			  if (responseLine.substring(0,4).compareTo("#Bye") == 0) {
                output_stream.close();
                input_stream.close();
                userSocket.close();
              }
			  else if (responseLine.substring(0,5).compareTo("#busy") == 0) {
                System.out.println("Sorry the server is busy right now, please try again later.");
              }
			  else if (responseLine.substring(0,6).compareTo("#Leave") == 0) {
                System.out.println("The user " + responseLine.substring(7) + " has left.");
              }
			  else if (responseLine.substring(0,8).compareTo("#welcome") == 0) {
                System.out.println("Connection has been established with the server.");
              }
			  else if (responseLine.substring(0,8).compareTo("#newuser") == 0) {
			    System.out.println("New user " + responseLine.substring(9) + " has joined the server.");
			  }
			  else if (responseLine.substring(0,10).compareTo("#newStatus") == 0) {
                System.out.println(responseLine.substring(11));
              }
			  else if (responseLine.substring(0,13).compareTo("#statusPosted") == 0) {
                System.out.println("Your status has been posted to the server.");
              }

			}
			closed = true;
			output_stream.close();
			input_stream.close();
			userSocket.close();
			return;
		} catch (IOException e) {
			System.err.println("  " );
		}
	}
}



