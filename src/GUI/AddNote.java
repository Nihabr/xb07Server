package GUI;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AddNote extends JPanel {
	
	private JLabel lblBackground, lblCBSlogo;
	private JButton btnMainMenu;
	private JButton btnAddNote;
	private JTextField textFieldText;
	private JTextField textFieldCreatedBy;
	private JLabel lblCreatedBy;
	private JLabel lblNote;
	public AddNote(){
		
		setPreferredSize(new Dimension(1366, 768));
		setSize(new Dimension(1366, 768));
		setLayout(null);
	
	lblCreatedBy = new JLabel("Created by");
	lblCreatedBy.setBounds(519, 175, 97, 16);
	add(lblCreatedBy);
	
	lblNote = new JLabel("Note");
	lblNote.setBounds(545, 258, 56, 16);
	add(lblNote);
	
	textFieldCreatedBy = new JTextField();
	textFieldCreatedBy.setBounds(646, 172, 231, 22);
	add(textFieldCreatedBy);
	textFieldCreatedBy.setColumns(10);
	
	textFieldText = new JTextField();
	textFieldText.setBounds(646, 207, 231, 119);
	add(textFieldText);
	textFieldText.setColumns(10);
	
	btnMainMenu = new JButton("Main menu");
	btnMainMenu.setBounds(665, 459, 97, 25);
	add(btnMainMenu);
	
	lblCBSlogo = new JLabel();
	lblCBSlogo.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/CBSLogo3.png")));
	lblCBSlogo.setBounds(10, 698, 250, 59);
	add(lblCBSlogo);
	
	btnAddNote = new JButton("Add note");
	btnAddNote.setBounds(665, 420, 97, 25);
	add(btnAddNote);

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
		btnMainMenu.addActionListener(l);
		
	}
	public JButton getBtnMainMenu() {
		return btnMainMenu;
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
}
