package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class AddEventGUI extends JPanel {
	private JTextField textField_Location;
	private JTextField textField_Createdby;
	private JTextField textFieldStartYear;
	private JButton btnSubmit;
	private JButton btnBack;
	private JLabel lblCBSlogo;
	private JButton btnMainMenu;
	private JLabel lblCreate;
	private JLabel lblEnd;
	private JLabel lblName;
	private JLabel lblText;
	private JLabel lblEmail;
	private JLabel lblTeam;
	private JLabel lblBackground;
	private JLabel lblCreateddate;
	private JTextField textField_Name;
	private JTextField textField_Text;
	private JLabel lblType;
	private JTextField textField_Type;
	private JTextField textFieldStartMonth;
	private JTextField textFieldStartHour;
	private JTextField textFieldStartMinute;
	private JTextField textFieldStartSecond;
	private JTextField textFieldEndSecond;
	private JTextField textFieldEndMinute;
	private JTextField textFieldEndHour;
	private JTextField textFieldEndMonth;
	private JTextField textFieldEndYear;
	private JTextField textFieldStartDateDay;
	private JTextField textFieldEndDateDay;
	private JLabel lblStartDate;
	private JLabel lblHour;
	private JLabel lblNewLabel_1;
	private JLabel lblSecond;

	/**
	 * Create the panel.
	 */
	public AddEventGUI() {
		setPreferredSize(new Dimension(1366, 768));
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		textFieldEndDateDay = new JTextField();
		textFieldEndDateDay.setForeground(SystemColor.controlDkShadow);
		textFieldEndDateDay.setColumns(10);
		textFieldEndDateDay.setBounds(885, 346, 53, 34);
		add(textFieldEndDateDay);
		
		textFieldStartDateDay = new JTextField();
		textFieldStartDateDay.setForeground(SystemColor.controlDkShadow);
		textFieldStartDateDay.setColumns(10);
		textFieldStartDateDay.setBounds(885, 297, 53, 34);
		add(textFieldStartDateDay);

		textFieldEndSecond = new JTextField();
		textFieldEndSecond.setForeground(SystemColor.controlDkShadow);
		textFieldEndSecond.setColumns(10);
		textFieldEndSecond.setBounds(1080, 346, 53, 34);
		add(textFieldEndSecond);

		textFieldEndMonth = new JTextField();
		textFieldEndMonth.setForeground(SystemColor.controlDkShadow);
		textFieldEndMonth.setColumns(10);
		textFieldEndMonth.setBounds(820, 346, 53, 34);
		add(textFieldEndMonth);

		textFieldEndMinute = new JTextField();
		textFieldEndMinute.setForeground(SystemColor.controlDkShadow);
		textFieldEndMinute.setColumns(10);
		textFieldEndMinute.setBounds(1015, 346, 53, 34);
		add(textFieldEndMinute);

		textFieldEndHour = new JTextField();
		textFieldEndHour.setForeground(SystemColor.controlDkShadow);
		textFieldEndHour.setColumns(10);
		textFieldEndHour.setBounds(950, 346, 53, 34);
		add(textFieldEndHour);

		textFieldEndYear = new JTextField();
		textFieldEndYear.setForeground(SystemColor.controlDkShadow);
		textFieldEndYear.setColumns(10);
		textFieldEndYear.setBounds(755, 346, 53, 34);
		add(textFieldEndYear);

		textFieldStartSecond = new JTextField();
		textFieldStartSecond.setForeground(SystemColor.controlDkShadow);
		textFieldStartSecond.setColumns(10);
		textFieldStartSecond.setBounds(1080, 297, 53, 34);
		add(textFieldStartSecond);

		textFieldStartMinute = new JTextField();
		textFieldStartMinute.setForeground(SystemColor.controlDkShadow);
		textFieldStartMinute.setColumns(10);
		textFieldStartMinute.setBounds(1015, 297, 53, 34);
		add(textFieldStartMinute);

		textFieldStartHour = new JTextField();
		textFieldStartHour.setForeground(SystemColor.controlDkShadow);
		textFieldStartHour.setColumns(10);
		textFieldStartHour.setBounds(950, 297, 53, 34);
		add(textFieldStartHour);

		textFieldStartMonth = new JTextField();
		textFieldStartMonth.setForeground(SystemColor.controlDkShadow);
		textFieldStartMonth.setColumns(10);
		textFieldStartMonth.setBounds(820, 297, 53, 34);
		add(textFieldStartMonth);

		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(UserInfo.class
				.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Arial", Font.BOLD, 30));
		btnBack.setContentAreaFilled(false);
		btnBack.setBounds(552, 688, 239, 50);
		add(btnBack);

		textField_Location = new JTextField();
		textField_Location.setForeground(new Color(105, 105, 105));
		textField_Location.setColumns(10);
		textField_Location.setBounds(755, 191, 120, 34);
		add(textField_Location);

		lblEmail = new JLabel("Location");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 26));
		lblEmail.setBounds(483, 195, 109, 30);
		add(lblEmail);

		textField_Createdby = new JTextField();
		textField_Createdby.setForeground(new Color(105, 105, 105));
		textField_Createdby.setColumns(10);
		textField_Createdby.setBounds(755, 238, 120, 34);
		add(textField_Createdby);

		lblTeam = new JLabel("Created by");
		lblTeam.setFont(new Font("Arial", Font.BOLD, 26));
		lblTeam.setForeground(new Color(255, 255, 255));
		lblTeam.setBounds(483, 242, 159, 31);
		add(lblTeam);

		textFieldStartYear = new JTextField();
		textFieldStartYear.setForeground(new Color(105, 105, 105));
		textFieldStartYear.setColumns(10);
		textFieldStartYear.setBounds(755, 297, 53, 34);
		add(textFieldStartYear);

		lblCreateddate = new JLabel("Start");
		lblCreateddate.setForeground(new Color(255, 255, 255));
		lblCreateddate.setFont(new Font("Arial", Font.BOLD, 26));
		lblCreateddate.setBounds(483, 300, 159, 31);
		add(lblCreateddate);

		btnSubmit = new JButton("Create");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 30));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Submit changes to databases
				// hvad sker der n���r ���ndringer ved en bruger submittes
			}
		});
		btnSubmit.setBounds(552, 548, 239, 50);
		add(btnSubmit);

		btnMainMenu = new JButton("Main menu");
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0,
						0), new Color(255, 255, 255), new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
						new Color(0, 0, 0), new Color(255, 255, 255),
						new Color(0, 0, 0))));
		btnMainMenu.setBounds(552, 618, 239, 50);
		add(btnMainMenu);

		lblCreate = new JLabel("Create event");
		lblCreate.setForeground(Color.WHITE);
		lblCreate.setFont(new Font("Arial", Font.BOLD, 78));
		lblCreate.setBounds(451, 90, 466, 91);
		add(lblCreate);

		lblEnd = new JLabel("End");
		lblEnd.setForeground(Color.WHITE);
		lblEnd.setFont(new Font("Arial", Font.BOLD, 26));
		lblEnd.setBounds(483, 349, 159, 31);
		add(lblEnd);

		lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Arial", Font.BOLD, 26));
		lblName.setBounds(483, 391, 159, 31);
		add(lblName);

		lblText = new JLabel("Text");
		lblText.setForeground(Color.WHITE);
		lblText.setFont(new Font("Arial", Font.BOLD, 26));
		lblText.setBounds(483, 441, 159, 31);
		add(lblText);

		textField_Name = new JTextField();
		textField_Name.setForeground(SystemColor.controlDkShadow);
		textField_Name.setColumns(10);
		textField_Name.setBounds(755, 391, 120, 34);
		add(textField_Name);

		textField_Text = new JTextField();
		textField_Text.setForeground(SystemColor.controlDkShadow);
		textField_Text.setColumns(10);
		textField_Text.setBounds(755, 438, 120, 34);
		add(textField_Text);

		textField_Type = new JTextField();
		textField_Type.setForeground(SystemColor.controlDkShadow);
		textField_Type.setColumns(10);
		textField_Type.setBounds(755, 485, 120, 34);
		add(textField_Type);

		lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Arial", Font.BOLD, 26));
		lblType.setBounds(483, 483, 159, 31);
		add(lblType);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(755, 275, 61, 16);
		add(lblYear);
		
		JLabel lblNewLabel = new JLabel("Month");
		lblNewLabel.setBounds(820, 275, 61, 16);
		add(lblNewLabel);
		
		lblStartDate = new JLabel("Date");
		lblStartDate.setBounds(885, 275, 61, 16);
		add(lblStartDate);
		
		lblHour = new JLabel("Hour");
		lblHour.setBounds(950, 275, 61, 16);
		add(lblHour);
		
		lblNewLabel_1 = new JLabel("Minute");
		lblNewLabel_1.setBounds(1015, 275, 61, 16);
		add(lblNewLabel_1);
		
		lblSecond = new JLabel("Second");
		lblSecond.setBounds(1080, 275, 61, 16);
		add(lblSecond);
		// =======

		// >>>>>>> FETCH_HEAD

		lblBackground = new JLabel("");
		lblBackground.setSize(new Dimension(1366, 768));
		lblBackground.setIcon(new ImageIcon(UserInfo.class
				.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);

	}

	public void addActionListener(ActionListener l) {
		btnSubmit.addActionListener(l);
		btnMainMenu.addActionListener(l);
		btnBack.addActionListener(l);
	}
	
	public String startDateTimeToString(){
		
		String start = getTextFieldStartYear().getText()+"-" + getTextFieldStartMonth().getText() + "-" + 
				getTextFieldStartDateDay().getText() + " " + getTextFieldStartHour().getText()+":"+getTextFieldStartMinute().getText() + ":" +
				getTextFieldStartSecond().getText();
		
		return start;
	}
	
	public String endDateTimeToString(){
		
		String end = getTextFieldEndYear().getText()+"-" + getTextFieldEndMonth().getText() + "-" + 
				getTextFieldEndDateDay().getText() + " " + getTextFieldEndHour().getText()+":"+getTextFieldEndMinute().getText() + ":" +
				getTextFieldEndSecond().getText();
		
		return end;
	}
	

	public JTextField getTextFieldStartDateDay() {
		return textFieldStartDateDay;
	}

	public JTextField getTextFieldEndDateDay() {
		return textFieldEndDateDay;
	}

	public JTextField getTextFieldStartYear() {
		return textFieldStartYear;
	}

	public JTextField getTextFieldStartMonth() {
		return textFieldStartMonth;
	}

	public JTextField getTextFieldStartHour() {
		return textFieldStartHour;
	}

	public JTextField getTextFieldStartMinute() {
		return textFieldStartMinute;
	}

	public JTextField getTextFieldStartSecond() {
		return textFieldStartSecond;
	}

	public JTextField getTextFieldEndSecond() {
		return textFieldEndSecond;
	}

	public JTextField getTextFieldEndMinute() {
		return textFieldEndMinute;
	}

	public JTextField getTextFieldEndHour() {
		return textFieldEndHour;
	}

	public JTextField getTextFieldEndMonth() {
		return textFieldEndMonth;
	}

	public JTextField getTextFieldEndYear() {
		return textFieldEndYear;
	}

	public JTextField getTextField_Location() {
		return textField_Location;
	}

	public JTextField getTextField_Createdby() {
		return textField_Createdby;
	}

//	public JTextField getTextField_Start() {
//		return textFieldStartYear;
//	}



	public JTextField getTextField_Name() {
		return textField_Name;
	}

	public JTextField getTextField_Text() {
		return textField_Text;
	}

	public JTextField getTextField_Type() {
		return textField_Type;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}

	public JButton getBtnBack() {
		return btnBack;
	}
}