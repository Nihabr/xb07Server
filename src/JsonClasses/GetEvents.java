package JsonClasses;

import java.util.ArrayList;

public class GetEvents implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String overallID = "getEvents";
	
	ArrayList<EventInfo> events = new ArrayList<EventInfo>();

	public String getOverallID() {
		return overallID;
	}

	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}

	public ArrayList<EventInfo> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<EventInfo> events) {
		this.events = events;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
