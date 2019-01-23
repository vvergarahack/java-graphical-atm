package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.BankAccount;

@SuppressWarnings("serial")
public class InfoView extends JPanel implements ActionListener {
		
	private ViewManager manager;			// manages interactions between the views, model, and database
	private JTextField accountNumberField;		// textfield where the user enters his or her account number
	private JPasswordField pinField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JComboBox<?> dayPicker;
	private JComboBox<?> monthPicker;
	private JComboBox<?> yearPicker;
	private JTextField phoneNumberField1;
	private JTextField phoneNumberField2;
	private JTextField phoneNumberField3;
	private JTextField streetAddressField;
	private JTextField cityField;
	private JComboBox<Object> stateField;
	private JTextField postalCodeField;
	private JButton editButton;
	private JButton saveButton;
	private JButton cancelButton;// label for potential error messages
	private JLabel errorMessageLabel;		// label for potential error messages
	private BankAccount account;
	
	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public InfoView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	
	public void setBankAccount(BankAccount setAccount) {
		this.account = setAccount;
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
		
		initEditButton();
		initCancelButton();
		initErrorMessageLabel();
	}
	
	public void initInfoPortion() {
		
		initAccountNumberField();
		initPinField();
		initFirstNameField();
		initLastNameField();
		initDOBField();
		initPhoneNumberField();
		initStreetAddressField();
		initCityField();
		initStateField();
		initPostalCodeField();

	}
	
	/*
	 * Initializes the components needed for the account number textfield.
	 */
	
	private void initAccountNumberField() {
		
		JLabel label = new JLabel("Account Number: ", SwingConstants.RIGHT);
		label.setBounds(50, 20, 120, 25);
		label.setLabelFor(accountNumberField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		accountNumberField = new JTextField(20);
		accountNumberField.setText(Long.toString(account.getActNumber()));
		accountNumberField.setBounds(205, 20, 200, 25);
		accountNumberField.setEditable(false);
		
		this.add(label);
		this.add(accountNumberField);
	}
	
	private void initPinField() {
		
		JLabel label = new JLabel("Pin Number: ", SwingConstants.RIGHT);
		label.setBounds(50,50,130,25);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setText(Integer.toString(account.getUser().getPin()));
		pinField.setBounds(205, 50, 200, 25);
		pinField.setEditable(false);
		
		this.add(label);
		this.add(pinField);	
	}
	
	private void initFirstNameField() {
		
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(50,80,130,25);
		label.setLabelFor(firstNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		firstNameField = new JTextField(20);
		firstNameField.setText(account.getUser().getFirstName());
		firstNameField.setBounds(205, 80, 200, 25);
		firstNameField.setEditable(false);

		this.add(label);
		this.add(firstNameField);	
	}
	
	private void initLastNameField() {
		
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(50,110,130,25);
		label.setLabelFor(lastNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lastNameField = new JTextField(20);
		lastNameField.setText(account.getUser().getLastName());
		lastNameField.setBounds(205, 110, 200, 25);
		lastNameField.setEditable(false);

		this.add(label);
		this.add(lastNameField);	
	}
	
	private void initDOBField() {
		
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(50, 140, 130, 25);
		//label.setLabelFor(dobField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] months = new String[12];
		String[] days = new String[31];
		String[] years = new String[119];
		
		for(int i = 0; i < months.length; i++) {
			months[i] = Integer.toString(i+1);
			if(months[i].length() != 2) {
				String original = months[i];
				months[i] = "0" + original;
			}
		}
		for(int i = 0; i < days.length; i++) {
			days[i] = Integer.toString(i + 1);
			if(days[i].length() != 2) {
				String original = days[i];
				days[i] = "0" + original;
			}
		}
		int initYear = 1900;
		for(int i = 0; i < years.length; i++) {
			years[i] = Integer.toString(initYear);
			initYear++;
		}
		
		monthPicker = new JComboBox<Object>(months);
		monthPicker.setSelectedItem(Integer.toString(account.getUser().getDob()).substring(0,2));
		monthPicker.setBounds(205, 140, 50, 25);
		monthPicker.setEnabled(false);

		dayPicker = new JComboBox<Object>(days);
		dayPicker.setBounds(260, 140, 50, 25);
		dayPicker.setSelectedItem(Integer.toString(account.getUser().getDob()).substring(2,4));
		dayPicker.setEnabled(false);

		yearPicker = new JComboBox<Object>(years);
		yearPicker.setSelectedItem(Integer.toString(account.getUser().getDob()).substring(4));
		yearPicker.setBounds(315, 140, 75, 25);
		yearPicker.setEnabled(false);

		
		this.add(label);
		this.add(monthPicker);
		this.add(dayPicker);
		this.add(yearPicker);
		
	}

	private void initPhoneNumberField() {
		
		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
		label.setBounds(50,170,130,25);
		label.setLabelFor(phoneNumberField1);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phoneNumberField1 = new JTextField(3);
		phoneNumberField1.setText(Long.toString(account.getUser().getPhone()).substring(0,3));
		phoneNumberField1.setBounds(205, 170, 35, 25);
		phoneNumberField1.setEditable(false);

		phoneNumberField2 = new JTextField(3);
		phoneNumberField2.setText(Long.toString(account.getUser().getPhone()).substring(3,6));
		phoneNumberField2.setBounds(245, 170, 35, 25);
		phoneNumberField2.setEditable(false);
		
		phoneNumberField3 = new JTextField(4);
		phoneNumberField3.setText(Long.toString(account.getUser().getPhone()).substring(6));
		phoneNumberField3.setBounds(285, 170, 50, 25);
		phoneNumberField3.setEditable(false);
		
		this.add(label);
		this.add(phoneNumberField1);	
		this.add(phoneNumberField2);
		this.add(phoneNumberField3);
	}
	
	private void initStreetAddressField() {
		
		JLabel label = new JLabel("Street Address", SwingConstants.RIGHT);
		label.setBounds(50,200,130,25);
		label.setLabelFor(streetAddressField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		streetAddressField = new JTextField(20);
		streetAddressField.setText(account.getUser().getStreetAddress());
		streetAddressField.setBounds(205, 200, 200, 25);
		streetAddressField.setEditable(false);
		
		this.add(label);
		this.add(streetAddressField);	
	}
	
	private void initCityField() {
		
		JLabel label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(50,230,130,25);
		label.setLabelFor(cityField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		cityField = new JTextField(20);
		cityField.setText(account.getUser().getCity());
		cityField.setBounds(205, 230, 200, 25);
		cityField.setEditable(false);
		
		this.add(label);
		this.add(cityField);	
	}
	
	private void initStateField() {
		
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(50,260,130,25);
		label.setLabelFor(stateField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] state = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		
		stateField = new JComboBox<Object>(state);
		stateField.setSelectedItem(account.getUser().getState());
		stateField.setBounds(205, 260, 200, 25);
		stateField.setEnabled(false);
		
		this.add(label);
		this.add(stateField);	
	}
	
	private void initPostalCodeField() {
		
		JLabel label = new JLabel("Postal Code", SwingConstants.RIGHT);
		label.setBounds(50,290,130,25);
		label.setLabelFor(postalCodeField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		postalCodeField = new JTextField(20);
		postalCodeField.setText(account.getUser().getZip());
		postalCodeField.setBounds(205, 290, 200, 25);
		postalCodeField.setEditable(false);
		
		this.add(label);
		this.add(postalCodeField);	
	}
	
	/*
	 * Initializes the components needed for the PIN textfield.
	 */
	
	
	/*
	 * Initializes the components needed for the login button.
	 */
	
	private void initEditButton() {	
		
		editButton = new JButton("Edit");
		editButton.setBounds(126, 340, 248, 35);
		editButton.addActionListener(this);
		
		this.add(editButton);
	}
	
	private void initErrorMessageLabel() {
		
		errorMessageLabel.setBounds(0, 310, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	/*
	 * Initializes the components needed for the create button.
	 */
	
	private void initCancelButton() {
		
		cancelButton = new JButton("Main Menu");
		cancelButton.setBounds(126, 380, 248, 35);
		cancelButton.addActionListener(this);
		
		//this.add(label);
		this.add(cancelButton);		
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
		else if(type == 3){
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
		else if(type == 4) {
			for(int i = 0; i < input.length(); i++) {
				if(!(input.charAt(i) >= 'a' && input.charAt(i) <= 'z') && !(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') && 
						!(input.charAt(i) == '\'') && !(input.charAt(i) == '-') && !(input.charAt(i) == ' ') && !(input.charAt(i) == ',') 
						&& !(input.charAt(i) == '.')) {
					System.out.print(i + " " + input.charAt(i));
					return false;
				}
			}
			return true;
		}
		else {
			for(int i = 0; i < input.length(); i++) {
				if(!(input.charAt(i) >= '0' && input.charAt(i) <= '9') && !(input.charAt(i) >= 'a' && input.charAt(i) <= 'z') && 
						!(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') && !(input.charAt(i) == '\'') && !(input.charAt(i) == '-') 
						&& !(input.charAt(i) == ' ') && !(input.charAt(i) == ',')&& !(input.charAt(i) == '.')) {
					System.out.print(i + " " + input.charAt(i));

					return false;
				}
			}
			return true;
		}
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks performed in the LoginView.
	 * 
	 * @param e
	 */
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source.equals(editButton)) {
			//set field to editable
			if(editButton.getText() == "Edit") {
				editButton.setText("Save");
				cancelButton.setText("Cancel");
				streetAddressField.setEditable(true);
				cityField.setEditable(true);
				stateField.setEnabled(true);
				postalCodeField.setEditable(true);
				phoneNumberField1.setEditable(true);
				phoneNumberField2.setEditable(true);
				phoneNumberField3.setEditable(true);
				pinField.setEditable(true);
				pinField.setEchoChar((char)0);
			}
			else if(editButton.getText() == "Save") {
				
				if(pinField.getText().length() != 4) {
					updateErrorMessage("Pin must be 4 characters long.");
				}
				else if(!checkUserInput(pinField.getText(), 1)) {
					updateErrorMessage("Pin can only includue numbers.");
				}
				else if(pinField.getText().length() != 4 || phoneNumberField1.getText().length() != 3 || phoneNumberField2.getText().length() != 3 
						|| phoneNumberField3.getText().length() != 4 || postalCodeField.getText().length() != 5) {
					updateErrorMessage("Entrues are too long or short.");
				}
				else if(!(checkUserInput(phoneNumberField1.getText(), 1)) || !(checkUserInput(phoneNumberField2.getText(), 1)) 
						|| !(checkUserInput(phoneNumberField3.getText(), 1))) {
					updateErrorMessage("Invalid entry, phoneNumber.");
				}
				else if(!(checkUserInput(postalCodeField.getText(), 1)) ) {
					updateErrorMessage("Invalid entry, postalCode.");
				}
				else if(!(checkUserInput(streetAddressField.getText(), 5))) {
					updateErrorMessage("Invalid entry, streetAddress.");
				}
				else if(!(checkUserInput(cityField.getText(), 4))) {
					updateErrorMessage("Invalid entry, city");
				}
				else {
				
					account.getUser().setStreetAddress(streetAddressField.getText());
					account.getUser().setCity(cityField.getText());
					account.getUser().setState(stateField.getSelectedItem().toString());
					account.getUser().setZip(postalCodeField.getText());
					account.getUser().setPhone(Long.valueOf(phoneNumberField1.getText() + phoneNumberField2.getText() + phoneNumberField3.getText()));
					account.getUser().setPin(account.getUser().getPin(), Integer.valueOf(pinField.getText()));
					manager.updateAct(account);

					streetAddressField.setEditable(false);
					cityField.setEditable(false);
					stateField.setEnabled(false);
					postalCodeField.setEditable(false);
					phoneNumberField1.setEditable(false);
					phoneNumberField2.setEditable(false);
					phoneNumberField3.setEditable(false);
					pinField.setEditable(false);
					pinField.setEchoChar('\u2022');
					editButton.setText("Edit");
					cancelButton.setText("Main Meu");
				}
			}
		}
		else if(source.equals(cancelButton)) {
			
			if(cancelButton.getText() == "Main Menu") {
				manager.sendBankAct(account, "Home");
				manager.switchTo(ATM.HOME_VIEW);
			}
			else {
				initInfoPortion();
				editButton.setText("Edit");
				cancelButton.setText("Main Menu");
			}
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
		
	}
}
