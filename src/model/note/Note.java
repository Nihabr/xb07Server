package model.note;

import java.sql.SQLException;

import model.Model;
import model.QueryBuild.*;

public class Note extends Model{
	
	NoteModel notes = new NoteModel( "", "", "");
	QueryBuilder qb = new QueryBuilder(); 
	
		// Metoden til at lave en ny note
		public void CreateNote(NoteModel note)	{
			
			
			String text = note.getText();
			
			String createdBy = note.getCreatedBy();
			String eventID = note.getEventID();
		
			String[] fields = {"eventId", "createdBy", "text"};
			String[] values = { eventID, createdBy, text};
			System.out.println(values);
			try {
				qb.insertInto("notes", fields).values(values).Execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Metode til at hente noter fra databasen
		public NoteModel GetNote (int noteID) throws SQLException{
			
			try {
				resultSet = qb.selectFrom("notes").where("noteID", "= ", String.valueOf(noteID)).ExecuteQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				while(resultSet.next()){
					notes = new NoteModel(
							
							resultSet.getString("text"), 
							 
							resultSet.getString("createdBy"), 
							
							resultSet.getString("eventID")
							);
				}
					return notes;
				
			
		
		}
		
		// Metode til at gemme noter i databasen
		public void SaveNote (NoteModel note){
			
			String text = note.getText();
			
			String createdBy = note.getCreatedBy();
			

			String eventID = note.getEventID();
			
			String[] fields = {"eventID", "createdBy", "text"};
			String[] values = {eventID, createdBy, text};
			try {
				qb.update("notes", fields, values).where("noteID", "=",eventID).Execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}
}
