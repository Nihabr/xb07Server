package JsonClasses;

public class DeleteEvent {

	private  final long serialVersionUID = 1L;
	private String overallID = "deleteEvent";
	private String eventId;
	private String userID;
	private String createdby;
	
	public DeleteEvent(){
		
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
}
