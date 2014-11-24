package databaseMethods;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Model;
import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;
import model.calendar.CalendarEvents;
import model.calendar.EncryptUserID;
import model.calendar.GetCalendarData;
import model.note.*;
import JsonClasses.*;

public class SwitchMethods extends Model
{
	QueryBuilder qb = new QueryBuilder();
	Gson gson = new GsonBuilder().create();
	QOTDModel qm = new QOTDModel();
	NoteModel nm = new NoteModel (0, "", "", "", 0, 0);
	Note note = new Note();
	GetDailyUpdate gdu = new GetDailyUpdate();
	ClientLogin clientLogin = new ClientLogin();
	EncryptUserID e = new EncryptUserID();
	GetCalendarData gcd = new GetCalendarData();
	ShareCalendars share = new ShareCalendars();
	
	String stringToBeReturned = "";
	String currentUser = "";

	
	/**
	 * Allows the client to create a new calendar
	 * @param userName
	 * @param calenderName
	 * @param privatePublic
	 * @return
	 * @throws SQLException
	 */

	public String 	addNewCalender (String newCalenderName, int publicOrPrivate, String email, ArrayList <String> sharedUsers) throws SQLException	{
		
		String result = "";
		
		//Queries DB to see if calendar name is in use
		resultSet = qb.selectFrom("calendar").where("name", "=", newCalenderName).ExecuteQuery();
		boolean duplicateCalendar = false;
//		boolean hasCBScalendar = false;
		
		//Checks resultset to find out if the user already has a calendar with the supplied name
		while (resultSet.next()){
			if(resultSet.getString("createdby").equals(email))
				duplicateCalendar = true;
		
//			
//			if(resultSet.getInt("isCBS") == 1)
//				hasCBScalendar = true;
		}
		if(!duplicateCalendar){
			
//			if(hasCBScalendar){
//			String [] fields = {"Name","active","CreatedBy","PrivatePublic","Email"};
//			String [] values = {newCalenderName,"1",email, Integer.toString(publicOrPrivate),email};
//			qb.insertInto("calender", fields).values(values).Execute();
//			result = "CBSCalendar has been created.";
//			}
//			
//			if(!hasCBScalendar){
			String [] fields = {"Name","active","CreatedBy","PrivatePublic"};
			String [] values = {newCalenderName,"1",email, Integer.toString(publicOrPrivate)};
			qb.insertInto("calender", fields).values(values).Execute();
			result = "Calendar has been created.";
			sharedUsers.add(email);

				if ( !sharedUsers.isEmpty()){
					resultSet = qb.selectFrom("calender").where("name", "=", newCalenderName).ExecuteQuery();
					int newCalendarID = 0;
					while(resultSet.next()){
						if(resultSet.getString("createdby").equals(email))
							newCalendarID = resultSet.getInt("calenderid");
					}
					share(sharedUsers, String.valueOf(newCalendarID), email); 
					result += " Calendar has been shared with specified users.";
//				}
			}
		} else
			result = "User already has calendar with that name. Please chose a new name for the calendar!";
		return result;
	}
	public String 	createEvent(String type,
			String location, String createdby, String start,
			String end, String name, String text,
			String customevent, String calendarID)throws SQLException{
		testConnection();
		String [] fields = {"type", "location", "createdby", "start",
				"end", "name","text","customevent", "calendarID"};
		String[] values = {type ,  location ,  createdby ,  start ,
				 end ,  name , text , customevent ,  calendarID };
		
		qb.insertInto("events", fields).values(values).Execute();
		stringToBeReturned = "Event has been created";
		return stringToBeReturned;
	}
	/**
	 * Allows the client to delete a calender
	 * @param userName
	 * @param calenderName
	 * @return
	 */

	public String 	share(ArrayList <String> sharedUsers, String calendarID, String email) throws SQLException{
		
		
		String [] values = {"calenderID","createdby"};
		resultSet = qb.selectFrom(values, "calender").where("email", "IS", null ).ExecuteQuery();
		while(resultSet.next()){

			//Tjekker at kalenderen findes, og at det ikke er brugerens CBS kalender
			
			if(resultSet.getInt("calenderID") == (Integer.valueOf(calendarID))){
				for (String su : sharedUsers){
					System.out.println("for loop køres");
					String [] fields = {"email","calendarID"};
					String [] value = {su, String.valueOf(calendarID)};
					qb.insertInto("calender_users", fields).values(value).Execute();
					}
				stringToBeReturned = "Calendar has been shared";
				}
			else{
				stringToBeReturned = "calendarID does not exist or the calendar cannot be shared because of restrictions";
			}
			
		}
	
		System.out.println("LOG");
		return stringToBeReturned;
		
		}
	public String 	deleteCalender (String userName, String calenderName) throws SQLException
	{
		testConnection();
		stringToBeReturned = removeCalender(userName, calenderName);

		return stringToBeReturned;
	}
	
	
	public String 	removeCalender (String userName, String calenderName) throws SQLException
	{
		String usernameOfCreator ="";
		String calenderExists = "";
		resultSet = qb.selectFrom("Calender").where("Name", "=", calenderName).ExecuteQuery();
				
//				("select * from calender where Name = '"+calenderName+"';");
		while(resultSet.next())
		{
			calenderExists = resultSet.toString();
		}
		if(!calenderExists.equals(""))
		{
			String [] value = {"CreatedBy"};
			resultSet = qb.selectFrom(value, "Calender").where("Name", "=", calenderName).ExecuteQuery();
			while(resultSet.next())
			{
				usernameOfCreator = resultSet.toString();
				System.out.println(usernameOfCreator);
			}
			if(!usernameOfCreator.equals(userName))
			{
				stringToBeReturned = "Only the creator of the calender is able to delete it.";
			}
			else
			{
				String [] keys = {"Active"};
				String [] values = {"2"};
				qb.update("calender", keys, values).where("Name", "=", calenderName).Execute();
				stringToBeReturned = "Calender has been set inactive";
			}
			stringToBeReturned = resultSet.toString();
		}
		else
		{
			stringToBeReturned = "The calender you are trying to delete, does not exists.";
		}
		
		
		
		return stringToBeReturned;
	}

	public String 	CreateNote(CreateNote cn){
		nm.setActive(cn.getIsActive());
		nm.setCreatedBy(cn.getCreatedBy());
		nm.setDateTime(cn.getDateTime());
		nm.setEventID(cn.getEventID());
		nm.setNoteID(cn.getNoteID());
		nm.setText(cn.getText());
		
		note.CreateNote(nm);
		return stringToBeReturned;
	}
	
	public String 	deleteEvent(String userID, String eventID)throws SQLException{
		
		String stringToBeReturned = "";
		testConnection();
		stringToBeReturned = removeEvent(userID, eventID);

		return stringToBeReturned;
		
	}
	public String 	removeEvent(String userID, String eventID) throws SQLException{
		
		
		String stringToBeReturend = "";
		String createdBy ="";
		resultSet = qb.selectFrom("events").where("eventID", "=", String.valueOf(eventID)).ExecuteQuery();
		
			while(resultSet.next())
			{
				System.out.println("createdby ledes efter: ");
				createdBy = resultSet.getString("createdby");
				System.out.println(createdBy);
			}
			if(!createdBy.equals(userID) || userID.equals("admin"))
			{
				stringToBeReturend = "Only the creator of the event is able to delete it.";
			}
			else
			{
				String [] keys = {"active"};
				String [] values = {"0"};
				qb.update("events", keys, values).where("eventID", "=", String.valueOf(eventID)).Execute();
				stringToBeReturend = "event has been set inactive";
			}
			
		
		return stringToBeReturend;
		
	}
	public String 	getDailyUpdate () throws SQLException{
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datetime = sdf.format(date) + " 11:00:00";
		resultSet = qb.selectFrom("dailyupdate").where("date", "=", datetime).ExecuteQuery();
		while(resultSet.next()){
			gdu.setCelsius(String.valueOf(resultSet.getDouble("apparentTemperature")));
			gdu.setDate(resultSet.getDate("date").toString());
			gdu.setDesc(resultSet.getString("summary"));
			gdu.setQuote(resultSet.getString("qotd"));
		}
		String gsonString = gson.toJson(gdu);
		
		return gsonString;
	}
	public String	deleteNote (String uID, String nID) throws SQLException{
		
		resultSet = qb.selectFrom("notes").where("noteID", "=", nID).ExecuteQuery();
		System.out.println("Checkpoint 1");
		while (resultSet.next()){
		if (uID.equals(resultSet.getString("createdBy"))){
			String[] fields ={"active"};
			String[] values ={"0"};
			qb.update("notes", fields, values).where("noteID", "=", nID).Execute();
			stringToBeReturned = "Note has been deleted";
		}
		else
			stringToBeReturned = "You are not authorized to delete this note.";
		}

		
		return stringToBeReturned;
	}
	public String 	clientLogin (String email, String password) throws SQLException{
		
		String gsonString = "";	
		String [] values = {"email", "password", "userID", "isAdmin"};
		resultSet = qb.selectFrom(values, "users").where("email", "=", email).ExecuteQuery();
		GetCalendarData g = new GetCalendarData();
		if(resultSet.next()){
			System.out.println("res = true");
			e.setEmail(email);
			CalendarEvents ce = new CalendarEvents(email);
			
			String calendarName = "CBScalendar " + email;
			
			//Sørger for at der eksisterer en CBS kalender i databasen til brugeren
			String [] fields = {"name", "createdby", "privatepublic", "isCBS"};
			String [] v = {calendarName, "CBS", "0", "1"};
			qb.insertInto("calendar", fields).values(v).Execute();
			
			//Opdaterer databasens CBS events
			try {
				gcd.getDataFromCalendar();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			clientLogin.setCalendars(ce.getCalendars());
			clientLogin.setIsAdmin(resultSet.getInt("isAdmin"));
			clientLogin.setUserID(resultSet.getInt("userID"));
			gsonString = gson.toJson(clientLogin);
			try {
				
				g.getDataFromCalendar();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else gsonString = "Login failed";

		return gsonString;
	}
}
