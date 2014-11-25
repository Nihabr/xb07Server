package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;

import model.QueryBuild.QueryBuilder;



public class AddCalendar extends JPanel {
	private JLabel lblBackground;
	private JLabel label;
	private JLabel lblHeader;
	private JButton btnBack;
	private JButton btnLogout;
	private JButton btnSubmit;
	private JLabel lblName;
	private JLabel lblPrivateOrPublic;
	private JTextField textName;
	private JTextField textPrivateOrPublic;
	private JTextField txtShare;
	private JLabel lblShareWith;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Object[] objects;
	QueryBuilder qb = new QueryBuilder();
	ResultSet rs;
	
	//Herunder tilføjes et JTable med dertilhørende scrollpane, samt alle JLabels, JButtons og JTextfields i tilføj kalender interfacet. 
	
	public AddCalendar() {
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		String[] columnNames = { "Email" };
		table = new JTable();
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnNames);
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(547, 184, 260, 182);
		add(scrollPane);

		lblShareWith = new JLabel("Share with:");
		lblShareWith.setForeground(Color.WHITE);
		lblShareWith.setFont(new Font("Arial", Font.BOLD, 26));
		lblShareWith.setBounds(476, 454, 147, 39);
		add(lblShareWith);
		
		txtShare = new JTextField();
		txtShare.setColumns(10);
		txtShare.setBounds(727, 466, 134, 28);
		add(txtShare);
		
		lblHeader = new JLabel("Add Calendar");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 78));
		lblHeader.setBounds(424, 81, 515, 90);
		add(lblHeader);
		
		btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Arial", Font.BOLD, 30));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnBack.setBounds(601, 590, 194, 50);
		add(btnBack);
		
		btnLogout = new JButton("Log out");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogout.setBounds(601, 660, 194, 50);
		add(btnLogout);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 30));
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnSubmit.setBounds(601, 520, 194, 50);
		add(btnSubmit);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(NoteList.class.getResource("/Images/CBSLogo3.png")));
		label.setBounds(10, 698, 250, 59);
		add(label);
		
		lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Arial", Font.BOLD, 26));
		lblName.setBounds(476, 379, 147, 39);
		add(lblName);
		
		lblPrivateOrPublic = new JLabel("Private or public:");
		lblPrivateOrPublic.setForeground(Color.WHITE);
		lblPrivateOrPublic.setFont(new Font("Arial", Font.BOLD, 26));
		lblPrivateOrPublic.setBounds(476, 420, 223, 39);
		add(lblPrivateOrPublic);
		
		textName = new JTextField();
		textName.setBounds(727, 388, 134, 28);
		add(textName);
		textName.setColumns(10);
		
		textPrivateOrPublic = new JTextField();
		textPrivateOrPublic.setBounds(727, 429, 134, 28);
		add(textPrivateOrPublic);
		textPrivateOrPublic.setColumns(10);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);
		
		}
	// Her tilføjes Actionlisteners
	public void addActionListener(ActionListener l) {
		btnLogout.addActionListener(l);
		btnBack.addActionListener(l);
		btnSubmit.addActionListener(l);
	}
	// Der oprettes en metode der kan opdatere tabellen. 
	public void updateTable() {
		try {
			// her fjernes alle elementer i JTable
			model.getDataVector().removeAllElements();
			// Henter values 'email' fra table 'users' i databasen. 
			QueryBuilder qb = new QueryBuilder();
			String[] values = {"email"};
			rs = qb.selectFrom(values, "users").all().ExecuteQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// Henter values 'email' fra table 'users' i databasen.
			int colNo = rsmd.getColumnCount();
			// tilføjer emails til JTable.
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
	
	public JButton getBtnBack() {
		return btnBack;
	}
	public JButton getBtnLogout() {
		return btnLogout;
	}
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JTextField getTxtShare() {
		return txtShare;
	}

	public JTextField getTextName() {
		return textName;
	}


	public JTextField getTextPrivateOrPublic() {
		return textPrivateOrPublic;
	}
}
