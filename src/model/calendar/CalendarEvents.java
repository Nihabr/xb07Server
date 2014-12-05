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
	
	/**Will set 'ArrayList <CalendarInfo> calendars' based on the
	 * subscriptions of the given email. calendars can be fetched
	 * using the 'getCalendars(String calendarID)' method.
	 * @author Niklas Broge
	 * @param email
	 * @throws SQLException
	 */
	public CalendarEvents (String email) throws SQLException{

		ResultSet rs = qb.selectFrom("calendar_users").where("email", "=", email).ExecuteQuery();
		while (rs.next()){
			int calID = rs.getInt("CalendarID");
			calendars.add(getUserCalendars(String.valueOf(calID)));
			System.out.println("calendar " + calID + " has been added");
		}
	}
	
	/**Returns object CalendarInfo with the calendars the email
	 * is subscribed to. CalendarInfo also contains the events
	 * bound to the different calendars.
	 * @author Niklas Broge
	 * @param cid
	 * @return calendars
	 * @throws SQLException
	 */
	public ArrayList<CalendarInfo> getCalendars() {
		return calendars;
	}

	/**Fetches events bound to given calendarID and stores them in an object
	 * of CalendarInfo, along with the name and private public status of the
	 * calendar.
	 * @author Niklas Broge
	 * @param cid
	 * @return CalendarInfo
	 * @throws SQLException
	 */
	public CalendarInfo getUserCalendars (String cid) throws SQLException{
		
		CalendarInfo subscribedCalendarEvents = new CalendarInfo();

		
			ResultSet rs = qb.selectFrom("events").where("CalendarID", "=", String.valueOf(cid)).ExecuteQuery();
			
				while (rs.next()){
					if(rs.getInt("active")!=0){
						UserEvent ue = new UserEvent( 0, "", "", "", "", "", "", "", 0);
						ue.setEventid(rs.getInt("eventid"));
						ue.setCalendarID(rs.getInt("calendarID"));
						ue.setCreatedby(rs.getString("createdby"));
						ue.setType(rs.getString("type"));
						ue.setEnd(rs.getString("end"));
						ue.setStart(rs.getString("start"));
						ue.setText(rs.getString("text"));
						ue.setTitle(rs.getString("name"));
						ue.setLocation(rs.getString("location"));
						subscribedCalendarEvents.getCalendars().add(ue);
					}
					
				}
		rs = qb.selectFrom("calendar").where("calendarid", "=", cid).ExecuteQuery();
		rs.next();
		subscribedCalendarEvents.setCalenderName(rs.getString("name"));
		subscribedCalendarEvents.setPublicOrPrivate(rs.getInt("privatepublic"));
		subscribedCalendarEvents.setCalendarId(rs.getInt("calendarId"));
		return subscribedCalendarEvents;
	}
}