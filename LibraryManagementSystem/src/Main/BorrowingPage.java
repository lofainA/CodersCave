package Main;

import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BorrowingPage implements ActionListener{
	
	JFrame frame = new JFrame();
	JPanel pane = new JPanel();
	JTable bookList =  new JTable();
	
	JTextField bookID = new JTextField("Enter book ID");
	JButton borrow = new JButton("Borrow");
	
	String name = null;
	String phone = null;
	
	JButton returnBook =  new JButton("Return book");
	
	BorrowingPage(String loginName, String loginPhone) throws Exception {
		
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
		
		name = loginName;
		phone = loginPhone;
		
		frame.add(bookID);
		bookID.setBounds(560,100,140,30);
		bookID.setFocusable(true);
		
		frame.add(returnBook);
		returnBook.setBounds(570,250,120,50);
		
		frame.add(borrow);
		borrow.setBounds(590,150,80,50);
		
		frame.add(pane);
		pane.setBounds(-100,50,800,600);
		
		pane.add(bookList);
		bookList = new JTable(getBookData()){
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};;
		
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
		
		pane.add(new JScrollPane(bookList));
		bookList.getColumnModel().getColumn(0).setPreferredWidth(1);
		bookList.getColumnModel().getColumn(1).setPreferredWidth(120);
		bookList.getColumnModel().getColumn(3).setPreferredWidth(40);
		
		returnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					new BookReturnPage(loginName, loginPhone);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		
		borrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet Bookset = null;
				ResultSet Userset =  null;
				try {
					Connection connection = DatabaseConnector.getConnection();
					
					Statement query1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					Statement query2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					Statement query3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					
					Bookset = query1.executeQuery("SELECT * from books;");
					Userset = query2.executeQuery("SELECT * from users;");

					
					List<Integer> IDarray = new ArrayList<>();
					
					while(Bookset.next()) {
				
						int id =  Bookset.getInt(1);
						IDarray.add(id);	
					}
					
					
					int bid = Integer.parseInt(bookID.getText());
					
					if(IDarray.contains(bid)) {
						
						Bookset.beforeFirst();
					
						PreparedStatement q = connection.prepareStatement("insert into borrowedbooks(book_id, book_name, "
								+ "borrower_phone, borrower_name, return_date, status) values(?,?,?,?,?,?)");
						
						System.out.println("Book id: " + bid);
						String bookName = null;
						
						while(Bookset.next()) {
							
							if(Bookset.getInt(1) == bid) {
								
								bookName = Bookset.getString(2);
								if(Bookset.getInt("Stock") == 0) {
									JOptionPane.showMessageDialog(frame, bookName +" is out of stock", "Out of stock", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								
							}
						}
						
						String brPhone = null;
						String brName = null;
						
						while(Userset.next()) {
							if(Userset.getString(3).equals(loginPhone)){
								
								brPhone = loginPhone;
								brName = loginName;
								break;
							}	
						}

						LocalDate currentDate = LocalDate.now();
						LocalDate rd = currentDate.plusWeeks(1);
						Date returnDate = Date.valueOf(rd);
						String status = null;
						
						q.setInt(1,bid);
						q.setString(2, bookName);
						q.setString(3, brPhone);
						q.setString(4, brName);
						q.setDate(5, returnDate);
						q.setString(6, status);
						
						int rowsAffected = q.executeUpdate();

						if(rowsAffected > 0) {
							JOptionPane.showMessageDialog(frame, "Book " + bookName + " has been borrowed. \nYour return date is " 
									+ returnDate + ". \nHave a good read!", "Successful", JOptionPane.INFORMATION_MESSAGE);	
							Bookset.beforeFirst();
							Userset.beforeFirst();
							
							PreparedStatement updateStockQuery = connection.prepareStatement("UPDATE books SET stock = stock - 1 WHERE book_id = ?");
				            updateStockQuery.setInt(1, bid);
				            int updatedRows = updateStockQuery.executeUpdate();
				            
				            updateStockQuery.close();
				            
				            if(updatedRows > 0) {
				            	System.out.println("Stock Updated succesfully");
				            }
				            else { 
				            	System.out.println("Stock Update Failed");
				            }
						}
						else {
						JOptionPane.showMessageDialog(frame, "Failed to borrow the book", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						 JOptionPane.showMessageDialog(frame, "Book with ID " + bid + " not found", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					DatabaseConnector.closeConnection();
				} 
				catch (SQLException | ClassNotFoundException e1) {
				
					e1.printStackTrace();
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(frame, "Enter a valid bookID", 
	        				"Invalid", JOptionPane.ERROR_MESSAGE);	
				} 
				finally {
					try {
						Bookset.close();
						Userset.close();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
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
		
	}
	
	public DefaultTableModel getBookData() throws Exception {
		
		Connection connection = DatabaseConnector.getConnection();
		
		PreparedStatement query = connection.prepareStatement("SELECT * from books;");
		ResultSet books = query.executeQuery();
        
        
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Author", "Genre"}, 0);
    
		while(books.next()) {
			
			model.addRow(new Object[] {books.getInt(1), books.getString(2), books.getString(3), books.getString(4)});
		}
		
		books.close();
        DatabaseConnector.closeConnection();
        
		return model;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
