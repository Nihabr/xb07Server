package JsonClasses;

public class CreateNote {

	private final long serialVersionUID = 1L;
	private String overallID = "createNote";
	private String text;
	private String createdBy;
	private String eventID;

	public CreateNote() {

	}

	public String getOverallID() {
		return overallID;
	}

	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

}
