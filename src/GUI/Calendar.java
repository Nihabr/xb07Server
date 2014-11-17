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
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import model.QueryBuild.QueryBuilder;

public class Calendar extends JPanel {
	private JButton btnVlgKalender;
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
	private JButton btnLogout;

	public Calendar() {
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
		scrollPane.setBounds(425, 240, 553, 315);

		// Add the scroll pane to this panel.
		add(scrollPane);

		lblHeader = new JLabel("Calendars");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 78));
		lblHeader.setBounds(527, 77, 376, 90);
		add(lblHeader);

		btnVlgKalender = new JButton("V\u00E6lg kalender");
		btnVlgKalender.setOpaque(true);
		btnVlgKalender.setForeground(new Color(0, 0, 205));
		btnVlgKalender.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
				0, 0, 255)));
		btnVlgKalender.setBounds(998, 460, 118, 29);
		add(btnVlgKalender);

		btnAdd = new JButton("Add");
		btnAdd.setOpaque(true);
		btnAdd.setForeground(new Color(0, 0, 205));
		btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				255)));
		btnAdd.setBounds(998, 493, 118, 29);
		add(btnAdd);

		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				255)));
		btnDelete.setBounds(998, 526, 118, 29);
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
		btnMainMenu.setBounds(601, 589, 194, 50);
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
		btnLogout.setBounds(601, 659, 194, 50);
		add(btnLogout);

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
		btnVlgKalender.addActionListener(l);
		btnAdd.addActionListener(l);
		btnDelete.addActionListener(l);
		btnMainMenu.addActionListener(l);
		btnLogout.addActionListener(l);
	}

	public void updateTable() {
		try {
			model.getDataVector().removeAllElements();
			QueryBuilder qb = new QueryBuilder();
			rs = qb.selectFrom("calender").all().ExecuteQuery();
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

	public JButton getBtnVlgKalender() {
		return btnVlgKalender;
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

	public JButton getBtnLogout() {
		return btnLogout;
	}
}