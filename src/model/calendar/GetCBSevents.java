
	package model.calendar;

	import java.util.ArrayList;
import java.util.Arrays;

	/**
	 * Created by jesperbruun on 10/10/14.
	 * Den laver selve arrayet af alle generede Event
	 */
	public class GetCBSevents {
	    ArrayList<CBSevents> events = new ArrayList<CBSevents>();

	    public ArrayList<CBSevents> getEvents() {

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
	    
//	    public static void main(String []args){
//	    	Events Hej = new Events();
//	    	
//	    	Hej.getEvents();
//	    	System.out.println(Hej.toString());
//	    }
	}

