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
	private boolean loggedIn = false;
	
	/**
	 * Constructor
	 */
	public Employee(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);
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
	}
	
	/**
	 * Logs the employee out of the system
	 */
	public void logOut()
	{
		loggedIn = false;
		
		Thread thread = new Thread(this);
		thread.stop();
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
