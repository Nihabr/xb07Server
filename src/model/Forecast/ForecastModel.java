package model.Forecast;

import model.QueryBuild.QueryBuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ForecastModel implements Runnable {

	     // Json parser to retrieve and map data from openweathermap.org
	     private ArrayList<Forecast> forecastList = new ArrayList<Forecast>();
	     private String weatherDescription = "";
	     QueryBuilder qb = new QueryBuilder();


	     public void run(){
	    	 updateFc();
	     }
	     // 
	     public ArrayList<Forecast> requestForecast() {
	         URL url;
	         HttpURLConnection conn;
	         BufferedReader rd;
	         String line;

	         String result = "";

	         try {
	             url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?lat=55.681589&lon=12.529092&cnt=14&mode=json&units=metric");
	             conn = (HttpURLConnection) url.openConnection();
	             conn.setRequestMethod("GET");
	             
	             //henter indholder fra hjemmesiden efter vi har aabnet en forbindelse
	             rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	             while ((line = rd.readLine()) != null) {
	             	
	             	//vi skal ligge alle linjerne oven i hinanden
	                 result += line;
	             }
	             rd.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         } 

	         try {
	             JSONParser jsonParser = new JSONParser();
	             JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

	             // get an array from the JSON object
	             JSONArray list = (JSONArray) jsonObject.get("list");

	             Iterator i = list.iterator();

	             // take each value from the json array separately
	             while (i.hasNext()) {

	                 JSONObject innerObj = (JSONObject) i.next();

	                 Date date = new Date( (long) innerObj.get("dt") * 1000L);
	                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                 String date_string = sdf.format(date);                
	                 
	                 JSONObject temp = (JSONObject) innerObj.get("temp");
	                 double celsius = ((Double) temp.get("day")).doubleValue();
	                 
	                 String temperatur = String.valueOf(celsius);
	                 JSONArray subList = (JSONArray) innerObj.get("weather");

	                 Iterator y = subList.iterator();

	                 while (y.hasNext()) {
	                     JSONObject childObj = (JSONObject) y.next();

	                     weatherDescription = (String) childObj.get("description");

	                 }
	                 
	                 forecastList.add(new Forecast(date_string, temperatur, weatherDescription));
	                 
	             }
	         } catch (ParseException ex) {
	             ex.printStackTrace();
	         } catch (NullPointerException ex) {
	             ex.printStackTrace();
	         }
	         return forecastList;
	     }
	     
	     // Henter vejrudsigten og gemmer de hentede data i en ArrayList
	     public ArrayList<Forecast> getForecast() throws SQLException{
	     	QueryBuilder qb = new QueryBuilder();
	     	Date date = new Date(); // Current date & time

	     		// Query database and fetch existing weather data from db
	     		ResultSet forecast = null;
	     		try {
	     			forecast = qb.selectFrom("dailyupdate").where("msg_type", "=", "fc").ExecuteQuery();
					// Method to add these ResultSet values to ArrayList needs to be created

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     		
	     		//Do something nice with ResultSet in order to make it into an ArrayList
	     		try {
					while(forecast.next()){
						
						date = forecast.getDate("date");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String date_string = sdf.format(date); 
						String temperatur = String.valueOf(forecast.getDouble("apparentTemperature"));
						String weatherDescription = forecast.getString("summary");
						
						forecastList.add(new Forecast(date_string, temperatur, weatherDescription));
					}
						return forecastList;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     		return null;
	     	}
	     


private void updateFc(){

	forecastList = requestForecast();

	for (Forecast fc : forecastList) {
		String dateTime = (fc.getDate());
		String temperature = fc.getCelsius().toString();
		String summary = fc.getDesc();
		String msg_type = "fc";
		String[] fields = { "date", "apparenttemperature",
				"summary", "msg_type" };
		String[] values = { dateTime, temperature, summary,
				msg_type };
		try {
			qb.insertInto("dailyupdate", fields).values(values)
					.Execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
}
