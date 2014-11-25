package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import model.QueryBuild.QueryBuilder;

public class EventList extends JPanel {

	/**
	 * Create the panel.
	 */

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnLogout;
	private JButton btnMainMenu;
	private JButton btnSelectEvent;
	private JLabel label;
	private JLabel lblChosenEvent;
	private JLabel lblEvents;
	private JLabel lblUpcomingEvent;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblHeader;
	private int row;
	private ResultSet rs;
	private Object[] objects;
	private String name;
	private String eventID;
	
	public EventList() {
		setSize(new Dimension(1366, 768));
		setLayout(null);

		// Laver tabellen inde i Eventlisten.
		String[] columnNames = { "EventID", "Type", "Location", "CreatedBy",
				"Start", "End", "Name", "Text","CalendarID", "Active"};


		table = new JTable();
    	model = (DefaultTableModel)table.getModel();
    	model.setColumnIdentifiers(columnNames);
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				row = table.getSelectedRow();
				if(row != -1){
				name = table.getValueAt(row, 6).toString();
				setName(name);
				eventID = table.getValueAt(row, 0).toString();
				setEventID(eventID);
				
				
//				lblCalendarName.setText(table.getValueAt(row, 1).toString());
				
				}
			}
		});
		
		lblChosenEvent = new JLabel("");
		lblChosenEvent.setBounds(233, 144, 611, 21);
		add(lblChosenEvent);
		
		btnSelectEvent = new JButton("Select event");
		btnSelectEvent.setOpaque(true);
		btnSelectEvent.setForeground(new Color(0, 0, 205));
		btnSelectEvent.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
						255)));
		btnSelectEvent.setBounds(1138, 434, 118, 29);
		add(btnSelectEvent);

		lblHeader = new JLabel("Events");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 78));
		lblHeader.setBounds(512, 41, 392, 90);
		add(lblHeader);
		
		// Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(218, 184, 884, 369);

		// Add the scroll pane to this panel.
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
		btnMainMenu.setBounds(559, 587, 194, 50);
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
		btnLogout.setBounds(559, 650, 194, 50);
		add(btnLogout);

		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				255)));
		btnDelete.setBounds(1138, 482, 118, 29);
		add(btnDelete);

		btnAdd = new JButton("Add");
		btnAdd.setOpaque(true);
		btnAdd.setForeground(new Color(0, 0, 205));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				255)));
		btnAdd.setBounds(1138, 524, 118, 29);
		add(btnAdd);

		label = new JLabel("");
		label.setIcon(new ImageIcon(EventList.class
				.getResource("/Images/MetalBackground.jpg")));
		label.setBounds(-26, -28, 1366, 768);
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
		btnSelectEvent.addActionListener(l);
	}
	

	public JLabel getLblChosenEvent() {
		return lblChosenEvent;
	}
	public void setLblChosenEvent(JLabel lblChosenEvent) {
		this.lblChosenEvent = lblChosenEvent;
	}
	public JButton getBtnSelectEvent() {
		return btnSelectEvent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public void updateTable(String value){
		try {
			model.getDataVector().removeAllElements();
 			QueryBuilder qb = new QueryBuilder();
 			
 			
 			
 			rs = qb.selectFrom("events").where("calendarID", "=", value).ExecuteQuery();
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
