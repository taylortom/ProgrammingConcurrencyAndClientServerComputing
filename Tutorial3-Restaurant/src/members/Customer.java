package members;

import java.util.ArrayList;

import datatypes.LoyaltyScheme;
import datatypes.Order;


/**
 * Class to store customer-related stuff
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Customer extends Member
{		
	private ArrayList<String> previousOrders = new ArrayList<String>();
	
	private LoyaltyScheme loyaltyScheme;
	
	/**
	 * Constructor
	 */
	public Customer(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);
		
		loyaltyScheme = new LoyaltyScheme();
	}
	
	/**
	 * Adds an order to the previous orders
	 * @param the order to add
	 */
	@Override
	public void addOrder(Order _order)
	{
		super.addOrder(_order);
		this.previousOrders.add(_order.getId());
	}
	
	/**
	 * Returns the total number of orders
	 */
	@Override
	public int getTotalOrders() 
	{
		return previousOrders.size();
	}
}
