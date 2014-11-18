package model.calendar;

import java.util.ArrayList;

/**
 * Created by jesperbruun on 10/10/14. Til hver specifik event bliver de
 * defineret saaledes.
 */
public class UserEvent {
	// private String activityid;
	private String eventid;
	private String type;
	private String title;
	private String text;
	private String location;
	private String createdby;
	private String start;
	private String end;
	private String customevent;
	private String calendarID;
//	String activityid
	public UserEvent( String eventid, String type,
			String location, String createdby, String start,
			String end, String title, String text,
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

	public void setStart(String start) {
		this.start = start;
	}

	public String getStart() {
		return start;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getEnd() {
		return end;
	}

}
