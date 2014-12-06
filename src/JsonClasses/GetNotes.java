package JsonClasses;

import java.util.ArrayList;

import model.calendar.UserEvent;

public class GetNotes {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String overallID = "getNotes";
	ArrayList<CreateNote> notes = new ArrayList<CreateNote>();
	ArrayList<UserEvent> Events = new ArrayList<UserEvent>();
	String email;
	
	public GetNotes(){
		
	}

	public ArrayList<CreateNote> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<CreateNote> notes) {
		this.notes = notes;
	}

	public ArrayList<UserEvent> getEvents() {
		return Events;
	}

	public void setEvents(ArrayList<UserEvent> events) {
		Events = events;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
