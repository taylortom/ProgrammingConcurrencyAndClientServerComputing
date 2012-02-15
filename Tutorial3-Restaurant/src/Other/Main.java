package Other;

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
		addCooks();
		addCashiers();
		addCustomers();
		
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

	/**
	 * Create a few new cooks
	 */
	private static void addCooks()
	{			
		CookManager.getInstance().addCook(new Cook("Waldy", "Waldron", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Laura", "Tyrell", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Jonny", "Bell", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Joe", "Valentine", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Paul", "Stanyer", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Gav", "McMaster", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Rozh", "Barzinji", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Bhav", "Mistry", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Ben", "Thomas", Utils.generateUniqueId(COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Jason", "Wells", Utils.generateUniqueId(COOK_PREFIX)));
	}
	
	/**
	 * Create a few new cashiers
	 */
	private static void addCashiers()
	{
		CashierManager.getInstance().addCashier(new Cashier("Dani", "Hamilton", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("James", "Spencer", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Chris", "Davies", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Eve", "Cooper", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("James", "Gamble", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Luke", "Everitt", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Sam", "Fisher", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Ana", "Amaral", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Sarah", "Chetwyn", Utils.generateUniqueId(CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Joel", "Herber", Utils.generateUniqueId(CASHIER_PREFIX)));
	}
	
	/**
	 * Create a few new customers
	 */
	private static void addCustomers()
	{		
		CustomerManager.getInstance().addCustomer(new Customer("James", "Spencer", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Pete", "James", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Fred", "Stevens", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Gill", "Phillips", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Alex", "Parker", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Steve", "Spears", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Roger", "Cadenhead", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Ashley", "Rennay", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Steve", "Spark", Utils.generateUniqueId(CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Tyler", "Hilton", Utils.generateUniqueId(CUSTOMER_PREFIX)));
	}
}
