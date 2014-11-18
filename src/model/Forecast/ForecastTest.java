package model.Forecast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit.*;

import model.QOTD.QOTD;
import model.QOTD.QOTDModel;

public class ForecastTest {

	// Main metode til at koere en test af vejrudsigt funktionen
    public static void main(String[] args) throws SQLException {

        ForecastModel fm = new ForecastModel();
        QOTDModel q = new QOTDModel();
//        q.saveQuote();
//        fm.requestForecast();
//        ArrayList<Forecast> forecastList = fm.getForecast();
//        
//        for (int i = 0; i < forecastList.size(); i++) {
//        	System.out.println(forecastList.get(i).toString());
//        	
//		}
       addt();
    }
      public static void addt(){
    	  ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(5);
    	  s.scheduleAtFixedRate(new QOTDModel(), 0, 1, TimeUnit.SECONDS);
   	  s.scheduleAtFixedRate(new ForecastModel(), 0, 1, TimeUnit.MINUTES);
    	  System.out.println("thread" + Thread.activeCount());
      }
       
       
    

}
