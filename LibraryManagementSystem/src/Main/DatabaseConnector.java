package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

	static String url = "jdbc:mysql://localhost:3306/library";
	static String username = "root";
	static String pwd = "";
	
	private static Connection connection;
	
	
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, pwd);
        }
        return connection;
    }
    
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    
	
}
