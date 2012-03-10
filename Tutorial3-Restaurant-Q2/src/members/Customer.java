package members;

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
	/**
	 * Constructor
	 */
	public Customer(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);		
	}
	
	/**
	 * Adds an order to the previous orders
	 * @param the order to add
	 */
	public void addOrder(Order _order)
	{
		this.orders.add(_order.getId());
	}
	
	/**
	 * Returns the total number of orders
	 */
	@Override
	public int getTotalOrders() 
	{
		return orders.size();
	}
}
