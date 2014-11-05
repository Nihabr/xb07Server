package model.calendar;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by jesperbruun on 13/10/14.
 */
public class GetCalendarData {
	
	EncryptUserID e = new EncryptUserID();

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
    	String userID = "1234";
//        String json = readUrl("http://calendar.cbs.dk/events.php/"+userID+"/"+e.getKey()+".json");
        String json = readUrl("http://calendar.cbs.dk/events.php/jolj13ab/c8376a342ad9d756d007125edaa281b3.json");
        

        Gson gson = new Gson();
        Events events = gson.fromJson(json, Events.class); 

        //tester events activityID's
        for (int i = 0; i < events.getEvents().size(); i++){
            System.out.println(events.getEvents().get(i).getActivityid());
        }
    }
}
