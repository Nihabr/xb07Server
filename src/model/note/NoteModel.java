package model.note;

/*
 * 		Mangler:
 * 			- Identifikation på brugeren der har lavet noten
 * 			- Hvem der skal kunne redigere noten
 * 			- Mulighed for at slette / redigere noter
 * 			- Active Status
 * 
 * 			ETA: 2 timer
 */

public class NoteModel {

	private String text;
	private String createdBy;
	private String eventID;

	public NoteModel(String text, String createdBy, String eventID) {
		super();
		this.text = text;

		this.createdBy = createdBy;

		this.eventID = eventID;

	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
