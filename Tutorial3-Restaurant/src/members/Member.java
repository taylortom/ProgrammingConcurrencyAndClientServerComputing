package members;

import datatypes.Order;
import managers.OrderManager;

/**
 * Low level class to store basic details
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Member
{
	private String firstName = "";
	private String surname = "";
	private String id = "";
		
	/**
	 * Constructor
	 */
	public Member(String _firstName, String _surname, String _id)
	{
		this.firstName = _firstName;
		this.surname = _surname;
		this.id = _id;
	}
	
	/**
	 * Adds an order to the system
	 * @param order to add
	 */
	public void addOrder(Order _order)
	{
		OrderManager.getInstance();
		OrderManager.addOrder(_order);
	}

	/**
	 * returns the total number of orders
	 * @return total orders
	 */
	public int getTotalOrders()
	{
		// to be implemented in subclass
		return 0;
	}
	
	public String getFirstName() { return this.firstName; }
	public String getSurname() { return this.surname; }
	public String getId() { return this.id; }
}
