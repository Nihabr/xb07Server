package JsonClasses;

import java.util.ArrayList;

public class RetrieveUserCalendar implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String overallID = "getCalendars";
	private String email;
	ArrayList<CalendarInfo> userCalendars = new ArrayList<CalendarInfo>();
		
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<CalendarInfo> getUserCalendars() {
		return userCalendars;
	}
	public void setUserCalendars(ArrayList<CalendarInfo> userCalendars) {
		this.userCalendars = userCalendars;
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
