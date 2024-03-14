package main;

import java.awt.BorderLayout;
import java.util.Stack;

import javax.swing.*;

public class MainFrame extends JFrame{
	
	private JPanel currentPanel;
	
	public MainFrame() {
		
		setTitle("SIS");
		//setLayout(null);
		setLayout(new BorderLayout());
		setSize(800,700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//currentPanel = null;
		
		showPanel(new StartPage());
		
	}
	
	public void showPanel(JPanel panel) {
       
        if (currentPanel != null) {
            getContentPane().remove(currentPanel);
        }

        currentPanel = panel;

        getContentPane().add(currentPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
