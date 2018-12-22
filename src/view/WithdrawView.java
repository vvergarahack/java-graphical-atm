package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class WithdrawView extends JPanel implements ActionListener {
		
	private ViewManager manager;			// manages interactions between the views, model, and database
	private JTextField amtField;		// textfield where the user enters his or her account number
	private JButton withdrawButton;
	private JButton mainMenuButton;// label for potential error messages
	private JLabel errorMessageLabel;		// label for potential error messages

	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public WithdrawView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
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
		
		initAmtField();
		initWithdrawButton();
		initMainMenuButton();
	}
	
	/*
	 * Initializes the components needed for the account number textfield.
	 */
	
	private void initAmtField() {
		JLabel label = new JLabel("Withdrawal Amount:", SwingConstants.RIGHT);
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
	
	private void initWithdrawButton() {	
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(225, 200, 150, 35);
		withdrawButton.addActionListener(this);
		
		this.add(withdrawButton);
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
		
		if (source.equals(withdrawButton)) {
			if(manager.account.withdraw(Double.valueOf(amtField.getText())) == 3) {
				JOptionPane.showMessageDialog(null, "Withdrawl Successful.");
				System.out.println("Success.");
			}
			else if(manager.account.withdraw(Double.valueOf(amtField.getText())) == 0) {
				JOptionPane.showMessageDialog(null, "Invalid Input.");
				System.out.println("Failure.");
			}
			else if(manager.account.withdraw(Double.valueOf(amtField.getText())) == 1) {
				JOptionPane.showMessageDialog(null, "Insufficient Funds.");
				System.out.println("Failure.");
			}
			else {
				System.out.println("Error");
			}
		}
		else if(source.equals(mainMenuButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}
