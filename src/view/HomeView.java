package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ViewManager;
import model.BankAccount;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private BankAccount account;
	private JLabel printBalance;
	private JLabel printName;
	private JLabel printAcct;
	private JButton logOffButton;
	private JButton depositButton;
	private JButton withdrawButton;
	private JButton transferButton;
	private JButton viewAcctButton;
	private JButton closeAcctButton;
	//private JButton viewBalanceButton;
	//private JButton viewAccountButton;
	//private JButton editAccountButton;
	//private JButton closeAccountButton;
	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	
	public void setBankAccount(BankAccount setAccount) {
		this.account = setAccount;
	}
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the HomeView.
		
		this.add(new javax.swing.JLabel("HomeView", javax.swing.SwingConstants.CENTER));
		
		this.setLayout(null);
		initLogOffButton();
		initDepositButton();
		initWithdrawButton();
		initTransferButton();
		initViewAcctButton();
		initCloseAcctButton();

		// TODO
		//
		// this is where you should build the HomeView (i.e., all the components that
		// allow the user to interact with the ATM - deposit, withdraw, transfer, etc.).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	public void initScreen() {
		System.out.println("Balance on initscreen: " + Double.toString(account.getBalance()));
		printName = new JLabel("Welcome, " + account.getUser().getFirstName() + " " + account.getUser().getLastName());
		printName.setBounds(10, 10, 500, 25);
		printName.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		
		this.add(printName);
		
		printAcct = new JLabel("Account Number: " + Long.toString(account.getActNumber()));
		printAcct.setBounds(10,40,500,25);
		printAcct.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(printAcct);
		
		printBalance = new JLabel("Current Balance: " + account.getCorrectBalance());
		printBalance.setBounds(10, 70, 500, 25);
		printBalance.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(printBalance);
	}
	
	private void initLogOffButton() {	
		logOffButton = new JButton("Log Out");
		logOffButton.setBounds(150, 130, 200, 35);
		logOffButton.addActionListener(this);
		
		this.add(logOffButton);
	}
	
	private void initDepositButton() {	
		depositButton = new JButton("Deposit");
		depositButton.setBounds(150, 180, 200, 35);
		depositButton.addActionListener(this);
		
		this.add(depositButton);
	}
	
	private void initWithdrawButton() {
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(150, 230, 200, 35);
		withdrawButton.addActionListener(this);
		
		this.add(withdrawButton);
	}
	
	private void initTransferButton() {
		transferButton = new JButton("Transfer");
		transferButton.setBounds(150, 280, 200, 35);
		transferButton.addActionListener(this);
		
		this.add(transferButton);
	}
	
	
	private void initViewAcctButton() {
		viewAcctButton = new JButton("Account Info");
		viewAcctButton.setBounds(150, 330, 200, 35);
		viewAcctButton.addActionListener(this);
		
		this.add(viewAcctButton);
	}
	
	private void initCloseAcctButton() {
		closeAcctButton = new JButton("Close Account");
		closeAcctButton.setBounds(150,380,200,35);
		closeAcctButton.addActionListener(this);
		
		this.add(closeAcctButton);
	}

	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source.equals(logOffButton)) {
			manager.updateAct(account);
			manager.getAct().setUser(null);
			manager.setAct(null);
			this.remove(printBalance);
			this.remove(printName);
			this.remove(printAcct);
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else if(source.equals(depositButton)) {
			this.remove(printBalance);
			this.remove(printName);
			this.remove(printAcct);
			manager.sendBankAct(account, "Deposit");
			manager.switchTo(ATM.DEPOSIT_VIEW);
		}
		else if(source.equals(withdrawButton)) {
			this.remove(printBalance);
			this.remove(printName);
			this.remove(printAcct);
			manager.sendBankAct(account, "Withdraw");
			manager.switchTo(ATM.WITHDRAW_VIEW);
		}
		else if(source.equals(transferButton)) {
			this.remove(printBalance);
			this.remove(printName);
			this.remove(printAcct);
			manager.sendBankAct(account, "Transfer");
			manager.switchTo(ATM.TRANSFER_VIEW);
		}
		else if(source.equals(viewAcctButton)) {
			this.remove(printBalance);
			this.remove(printName);
			this.remove(printAcct);
			manager.sendBankAct(account, "ViewInfo");
			manager.switchTo(ATM.INFO_VIEW);
		}
		else if(source.equals(closeAcctButton)) {
			int choice = JOptionPane.showConfirmDialog(
					null,
					"Are you sure?",
					"Close Account",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);
				if (choice == 0) {
					if(manager.closeAct(account)) {
						this.remove(printBalance);
						this.remove(printName);
						this.remove(printAcct);
						
						manager.switchTo(ATM.LOGIN_VIEW);
						
						manager.getAct().setUser(null);
						manager.setAct(null);
						}
				}
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
}
