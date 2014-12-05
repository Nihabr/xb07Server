
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import JsonClasses.CalendarInfo;



public class ClientWorker implements  Runnable{
	private Socket connectionSocketConected;
	private CalendarInfo CI = new CalendarInfo();
	private GiantSwitch GS = new GiantSwitch();
	private ByteCoder byteCryp = new ByteCoder();
	private String incomingJson;
//	private encryptionAES cryp =  new encryptionAES();
	
	ClientWorker(Socket connectionSocket){
		this.connectionSocketConected = connectionSocket;
	}
	
//	public void run(){
//		try{
//			System.out.println("forbindelse Oprettet!");
//			//BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//			byte[] b = new byte[500000];
//			int count = connectionSocketConected.getInputStream().read(b);
//			ByteArrayInputStream bais = new ByteArrayInputStream(b);
//			DataInputStream inFromClient = new DataInputStream(connectionSocketConected.getInputStream());		
//			//Creates an object of the data which is to be send back to the client, via the connectionSocket
//			DataOutputStream outToClient = new DataOutputStream(connectionSocketConected.getOutputStream());
//			System.out.println("Outtoclient oprettet!");
//			//Sets client sentence equals input from client
//			//incomingJson = inFromClient.readLine();			
//			System.out.println("b :" + b);
//			String ny = byteCryp.decrypt(b);
//						
//			//cryp.StringEncryption(inFromClient.readLine());
//			System.out.println("Besked modtaget!");
//			//Sysout recieved message
//			System.out.println("Received: " + ny);
//			String returnSvar = GS.GiantSwitchMethod(cryp.decrypt(ny));		
//			//Sends the capitalized message back to client!!
//			outToClient.writeBytes(cryp.encrypt(returnSvar) + "\n");
//			System.out.println("svar sendt");
//			//BufferedWriter writer = new BufferedWriter(arg0)
//		}catch(Exception exception){
//			System.err.print(exception);
//		}
//	}
	
	public void run(){
		try{
			System.out.println("forbindelse Oprettet!");
			//BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			byte[] b = new byte[500000];
			int count = connectionSocketConected.getInputStream().read(b);
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			DataInputStream inFromClient = new DataInputStream(connectionSocketConected.getInputStream());		
			//Creates an object of the data which is to be send back to the client, via the connectionSocket
			DataOutputStream outToClient = new DataOutputStream(connectionSocketConected.getOutputStream());
			System.out.println("Outtoclient oprettet!");
			//Sets client sentence equals input from client
			//incomingJson = inFromClient.readLine();			
			
			String ny = byteCryp.decrypt(b);
			
			//cryp.StringEncryption(inFromClient.readLine());
			System.out.println("Besked modtaget!");
			//Sysout recieved message
			System.out.println("Received: " + ny);
			String returnSvar = GS.GiantSwitchMethod(ny);		
			//Sends the capitalized message back to client!!
			outToClient.writeBytes(returnSvar + "\n");
			System.out.println("svar sendt");
			//BufferedWriter writer = new BufferedWriter(arg0)
		}catch(Exception exception){
			System.err.print(exception);
		}
	}
}



