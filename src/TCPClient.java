import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import JsonClasses.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("unused")
public class TCPClient {
	public static void main(String[] args) throws Exception {
		
		ClientLogin c = new ClientLogin();
//		CreateCalendar c = new CreateCalendar();
		
		String modifiedSentence;
		Gson gson = new GsonBuilder().create();


		//CLIENT LOGIN
		c.setEmail("nihabr");
		c.setPassWord("1");
		
		
		//CLIENTLOGOUT
		
//		ClientLogout lo = new ClientLogout();
//		lo.setEmail("nihabr");
		
		//CREATE CALENDAR

//		ArrayList<String> users = new ArrayList<String>();
//		c.setEmail("nibr13ae");
//		c.setCalendarName("DENNE SKAL");
//		c.setPublicOrPrivate(1);
//		users.add("jolj13ab");
//		users.add("dude");
//		c.setSharedUsers(users);
		

	
		String gsonString = gson.toJson(c);
		System.out.println(gsonString);
		
		

		Socket clientSocket = new Socket("130.226.41.17", 6666);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		byte[] input = gsonString.getBytes();
		byte key = (byte) 3.1470;
		byte[] encrypted = input;
		for (int i = 0; i < encrypted.length; i++)
			encrypted[i] = (byte) (encrypted[i] ^ key);

		outToServer.write(encrypted);
		outToServer.flush();
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		modifiedSentence = inFromServer.readLine();
		System.out.println(modifiedSentence);
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
}