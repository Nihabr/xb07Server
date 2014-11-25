package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import model.QueryBuild.QueryBuilder;

public class EventList extends JPanel {

	//Panel med events oprettes.

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnLogout;
	private JButton btnMainMenu;
	private JLabel label;
	private JLabel lblEvents;
	private JLabel lblUpcomingEvent;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblHeader;
	private JLabel lblCBSlogo;
	private int row;
	private ResultSet rs;
	Object[] objects;
	
	public EventList() {
		setSize(new Dimension(1366, 768));
		setLayout(null);

		// Laver tabellen inde i Eventlisten.
		String[] columnNames = { "EventID", "Type", "Location", "CreatedBy",
				"Start", "End", "Name", "Text","Active", "CustomEvent", "CalendarID" };
		
		table = new JTable();
    	model = (DefaultTableModel)table.getModel();
    	model.setColumnIdentifiers(columnNames);
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);

		lblHeader = new JLabel("Events");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 78));
		lblHeader.setBounds(510, 90, 392, 90);
		add(lblHeader);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(94, 186, 884, 369);

		add(scrollPane);

		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnMainMenu.setBounds(602, 590, 194, 50);
		add(btnMainMenu);

		btnLogout = new JButton("Log out");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnLogout.setBounds(602, 656, 194, 50);
		add(btnLogout);

		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				255)));
		btnDelete.setBounds(990, 486, 118, 29);
		add(btnDelete);

		btnAdd = new JButton("Add");
		btnAdd.setOpaque(true);
		btnAdd.setForeground(new Color(0, 0, 205));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				255)));
		btnAdd.setBounds(990, 526, 118, 29);
		add(btnAdd);
		
		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(EventList.class.getResource("/Images/MetalBackground.jpg")));
		label.setBounds(0, 0, 1366, 768);
		add(label);

		lblEvents = new JLabel("Eventlist");
		lblEvents.setForeground(Color.WHITE);
		lblEvents.setFont(new Font("Arial", Font.BOLD, 78));
		lblEvents.setBounds(521, 90, 323, 90);
		add(lblEvents);

		lblUpcomingEvent = new JLabel("Upcomming Events:");
		lblUpcomingEvent.setFont(new Font("Arial", Font.BOLD, 27));
		lblUpcomingEvent.setForeground(Color.WHITE);
		lblUpcomingEvent.setBounds(51, 140, 309, 33);
		add(lblUpcomingEvent);
	
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public Object[] getObjects() {
		return objects;
	}
	public void addMouseListener(MouseListener m) {
		table.addMouseListener(m);
	}
	
	public void addActionListener(ActionListener l) {
		btnAdd.addActionListener(l);
		btnDelete.addActionListener(l);
		btnLogout.addActionListener(l);
		btnMainMenu.addActionListener(l);
		
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}
	

	public JTable getTable() {
		return table;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}
	// Der oprettes en metode der kan opdatere tabellen. 
	public void updateTable(){
		try {
			// her fjernes alle elementer i JTable
			model.getDataVector().removeAllElements();
			// Henter alle events der er sat til at være 'active' og tilføjer dem til JTable.
 			QueryBuilder qb = new QueryBuilder();
 			String key [] = {"active"};
 			String value = "1";
 			rs = qb.selectFrom("events").where("active", "=", value).ExecuteQuery();
 			ResultSetMetaData rsmd = rs.getMetaData();
 			int colNo = rsmd.getColumnCount();
 			
 	        while (rs.next()) {
 	        	
 	        	objects = new Object[colNo];
 	        	
 	        	for(int i=0;i<colNo;i++){
 	        		  objects[i]=rs.getObject(i+1);
 	        		  }
 	        		 model.addRow(objects);
 	        		}
 	        		table.setModel(model);
 	        		
    		} catch (SQLException e1) {
     			e1.printStackTrace();
     		}
	}

}
