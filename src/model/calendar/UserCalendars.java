package model.calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.QueryBuild.QueryBuilder;
public class UserCalendars {

	ArrayList <String> subscribedCalendars = new ArrayList<String>();
	QueryBuilder qb = new QueryBuilder();
	
	public UserCalendars (String email){
		try {
			getUserCalendars (email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getUserCalendars (String email) throws SQLException{
		
		ResultSet rs = qb.selectFrom("Calendar_users").where("email", "=", email).ExecuteQuery();
		
		while (rs.next()){
			subscribedCalendars.add(rs.getString("calendarid"));
		}
		
		return subscribedCalendars;
	}
}
