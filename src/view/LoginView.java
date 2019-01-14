package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class LoginView extends JPanel implements ActionListener {
		
	private ViewManager manager;			// manages interactions between the views, model, and database
	private JButton loginButton;			// button that redirects users to the HomeView (if credentials match)
	private JButton createButton;			// button that directs users to the CreateView
	private JButton powerButton;			// button that powers off the ATM
	private JTextField accountField;		// textfield where the user enters his or her account number
	private JPasswordField pinField;		// textfield where the user enters his or her PIN
	private JLabel errorMessageLabel;		// label for potential error messages
	
	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public LoginView(ViewManager manager) {
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
		
		initAccountField();
		initPinField();
		initLoginButton();
		initCreateButton();
		initErrorMessageLabel();
		initPowerButton();
	}
	
	/*
	 * Initializes the components needed for the account number textfield.
	 */
	
	private void initAccountField() {
		JLabel label = new JLabel("Account No.", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(accountField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		accountField = new JTextField(20);
		accountField.setBounds(205, 100, 200, 35);
		accountField.addActionListener(this);
		
		this.add(label);
		this.add(accountField);
	}
	
	/*
	 * Initializes the components needed for the PIN textfield.
	 */
	
	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(100, 140, 95, 35);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setBounds(205, 140, 200, 35);
		pinField.addActionListener(this);
		
		this.add(label);
		this.add(pinField);
	}
	
	/*
	 * Initializes the components needed for the login button.
	 */
	
	private void initLoginButton() {	
		loginButton = new JButton("Login");
		loginButton.setBounds(205, 180, 200, 35);
		loginButton.addActionListener(this);
		
		this.add(loginButton);
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
	
	private void initCreateButton() {
		JLabel label = new JLabel("Need an account? Open one today!", SwingConstants.CENTER);
		label.setBounds(0, 320, 500, 35);
		label.setLabelFor(createButton);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		createButton = new JButton("Open an Account");
		createButton.setBounds(126, 360, 248, 35);
		createButton.addActionListener(this);
		
		this.add(label);
		this.add(createButton);		
	}
	
	/*
	 * Initializes the components needed for the power button.
	 */
	
	private void initPowerButton() {
		powerButton = new JButton();
		powerButton.setBounds(5, 5, 50, 50);
		powerButton.addActionListener(this);
		
		try {
			Image image = ImageIO.read(new File("images/power-off.png"));
			powerButton.setIcon(new ImageIcon(image));
		} catch (Exception e) {
			powerButton.setText("OFF");
		}
		
		this.add(powerButton);
	}
	
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
		
		if (source.equals(loginButton)) {
			boolean ok = true;
			
			if(!checkUserInput(accountField.getText(), 2) || !checkUserInput(pinField.getText(), 1)) {
				ok = false;
				updateErrorMessage("Invalid entry.");
			}
			
			if(ok) {
				updateErrorMessage("");
				String accountNum = accountField.getText();
				char[] pin = pinField.getPassword();
				accountField.setText("");
				pinField.setText("");
				manager.login(accountNum, pin);
			}
		} else if (source.equals(createButton)) {
			updateErrorMessage("");
			manager.switchTo(ATM.CREATE_VIEW);
		} else if (source.equals(powerButton)) {
			updateErrorMessage("");
			manager.shutdown();
		} else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}
