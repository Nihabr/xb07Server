package JsonClasses;

public class ClientLogout implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String overallID = "logOut";
	String email;
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
