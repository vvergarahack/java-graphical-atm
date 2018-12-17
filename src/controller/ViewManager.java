package controller;

import java.awt.CardLayout;
import java.awt.Container;

import data.Database;
import model.BankAccount;
import view.ATM;
import view.LoginView;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	private BankAccount destination;		// an account to which the user can transfer funds
	
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
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 */
	
	public void login(String accountNumber, char[] pin) {
		account = db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
		
		if (account == null) {
			LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
			lv.updateErrorMessage("Invalid account number and/or PIN.");
		} else {
			switchTo(ATM.HOME_VIEW);
			
			LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
			lv.updateErrorMessage("");
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
