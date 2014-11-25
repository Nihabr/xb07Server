package model.calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.QueryBuild.QueryBuilder;
public class CalendarEvents {

	ArrayList <ArrayList<UserEvent>> calendars = new ArrayList <ArrayList<UserEvent>>();
	
	public ArrayList<ArrayList<UserEvent>> getCalendars() {
		return calendars;
	}

	QueryBuilder qb = new QueryBuilder();
	
	public CalendarEvents (String email) throws SQLException{

		ResultSet rs = qb.selectFrom("calendar_users").where("email", "=", email).ExecuteQuery();
		while (rs.next()){
			int CalID = rs.getInt("CalendarID");
			calendars.add(getUserCalendars(String.valueOf(CalID)));
		}
	}

	public ArrayList<UserEvent> getUserCalendars (String cid) throws SQLException{
		
		ArrayList <UserEvent> subscribedCalendarEvents = new ArrayList<UserEvent>();
		UserEvent ue = new UserEvent( 0, "", "", "", "", "", "", "", 0);
		
			ResultSet rsCalE = qb.selectFrom("events").where("CalendarID", "=", String.valueOf(cid)).ExecuteQuery();
			
				while (rsCalE.next()){
					if(rsCalE.getInt("active")!=0){
						ue.setEventid(rsCalE.getInt("eventid"));
						ue.setCalendarID(rsCalE.getInt("calendarID"));
						ue.setCreatedby(rsCalE.getString("createdby"));
						ue.setType(rsCalE.getString("type"));
						ue.setEnd(rsCalE.getString("end"));
						ue.setStart(rsCalE.getString("start"));
						ue.setText(rsCalE.getString("text"));
						ue.setTitle(rsCalE.getString("name"));
						ue.setLocation(rsCalE.getString("location"));
					}
					subscribedCalendarEvents.add(ue);
				}
					
		
		return subscribedCalendarEvents;
	}
}
