package JsonClasses;

public class DeleteNote {
	private  final long serialVersionUID = 1L;
	private String overallID = "deleteNote";
	String noteID;
	String userID;
	
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
