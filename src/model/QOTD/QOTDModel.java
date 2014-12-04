package model.QOTD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.QueryBuild.QueryBuilder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class QOTDModel implements Runnable {

	
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
			
			quote = quote.replace("'", "''");
			String fQuote = (author + ": \"" + quote + "\"");
			

			String[] keys = { "qotd" };
			String[] keys2 = { fQuote };
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String datetime = sdf.format(date) + " 12:00:00";

		

				qb.update("dailyupdate", keys, keys2)
						.where("date", "=", datetime).Execute();
				
		}catch(Exception e){
			e.printStackTrace();
		}


	}

	/**
	 * Retrieve Quote from a website and put it into a String, Afterwards we
	 * will make it into a json object so it can be printed out to the client.
	 */
	public String getQuote() {
		String quote = "";
		try {
			resultSet = qb.selectFrom("dailyupdate").all().ExecuteQuery();
			while (resultSet.next()) {
				quote = resultSet.getString("qotd");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quote;
	}
}
