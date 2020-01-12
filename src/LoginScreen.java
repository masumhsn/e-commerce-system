import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//screen for user login
public class LoginScreen extends JFrame implements ActionListener {

	//integer representing the user currently signed in
	public static int currentUser = 0;

	//create button for going to previous screen
	private JButton back = new JButton("BACK");

	//setup jlabels for headers
	private JLabel screenTitle = new JLabel("Product Picker 2019");
	private JLabel usernameLabel = new JLabel("Username:");
	private JLabel passwordLabel = new JLabel("Password:");
	private JCheckBox checkBox = new JCheckBox("Show Password");

	//setup fields for user entry
	private JTextField username;
	private JPasswordField password;

	//create fonts
	private Font font = new Font("Sylfaen", Font.BOLD, 60);
	private Font font2 = new Font("Sylfaen", Font.PLAIN, 35);
	private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

	//create button to log into program
	private JButton login = new JButton("Login");

	//constructor
	/**
	 * @author: Masum
	 */
	public LoginScreen() throws IOException {

		//setup jframe
		setLayout(null);
		setBounds(0, 0, 1000, 600);
		this.setTitle("Login");
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color (250, 190, 200));
		setSize(1000, 600);
		repaint();

		//setup buttons and labels
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.setBounds(10, 10, 100, 76);
		add(back);
		back.setFont(font2);
		back.addActionListener(this);
		back.setToolTipText("Go back to the home screen.");

		screenTitle.setForeground(Color.BLACK);
		screenTitle.setFont(font);
		screenTitle.setBounds(213, 10, 575, 65);
		add(screenTitle);

		usernameLabel.setForeground(Color.BLACK);
		usernameLabel.setFont(font2);
		usernameLabel.setBounds(310, 125, 200, 35);
		add(usernameLabel);

		username = new JTextField(20);
		username.setBounds(480, 125, 250, 40);
		add(username);
		TextFieldListener tf1Listener = new TextFieldListener();
		username.addActionListener(tf1Listener);

		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(font2);
		passwordLabel.setBounds(315, 200, 200, 35);
		add(passwordLabel);

		password  = new JPasswordField(20);
		password.setEchoChar('*');
		password.setBounds(480, 200, 250, 40);
		add(password);
		TextFieldListener tf2Listener = new TextFieldListener();
		password.addActionListener(tf2Listener);

		login.setOpaque(true);
		login.setBorderPainted(false);
		login.setFont(new Font("Segoe Script", Font.PLAIN, 30));
		login.setForeground(Color.BLACK);
		login.setBounds(400, 300, 200, 50);
		add(login);
		login.addActionListener(this);

		checkBox.setFont(font3);
		checkBox.setBounds(490, 250, 200, 25);
		checkBox.addActionListener(this);
		add(checkBox);

		setVisible(true);

	}

	//boolean to check if user is inputting a matched username and password
	private boolean validateCredentials() throws IOException {

		//get list of accounts
		CreateAccount.load(CreateAccount.userDataFile.toString());

		//get user input
		String tempUsername = username.getText();
		String tempPassword = password.getText();

		//cycle through user data
		for(int x = 0; x < CreateAccount.userArray.size(); x++){

			//if username and password match
			if(tempUsername.equals(CreateAccount.userArray.get(x).getUsername()) && tempPassword.equals(CreateAccount.userArray.get(x).getPassword())) {

				//set the user to the user who logged in
				currentUser = x;
				CreateAccount.getUserData();

				return true;
			}

		}
		//if the user never matches, false will return by default (if true, will break loop)
		return false;
	}

	//setup mesage box for incorrect entry
	/**
	 * @author: Masum
	 * @param: description string
	 */
	public void loginError(String description) {
		JTextArea textArea = new JTextArea(description);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setPreferredSize(new Dimension(300, 50));
		JOptionPane.showMessageDialog(textArea, scrollPane, "Login Error", JOptionPane.INFORMATION_MESSAGE);
	}

	//if action performed
	@Override
	public void actionPerformed(ActionEvent e) {

		//if checkbox clicked
		if(e.getSource() == checkBox){

			//show and unshow password
			if(checkBox.isSelected() == true)
				password.setEchoChar((char)0);
			else
				password.setEchoChar('*');
		}

		//if login button pressed
		if(e.getSource() == login){

			try {

				//check if credentials are valid
				if (validateCredentials() == true) {

					//make button green
					login.setBackground(Color.green);

					//show green button for a second
					new java.util.Timer().schedule(
							new java.util.TimerTask() {
								@Override
								public void run() {

									login.setBackground(Color.WHITE);
									
									//open new frame for homepage
									FinalProjectTest.loadDatabase();
									FinalProjectTest.loadCategoryLists();
									new Frame();
									dispose();

								}
							},
							1000
							);

					//if incorrect credentials
				} else {

					//make button red for a second
					login.setBackground(Color.red);
					new java.util.Timer().schedule(
							new java.util.TimerTask() {
								@Override
								public void run() {

									login.setBackground(Color.WHITE);

								}
							},
							1000
							);

					loginError("Username and/or password is incorrect!");

				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		//if back button pressed
		if(e.getSource() == back){

			//close screen and open previous screen, homescreen
			dispose();
			new HomeScreen();
			
		}
		
	}

	private class TextFieldListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
	}
	
}



