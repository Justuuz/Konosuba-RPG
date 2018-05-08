package io.osu.konosuba.util;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;

public class DatabaseConnectionHandler {

	static long userid;
	
	
	
	/**
	 * Establishes connection to database
	 * @requires Database is online and exists
	 * @ensures Connection to database
	 */
	 Statement SQLiteJDBC() throws Exception{
		Connection c = null;
		Statement statement = null;
		
			Class.forName("org.postgresql.Driver");
			c= DriverManager
					.getConnection("jdbc:sqlite:" + System.getProperty("user.home") + "/git/KonosubaBot/Data/data.db");
			c.setAutoCommit(false);
			System.out.println("Connection success");
			statement  =  c.createStatement();
			 
			
			return statement;
	}
	
}
