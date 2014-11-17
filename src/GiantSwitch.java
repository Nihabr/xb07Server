import java.sql.SQLException;

import model.QOTD.QOTDModel;
import model.calendar.UserEvent;
import model.note.Note;
import JsonClasses.CalendarInfo;
import JsonClasses.ClientLogin;
import JsonClasses.CreateCalender;
import JsonClasses.CreateEvent;
import JsonClasses.CreateNote;
import JsonClasses.DeleteCalender;
import JsonClasses.DeleteEvent;
import JsonClasses.DeleteNote;

import com.google.gson.*;

import databaseMethods.SwitchMethods;

public class GiantSwitch {
	
	
	
	public String GiantSwitchMethod(String jsonString) throws SQLException {

		//Events eventsKlasse = new Events(0, 0, 0, jsonString, jsonString, jsonString, jsonString, jsonString);

		//ForecastModel forecastKlasse = new ForecastModel();
		QOTDModel QOTDKlasse = new QOTDModel();
		SwitchMethods SW = new SwitchMethods();
		
		Gson gson = new GsonBuilder().create();
		String answer = "";	
		//Creates a switch which determines which method should be used. Methods will be applied later on
		switch (Determine(jsonString)) {
		//If the Json String contains one of the keywords below, run the relevant method.

		/**********
		 ** LOGIN **
		 **********/
		case "logIn":
			ClientLogin cl = (ClientLogin)gson.fromJson(jsonString, ClientLogin.class);
			answer = SW.clientLogin(cl.getEmail(), cl.getPassWord());
			break;

		case "logOut":
			System.out.println("Recieved logOut");
			break;

		/*************
		 ** CALENDAR **
		 *************/
		case "createCalender":
			CreateCalender CC = (CreateCalender)gson.fromJson(jsonString, CreateCalender.class);
			System.out.println(CC.getCalenderName()+ "Den har lagt det nye ind i klassen");
			answer = SW.createNewCalender(CC.getUserName(), CC.getCalenderName(), CC.getPublicOrPrivate());
			
			
			
			break;
		
		case "deleteCalender":
			DeleteCalender DC = (DeleteCalender)gson.fromJson(jsonString, DeleteCalender.class);
			System.out.println(DC.getCalenderName()+ "Den har lagt det nye ind i klassen");
			answer = SW.deleteCalender(DC.getUserName(), DC.getCalenderName());
			break;
		

		case "getEvents":
			
			
			System.out.println("Recieved getEvents");
			break;

		case "createEvent":
			
			CreateEvent ce = (CreateEvent)gson.fromJson(jsonString, CreateEvent.class);
			
			System.out.println("Recieved new event" + ce.getEventid());
			answer = SW.createEvent(ce.getEventid(), ce.getType(), ce.getLocation(),
					ce.getCreatedby(),ce.getStart(), ce.getEnd(), ce.getTitle(), ce.getText(), ce.getCustomevent(), ce.getCalendarID());
			break;

		case "getEventInfo":
			System.out.println("Recieved getEventInfo");
			break;
			
		case "deleteEvent":
			
			DeleteEvent de = (DeleteEvent)gson.fromJson(jsonString, DeleteEvent.class);
			
			answer = SW.deleteEvent(de.getUserID(), de.getEventId());
			System.out.println("Event" + de.getEventId() +"is deleted");
			
			System.out.println("Recieved deleteEvent");
			break;
			
		case "createNote":
			//Niklas
			CreateNote cn = (CreateNote)gson.fromJson(jsonString, CreateNote.class);
			SW.CreateNote(cn);
			System.out.println("Recieved saveNote");
			break;
			
		case "deleteNote":
			System.out.println("Recieved deleteNote");
			DeleteNote dn = (DeleteNote)gson.fromJson(jsonString, DeleteNote.class);
			answer = SW.deleteNote(dn.getUserID(), dn.getNoteID());
			break;

		/*****************
		 ** DAILYUPDATE **
		 *****************/
		case "getDailyUpdate":
			answer = SW.getDailyUpdate();
			break;
		
		default:
			System.out.println("Error");
			break;
		}
		return answer;
		
	}

	//Creates a loooong else if statement, which checks the JSon string which keyword it contains, and returns the following 
	//keyword if
	public String Determine(String ID) {

		if (ID.contains("getEvents")) {
			return "getEvents";
		} else if (ID.contains("getEventInfo")) {
			return "getEventInfo";
		} else if (ID.contains("saveNote")) {
			return "saveNote";
		} else if (ID.contains("getNote")) {
			return "getNote";
		} else if (ID.contains("deleteNote")){
			return "deleteNote";
		}else if  (ID.contains("deleteCalender")){
			return "deleteCalender";
		} else if (ID.contains("getClientForecast")) {
			return "getClientForecast";
		} else if (ID.contains("saveImportedCalender")) {
			return "saveImportedCalender";
		}else if (ID.contains("importCourse")) {
			return "importCourse";
		} else if (ID.contains("exportCourse")) {
			return "exportCourse";
		} else if (ID.contains("getDailyUpdate")) {
			return "getDailyUpdate";
		} else if (ID.contains("logIn")) {
			return "logIn";
		} else if (ID.contains("logOut")) {
			return "logOut";
		} else if (ID.contains("getCalender")) {
			return "getCalender";
		} else if (ID.contains("createEvent")) {
			return "createEvent";
		} else if (ID.contains("deleteEvent")) {
			return "deleteEvent"; 
		} else if (ID.contains("createCalender")) {
			return "createCalender";
		} else if (ID.contains("createNote")){
			return "createNote";
		}
		

		else
			return "error";
	}
	

}
