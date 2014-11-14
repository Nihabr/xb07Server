package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

public class Screen extends JFrame {

	public static final String LOGIN = "name_276091497157488";
	public static final String MAINMENU = "name_276416022878030";
	public static final String USERINFO = "name_277892826656058";
	public static final String ADDUSERGUI = "name_278604525733268";
	public static final String NOTELIST = "name_278522430661848";
	public static final String USERLIST = "name_280161954000083";
	public static final String EVENTLIST = "name_534038022095149";
	public static final String ADDEVENTGUI = "name_6308445225625";
	public static final String ADDUSER = "name_10334207821613";
	public static final String ADDNOTE = "addnote";
	
	
	private JPanel contentPane;
	private final Login login = new Login();
	private final MainMenu mainMenu = new MainMenu();
	private final UserInfo userInfo = new UserInfo();
	private final NoteList noteList = new NoteList();
	private final UserList userlist = new UserList();
	private final EventList eventList = new EventList();
	private final AddNote addNote = new AddNote();
	private AddEventGUI addEventGUI;
	private final AddUser addUser = new AddUser();
	private final AddCourse addCourse = new AddCourse();
	CardLayout c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen frame = new Screen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Screen() {
		addCourse.getBtnMainMenu().setLocation(605, 620);
		addCourse.getBtnLogout().setLocation(605, 690);
		addCourse.getBtnLogout().setSize(194, 50);
		addCourse.getBtnMainMenu().setSize(194, 50);
		setTitle("Doek4life");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		contentPane.add(addUser, "name_10334207821613");
		
		addEventGUI = new AddEventGUI();
		contentPane.add(addEventGUI, "name_6308445225625");
		login.getBtnLogIn().setContentAreaFilled(false);
		login.getBtnForgotLogIn().setContentAreaFilled(false);
		
		contentPane.add(login, "name_276091497157488");
		
		contentPane.add(mainMenu, "name_276416022878030");
		
		contentPane.add(userInfo, "name_277892826656058");
		
		contentPane.add(noteList, "name_278522430661848");
		
		contentPane.add(eventList, "name_534038022095149");
		
		contentPane.add(userlist, "name_280161954000083");
		
		contentPane.add(addNote, "addnote");
		c = (CardLayout) getContentPane().getLayout();
	}
	
	public Login getLogin() {
		return login;
	}
	
	public MainMenu getMainMenu() {
		return mainMenu;
	}
	public AddNote getAddNote(){
		return addNote;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public NoteList getNoteList() {
		return noteList;
	}
	public UserList getUserList() {
		return userlist;
	}
	public void show(String card) {
		c.show(getContentPane(),  card);
	}
	public EventList getEventlist() {
		return eventList;
	}
	public AddEventGUI getAddEventGUI() {
		return addEventGUI;
	}
	public AddUser getAddUser() {
		return addUser;
	}
	public AddCourse getAddCourse() {
		return addCourse;
	}

}
