
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import model.Forecast.ForecastModel;
import model.QOTD.QOTDModel;
import GUI.GUILogic;
public class AdminWorker implements Runnable{
	public void run(){
		GUILogic logic = new GUILogic();
		addThreads();
		logic.run();
	
    
    }
      public void addThreads(){
    	  ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(5);
    	  s.scheduleAtFixedRate(new QOTDModel(), 0, 1, TimeUnit.DAYS);
   	  s.scheduleAtFixedRate(new ForecastModel(), 0, 1, TimeUnit.HOURS);
    	  System.out.println("thread" + Thread.activeCount());
      }
}
