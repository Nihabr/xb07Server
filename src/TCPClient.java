import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import JsonClasses.*;

public class TCPClient {
	public static void main(String[] args) throws Exception {
		String modifiedSentence;
		Gson gson = new GsonBuilder().create();
		
		CreateEvent ce = new CreateEvent();
		ce.setEventid("2");
		ce.setType("1");
		ce.setLocation("1");
		ce.setCreatedby("1");
		ce.setStart("1000-01-01 00:00:00");
		ce.setEnd("1001-01-01 00:00:00");
		ce.setTitle("etevent");
		ce.setText("hihihi");
		ce.setCustomevent("0");
		ce.setCalendarID("1");
		String gsonString = gson.toJson(ce);
		System.out.println(ce);
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