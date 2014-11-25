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

import javax.swing.JTextField;

public class ShareCalendar extends JPanel{
	private JLabel lblBackground;
	private JLabel label;
	private JLabel lblHeader;
	private JLabel lblChosenCalendar;
	private JLabel lblShareWith;
	private JLabel lblChooseCalendarBy;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Object[] objects;
	private JButton btnBack;
	private JButton btnShare;
	private JTextField textFieldShareWith;
	private JTextField textFieldChooseCal;

	QueryBuilder qb = new QueryBuilder();
	ResultSet rs;
	
	// Panel hvor man kan dele kalndere. Herunder oprettes JTable med kalender info, samt alle JButtons, JLabels og JTextfields.
	
	public ShareCalendar() {
	
		setSize(new Dimension(1366, 768));
		setLayout(null);

		String[] columnNames = { "CalendarID", "Name", "Active", "CreatedBy",
				"PrivatePublic" };
		table = new JTable();
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnNames);
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);

		lblShareWith = new JLabel("Share with"); 
		lblShareWith.setFont(new Font("Arial", Font.BOLD, 26));
		lblShareWith.setForeground(new Color(255, 255, 255));
		lblShareWith.setBounds(313, 571, 159, 31);
		add(lblShareWith);
		
		lblChooseCalendarBy = new JLabel("Choose calendar by ID");
		lblChooseCalendarBy.setFont(new Font("Arial", Font.BOLD, 26));
		lblChooseCalendarBy.setForeground(new Color(255, 255, 255));
		lblChooseCalendarBy.setBounds(313, 533, 278, 31);
		add(lblChooseCalendarBy);
		
		textFieldShareWith = new JTextField();
		textFieldShareWith.setBounds(604, 571, 194, 22);
		add(textFieldShareWith);
		textFieldShareWith.setColumns(10);
		
		textFieldChooseCal = new JTextField();
		textFieldChooseCal.setColumns(10);
		textFieldChooseCal.setBounds(604, 533, 194, 22);
		add(textFieldChooseCal);
		
		btnShare = new JButton("Share");
		btnShare.setForeground(Color.WHITE);
		btnShare.setFont(new Font("Arial", Font.BOLD, 30));
		btnShare.setContentAreaFilled(false);
		btnShare.setBorder(new CompoundBorder(new BevelBorder(
						BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
								0), new Color(255, 255, 255), new Color(0, 0, 0)),
						new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
								new Color(0, 0, 0), new Color(255, 255, 255),
								new Color(0, 0, 0))));
		btnShare.setBounds(604, 617, 194, 50);
		add(btnShare);
		
		lblChosenCalendar = new JLabel("");
		lblChosenCalendar.setBounds(1030, 412, 56, 16);
		add(lblChosenCalendar);

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(421, 180, 553, 315);

		add(scrollPane);

		lblHeader = new JLabel("Calendars");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 78));
		lblHeader.setBounds(527, 77, 376, 90);
		add(lblHeader);


		btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Arial", Font.BOLD, 30));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnBack.setBounds(604, 680, 194, 50);
		add(btnBack);

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
	// metode der opdaterer JTable
	public void updateTable() {
		try {
			// Her fjernes alle elementer i JTable
			model.getDataVector().removeAllElements();

			// Her hentes alle info i tabellen 'calendar' i databasen, hvorefter de tilføjer i JTable.
			rs = qb.selectFrom("calendar").all().ExecuteQuery();
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
	// Actionlisteners tilføjes.
	public void addActionListener(ActionListener l) {
		btnShare.addActionListener(l);
		btnBack.addActionListener(l);
	}
	public JButton getBtnBack() {
		return btnBack;
	}
	public JButton getBtnShare() {
		return btnShare;
	}
	public JTextField getTextFieldShareWith() {
		return textFieldShareWith;
	}
	public JTextField getTextFieldChooseCal() {
		return textFieldChooseCal;
	}
	
}
