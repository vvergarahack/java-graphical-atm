package view;

import java.awt.CardLayout;
import java.awt.Container;

import data.Database;

public class ViewManager {
	
	private Container views;
	private Database db;
	
	/**
	 * Constructs an instance (or object) of the ViewManager class.
	 * 
	 * @param layout
	 * @param container
	 */
	
	public ViewManager(Container views) {
		this.views = views;
		this.db = new Database();
	}
	
	/**
	 * Routes a login request from the LoginView to the Database. This will later be modified to
	 * return a BankAccount object that your program can reference. Stay tuned...
	 * 
	 * @param accountNumber
	 * @param pin
	 */
	
	public void login(String accountNumber, char[] pin) {
		boolean success = db.login(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
		
		if (success) {
			switchTo(ATM.HOME_VIEW);
		}
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
}