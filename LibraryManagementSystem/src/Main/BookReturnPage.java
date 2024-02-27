package Main;

import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookReturnPage implements ActionListener{
	
	JFrame frame = new JFrame();
	JPanel pane = new JPanel();
	JTable bookList =  new JTable();
	
	JTextField bookID = new JTextField("Book id");
	JButton returnBook = new JButton("Return");
	
	String name = null;
	String phone = null;
	
	BookReturnPage(String loginName, String loginPhone) throws Exception {
		
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
				try {
					new BorrowingPage(loginName, loginPhone);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.dispose();
				
			}
			
		});
		
		name = loginName;
		phone = loginPhone;
		
		frame.add(bookID);
		bookID.setBounds(560,100,140,30);
		
		frame.add(returnBook);
		returnBook.setBounds(590,150,80,50);
		returnBook.addActionListener(this);
		returnBook.setFocusable(false);
		
		frame.add(pane);
		pane.setBounds(-100,50,800,600);
		
		pane.add(bookList);
		bookList = new JTable(getBookData(loginPhone)){
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};
		
		pane.add(new JScrollPane(bookList));
		bookList.getColumnModel().getColumn(0).setPreferredWidth(1);
		bookList.getColumnModel().getColumn(1).setPreferredWidth(120);
		bookList.getColumnModel().getColumn(3).setPreferredWidth(40);
		
		bookList.addMouseListener(new MouseAdapter() { 
			  public void mouseClicked(MouseEvent e) { 
				  int row = bookList.rowAtPoint(e.getPoint()); 
				  int col = bookList.columnAtPoint(e.getPoint()); 
				  if (row >= 0 && col == 0) {
					  Object id = bookList.getValueAt(row, 0);
		  
					  bookID.setText(id.toString()); 
				  } 
			  } 
		});
		
		bookID.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		       
		        if (bookID.getText().equals("Enter book ID")) {
		            bookID.setText("");
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        
		        if (bookID.getText().isEmpty()) {
		            bookID.setText("Enter book ID");
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
	
	public DefaultTableModel getBookData(String loginPhone) throws Exception {
		
		System.out.println("User phone: " + loginPhone);
		Connection connection = DatabaseConnector.getConnection();
		
		PreparedStatement query = connection.prepareStatement("SELECT * from borrowedbooks;");
		ResultSet books = query.executeQuery();
        
        
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Return date", "Status"}, 0);
    
		while(books.next()) {
			
			if(books.getString(3).equals(loginPhone)) {
				model.addRow(new Object[] {books.getInt(1), books.getString(2), books.getString(5), books.getString(6)});
			}
			
		}
		
		books.close();
        DatabaseConnector.closeConnection();
        
		return model;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == returnBook) {
			
			ResultSet borrowedBooks = null;
			try {
				Connection connection = DatabaseConnector.getConnection();
				
				Statement query1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				borrowedBooks = query1.executeQuery("SELECT * from borrowedbooks;");
				
				List<Integer> IDarray = new ArrayList<>();
				
				String bookName = null;
				int bid = Integer.parseInt(bookID.getText());
				
				while(borrowedBooks.next()) {
					int id =  borrowedBooks.getInt(1);
					IDarray.add(id);
					
					if(id == bid) {
						bookName = borrowedBooks.getString(2);
					}
				}
				
				if(IDarray.contains(bid)) {
					
					borrowedBooks.beforeFirst();
					borrowedBooks.next();
					
					PreparedStatement deleteRowQuery = connection.prepareStatement("DELETE from borrowedbooks where Book_ID = ?");
		            deleteRowQuery.setInt(1, bid);
		            int deletedRows = deleteRowQuery.executeUpdate();
		            deleteRowQuery.close();
		            
		            if(deletedRows > 0) {
		            	JOptionPane.showMessageDialog(frame, "Your book, " + bookName + "\nhas been returned to the library", 
		        				"Success", JOptionPane.PLAIN_MESSAGE);
		            	
		            	PreparedStatement updateStockQuery = connection.prepareStatement("UPDATE books SET stock = stock + 1 WHERE book_id = ?");
			            updateStockQuery.setInt(1, bid);
			            int updatedRows = updateStockQuery.executeUpdate();
			            updateStockQuery.close();
			            
			            DefaultTableModel model = (DefaultTableModel) bookList.getModel();
			            int selectedRow = bookList.getSelectedRow();
			            if (selectedRow != -1) {
			                model.removeRow(selectedRow);
			            }

		            }
				}	
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
	}
}
