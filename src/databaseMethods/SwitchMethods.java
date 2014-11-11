package databaseMethods;
import java.sql.SQLException;

import model.Model;
import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;

public class SwitchMethods extends Model
{
	QueryBuilder qb = new QueryBuilder();
	QOTDModel qm = new QOTDModel();
	

	
	/**
	 * Allows the client to create a new calendar
	 * @param userName
	 * @param calenderName
	 * @param privatePublic
	 * @return
	 * @throws SQLException
	 */

	public String createNewCalender (String userName, String calenderName, int privatePublic) throws SQLException
	{
		String stringToBeReturned ="";
		testConnection();
		if(authenticateNewCalender(calenderName) == false)
		{
			addNewCalender(calenderName, userName, privatePublic);
			stringToBeReturned = "The new calender has been created!";
		}
		else
		{
			stringToBeReturned = "The new calender has not been created!";
		}
		
		
		return stringToBeReturned;
	}
	
	public boolean authenticateNewCalender(String newCalenderName) throws SQLException
	{
		getConn();
		boolean authenticate = false;
		
		resultSet= qb.selectFrom("calender").where("name", "=", newCalenderName).ExecuteQuery();
				
				//("select * from test.calender where Name = '"+newCalenderName+"';");
		while(resultSet.next())
		{
			authenticate = true;
		}
		return authenticate;
	}
	
	public void addNewCalender (String newCalenderName, String userName, int publicOrPrivate) throws SQLException
	{
		String [] fields = {"Name","active","CreatedBy","PrivatePublic"};
		String [] values = {newCalenderName,"1",userName, Integer.toString(publicOrPrivate)};
		qb.insertInto("calender", fields).values(values).Execute();
		
	}
	public String createEvent( String eventid, String type,
			String location, String createdby, String start,
			String end, String name, String text,
			String customevent, String calendarID)throws SQLException{
		testConnection();
		String stringToBeReturned ="";
		String [] fields = {"eventid", "type", "location", "createdby", "start",
				"end", "name","text","customevent", "calendarID"};
		String[] values = {eventid,  type ,  location ,  createdby ,  start ,
				 end ,  name , text , customevent ,  calendarID };
		
		qb.insertInto("events", fields).values(values).Execute();
		stringToBeReturned = "Event has been created";
		return stringToBeReturned;
	}
	/**
	 * Allows the client to delete a calender
	 * @param userName
	 * @param calenderName
	 * @return
	 */
	
	
	public String deleteCalender (String userName, String calenderName) throws SQLException
	{
		String stringToBeReturned ="";
		testConnection();
		stringToBeReturned = removeCalender(userName, calenderName);

		return stringToBeReturned;
	}
	
	public String removeCalender (String userName, String calenderName) throws SQLException
	{
		String stringToBeReturend = "";
		String usernameOfCreator ="";
		String calenderExists = "";
		resultSet = qb.selectFrom("Calender").where("Name", "=", calenderName).ExecuteQuery();
				
//				("select * from calender where Name = '"+calenderName+"';");
		while(resultSet.next())
		{
			calenderExists = resultSet.toString();
		}
		if(!calenderExists.equals(""))
		{
			String [] value = {"CreatedBy"};
			resultSet = qb.selectFrom(value, "Calender").where("Name", "=", calenderName).ExecuteQuery();
			while(resultSet.next())
			{
				usernameOfCreator = resultSet.toString();
				System.out.println(usernameOfCreator);
			}
			if(!usernameOfCreator.equals(userName))
			{
				stringToBeReturend = "Only the creator of the calender is able to delete it.";
			}
			else
			{
				String [] keys = {"Active"};
				String [] values = {"2"};
				qb.update("calender", keys, values).where("Name", "=", calenderName).Execute();
				stringToBeReturend = "Calender has been set inactive";
			}
			stringToBeReturend = resultSet.toString();
		}
		else
		{
			stringToBeReturend = "The calender you are trying to delete, does not exists.";
		}
		
		
		
		return stringToBeReturend;
	}
}
