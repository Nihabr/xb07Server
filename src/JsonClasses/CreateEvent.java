package JsonClasses;

public class CreateEvent {


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
		
		private  final long serialVersionUID = 1L;
		private String overallID = "createEvent";
//		String activityid
		public CreateEvent(){ 
		}
		
		public String getOverallID() {
			return overallID;
		}

		public void setOverallID(String overallID) {
			this.overallID = overallID;
		}

		public long getSerialVersionUID() {
			return serialVersionUID;
		}

		public String getEventid() {
			return eventid;
		}
		public void setEventid(String eventid) {
			this.eventid = eventid;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getCreatedby() {
			return createdby;
		}
		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}
		public String getStart() {
			return start;
		}
		public void setStart(String start) {
			this.start = start;
		}
		public String getEnd() {
			return end;
		}
		public void setEnd(String end) {
			this.end = end;
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



	}


