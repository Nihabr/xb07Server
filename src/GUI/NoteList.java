package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class NoteList extends JPanel {
	private JTable table;
	private final JLabel lblBackground = new JLabel("");
	private JLabel lblHeader;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnMainMenu;
	private JButton btnLogout;
	private JLabel label;
	private Object[] objects;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private ResultSet rs;
	private int row;
	private String noteID;
	

	//Panel med noteliste oprettes.
	
	public NoteList() {
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		//Laver tabellen inde i Eventlisten.
		String[] columnNames = { "noteId", "eventId", "createdBy", "text", "Created", "active"};

		table = new JTable();
    	model = (DefaultTableModel)table.getModel();
    	model.setColumnIdentifiers(columnNames);
    	// Her hentes alle data ift. noter i databasen i tabellen 'notes', og tilføjes JTable.
    
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				row = table.getSelectedRow();
				if(row != -1){
				
				noteID = table.getValueAt(row, 0).toString();
				setNoteID(noteID);
				
				
//				lblCalendarName.setText(table.getValueAt(row, 1).toString());
				
				}
			}
		});

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(149, 171, 1062, 376);

		add(scrollPane);
		
		
		lblHeader = new JLabel("Notes");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 78));
		lblHeader.setBounds(527, 77, 312, 90);
		add(lblHeader);
		
		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnDelete.setBounds(1223, 518, 118, 29);
		add(btnDelete);
		
		btnAdd = new JButton("Add");
		btnAdd.setOpaque(true);
		btnAdd.setForeground(new Color(0, 0, 205));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnAdd.setBounds(1223, 484, 118, 29);
		add(btnAdd);
		
		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnMainMenu.setBounds(601, 589, 194, 50);
		add(btnMainMenu);
		
		btnLogout = new JButton("Log out");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogout.setBounds(601, 659, 194, 50);
		add(btnLogout);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(NoteList.class.getResource("/Images/CBSLogo3.png")));
		label.setBounds(10, 698, 250, 59);
		add(label);
		lblBackground.setIcon(new ImageIcon(NoteList.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		
		add(lblBackground);
	}
	public String getNoteID() {
		return noteID;
	}
	public void setNoteID(String noteID) {
		this.noteID = noteID;
	}
	// Actionlisteners tilføjes
	public void addActionListener(ActionListener l) {
		btnAdd.addActionListener(l);
		btnDelete.addActionListener(l);
		btnLogout.addActionListener(l);
		btnMainMenu.addActionListener(l);
	}
	public void updateTable(String value){
		try {
			model.getDataVector().removeAllElements();
 			QueryBuilder qb = new QueryBuilder();
 			
 			
 			
 			rs = qb.selectFrom("notes").where("eventID", "=", value).ExecuteQuery();
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
	

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}
	
}
