package managers;

// Java imports
import java.util.ArrayList;

import members.Customer;


//Java Library imports
import utils.Utils;

/**
 * Class to deal with all of the customers
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class CustomerManager
{
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	// the CustomerManager instance
	private static CustomerManager instance = null;

	/**
	 * Returns the instance of the CustomerManager
	 * @return the CustomerManager instance
	 */
	public static synchronized CustomerManager getInstance() 
	{
		if(instance == null) { instance = new CustomerManager(); }
		return instance;
	}
	
	/**
	 * Adds the passed Customer to the customers list
	 * @param _order
	 */
	public static synchronized void addCustomer(Customer _customer)
	{
		if(Utils.arraySearch(customers, _customer).equals(false)) customers.add(_customer);
	}
	
	/**
	 * Gets a random Customer from the list using Utils.generateRandomNumber
	 * @return a random Customer
	 */
	public static synchronized Customer getRandomCustomer()
	{
		int randomNumber = Utils.generateRandomNumber(customers.size()-1);
		return customers.get(randomNumber);
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected CustomerManager() {	}
}
