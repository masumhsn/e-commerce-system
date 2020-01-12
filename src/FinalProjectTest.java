

/*
Name: 
Masum Hasan


Description:
This product is able to find products from a database, given many different parameters. The user from the home screen can choose a category to 
browse through them, or make their own search to find products relevant to the search. However, on top of only relevancy, the program will be
smart in which products it is showing, as it will use user data to help it. For example, as the product is used, the program will learn about the user
and start showing products according to the relative pricepoint the user tends to purchase from, how far the product is from the user, favorite or 
frequent categories, and more.

Features:
As the user clicks on products, the program learns about it. For example, if the user clicks on a copier, it will compare the price of it to the 
rest of the copiers, to determine the price range the user purchased it in. After clicks, an image is made of the user so when the user searches things,
products shown are weighted so the products in the users frequent price range is shown higher. Similarly top categories for the home page are learned.
As well, location and distance to the product and user are another factor

If something is slightly misspelled, such as enevlope, the program is typically able determine what the user was trying to say, and still gives relevant
searches

Multiple accounts can be made and the user data is specific to each user, so multiple users can have personalized searches

Major Skills:
Use of JSoup and webpage scraping. A URL was created by my program using the search of the user, and JSoup was used to scrape the webpage.
From here, I looked through the webpage data to find what I wanted, including links and images

Use of the Bing Maps API. This was used to determine the distance between the user and the product. A JSON response was given, and I looked through it
for keywords in order to get the distance

Use of the Apache Excel API. This was used to take the data from my database, a spreadsheet, and get it into my program. A typical csv couldn't be used
because many of the cells included commas

Manipulation of CSVs. CSVs were used to store user data, and I was able to read in and load this data, as well as manipulate certain parts of it,
such as when the user image was being changed based on pricepoint, category poitns, and more
 
Areas of Concern:
The program requires internet connection
Since for every search, the program needs to make upwards of 15 searches, this takes a lot of time, and therefore it takes a long time for the page to 
open. When someone logs in to go to the home page, makes a search, or something similar, the user may have to wait many seconds for the page to open up
as my JSoup API makes many searches and scrapes HTML data to display the pictures

Online sources were used to learn how to use some of the APIs, and how the documentation works, where I found some lines for executing the API to get data,
but the code should be fairly unique 
*/



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JFrame;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FinalProjectTest {

	//arraylists for each big category
	static ArrayList <Product>[] technology = new ArrayList[4];
	static ArrayList<Product>[] furniture = new ArrayList[4];
	static ArrayList<Product>[] officeSupplies = new ArrayList[9];

	//arraylist for all products
	static ArrayList <Product> productList  = new ArrayList <Product>();

	//filename of database	
	private static final String FILE_NAME = "res/NewDatabase.xlsx";
	
	//array of names
	static String [] categoryNames = new String[] {"Phones", "Accessories", "Copiers", "Machines", "Chairs", "Tables", "Bookcases", "Furnishings", "Binders", "Supplies", "Appliances", "Storage", "Art", "Paper", "Envelopes", "Labels", "Fasteners"};
	//array with the average price to each, to be calculated later
	static double [] categoryPriceAvg = new double[17];



	//main method
	/**
	 * @author: Masum
	 */
	public static void main (String[] args) {

		//open the home screen
		new HomeScreen();
		
	}

	//method for getting average price of each category
	/**
	 * @author: Masum
	 */
	public static void setupPricepoints () {

		//cycle through technology
		for (int cycleCats = 0; cycleCats < technology.length; cycleCats ++) {

			//add to total price in a subcategory
			double totalCat = 0;
			for (int i = 0; i < technology[cycleCats].size(); i++) {

				totalCat += technology[cycleCats].get(i).getPrice();

			}
			
			//divide by size to get average price
			categoryPriceAvg[cycleCats] = totalCat / technology[cycleCats].size();

		}

		//cycle through furniture
		for (int cycleCats = 0; cycleCats < furniture.length; cycleCats ++) {

			//add to total price in a subcategory
			double totalCat = 0;
			for (int i = 0; i < furniture[cycleCats].size(); i++) {

				totalCat += furniture[cycleCats].get(i).getPrice();

			}

			//divide by size to get average price
			categoryPriceAvg[cycleCats + 4] = totalCat / furniture[cycleCats].size();

		}

		//cycle through office supplies
		for (int cycleCats = 0; cycleCats < officeSupplies.length; cycleCats ++) {

			//add to total price in the subcategory
			double totalCat = 0;
			for (int i = 0; i < officeSupplies[cycleCats].size(); i++) {

				totalCat += officeSupplies[cycleCats].get(i).getPrice();

			}
			
			//get average
			categoryPriceAvg[cycleCats + 4 + 4] = totalCat / officeSupplies[cycleCats].size();

		}

		//cycle through entire list of products
		for (int cycleProds = 0; cycleProds < productList.size(); cycleProds++) {
			
			int categoryNum = 0;
			
			//get the category
			for (int i = 0; i < 17; i++) {
				if (productList.get(cycleProds).getSubCategory().contains(categoryNames[i])) {
					categoryNum = i;
				}
			}
			
			//set the relative pricepoint of the object
			double pricepoint = productList.get(cycleProds).getPrice() / categoryPriceAvg[categoryNum];
			productList.get(cycleProds).setPricepoint(pricepoint);
			
		}
		

	}

	//method for loading in database
	/**
	 * @author: Masum
	 */
	public static void loadDatabase () {

		//initialize all lists
		for(int i=0; i<technology.length; i++){

			technology[i]=new ArrayList<Product>();

		}

		for(int i=0; i<officeSupplies.length; i++){

			officeSupplies[i]=new ArrayList<Product>();

		}

		for(int i=0; i<furniture.length; i++){

			furniture[i]=new ArrayList<Product>();

		}

		try {

			//get database
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			
			//create iterator to cycle through spreadsheet
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();

			//while there is more data
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				int cellCount = 0;

				//create and initialize all variables
				String shipMode = "";
				String customerType = "";
				String city = "";
				String state = "";
				String country = "";
				String productID = "";
				String category = "";
				String subCategory = "";
				String name = "";
				int quantity = 0;
				double price = 0;
				double rating = 0;
				int ratingCount = 0;

				//while there is more data
				while (cellIterator.hasNext()) {

					//get current cell
					Cell currentCell = cellIterator.next();

					//add value to appropriate variable
					switch (cellCount) {
					case 0:
						shipMode = currentCell.getStringCellValue();
						break;
					case 1:
						customerType = currentCell.getStringCellValue();
						break;
					case 2:
						city = currentCell.getStringCellValue();
						break;
					case 3:
						state = currentCell.getStringCellValue();
						break;
					case 4:
						country = currentCell.getStringCellValue();
						break;
					case 5:
						productID = currentCell.getStringCellValue();
						break;
					case 6:
						category = currentCell.getStringCellValue();
						break;
					case 7:
						subCategory = currentCell.getStringCellValue();
						break;
					case 8:
						name = currentCell.getStringCellValue();
						break;
					case 9:
						quantity = (int)currentCell.getNumericCellValue();
						break;
					case 10:
						price =  currentCell.getNumericCellValue();
						break;
					case 11:
						rating =  currentCell.getNumericCellValue();
					case 12:
						ratingCount = (int) currentCell.getNumericCellValue();

					}

					cellCount++;

				}
				
				//create product, add it to arraylist of products
				productList.add(new Product(shipMode, customerType, city, state, country, productID, category, subCategory, name, quantity, price, rating, ratingCount, 0, 0, 0, 1));    

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		//shuffle the product list, so when using for the first time, and the program doesn't know anything about you,
		//the products are random
		Collections.shuffle(productList);

	}
	
	//read in from large arraylist into arraylist for each category
	/**
	 * @author: Masum
	 */
	public static void loadCategoryLists () {

		//cycle through list
		for (int cycleList = 0; cycleList < productList.size(); cycleList++) {

			//cycle through categories
			for (int cycleCat = 0; cycleCat < technology.length; cycleCat++) {

				//add if proper category
				if (productList.get(cycleList).getSubCategory().contains(categoryNames[cycleCat])) {

					technology[cycleCat].add(productList.get(cycleList));

				}


			}

			//cycle through categories
			for (int cycleCat = 0; cycleCat < furniture.length; cycleCat++) {

				//add if proper category
				if (productList.get(cycleList).getSubCategory().contains(categoryNames[cycleCat + 4])) {

					furniture[cycleCat].add(productList.get(cycleList));

				}

			}

			//cycle through categories
			for (int cycleCat = 0; cycleCat < officeSupplies.length; cycleCat++) {

				//add if proper category
				if (productList.get(cycleList).getSubCategory().contains(categoryNames[cycleCat + 8])) {
					officeSupplies[cycleCat].add(productList.get(cycleList));

				}

			}


		}

		//call method to set pricepoint in each product
		setupPricepoints();

	}


}
