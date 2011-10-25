package restaurant;

/**
 * Class to store all cashier-related code
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
	
	public void addOrder(Order _order)
	{
		OrderManager.getInstance();
		// to be implemented in subclass
		OrderManager.addOrder(_order);
	}

	public int getTotalOrders()
	{
		// to be implemented in subclass
		return 0;
	}
	
	public String getFirstName() { return this.firstName; }
	public String getSurname() { return this.surname; }
	public String getId() { return this.id; }
}
