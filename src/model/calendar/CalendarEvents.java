package model.calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.QueryBuild.QueryBuilder;
import JsonClasses.CalendarInfo;
public class CalendarEvents {

	ArrayList <CalendarInfo> calendars = new ArrayList <CalendarInfo>();
	CalendarInfo ci = new CalendarInfo();
	


	QueryBuilder qb = new QueryBuilder();
	
	public CalendarEvents (String email) throws SQLException{

		ResultSet rs = qb.selectFrom("calendar_users").where("email", "=", email).ExecuteQuery();
		while (rs.next()){
			int calID = rs.getInt("CalendarID");
			calendars.add(getUserCalendars(String.valueOf(calID)));
			System.out.println("calendar " + calID + " has been added");
		}
	}

	public ArrayList<CalendarInfo> getCalendars() {
		return calendars;
	}
	
	public CalendarInfo getUserCalendars (String cid) throws SQLException{
		
		CalendarInfo subscribedCalendarEvents = new CalendarInfo();
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
						subscribedCalendarEvents.getCalendars().add(ue);
					}
					System.out.println(ue.getEventid());
					
				}
		rsCalE = qb.selectFrom("calendar").where("calendarid", "=", cid).ExecuteQuery();
		rsCalE.next();
		subscribedCalendarEvents.setCalenderName(rsCalE.getString("name"));
		subscribedCalendarEvents.setPublicOrPrivate(rsCalE.getInt("privatepublic"));
		
		return subscribedCalendarEvents;
	}
}
