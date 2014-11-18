
import GUI.GUILogic;
public class AdminWorker implements Runnable{
	public void run(){
		GUILogic logic = new GUILogic();
		logic.run();
	}
}
