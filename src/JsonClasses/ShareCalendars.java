package JsonClasses;

import java.util.ArrayList;


public class ShareCalendars {

	private  final long serialVersionUID = 1L;
	private String overallID = "shareCalendar";
	String email;
	ArrayList <String> shareEmail;
	String calendarID;
	
	public ShareCalendars(){
		
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


	public ArrayList<String> getShareEmail() {
		return shareEmail;
	}

	public void setShareEmail(ArrayList<String> shareEmail) {
		this.shareEmail = shareEmail;
	}

	public String getCalendarID() {
		return calendarID;
	}

	public void setCalendarID(String calendarID) {
		this.calendarID = calendarID;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
	
	
		
		
	
	
	
	
	
	
	
	
	
}
