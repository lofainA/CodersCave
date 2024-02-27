package Main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LandingPage implements ActionListener {
	
	JFrame frame = new JFrame();
	
	JPanel panel =  new JPanel();
	
	JLabel head =  new JLabel("Library System");
	JButton studentLogin =  new JButton("Student Login");
	JButton adminLogin =  new JButton("Admin Login");
	
	LandingPage() {
		
		
		frame.add(head);
		head.setBounds(250,90,800,100);
		head.setFont(new Font("Roboto", Font.BOLD, 38));
		
		frame.add(studentLogin);
		studentLogin.setBounds(180, 300, 200, 50);
		studentLogin.setFont(new Font("Roboto", Font.PLAIN, 20));
		studentLogin.setFocusable(false);
		studentLogin.addActionListener(this);
		
		frame.add(adminLogin);
		adminLogin.setBounds(400, 300, 200, 50);
		adminLogin.setFont(new Font("Roboto", Font.PLAIN, 20));
		adminLogin.setFocusable(false);
		adminLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new AdminLoginPage();
			}
			
		});
		
		frame.setTitle("Library Management System");
		frame.setLayout(null);
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == studentLogin) {
			frame.dispose();
			new UserLoginPage();
			
		}
		
	}
}
