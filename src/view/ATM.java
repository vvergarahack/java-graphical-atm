package view;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ATM extends JFrame {
	
	public final static int INVALID_AMOUNT = 0;
	public final static int INSUFFICIENT_FUNDS = 1;
	public final static int ACCOUNT_NOT_FOUND = 2;
	public final static int SUCCESS = 3;
	
	public final static String LOGIN_VIEW = "LOGIN_VIEW";
	public final static String CREATE_VIEW = "CREATE_VIEW";
	public final static String HOME_VIEW = "HOME_VIEW";
		
	/**
	 * Constructs an instance (or object) of the ATM class.
	 */
	
	public ATM() {
		super("Graphical ATM");
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////

	/*
	 * Initialies the ATM components.
	 */
	
	private void initialize() {		
		JPanel views = new JPanel(new CardLayout());
		ViewManager manager = new ViewManager(views);
		
		// add child views to the parent container

		views.add(new LoginView(manager), ATM.LOGIN_VIEW);
		views.add(new CreateView(manager), ATM.CREATE_VIEW);
		views.add(new HomeView(manager), ATM.HOME_VIEW);
		
		this.add(views);
		this.setBounds(100, 100, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	///////////////////// MAIN METHOD /////////////////////////////////////////////////

	/**
	 * Program execution starts here.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ATM().initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
