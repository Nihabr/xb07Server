package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.user.*;
import GUI.UserInformation;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import databaseMethods.SwitchMethods;
import model.note.*;
import model.QueryBuild.*;
import GUI.Screen;
import JsonClasses.CreateEvent;

public class GUILogic {
	private Screen screen;
	private int loggedIn;
	private String action;
	private String currentUser = "";
	
	private boolean full = false;
	QueryBuilder qb = new QueryBuilder();
	AuthenticateUser auth = new AuthenticateUser();
	int row;
	SwitchMethods sw = new SwitchMethods();

	private ResultSet res;

	public GUILogic() {

		screen = new Screen();
		screen.getLogin().addActionListener(new LoginActionListener());
		screen.getMainMenu().addActionListener(new MainMenuActionListener());
		screen.getUserInfo().addActionListener(new UserInfoActionListener());
		screen.getNoteList().addActionListener(new NoteListActionListener());
		screen.getUserList().addActionListener(new UserListActionListener());
		screen.getEventlist().addActionListener(new EventListActionListener());
		screen.getAddEventGUI().addActionListener(new AddEventGUIActionListener());
		screen.getAddUser().addActionListener(new AddUserActionListener());
		screen.getAddNote().addActionListener(new AddNoteActionListener());
		//screen.getAddCourse().addActionListener(new AddCourseActionListener());
		screen.getUserInfo().addDocumentListener(new AddUserInfoDocumentListener());
		screen.getCalendar().addActionListener(new CalendarActionListener());
		screen.getAddCalendar().addActionListener(new AddCalendarActionListener());

		
		
	}

	public void run() {

		screen.show(Screen.ADDUSER);
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
					
					loggedIn = auth.authenticate(userName, password, true);
			

					if (loggedIn == 0)

					{
						setCurrentUser(userName);
						screen.getCalendar().updateTable();
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
				screen.getUserList().updateTable();
				screen.show(Screen.USERLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnNotelist()) {
				screen.show(Screen.NOTELIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnEventlist()) {
				screen.getEventlist().updateTable();
				screen.show(Screen.EVENTLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnCalendars()) {
				screen.show(Screen.CALENDAR);
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
					try{
					sw.createEvent(type, location, createdby, start, end, name, text, customEvent, calendarID);
					screen.getEventlist().updateTable();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"\nEvent has been added!", "", JOptionPane.PLAIN_MESSAGE);
					screen.show(Screen.EVENTLIST);
				}
				}
				
			}
		}
	}
	
	private class ShareCalendarActionListener implements ActionListener{

	
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == screen.getShareCalendar().getBtnShare()) {
				
				
			}
			if (e.getSource() == screen.getShareCalendar().getBtnBack()) {
				screen.show(Screen.CALENDAR);
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

					String[] kolonner = {"email", "active", "password" };
					String[] Values = { Email, userActive, Password };
					
					try {
						String[] email = {"email"};
						res = qb.selectFrom(email, "users").all().ExecuteQuery();
						while(res.next())
						if(res.getString("email").equals(Email)){
							JOptionPane.showMessageDialog(null,
									"\nUser already exists", "", JOptionPane.PLAIN_MESSAGE);
						}else{
						qb.insertInto("users", kolonner).values(Values).Execute();
						
						String[] value = {"userID"};
						
						res = qb.selectFrom(value, "users").where("email", "=", Email).ExecuteQuery();
						System.out.println(res.last());
						String[] kolonner2 = { "userid", "type" };
						String[] values2 = {String.valueOf(res.getInt("userID")),type};
						System.out.println(res.getInt("userID"));
												
						qb.insertInto("roles", kolonner2).values(values2).Execute();
						
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
			JOptionPane.showMessageDialog(null,
						"\nUser has been added!", "", JOptionPane.PLAIN_MESSAGE);
			screen.getUserList().updateTable();
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
			JOptionPane.showMessageDialog(null,
					"\nNote has been added!", "", JOptionPane.PLAIN_MESSAGE);
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
			screen.getUserList().updateTable();
			screen.show(Screen.USERLIST);
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
				
				String email = screen.getUserList().getLblChosenUser().getText();
				
				String fields[] = {"active"};
				String values[] = {"0"};
				try{
					
					qb.update("users", fields, values).where("email", "=", email).Execute();
					screen.getCalendar().updateTable();
				}catch (Exception e4){
					e4.printStackTrace();
				}
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
				int eventID = (int) screen.getEventlist().getModel().getValueAt(row, 1);
//				try {
//					sw.removeEvent("1", eventID);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				//mangler
			}
		}
	}
	
	private class CalendarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == screen.getCalendar().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getCalendar().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getCalendar().getBtnAdd()) {
				screen.getAddCalendar().updateTable();
				screen.show(Screen.ADDCALENDAR);
			}
			if (e.getSource() == screen.getCalendar().getChooseCalendar()) {
				
			}
			if (e.getSource() == screen.getCalendar().getBtnDelete()) {
				
				String name = screen.getCalendar().getLblChosenCalendar().getText();
				
				String fields[] = {"active"};
				String values[] = {"0"};
				try{
					
					qb.update("calender", fields, values).where("name", "=", name).Execute();
					screen.getCalendar().updateTable();
				}catch (Exception e4){
					e4.printStackTrace();
				}
				}
			
			if (e.getSource() == screen.getCalendar().getBtnShare()) {
				screen.show(Screen.SHARECALENDAR);
			}
		}
	}
	
	private class AddCalendarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == screen.getAddCalendar().getBtnBack()) {
				screen.show(Screen.CALENDAR);
			}
			if (e.getSource() == screen.getAddCalendar().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddCalendar().getBtnSubmit()) {
				
				String email = getCurrentUser();
				String name = screen.getAddCalendar().getTextName().getText();
				String privatePublic = screen.getAddCalendar().getTextPrivateOrPublic().getText();
				int privatepublic = Integer.valueOf(privatePublic);
				String shareWith = screen.getAddCalendar().getTxtShare().getText();
				
				ArrayList<String>sharedUsers = new ArrayList<String>();
				sharedUsers.add(shareWith);
				
				
				
				try{
				
				sw.addNewCalender(name, privatepublic, email, sharedUsers);
				
				
				
				JOptionPane.showMessageDialog(null,
						"\nCalendar has been added!", "", JOptionPane.PLAIN_MESSAGE);
				screen.getCalendar().updateTable();
				screen.show(Screen.CALENDAR);
			} catch (Exception e2){
				e2.printStackTrace();
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
	}
	private class EventListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			row = screen.getEventlist().getTable().getSelectedRow();
			row++;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
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

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
		
}
	

