package JsonClasses;

import java.util.ArrayList;

import model.calendar.UserEvent;

public class ClientLogin {

	private  final long serialVersionUID = 1L;
	private String overallID = "logIn";
	String email;
	String passWord;
	int userID;
	int isAdmin;
	ArrayList <ArrayList<UserEvent>> calendars = new ArrayList <ArrayList<UserEvent>>();
	
	
	public ClientLogin (){
		
	}
	
	public ArrayList<ArrayList<UserEvent>> getCalendars() {
		return calendars;
	}

	public void setCalendars(ArrayList<ArrayList<UserEvent>> calendars) {
		this.calendars = calendars;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
}
