package model.QOTD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.Forecast.Forecast;
import model.QueryBuild.QueryBuilder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class QOTDModel implements Runnable {

	private ArrayList<QOTD> qotdlist = new ArrayList<>();

	QOTD qotdlist2 = new QOTD(null, null, null);
	QueryBuilder qb = new QueryBuilder();

	private ResultSet resultSet;

	/**
     *
     */

	public void run() {

		saveQuote();
	}

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

	public void saveQuote() {

		/**
		 * getting text from website and putiing into string Making a new object
		 * of JSON, and prints out quote
		 */
		String json;
		try {
			json = readUrl("http://dist-sso.it-kartellet.dk/quote/");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

			String quote = (String) jsonObject.get("quote");
			String author = (String) jsonObject.get("author");

			String fQuote = (author + ": \"" + quote + "\"");

			System.out.println(fQuote);

			String[] keys = { "qotd" };
			String[] keys2 = { fQuote };
			String apostrophe = "'";
			String[] a = { "''" };
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String datetime = sdf.format(date) + " 11:00:00";

			resultSet = qb.selectFrom(keys, "dailyupdate").all().ExecuteQuery();
			if(resultSet.next()){
			
				resultSet.getString("qotd").replaceAll("'", "''");
				

			} else {

				qb.update("dailyupdate", keys, keys2)
						.where("date", "=", datetime).Execute();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Retrieve Quote from a website and put it into a String, Afterwards we
	 * will make it into a json object so it can be printed out to the client.
	 */
	public String getQuote() {
		String q = "";
		String[] key = { "qotd" };
		try {
			resultSet = qb.selectFrom("dailyupdate").all().ExecuteQuery();
			while (resultSet.next()) {
				q = resultSet.getString("qotd");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
	}

	// public void updateQuote(){
	// // Date date = new Date(); // Current date & time
	// // Timer timer = new Timer();
	// // long maxTimeNoUpdate = 86400; // Maximum one day with no update
	// //
	// // long date1 = date.getTime();
	// // long date2 = date.getTime() - maxTimeNoUpdate; // minus 1 hour --
	// should be fetched from database
	// //
	// // long timeSinceUpdate = date1 - date2;
	//
	// final ScheduledExecutorService exec =
	// Executors.newScheduledThreadPool(1);
	//
	// exec.schedule(new Runnable(){
	// @Override
	// public void run(){
	// saveQuote();
	// }
	// }, 1, TimeUnit.SECONDS);
	//
	// // if more than 1 hour ago, do update
	// // if(timeSinceUpdate > 864000){
	// // return fresh weather data
	//
	// // saveQuote();
	// }
}
