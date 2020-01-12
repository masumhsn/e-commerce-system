
//object for product 
public class Product {

	//create fields for a product
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
	int ratingCount;
	double relevancyScore;
	double userScore;
	double totalScore;
	double pricepoint;
	
	//create constructor method 
	/**
	 * @author: Masum
	 * @param: all fields of the product
	 */
	public Product(String shipMode, String customerType, String city, String state, String country, String productID,
			String category, String subCategory, String name, int quantity, double price, double rating,
			int ratingCount, double relevancyScore, double userScore, double totalScore, double pricepoint) {
		super();
		this.shipMode = shipMode;
		this.customerType = customerType;
		this.city = city;
		this.state = state;
		this.country = country;
		this.productID = productID;
		this.category = category;
		this.subCategory = subCategory;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.rating = rating;
		this.ratingCount = ratingCount;
		this.relevancyScore = relevancyScore;
		this.userScore = userScore;
		this.totalScore = totalScore;
		this.pricepoint = pricepoint;
	}
	
	//create getters and setters
	public String getShipMode() {
		return shipMode;
	}
	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public double getRelevancyScore() {
		return relevancyScore;
	}
	public void setRelevancyScore(double relevancyScore) {
		this.relevancyScore = relevancyScore;
	}
	public double getUserScore() {
		return userScore;
	}
	public void setUserScore(double userScore) {
		this.userScore = userScore;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public double getPricepoint() {
		return pricepoint;
	}
	public void setPricepoint(double pricepoint) {
		this.pricepoint = pricepoint;
	}
	
	//create tostring
	@Override
	public String toString() {
		return "Product [shipMode=" + shipMode + ", customerType=" + customerType + ", city=" + city + ", state="
				+ state + ", country=" + country + ", productID=" + productID + ", category=" + category
				+ ", subCategory=" + subCategory + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", rating=" + rating + ", ratingCount=" + ratingCount + ", relevancyScore=" + relevancyScore
				+ ", userScore=" + userScore + ", totalScore=" + totalScore + ", pricepoint=" + pricepoint + "]";
	}
	
	
	
	
	
	
}
