package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class AddUser extends JPanel {
	private JTextField textField_Email;
	private JTextField textField_Type;
	private JTextField textField_Password;
	private JButton btnSubmit;
	private JLabel lblCBSlogo;
	private JButton btnMainMenu;
	private JLabel lblUserInfo;
	private JLabel lblEmail;
	private JLabel lblType;
	private JLabel lblUserOrAdmin;
	private JLabel lblPass;
	private JLabel lblBackground;
	
	
	// Laver panel der kan tilføje brugere. Alle JLabels, JButtons og JTextfields oprettes.
	public AddUser() {
		setPreferredSize(new Dimension(1366, 768));
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		lblCBSlogo = new JLabel();
		lblCBSlogo.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);

		textField_Email = new JTextField();
		textField_Email.setForeground(new Color(105, 105, 105));
		textField_Email.setName("");
		textField_Email.setBounds(755, 213, 120, 34);
		textField_Email.setColumns(10);
		add(textField_Email);
	
		lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 26));
		lblEmail.setBounds(483, 211, 104, 30);
		add(lblEmail);

		textField_Type = new JTextField();
		textField_Type.setForeground(new Color(105, 105, 105));
		textField_Type.setColumns(10);
		textField_Type.setBounds(755, 259, 120, 34);
		add(textField_Type);

		lblType = new JLabel("User type");
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setFont(new Font("Arial", Font.BOLD, 26));
		lblType.setBounds(483, 257, 139, 30);
		add(lblType);
		
		lblUserOrAdmin = new JLabel("(user/admin)");
		lblUserOrAdmin.setForeground(new Color(255, 255, 255));
		lblUserOrAdmin.setFont(new Font("Arial", Font.BOLD, 16));
		lblUserOrAdmin.setBounds(619, 262, 109, 25);
		add(lblUserOrAdmin);

		textField_Password = new JTextField();
		textField_Password.setForeground(new Color(105, 105, 105));
		textField_Password.setColumns(10);
		textField_Password.setBounds(755, 305, 120, 34);
		add(textField_Password);

		lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Arial", Font.BOLD, 26));
		lblPass.setForeground(new Color(255, 255, 255));
		lblPass.setBounds(483, 300, 127, 30);
		add(lblPass);

		btnSubmit = new JButton("Create User");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 30));
		btnSubmit.setBounds(560, 400, 194, 50);
		add(btnSubmit);
	
		btnMainMenu = new JButton("Main menu");
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnMainMenu.setBounds(560, 470, 194, 50);
		add(btnMainMenu);
				
		lblUserInfo = new JLabel("Create user");
		lblUserInfo.setForeground(Color.WHITE);
		lblUserInfo.setFont(new Font("Arial", Font.BOLD, 78));
		lblUserInfo.setBounds(451, 90, 464, 90);
		add(lblUserInfo);
						
		lblBackground = new JLabel("");
		lblBackground.setSize(new Dimension(1366, 768));
		lblBackground.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);

		

	}
	// Actionlisteners tilfæjes
	public void addActionListener(ActionListener l) {
		btnSubmit.addActionListener(l);
		btnMainMenu.addActionListener(l);
	}

	

	public JTextField getTextField_Email() {
		return textField_Email;
	}

	public JTextField getTextField_Type() {
		return textField_Type;
	}

	public JTextField getTextField_Password() {
		return textField_Password;
	}

	
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}
	

}
