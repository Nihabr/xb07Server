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
	private boolean userloggedin;
	private boolean full = false;
	
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

		
		
	}
	public void run() {

		screen.show(Screen.LOGIN);
		screen.setVisible(true);
	}
	
	private class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try{
				
			String userName = screen.getLogin().getTextFieldUsername().getText().trim();
			String password = screen.getLogin().getTextFieldPassword().getText();
			
			// Giv auth noget data som passer til metoden
			// DernÃ¦st skal auth returnere 0 hvis dataen er god, og ellers give en fejl
			// brug if / else statement til at printe om det er godkendt eller ej, og hvis ikke
			// skal det printe hvilken fejl der er (bare print den int vÃ¦rdi i modtager)
			if (e.getSource() == screen.getLogin().getBtnLogIn()){
				
				loggedIn=auth.authenticate(userName, password, true);
				
				if(loggedIn != 0){
					JOptionPane.showMessageDialog(null, "\nLogin failed, error: " + loggedIn
							, "Error message",JOptionPane.PLAIN_MESSAGE);
			}

			if	(loggedIn == 0)
					{
						screen.show(Screen.MAINMENU);
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
				QueryBuilder qb = new QueryBuilder();
				
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
				
				
				//her kan vi også bruke goodpass metoden fra den tidligere oppgave
				if (Email.equals("")|| Type.equals("")|| Password.equals(""))
				{
					JOptionPane.showMessageDialog(null, "\nPlease fill out all the fields"
							, "Error message",JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
				QueryBuilder qb = new QueryBuilder();
				
				String[] kolonner = { "email", "password", "active"};
				String[] Values = { Email, Password, userActive};
				
				//Hva brukes disse til?
				
				String[] kolonner2 = { "types"};
				String[] Values2 = { Type};
				try {
					qb.insertInto("users", kolonner ).values(Values).ExecuteQuery();
					qb.insertInto("roles", kolonner ).values(Values).ExecuteQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				
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
