package JsonClasses;

import java.util.ArrayList;

public class ClientLogin implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String overallID = "logIn";
	String email;
	String passWord;
	int userID;
	String role;
	ArrayList <CalendarInfo> calendars = new ArrayList <CalendarInfo>();
	boolean loggedIn = false;

	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ArrayList<CalendarInfo> getCalendars() {
		return calendars;
	}


	public void setCalendars(ArrayList<CalendarInfo> calendars) {
		this.calendars = calendars;
	}


	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}
