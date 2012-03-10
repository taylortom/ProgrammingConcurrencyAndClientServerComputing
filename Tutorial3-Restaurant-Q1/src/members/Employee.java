package members;

/**
 * Class to store all employee-related code
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Employee extends Member implements Runnable
{
	// if the employee is currently logged in
	private boolean loggedIn = false;
	
	/**
	 * Constructor
	 */
	public Employee(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);
	}
	
	/**
	 * Creates the GUI for the employee
	 */
	protected void initGUI()
	{
		// should be overridden in subclass
	}
	
	/**
	 * Connects to the central server
	 * @return whether the employee's connected
	 */
	protected boolean connectToSystem()
	{
		return false;
	}
	
	@Override
	public void run()
	{
		// Should be overridden in subclass
	}
	
	/**
	 * Logs the employee into the system
	 */
	public void logIn()
	{
		loggedIn = true;
		
		Thread thread = new Thread(this);
		thread.start();
		
		initGUI();
	}
	
	/**
	 * Logs the employee out of the system
	 */
	public void logOut()
	{
		System.out.println("Employee.logOut: " + this.getFirstName() + " " + this.getSurname());
		loggedIn = false;
	}
	
	/**
	 * Whether the employee is currently logged in
	 * @return logged in
	 */
	public boolean loggedIn()
	{
		return this.loggedIn;
	}
}
