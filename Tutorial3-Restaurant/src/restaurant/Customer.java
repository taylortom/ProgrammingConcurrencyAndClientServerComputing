package restaurant;

/**
 * Class to store customer-related stuff
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Customer
{
	private String _name = "";
	private String  _id = "";	
	
	private Order[] previousOrders;
	
	private LoyaltyScheme loyaltyScheme;
	
	/**
	 * Constructor
	 */
	public Customer()
	{
		// TODO Customer constructor
	}
	
	/**
	 * Returns the total number of orders
	 * placed by the customer
	 * @return
	 */
	public int totalOrderCount() 
	{
		return previousOrders.length;
	}
}
