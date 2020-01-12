import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

//screen for account creation
public class CreateAccount extends JFrame implements ActionListener{

	//file for data of all users
	static File userDataFile = new File("res/userDataFile.txt");

	//user array of each user object
	public static ArrayList <User> userArray = new ArrayList <User>();

	//array of files for each user
	public static File[] fileArray = new File[100];

	//user number
	static int index = 0;

	//setup fonts
	private Font font = new Font("Sylfaen", Font.BOLD, 60);
	private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
	private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

	//create textfields and buttons for input
	private static JTextField usernameInput = new JTextField();
	private static JPasswordField passwordInput;
	private JCheckBox checkBox = new JCheckBox("Show Password");
	private static JTextField locationInput= new JTextField();
	private static JButton createAccountButton = new JButton("Create User");

	//create jlabels as headers
	private JLabel screenTitle = new JLabel("Create Personal User");
	private JLabel username = new JLabel("Username:");
	private JLabel password = new JLabel("Password:");
	private JLabel location = new JLabel("Location:");
	private JButton back = new JButton("BACK");

	//constructor method
	/**
	 * @author: Masum
	 */
	public CreateAccount () throws IOException {

		//setup frame
		setLayout(null);
		setBounds(0, 0, 1000, 600);
		this.setTitle("Create Personal User");
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color (130, 110, 255));


		//setup buttons, labels, and fields
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.setBounds(10, 10, 100, 76);
		add(back);
		back.addActionListener(this);
		back.setToolTipText("Go back to the home screen.");


		checkBox.setFont(font3);
		checkBox.setBounds(200, 250, 200, 25);
		checkBox.addActionListener(this);
		add(checkBox);

		screenTitle.setForeground(Color.BLACK);
		screenTitle.setFont(font);
		screenTitle.setBounds(175, 10, 650, 65);
		add(screenTitle);

		username.setForeground(Color.BLACK);
		username.setFont(font3);
		username.setBounds(80, 160, 150, 20);
		add(username);

		usernameInput.setForeground(Color.BLACK);
		usernameInput.setFont(font3);
		usernameInput.setBounds(200, 160, 250, 30);
		add(usernameInput);

		password.setForeground(Color.BLACK);
		password.setFont(font3);
		password.setBounds(80, 220, 150, 20);
		add(password);

		passwordInput  = new JPasswordField(20);
		passwordInput.setEchoChar('*');
		passwordInput.setBounds(200, 220, 250, 30);
		add(passwordInput);

		location.setForeground(Color.BLACK);
		location.setFont(font3);
		location.setBounds(80, 280, 100, 25);
		add(location);

		locationInput.setForeground(Color.BLACK);
		locationInput.setFont(font3);
		locationInput.setBounds(200, 280, 250, 30);

		add(locationInput);

		createAccountButton.setOpaque(false);
		createAccountButton.setContentAreaFilled(false);
		createAccountButton.setBorderPainted(false);
		createAccountButton.setBorder(null);
		createAccountButton.setFont(new Font("Segoe Script", Font.PLAIN, 30));
		createAccountButton.setForeground(Color.BLACK);
		createAccountButton.setBounds(50, 475, 300, 75);
		add(createAccountButton);
		createAccountButton.addActionListener(this);

		// Makes button bigger when hovered over
		createAccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				createAccountButton.setForeground(Color.BLACK);
				createAccountButton.setFont(new Font("Segoe Script", Font.PLAIN, 40));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				createAccountButton.setForeground(Color.BLACK);
				createAccountButton.setFont(new Font("Segoe Script", Font.PLAIN, 30));
			}
		});

		//setup frame
		setVisible(true);
		repaint();

	}

	//method for saving account data
	/**
	 * @author: Masum
	 */
	private void save() {

		try {

			//if file doesn't exist, create ite
			if (!userDataFile.exists()) {
				userDataFile.createNewFile();
			}

			//create writers for the file to add onto it
			FileWriter fWriter = new FileWriter(userDataFile, true);
			PrintWriter pWriter = new PrintWriter(fWriter);

			//write in the values entered, in csv format
			pWriter.print(",");
			pWriter.println();
			pWriter.write(usernameInput.getText() + ",");
			pWriter.write(passwordInput.getText() + ",");
			pWriter.write(locationInput.getText() + "");
			
			//Closing PrintWriter Stream
			pWriter.close();

		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}


	}

	//method for loading data
	/**
	 * @author: Masum
	 * @param: name of file which is being loaded
	 */
	public static void load(String fileName) {

		//setup array of files
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = new File("res/user" + i + ".txt");

		}

		//clear the array of users to re-add them
		userArray.clear();


		try {

			// Reads in text file
			Scanner input = new Scanner(new File(fileName));

			//splits by the comma
			input.useDelimiter(",");

			//number of inputs
			int index = 0;

			//while there is more data
			while (input.hasNextLine()) {

				//skip the space
				input.nextLine();

				//get inputs
				String username = input.next();
				String password = input.next();
				String location = input.next();

				int[] tempArray = new int[17];

				//add user to userarray
				userArray.add(new User (username, password, location, 1, 0, tempArray));

				//if a file doesn't already exist
				if (!fileArray[index].exists()) {

					//create a file
					try {
						fileArray[index].createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			

				}

				//increment users
				index++;

			}

			//close input
			input.close();

		} catch (FileNotFoundException e) {

			System.out.println("Load file not found + load");

		}
	}

	//method for reading in user data
	/**
	 * @author: Masum
	 */
	public static void getUserData() {

		try {

			//get input file
			Scanner input = new Scanner(new File("res/user" + LoginScreen.currentUser + ".txt"));

			//split data by the comma
			input.useDelimiter(",");

			//if there is more data
			while (input.hasNextLine()) {

				//read in data
				double pricePoint = input.nextDouble();
				int pricePointSize = input.nextInt();

				//read in next data into array
				int [] categoryAmountArray = new int [17];
				for (int i = 0; i < 17; i++) {

					categoryAmountArray[i] = input.nextInt();

				}

				//get already existing values 
				CreateAccount.userArray.get(LoginScreen.currentUser).setPricePoint(pricePoint);
				CreateAccount.userArray.get(LoginScreen.currentUser).setPricePointSize(pricePointSize);
				CreateAccount.userArray.get(LoginScreen.currentUser).setCategoryScores(categoryAmountArray);

			}

			//close input
			input.close();

		} catch (FileNotFoundException e) {

			System.out.println("Load file not found getuserdata");

		}

	}

	//if action occurs
	@Override
	public void actionPerformed(ActionEvent e) {

		//if it is the create account button
		if (e.getSource() == createAccountButton) {

			//save data
			save();
			
			//go to home screen
			dispose();
			new HomeScreen();


		}

		//if back button pressed
		if(e.getSource() == back){

			//close screen and open previous screen, homescreen
			dispose();
			new HomeScreen();
		}

		//if checkbox changed
		if(e.getSource() == checkBox){

			//if selected
			if(checkBox.isSelected() == true)
				
				//show password
				passwordInput.setEchoChar((char)0);
			else
				
				//otherwise, hide with ***
				passwordInput.setEchoChar('*');
		}
		
	}
	
}


