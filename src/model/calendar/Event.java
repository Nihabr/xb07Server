package model.calendar;

import java.util.ArrayList;

/**
 * Created by jesperbruun on 10/10/14. Til hver specifik event bliver de
 * defineret saaledes.
 */
public class Event {
	// private String activityid;
	private String eventid;
	private String type;
	private String title;
	private String text;
	private String location;
	private String createdby;
	private ArrayList<String> start;
	private ArrayList<String> end;
	private String customevent;
	private String calendarID;
//	String activityid
	public Event( String eventid, String type,
			String location, String createdby, ArrayList<String> start,
			ArrayList<String> end, String title, String text,
			String customevent, String calendarID) {
		super();
		// this.activityid = activityid;
		this.eventid = eventid;
		this.type = type;
		this.title = title;
		this.text = text;
		this.location = location;
		this.createdby = createdby;
		this.start = start;
		this.end = end;
		this.customevent = customevent;
		this.calendarID = calendarID;
	}

	// Settere og gettere for Event objektet
//	public void setActivityid(String activityid) {
//		this.activityid = activityid;
//	}
//
//	public String getActivityid() {
//		return activityid;
//	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getCustomevent() {
		return customevent;
	}

	public void setCustomevent(String customevent) {
		this.customevent = customevent;
	}

	public String getCalendarID() {
		return calendarID;
	}

	public void setCalendarID(String calendarID) {
		this.calendarID = calendarID;
	}

	public String getEventid() {
		return eventid;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setStart(ArrayList<String> start) {
		this.start = start;
	}

	public ArrayList<String> getStart() {
		return start;
	}

	public void setEnd(ArrayList<String> end) {
		this.end = end;
	}

	public ArrayList<String> getEnd() {
		return end;
	}

}
