import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import JsonClasses.CreateCalender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TCPClient {
	public static void main(String[] args) throws Exception {
		
//		ClientLogin c = new ClientLogin();
		
		String modifiedSentence;
		Gson gson = new GsonBuilder().create();
		CreateCalender c = new CreateCalender();
		ArrayList<String> users = new ArrayList<String>();
		
		c.setEmail("nibr13ae");
		c.setCalenderName("DENNE SKAL");
		c.setPublicOrPrivate(1);
		users.add("jolj13ab");
		users.add("dude");
		c.setSharedUsers(users);
		

	
		String gsonString = gson.toJson(c);
		System.out.println(gsonString);
		
		

		Socket clientSocket = new Socket("localhost", 6666);
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