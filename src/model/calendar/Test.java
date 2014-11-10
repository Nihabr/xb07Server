package model.calendar;

public class Test {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
//		Events etest = new Events();
//		etest.getEvents();
//		System.out.println(etest.toString());
		GetCalendarData gd = new GetCalendarData();
		try {
			gd.getDataFromCalendar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
