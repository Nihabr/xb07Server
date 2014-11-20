package JsonClasses;

import java.util.ArrayList;

public class CreateCalender implements java.io.Serializable
{
	private  final long serialVersionUID = 1L;
	private String overallID = "createCalender";
	private String calenderName;
	private String email;
	private int publicOrPrivate;
	private ArrayList <String> sharedUsers;
	
	public ArrayList<String> getSharedUsers() {
		return sharedUsers;
	}
	public void setSharedUsers(ArrayList<String> sharedUsers) {
		this.sharedUsers = sharedUsers;
	}
	//Getters and setters for everything
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getCalenderName() {
		return calenderName;
	}
	public void setCalenderName(String calenderName) {
		this.calenderName = calenderName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPublicOrPrivate() {
		return publicOrPrivate;
	}
	public void setPublicOrPrivate(int publicPrivate) {
		this.publicOrPrivate = publicPrivate;
	}

}
