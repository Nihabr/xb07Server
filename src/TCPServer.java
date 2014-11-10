import java.io.*;
import java.net.*;
import JsonClasses.CalendarInfo;

import com.google.gson.stream.JsonReader;

class TCPServer {    
	public static void main(String argv[]) throws Exception       {
		CalendarInfo CI = new CalendarInfo();
		GiantSwitch GS = new GiantSwitch();
		encryption cryp = new encryption();
		

		
		//Creates a socket to send and recieve messages in port 8888
		ServerSocket welcomeSocket = new ServerSocket(6666);
		//Creates 2 string to hold random stuff
		String incomingJson;
		//While something is true
		while(true){
			//Creates a socket and a buffered reader which recieves some sort of input from somewhere around the internet!
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("forbindelse Oprettet!");
			//BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			byte[] b = new byte[500000];
			int count = connectionSocket.getInputStream().read(b);
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());		
			//Creates an object of the data which is to be send back to the client, via the connectionSocket
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			System.out.println("Outtoclient oprettet!");
			
			String ny = cryp.decrypt(b);
			
			System.out.println("Received: " + ny);
			String returnSvar = GS.GiantSwitchMethod(ny);		

			System.out.println(returnSvar.toString());
			outToClient.writeBytes(returnSvar + "\n");
			System.out.println("svar sendt");
			//BufferedWriter writer = new BufferedWriter(arg0)
			}
		}
	
		} 