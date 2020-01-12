import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//screen to show and make searches
public class Search extends JPanel implements ActionListener {

	//create array of labels to be displayed
	public static JLabel labelArray[] = new JLabel[12];

	//create button to move to previous screen
	JButton back = new JButton ("BACK");

	//create font
	private Font font2 = new Font("Sylfaen", Font.PLAIN, 35);

	//create first constructor for query search
	/**
	 * @author: Masum
	 */
	public Search () {

		//setup frame
		setBounds(0, 0, 1200, 1000);
		setLayout(null);
		setBackground(new Color (240, 250, 255));
		setOpaque(true);


		//setup back button
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.setBounds(10, 10, 100, 76);
		add(back);
		back.setFont(font2);
		back.addActionListener(this);

		//load the database
		FinalProjectTest.loadDatabase();

		//call method to get search relevancy based on user query
		getSearchRelevancy();

		//get total score
		getTotalScore();

		//sort the array into the highest scoring product at the top
		SortScores sortScores = new SortScores();
		Collections.sort(FinalProjectTest.productList, sortScores);

		//if it isn't a relevant search, hugely decrease the score
		for (int i = 12; i < FinalProjectTest.productList.size(); i++) {
			FinalProjectTest.productList.get(i).setTotalScore(FinalProjectTest.productList.get(i).getTotalScore() - 1000000);
		}

		//get the user relevancy for the top 12 products
		getUserRelevancy(FinalProjectTest.productList, 12);

		//get total score
		getTotalScore();

		//sort arraylist once again with best scoring at the top
		sortScores = new SortScores();
		Collections.sort(FinalProjectTest.productList, sortScores);

		getTopResults();

		//cycle through the first column
		for (int i = 0; i < 4; i++) {

			//if score is enough
			if (FinalProjectTest.productList.get(i).getTotalScore() > 15) {

				//setup and add label
				add (labelArray[i]);
				labelArray[i].setLocation(50, i * 200 + 100);
			}
		}

		//cycle through second and third column
		for (int i = 4; i < 8; i++) {
			if (FinalProjectTest.productList.get(i).getTotalScore() > 15) {
				add (labelArray[i]);
				labelArray[i].setLocation(410, (i * 200) - 800 + 100);
			}
		}

		for (int i = 8; i < 12; i++) {
			if (FinalProjectTest.productList.get(i).getTotalScore() > 15) {
				add (labelArray[i]);
				labelArray[i].setLocation(780, (i * 200) - 1600 + 100);
			}
		}

		setVisible(true);
	}

	//create second constructor for if a category button is clicked
	/**
	 * @author: Masum
	 * @param: arraylist of the product category being found
	 */
	public Search (ArrayList <Product> categoryList) {

		//setup panel
		setBounds(0, 0, 1200, 1000);
		setLayout(null);
		setBackground(new Color (240, 250, 255));
		setOpaque(true);


		//setup back button
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.setBounds(10, 10, 100, 76);
		add(back);
		back.setFont(font2);
		back.addActionListener(this);

		//load database
		FinalProjectTest.loadDatabase();

		//get total score
		getTotalScore();

		//cycle through products
		for (int i = 0; i < FinalProjectTest.productList.size(); i++) {

			//if in the proper category, add hugely to score
			if (FinalProjectTest.productList.get(i).getSubCategory().contains(Frame.search)) {
				FinalProjectTest.productList.get(i).setTotalScore(FinalProjectTest.productList.get(i).getTotalScore() + 100000);
				System.out.println(FinalProjectTest.productList.get(i).getSubCategory() + " fjdksajfdksla ");

			}
		}

		//reset total score
		getTotalScore();

		//sort by maximum scoring on top
		SortScores sortScores = new SortScores();
		Collections.sort(FinalProjectTest.productList, sortScores);

		//if not in the top 12 most relevant
		for (int i = 12; i < FinalProjectTest.productList.size(); i++) {

			//hugely remove points
			FinalProjectTest.productList.get(i).setTotalScore(FinalProjectTest.productList.get(i).getTotalScore() - 1000000);
		}

		//get user relevancy for top 12 products
		getUserRelevancy(FinalProjectTest.productList, 12);

		//get total score
		getTotalScore();

		//sort by highest score on top
		sortScores = new SortScores();
		Collections.sort(FinalProjectTest.productList, sortScores);

		//reset top results
		getTopResults();

		//add labels onto the screen
		for (int i = 0; i < 4; i++) {
			if (FinalProjectTest.productList.get(i).getTotalScore() > 15) {
				add (labelArray[i]);
				labelArray[i].setLocation(50, i * 200 + 100);
				System.out.println(labelArray[i].getSize());				
			}
		}
		for (int i = 4; i < 8; i++) {
			if (FinalProjectTest.productList.get(i).getTotalScore() > 15) {
				add (labelArray[i]);
				labelArray[i].setLocation(410, (i * 200) - 800 + 100);
			}
		}

		for (int i = 8; i < 12; i++) {
			if (FinalProjectTest.productList.get(i).getTotalScore() > 15) {
				add (labelArray[i]);
				labelArray[i].setLocation(780, (i * 200) - 1600 + 100);
			}
		}

		setVisible(true);

	}


	//method for getting serach relevancy
	/**
	 * @author: Masum
	 */
	public static void getSearchRelevancy () {

		//get serach query
		String search = Frame.search;

		//reset all scores
		for (int i = 0; i < FinalProjectTest.productList.size(); i++) {

			FinalProjectTest.productList.get(i).setUserScore(0);
			FinalProjectTest.productList.get(i).setRelevancyScore(0);
			FinalProjectTest.productList.get(i).setTotalScore(0);

		}		

		//cycle through search by sublenth, going from top down
		for (int subLength = search.length() ; subLength > 1; subLength--) {

			//cycle through index for which the search is in
			//these two loops, will cycle through all of the possible subwords
			//such as for phone, it will check:
			//phone, phon, hone, pho, hon, one, ph, ho, on, ne
			for (int subIndex = 0; subIndex < search.length() - subLength + 1; subIndex++) {

				//get the substring
				String substringSearch = search.substring(subIndex, subLength + subIndex);

				//if contains, add points
				for (int cycleProducts = 0; cycleProducts < FinalProjectTest.productList.size(); cycleProducts++) {

					//if in the name, add more points
					if (FinalProjectTest.productList.get(cycleProducts).getName().contains(substringSearch)) {
						FinalProjectTest.productList.get(cycleProducts).setRelevancyScore(FinalProjectTest.productList.get(cycleProducts).getRelevancyScore() + (subLength + 1));
					}					

					//if in the category, and least points
					if (FinalProjectTest.productList.get(cycleProducts).getCategory().contains(substringSearch)) {
						FinalProjectTest.productList.get(cycleProducts).setRelevancyScore(FinalProjectTest.productList.get(cycleProducts).getRelevancyScore() + ((subLength + 1)) * .6);
					}

					//if in the subcategory, add some points
					if (FinalProjectTest.productList.get(cycleProducts).getSubCategory().contains(substringSearch)) {
						FinalProjectTest.productList.get(cycleProducts).setRelevancyScore(FinalProjectTest.productList.get(cycleProducts).getRelevancyScore() + ((subLength + 1)) * .8);
					}

				}

			}

		}

	}

	//get relevancy to the specific user
	/**
	 * @author: Masum
	 * @param: a list of either all products, or a list of products in one category
	 * @param: number of products wanted to be found
	 */
	public static void getUserRelevancy(ArrayList <Product> list, int num) {

		//sort the scores
		SortScores sortScores = new SortScores();
		Collections.sort(list, sortScores);

		//cycle through products in the number of total products wanted
		for (int cycleProds = 0; cycleProds < num; cycleProds++) {

			//get the score for the closeness to the pricepoint from the user and product
			double userScore = (6 - Math.abs(CreateAccount.userArray.get(LoginScreen.currentUser).getPricePoint() - list.get(cycleProds).getPricepoint())) * 15;

			//add onto scores
			list.get(cycleProds).setUserScore(list.get(cycleProds).getUserScore() + userScore);

		}

		//sort the scores
		sortScores = new SortScores();
		Collections.sort(list, sortScores);

		//cycle through the top number of products
		for (int cycleProds = 0; cycleProds < num; cycleProds++) {

			//get and add score based on the rating
			double rating = list.get(cycleProds).getRating() * 5;
			list.get(cycleProds).setUserScore(list.get(cycleProds).getUserScore() + rating);	
		}

		//sort the scores
		sortScores = new SortScores();
		Collections.sort(list, sortScores);

		//cycle through products
		for (int cycleProds = 0; cycleProds < num; cycleProds++) {

			//get and add score for the distance to the product
			double dist =( getDistance(list.get(cycleProds).getCity(), CreateAccount.userArray.get(LoginScreen.currentUser).getLocation()) / 300);
			list.get(cycleProds).setUserScore(list.get(cycleProds).getUserScore() + dist);	

		}

		//sort scores
		sortScores = new SortScores();
		Collections.sort(list, sortScores);

	}

	//method for getting the distance from the user to the product
	/**
	 * @author: Masum
	 * @param: the starting location for distance, or user
	 * @param: the ending location for distance, or product
	 */
	public static double getDistance(String city1, String city2) {

		//take out all spaces in the name
		String from = city1.replaceAll("\\s+","");
		String to =   city2.replaceAll("\\s+","");

		//create and setup url
		URL url = null;

		try {
			//access the url to return the wanted data
			url = new URL("http://dev.virtualearth.net/REST/V1/Routes/Driving?o=xml&wp.0=" + from + "&wp.1="+ to + "&avoid=minimizeTolls&key=AhD1RcTPiE3i3XeCIWr50LIEiVXH_hGI8TmvsbQzgMz3prFzsKkHpsw1A5KRixKf ");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//setup connection
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//get data from link
		try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//setup variables for the incoming data
		String line, outputString = "";

		//use a buffered reader to read in data from link
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			try {
				//cycle through the lines to grab all the data
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
			} catch (IOException e) {

				//if route not possible, such as ocean blocking it, default to high distance
				return 10000;
			}

		} catch (IOException e) {

			//if route not possible, default to high distance
			return 10000;
		}

		//create variable for distance
		double dist = 10000;

		//cycle through full json response, looking for keyword travel distance
		for (int i = 0; i < outputString.length() - 16; i++) {
			if (outputString.substring(i, i + 16).contains("<TravelDistance>")) {

				//if found, extract the number for the distance and break out of the loop
				String distString = outputString.substring(i + 16, i + 16 + 5);
				distString= distString.replaceAll("\\D+","");
				dist = Double.parseDouble(distString);
				break;
			}

		}

		//return the distance value
		return dist;

	}

	//method for getting the highest scoring results
	/**
	 * @author: Masum
	 */
	public void getTopResults () {

		//create arrays for the product and product names
		String topResultNames [] = new String [12];
		Product topResults[] = new Product [12];

		//cycle through top 12 products
		for (int i = 0; i < 12; i++) {

			//get the top 12
			topResults[i] = FinalProjectTest.productList.get(i);
			topResultNames[i] = topResults[i].getName();

			//call method to setup label to appropriate product
			setLabel(topResults[i], topResults[i].getName(), i);

		}

	}


	//method for getting total score
	/**
	 * @author: Masum
	 */
	public void getTotalScore() {

		//cycle through all products
		for (int i = 0; i < FinalProjectTest.productList.size(); i++) {

			//add up all the scores into the total scores
			FinalProjectTest.productList.get(i).setTotalScore(FinalProjectTest.productList.get(i).getTotalScore() + FinalProjectTest.productList.get(i).getRelevancyScore() + FinalProjectTest.productList.get(i).getUserScore());

		}

	}

	//method for setting up label
	/**
	 * @author: Masum
	 * @param: the product being shown
	 * @param: the search term used to find the product
	 * @param: the number of which category is being found
	 */

	public static void setLabel(Product product, String search, int resultNum) {

		//get search query
		search = URLEncoder.encode(search);

		//create full website url for the search for the image
		String websiteURL = "https://www.bing.com/images/search?q=" + search;

		//create string for the url of the image
		String finalImageURL = "";

		try {

			//Connect to the website and get the html
			Document doc = Jsoup.connect(websiteURL).get();

			//Get all elements with img tag ,
			Elements img = doc.getElementsByTag("img");


			int cycleUrls = 0;
			int cycleResultNum = 0;

			//cycle until data with the th? keyword is found
			do {

				//if there is still data remaining
				if (cycleUrls < img.size()) {

					//get the url
					finalImageURL = img.get(cycleUrls).absUrl("src");
					cycleUrls++;
				} else {

					//otherwise make it so there is no url
					finalImageURL = "none";
					break;
				}

			} while (!finalImageURL.contains("th?"));

			cycleResultNum++;

		} catch (IOException ex) {
			System.err.println("There was an error");
		}

		//add to label array
		try {
			labelArray [resultNum] = new ProductLabel(product, finalImageURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	//if action performed
	@Override
	public void actionPerformed(ActionEvent e) {

		//if back button pressed
		if (e.getSource() == back) {

			//go to previous screen
			FinalProjectTest.loadDatabase();
			FinalProjectTest.loadCategoryLists();
			Frame.mainFrame.dispose();
			new Frame();

		}

	} 

}
