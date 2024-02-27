package Main;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class UserLoginPage implements ActionListener{
	
	JFrame frame = new JFrame();
	
	JLabel head =  new JLabel("User Login");
	JTextField name =  new JTextField("Name");
	JTextField phone = new JTextField("Phn.no");
	JButton login = new JButton("Login");
	JButton signUp = new JButton("Sign Up");
	
	String loginName = null;
	String loginPhone = null;
	
	UserLoginPage()  {
		
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
		
		frame.add(name);
		name.setBounds(170, 250, 400, 30);
	
		frame.add(phone);
		phone.setBounds(170, 300, 400, 30);
		
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
		
		frame.add(signUp);
		signUp.setBounds(320,410,100,50);
		signUp.setOpaque(false);
		signUp.setContentAreaFilled(false);
		signUp.setBorderPainted(false);
		signUp.setForeground(Color.blue);
		signUp.setText("<html><u>Sign Up</u></html>");
		signUp.setFocusable(false);
		signUp.addActionListener(this);
		
		name.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		       
		        if (name.getText().equals("Name")) {
		            name.setText("");
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        
		        if (name.getText().isEmpty()) {
		            name.setText("Name");
		        }
		    }
		});
		
		phone.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		       
		        if (phone.getText().equals("Phn.no")) {
		            phone.setText("");
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        
		        if (phone.getText().isEmpty()) {
		            phone.setText("Phn.no");
		        }
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
	
	public void authorization() throws Exception {
		
		Connection connection = DatabaseConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet users = statement.executeQuery();
        
		try {
			
            String n = name.getText();
			String phn = phone.getText();
			boolean flag = false;
            
            while(users.next()) {
            	
            	if(n.equals(users.getString(2)) && phn.equals(users.getString(3))) { 
            		
            		new BorrowingPage(n, phn);
            		frame.dispose();
            		flag = true;
            		break;
            	}
            	else {
            		flag = false;
            	}
            } 
            
            if(flag == false) {
        		JOptionPane.showMessageDialog(frame, "Invalid Name or Phone number!", 
        				"Invalid credentials", JOptionPane.ERROR_MESSAGE);
        	}
        	
        	
        	users.close();
            statement.close();
            DatabaseConnector.closeConnection();
         
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == signUp) {
			frame.dispose();
			new UserSignUpPage();
		}
		
	}

}
