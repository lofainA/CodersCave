package Main;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main {

	public static void main(String[] args) {
		
		DatabaseConnector dbc =  new DatabaseConnector();
		LandingPage lp = new LandingPage();
	}

}
