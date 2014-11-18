package databaseMethods;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
	
	String stringToBeReturned = "";

	
	/**
	 * Allows the client to create a new calendar
	 * @param userName
	 * @param calenderName
	 * @param privatePublic
	 * @return
	 * @throws SQLException
	 */

	public String 	createNewCalender (String userName, String calenderName, int privatePublic) throws SQLException
	{

		testConnection();
		if(authenticateNewCalender(calenderName) == false)
		{
			addNewCalender(calenderName, userName, privatePublic);
			stringToBeReturned = "The new calender has been created!";
		}
		else
		{
			stringToBeReturned = "The new calender has not been created!";
		}
		
		
		return stringToBeReturned;
	}
	
	public boolean 	authenticateNewCalender(String newCalenderName) throws SQLException
	{
		getConn();
		boolean authenticate = false;
		
		resultSet= qb.selectFrom("calender").where("name", "=", newCalenderName).ExecuteQuery();
				
				//("select * from test.calender where Name = '"+newCalenderName+"';");
		while(resultSet.next())
		{
			authenticate = true;
		}
		return authenticate;
	}
	
	public void 	addNewCalender (String newCalenderName, String userName, int publicOrPrivate) throws SQLException
	{
		String [] fields = {"Name","active","CreatedBy","PrivatePublic"};
		String [] values = {newCalenderName,"1",userName, Integer.toString(publicOrPrivate)};
		qb.insertInto("calender", fields).values(values).Execute();
		
	}
	public String 	createEvent( String eventid, String type,
			String location, String createdby, String start,
			String end, String name, String text,
			String customevent, String calendarID)throws SQLException{
		testConnection();
		String [] fields = {"eventid", "type", "location", "createdby", "start",
				"end", "name","text","customevent", "calendarID"};
		String[] values = {eventid,  type ,  location ,  createdby ,  start ,
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

		return gsonString;
	}
}
