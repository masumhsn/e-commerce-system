import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

//screen for home page when logging on
public class Home extends JPanel implements ActionListener, MouseListener {

	//setup labels for main categories
	JLabel technologyLabel = new JLabel ("Technology");
	JLabel furnitureLabel = new JLabel ("Furniture");
	JLabel officeSuppliesLabel = new JLabel ("Office Supplies");

	//setup arraylist of labels for each subcategory
	JLabel technologySubLabels [] = new JLabel [4];
	JLabel furnitureSubLabels [] = new JLabel [4];
	JLabel officeSuppliesSubLabels [] = new JLabel [9];

	//create product labels for the top categories
	JLabel topCatLabels[] = new JLabel[3];

	//create arraylist of the top 3 categories
	static ArrayList <Product>[] topCats = new ArrayList[3];

	//constructor which sets up panel
	/**
	 * @author: Masum
	 */
	public Home () {

		//setup panel
		setBounds(0, 0, 1200, 1000);
		setLayout(null);
		setBackground(new Color (240, 250, 255));
		setOpaque(true);


		//add and setup labels
		technologyLabel.setFont(new Font("Segoe Script", Font.PLAIN, 40));
		technologyLabel.setBounds(95, 700, 320, 75);
		technologyLabel.setOpaque(true);
		technologyLabel.setHorizontalAlignment(JLabel.CENTER);
		technologyLabel.setVerticalAlignment(JLabel.CENTER);
		technologyLabel.setBackground(Color.GRAY);
		add(technologyLabel);
		technologyLabel.addMouseListener(this);


		technologySubLabels[0] = new JLabel("Phones");
		technologySubLabels[1] = new JLabel("Accessories");
		technologySubLabels[2] = new JLabel("Copiers");
		technologySubLabels[3] = new JLabel("Machines");

		for (int cycleCat = 0; cycleCat < technologySubLabels.length; cycleCat++) {
			technologySubLabels[cycleCat].setFont(new Font("Segoe Script", Font.PLAIN, 30));
			technologySubLabels[cycleCat].setBackground(Color.LIGHT_GRAY);
			technologySubLabels[cycleCat].setOpaque(true);
			technologySubLabels[cycleCat].setHorizontalAlignment(JLabel.CENTER);
			technologySubLabels[cycleCat].setVerticalAlignment(JLabel.CENTER);
			technologySubLabels[cycleCat].setBounds(95 + cycleCat * 240, 800, 240, 50);
			add(technologySubLabels[cycleCat]);
			technologySubLabels[cycleCat].addMouseListener(this);
			technologySubLabels[cycleCat].setVisible(false);
		}

		officeSuppliesLabel.setFont(new Font("Segoe Script", Font.PLAIN, 30));
		officeSuppliesLabel.setBounds(415, 700, 320, 75);
		officeSuppliesLabel.setOpaque(true);
		officeSuppliesLabel.setHorizontalAlignment(JLabel.CENTER);
		officeSuppliesLabel.setVerticalAlignment(JLabel.CENTER);
		officeSuppliesLabel.setBackground(Color.GRAY);
		add(officeSuppliesLabel);
		officeSuppliesLabel.addMouseListener(this);

		officeSuppliesSubLabels[0] = new JLabel("Binders");
		officeSuppliesSubLabels[1] = new JLabel("Supplies");
		officeSuppliesSubLabels[2] = new JLabel("Appliances");
		officeSuppliesSubLabels[3] = new JLabel("Storage");
		officeSuppliesSubLabels[4] = new JLabel("Art");
		officeSuppliesSubLabels[5] = new JLabel("Paper");
		officeSuppliesSubLabels[6] = new JLabel("Envelopes");
		officeSuppliesSubLabels[7] = new JLabel("Labels");
		officeSuppliesSubLabels[8] = new JLabel("Fasteners");

		for (int cycleCat = 0; cycleCat < officeSuppliesSubLabels.length; cycleCat++) {
			officeSuppliesSubLabels[cycleCat].setFont(new Font("Segoe Script", Font.PLAIN, 30));
			officeSuppliesSubLabels[cycleCat].setBackground(Color.LIGHT_GRAY);
			officeSuppliesSubLabels[cycleCat].setOpaque(true);
			officeSuppliesSubLabels[cycleCat].setHorizontalAlignment(JLabel.CENTER);
			officeSuppliesSubLabels[cycleCat].setVerticalAlignment(JLabel.CENTER);

			officeSuppliesSubLabels[cycleCat].setBounds(((cycleCat - 4) * 190) + 95, 850, 190, 50);

			add(officeSuppliesSubLabels[cycleCat]);
			officeSuppliesSubLabels[cycleCat].addMouseListener(this);
			officeSuppliesSubLabels[cycleCat].setVisible(false);
		}

		officeSuppliesSubLabels[0].setBounds(95, 800, 190, 50);
		officeSuppliesSubLabels[1].setBounds(285, 800, 190, 50);
		officeSuppliesSubLabels[2].setBounds(665, 800, 190, 50);
		officeSuppliesSubLabels[3].setBounds(855, 800, 190, 50);

		furnitureLabel.setFont(new Font("Segoe Script", Font.PLAIN, 40));
		furnitureLabel.setBounds(735, 700, 320, 75);
		furnitureLabel.setOpaque(true);
		furnitureLabel.setHorizontalAlignment(JLabel.CENTER);
		furnitureLabel.setVerticalAlignment(JLabel.CENTER);
		furnitureLabel.setBackground(Color.GRAY);
		add(furnitureLabel);
		furnitureLabel.addMouseListener(this);

		furnitureSubLabels[0] = new JLabel("Chairs");
		furnitureSubLabels[1] = new JLabel("Tables");
		furnitureSubLabels[2] = new JLabel("Bookcases");
		furnitureSubLabels[3] = new JLabel("Furnishings");

		for (int cycleCat = 0; cycleCat < technologySubLabels.length; cycleCat++) {
			furnitureSubLabels[cycleCat].setFont(new Font("Segoe Script", Font.PLAIN, 30));
			furnitureSubLabels[cycleCat].setBackground(Color.LIGHT_GRAY);
			furnitureSubLabels[cycleCat].setOpaque(true);
			furnitureSubLabels[cycleCat].setHorizontalAlignment(JLabel.CENTER);
			furnitureSubLabels[cycleCat].setVerticalAlignment(JLabel.CENTER);
			furnitureSubLabels[cycleCat].setBounds(95 + cycleCat * 240, 800, 240, 50);
			add(furnitureSubLabels[cycleCat]);
			furnitureSubLabels[cycleCat].addMouseListener(this);
			furnitureSubLabels[cycleCat].setVisible(false);
		}

		//create arraylist for the most clicked categories
		int [] topIndex = new int [] {0,0,0};

		//get current scores 
		int [] categoryScoresTemp = CreateAccount.userArray.get(LoginScreen.currentUser).getCategoryScores();

		//cycle through scores
		for(int i = 0; i < categoryScoresTemp.length; i++) {

			//if it is the highest
			if (categoryScoresTemp[i] > topIndex[0]) {

				//make it the first, and slide everything down
				topIndex[2] = topIndex[1];
				topIndex[1] = topIndex[0];
				topIndex[0] = categoryScoresTemp[i];

				//if it is the second highest
			} else if ( categoryScoresTemp[i] > topIndex[1]) {

				//make it second and slide everything under down
				topIndex[2] = topIndex[1];
				topIndex[1] = categoryScoresTemp[i];

				//if it is third
			} else if (categoryScoresTemp[i] > topIndex[2]) {

				//make it third
				topIndex[2] = categoryScoresTemp[i];
			}

		}

		//cycle through 3 scores
		for (int i = 0; i < 3; i++) {

			//cycle through all categories
			for (int j = 0; j < categoryScoresTemp.length; j++) {

				//if score matches
				if (categoryScoresTemp[j] == topIndex[i]) {

					//make the category to the corresponding highest value
					if (j < 4) {
						topCats[i] = FinalProjectTest.technology[j];
						break;
					}
					else if (j < 8) {
						topCats[i] = FinalProjectTest.furniture[j - 4];
						break;
					}
					else {
						topCats[i] = FinalProjectTest.officeSupplies[j - 8];
						break;
					}


				}

			}

		}

		//if there is a tie, such as no data being present for the user, pick a random one
		int r = (int) (Math.random() * 16);
		if (topIndex[0] == topIndex[1] && topIndex[1] == topIndex[2]) {
			r = (int) (Math.random() * 4) + 4;
			topCats[1] = FinalProjectTest.furniture[r - 4];
			r = (int) (Math.random() * 4) + 8;
			topCats[1] = FinalProjectTest.officeSupplies[r - 8];
		} else if (topIndex[1] == topIndex[2]) {
			r = (int) (Math.random() * 4) + 8;
			topCats[1] = FinalProjectTest.officeSupplies[r - 8];
		}

		//setup labels for top categories
		for (int i = 0; i < 3; i++) {
			topCatLabels[i] = new JLabel();
			topCatLabels[i].setText(topCats[i].get(1).getSubCategory());
			topCatLabels[i].setFont(new Font("Segoe Script", Font.PLAIN, 50));
			topCatLabels[i].setBounds((350 * i) + 75, 50, 330, 100);
			add(topCatLabels[i]);
		}

		//setup labels for each product
		for (int j = 0; j < 3; j++) {

			//get the top scoring results
			getTopResults(topCats[j]);

			//format and display them
			for (int i = 0; i < 3; i++) {
				add (Search.labelArray[i]);
				Search.labelArray[i].setLocation((350 * j) + 75, i * 200 + 120);
			}

		}

	}

	//method for sorting by category
	public void sortCats () {

		//get the user relevancy for all 3 categories
		for (int i = 0; i < 3; i++) {
			Search.getUserRelevancy(topCats[i], 4);

		}

	}

	//method for getting top results
	/**
	 * @author: Masum
	 * @param: arraylist of either a category list or list of all products
	 */
	public void getTopResults (ArrayList<Product> list) {

		//array of the top results
		String topResultNames [] = new String [10];
		Product topResults[] = new Product [10];

		//cycle through to get 3 for the one category
		for (int i = 0; i < 3; i++) {

			//setup the label for the product
			topResults[i] = list.get(i);
			topResultNames[i] = topResults[i].getName();			
			Search.setLabel(topResults[i], topResults[i].getName(), i);

		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	//if mouse entered
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

		//open up the corresponding button that was clicked
		if (e.getSource() == technologyLabel) { 

			technologyLabel.setFont(new Font("Segoe Script", Font.PLAIN, 50));
			for (int cycleCat = 0; cycleCat < technologySubLabels.length; cycleCat++) {
				technologySubLabels[cycleCat].setVisible(true);
			}
			for (int cycleSupplies = 0; cycleSupplies < officeSuppliesSubLabels.length; cycleSupplies++) {
				officeSuppliesSubLabels[cycleSupplies].setVisible(false);
			}
			for (int cycleFurniture = 0; cycleFurniture < furnitureSubLabels.length; cycleFurniture++) {
				furnitureSubLabels[cycleFurniture].setVisible(false);	
			}

		} else if (e.getSource() == officeSuppliesLabel) {
			officeSuppliesLabel.setFont(new Font("Segoe Script", Font.PLAIN, 30));
			for (int cycleCat = 0; cycleCat < technologySubLabels.length; cycleCat++) {
				technologySubLabels[cycleCat].setVisible(false);
			}
			for (int cycleSupplies = 0; cycleSupplies < officeSuppliesSubLabels.length; cycleSupplies++) {
				officeSuppliesSubLabels[cycleSupplies].setVisible(true);
			}
			for (int cycleFurniture = 0; cycleFurniture < furnitureSubLabels.length; cycleFurniture++) {
				furnitureSubLabels[cycleFurniture].setVisible(false);	
			}

		} else if (e.getSource() == furnitureLabel) {
			furnitureLabel.setFont(new Font("Segoe Script", Font.PLAIN, 50));
			for (int cycleCat = 0; cycleCat < technologySubLabels.length; cycleCat++) {
				technologySubLabels[cycleCat].setVisible(false);
			}
			for (int cycleSupplies = 0; cycleSupplies < officeSuppliesSubLabels.length; cycleSupplies++) {
				officeSuppliesSubLabels[cycleSupplies].setVisible(false);
			}
			for (int cycleFurniture = 0; cycleFurniture < furnitureSubLabels.length; cycleFurniture++) {
				furnitureSubLabels[cycleFurniture].setVisible(true);	
			}

		}

	}

	//if mouse exited
	@Override
	public void mouseExited(MouseEvent e) {

		//decrease font again
		if (e.getSource() == technologyLabel) { 
			technologyLabel.setFont(new Font("Segoe Script", Font.PLAIN, 40));
		} else if (e.getSource() == officeSuppliesLabel) {
			officeSuppliesLabel.setFont(new Font("Segoe Script", Font.PLAIN, 35));
		} else if (e.getSource() == furnitureLabel) {
			furnitureLabel.setFont(new Font("Segoe Script", Font.PLAIN, 40));
		}


	}

	//if mouse pressed
	@Override
	public void mousePressed(MouseEvent e) {

		//check which button was pressed, and open the corresponding search screen
		for (int cycleCat = 0; cycleCat < technologySubLabels.length; cycleCat++) {
			if (e.getSource() == technologySubLabels[cycleCat]) {

				Frame.search = FinalProjectTest.technology[cycleCat].get(1).getSubCategory();

				System.out.println(FinalProjectTest.technology[cycleCat]);
				Search catSearch = new Search(FinalProjectTest.technology[cycleCat]);
				Frame.mainFrame.getContentPane().remove(Frame.homePanel);
				Frame.mainFrame.getContentPane().remove(Frame.searchPanel);				
				Frame.mainFrame.add(catSearch);
				Frame.mainFrame.setVisible(true);
				Frame.mainFrame.validate();
				Frame.mainFrame.repaint();
			}
		}

		for (int cycleCat = 0; cycleCat < furnitureSubLabels.length; cycleCat++) {
			if (e.getSource() == furnitureSubLabels[cycleCat]) {

				Frame.search = FinalProjectTest.furniture[cycleCat].get(1).getSubCategory();

				Search catSearch = new Search(FinalProjectTest.furniture[cycleCat]);
				Frame.mainFrame.getContentPane().remove(Frame.homePanel);
				Frame.mainFrame.getContentPane().remove(Frame.searchPanel);				
				Frame.mainFrame.add(catSearch);
				Frame.mainFrame.setVisible(true);
				Frame.mainFrame.validate();
				Frame.mainFrame.repaint();
			}
		}


		for (int cycleCat = 0; cycleCat < officeSuppliesSubLabels.length; cycleCat++) {
			if (e.getSource() == officeSuppliesSubLabels[cycleCat]) {

				Frame.search = FinalProjectTest.officeSupplies[cycleCat].get(1).getSubCategory();

				Search catSearch = new Search(FinalProjectTest.officeSupplies[cycleCat]);
				Frame.mainFrame.getContentPane().remove(Frame.homePanel);
				Frame.mainFrame.getContentPane().remove(Frame.searchPanel);				
				Frame.mainFrame.add(catSearch);
				Frame.mainFrame.setVisible(true);
				Frame.mainFrame.validate();
				Frame.mainFrame.repaint();
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
