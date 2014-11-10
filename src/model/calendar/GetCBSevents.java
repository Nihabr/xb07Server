
	package model.calendar;

	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Time;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Date;

	import model.QueryBuild.QueryBuilder;

	/**
	 * Created by jesperbruun on 10/10/14.
	 * Den laver selve arrayet af alle generede Event
	 */
	public class GetCBSevents {
	    ArrayList<CBSevents> events = new ArrayList<CBSevents>();

	    public ArrayList<CBSevents> getEvents() {
	    	QueryBuilder qb = new QueryBuilder();
	    	try {
				ResultSet rs = qb.selectFrom("events").all().ExecuteQuery();
				while (rs.next())
				{
					//String values from SQL database (must be created)
					
//					int location = rs.getInt("location");
//					int createdby = rs.getInt("createdby");
					
					String eventID = rs.getString("eventid");
					String type = rs.getString("type");
					
					Date startDate = rs.getDate("start");
					Time startTime = rs.getTime("start");
					
					Date endDate = rs.getDate("end");
					Time endTime = rs.getTime("end");
					
					
					String activityID = rs.getString("activityid");
					
					String nameEvent = rs.getString("name");
					String text = rs.getString("text");
					
					String title = rs.getString("title");
					String description = rs.getString("description");
					
					String location = rs.getString("location");
					
					
//					String stringLocation = String.valueOf(location);
//					String stringCreatedby = String.valueOf(createdby);
					String stringStartDate = String.valueOf(startDate);
					String stringStartTime = String.valueOf(startTime);				
					String stringEndDate = String.valueOf(endDate);
					String stringEndTime = String.valueOf(endTime);
					
					ArrayList<String> alStart = new ArrayList<String>();
					alStart.add(stringStartDate + "" + stringStartTime);
					
					ArrayList<String> alEnd = new ArrayList<String>();
					alEnd.add(stringEndDate + "" + stringEndTime);
					
					
					events.add(new CBSevents(activityID, eventID, type, title, description, alStart, alEnd,location));				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    	return events;
	    }

	    public void setEvents(ArrayList<CBSevents> event) {
	        this.events = event;
	    }
	    
	    // Konverterer array events til en tekst streng
	    @Override
	    public String toString() {
	        return Arrays.toString(events.toArray());
	    }
	    
	    public static void main(String []args){
	    	Events Hej = new Events();
	    	
	    	Hej.getEvents();
	    	System.out.println(Hej.toString());
	    }
	}

