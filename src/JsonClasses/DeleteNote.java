package JsonClasses;

public class DeleteNote implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String overallID = "deleteNote";
	String noteID;
	String email;
	
	
	
	
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
	public String getNoteID() {
		return noteID;
	}
	public void setNoteID(String noteID) {
		this.noteID = noteID;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
}
