package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import controller.ViewManager;
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JComboBox dayPicker;
	private JComboBox monthPicker;
	private JComboBox yearPicker;
	private JTextField phoneNumberField1;
	private JTextField phoneNumberField2;
	private JTextField phoneNumberField3;
	private JTextField streetAddressField;
	private JTextField cityField;
	private JComboBox stateField;
	private JTextField postalCodeField;
	private JTextField pinField;
	private JButton cancelButton;	
	private JButton createAccountButton;	
	private JLabel errorMessageLabel;
	private long newAccountNumber;
	private BankAccount newAccount;
	private User newUser;
	private JFrame frame;
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
		initialize();
	}
	
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initFirstNameField();
		initLastNameField();
		initDOBField();
		initCancelButton();
		initPhoneNumberField();
		initStreetAddressField();
		initCityField();
		initStateField();
		initPostalCodeField();
		initPinField();
		initCreateAccountButton();
		initCancelButton();
		
		this.add(new javax.swing.JLabel("CreateView", javax.swing.SwingConstants.CENTER));
		
	}
	
	private void initFirstNameField() {
		JLabel label = new JLabel("First Name:", SwingConstants.RIGHT);
		label.setBounds(50, 60, 130, 25);
		label.setLabelFor(firstNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		firstNameField = new JTextField(20);
		firstNameField.setBounds(205, 60, 200, 25);
		//firstNameField.addActionListener(this);
		this.add(label);
		this.add(firstNameField);
	}
	
	private void initLastNameField() {
		JLabel label = new JLabel("Last Name:", SwingConstants.RIGHT);
		label.setBounds(50, 95, 130, 25);
		label.setLabelFor(lastNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lastNameField = new JTextField(20);
		lastNameField.setBounds(205, 95, 200, 25);
		//lastNameField.addActionListener(this);
		
		this.add(label);
		this.add(lastNameField);
	}
	
	private void initPinField() {
		JLabel label = new JLabel("Pin Number:", SwingConstants.RIGHT);
		label.setBounds(50,130,130,25);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setBounds(205, 130, 200, 25);
		
		this.add(label);
		this.add(pinField);	
	}
	
	private void initDOBField() {
		JLabel label = new JLabel("Date of Birth:", SwingConstants.RIGHT);
		label.setBounds(50, 165, 130, 25);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] month = new String[12];
		String[] day = new String[31];
		String[] year = new String[119];
		
		for(int i = 0; i < day.length; i++) {
			day[i] = Integer.toString(i+1);
			if(day[i].length() != 2) {
				String original = day[i];
				day[i] = "0" + original;
			}
		}
		dayPicker = new JComboBox(day);
		dayPicker.setBounds(260, 165, 50, 25);
		
		for(int i = 0; i < month.length; i++) {
			month[i] = Integer.toString(i+1);
			if(month[i].length() != 2) {
				String original = month[i];
				month[i] = "0" + original;
			}
		}
		monthPicker = new JComboBox(month);
		monthPicker.setBounds(205, 165, 50, 25);

		int initYear = 1900;
		for(int i = 0; i < year.length; i++) {
			year[i] = Integer.toString(initYear);
			initYear++;
		}
		yearPicker = new JComboBox(year);
		yearPicker.setBounds(315, 165, 75, 25);
		
		this.add(label);
		this.add(monthPicker);
		this.add(dayPicker);
		this.add(yearPicker);
		
	}
	
	private void initPhoneNumberField() {
		JLabel label = new JLabel("Phone Number:", SwingConstants.RIGHT);
		label.setBounds(50,200,130,25);
		label.setLabelFor(phoneNumberField1);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phoneNumberField1 = new JTextField(3);
		phoneNumberField1.setBounds(205, 200, 35, 25);
		
		phoneNumberField2 = new JTextField(3);
		phoneNumberField2.setBounds(245, 200, 35, 25);

		phoneNumberField3 = new JTextField(4);
		phoneNumberField3.setBounds(285, 200, 50, 25);
		
		this.add(label);
		this.add(phoneNumberField1);	
		this.add(phoneNumberField2);
		this.add(phoneNumberField3);
	}
	
	private void initStreetAddressField() {
		JLabel label = new JLabel("Street Address:", SwingConstants.RIGHT);
		label.setBounds(50, 235, 130, 25);
		label.setLabelFor(streetAddressField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		streetAddressField = new JTextField(20);
		streetAddressField.setBounds(205, 235, 200, 25);
		//streetAddressField.addActionListener(this);
		
		this.add(label);
		this.add(streetAddressField);
	}
	
	private void initStateField() {
			JLabel label = new JLabel("State:", SwingConstants.RIGHT);
			label.setBounds(50,270,130,25);
			label.setLabelFor(stateField);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			String[] state = {"AL", "AK", "AZ", "AR", "CA", 
					"CO", "CT", "DC", "DE", "FL", "GA", "HI", 
					"ID", "IL", "IN", "IA", "KS", "KY", "LA", 
					"ME", "MD", "MA", "MI", "MN", "MS", "MO", 
					"MT", "NE", "NV", "NH", "NJ", "NM", "NY", 
					"NC", "ND", "OH", "OK", "OR", "PA", "PR", 
					"RI", "SC", "SD", "TN", "TX", "UT", "VT", 
					"VA", "WA", "WV", "WI", "WY"};
			
			stateField = new JComboBox<Object>(state);
			stateField.setBounds(205, 270, 50, 25);
			
			this.add(label);
			this.add(stateField);	
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City:", SwingConstants.RIGHT);
		label.setBounds(50, 305, 130, 25);
		label.setLabelFor(cityField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		cityField = new JTextField(20);
		cityField.setBounds(205, 305, 200, 25);
		//cityField.addActionListener(this);
		
		this.add(label);
		this.add(cityField);
	}
	
	private void initPostalCodeField() {
		JLabel label = new JLabel("Postal Code:", SwingConstants.RIGHT);
		label.setBounds(50, 340, 130, 25);
		label.setLabelFor(postalCodeField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		postalCodeField = new JTextField(20);
		postalCodeField.setBounds(205, 340, 200, 25);
		//postalCodeField.addActionListener(this);
		
		this.add(label);
		this.add(postalCodeField);
	}
	
	private void initCancelButton() {
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds (20, 20, 100, 25);
		cancelButton.addActionListener(this);

		this.add(cancelButton);	
	}
	
	private void initCreateAccountButton() {
		createAccountButton = new JButton("Create Account");
		createAccountButton.setBounds(140, 390, 220, 40);
		createAccountButton.addActionListener(this);

		this.add(createAccountButton);		
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
			newAccountNumber = manager.db.highestAcctNumber() + 1;
			JOptionPane.showMessageDialog(frame,"Your account number is: " + Long.toString(newAccountNumber));
			newUser = new User(Integer.valueOf(pinField.getText()), Integer.valueOf((String)yearPicker.getSelectedItem() + 
					(String)monthPicker.getSelectedItem() + (String)dayPicker.getSelectedItem()), 
					Long.valueOf(phoneNumberField1.getText() + phoneNumberField2.getText() + phoneNumberField3.getText()), 
					firstNameField.getText(), lastNameField.getText(), streetAddressField.getText(), cityField.getText(), 
					(String)stateField.getSelectedItem(), postalCodeField.getText());
			System.out.println(newUser.toString());
			newAccount = new BankAccount('Y', newAccountNumber, 0.00, newUser);
			manager.db.insertAccount(newAccount); 
			pinField.setText("");
			firstNameField.setText("");
			lastNameField.setText("");
			dayPicker.setSelectedIndex(0);
			monthPicker.setSelectedIndex(0);
			yearPicker.setSelectedIndex(0);
			phoneNumberField1.setText("");
			phoneNumberField2.setText("");
			phoneNumberField3.setText("");
			streetAddressField.setText("");
			cityField.setText("");
			stateField.setSelectedItem("AL");
			postalCodeField.setText("");
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else /*if(source.equals(cancelButton))*/ {
			pinField.setText("");
			firstNameField.setText("");
			lastNameField.setText("");
			dayPicker.setSelectedIndex(0);
			monthPicker.setSelectedIndex(0);
			yearPicker.setSelectedIndex(0);
			phoneNumberField1.setText("");
			phoneNumberField2.setText("");
			phoneNumberField3.setText("");
			streetAddressField.setText("");
			cityField.setText("");
			stateField.setSelectedItem("AL");
			postalCodeField.setText("");
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		/*else {
			System.out.println("Error!");
		}*/
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
}
