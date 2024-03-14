package main;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class AcademicDetails extends JPanel implements ActionListener{
	
	JLabel head = new JLabel("Academic Details");
	
	JLabel name = new JLabel();
	JLabel reg = new JLabel();
	
	JLabel cgpaLab = new JLabel("CGPA:     ");
	JLabel cgpa = new JLabel();
	
	Font detFont = new Font("Calibri", Font.PLAIN, 20);
	
	
	ImageIcon icon = new ImageIcon("icons\\left-arrow.png");
	Image sIcon = icon.getImage().getScaledInstance(25, 17, Image.SCALE_SMOOTH);
	ImageIcon sizedIcon = new ImageIcon(sIcon);
	
	JButton button = new JButton(sizedIcon);
	
	String stud;
	
	AcademicDetails(String regno) throws Exception{
		
		stud = regno;
		
		button.addActionListener(this);
		
		setLayout(null);
		
		button.setBounds(20,20,35,30);
		button.setFocusable(false);
		add(button);
		
		
		add(head);
		head.setBounds(245,50,500,50);
		head.setFont(new Font("Calibri", Font.BOLD, 42));
		
		Connection connection = DatabaseConnector.getConnection();
		
		PreparedStatement ps = connection.prepareStatement("Select * from academic_info where Register_no = ?");
		ps.setString(1, regno);
		ResultSet rs = ps.executeQuery();
		
		PreparedStatement ps1 = connection.prepareStatement("Select * from student where Register_no = ?");
		ps1.setString(1, regno);
		ResultSet rs1 = ps1.executeQuery();
		
		add(name);
		
		name.setBounds(280, 100, 500, 100);
		name.setFont(detFont);
		
		add(reg);
		reg.setFont(detFont);
		reg.setBounds(280, 130, 500, 100);
		
		int batch = 0;
		
		while(rs1.next()) {
			name.setText("Name:  " + rs1.getString("Name"));
			reg.setText("Reg.no:  " + rs1.getString("Register_no"));
			batch  = rs1.getInt("Batch");
		}
		
		ps1.close();
		rs1.close();
		
		int numOfSems = (Integer.parseInt(Year.now().toString()) - batch) *2;
		
		int y = 230;
		int col = 4;
		int cgpaInt = 0;
		
		while(rs.next()) {
			
			for(int j = 1; j <= numOfSems; j++) {
				
				float mark = rs.getFloat(col);
				
				JLabel sem = new JLabel();
				sem.setText("Sem " + j + ":      " + mark);
				add(sem);
				sem.setBounds(300, y, 150, 50);
				sem.setFont(new Font("Calibri", Font.PLAIN, 22));
				
				cgpaInt += mark;
				y += 40;
				col++;
			}
		}
		
		add(cgpaLab);
		cgpaLab.setBounds(300, 500, 150, 50);
		cgpaLab.setFont(new Font("Calibri", Font.PLAIN, 28));
		
		float totCgpa = cgpaInt/numOfSems;
		
		add(cgpa);
		cgpa.setText(Float.toString(totCgpa));
		cgpa.setBounds(380, 500, 150, 50);
		cgpa.setFont(new Font("Calibri", Font.BOLD, 28));
		
		
		ps.close();
		rs.close();
		
		DatabaseConnector.closeConnection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button) {
			
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
}
