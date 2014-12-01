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



public class MainMenu extends JPanel {
	private JLabel lblMainMenu;
	private JButton btnLogOut;
	private JButton btnUserlist;
	private JButton btnEventlist;
	private JButton btnNotelist;
	private JLabel lblCBSlogo;
	private JLabel lblBackground;
	private JButton btnCalendars;

	// Panel med hovedmenu oprettes.
	
	public MainMenu() {
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setForeground(Color.WHITE);
		lblMainMenu.setFont(new Font("Arial", Font.BOLD, 78));
		lblMainMenu.setBounds(481, 90, 404, 90);
		add(lblMainMenu);
		
		btnCalendars = new JButton("Calendars");
		btnCalendars.setContentAreaFilled(false);
		btnCalendars.setForeground(Color.WHITE);
		btnCalendars.setFont(new Font("Arial", Font.BOLD, 30));
		btnCalendars.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnCalendars.setBackground(Color.WHITE);
		btnCalendars.setBounds(588, 245, 194, 50);
		add(btnCalendars);
		
		btnUserlist = new JButton("Users");
		btnUserlist.setContentAreaFilled(false);
		btnUserlist.setForeground(Color.WHITE);
		btnUserlist.setFont(new Font("Arial", Font.BOLD, 30));
		btnUserlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnUserlist.setBackground(Color.WHITE);
		btnUserlist.setBounds(588, 385, 194, 50);
		add(btnUserlist);
		
		btnEventlist = new JButton("Events");
		btnEventlist.setContentAreaFilled(false);
		btnEventlist.setForeground(Color.WHITE);
		btnEventlist.setFont(new Font("Arial", Font.BOLD, 30));
		btnEventlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnEventlist.setBackground(Color.WHITE);
		btnEventlist.setBounds(588, 455, 194, 50);
		add(btnEventlist);
		
		btnNotelist = new JButton("Notes");
		btnNotelist.setContentAreaFilled(false);
		btnNotelist.setForeground(Color.WHITE);
		btnNotelist.setFont(new Font("Arial", Font.BOLD, 30));
		btnNotelist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnNotelist.setBackground(Color.WHITE);
		btnNotelist.setBounds(588, 315, 194, 50);
		add(btnNotelist);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogOut.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setBounds(588, 575, 194, 50);
		add(btnLogOut);
		

		
		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);
		
		
	}
	// Actionlisteners tilføjes
	public void addActionListener(ActionListener l) {
		btnLogOut.addActionListener(l);
		btnEventlist.addActionListener(l);
		btnNotelist.addActionListener(l);
		btnUserlist.addActionListener(l);
		btnCalendars.addActionListener(l);
	}
	
	public JButton getBtnUserlist() {
		return btnUserlist;
	}
	public JButton getBtnEventlist() {
		return btnEventlist;
	}
	public JButton getBtnNotelist() {
		return btnNotelist;
	}
	public JButton getBtnLogOut() {
		return btnLogOut;
	}
	public JButton getBtnCalendars() {
		return btnCalendars;
	}

}
