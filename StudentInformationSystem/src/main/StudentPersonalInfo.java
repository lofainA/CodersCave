package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class StudentPersonalInfo extends JPanel implements ActionListener{
	
	JLabel head = new JLabel("Student details");
	
	JLabel name = new JLabel("Name: ");
	JLabel reg = new JLabel("Registration no: ");
	JLabel dob = new JLabel("DOB: ");
	JLabel phone = new JLabel("Phone no: ");
	JLabel email = new JLabel("E-mail: ");
	JLabel sex = new JLabel("Gender: ");
	JLabel address = new JLabel("Address: ");
	JLabel quota = new JLabel("Quota: ");
	JLabel cgpa = new JLabel("CGPA: ");
	
	Font detFont = new Font("Calibri", Font.BOLD, 22);

	JButton check = new JButton("Check Academic details");
	
	String student;
	
	ImageIcon icon = new ImageIcon("icons\\left-arrow.png");
	Image sIcon = icon.getImage().getScaledInstance(25, 17, Image.SCALE_SMOOTH);
	ImageIcon sizedIcon = new ImageIcon(sIcon);
	
	JButton button = new JButton(sizedIcon);
	
	int year;
	
	public StudentPersonalInfo(String regno)  throws Exception{
		
		student = regno;
		
		add(button);
		button.addActionListener(this);
		button.setBounds(20,20,35,30);
		button.setFocusable(false);
		
		setLayout(null);
		
		int s = 100;
		
		add(head);
		head.setBounds(275,50,500,50);
		head.setFont(new Font("Calibri", Font.BOLD, 42));
		
		add(name);
		s += 40;
		name.setBounds(130, s, 500, 50);
		name.setFont(detFont);
		
		add(reg);
		s += 40;
		reg.setBounds(130, s, 500, 50);
		reg.setFont(detFont);
		
		add(dob);
		s += 40;
		dob.setBounds(130, s, 500, 50);
		dob.setFont(detFont);
		
		add(phone);
		s += 40;
		phone.setBounds(130, s, 500, 50);
		phone.setFont(detFont);
		
		add(email);
		s += 40;
		email.setBounds(130, s, 500, 50);
		email.setFont(detFont);
		
		add(sex);
		s += 40;
		sex.setBounds(130, s, 500, 50);
		sex.setFont(detFont);
		
		add(address);
		s += 40;
		address.setBounds(130, s, 500, 50);
		address.setFont(detFont);
		
		add(quota);
		s += 40;
		quota.setBounds(130, s, 500, 50);
		quota.setFont(detFont);
		
		add(cgpa);
		s += 40;
		cgpa.setBounds(130, s, 500, 50);
		cgpa.setFont(detFont);
		
		add(check);
		check.setBounds(290, 550, 200, 50);
		
		MainFrame parentFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
		
		check.addActionListener(this);
		
		Connection connection = DatabaseConnector.getConnection();
		
		PreparedStatement ps = connection.prepareStatement("Select * from stpersonal_info");
		ResultSet rs = ps.executeQuery();
		
		PreparedStatement ps1 = connection.prepareStatement("Select * from student");
		ResultSet rs1 = ps1.executeQuery();
		
		while(rs1.next()) {
			
			if(rs1.getString("Register_no").equals(regno)) {
				year = rs1.getInt("Batch");
			}
		}
		
		while(rs.next()) {
			
			if(rs.getString("Register_no").equals(regno)) {
				
				
				JLabel rname = new JLabel(rs.getString("Name"));
				JLabel rreg = new JLabel(rs.getString("Register_no"));
				JLabel rdob = new JLabel(rs.getString("DOB"));
				JLabel rphone = new JLabel(rs.getString("Phone"));
				JLabel remail = new JLabel(rs.getString("E-mail"));
				JLabel rsex = new JLabel(rs.getString("Sex"));
				JLabel raddress = new JLabel(rs.getString("Address"));
				JLabel rquota = new JLabel(rs.getString("Quota"));
				JLabel rcgpa = new JLabel(rs.getString("CGPA"));
				
				detFont = new Font("Calibri", Font.PLAIN, 22);
				
				s = 100;
				int i = 40;
				
				add(rname);
				s += 40;
				rname.setBounds(330, s, 500, 50);
				rname.setFont(detFont);
				
				add(rreg);
				s += 40;
				rreg.setBounds(330, s, 500, 50);
				rreg.setFont(detFont);
				
				add(rdob);
				s += 40;
				rdob.setBounds(330, s, 500, 50);
				rdob.setFont(detFont);
				
				add(rphone);
				s += 40;
				rphone.setBounds(330, s, 500, 50);
				rphone.setFont(detFont);
				
				add(remail);
				s += 40;
				remail.setBounds(330, s, 500, 50);
				remail.setFont(detFont);
				
				add(rsex);
				s += 40;
				rsex.setBounds(330, s, 500, 50);
				rsex.setFont(detFont);
				
				add(raddress);
				s += 40;
				raddress.setBounds(330, s, 500, 50);
				raddress.setFont(detFont);
				
				add(rquota);
				s += 40;
				rquota.setBounds(330, s, 500, 50);
				rquota.setFont(detFont);
				
				add(rcgpa);
				s += 40;
				rcgpa.setBounds(330, s, 500, 50);
				rcgpa.setFont(detFont);
				
			}
		}
		ps.close();
		rs.close();
		DatabaseConnector.closeConnection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame parentFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
		
		if(e.getSource() == check) {
			
			
			if (parentFrame instanceof MainFrame) {
	            try {
					parentFrame.showPanel(new AcademicDetails(student));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getSource() == button) {
			
			//MainFrame parentFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
			
			if (parentFrame instanceof MainFrame) {
	            try {
					parentFrame.showPanel(new BatchInfo(year));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
