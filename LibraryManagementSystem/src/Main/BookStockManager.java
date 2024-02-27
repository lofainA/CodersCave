package Main;

import java.awt.Image;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookStockManager implements ActionListener{
	
	JFrame frame = new JFrame();
	JPanel pane = new JPanel();
	JTable bookList =  new JTable();
	
	JLabel addLabel = new JLabel("Add Book to Library");
	JTextField bookName = new JTextField("Enter book name");
	JTextField bookAuthor = new JTextField("Enter author name");
	JTextField bookGenre = new JTextField("Enter the genre");
	
	JButton addBook = new JButton("Add Book");
	
	String un = null;
	String pwd = null;
	
	
	BookStockManager(String username, String password) {
		
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
				new AdminLoginPage();
				frame.dispose();
				
			}
			
		});
		
		un = username;
		pwd = password;
		
		frame.add(addLabel);
		addLabel.setBounds(570,70,140,30);
		
		frame.add(bookName);
		bookName.setBounds(530,100,200,30);
		
		frame.add(bookAuthor);
		bookAuthor.setBounds(530,140,200,30);
		
		frame.add(bookGenre);
		bookGenre.setBounds(530,180,200,30);
		
		frame.add(addBook);
		addBook.setBounds(580,220,90,30);
		addBook.addActionListener(this);
		
		
		frame.add(pane);
		pane.setBounds(-100,20,700,600);
		
		pane.add(bookList);
		bookList.setBounds(40, 40, 1000, 500);
		
		try {
			bookList = new JTable(getBookData()){
				
			    public boolean isCellEditable(int row, int column) {
			        return false; 
			    }
			};;
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
		
		bookList.addMouseListener(new MouseAdapter() { 
			  public void mouseClicked(MouseEvent e) { 
				  int row = bookList.rowAtPoint(e.getPoint()); 
				  int col = bookList.columnAtPoint(e.getPoint()); 
				  if (row >= 0 && col >= 0) {
					  Object name = bookList.getValueAt(row, 1);
					  Object author = bookList.getValueAt(row,2);
					  Object genre = bookList.getValueAt(row,3);
		  
					  bookName.setText(name.toString()); 
					  bookAuthor.setText(author.toString());
					  bookGenre.setText(genre.toString());
					  
				  } 
			  } 
		});
		
		pane.add(new JScrollPane(bookList));
		bookList.getColumnModel().getColumn(0).setPreferredWidth(1);
		bookList.getColumnModel().getColumn(1).setPreferredWidth(120);
		bookList.getColumnModel().getColumn(3).setPreferredWidth(40);
		bookList.getColumnModel().getColumn(4).setPreferredWidth(1);
		bookList.getColumnModel().getColumn(5).setPreferredWidth(40);
		
		frame.setLayout(null);
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public DefaultTableModel getBookData() throws Exception {
		
		Connection connection = DatabaseConnector.getConnection();
		
		PreparedStatement query = connection.prepareStatement("SELECT * from books;");
		ResultSet books = query.executeQuery();
        
        
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Author", "Genre","Stock", "Status"}, 0);
    
		while(books.next()) {
			
			model.addRow(new Object[] {books.getInt(1), books.getString(2), books.getString(3), 
					books.getString(4), books.getString(5), books.getString(6)});
		}
		
		books.close();
        DatabaseConnector.closeConnection();
        
		return model;
		
	}
	
	public void insertBook() throws Exception {
		
		Connection connection = DatabaseConnector.getConnection();
        
        PreparedStatement id = connection.prepareStatement("SELECT Book_ID FROM books ORDER BY Book_ID DESC LIMIT 1;");
        ResultSet rsID = id.executeQuery();
        
        PreparedStatement books = connection.prepareStatement("SELECT * from books;");
        ResultSet rsBooks = books.executeQuery();
        
        int curruid = 0;
        
        while(rsID.next()) {
        	curruid = rsID.getInt("Book_ID");
        }
        
        int bid = curruid + 1;
        String n = bookName.getText();
        String auth = bookAuthor.getText();
        String gen = bookGenre.getText();
       
        while(rsBooks.next()) {
        	if(rsBooks.getString(2).equals(n) && rsBooks.getString(3).equals(auth) && rsBooks.getString(4).equals(gen)) {
        		
        		int boid = rsBooks.getInt("Book_ID");
        		
        		PreparedStatement updateStockQuery = connection.prepareStatement("UPDATE books SET stock = stock +1 WHERE book_id = ?");
	            updateStockQuery.setInt(1, boid);
	            int updatedRows = updateStockQuery.executeUpdate();
	            
	            updateStockQuery.close();
	            
	            return;
        		
        	}
        	
        }
        
        String sql = "insert into books (book_id, name, author, genre, stock, status) values (?,?,?,?,?,?)";
        try (PreparedStatement insert = connection.prepareStatement(sql)) {
        	
            insert.setInt(1, bid);
            insert.setString(2, n);
            insert.setString(3, auth);
            insert.setString(4,gen);
            insert.setInt(5, 1);
            insert.setString(6, "available");
            
            int booksInserted = insert.executeUpdate();
        }
        
        
        
        rsID.close();
        rsBooks.close();
        id.close();
        books.close();
        DatabaseConnector.closeConnection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addBook) {
			try {
				if(bookName.getText().equals("Book name") || bookName.getText().equals("Author") || bookName.getText().equals("Genre")) {
					JOptionPane.showMessageDialog(frame, "Fill in book details", 
	        				"Invalid", JOptionPane.ERROR_MESSAGE);
				}
				else if(bookName.getText().isEmpty() || bookName.getText().isEmpty() || bookName.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame, "Fill in book details", 
	        				"Invalid", JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					insertBook();
					JOptionPane.showMessageDialog(frame, "Book updated succesfully!", "Book list updation", JOptionPane.PLAIN_MESSAGE);
			
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
