package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.user.*;
import GUI.UserInformation;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.note.*;
import model.QueryBuild.*;
import GUI.Screen;

public class GUILogic {
	private Screen screen;
	private int loggedIn;
	private String action;
	private boolean full = false;
	QueryBuilder qb = new QueryBuilder();
	AuthenticateUser auth = new AuthenticateUser();

	private ResultSet res;

	public GUILogic() {

		screen = new Screen();

		screen.getLogin().addActionListener(new LoginActionListener());
		screen.getMainMenu().addActionListener(new MainMenuActionListener());
		screen.getUserInfo().addActionListener(new UserInfoActionListener());
		screen.getNoteList().addActionListener(new NoteListActionListener());
		screen.getUserList().addActionListener(new UserListActionListener());
		screen.getEventlist().addActionListener(new EventListActionListener());
		screen.getAddEventGUI().addActionListener(
				new AddEventGUIActionListener());
		screen.getAddUser().addActionListener(new AddUserActionListener());
		screen.getAddNote().addActionListener(new AddNoteActionListener());
		//screen.getAddCourse().addActionListener(new AddCourseActionListener());
		screen.getUserInfo().addDocumentListener(new AddUserInfoDocumentListener());


	}

	public void run() {

		screen.show(Screen.LOGIN);
		screen.setVisible(true);
	}

	private class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				
				action = e.getActionCommand();
				String userName = screen.getLogin().getTextFieldUsername()
						.getText().trim();
				char[] pass = screen.getLogin().getTextFieldPassword()
						.getPassword();
				String password = String.valueOf(pass);
				// Giv auth noget data som passer til metoden
				// Dern��st skal auth returnere 0 hvis dataen er god, og ellers
				// give en fejl
				// brug if / else statement til at printe om det er godkendt
				// eller ej, og hvis ikke
				// skal det printe hvilken fejl der er (bare print den int
				// v��rdi i modtager)
				if ((action.equals("btnLogIn"))) {
					System.out.println("hit1");
					loggedIn = auth.authenticate(userName, password, true);
					System.out.println("hit2");

					if (loggedIn == 0)

					{

						screen.show(Screen.MAINMENU);
						screen.getLogin().Refresh();
					}

					else if (loggedIn != 0) {
						JOptionPane.showMessageDialog(null,
								"\nLogin failed, error: " + loggedIn,
								"Error message", JOptionPane.PLAIN_MESSAGE);
						
					}

				}
			} catch (Exception e3) {
			}
		}
	}

	private class MainMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getMainMenu().getBtnLogOut()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getMainMenu().getBtnUserlist()) {
				screen.show(Screen.USERLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnNotelist()) {
				screen.show(Screen.NOTELIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnEventlist()) {
				screen.show(Screen.EVENTLIST);
			}

		}
	}

	private class AddEventGUIActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			if (e.getSource() == screen.getAddEventGUI().getBtnLogout()){
//				screen.show(Screen.LOGIN);
//			}
			
			if (e.getSource() == screen.getAddEventGUI().getBtnBack()){
				screen.show(Screen.EVENTLIST);
			}
			if (e.getSource() == screen.getAddEventGUI().getBtnSubmit()){
				String type = screen.getAddEventGUI().getTextField_Type().getText();
				String location = screen.getAddEventGUI().getTextField_Location().getText();
				String createdby = screen.getAddEventGUI().getTextField_Createdby().getText();
				String start = "";
				start = screen.getAddEventGUI().startDateTimeToString();
				String end = "";
				end = screen.getAddEventGUI().endDateTimeToString();
				String name = screen.getAddEventGUI().getTextField_Name().getText();
				String text = screen.getAddEventGUI().getTextField_Text().getText();
				String customEvent = "1";
				String calendarID = "1";
				
				
				
				//Her m� det ogs� tilf�yes nye felter til add event panelet.
				
				if (type.equals("")|| location.equals("")|| createdby.equals("")|| start.equals("")|| end.equals("")|| name.equals("")|| text.equals(""))
				{
					JOptionPane.showMessageDialog(null, "\nPlease fill out all the fields"
							, "Error message",JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
				
				
				String[] eventColumns = {"Type", "Location", "CreatedBy","Start",
						"End","Name", "Text","CustomEvent","CalendarID"};
				String[] Values = {type, location, createdby,start,
						end,name, text,customEvent,calendarID};
				// for � f� dette til � fungere m� vi f�rst opprette en kalender...
				
				//Har lagt til variabler i arrayet slik det ser ut i databasen. 
				try {
					qb.insertInto("events", eventColumns ).values(Values).Execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				
			}
		}
	}

	private class AddUserActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddUser().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddUser().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddUser().getBtnSubmit()) {

				String Email = screen.getAddUser().getTextField_Email()
						.getText();
				String type = screen.getAddUser().getTextField_Type().getText();
				String Password = screen.getAddUser().getTextField_Password()
						.getText();
				int active = 1;
				String userActive = String.valueOf(active);
				int adminstatus;
				String admin = "0";
				if (type.equals("admin")) {
					// isAdmin = true;
					
					adminstatus = 1;
					admin = String.valueOf(adminstatus);
				}
				else if(type.equals("user")){
						
						adminstatus = 0;
						admin = String.valueOf(adminstatus);
					}
				

				// her kan vi ogs� bruke goodpass metoden fra den tidligere
				// oppgave
				if (Email.equals("") || type.equals("") || Password.equals("")) {
					JOptionPane.showMessageDialog(null,
							"\nPlease fill out all the fields",
							"Error message", JOptionPane.PLAIN_MESSAGE);
				} else {

					String[] kolonner = {"email", "active", "password", "isadmin" };
					String[] Values = { Email, userActive, Password, admin };
					
					try {
						qb.insertInto("users", kolonner).values(Values).Execute();
						System.out.println("s� langt s� godt");
						String[] value = {"userID"};
						
						res = qb.selectFrom(value, "users").where("email", "=", Email).ExecuteQuery();
						System.out.println(res.last());
						String[] kolonner2 = { "userid", "type" };
						String[] values2 = {String.valueOf(res.getInt("userID")),type};
						System.out.println(res.getInt("userID"));
												
						qb.insertInto("roles", kolonner2).values(values2).Execute();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
			screen.show(Screen.USERLIST);
			}
		}
	}

	private class AddNoteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getAddNote().getBtnAddNote()) {
				
				int nID = 0;
				int eID = 0;
				String createdBy = screen.getAddNote().getTextFieldCreatedBy()
						.getText();
				String date = "1000-01-01 00:00:00";
				String text = screen.getAddNote().getTextFieldText().getText();
				int isActive = 1;
				NoteModel noteModel = new NoteModel(nID, text, date, createdBy, isActive, eID);
				Note note = new Note();
				note.CreateNote(noteModel);

				screen.show(Screen.NOTELIST);

			}
			
			if (e.getSource() == screen.getAddNote().getBtnBack()) {
				screen.show(Screen.NOTELIST);
			}
		}

	}

	private class UserInfoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == screen.getUserInfo().getBtnBack()) {
				screen.show(Screen.USERLIST);
			}
			if (e.getSource() == screen.getUserInfo().getBtnSubmit()) {
			String userID = screen.getUserInfo().getTxtField_UserID().getText();
			String email = screen.getUserInfo().getTxtField_Email().getText();
			String password = screen.getUserInfo().getTxtField_Password().getText();
			String[] fields = {"email", "Password"};
			String[] values = {email, password};
			try {
				qb.update("users", fields, values).where("userID", "=", userID).Execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,
					"\nThe changes has been successfully made!", "", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private class NoteListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getNoteList().getBtnAdd()) {
				screen.show(Screen.ADDNOTE);
			}

			if (e.getSource() == screen.getNoteList().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getNoteList().getBtnLogout()) {
				screen.show(Screen.LOGIN);
				screen.getLogin().Refresh();
			}
			if (e.getSource() == screen.getNoteList().getBtnDelete()) {
				//mangler
			}
		}
	}

	private class UserListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getUserList().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getUserList().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getUserList().getBtnAdd()) {
				screen.show(Screen.ADDUSER);
			}
			if (e.getSource() == screen.getUserList().getBtnEditUser()){
				screen.show(Screen.USERINFO);
				screen.getUserInfo().Refresh();
			}
			if (e.getSource() == screen.getUserList().getBtnDelete()) {
			//mangler
			}

		}
	}

	private class EventListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getEventlist().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getEventlist().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getEventlist().getBtnAdd()){
				screen.show(Screen.ADDEVENTGUI);
			}
			if (e.getSource() == screen.getEventlist().getBtnDelete()){
				// mangler
			}
		}
	}
	
//	private class AddCourseActionListener implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			
//			if (e.getSource() == screen.getAddCourse().getBtnMainMenu()){
//				screen.show(Screen.MAINMENU);
//			}
//			if (e.getSource() == screen.getAddCourse().getBtnLogout()){
//				screen.show(Screen.LOGIN);
//			}
//			if (e.getSource() == screen.getAddCourse().getBtnAdd()) {
//				// mangler
//			}
//			if (e.getSource() == screen.getAddCourse().getBtnDelete()) {
//				// mangler
//			}
//		}
//	}	

	private class AddUserInfoDocumentListener implements DocumentListener{

			  public void changedUpdate(DocumentEvent e) {
			    try {
					warn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			  public void removeUpdate(DocumentEvent e) {
			    try {
					warn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			  public void insertUpdate(DocumentEvent e) {
			    try {
					warn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			  public void warn() throws SQLException{
				  String value = screen.getUserInfo().getTxtField_UserID().getText();
				  
				  res = qb.selectFrom("users").where("userid", "=" , value).ExecuteQuery();
				  while (res.next()){
					screen.getUserInfo().getTxtField_CreatedDate().setText(res.getString("created").toString());
					screen.getUserInfo().getTxtField_Email().setText(res.getString("email"));
					System.out.println(res.getInt("active"));
					screen.getUserInfo().setOnlineDot(res.getInt("active"));
				  }
			  }
		}
	
}
