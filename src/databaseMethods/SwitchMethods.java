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

public class SwitchMethods extends Model {
	QueryBuilder qb = new QueryBuilder();
	Gson gson = new GsonBuilder().create();
	QOTDModel qm = new QOTDModel();
	NoteModel nm = new NoteModel("", "", "");
	Note note = new Note();
	GetDailyUpdate gdu = new GetDailyUpdate();
	CreateEvent createEvent = new CreateEvent();
	ClientLogin clientLogin = new ClientLogin();
	EncryptUserID e = new EncryptUserID();
	GetCalendarData gcd = new GetCalendarData();
	ShareCalendars share = new ShareCalendars();
	CalendarInfo cInfo = new CalendarInfo();
	GetEvents ge = new GetEvents();
	
	
	String stringToBeReturned = "";
	

	/**
	 * Allows the client to create a new calendar
	 * 
	 * @param userName
	 * @param calendarName
	 * @param privatePublic
	 * @return
	 * @throws SQLException
	 */

	public String addNewCalendar(String newCalendarName, int publicOrPrivate,
			String email, ArrayList<String> sharedUsers, int isCBS)
			throws SQLException {

		String result = "";

		// Queries DB to see if calendar name is in use
		resultSet = qb.selectFrom("calendar")
				.where("name", "=", newCalendarName).ExecuteQuery();
		boolean duplicateCalendar = false;

		// Checks resultset to find out if the user already has a calendar with
		// the supplied name
		while (resultSet.next()) {
			if (resultSet.getString("createdby").equals(email))
				duplicateCalendar = true;

		}
		if (!duplicateCalendar) {

			String[] fields = { "Name", "CreatedBy", "PrivatePublic", "isCBS" };
			String[] values = { newCalendarName, email,
					Integer.toString(publicOrPrivate), Integer.toString(isCBS) };
			qb.insertInto("calendar", fields).values(values).Execute();
			result = "Calendar has been created.";
			sharedUsers.add(email);

			if (!sharedUsers.isEmpty()) {
				resultSet = qb.selectFrom("calendar")
						.where("name", "=", newCalendarName).ExecuteQuery();
				int newCalendarID = 0;
				while (resultSet.next()) {
					if (resultSet.getString("createdby").equals(email))
						newCalendarID = resultSet.getInt("calendarID");
				}
				share(sharedUsers, String.valueOf(newCalendarID), email);
				result += " Calendar has been shared with specified users.";

				// }
			}
		} else
			result = "User already has calendar with that name. Please chose a new name for the calendar!";
		return result;
	}

	public String createEvent(String type, String location, String createdby,
			String start, String end, String name, String text,
			String calendarID) throws SQLException {
		testConnection();
		String[] fields = { "type", "location", "createdby", "start", "end",
				"name", "text", "calendarID" };
		String[] values = { type, location, createdby, start, end, name, text,
				calendarID };

		qb.insertInto("events", fields).values(values).Execute();
		
		createEvent.setCalendarID(calendarID);
		createEvent.setCreatedby(createdby);
		createEvent.setStart(start);
		createEvent.setEnd(end);
		createEvent.setTitle(name);
		createEvent.setText(text);
		createEvent.setType(type);
		
		stringToBeReturned = gson.toJson(createEvent);
		return stringToBeReturned;
	}

	/**
	 * Allows the client to delete a calendar
	 * 
	 * @param
	 * @return String message
	 */

	public String share(ArrayList<String> sharedUsers, String calendarID,
			String email) throws SQLException {

		if (sharedUsers != null) {
			String[] values = { "calendarID", "createdby" };
			resultSet = qb.selectFrom(values, "calendar").all().ExecuteQuery();
					
			while (resultSet.next()) {

				// Tjekker at kalenderen findes, og at det ikke er brugerens CBS
				// kalender

				if (resultSet.getInt("calendarID") == (Integer
						.valueOf(calendarID))) {
					for (String su : sharedUsers) {
						System.out.println("for loop køres");
						String[] fields = { "email", "calendarID" };
						String[] value = { su, String.valueOf(calendarID) };
						qb.insertInto("calendar_users", fields).values(value)
								.Execute();
					}
					stringToBeReturned = "Calendar has been shared";
				} else {
					stringToBeReturned = "calendarID does not exist or the calendar cannot be shared because of restrictions";
				}

			}

			System.out.println("LOG");
			return stringToBeReturned;
		} else
			return "";
	}

	public String deleteCalendar(String userName, String calendarName)
			throws SQLException {
		testConnection();
		stringToBeReturned = removeCalendar(userName, calendarName);

		return stringToBeReturned;
	}

	public String removeCalendar(String userName, String calendarName)
			throws SQLException {
		String usernameOfCreator = "";
		String calendarExists = "";
		resultSet = qb.selectFrom("calendar").where("Name", "=", calendarName)
				.ExecuteQuery();

		// ("select * from calendar where Name = '"+calendarName+"';");
		while (resultSet.next()) {
			calendarExists = resultSet.toString();
		}
		if (!calendarExists.equals("")) {
			String[] value = { "CreatedBy" };
			resultSet = qb.selectFrom(value, "calendar")
					.where("Name", "=", calendarName).ExecuteQuery();
			while (resultSet.next()) {
				usernameOfCreator = resultSet.toString();
				System.out.println(usernameOfCreator);
			}
			if (!usernameOfCreator.equals(userName)) {
				stringToBeReturned = "Only the creator of the calendar is able to delete it.";
			} else {
				String[] keys = { "Active" };
				String[] values = { "2" };
				qb.update("calendar", keys, values)
						.where("Name", "=", calendarName).Execute();
				stringToBeReturned = "Calendar has been set inactive";
			}
			stringToBeReturned = resultSet.toString();
		} else {
			stringToBeReturned = "The calendar you are trying to delete, does not exists.";
		}

		return stringToBeReturned;
	}

	public String CreateNote(CreateNote cn) {

		nm.setCreatedBy(cn.getCreatedBy());

		nm.setEventID(cn.getEventID());

		nm.setText(cn.getText());

		note.CreateNote(nm);
		return stringToBeReturned;
	}

	public String deleteEvent(String userID, String eventID)
			throws SQLException {

		String stringToBeReturned = "";
		testConnection();
		stringToBeReturned = removeEvent(userID, eventID);

		return stringToBeReturned;

	}

	public String removeEvent(String userID, String eventID)
			throws SQLException {

		String stringToBeReturend = "";
		String createdBy = "";
		resultSet = qb.selectFrom("events")
				.where("eventID", "=", String.valueOf(eventID)).ExecuteQuery();

		while (resultSet.next()) {
			System.out.println("createdby ledes efter: ");
			createdBy = resultSet.getString("createdby");
			System.out.println(createdBy);
		}
		if (!createdBy.equals(userID) || userID.equals("admin")) {
			stringToBeReturend = "Only the creator of the event is able to delete it.";
		} else {
			String[] keys = { "active" };
			String[] values = { "0" };
			qb.update("events", keys, values)
					.where("eventID", "=", String.valueOf(eventID)).Execute();
			stringToBeReturend = "event has been set inactive";
		}

		return stringToBeReturend;

	}

	public String getDailyUpdate() throws SQLException {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datetime = sdf.format(date) + " 12:00:00";
		resultSet = qb.selectFrom("dailyupdate").where("date", "=", datetime)
				.ExecuteQuery();
		while (resultSet.next()) {
			gdu.setCelsius(String.valueOf(resultSet
					.getDouble("apparentTemperature")));
			gdu.setDate(resultSet.getDate("date").toString());
			gdu.setDesc(resultSet.getString("summary"));
			gdu.setQuote(resultSet.getString("qotd"));
		}
		String gsonString = gson.toJson(gdu);

		return gsonString;
	}

	public String deleteNote(String uID, String nID) throws SQLException {

		resultSet = qb.selectFrom("notes").where("noteID", "=", nID)
				.ExecuteQuery();
		System.out.println("Checkpoint 1");
		while (resultSet.next()) {
			if (uID.equals(resultSet.getString("createdBy"))) {
				String[] fields = { "active" };
				String[] values = { "0" };
				qb.update("notes", fields, values).where("noteID", "=", nID)
						.Execute();
				stringToBeReturned = "Note has been deleted";
			} else
				stringToBeReturned = "You are not authorized to delete this note.";
		}

		return stringToBeReturned;
	}
	
	public String getEvents(String calendarID) throws SQLException{
		
		
		resultSet = qb.selectFrom("events").where("calendarId", "=", calendarID).ExecuteQuery();
		while(resultSet.next()){
		ge.setEventId(resultSet.getInt("eventid"));
		ge.setName(resultSet.getString("name"));
		
		
		stringToBeReturned = gson.toJson(ge);
		}
		
		
		return stringToBeReturned;
	}

	public String clientLogin(String email, String password)
			throws SQLException {

		String gsonString = "";
		String[] values = { "email", "password", "userID" };
		resultSet = qb.selectFrom(values, "users").where("email", "=", email)
				.ExecuteQuery();
		GetCalendarData g = new GetCalendarData();
		if (resultSet.next()) {
			System.out.println("res = true");
			e.setEmail(email);

			String calendarName = "CBScalendar " + email;

			// Sørger for at der eksisterer en CBS kalender i databasen til
			// brugeren
			ArrayList<String> s = new ArrayList<String>();
			addNewCalendar(calendarName, 0, "CBS", s, 1);

			// Opdaterer databasens CBS events
			try {
				gcd.getDataFromCalendar();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			clientLogin.setLoggedIn(true);
			CalendarEvents ce = new CalendarEvents(email);
			clientLogin.setCalendars(ce.getCalendars());
			resultSet = qb.selectFrom("roles").where("email", "=", email)
					.ExecuteQuery();
			resultSet.next();
			clientLogin.setRole(resultSet.getString("type"));
			clientLogin.setEmail(email);
			gsonString = gson.toJson(clientLogin);

		} else
			gsonString = "Login failed";

		return gsonString;
	}

	public String clientLogout(String email) throws SQLException {
		String gsonString = "";
		String[] fields = { "active" };
		String[] values = { "0" };
		qb.update("users", fields, values).where("email", "=", email).Execute();
		return gsonString;
	}
}
