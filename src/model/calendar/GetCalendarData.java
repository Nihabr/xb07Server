package model.calendar;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.QueryBuild.QueryBuilder;
import JsonClasses.CreateEvent;

import com.google.gson.Gson;


/**
 * Created by jesperbruun on 13/10/14.
 */
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
    @SuppressWarnings("null")
	public void getDataFromCalendar() throws Exception {

        /**
         * Get URL From calendar.cbs.dk -> Subscribe -> change URL to end with .json
         * Encrypt hash from
         */
    	String email = e.getEmail();
	    String json = readUrl("http://calendar.cbs.dk/events.php/"+ email + "/" + e.getKey() + ".json");
	    System.out.println(json);
	    
	    rs = qb.selectFrom("calendar").where("name", "=", "CBScalendar " + email).ExecuteQuery();
	    int calID = 0;
	    
	    
	    while (rs.next())
	    	calID = rs.getInt("calendarID");
	    
        Gson gson = new Gson();
        GetCBSevents cbsEvents = gson.fromJson(json, GetCBSevents.class);
        
        rs = qb.selectFrom("calendar_users").where("email", "=", email).ExecuteQuery();
        boolean exists = false;
        while (rs.next()){
        	if (rs.getInt("calendarID") == calID){
        		exists = true;
        	}
        }
        if (!exists){
    		String[] calUfields = {"calendarID", "email"};
    		String[] calUvalues = {String.valueOf(calID), email};
    		qb.insertInto("calendar_users", calUfields).values(calUvalues).Execute();
        }
        
        rs = qb.selectFrom("events").where("createdby", "=", "CBS").ExecuteQuery();
        ArrayList <CreateEvent> dbArray = new ArrayList<CreateEvent>();
        CreateEvent cbsEv = new CreateEvent();
        while(rs.next()){
        	
        	cbsEv.setType(rs.getString("Type"));
        	cbsEv.setLocation(rs.getString("Location"));
        	cbsEv.setCreatedby(rs.getString("CreatedBy"));
        	cbsEv.setTitle(rs.getString("Name"));
        	cbsEv.setText(rs.getString("text"));
        	cbsEv.setCalendarID(String.valueOf(rs.getInt("CalendarID")));
        	cbsEv.setStart(rs.getString("start"));
        	cbsEv.setEnd(rs.getString("End"));
        	cbsEv.setCBSeventID(rs.getString("CBSeventID"));
        	System.out.println("rs cbseventid: " + rs.getString("CBSeventID"));
        	dbArray.add(cbsEv);	
        	cbsEv = new CreateEvent();
        }
        
        for (CBSevents cbs : cbsEvents.getEvents()){

        	 //	["2014",8,"5","8","00"]
        	String start ="";
        	start = String.format("%s-%s-%s %s:%s:00", 
        			cbs.getStart().get(0), 
					cbs.getStart().get(1),
					cbs.getStart().get(2),
					cbs.getStart().get(3),
					cbs.getStart().get(4));
        	System.out.println("stringStart: " + start);
        	
        	String end ="";
        	end = String.format("%s-%s-%s %s:%s:00", 
        			cbs.getEnd().get(0), 
					cbs.getEnd().get(1),
					cbs.getEnd().get(2),
					cbs.getEnd().get(3),
					cbs.getEnd().get(4));
        	
            cbsEv = getArray(cbs.getEventid(), dbArray);
            
            System.out.println("start1 " + cbsEv.getStart());
            System.out.println("start2 " + start);
            if	(
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
		        String[] values = {	cbs.getActivityid(),
		        					"CBS",
		        					cbs.getEventid(),
		        					cbs.getType(),
		        					cbs.getTitle(),
		        					cbs.getDescription(),
		        					cbs.getLocation(),
		        					start,
		        					end,
		        					String.valueOf(calID)};
		
		        try{
		        qb.insertInto("events", fields).values(values).Execute();
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
            }
            else System.out.println("if = false");
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
