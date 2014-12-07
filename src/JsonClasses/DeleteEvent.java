package JsonClasses;

public class DeleteEvent implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String overallID = "deleteEvent";
	private String eventId;
	private String email;
	

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

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}


	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
}
