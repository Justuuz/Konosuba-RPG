package io.osu.konosuba.test;

import io.osu.konosuba.Konosuba;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Data {
	/*
	 * TABLE 'test'
	 * +--+-------+
	 * |id|balance|
	 * +--+-------+
	 *
	 */

	private boolean first = true;

	// ====================
	private long id;
	private int  balance;
	// ====================

	Data(long id) throws SQLException {
		Statement statement = Konosuba.CONNECTION1.createStatement();
		if (first) {
			statement.execute("CREATE TABLE IF NOT EXISTS 'test' (id INT PRIMARY KEY NOT NULL, balance INT NOT NULL DEFAULT 0);");
			first = false;
		}

		ResultSet result = statement.executeQuery("SELECT * FROM 'test' WHERE id="+id+";");
		boolean hasResult = result.next();

		this.id = id;
		this.balance = hasResult ? result.getInt("balance") : 0;

		statement.close();
	}

	private void update(String key, Object value) {
		try {
			Statement statement = Konosuba.CONNECTION1.createStatement();

			statement.addBatch("INSERT OR IGNORE INTO 'test' (id,"+key+") VALUES ("+id+","+value+");");
			statement.addBatch("UPDATE 'test' SET "+key+"="+value+" WHERE id="+id+";");

			int[] result = statement.executeBatch();
			for (int i : result) System.out.println(i);

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		update("balance", balance);
		this.balance = balance;
	}
}
