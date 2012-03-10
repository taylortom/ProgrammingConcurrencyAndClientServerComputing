package other;

import managers.*;
import members.*;
import utils.Utils;

/**
 * Handles adding test data to the program
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Setup
{		
	/**
	 * Create a few new cooks
	 */
	public static void addCooks()
	{			
		CookManager.getInstance().addCook(new Cook("Waldy", "Waldron", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Laura", "Tyrell", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Jonny", "Bell", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Joe", "Valentine", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Paul", "Stanyer", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Gav", "McMaster", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Rozh", "Barzinji", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Bhav", "Mistry", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Ben", "Thomas", Utils.generateUniqueId(Constants.COOK_PREFIX)));
		CookManager.getInstance().addCook(new Cook("Jason", "Wells", Utils.generateUniqueId(Constants.COOK_PREFIX)));
	}
	
	/**
	 * Create a few new cashiers
	 */
	public static void addCashiers()
	{
		CashierManager.getInstance().addCashier(new Cashier("Dani", "Hamilton", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("James", "Spencer", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Chris", "Davies", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Eve", "Cooper", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("James", "Gamble", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Luke", "Everitt", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Sam", "Fisher", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Ana", "Amaral", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Sarah", "Chetwyn", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
		CashierManager.getInstance().addCashier(new Cashier("Joel", "Herber", Utils.generateUniqueId(Constants.CASHIER_PREFIX)));
	}
	
	/**
	 * Create a few new customers
	 */
	public static void addCustomers()
	{		
		CustomerManager.getInstance().addCustomer(new Customer("James", "Spencer", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Pete", "James", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Fred", "Stevens", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Gill", "Phillips", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Alex", "Parker", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Steve", "Spears", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Roger", "Cadenhead", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Ashley", "Rennay", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Steve", "Spark", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
		CustomerManager.getInstance().addCustomer(new Customer("Tyler", "Hilton", Utils.generateUniqueId(Constants.CUSTOMER_PREFIX)));
	}
}
