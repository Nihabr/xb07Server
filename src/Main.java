import config.Configurations;

public class Main {
	//Starts public main method.
	
	public static void main(String[] args) throws Exception {
		Configurations cf = new Configurations();
		cf.ReadFile();
		
		TCPServer tcp = new TCPServer();
		tcp.StartTCP();
		

	}

}
