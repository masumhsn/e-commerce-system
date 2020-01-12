import java.util.Arrays;

//user object
public class User {

	//create fields for a user
	private String username;
	private String password;
	private String location;
	private double pricePoint;
	private int pricePointSize;
	private int[] categoryScores;
	
	//create constructor for the user
	/**
	 * @author: Masum
	 * @param: all fields for the user
	 */
	public User(String username, String password, String location, double pricePoint, int pricePointSize, int[] categoryScores) {
		super();
		this.username = username;
		this.password = password;
		this.location = location;
		this.pricePoint = pricePoint;
		this.pricePointSize = pricePointSize;
		this.categoryScores = categoryScores;
	}
	
	//create getters and setters for the user
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getPricePoint() {
		return pricePoint;
	}
	public void setPricePoint(double pricePoint) {
		this.pricePoint = pricePoint;
	}
	public int getPricePointSize() {
		return pricePointSize;
	}
	public void setPricePointSize(int pricePointSize) {
		this.pricePointSize = pricePointSize;
	}
	public int[] getCategoryScores() {
		return categoryScores;
	}
	public void setCategoryScores(int[] categoryScores) {
		this.categoryScores = categoryScores;
	}
	
	//create tostring for user
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", location=" + location + ", pricePoint="
				+ pricePoint + ", pricePointSize=" + pricePointSize + ", categoryScores="
				+ Arrays.toString(categoryScores) + "]";
	}
	
	
	
	
}
