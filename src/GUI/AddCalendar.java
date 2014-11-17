package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;



public class AddCalendar extends JPanel {
	private JLabel lblBackground;
	private JLabel label;
	private JLabel lblHeader;
	private JButton btnBack;
	private JButton btnLogout;
	private JButton btnSubmit;
	private JLabel lblCalendarID;
	private JLabel lblName;
	private JLabel lblActive;
	private JLabel lblCreatedBy;
	private JLabel lblPrivateOrPublic;
	private JTextField txtCalendarID;
	private JTextField textName;
	private JTextField textActive;
	private JTextField textCreatedBy;
	private JTextField textPrivateOrPublic;
	
	
	
	
	public AddCalendar() {
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
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
		
		lblCalendarID = new JLabel("Calendar ID:");
		lblCalendarID.setForeground(Color.WHITE);
		lblCalendarID.setFont(new Font("Arial", Font.BOLD, 26));
		lblCalendarID.setBounds(476, 220, 162, 39);
		add(lblCalendarID);
		
		lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Arial", Font.BOLD, 26));
		lblName.setBounds(476, 270, 147, 39);
		add(lblName);
		
		lblActive = new JLabel("Active:");
		lblActive.setForeground(Color.WHITE);
		lblActive.setFont(new Font("Arial", Font.BOLD, 26));
		lblActive.setBounds(476, 320, 147, 39);
		add(lblActive);
		
		lblCreatedBy = new JLabel("Created by:");
		lblCreatedBy.setSize(147, 39);
		lblCreatedBy.setLocation(473, 440);
		lblCreatedBy.setForeground(Color.WHITE);
		lblCreatedBy.setFont(new Font("Arial", Font.BOLD, 26));
		lblCreatedBy.setBounds(476, 370, 147, 39);
		add(lblCreatedBy);
		
		lblPrivateOrPublic = new JLabel("Private or public:");
		lblPrivateOrPublic.setForeground(Color.WHITE);
		lblPrivateOrPublic.setFont(new Font("Arial", Font.BOLD, 26));
		lblPrivateOrPublic.setBounds(476, 420, 223, 39);
		add(lblPrivateOrPublic);
		
		txtCalendarID = new JTextField();
		txtCalendarID.setBounds(727, 229, 134, 28);
		add(txtCalendarID);
		txtCalendarID.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(727, 279, 134, 28);
		add(textName);
		textName.setColumns(10);
		
		textActive = new JTextField();
		textActive.setBounds(727, 329, 134, 28);
		add(textActive);
		textActive.setColumns(10);
		
		textCreatedBy = new JTextField();
		textCreatedBy.setBounds(727, 379, 134, 28);
		add(textCreatedBy);
		textCreatedBy.setColumns(10);
		
		textPrivateOrPublic = new JTextField();
		textPrivateOrPublic.setBounds(727, 429, 134, 28);
		add(textPrivateOrPublic);
		textPrivateOrPublic.setColumns(10);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);
		
		}
	
	public void addActionListener(ActionListener l) {
		btnLogout.addActionListener(l);
		btnBack.addActionListener(l);
		btnSubmit.addActionListener(l);
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
}
