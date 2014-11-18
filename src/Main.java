import config.Configurations;

public class Main {
	//Starts public main method.
	
	public static void main(String[] args) {
		Configurations cf = new Configurations();
		cf.ReadFile();
		
		cf.getWeather_lat();
		cf.getWeather_lon();
		
		new TCPServer();
		
		

	}

}
