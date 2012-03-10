package members;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Low level class to store basic details
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Member implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String firstName = "";
	private String surname = "";
	private String id = "";
	
	protected ArrayList<String> orders = new ArrayList<String>();
		
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
