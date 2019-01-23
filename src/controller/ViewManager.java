package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.Database;
import model.BankAccount;
import view.ATM;
import view.LoginView;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	public Database db;					// a reference to the database
	public BankAccount act;			// the user's bank account
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
	

	
	public long highestAcctNumber() {
		return db.highestAcctNumber();
	}
	
	public void setAct(BankAccount act) {
		this.act = act;
	}
	public void login(String actNumber, char[] pin) {
		try {
			act = db.getAct(Long.valueOf(actNumber), Integer.valueOf(new String(pin)));
			
			if (act == null || act.getStatus() == 'N') {
				LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
				lv.updateErrorMessage("Invalid account number and/or PIN.");
			} else {
				sendBankAct(act, "Home");
				switchTo(ATM.HOME_VIEW);
			}
		} catch (NumberFormatException e) {
			// ignore
		}
	}
	
	public boolean updateAct(BankAccount act) {
		
		return db.updateAct(act);
	}
	
	public boolean closeAct(BankAccount act) {
		
		return db.closeAct(act);
	}
	
	public boolean insertAct(BankAccount act) {
		
		return db.insertAct(act);
	}
	
	public BankAccount getAct(Long actNumber) {
		
		return db.getAct(actNumber);
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	/**
	 * Routes a shutdown request to the database before exiting the application. This
	 * allows the database to clean up any open resources it used.
	 */
	
	public BankAccount getAct() {
		return act;
	}
	
	public void shutdown() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendBankAct(BankAccount act, String view) {
		if (view.equals("Home")) {
			view.HomeView hv = ((view.HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setBankAccount(act);
			hv.initScreen();
		}
		else if (view.equals("Deposit")) {
			view.DepositView dv = ((view.DepositView) views.getComponents()[ATM.DEPOSIT_VIEW_INDEX]);
			dv.setBankact(act);
		}
		else if (view.equals("Withdraw")) {
			view.WithdrawView wv = ((view.WithdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX]);
			wv.setBankAccount(act);
		}
		else if (view.equals("Transfer")) {
			view.TransferView tv = ((view.TransferView) views.getComponents()[ATM.TRANSFER_VIEW_INDEX]);
			tv.setBankAccount(act);
		}
		else if (view.equals("ViewInfo")) {
			view.InfoView iv = ((view.InfoView) views.getComponents()[ATM.INFO_VIEW_INDEX]);
			iv.setBankAccount(act);
			iv.initInfoPortion();
		}
	}
	
}
