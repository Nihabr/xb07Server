

import java.net.*;

import config.Configurations;

class TCPServer{    
	
	public static void main(String argv[]) throws Exception       {
		Configurations cf = new Configurations();

		AdminWorker admin = new AdminWorker();
		admin.run();
		//Creates a socket to send and receive messages in port 6666
		ServerSocket welcomeSocket = new ServerSocket(8888);
		
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