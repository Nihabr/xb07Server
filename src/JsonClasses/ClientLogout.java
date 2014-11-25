package JsonClasses;

public class ClientLogout {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String overallID = "logOut";
	String email;
	
	public ClientLogout (){
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
