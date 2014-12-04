package JsonClasses;

import java.util.ArrayList;

public class CreateCalendar implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String overallID = "createCalendar";
	private String calenderName;
	private String createdBy;
	private int publicOrPrivate;
	private int isCBS;
	private ArrayList <String> sharedUsers;
	
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
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
	public String getCalendarName() {
		return calenderName;
	}
	public void setCalendarName(String calenderName) {
		this.calenderName = calenderName;
	}
	public int getPublicOrPrivate() {
		return publicOrPrivate;
	}
	public void setPublicOrPrivate(int publicPrivate) {
		this.publicOrPrivate = publicPrivate;
	}
	public int getIsCBS() {
		return isCBS;
	}
	public void setIsCBS(int isCBS) {
		this.isCBS = isCBS;
	}

}
