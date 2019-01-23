package data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSetMetaData;
import model.BankAccount;

public class Database {
	
	/*
	 * Field names for database table: accounts.
	 */
	
	public static final String ACT_NUMBER = "act_number";
	public static final String PIN = "pin";
	public static final String BALANCE = "balance";
	public static final String LAST_NAME = "last_name";
	public static final String FIRST_NAME = "first_name";
	public static final String DOB = "dob";
	public static final String PHONE = "phone";
	public static final String STREET_ADDRESS = "street_address";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String ZIP = "zip";
	public static final String STATUS = "status";
	
	private Connection conn;			// a connection to the database
	private Statement stmt;				// the statement used to build inserts, updates and selects
	private ResultSet rs;				// result set used for selects
	private DatabaseMetaData meta;		// metadata about the database
	
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
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Retrieves an existing account by account number and PIN.
	 * 
	 * @param actNumber
	 * @param pin
	 * @return
	 */
	
	public BankAccount getAct(long actNumber, int pin) {
		try {
			stmt = conn.createStatement();
			
			PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM acts WHERE act_number = ? AND pin = ?");
			selectStmt.setLong(1, actNumber);
			selectStmt.setInt(2, pin);
			
			rs = selectStmt.executeQuery();
			if (rs.next()) {
				return new BankAccount(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Retrieves an existing account by account number.
	 * 
	 * @param actNumber
	 * @return
	 */
	
	public BankAccount getAct(long actNumber) {
		try {
			stmt = conn.createStatement();
			
			PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM acts WHERE act_number = ?");
			selectStmt.setLong(1, actNumber);
			
			rs = selectStmt.executeQuery();
			if (rs.next()) {
				return new BankAccount(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Inserts an account into the database.
	 * 
	 * @param act
	 * @return true if the insert is successful; false otherwise.
	 */
	
	public boolean insertAct(BankAccount act) {
		try {
			stmt = conn.createStatement();
			
			PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO acts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");		
			insertStmt.setLong(1, act.getActNumber());
			insertStmt.setInt(2, act.getUser().getPin());
			
			insertStmt.setDouble(3, act.getBalance());
			
			insertStmt.setString(4, act.getUser().getLastName());
			insertStmt.setString(5, act.getUser().getFirstName());
			
			insertStmt.setInt(6, act.getUser().getDob());
			insertStmt.setLong(7, act.getUser().getPhone());
			
			insertStmt.setString(8, act.getUser().getStreetAddress());
			insertStmt.setString(9, act.getUser().getCity());
			insertStmt.setString(10, act.getUser().getState());
			insertStmt.setString(11, act.getUser().getZip());
			
			insertStmt.setString(12, String.valueOf(act.getStatus()));
			
			insertStmt.executeUpdate();
			insertStmt.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Performs a soft delete of an account by setting the status to closed.
	 * 
	 * @param act
	 * @return true if the transaction is successful; false otherwise.
	 */
	
	public boolean closeAct(BankAccount act) {
		try {
			stmt = conn.createStatement();
			
			PreparedStatement insertStmt = conn.prepareStatement("UPDATE acts SET status = ? WHERE act_number = ?");		
			insertStmt.setString(1, "N");
			insertStmt.setLong(2, act.getActNumber());
			
			insertStmt.executeUpdate();
			insertStmt.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Updates all potentially edited fields (i.e., PIN, account balance, phone number,
	 * street address, city, state, and zip code).
	 * 
	 * @param act
	 * @return true if the transaction is successful; false otherwise.
	 */
	
	public boolean updateAct(BankAccount act) {
		try {
			stmt = conn.createStatement();
			
			// all editable fields are included in this update statement
			
			PreparedStatement insertStmt = conn.prepareStatement(
				"UPDATE acts SET " +
					"pin = ?, " +
					"balance = ?, " +
					"phone = ?, " +
					"street_address = ?, " +
					"city = ?, " +
					"state = ?, " +
					"zip = ? " +
				"WHERE act_number = ?"
			);		
			insertStmt.setInt(1, act.getUser().getPin());
			insertStmt.setDouble(2, act.getBalance());
			insertStmt.setLong(3, act.getUser().getPhone());
			insertStmt.setString(4, act.getUser().getStreetAddress());
			insertStmt.setString(5, act.getUser().getCity());
			insertStmt.setString(6, act.getUser().getState());
			insertStmt.setString(7, act.getUser().getZip());
			insertStmt.setLong(8, act.getActNumber());
			
			insertStmt.executeUpdate();
			insertStmt.close();
			
			return true;
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
	public long highestAcctNumber() {
		long actNum = 0;
		try {
			stmt = conn.createStatement();


			PreparedStatement selectStmt = conn.prepareStatement("SELECT MAX(act_number) FROM acts");
//			selectStmt.setLong(1, accountNumber);
			
			rs = selectStmt.executeQuery();
			System.out.println(rs);
			//return rs;
			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actNum;
	}
	
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
		createActsTable();
		insertDefaultAct();
	}
	
	
	private void throwOut() throws SQLException {
		try {
			stmt = conn.createStatement();


			PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM acts WHERE 1 = 1");
			
			deleteStmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * Creates the initial accounts table. This will only be done once during initial setup.
	 * 
	 * @throws SQLException
	 */
	
	private void createActsTable() throws SQLException {
		meta = conn.getMetaData();
		rs = meta.getTables(null, "USER1", "ACTS", null);
		
		if (!rs.next()) {
			stmt = conn.createStatement();
			
			stmt.execute(
				"CREATE TABLE acts (" +
					"act_number BIGINT PRIMARY KEY, " +
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
	
	private void insertDefaultAct() throws SQLException {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT COUNT(*) FROM acts");
		if (rs.next() && rs.getInt(1) == 0) {
			PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO acts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
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
