

// Java Library imports
import managers.CashierManager;
import managers.CookManager;
import managers.CustomerManager;
import members.Cashier;
import members.Cook;
import members.Customer;
import clients.DisplayClient;
import utils.Utils;

/**
 * A main class to kick everything off
 *
 *	TODO implement receipt - possibly separate class, output to file
 * TODO change singleton implementation to use enum? 
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Main
{		
	/**
	 * The kick-off point...
	 * @param args
	 */
	public static void main(String[] args)
	{		
		// Populate the system
		addCooks();
		addCashiers();
		addCustomers();
		
		initialiseGUI();
		
		logInUsers();
	}

	private static void initialiseGUI()
	{
		System.out.println("Main.initialiseGUI");
		
		DisplayClient displayClient = new DisplayClient();
	}

	/**
	 * Randomly chooses a few cooks/cashiers to log in
	 */
	private static void logInUsers()
	{		
		CookManager.getInstance();
		CashierManager.getInstance();
		
		for (int i = 0; i < CashierManager.getNumberOfCashiers(); i++)
			CashierManager.getCashier(i).logIn();
				
		for (int j = 0; j < CookManager.getNumberOfCooks(); j++)
			CookManager.getCook(j).logIn();
	}

	/**
	 * Create a few new cooks
	 */
	private static void addCooks()
	{			
		CookManager.getInstance();
		CookManager.addCook(new Cook("Raymond", "Slater", Utils.generateUniqueId("CO")));
		CookManager.addCook(new Cook("Laura", "Conner", Utils.generateUniqueId("CO")));
		CookManager.addCook(new Cook("Grace", "Stafford", Utils.generateUniqueId("CO")));
		CookManager.addCook(new Cook("Joe", "Stevens", Utils.generateUniqueId("CO")));
		CookManager.addCook(new Cook("Paul", "McBride", Utils.generateUniqueId("CO")));
	}
	
	/**
	 * Create a few new cashiers
	 */
	private static void addCashiers()
	{
		CashierManager.getInstance();
		CashierManager.addCashier(new Cashier("Emma", "Fitzgerald", Utils.generateUniqueId("CA")));
		CashierManager.addCashier(new Cashier("John", "Price", Utils.generateUniqueId("CA")));
		CashierManager.addCashier(new Cashier("Sarah", "Simpson", Utils.generateUniqueId("CA")));
		CashierManager.addCashier(new Cashier("Steve", "Spears", Utils.generateUniqueId("CA")));
		CashierManager.addCashier(new Cashier("Nicole", "Black", Utils.generateUniqueId("CA")));
	}
	
	/**
	 * Create a few new customers
	 */
	private static void addCustomers()
	{		
		CustomerManager.getInstance();
		CustomerManager.addCustomer(new Customer("Geoff", "Spence", Utils.generateUniqueId("CU")));
		CustomerManager.addCustomer(new Customer("Pete", "James", Utils.generateUniqueId("CU")));
		CustomerManager.addCustomer(new Customer("Fred", "Stevens", Utils.generateUniqueId("CU")));
		CustomerManager.addCustomer(new Customer("Gill", "Phillips", Utils.generateUniqueId("CU")));
		CustomerManager.addCustomer(new Customer("Alex", "Parker", Utils.generateUniqueId("CU")));
	}
}
