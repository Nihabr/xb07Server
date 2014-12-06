package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.QueryBuild.QueryBuilder;
import model.note.Note;
import model.note.NoteModel;
import model.user.AuthenticateUser;
import GUI.Screen;
import databaseMethods.SwitchMethods;

public class Controller {
	private Screen screen;
	private int loggedIn;
	private String action;
	private String currentUser = "";
	private String currentCalendar = "";
	private String eventID = "";
	
	private boolean full = false;
	QueryBuilder qb = new QueryBuilder();
	AuthenticateUser auth = new AuthenticateUser();
	int row;
	SwitchMethods sw = new SwitchMethods();

	private ResultSet res;

	public Controller() {

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
		screen.getUserInfo().addDocumentListener(new AddUserInfoDocumentListener());
		screen.getCalendar().addActionListener(new CalendarActionListener());
		screen.getAddCalendar().addActionListener(new AddCalendarActionListener());
		screen.getShareCalendar().addActionListener(new ShareCalendarActionListener());
		
		
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
				// brug if / else statement til at  om det er godkendt
				// eller ej, og hvis ikke
				// skal det printe hvilken fejl der er (bare print den int
				// v��rdi i modtager)
				if ((action.equals("btnLogIn"))) {
					
					loggedIn = auth.authenticate(userName, password);
			

					if (loggedIn == 0)

					{
						
						setCurrentUser(userName);
						screen.getCalendar().updateTable("1");
						screen.show(Screen.SHOWCALENDAR);
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
				
				String[] fields = {"active"};
				String[] values = {"0"};
				
				try{
				qb.update("users", fields, values).where("email", "=", getCurrentUser()).Execute();
				setCurrentcalendar(null);
				setCurrentUser(null);
				setEventID(null);
				screen.show(Screen.LOGIN);
				}
				catch(Exception e5){
					
				
					e5.printStackTrace();
				
				}
				
			}
			if (e.getSource() == screen.getMainMenu().getBtnUserlist()) {
				screen.getUserList().updateTable();
				screen.show(Screen.USERLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnNotelist()) {
				screen.getNoteList().updateTable(getEventID());
				screen.show(Screen.NOTELIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnEventlist()) {
				screen.getEventlist().updateTable(getCurrentCalendar());
				screen.show(Screen.EVENTLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnCalendars()) {
				screen.getCalendar().updateTable("1");
				screen.show(Screen.SHOWCALENDAR);
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
				String createdby = getCurrentUser();
				String start = "";
				start = screen.getAddEventGUI().startDateTimeToString();
				String end = "";
				end = screen.getAddEventGUI().endDateTimeToString();
				String name = screen.getAddEventGUI().getTextField_Name().getText();
				String text = screen.getAddEventGUI().getTextField_Text().getText();
			
				
				
				
				
				
				if (type.equals("")|| location.equals("")|| createdby.equals("")|| start.equals("")|| end.equals("")|| name.equals("")|| text.equals(""))
				{
					JOptionPane.showMessageDialog(null, "\nPlease fill out all the fields"
							, "Error message",JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					try{
					sw.createEvent(type, location, createdby, start, end, name, text, getCurrentCalendar());
					screen.getEventlist().updateTable(getCurrentCalendar());
					
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
				
				String calendarID = screen.getShareCalendar().getTextFieldChooseCal().getText();
				
				
				
				String shareEmail = screen.getShareCalendar().getTextFieldShareWith().getText();
				ArrayList<String>sharedUsers = new ArrayList<String>();
				
				if(calendarID != "" || shareEmail != ""){
					
					sharedUsers.add(shareEmail);
				}else{
					//feilmelding
				}
				
				
				try {
					sw.share(sharedUsers, calendarID, getCurrentUser());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				screen.show(Screen.SHOWCALENDAR);
			}
			if (e.getSource() == screen.getShareCalendar().getBtnBack()) {
				screen.show(Screen.SHOWCALENDAR);
			}
		}
		
		
		
		
	}

	private class AddUserActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getAddUser().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddUser().getBtnSubmit()) {

				String Email = screen.getAddUser().getTextField_Email()
						.getText();
				String type = screen.getAddUser().getTextField_Type().getText();
				String Password = screen.getAddUser().getTextField_Password()
						.getText();
				
				

				
				if (Email.equals("") || type.equals("") || Password.equals("")) {
					JOptionPane.showMessageDialog(null,
							"\nPlease fill out all the fields",
							"Error message", JOptionPane.PLAIN_MESSAGE);
					
					
					
				} else {

					String[] kolonner = {"email", "password" };
					String[] Values = { Email, Password };
					
					try {
//						String[] email = {"email"};
//						res = qb.selectFrom(email, "users").all().ExecuteQuery();
//						while(res.next())
//						if(res.getString("email").equals(Email)){
//							JOptionPane.showMessageDialog(null,
//									"\nUser already exists", "", JOptionPane.PLAIN_MESSAGE);
//						}else{
						qb.insertInto("users", kolonner).values(Values).Execute();
						
						String[] value = {"email"};
						
						res = qb.selectFrom(value, "users").where("email", "=", Email).ExecuteQuery();
						System.out.println(res.last());
						String[] kolonner2 = { "email", "type" };
						String[] values2 = {Email,type};
																		
						qb.insertInto("roles", kolonner2).values(values2).Execute();
						
						
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
				
				
				String eID = getEventID();
				String createdBy = getCurrentUser();
				String text = screen.getAddNote().getTextFieldText().getText();
				NoteModel noteModel = new NoteModel( text, createdBy, eID);
				Note note = new Note();
				note.CreateNote(noteModel);

				screen.show(Screen.NOTELIST);
				screen.getNoteList().updateTable(getEventID());
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
			if (e.getSource() == screen.getNoteList().getBtnDelete()) {
				
				String noteId = screen.getNoteList().getNoteID();
				String [] fields = {"active"};
				String [] values = {"0"};
				try{
					
					qb.update("notes", fields, values).where("noteID", "=", noteId).Execute();
					screen.getNoteList().updateTable(getEventID());
				}catch(Exception ee){
					ee.printStackTrace();
				}
			}
		}
	}

	private class UserListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getUserList().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
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
					screen.getCalendar().updateTable("1");
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

			if (e.getSource() == screen.getEventlist().getBtnAdd()){
				screen.show(Screen.ADDEVENTGUI);
			}
			if (e.getSource() == screen.getEventlist().getBtnSelectEvent()){
				
				setEventID(screen.getEventlist().getEventID());
				screen.getEventlist().getLblChosenEvent().setText("Go back to Main menu and then enter Notes to see specific notes for this event: " + screen.getEventlist().getName());
				
				
			}
			if (e.getSource() == screen.getEventlist().getBtnDelete()){
				
				if(!screen.getEventlist().getCreatedBy().equals("CBS")){
					
				String[] fields = {"active"};
				String [] values ={"0"};
				String name = screen.getEventlist().getName();
				try{
					qb.update("events", fields, values).where("name", "=", name).Execute();
					
					screen.getEventlist().updateTable(getCurrentCalendar());
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				}
				else{
					JOptionPane.showMessageDialog(null,
							"\nThis event cannot be deleted because of restrictions!", "", JOptionPane.PLAIN_MESSAGE);
					
					
				}
				
			}
		}
	}
	
	private class CalendarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == screen.getCalendar().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getCalendar().getBtnAdd()) {
				screen.getAddCalendar().updateTable();
				screen.show(Screen.ADDCALENDAR);
			}
			if (e.getSource() == screen.getCalendar().getChooseCalendar()) {
				
				setCurrentcalendar(screen.getCalendar().getId());
				screen.getCalendar().getLblCalendarInfo().setText("the current calendar is now set to: " + screen.getCalendar().getName());
				
			}
			if (e.getSource() == screen.getCalendar().getBtnDelete()) {
				
				String name = screen.getCalendar().getName();
				
				if(!screen.getCalendar().getIsCBS().equals("1")){
				String fields[] = {"active"};
				String values[] = {"0"};
				
				try{
					
					qb.update("calendar", fields, values).where("name", "=", name).Execute();
					screen.getCalendar().updateTable("1");
				}catch (Exception e4){
					e4.printStackTrace();
				}
				}else{
					JOptionPane.showMessageDialog(null,
							"\nThis calendar can not be deleted!", "", JOptionPane.PLAIN_MESSAGE);
					
					
					
				}
				
				}
			
			
			if (e.getSource() == screen.getCalendar().getBtnShare()) {
				screen.getShareCalendar().updateTable("1");
				screen.show(Screen.SHARECALENDAR);
			}
			
		}
	}
	
	private class AddCalendarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == screen.getAddCalendar().getBtnBack()) {
				screen.show(Screen.SHOWCALENDAR);
			}

			if (e.getSource() == screen.getAddCalendar().getBtnSubmit()) {
				
				String email = getCurrentUser();
				String name = screen.getAddCalendar().getTextName().getText();
				String privatePublic = screen.getAddCalendar().getTextPrivateOrPublic().getText();
				int isCbs = 0;
				String shareWith = screen.getAddCalendar().getTxtShare().getText();
				ArrayList<String>sharedUsers = new ArrayList<String>();
				try{
					
				int visibilitystatus = 0;
				
				if(privatePublic.equals("private")){
					
					visibilitystatus = 0;
				}else if(privatePublic.equals("public")){
					visibilitystatus = 1;
				}
					
					
					
				if(screen.getAddCalendar().getChckbxIfYesCheck().isSelected())
				{
				screen.getAddCalendar().showShareFields();
				
				sharedUsers.add(shareWith);
				
				}else{
					sharedUsers.isEmpty();
				}	
				
				
				
				sw.addNewCalendar(name, visibilitystatus, email, sharedUsers, isCbs);
				
				
				
				JOptionPane.showMessageDialog(null,
						"\nCalendar has been added!", "", JOptionPane.PLAIN_MESSAGE);
				screen.getCalendar().updateTable("1");
				screen.show(Screen.SHOWCALENDAR);
				
			} catch (Exception e2){
				e2.printStackTrace();
			}
				
		}
	}

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

	public String getCurrentCalendar() {
		return currentCalendar;
	}

	public void setCurrentcalendar(String currentCalendar) {
		this.currentCalendar = currentCalendar;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
		
}
	

