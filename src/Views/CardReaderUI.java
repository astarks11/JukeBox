package Views;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.CardReader;
/*-----------------------------------------------
 * Class: CardReaderUI
 * Author: Alex Starks
 * 
 * Purpose: This class acts as a JPanel and
 * contains the user interface for the User to swipe card
 * ---------------------------------------------*/
public class CardReaderUI extends JPanel {

	private JPanel thisPanel = this;
	private JButton loginButton; // login button on JPanel
	private JButton logoutButton; // logout button on Jpanel
	JLabel accountNameLabel; // label for account name
	JLabel accountPassLabel; // label for account password
	JLabel accountStatus; // status for account
	JLabel accountTimePlayed; // time played for account
	JTextArea accountNameText; // text area for account name input
	JPasswordField accountPassText; // text area for account password input
	private int HEIGHT; // height of JPanel
	private int WIDTH; // width of JPanel
	private int xLocation; // xLocation
	private int yLocation; // yLocation
	private final Border myBorder = BorderFactory.createLineBorder(Color.BLACK); // border for JTextArea
	private CardReader cardReader; // cardreader object
	private int userStatus; // user status
	private double userSeconds; // user seconds left

	
	/*--------------------------------------------
	 * Constructor: CardReaderUI
	 * Purpose: This constructor is responsible for setting
	 * instance variables and setting JPanel values
	 * -------------------------------------------*/
	public CardReaderUI(int width, int height, CardReader cR) {
		// set instance variables
		this.HEIGHT = 104; // (int)(height/4);
		this.WIDTH = 230; // (int)(width/2);
		this.xLocation = 0;
		this.yLocation = 0;
		this.cardReader = cR;
		
		// set JPanel variables
		this.setLayout(new GridLayout(4,2,10,10));
		this.setSize(WIDTH,HEIGHT);
		this.setBackground(Color.WHITE);
		this.setLocation(xLocation, yLocation);
		
		// ---------------------- initialize view --------------------------------------
		accountNameLabel = new JLabel("Account Name");
		this.add(accountNameLabel);
		
		accountNameText = new JTextArea();
		accountNameText.setBorder(myBorder);
		this.add(accountNameText);
		
		accountPassLabel = new JLabel("Password");
		this.add(accountPassLabel);
		
		accountPassText = new JPasswordField();
		accountPassText.setBorder(myBorder);
		this.add(accountPassText);
		
		loginButton = new JButton("login");
		this.add(loginButton);
		
		logoutButton = new JButton("sign out");
		this.add(logoutButton);
		
		accountStatus = new JLabel("");
		this.add(accountStatus);
		
		accountTimePlayed = new JLabel("");
		this.add(accountTimePlayed);
		
		// add KeyListeners
		MyKeyListener keyListener = new MyKeyListener();
		accountNameText.addKeyListener(keyListener);
		accountPassText.addKeyListener(keyListener);
		loginButton.addKeyListener(keyListener);
		logoutButton.addKeyListener(keyListener);
		// add Button Listeners
		ButtonListener buttonListener = new ButtonListener();
		loginButton.addActionListener(buttonListener);
		logoutButton.addActionListener(buttonListener);
		// set focus traversal to off (this way you can decide what happens when TAB is pressed)
		loginButton.setFocusTraversalKeysEnabled(false);
		logoutButton.setFocusTraversalKeysEnabled(false);
		// ---------------------- initialize view --------------------------------------
	}
	

	/* button listener class for the login and logout buttons */
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();
			
			if (buttonPressed == loginButton)
			{
				if (cardReader.validate(accountNameText.getText().trim(),accountPassText.getText().trim())) {
					userSeconds = (cardReader.getCurrentUser().secondsLeft());
					updateStatus();
				}
				else {
					accountStatus.setText("");
					accountTimePlayed.setText("");
					JOptionPane.showMessageDialog(null, "Invalid Credentials");
				}
			}
			if (buttonPressed == logoutButton) {
				accountStatus.setText("");
				accountTimePlayed.setText("");
			}
		}
	} // buttonlistener
	
	/* Method to set user seconds */
	public void setTime(Double seconds) {
		int hours = (int)(seconds / 3600);
		String strHours = "";
		if(hours / 10 == 0)
		{
			strHours = "0" + hours;
		}
		else strHours = "" + hours;
		Double remainder = seconds - hours * 3600;
		int mins = (int)(remainder / 60);
		String strMins = "";
		if(mins / 10 == 0)
		{
			strMins = "0" + mins;
		}
		else strMins = "" + mins;
		int secs = (int)(remainder - mins * 60);
		String strSecs = "";
		if(secs / 10 == 0)
		{
			strSecs = "0" + secs;
		}
		else strSecs = "" + secs;
		accountTimePlayed.setText("played: " + strHours +":"+ strMins +":"+ strSecs );
	}
	
	/* method for set status */
	public void updateStatus() {
		accountStatus.setText("status: "+cardReader.getCurrentUser().getSongsPlayed());
		setTime(cardReader.getCurrentUser().secondsLeft());
	}
	
	
	/* MyKeyListener class is used by JButtons and JTextAreas */
	public class MyKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// keyCode 9 is TAB
			// keyCode 10 is ENTER
			if (e.getSource() == accountNameText && e.getKeyCode() == 9)
			{
				accountNameText.setText(accountNameText.getText().trim());
				accountPassText.grabFocus();
			}
			if (e.getSource() == accountPassText && e.getKeyCode() == 9)
			{
				accountPassText.setText(accountPassText.getText().trim());
				loginButton.grabFocus();
			}
			if (e.getSource() == accountPassText && e.getKeyCode() == 10) 
			{
				loginButton.doClick();
			}
			if (e.getSource() == loginButton && e.getKeyCode() == 10)
			{
				loginButton.doClick();
			}
			if (e.getSource() == loginButton && e.getKeyCode() == 9)
			{
				logoutButton.grabFocus();
			}
			if (e.getSource() == logoutButton && e.getKeyCode() == 10)
			{
				logoutButton.doClick();
			}
			if (e.getSource() == logoutButton && e.getKeyCode() == 9)
			{
				accountNameText.grabFocus();	
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
