package io.osu.konosuba.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class UserHandler extends DatabaseConnectionHandler {
	
	static ResultSet result;
	static long userid;
	
	public UserHandler(long userid) {
		this.userid = userid;
	}
	
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
	
	void getter() throws Exception {
		Statement statement = SQLiteJDBC();
		result = statement.executeQuery("SELECT * FROM clientdata WHERE id=" +userid + ";");
		statement.close();
	}
	
	void setter(String type, String entity) throws Exception{
		Statement statement = SQLiteJDBC();
		statement.executeUpdate("UPDATE client" +
										"SET" + type + "=" + entity + "WHERE id=" + userid + ";");
	}
	
	void inserter(String type, String entity) throws Exception{
		Statement statement = SQLiteJDBC();
		statement.executeUpdate("INSERT INTO clientdata (" + type + ") " +
										"VALUES(" + entity + ");");
	}
	
	
	
	
	public String getHelmet() throws Exception {
		getter();
		return result.getString("helmet");
		
	}
	
	public void setHelmet(String helmet) throws Exception {
		setter("helmet", helmet);
		
	}
	
	
		
	
}
