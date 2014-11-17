package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionEvent;

public class AddNote extends JPanel {
	
	private JLabel lblBackground, lblCBSlogo;
	private JButton btnAddNote;
	private JTextField textFieldText;
	private JTextField textFieldCreatedBy;
	private JLabel lblCreatedBy;
	private JLabel lblNote;
	private JLabel lblAddUser;
	private JButton btnBack;
	public AddNote(){
		
		setPreferredSize(new Dimension(1366, 768));
		setSize(new Dimension(1366, 768));
		setLayout(null);
	
	lblAddUser = new JLabel("Add note");
	lblAddUser.setForeground(Color.WHITE);
	lblAddUser.setFont(new Font("Arial", Font.BOLD, 78));
	lblAddUser.setBounds(509, 107, 464, 90);
	add(lblAddUser);
	
	lblCreatedBy = new JLabel("Created by:");
	lblCreatedBy.setForeground(Color.WHITE);
	lblCreatedBy.setFont(new Font("Arial", Font.BOLD, 26));
	lblCreatedBy.setBounds(473, 261, 147, 39);
	add(lblCreatedBy);
	
	lblNote = new JLabel("Note:");
	lblNote.setForeground(Color.WHITE);
	lblNote.setFont(new Font("Arial", Font.BOLD, 26));
	lblNote.setBounds(473, 312, 105, 31);
	add(lblNote);
	
	textFieldCreatedBy = new JTextField();
	textFieldCreatedBy.setBounds(651, 267, 231, 22);
	add(textFieldCreatedBy);
	textFieldCreatedBy.setColumns(10);
	
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
		textFieldCreatedBy.addActionListener(l);
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
	public JTextField getTextFieldCreatedBy() {
		return textFieldCreatedBy;
	}
	public JButton getBtnBack() {
		return btnBack;
	}
}
