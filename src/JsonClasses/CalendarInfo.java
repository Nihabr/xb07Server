package JsonClasses;

import java.util.ArrayList;

import model.calendar.UserEvent;

public class CalendarInfo implements java.io.Serializable {

		private static final long serialVersionUID = 1L;
		private String overallID = "calendarInfo";
		private String calenderName;
		private String userName;
		private int publicOrPrivate;
		ArrayList<UserEvent> calendars = new ArrayList<>();
		
		
		//Getters and setters
		
		public String getOverallID() {
			return overallID;
		}
		public ArrayList<UserEvent> getCalendars() {
			return calendars;
		}
		public void setCalendars(ArrayList<UserEvent> calendars) {
			this.calendars = calendars;
		}
		public void setOverallID(String overallID) {
			this.overallID = overallID;
		}
		public String getCalenderName() {
			return calenderName;
		}
		public void setCalenderName(String calenderName) {
			this.calenderName = calenderName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public int getPublicOrPrivate() {
			return publicOrPrivate;
		}
		public void setPublicOrPrivate(int publicPrivate) {
			this.publicOrPrivate = publicPrivate;
		}
}