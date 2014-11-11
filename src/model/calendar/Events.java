package model.calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import model.QueryBuild.QueryBuilder;

/**
 * Created by jesperbruun on 10/10/14.
 * Den laver selve arrayet af alle generede Event
 */
public class Events {
    ArrayList<UserEvent> events = new ArrayList<UserEvent>();
    QueryBuilder qb = new QueryBuilder();

    public ArrayList<UserEvent> getEvents() {
    	QueryBuilder qb = new QueryBuilder();
    	try {
			ResultSet rs = qb.selectFrom("events").all().ExecuteQuery();
			while (rs.next())
			{
				//String values from SQL database (must be created)
				int eventID = rs.getInt("eventid");
				int type = rs.getInt("type");
				int location = rs.getInt("location");
				int createdby = rs.getInt("createdby");

				String nameEvent = rs.getString("name");
				String text = rs.getString("text");
				int customevent = rs.getInt("customevent");
				int CalendarID = rs.getInt("CalendarID");
				String stringEventID = String.valueOf(eventID);
				String stringType = String.valueOf(type);
				String stringLocation = String.valueOf(location);
				String stringCreatedby = String.valueOf(createdby);

			

				String customEvent = String.valueOf(customevent);
				String calId = String.valueOf(CalendarID);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String stringStartDate = sdf.format(rs.getDate("start"));
				String stringEndDate = sdf.format(rs.getDate("end"));

				System.out.println(stringStartDate);
				
				
				events.add(new UserEvent( stringEventID, stringType, stringLocation,stringCreatedby, stringStartDate, stringEndDate , nameEvent, text,customEvent,calId ));
//				events.add(new Event(stringEventID, stringEventID, stringType, stringType, stringLocation, stringLocation,stringCreatedby, alStart, alEnd));
				//er det noen grunn til at det tillegges to ganger p� fler av variablene?
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return events;
    }


    public void setEvents(ArrayList<UserEvent> event) {
        this.events = event;
    }
    
    // Konverterer array events til en tekst streng
    @Override
    public String toString() {
        return Arrays.toString(events.toArray());
    }
    
//    public static void main(String []args){
//    	Events Hej = new Events();
//    	
//    	Hej.getEvents();
//    	System.out.println(Hej.toString());
//
//    }
}