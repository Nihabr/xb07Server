

import java.net.*;

class TCPServer{    
	
	public static void main(String argv[]) throws Exception       {

		AdminWorker admin = new AdminWorker();
		//Creates a socket to send and receive messages in port 8888
		ServerSocket welcomeSocket = new ServerSocket(6666);
		
		//While something is true
		while(true){
			//Creates a socket and a buffered reader which receives some sort of input from somewhere around the internet!
			Socket connectionSocket = welcomeSocket.accept();
			ClientWorker client= new ClientWorker(connectionSocket);
			Thread thread = new Thread(client, "client");
			thread.start();
		}
	}
}