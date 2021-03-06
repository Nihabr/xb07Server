package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentListener;

import java.awt.Cursor;

public class UserInfo extends JPanel {
	private JTextField txtField_UserID;
	private JTextField txtField_Email;
	private JTextField txtField_Password;
	private JTextField txtField_CreatedDate;
	private JButton btnSubmit;
	private JButton btnBack;
	private JLabel lblOnlineDot;
	private JLabel lblCBSlogo;
	private JLabel lblUserInfo;
	private JLabel lblUserID;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblCreateddate;
	private JLabel lblOnline;
	private JLabel lblBackground;

	// Panel hvor man kan ændre bruger informationer.
	public UserInfo() {
		setPreferredSize(new Dimension(1366, 768));
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);
		

		btnBack = new JButton("Back");
		btnBack.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Arial", Font.BOLD, 30));
		btnBack.setContentAreaFilled(false);
		btnBack.setBounds(563, 570, 239, 50);
		add(btnBack);

		txtField_UserID = new JTextField();
		txtField_UserID.setForeground(new Color(105, 105, 105));
		txtField_UserID.setText("");
		txtField_UserID.setName("");
		txtField_UserID.setBounds(755, 207, 120, 34);
		add(txtField_UserID);
		txtField_UserID.setColumns(10);

		lblUserID = new JLabel("UserID");
		lblUserID.setForeground(new Color(255, 255, 255));
		lblUserID.setFont(new Font("Arial", Font.BOLD, 26));
		lblUserID.setBounds(483, 210, 84, 30);
		add(lblUserID);

		txtField_Email = new JTextField();
		txtField_Email.setForeground(new Color(105, 105, 105));
		txtField_Email.setText("");
		txtField_Email.setColumns(10);
		txtField_Email.setBounds(755, 255, 120, 34);
		add(txtField_Email);

		lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 26));
		lblEmail.setBounds(483, 253, 71, 30);
		add(lblEmail);

		txtField_Password = new JTextField();
		txtField_Password.setForeground(new Color(105, 105, 105));
		txtField_Password.setText("");
		txtField_Password.setColumns(10);
		txtField_Password.setBounds(755, 301, 120, 34);
		add(txtField_Password);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 26));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(483, 296, 129, 30);
		add(lblPassword);

		txtField_CreatedDate = new JTextField();
		txtField_CreatedDate.setForeground(new Color(105, 105, 105));
		txtField_CreatedDate.setText("");
		txtField_CreatedDate.setColumns(10);
		txtField_CreatedDate.setBounds(755, 347, 120, 34);
		add(txtField_CreatedDate);
		
		lblCreateddate = new JLabel("Created Date");
		lblCreateddate.setForeground(new Color(255, 255, 255));
		lblCreateddate.setFont(new Font("Arial", Font.BOLD, 26));
		lblCreateddate.setBounds(483, 339, 159, 31);
		add(lblCreateddate);

		lblOnline = new JLabel("Online");
		lblOnline.setForeground(new Color(255, 255, 255));
		lblOnline.setFont(new Font("Arial", Font.BOLD, 26));
		lblOnline.setBounds(483, 420, 82, 30);
		add(lblOnline);

		btnSubmit = new JButton("Submit changes");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 30));
		btnSubmit.setBounds(563, 500, 239, 50);
		add(btnSubmit);

		lblOnlineDot = new JLabel("");
		lblOnlineDot.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/Red.png")));
		lblOnlineDot.setBounds(755, 433, 20, 20);
		add(lblOnlineDot);
		lblOnlineDot.setBackground(Color.RED);
		lblOnlineDot.repaint();
		lblOnlineDot.setText("Offline");
				
		lblUserInfo = new JLabel("User Info");
		lblUserInfo.setForeground(Color.WHITE);
		lblUserInfo.setFont(new Font("Arial", Font.BOLD, 78));
		lblUserInfo.setBounds(514, 90, 338, 90);
		add(lblUserInfo);
						
		lblBackground = new JLabel("");
		lblBackground.setSize(new Dimension(1366, 768));
		lblBackground.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);
						
	}
	// metode der clearer textfields
	public void Refresh(){
	txtField_UserID.setText("");
	txtField_Password.setText("");
	txtField_Email.setText("");
	txtField_CreatedDate.setText("");
	}
	// documentlistener tilføjes
	public void addDocumentListener(DocumentListener e){
		txtField_UserID.getDocument().addDocumentListener(e);
	}
	// actionlistener tilføjes
	public void addActionListener(ActionListener l) {
		btnSubmit.addActionListener(l);
		btnBack.addActionListener(l);
	}
	// metode der tjekker om brugeren er online. er active = 1 (online), sættes 'dot' til grøn, er active derimod andet (0), sættes 'dot' til rød for offline.
	public void setOnlineDot(int active){
		if (active == 1) {
			lblOnlineDot.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/Green.png")));
			lblOnlineDot.setText("Online");


		}
		else{
			lblOnlineDot.setIcon(new ImageIcon(UserInfo.class.getResource("/Images/Red.png")));
			lblOnlineDot.setText("Offline");

		}
	}
		

	public JTextField getTxtField_UserID() {
		return txtField_UserID;
	}

	public JTextField getTxtField_Email() {
		return txtField_Email;
	}

	public JTextField getTxtField_Password() {
		return txtField_Password;
	}

	public JTextField getTxtField_CreatedDate() {
		return txtField_CreatedDate;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	
	public JButton getBtnBack() {
		return btnBack;
	}
}