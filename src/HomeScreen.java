//declares all imports needed for this screen
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.GridLayout;

//screen when program opened
public class HomeScreen extends JFrame implements ActionListener {
	
	//the title label for this screen
   private JLabel titleLabel = new JLabel("Product Picker 2019"); 
   
   //setup buttons
   private JButton startButton = new JButton("Login");                      
   private JButton setupButton = new JButton("Create an Account");


   //constructor method setups up frame
	/**
	 * @author: Masum
	 */
   public HomeScreen() {

	   //setup screen
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                             
       setBounds(0, 0, 1000, 622); 
       setLayout(null);
       getContentPane().setBackground(new Color (255, 215, 0));

       //setup buttons and labels
       titleLabel.setBounds(300, 0, 500, 133);                         
       titleLabel.setFont(new Font("Athelas", Font.PLAIN, 45));          
       add(titleLabel);                                                  

       startButton.addActionListener(this);                              
       startButton.setBounds(340, 250, 300, 50);                                 
       startButton.setFont(new Font("Athelas", Font.PLAIN, 30));                 
       startButton.setForeground(new Color(0, 0, 0));
       startButton.setBackground(Color.WHITE);                                   
       add(startButton);                              

       setupButton.addActionListener(this);                                      
       setupButton.setBounds(340, 350, 300, 50);                                 
       setupButton.setBackground(new Color(255, 255, 255));
       setupButton.setFont(new Font("Athelas", Font.PLAIN, 30));                  
       add(setupButton);                                                          

       setVisible(true);

   }

   //if action performed
   @Override
   public void actionPerformed(ActionEvent event) {

       //if the start button is clicked on, lead the user to the login screen
       if(event.getSource()==startButton) {
           try {
               new LoginScreen();
               dispose();
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }

       //if the set up button is clicked on, lead the user to the account creation screen
       if(event.getSource()==setupButton) {
           try {
               new CreateAccount();
               dispose();
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }
       
   }

}
