package data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private DatabaseMetaData meta;
	
	/**
	 * Constructs an instance (or object) of the Database class.
	 */
	
	public Database() {
		
		try {
			this.connect();
			this.setup();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs into an existing account.
	 * 
	 * @param accountNumber
	 * @param pin
	 * @return
	 */
	
	public boolean login(long accountNumber, int pin) {
		try {
			stmt = conn.createStatement();
			
			PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND pin = ?");
			selectStmt.setLong(1, accountNumber);
			selectStmt.setInt(2, pin);
			
			rs = selectStmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Shuts down the database, releasing all allocated resources.
	 * 
	 * @throws SQLException
	 */
	
	public void shutdown() throws SQLException {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Establishes a connection to the database.
	 * 
	 * @throws SQLException
	 */
	
	private void connect() throws SQLException {
		Properties props = new Properties();
        props.put("user", "user1");
        props.put("password", "user1");

        conn = DriverManager.getConnection("jdbc:derby:atm;create=true", props);
	}
	
	/*
	 * Performs initial database setup.
	 * 
	 * @throws SQLException
	 */
	
	private void setup() throws SQLException {
		createAccountsTable();
		insertDefaultAccount();
	}
	
	/*
	 * Creates the initial accounts table. This will only be done once during initial setup.
	 * 
	 * @throws SQLException
	 */
	
	private void createAccountsTable() throws SQLException {
		meta = conn.getMetaData();
		rs = meta.getTables(null, "USER1", "ACCOUNTS", null);
		
		if (!rs.next()) {
			stmt = conn.createStatement();
			
			stmt.execute(
				"CREATE TABLE accounts (" +
					"account_number BIGINT PRIMARY KEY, " +
					"pin INT, " +
					"balance FLOAT, " +
					"last_name VARCHAR(20), " +
					"first_name VARCHAR(15), " +
					"dob INT, " +
					"phone BIGINT, " +
					"street_address VARCHAR(30), " +
					"city VARCHAR(30), " +
					"state VARCHAR(2), " +
					"zip VARCHAR(5), " +
					"status CHAR(1)" +
				")"
			);
		}
	}
	
	/*
	 * Inserts a default account into the database. This will only be done once during initial setup.
	 * 
	 * @throws SQLException
	 */
	
	private void insertDefaultAccount() throws SQLException {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT COUNT(*) FROM accounts");
		if (rs.next() && rs.getInt(1) == 0) {
			PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO accounts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			insertStmt.setLong(1, 100000001L);
			insertStmt.setInt(2, 1234);
			insertStmt.setDouble(3, 0.00);
			insertStmt.setString(4, "Wilson");
			insertStmt.setString(5, "Ryan");
			insertStmt.setInt(6, 19700707);
			insertStmt.setLong(7, 55555555555L);
			insertStmt.setString(8, "1776 Raritan Road");
			insertStmt.setString(9, "Scotch Plains");
			insertStmt.setString(10, "NJ");
			insertStmt.setString(11, "07065");
			insertStmt.setString(12, "Y");
			
			insertStmt.executeUpdate();
			insertStmt.close();
		}
	}
}