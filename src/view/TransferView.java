package view;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.BankAccount;

@SuppressWarnings("serial")
public class TransferView extends JPanel implements ActionListener {
		
	
	private BankAccount act;
	private ViewManager manager;
	private JTextField accountField;// manages interactions between the views, model, and database
	private JTextField amtField;		// textfield where the user enters his or her account number
	private JButton transferButton;
	private JButton mainMenuButton;// label for potential error messages
	private JLabel errorMessageLabel;		// label for potential error messages

	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public TransferView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	public void setBankAccount(BankAccount setAccount) {
		this.act = setAccount;
	}
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Updates the error message label.
	 * 
	 * @param errorMessage
	 */
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the LoginView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initAccountField();
		initAmtField();
		initTransferButton();
		initMainMenuButton();
		initErrorMessageLabel();
	}
	
	/*
	 * Initializes the components needed for the account number textfield.
	 */
	
	private void initAccountField() {
		JLabel label = new JLabel("Account Number: ", SwingConstants.RIGHT);
		label.setBounds(30, 100, 155, 35);
		label.setLabelFor(accountField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		accountField = new JTextField(20);
		accountField.setBounds(225, 100, 150, 35);
		
		this.add(label);
		this.add(accountField);
	}
	
	private void initAmtField() {
		JLabel label = new JLabel("Transfer Amount: ", SwingConstants.RIGHT);
		label.setBounds(30, 150, 155, 35);
		label.setLabelFor(amtField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		amtField = new JTextField(20);
		amtField.setBounds(225, 150, 150, 35);
		
		this.add(label);
		this.add(amtField);
	}
	
	/*
	 * Initializes the components needed for the PIN textfield.
	 */
	
	
	/*
	 * Initializes the components needed for the login button.
	 */
	
	private void initTransferButton() {	
		transferButton = new JButton("Transfer");
		transferButton.setBounds(225, 200, 150, 35);
		transferButton.addActionListener(this);
		
		this.add(transferButton);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(0, 240, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	/*
	 * Initializes the components needed for the create button.
	 */
	
	private void initMainMenuButton() {
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setBounds(126, 300, 248, 35);
		mainMenuButton.addActionListener(this);
		
		//this.add(label);
		this.add(mainMenuButton);		
	}
	
	/*
	 * Initializes the components needed for the power button.
	 */

	
	/*
	 * LoginView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	public boolean checkUserInput(String input, int type) {
		// 1 = integer, 2 = double, 3 = long
		if(type == 1) {
			int integerInput;
			try{
				integerInput = Integer.parseInt(input);
		    }
		    catch(NumberFormatException e){
		    	System.out.println("Response must be numerical. Try again.\n");
		    	return false;
		    }
			return true;
		}
		
		else if(type == 2){
			double doubleInput;
			try {
				doubleInput = Double.parseDouble(input);
			}
			catch (NumberFormatException e){
				System.out.println("Response must be numerical. Try again.\n");
				return false;
			}
			return true;
		}
		else {
			Long longInput;
			try {
				longInput = Long.parseLong(input);
			}
			catch (NumberFormatException e) {
				System.out.println("Response must be numerical. Try again.\n");
				return false;
			}
			return true;
		}
	}
	
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The LoginView class is not serializable.");
	}

	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks performed in the LoginView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(transferButton)) {
			int test = -1;
			if(accountField.getText() == "" || !checkUserInput(accountField.getText(), 3) || Long.valueOf(accountField.getText()) == act.getActNumber()) {
				test = 2;
				updateErrorMessage("Invalid account number.");

			}
			else if(amtField.getText() == "" || !checkUserInput(amtField.getText(), 2)) {
				test = 0;
				updateErrorMessage("Invalid amount.");

			}
			else{
				BankAccount transferAccount = manager.db.getAct(Long.valueOf(accountField.getText()));
				test = act.transfer(transferAccount, Double.valueOf(amtField.getText()));
				switch (test) {
					case 0:
						updateErrorMessage("Invalid amount.");
						System.out.println("Failure.");
						break;
					case 1:
					updateErrorMessage("Insufficient funds.");
						break;
					case 2:
						updateErrorMessage("Invalid account number.");
						break;
					case 3:
						manager.db.updateAct(act);
						manager.db.updateAct(transferAccount);
						amtField.setText("");
						accountField.setText("");
						updateErrorMessage("Amount successfully transferred.");
						break;
					default:
						System.out.println("Error");
						break;
				}
			}
		}
		else if(source.equals(mainMenuButton)) {
			updateErrorMessage("");
			amtField.setText("");
			manager.sendBankAct(act, "Home");
			manager.switchTo(ATM.HOME_VIEW);
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}
