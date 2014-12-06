package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;

import model.QueryBuild.QueryBuilder;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class AddNote extends JPanel {
	
	private JLabel lblBackground, lblCBSlogo;
	private JButton btnAddNote;
	private JTextField textFieldText;
	private JLabel lblNote;
	private JLabel lblAddUser;
	private JButton btnBack;
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Object[] objects;
	QueryBuilder qb = new QueryBuilder();
	ResultSet rs;
	public AddNote(){
		
		setPreferredSize(new Dimension(1366, 768));
		setSize(new Dimension(1366, 768));
		setLayout(null);
		String[] columnNames = { "CalendarID", "Name", "Active", "CreatedBy",
				"PrivatePublic","Email" };
		table = new JTable();
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnNames);
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				
//				row = table.getSelectedRow();
//				if(row != -1){
//				
//				
//
//				lblChosenCalendar.setText(table.getValueAt(row, 1).toString());
//				
//				}
//			}
//		});
	lblAddUser = new JLabel("Add note");
	lblAddUser.setForeground(Color.WHITE);
	lblAddUser.setFont(new Font("Arial", Font.BOLD, 78));
	lblAddUser.setBounds(509, 107, 464, 90);
	add(lblAddUser);
	
	lblNote = new JLabel("Note:");
	lblNote.setForeground(Color.WHITE);
	lblNote.setFont(new Font("Arial", Font.BOLD, 26));
	lblNote.setBounds(473, 312, 105, 31);
	add(lblNote);
	
	textFieldText = new JTextField();
	textFieldText.setBounds(651, 302, 231, 119);
	add(textFieldText);
	textFieldText.setColumns(10);
	
	lblCBSlogo = new JLabel();
	lblCBSlogo.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/CBSLogo3.png")));
	lblCBSlogo.setBounds(10, 698, 250, 59);
	add(lblCBSlogo);
	
	btnAddNote = new JButton("Add note");
	btnAddNote.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	btnAddNote.setContentAreaFilled(false);
	btnAddNote.setForeground(Color.WHITE);
	btnAddNote.setFont(new Font("Arial", Font.BOLD, 30));
	btnAddNote.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
	btnAddNote.setBackground(Color.WHITE);
	btnAddNote.setBounds(661, 475, 194, 50);
	add(btnAddNote);
	
	btnBack = new JButton("Back");
	btnBack.setContentAreaFilled(false);
	btnBack.setForeground(Color.WHITE);
	btnBack.setFont(new Font("Arial", Font.BOLD, 30));
	btnBack.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
	btnBack.setBackground(Color.WHITE);
	btnBack.setBounds(661, 545, 194, 50);
	add(btnBack);

	lblBackground = new JLabel("");
	lblBackground.setSize(new Dimension(1366, 768));
	lblBackground.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/MetalBackground.jpg")));
	lblBackground.setBounds(0, 0, 1366, 768);
	add(lblBackground);
	}
	public void addActionListener(ActionListener l) {
	
		textFieldText.addActionListener(l);
		btnAddNote.addActionListener(l);
		btnBack.addActionListener(l);
		
	}
	public JButton getBtnAddNote() {
		return btnAddNote;
	}
	public JTextField getTextFieldText() {
		return textFieldText;
	}

	public JButton getBtnBack() {
		return btnBack;
	}
}
