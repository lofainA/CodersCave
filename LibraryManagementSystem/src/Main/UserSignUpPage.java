package Main;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class UserSignUpPage extends JFrame implements ActionListener {
	
	JFrame frame = new JFrame();
	
	JLabel head =  new JLabel("User SignUp");
	JTextField name =  new JTextField("Name");
	JTextField phone = new JTextField("Phn.no");
	JTextField address = new JTextField("Address");
	JButton signUp = new JButton("Sign Up");

	
	UserSignUpPage() {
		
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
				new UserLoginPage();
				frame.dispose();
				
			}
			
		});
		
		frame.add(head);
		head.setBounds(270,90,800,100);
		head.setFont(new Font("Roboto", Font.BOLD, 38));
		
		frame.add(name);
		name.setBounds(170, 220, 400, 30);
		
		frame.add(phone);
		phone.setBounds(170, 270, 400, 30);
		
		frame.add(address);
		address.setBounds(170, 320, 400, 30);
		
		frame.add(signUp);
		signUp.setBounds(270, 400, 200, 50 );
		signUp.addActionListener(this);
		
		frame.setTitle("Library Management System");
		frame.setLayout(null);
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void insertUsers() throws Exception {
		
		Connection connection = DatabaseConnector.getConnection();
        
        PreparedStatement id = connection.prepareStatement("SELECT User_ID FROM users ORDER BY User_ID DESC LIMIT 1;");
        ResultSet rsID = id.executeQuery();
        int curruid = 0;
        
        while(rsID.next()) {
        	curruid = rsID.getInt("User_ID");
        }
        
        String sql = "insert into users (user_id, name, phone, address) values (?,?,?,?)";
        try (PreparedStatement insert = connection.prepareStatement(sql)) {
        	
        	int uid = curruid + 1;
            String n = name.getText();
            String phn = phone.getText();
            String ad = address.getText();
            
            insert.setInt(1, uid);
            insert.setString(2, n);
            insert.setString(3, phn);
            insert.setString(4,ad);
            
            int usersInserted = insert.executeUpdate();
        }
        
        rsID.close();
        id.close();
        DatabaseConnector.closeConnection();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == signUp) {
			try {
				if(name.getText().equals("Name") || phone.getText().equals("Phn.no") || address.getText().equals("Address")) {
					JOptionPane.showMessageDialog(frame, "Fill in credentials", 
	        				"Invalid", JOptionPane.ERROR_MESSAGE);
				}
				else if(name.getText().isEmpty() || phone.getText().isEmpty() || address.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame, "Fill in credentials", 
	        				"Invalid", JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					insertUsers();
					JOptionPane.showMessageDialog(frame, "Sign Up Succesful!", "Sign Up", JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
					new BorrowingPage(name.getText(), phone.getText());
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
