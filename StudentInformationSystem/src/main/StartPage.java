package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

import javax.swing.*;

public class StartPage extends JPanel implements ActionListener{
	
	//JButton test = new JButton("Test Button");
	JLabel head =  new JLabel("Student Information System");
	JLabel label = new JLabel("Select the batch you want information from:");
	
	JButton batch1 = new JButton("2021");
	JButton batch2 = new JButton("2022");
	JButton batch3 = new JButton("2023");
	
	public StartPage() {
		
		setLayout(null);
		
		add(head);
		head.setBounds(150,150,500,50);
		head.setFont(new Font("Calibri", Font.BOLD, 42));
		
		add(label);
		label.setBounds(180, 220, 500, 50);
		label.setFont(new Font("Calibri", Font.PLAIN, 24));
		
		add(batch1);
		add(batch2);
		add(batch3);
		
		batch1.setBounds(190, 400, 100, 50);
		batch2.setBounds(340, 400, 100, 50);
		batch3.setBounds(490, 400, 100, 50);
		
		batch1.addActionListener(this);
		batch2.addActionListener(this);
		batch3.addActionListener(this);
		
		batch3.setEnabled(false);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		MainFrame parentFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
		
		if(e.getSource() == batch1) {
			if (parentFrame instanceof MainFrame) {
				int year = Integer.parseInt(batch1.getText());
	            parentFrame.showPanel(new BatchInfo(year));
			}
			
		}
		
		if(e.getSource() == batch2) {
			if (parentFrame instanceof MainFrame) {
				int year = Integer.parseInt(batch2.getText());
	            parentFrame.showPanel(new BatchInfo(year));
			}
			
		}
	}
}
