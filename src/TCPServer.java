

import java.net.*;

import config.Configurations;

class TCPServer{    
	
	public void StartTCP() throws Exception       {

		AdminWorker admin = new AdminWorker();
		admin.run();
		//Creates a socket to send and receive messages in port 6666
		ServerSocket welcomeSocket = new ServerSocket(6666);
		
		//While something is true
		while(true){
			//Creates a socket and a buffered reader which receives some sort of input from somewhere around the internet!
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Connection welcome");
			ClientWorker client= new ClientWorker(connectionSocket);
			Thread thread = new Thread(client, "client");
			thread.start();
		}
	}
}