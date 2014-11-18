package model.calendar;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.QueryBuild.QueryBuilder;

import com.google.gson.Gson;


/**
 * Created by jesperbruun on 13/10/14.
 */
public class GetCalendarData {
	
	EncryptUserID e = new EncryptUserID();
	QueryBuilder qb = new QueryBuilder();

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
	    
	    ResultSet rs = qb.selectFrom("calender").where("email", "=", email).ExecuteQuery();
	    int calID = rs.getInt("calenderID");
	    
        Gson gson = new Gson();
        GetCBSevents cbsEvents = gson.fromJson(json, GetCBSevents.class);
        
        for (CBSevents cbs : cbsEvents.getEvents()){

        	 //	["2014",8,"5","8","00"]
        	
        	String start ="";
        	start = String.format("%s-%s-%s %s:%s:00", 
        			cbs.getStart().get(1), 
					cbs.getStart().get(2),
					cbs.getStart().get(3),
					cbs.getStart().get(4),
					cbs.getStart().get(5));
        	System.out.println("stringStart: " + start);
        	
        	String end ="";
        	end = String.format("%s-%s-%s %s:%s:00", 
        			cbs.getEnd().get(1), 
					cbs.getEnd().get(2),
					cbs.getEnd().get(3),
					cbs.getEnd().get(4),
					cbs.getEnd().get(5));
    			
        String[] fields = {"activityID", "CBSeventid", "type", "name", "text", "location", "start", "end", "calendarid"};
        String[] values = {	cbs.getActivityid(),
        					cbs.getEventid(),
        					cbs.getType(),
        					cbs.getTitle(),
        					cbs.getDescription(),
        					cbs.getLocation(),
        					start,
        					end,
        					String.valueOf(calID)};
        
        qb.insertInto("events", fields).values(values).Execute();
        
        String[] calUfields = {"calendarID", "email"};
        String[] calUvalues = {String.valueOf(calID), email};
        qb.insertInto("calender_users", calUfields).values(calUvalues).Execute();
        }

    }
}
