package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LoginView extends JPanel implements ActionListener {
	
	private final static String LOGIN = "Login";
	private final static String CREATE = "Open an Account";
	
	private ViewManager manager;
	private JButton loginButton;
	private JButton createButton;
	private JTextField accountField;
	private JPasswordField pinField;

	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public LoginView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initialies the LoginView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initAccountField();
		initPinField();
		initLoginButton();
		initCreateButton();
	}
	
	/*
	 * Initialies the components needed for the account number textfield.
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

	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/**
	 * Responds to button clicks performed in the LoginView.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case LOGIN: manager.login(accountField.getText(), pinField.getPassword()); break;
			case CREATE: manager.switchTo(ATM.CREATE_VIEW); break;
			default: System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")"); break;
		}
	}

}
