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
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import model.QueryBuild.QueryBuilder;

public class Calendar extends JPanel {
	private JButton btnChooseCalendar;
	private JLabel lblBackground;
	private JLabel label;
	private JLabel lblHeader;

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Object[] objects;
	QueryBuilder qb = new QueryBuilder();
	ResultSet rs;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnMainMenu;
	private JButton btnShare;
	private String id;
	private String name;
	private String isCBS;
	int row;
	private JLabel lblCalendarInfo;

	
	
	

	public Calendar() {
		setSize(new Dimension(1366, 768));
		setLayout(null);

		String[] columnNames = { "CalendarID", "Name", "Active", "CreatedBy","privatepublic","IsCBS" };
		table = new JTable();
		model = (DefaultTableModel) table.getModel();
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
				
				id = table.getValueAt(row, 0).toString();
				setId(id);
				name = table.getValueAt(row, 1).toString();
				setName(name);
				isCBS = table.getValueAt(row, 5).toString();
				setIsCBS(isCBS);

				
				}
			}
		});
		

		
		lblCalendarInfo = new JLabel("");
		lblCalendarInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCalendarInfo.setBounds(490, 198, 413, 29);
		
		add(lblCalendarInfo);
		

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
		scrollPane.setBounds(283, 240, 695, 315);

		// Add the scroll pane to this panel.
		add(scrollPane);

		lblHeader = new JLabel("Calendars");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 78));
		lblHeader.setBounds(527, 77, 376, 90);
		add(lblHeader);

		btnAdd = new JButton("Add");
		btnAdd.setOpaque(true);
		btnAdd.setForeground(new Color(0, 0, 205));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				255)));
		btnAdd.setBounds(998, 490, 118, 29);
		add(btnAdd);

		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				255)));
		btnDelete.setBounds(998, 526, 118, 29);

		
		btnChooseCalendar = new JButton("Choose Calendar");
		btnChooseCalendar.setOpaque(true);
		btnChooseCalendar.setForeground(new Color(0, 0, 205));
		btnChooseCalendar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnChooseCalendar.setBounds(998, 455, 118, 29);
		add(btnChooseCalendar);
		
		btnShare = new JButton("Share");
		btnShare.setOpaque(true);
		btnShare.setForeground(new Color(0, 0, 205));
		btnShare.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnShare.setBounds(998, 420, 118, 29);
		add(btnShare);
		
		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnDelete.setBounds(998, 525, 118, 29);

		add(btnDelete);

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
		btnMainMenu.setBounds(537, 590, 194, 50);
		add(btnMainMenu);

		label = new JLabel("");
		label.setIcon(new ImageIcon(NoteList.class
				.getResource("/Images/CBSLogo3.png")));
		label.setBounds(10, 698, 250, 59);
		add(label);

		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MainMenu.class
				.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);

	}

	public void addActionListener(ActionListener l) {
		btnChooseCalendar.addActionListener(l);
		btnAdd.addActionListener(l);
		btnDelete.addActionListener(l);
		btnMainMenu.addActionListener(l);
		btnShare.addActionListener(l);
	}

	
	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}


	public void updateTable(String value) {
		try {
			model.getDataVector().removeAllElements();

			rs = qb.selectFrom("calendar").where("active", "=", value).ExecuteQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();

			while (rs.next()) {

				objects = new Object[colNo];

				for (int i = 0; i < colNo; i++) {
					objects[i] = rs.getObject(i + 1);
				}
				model.addRow(objects);
			}
			table.setModel(model);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public JButton getBtnChooseCalendar() {
		return btnChooseCalendar;
	}
 		
	public JButton getChooseCalendar() {
		return btnChooseCalendar;

	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}

	public JButton getBtnShare() {
		return btnShare;
	}

	public JLabel getLblCalendarInfo() {
		return lblCalendarInfo;
	}

	public void setLblCalendarInfo(JLabel lblCalendarInfo) {
		this.lblCalendarInfo = lblCalendarInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsCBS() {
		return isCBS;
	}

	public void setIsCBS(String isCBS) {
		this.isCBS = isCBS;
	}
	
}