import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

//label for product being shown
public class ProductLabel extends JLabel implements MouseListener{
	
	JLabel productPic = new JLabel("");
	JLabel productName = new JLabel();
	JLabel productCategory = new JLabel();
	JLabel productLocation = new JLabel();
	JLabel productSubCategory = new JLabel();
	JLabel productPrice = new JLabel();
	JLabel productRating = new JLabel();
	
	Product productGlobal;
	
	/**
	 * @author: Masum
	 * @param: the product being shown
	 * @param: the name of the url for the image
	 */
	public ProductLabel (Product product, String urlString) throws IOException {
		
		//setup label
		setBackground(new Color(130, 200, 150));
		setOpaque(true);
		
		//make the product visible further
		productGlobal = product;
		
		//setup label
		setBounds(0, 0, 350, 150);
		setLayout(null);
		addMouseListener(this);
		setToolTipText(product.getName());
		
		//if a picture does exist
		if (!urlString.contains("none")) {
			
			//add that image to the label
			URL url = new URL(urlString);
			 Image image = ImageIO.read(url);
			Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			productPic.setIcon(new ImageIcon(resizedImage));
			
		}
		
		//setup picture
		productPic.setBounds(200, 50, 100, 100);
		productPic.setVisible(true);
		add(productPic);
		
		//setup all other labels in the bigger label
		productName.setText(product.getName());
		productName.setBounds(0, 0, 350, 50);
		productName.setVisible(true);
		productName.setFont(new Font("Segoe Script", Font.BOLD, 15));
		add(productName);

		productSubCategory.setText(product.getSubCategory());
		productSubCategory.setBounds(0, 37, 250, 50);
		productSubCategory.setVisible(true);
		productSubCategory.setFont(new Font("Segoe Script", Font.PLAIN, 15));

		add(productSubCategory);

		productPrice.setText("$" + Double.toString(product.getPrice()));
		productPrice.setBounds(120, 60, 100, 50);
		productPrice.setVisible(true);
		productPrice.setFont(new Font("Segoe Script", Font.BOLD, 15));
		add(productPrice);

		productRating.setText(Double.toString(product.getRating()) + " stars");
		productRating.setBounds(0, 74, 250, 50);
		productRating.setVisible(true);
		productRating.setFont(new Font("Segoe Script", Font.PLAIN, 15));
		add(productRating);

		
		productLocation.setText(product.getCountry() + ", " + product.getCity());
		productLocation.setBounds(0, 111, 250, 50);
		productLocation.setVisible(true);
		productLocation.setFont(new Font("Segoe Script", Font.PLAIN, 15));
		add(productLocation);
		
		setVisible(true);
		
	}
	
	//find a link on google for the user to be able to purchase it
	/**
	 * @author: Masum
	 * @param: name of the search term being looked for
	 */
	public static String findLink (String search) {

		//string for start of the url for a bing search
		String searchStart = "https://www.bing.com/search?q=";

		//full url including start of url and the search query
		String searchURL = searchStart + URLEncoder.encode(search);

		//setup and initialize links
		Elements links = null;

		try {			
			//use Jsoup API and connect to the webpage
			links = Jsoup.connect(searchURL).get().select("li.b_algo h2 a");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//get the first image off the search
		String openLink = links.get(0).absUrl("href");
		
		//return the image linl
		return openLink;

	}

	//method to open link
	/**
	 * @author: Masum
	 * @param: the url for the link to be opened
	 */
	public static void openLink (String openLink) {

		//if supported
		if(Desktop.isDesktopSupported()) {
			try {
				//open url
				Desktop.getDesktop().browse(new URI(openLink));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}

	}

	//method for making alterations to the user profile
	/**
	 * @author: Masum
	 */
	public void changeUserProfile () {
		
		//get temporary value of scores to be changed
		int [] tempCategoryScores = CreateAccount.userArray.get(LoginScreen.currentUser).getCategoryScores();
		
		//create variable for product number
		int prodNum = 0;
		
		//cycle through product categories
		for (int i = 0; i < FinalProjectTest.categoryNames.length; i++) {
			if (FinalProjectTest.categoryNames[i].contains(productGlobal.getSubCategory())) {
				
				//get product index
				prodNum = i;				
			}
		}
		
		//whichever product was clicked, add a point to it
		tempCategoryScores[prodNum] += 1;
		
		//update the users category scores
		CreateAccount.userArray.get(LoginScreen.currentUser).setCategoryScores(tempCategoryScores);
		
		//get the pricepoint of the product, and sample size
		double tempPricepoint = productGlobal.getPricepoint();
		int tempPricepointSize = CreateAccount.userArray.get(LoginScreen.currentUser).getPricePointSize();
		
		//get the total number of price including all the sample size
		double totalAverages = tempPricepoint + (productGlobal.getPricepoint() * (tempPricepointSize ));
		
		//calculate the new average
		tempPricepoint = (double)(tempPricepoint + (CreateAccount.userArray.get(LoginScreen.currentUser).getPricePoint() * (tempPricepointSize ))) / (tempPricepointSize + 1);

		//increment the sample size
		tempPricepointSize++;

		//update pricepoint and pricepoint size
		CreateAccount.userArray.get(LoginScreen.currentUser).setPricePoint(tempPricepoint);
		CreateAccount.userArray.get(LoginScreen.currentUser).setPricePointSize(tempPricepointSize);

		//write this data back into the user file
		rewriteUser();
		
	}
	
	//method which rewrites the user file using updated information about pricepoint, size, category scores
	/**
	 * @author: Masum
	 */
	public void rewriteUser () {
		
		//create new file to overwrite the old one if it already exists
			try {
				CreateAccount.fileArray[LoginScreen.currentUser].createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//create writer
			FileWriter fWriter = null;
			try {
				fWriter = new FileWriter(CreateAccount.fileArray[LoginScreen.currentUser]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter pWriter = new PrintWriter(fWriter);

			//get the file
			User tempUser = CreateAccount.userArray.get(LoginScreen.currentUser);
			
			//write in the pricepoint and sample size
			pWriter.write(tempUser.getPricePoint() + "");
			pWriter.write("," + tempUser.getPricePointSize());
						
			//get the array for category scores
			int [] tempCategoryScores = CreateAccount.userArray.get(LoginScreen.currentUser).getCategoryScores();

			//rewrite the scores into the array
			for (int i = 0; i < 17; i++) {
				
				pWriter.write("," + tempCategoryScores[i]);
				
			}
			pWriter.close();

		
	}

	//if mouse clicked
	@Override
	public void mouseClicked(MouseEvent arg0) {

		//update profile
		changeUserProfile();
		//open link for product clicked
		openLink(findLink(productGlobal.getName() + " +best buy+ amazon +kijiji+ staples"));

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
