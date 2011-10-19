package restaurant;

import java.lang.reflect.Array;

/**
 * A class to store order information
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Order
{
	// the customer who placed the order
	public Customer customer = null;
	// the cashier who took the order
	private Cashier cashier = null;
	// the cook cooking(!) the order
	private Cook cook = null;
	
	private double total;
	
	// an array of strings with each order
	// TODO create a menu item class
	private String[] order;
	
	private enum orderStatus
	{
		processing,
		inProgress,
		completed;
	}
	
	/**
	 * Constructor
	 */
	public Order()
	{
		// TODO Order constructor
	}
	
	public double total()
	{
		return total;
	}
}
