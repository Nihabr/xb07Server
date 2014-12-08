package GUI;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import model.QueryBuild.QueryBuilder;
 
public class UserList extends JPanel {
   
	private static final long serialVersionUID = 1L;
//	private static final ActionListener ActionListener = null;

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnMainMenu;
	private JScrollPane scrollPane;
	private JLabel lblUserlist;
	private JLabel lblLogo;
	private JLabel lblChosenUser;
	private JLabel lblBackground;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnEditUser;
	private Object[] objects;
	private ResultSet rs;
	int row;
	
	
	// Panel hvori man kan se brugere. heruner oprettes JButtons, JLabels og et JTable hvori brugerne kan ses.
    public UserList() {
    	setSize(new Dimension(1366, 768));
    	   setLayout(null);
    	
        String[] columnNames = {"UserID",
                                "Email",
                                "Active",
                                "Created datetime",
                                "Password"};
        table = new JTable();
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnNames);
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				row = table.getSelectedRow();
				if(row != -1){
					
					
					lblChosenUser.setText(table.getValueAt(row, 1).toString());
				}
			}
		});

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

		add(scrollPane);

        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(1013, 524, 118, 29);
        btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        btnAdd.setForeground(new Color(0, 0, 205));
        btnAdd.setOpaque(true);
        add(btnAdd);
        
        btnMainMenu = new JButton("Main Menu");
        btnMainMenu.setBounds(616, 596, 194, 50);
        btnMainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnMainMenu.setForeground(Color.WHITE);
        btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
        btnMainMenu.setContentAreaFilled(false);
        btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
        add(btnMainMenu);
        
        lblUserlist = new JLabel("Users");
        lblUserlist.setBounds(534, 90, 298, 90);
        lblUserlist.setForeground(Color.WHITE);
        lblUserlist.setFont(new Font("Arial", Font.BOLD, 78));

        add(lblUserlist);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(1013, 483, 118, 29);
        btnDelete.setOpaque(true);
        btnDelete.setForeground(new Color(0, 0, 205));
        btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        add(btnDelete);
        
        lblLogo = new JLabel("");
        lblLogo.setBounds(36, 695, 268, 67);
        lblLogo.setIcon(new ImageIcon(UserList.class.getResource("/Images/CBSLogo3.png")));
        add(lblLogo);
        
        btnEditUser = new JButton("Edit User");
        btnEditUser.setBounds(1013, 442, 118, 29);
        btnEditUser.setOpaque(true);
        btnEditUser.setForeground(new Color(0, 0, 205));
        btnEditUser.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        add(btnEditUser);
    
        lblBackground = new JLabel("Background");
        lblBackground.setBounds(0, 0, 1376, 768);
        lblBackground.setIcon(new ImageIcon(UserList.class.getResource("/Images/MetalBackground.jpg")));
        lblBackground.setBackground(new Color(245, 245, 245));
        lblBackground.setForeground(new Color(245, 255, 250));
        lblBackground.setOpaque(true);
        add(lblBackground);
        
        lblChosenUser = new JLabel("");
        lblChosenUser.setBounds(1013, 393, 56, 16);
        lblChosenUser.setVisible(false);
        add(lblChosenUser);
    }
    // 
    public void updateTable(){
    	try {
			model.getDataVector().removeAllElements();

			QueryBuilder qb = new QueryBuilder();
			rs = qb.selectFrom("users").where("exist", "=", "1").ExecuteQuery();
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
    
    // Actionlisteners tilføjes.
    public void addActionListener(ActionListener l) {
		btnAdd.addActionListener(l);
		btnDelete.addActionListener(l);
		btnMainMenu.addActionListener(l);
		btnEditUser.addActionListener(l);
		
	}

	public JLabel getLblChosenUser() {
		return lblChosenUser;
	}

	public void setLblChosenUser(JLabel lblChosenUser) {
		this.lblChosenUser = lblChosenUser;
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


	public JButton getBtnEditUser() {
		return btnEditUser;
	}
}