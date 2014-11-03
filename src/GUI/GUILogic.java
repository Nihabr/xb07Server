package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import model.user.*;
import GUI.UserInformation;

import javax.swing.JOptionPane;

import model.QueryBuild.*;
import GUI.Screen;

public class GUILogic {
	private Screen screen;
	private int loggedIn;
	private String action;
	private boolean full = false;
	QueryBuilder qb = new QueryBuilder();
	AuthenticateUser auth = new AuthenticateUser();
	

	public GUILogic(){
		
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

		
		
	}
	public void run() {

		screen.show(Screen.LOGIN);
		screen.setVisible(true);
	}
	
	private class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try{
				
				action = e.getActionCommand();
			
			String userName = screen.getLogin().getTextFieldUsername().getText().trim();
			char[] pass = screen.getLogin().getTextFieldPassword().getPassword();
			String password = String.valueOf(pass);
			// Giv auth noget data som passer til metoden
			// DernÃ¦st skal auth returnere 0 hvis dataen er god, og ellers give en fejl
			// brug if / else statement til at printe om det er godkendt eller ej, og hvis ikke
			// skal det printe hvilken fejl der er (bare print den int vÃ¦rdi i modtager)
			if ( (action.equals("btnLogIn"))){
				
				loggedIn=auth.authenticate(userName, password,true);
				System.out.println("hit");
				
				if	(loggedIn == 0)
					
				{
					
					screen.show(Screen.MAINMENU);
					
				}
				
				else if(loggedIn != 0){
					JOptionPane.showMessageDialog(null, "\nLogin failed, error: " + loggedIn
							, "Error message",JOptionPane.PLAIN_MESSAGE);
			}

			
				
	
			}	
			}	
			catch(Exception e3){
			}
		}	
	}	
	
	private class MainMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getMainMenu().getBtnLogOut()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getMainMenu().getBtnUserlist()){
				screen.show(Screen.USERLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnNotelist()){
				screen.show(Screen.NOTELIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnEventlist()){
				screen.show(Screen.EVENTLIST);
			}
			

		}
	}
	
	private class AddEventGUIActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddEventGUI().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddEventGUI().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddEventGUI().getBtnSubmit()){
				String Type = screen.getAddEventGUI().getTextField_Type().getText();
				String Location = screen.getAddEventGUI().getTextField_Location().getText();
				String Createdby = screen.getAddEventGUI().getTextField_Createdby().getText();
				String start = screen.getAddEventGUI().getTextField_Start().getText();
				String end = screen.getAddEventGUI().getTextField_End().getText();
				String name = screen.getAddEventGUI().getTextField_Name().getText();
				String text = screen.getAddEventGUI().getTextField_Text().getText();
				
				
				//Her må det også tilføyes nye felter til add event panelet.
				
				if (Type.equals("")|| Location.equals("")|| Createdby.equals("")|| start.equals("")|| end.equals("")|| name.equals("")|| text.equals(""))
				{
					JOptionPane.showMessageDialog(null, "\nPlease fill out all the fields"
							, "Error message",JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
				
				
				String[] eventColumns = { "EventID", "Type", "Location", "CreatedBy","Start",
						"End","Name", "Text","CustomEvent","CalendarID"};
				String[] Values = { "Type", "Location", "CreatedBy","Start",
						"End","Name", "Text","CustomEvent","CalendarID"};
				
				//Har lagt til variabler i arrayet slik det ser ut i databasen. 
				try {
					qb.insertInto("events", eventColumns ).values(Values).ExecuteQuery();
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
			if (e.getSource() == screen.getAddUser().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddUser().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddUser().getBtnSubmit()){
				
				String Email = screen.getAddUser().getTextField_Email().getText();
				String Type = screen.getAddUser().getTextField_Type().getText();
				String Password = screen.getAddUser().getTextField_Password().getText();
				int active = 1;
				String userActive = String.valueOf(active);
				boolean isAdmin = false;
				String admin = "false";
				if(Type.equals("admin")){
					isAdmin = true;
					admin = "true";
				}
				
				
				//her kan vi også bruke goodpass metoden fra den tidligere oppgave
				if (Email.equals("")|| Type.equals("")|| Password.equals(""))
				{
					JOptionPane.showMessageDialog(null, "\nPlease fill out all the fields"
							, "Error message",JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
				
				
				String[] kolonner = { "userid","email", "active", "created","password","isadmin"};
				String[] Values = { Email,userActive, Password,admin};
				
				//Hva brukes disse til?
				
				String[] kolonner2 = { "userid","type"};
				
				String[] Values2 = { Type};
				
//				StringBuilder sql = new StringBuilder();
//				sql.append("SELECT @last := LAST_INSERT_ID();\n");
//				sql.append("insert into cbscalendar.roles values\n");
//				sql.append("(NULL, @last, 'admin' );\n");
				try {
					qb.insertInto("users", kolonner ).values(Values).ExecuteQuery();
//					System.out.println(qb.insertInto("users", kolonner ).values(Values).ExecuteQuery());
					qb.insertInto("roles", kolonner2 ).values(Values2).ExecuteQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				
				
			}
		}
	}
	
	private class AddNoteActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == screen.getAddNote().getBtnAddNote()){
				
				String createdBy = screen.getAddNote().getTextFieldCreatedBy().getText();
				String text = screen.getAddNote().getTextFieldText().getText();
				int active = 1;
				String isActive = String.valueOf(active);
				
//				String[] noteHeader = {"createdBy","text"};
//				String[] noteValues = {createdBy,text};
				String[] fields = {"noteId", "eventId", "createdBy", "text", "dateTime", "active"};
				String[] values = {null, null, createdBy, text, null, isActive};
				
				try{
					qb.insertInto("notes", fields).values(values).Execute();
					
				}catch(Exception e4){
					
					e4.printStackTrace();
				}
				
				screen.show(Screen.NOTELIST);
				
			}
		}
		
	}
	
	private class UserInfoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getUserInfo().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getUserInfo().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getUserInfo().getBtnSubmit()){
				
			}
		}
	}
	
	private class NoteListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()== screen.getNoteList().getBtnAdd()){
				
				screen.show(Screen.ADDNOTE);
				
				
				
				
				
				
			}

			if (e.getSource() == screen.getNoteList().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getNoteList().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
		}
	}
	
	private class UserListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == screen.getUserList().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getUserList().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getUserList().getBtnAdd()){
				
				screen.show(Screen.ADDUSER);
			
			}
			if (e.getSource() == screen.getUserList().getBtnDelete()){
				
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
		}
	}
	
	
}
