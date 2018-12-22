package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField birthDayField;
	private JTextField birthMonthField;
	private JTextField birthYearField;
	private JTextField phoneNumberField;
	private JTextField streetAddressField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField postalCodeField;
	private JTextField pinField;
	private JButton createAccountButton;	
	private JButton cancelButton;			
	private JLabel errorMessageLabel;
	private BankAccount newAccount;
	private User newUser;
	/*private BankAccount newAccount;
	private User newUser;*/
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initFirstNameField();
		initLastNameField();
		initBirthDayField();
		initBirthMonthField();
		initBirthYearField();
		initPhoneNumberField();
		initStreetAddressField();
		initCityField();
		initStateField();
		initPostalCodeField();
		initPinFieldField();
		initCreateAccountButton();
		initCancelButton();
		
		this.add(new javax.swing.JLabel("CreateView", javax.swing.SwingConstants.CENTER));
		
	}
	
	private void initFirstNameField() {
		JLabel label = new JLabel("First Name:", SwingConstants.RIGHT);
		label.setBounds(50, 20, 130, 25);
		label.setLabelFor(firstNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		firstNameField = new JTextField(20);
		firstNameField.setBounds(205, 20, 200, 25);
		firstNameField.addActionListener(this);
		
		this.add(label);
		this.add(firstNameField);
	}
	
	private void initLastNameField() {
		JLabel label = new JLabel("Last Name:", SwingConstants.RIGHT);
		label.setBounds(50, 50, 130, 25);
		label.setLabelFor(lastNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lastNameField = new JTextField(20);
		lastNameField.setBounds(205, 50, 200, 25);
		lastNameField.addActionListener(this);
		
		this.add(label);
		this.add(lastNameField);
	}
	
	private void initBirthDayField() {
		JLabel label = new JLabel("Day of Birth:", SwingConstants.RIGHT);
		label.setBounds(50, 80, 130, 25);
		label.setLabelFor(birthDayField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		birthDayField = new JTextField(20);
		birthDayField.setBounds(205, 80, 200, 25);
		birthDayField.addActionListener(this);
		
		this.add(label);
		this.add(birthDayField);
	}
	
	private void initBirthMonthField() {
		JLabel label = new JLabel("Month of Birth:", SwingConstants.RIGHT);
		label.setBounds(50, 110, 130, 25);
		label.setLabelFor(birthMonthField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		birthMonthField = new JTextField(20);
		birthMonthField.setBounds(205, 110, 200, 25);
		birthMonthField.addActionListener(this);
		
		this.add(label);
		this.add(birthMonthField);
	}
	
	private void initBirthYearField() {
		JLabel label = new JLabel("Year of Birth:", SwingConstants.RIGHT);
		label.setBounds(50, 140, 130, 25);
		label.setLabelFor(birthYearField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		birthYearField = new JTextField(20);
		birthYearField.setBounds(205, 140, 200, 25);
		birthYearField.addActionListener(this);
		
		this.add(label);
		this.add(birthYearField);
	}
	
	private void initPhoneNumberField() {
		JLabel label = new JLabel("Phone Number:", SwingConstants.RIGHT);
		label.setBounds(50, 170, 130, 25);
		label.setLabelFor(phoneNumberField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phoneNumberField = new JTextField(20);
		phoneNumberField.setBounds(205, 170, 200, 25);
		phoneNumberField.addActionListener(this);
		
		this.add(label);
		this.add(phoneNumberField);
	}
	
	private void initStreetAddressField() {
		JLabel label = new JLabel("Street Address:", SwingConstants.RIGHT);
		label.setBounds(50, 200, 130, 25);
		label.setLabelFor(streetAddressField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		streetAddressField = new JTextField(20);
		streetAddressField.setBounds(205, 200, 200, 25);
		streetAddressField.addActionListener(this);
		
		this.add(label);
		this.add(streetAddressField);
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City:", SwingConstants.RIGHT);
		label.setBounds(50, 230, 130, 25);
		label.setLabelFor(cityField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		cityField = new JTextField(20);
		cityField.setBounds(205, 230, 200, 25);
		cityField.addActionListener(this);
		
		this.add(label);
		this.add(cityField);
	}
	
	private void initStateField() {
		JLabel label = new JLabel("State:", SwingConstants.RIGHT);
		label.setBounds(50, 260, 130, 25);
		label.setLabelFor(stateField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		stateField = new JTextField(20);
		stateField.setBounds(205, 260, 200, 25);
		stateField.addActionListener(this);
		
		this.add(label);
		this.add(stateField);
	}
	
	private void initPostalCodeField() {
		JLabel label = new JLabel("Postal Code:", SwingConstants.RIGHT);
		label.setBounds(50, 290, 130, 25);
		label.setLabelFor(postalCodeField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		postalCodeField = new JTextField(20);
		postalCodeField.setBounds(205, 290, 200, 25);
		postalCodeField.addActionListener(this);
		
		this.add(label);
		this.add(postalCodeField);
	}
	
	private void initPinFieldField() {
		JLabel label = new JLabel("PIN:", SwingConstants.RIGHT);
		label.setBounds(50, 320, 130, 25);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JTextField(20);
		pinField.setBounds(205, 320, 200, 25);
		pinField.addActionListener(this);
		
		this.add(label);
		this.add(pinField);
	}
	
	private void initCreateAccountButton() {
		createAccountButton = new JButton("Create Account");
		createAccountButton.setBounds(150, 370, 200, 35);
		createAccountButton.addActionListener(this);

		this.add(createAccountButton);		
	}
	
	private void initCancelButton() {
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(180, 410, 140, 35);
		cancelButton.addActionListener(this);

		this.add(cancelButton);	
	}
	
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
				
		if(source.equals(createAccountButton)) {
			//int dob = 
			System.out.println(Long.toString(manager.db.highestAcctNumber()));
			newUser = new User(Integer.valueOf(pinField.getText()), 010101, Long.valueOf(pinField.getText()), firstNameField.getText(), lastNameField.getText(), streetAddressField.getText(), cityField.getText(), stateField.getText(), postalCodeField.getText());
			newAccount = new BankAccount('Y', manager.db.highestAcctNumber() + 1, 0.00, newUser);
			manager.db.insertAccount(newAccount);
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else if(source.equals(cancelButton)) {
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else {
			System.out.println("Error");
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
