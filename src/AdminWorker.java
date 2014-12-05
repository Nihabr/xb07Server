
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import model.Forecast.ForecastModel;
import model.QOTD.QOTDModel;
import controller.Controller;

public class AdminWorker implements Runnable{
	public void run(){
		Controller controller = new Controller();
		addThreads();
		controller.run();
	
    
    }
      public void addThreads(){
    	  try { 
    	  ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(5);
    	  s.scheduleAtFixedRate(new QOTDModel(), 1, 1, TimeUnit.DAYS);
   	  s.scheduleAtFixedRate(new ForecastModel(), 1, 1, TimeUnit.HOURS);
    	  System.out.println("thread" + Thread.activeCount());
    	  } catch(Exception e){
    		  e.printStackTrace();
    	  }
      }
}
