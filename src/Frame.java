import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

//main frame class which adds panels on top
public class Frame implements ActionListener{

	//create field for user to search
	public JTextField searchField = new JTextField();

	//create button for user to click to make the search
	public JButton searchButton = new JButton("SEARCH");

	//create panels for home page and searched page
	static JPanel searchPanel = new JPanel();
	static JPanel homePanel = new JPanel();
	
	//create main frame of program
	static JFrame mainFrame = new JFrame();
	
	//create and initilaize variable for search query
	static String search = "";
	
	//constructor method which sets up a frame
	/**
	 * @author: Masum
	 */
	public Frame () {
		
		//setup frame
		mainFrame.setBounds(0, 0, 1200, 1000);
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);
		Frame.mainFrame.getContentPane().removeAll();

		//add searchfield and button to frame
		searchField.setBounds(300, 900, 400, 50);
		mainFrame.add(searchField);
		searchButton.setBounds(800, 900, 100, 50);
		searchButton.addActionListener(this);
		mainFrame.add(searchButton);

		//add home panel to frame
		homePanel = new Home();
		mainFrame.add(homePanel);

		//make frame visible
		mainFrame.setVisible(true);

	}

	//if action performed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if search button clicked
		if (e.getSource() == searchButton) {
			
			//set search to text field
			search = searchField.getText();
			
			//create new search
			Search catSearch = new Search();
			
			//remove everything from frame
			Frame.mainFrame.getContentPane().removeAll();

			//add back field
			mainFrame.add(searchField);

			//add search button
			mainFrame.add(searchButton);

			//and search page and format
			Frame.mainFrame.add(catSearch);
			Frame.mainFrame.setVisible(true);
			Frame.mainFrame.validate();
			Frame.mainFrame.repaint();
			
			

		}
		
	}

}
