package Main;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class AdminLoginPage implements ActionListener{
	
	JFrame frame = new JFrame();
	
	JLabel head =  new JLabel("Admin Login");
	
	JTextField username =  new JTextField("Username");
	JTextField password = new JTextField("Password");
	JButton login = new JButton("Login");
	
	String loginName = null;
	String loginPassword = null;
	
	public AdminLoginPage() {
		
		ImageIcon icon = new ImageIcon("icons\\left-arrow.png");
		Image sIcon = icon.getImage().getScaledInstance(25, 17, Image.SCALE_SMOOTH);
		ImageIcon sizedIcon = new ImageIcon(sIcon);
		
		JButton button = new JButton(sizedIcon);
		
		button.setBounds(20,20,35,30);
		button.setFocusable(false);
		frame.add(button);
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new LandingPage();
				frame.dispose();
				
			}
			
		});
		
		frame.add(head);
		head.setBounds(270,90,800,100);
		head.setFont(new Font("Roboto", Font.BOLD, 38));
		
		frame.add(username);
		username.setBounds(170, 250, 400, 30);
		
		frame.add(password);
		password.setBounds(170, 300, 400, 30);
		
		frame.add(login);
		login.setBounds(270, 370, 200, 50 );
		
		login.addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent ae) {
				
				try {
					authorization();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			
		});
		
		username.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		       
		        if (username.getText().equals("Username")) {
		            username.setText("");
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        
		        if (username.getText().isEmpty()) {
		            username.setText("Username");
		        }
		    }
		});
		
		password.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		       
		        if (password.getText().equals("Password")) {
		            password.setText("");
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        
		        if (password.getText().isEmpty()) {
		            password.setText("Password");
		        }
		    }
		});
		
		frame.setLayout(null);
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void authorization() throws Exception {
		
		Connection connection = DatabaseConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM admins");
        ResultSet admins = statement.executeQuery();
        
		try {
			
            String un = username.getText();
			String pwd = password.getText();
			boolean flag = false;
            
            while(admins.next()) {
            	
            	if(un.equals(admins.getString("username")) && pwd.equals(admins.getString("password"))) { 
            		
            		new BookStockManager(un, pwd);
            		frame.dispose();
            		flag = true;
            		break;
            	}
            	
            	else {
            		flag = false;
            	}
            } 
            
            if(flag == false) {
        		JOptionPane.showMessageDialog(frame, "Invalid username or password!", 
        				"Invalid credentials", JOptionPane.ERROR_MESSAGE);
        	}
        	
        	
        	admins.close();
            statement.close();
            DatabaseConnector.closeConnection();
         
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
