

// Java Library imports
import managers.*;
import members.*;
import clients.*;
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
		CashierClient cashierClient = new CashierClient();
		//CashierClient cashierClient2 = new CashierClient();
	}

	/**
	 * Randomly chooses a few cooks/cashiers to log in
	 */
	private static void logInUsers()
	{		
		CookManager.getInstance();
		CashierManager.getInstance();
		
		for (int i = 0; i < CashierManager.getInstance().getNumberOfCashiers(); i++)
			CashierManager.getInstance().getCashier(i).logIn();
				
		for (int j = 0; j < CookManager.getInstance().getNumberOfCooks(); j++)
			CookManager.getInstance().getCook(j).logIn();
	}

	/**
	 * Create a few new cooks
	 */
	private static void addCooks()
	{			
		CookManager.getInstance().addCook(new Cook("Raymond", "Slater", Utils.generateUniqueId("CO")));
		CookManager.getInstance().addCook(new Cook("Laura", "Conner", Utils.generateUniqueId("CO")));
		CookManager.getInstance().addCook(new Cook("Grace", "Stafford", Utils.generateUniqueId("CO")));
		CookManager.getInstance().addCook(new Cook("Joe", "Stevens", Utils.generateUniqueId("CO")));
		CookManager.getInstance().addCook(new Cook("Paul", "McBride", Utils.generateUniqueId("CO")));
	}
	
	/**
	 * Create a few new cashiers
	 */
	private static void addCashiers()
	{
		CashierManager.getInstance().addCashier(new Cashier("Emma", "Fitzgerald", Utils.generateUniqueId("CA")));
		CashierManager.getInstance().addCashier(new Cashier("John", "Price", Utils.generateUniqueId("CA")));
		CashierManager.getInstance().addCashier(new Cashier("Sarah", "Simpson", Utils.generateUniqueId("CA")));
		CashierManager.getInstance().addCashier(new Cashier("Steve", "Spears", Utils.generateUniqueId("CA")));
		CashierManager.getInstance().addCashier(new Cashier("Nicole", "Black", Utils.generateUniqueId("CA")));
	}
	
	/**
	 * Create a few new customers
	 */
	private static void addCustomers()
	{		
		CustomerManager.getInstance().addCustomer(new Customer("Geoff", "Spence", Utils.generateUniqueId("CU")));
		CustomerManager.getInstance().addCustomer(new Customer("Pete", "James", Utils.generateUniqueId("CU")));
		CustomerManager.getInstance().addCustomer(new Customer("Fred", "Stevens", Utils.generateUniqueId("CU")));
		CustomerManager.getInstance().addCustomer(new Customer("Gill", "Phillips", Utils.generateUniqueId("CU")));
		CustomerManager.getInstance().addCustomer(new Customer("Alex", "Parker", Utils.generateUniqueId("CU")));
	}
}
