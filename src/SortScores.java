import java.util.Comparator;

//comparator class
public class SortScores implements Comparator <Product> {

	//create comparator method
	/**
	 * @author: Masum
	 */
	@Override
	public int compare(Product a, Product b) {
		
		return -Double.compare(a.getTotalScore(), b.getTotalScore());
		
	}

}
