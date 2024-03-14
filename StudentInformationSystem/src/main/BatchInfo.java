package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Year;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BatchInfo extends JPanel implements ActionListener{
	
	JLabel head =  new JLabel("Student List");
	
	JButton cse = new JButton("CSE");
	JButton it = new JButton("IT");
	JButton ece = new JButton("ECE");
	JButton eee = new JButton("EEE");
	
	JTextField studReg = new JTextField();
	JButton find = new JButton("Get Info");
	
	ButtonGroup bg = new ButtonGroup();
	
	JPanel pane = new JPanel();
	JTable table = new JTable(){ 
		public boolean isCellEditable(int row, int column) { 
			return false; 
		} 
	};
	
	String stud;
	
	ImageIcon icon = new ImageIcon("icons\\left-arrow.png");
	Image sIcon = icon.getImage().getScaledInstance(25, 17, Image.SCALE_SMOOTH);
	ImageIcon sizedIcon = new ImageIcon(sIcon);
	
	JButton button = new JButton(sizedIcon);
	
	public BatchInfo(int year){
		
		add(button);
		button.addActionListener(this);
		button.setBounds(20,20,35,30);
		button.setFocusable(false);
		
		add(studReg);
		add(find);
		studReg.setBounds(620, 250,100,30);
		find.setBounds(620, 300, 100, 30);
		find.addActionListener(this);
		
		setLayout(null);
		add(pane);
		
		add(head);
		head.setBounds(280,50,500,50);
		head.setFont(new Font("Calibri", Font.BOLD, 42));
			 
		setTableModel(year, table, "all");
		
		add(cse);
		cse.setBounds(140, 130, 80, 40);
		cse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setTableModel(year, table, "CSE");
			}
		});
		
		add(it);
		it.setBounds(290, 130, 80, 40);
		it.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setTableModel(year, table, "IT");
			}
		});
		
		add(ece);
		ece.addActionListener(this);
		ece.setEnabled(false);
		ece.setBounds(440, 130, 80, 40);
		
		add(eee);
		eee.addActionListener(this);
		eee.setEnabled(false);
		eee.setBounds(590, 130, 80, 40);
		
		bg.add(cse);
		bg.add(it);
		bg.add(ece);
		bg.add(eee);
		
		pane.setBounds(-70,200,900,500);

		table.addMouseListener(new MouseAdapter() { 
			  public void mouseClicked(MouseEvent e) { 
				  int row = table.rowAtPoint(e.getPoint()); 
				  int col = table.columnAtPoint(e.getPoint());
				  
				  if (row >= 0 && col >= 0) {
					  Object reg = table.getValueAt(row,2);
					  stud = reg.toString(); 
					  studReg.setText(stud);
					   
				  } 
			  } 
		});
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scrollPane = new JScrollPane(table); 
		pane.add(scrollPane);
		scrollPane.setSize(900,300);

	}
	
	
	public DefaultTableModel getBookData(int year, String dept) throws Exception {
		
		Connection connection = DatabaseConnector.getConnection();
		
		Statement query = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet student = query.executeQuery("SELECT * from student;");
		
		Statement query1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet studentpi = query1.executeQuery("SELECT * from stpersonal_info;");
        
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Register no.", "E-mail"}, 0);
        
        if(dept.equals("all")) {
        	
        	while(student.next() && studentpi.next()) {
    			
    			if(student.getInt("batch") == year) {
    				
    				model.addRow(new Object[] {student.getInt(1), student.getString(2), student.getString(3), studentpi.getString(6)});
    			}
    		}
    		return model;
        }
        
        else {
        	
        	DefaultTableModel newModel = new DefaultTableModel(new Object[]{"ID", "Name", "Register no.", "E-mail"}, 0);
        	
        	while(student.next() && studentpi.next()) {
        		
    			if(student.getInt("batch") == year) {
    				
    				if(student.getString("dept").equals(dept)) {
    					
    					newModel.addRow(new Object[] {student.getInt(1), student.getString(2), student.getString(3), studentpi.getString(6)});
    				}
    			}
    		}
        	
        	student.close();
    		studentpi.close();
    		DatabaseConnector.closeConnection();
    		
        	return newModel;
        }
	}
	
	public void setTableModel(int year, JTable table, String dept) {
		
		try {
			DefaultTableModel newModel;
			newModel = getBookData(year, dept);
			table.setModel(newModel);
	        
	        table.getColumnModel().getColumn(0).setPreferredWidth(20);
			table.getColumnModel().getColumn(1).setPreferredWidth(130);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == find) {
			
			if(!studReg.getText().equals("")) {
				MainFrame parentFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
				
				if (parentFrame instanceof MainFrame) {
		            try {
						parentFrame.showPanel(new StudentPersonalInfo(stud));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		if(e.getSource() == button) {
			
			MainFrame parentFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
			
			if (parentFrame instanceof MainFrame) {
	            try {
					parentFrame.showPanel(new StartPage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
