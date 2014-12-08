package model.calendar;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.QueryBuild.QueryBuilder;
import JsonClasses.CreateEvent;

import com.google.gson.Gson;



public class GetCalendarData {
	
	EncryptUserID e = new EncryptUserID();
	QueryBuilder qb = new QueryBuilder();
	ResultSet rs;
	
	
	//henter data fra URL og l??er ind til en string
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    //Nu har vi alle data liggende i en string (JSON). 
    //Saa bruger vi Google's udviklede library Json string. den kan lave det om til java objekter
    //Events laver en arraylist af Event
    
    /**
     * Allows client to retrieve CBS's calendar and then access it.
     * @throws Exception
     */
    public void getDataFromCalendar() throws Exception {

        /**
         * Get URL From calendar.cbs.dk -> Subscribe -> change URL to end with .json
         * Encrypt hash from
         */
    	String email = e.getEmail();
	    String json = readUrl("http://calendar.cbs.dk/events.php/"+ email + "/" + e.getKey() + ".json");
	    System.out.println(json);

        Gson gson = new Gson();
        GetCBSevents cbsEvents = gson.fromJson(json, GetCBSevents.class);
        
	    //Check whether course exists in database
        String[] values = {"courseid"};
        String[] calendarFields = {"name", "CreatedBy", "privatepublic", "isCBS"};
        
        rs = qb.selectFrom(values, "courses").all().ExecuteQuery();
        ResultSet rs2;
        
        //ArrayList will contain one String representation of the different
        //course titles of the JSON string from CBS Calendar
        ArrayList<String> courseTitles = new ArrayList<String>();
        for (CBSevents cbse : cbsEvents.getEvents()){
        	
        	//Adds course titles to arraylist courseTitles if they do not already
        	//exist in the arrayList
        	if(!courseTitles.contains(cbse.getTitle())){
        		courseTitles.add(cbse.getTitle());
        	
        	}
        }
        //Iterates over coursetitles to check if any coursetitles do not exist in
        //the database, and adds them if they don't.
        for (String cbse : courseTitles){
        	
        	boolean courseExists = false;
        	//'resets' the resultset rs so the while-loop can iterate from the beginning
        	rs.beforeFirst();
        	while(rs.next()){
        		
        		if(rs.getString("courseid").equals(cbse)){
        			System.out.println("Line 87: courseID = " + rs.getString("courseid") + " equals.cbse: " + cbse );
        			courseExists = true;
        		}
        	}
        	// If course does not exist, add calendar to course and subscribe the user to the course
        	System.out.println("Line 92: courseExists = " + courseExists);
        	if (!courseExists){
        		String[] calendarValues = {cbse, "CBS", "0", "1"};
        		qb.insertInto("calendar", calendarFields).values(calendarValues).Execute();
        		rs2 = qb.selectFrom("calendar").where("name", "=", cbse).ExecuteQuery();
        		rs2.next();
        		String [] fields = {"courseid", "calendarID"};
        		String [] courseValues = {cbse, String.valueOf(rs2.getInt("calendarid"))};
        		qb.insertInto("courses", fields).values(courseValues).Execute();
        	}
        }
        	
        ArrayList<String> calendarID = new ArrayList<String>();
        
        //Retrieves calendar ID's from the calendars related to the courseTitles
	    for(String ct : courseTitles){	    
		    rs = qb.selectFrom("courses").where("courseid", "=", ct).ExecuteQuery();
		    
		    while(rs.next())
		    {
		    	calendarID.add(rs.getString("calendarID"));
		    }
	    }
	    CreateEvent cbsEv = new CreateEvent();
	    ArrayList <CreateEvent> dbArray = new ArrayList<CreateEvent>();
	    //Iterates over the calendarID's to retrieve the related events. Adds the events
	    //to an ArrayList.
        for(String cid : calendarID){
        	
	        rs = qb.selectFrom("events").where("calendarID", "=", cid).ExecuteQuery();
	       
	        
	        while(rs.next()){
	        	cbsEv.setType(rs.getString("Type"));
	        	cbsEv.setLocation(rs.getString("Location"));
	        	cbsEv.setCreatedby(rs.getString("CreatedBy"));
	        	cbsEv.setTitle(rs.getString("Name"));
	        	cbsEv.setText(rs.getString("text"));
	        	cbsEv.setCalendarID(cid);
	        	cbsEv.setStart(rs.getString("start"));
	        	cbsEv.setEnd(rs.getString("end"));
	        	cbsEv.setCBSeventID(rs.getString("CBSeventID"));
	           	dbArray.add(cbsEv);	
	        	cbsEv = new CreateEvent();
	        }
        }
        int m = 0;
        //Iterates over all the events, and finds the corresponding event from the database
        //to check if any information has been changed, and updates the database if it has.
        for (CBSevents cbs : cbsEvents.getEvents()){

        	 //	formats the CBS dates to match the database dates
        	String start ="";
        	m = (Integer.parseInt(cbs.getStart().get(1))) + 1;
        	start = String.format("%s-%d-%s %s:%s:00", 
        			cbs.getStart().get(0), 
					m,
					cbs.getStart().get(2),
					cbs.getStart().get(3),
					cbs.getStart().get(4));
        	System.out.println("stringStart: " + start);
        	
        	String end ="";
        	m = (Integer.parseInt(cbs.getEnd().get(1))) + 1;
        	end = String.format("%s-%d-%s %s:%s:00", 
        			cbs.getEnd().get(0), 
        			m,
					cbs.getEnd().get(2),
					cbs.getEnd().get(3),
					cbs.getEnd().get(4));
        	
        	//Retrieves the event with given eventID from ArrayList dbArray
            cbsEv = getArray(cbs.getEventid(), dbArray);            	
          
            String cid = "";
            if	(cbsEv.getStart()==null){
            	rs = qb.selectFrom("calendar").where("name", "=", cbs.getTitle()).ExecuteQuery();
            	rs.next();
            	cid = rs.getString("calendarid");
            }
            else
            	cid = cbsEv.getCalendarID();
        
            
            if	(	 cbsEv.getStart()==null							||
            		!cbsEv.getStart().equals(start)		 			||
            		!cbsEv.getEnd().equals(end)						||
            		!cbsEv.getType().equals(cbs.getType())			||
            		!cbsEv.getTitle().equals(cbs.getTitle()) 		||
            		!cbsEv.getText().equals(cbs.getDescription())	||
            		!cbsEv.getLocation().equals(cbs.getLocation())	||
            		!cbsEv.getText().equals(cbs.getDescription())	
        		)
            {
		        String[] fields = {"activityID", "createdby", "CBSeventid", "type", "name", "text", "location", "start", "end", "calendarid"};
		        String[] values1 = {	cbs.getActivityid(),
			        					"CBS",
			        					cbs.getEventid(),
			        					cbs.getType(),
			        					cbs.getTitle(),
			        					cbs.getDescription(),
			        					cbs.getLocation(),
			        					start,
			        					end,
			        					cid};
		
		        try{
		        qb.insertInto("events", fields).values(values1).Execute();
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
            }   else System.out.println("if = false");
            
        }
    }
    public CreateEvent getArray(String CBSeventID, ArrayList<CreateEvent> al){
    	for(CreateEvent temp: al){
    		if(temp.getCBSeventID().equals(CBSeventID))
    			return temp;
    	}
    	CreateEvent empty = new CreateEvent();
    	return empty;
    	
    }
}
