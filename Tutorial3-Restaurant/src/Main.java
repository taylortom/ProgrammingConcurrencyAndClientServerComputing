
import other.Constants;
import other.Setup;
import network.Server;
import managers.*;
import members.*;
import utils.Utils;

/**
 * A main class to kick everything off
 *
 * TODO change singleton implementation to use enum? 
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Main
{		
	private static String CASHIER_PREFIX = "CA"; 
	private static String COOK_PREFIX = "CO"; 
	private static String CUSTOMER_PREFIX = "CU"; 
	
	/**
	 * The kick-off point...
	 * @param args
	 */
	public static void main(String[] args)
	{	
		// Initialise the server
		OrderManager.getInstance();
		
		// Populate the system
		Setup.addCooks();
		Setup.addCashiers();
		Setup.addCustomers();
		
		logInUsers();
	}

	/**
	 * Randomly chooses a few cooks/cashiers to log in
	 */
	private static void logInUsers()
	{		
		for (int i = 0; i < Constants.NUMBER_OF_CASHIERS; i++) 
		{
			while(true)
			{
				Cashier cashier = CashierManager.getInstance().getRandomCashier();
				if(!cashier.loggedIn())
				{
					cashier.logIn();
					break;
				}
			}
		}
		
		for (int j = 0; j < Constants.NUMBER_OF_COOKS; j++) 
		{
			while(true)
			{
				Cook cook = CookManager.getInstance().getRandomCook();
				if(!cook.loggedIn())
				{
					cook.logIn();
					break;
				}
			}
		}
	}
}
