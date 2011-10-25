package restaurant;

// Java imports
import java.util.ArrayList;

// Java Library imports
import utils.ErrorLog;
import utils.Utils;

/**
 * Class to deal with all of the orders
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class OrderManager
{
	private static ArrayList<Order> processingOrders = new ArrayList<Order>();
	// TODO iterate over a queue?
	// private static Queue pendingOrders;
	// essentially a Queue, but needed basic iteration
	private static ArrayList<Order> pendingOrders = new ArrayList<Order>();
	private static ArrayList<Order> completedOrders = new ArrayList<Order>();

	private static ArrayList<Order> testOrders = new ArrayList<Order>();
	
	// the OrderManager instance
	private static OrderManager instance = null;

	/**
	 * Returns the instance of the CashierManager
	 * @return the CashierManager instance
	 */
	public static OrderManager getInstance() 
	{
		if(instance == null) { instance = new OrderManager(); }
		return instance;
	}
	
	/**
	 * Adds the passed order to the pending orders queue
	 * @param _order
	 */
	public static synchronized void addOrder(Order _order)
	{
		pendingOrders.add(_order);
	}
	
	/**
	 * Gets the next order from pendingOrders, and adds to processingOrders
	 * @return the next order
	 */
	public static synchronized Order getOrder()
	{
		if (pendingOrders.size() != 0)
		{
			Order order = pendingOrders.get(0);
			processingOrders.add(order);
			pendingOrders.remove(0);
			return order;
		}
		else return null;
	}
	/*public static Order getOrder()
	{
		Order order = null; 
		
		try 
		{ 
			order = (Order)pendingOrders.remove(); 
		}
		catch(NoSuchElementException e)
		{
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("OrderManager", "getOrder", e.getMessage(), ErrorLog.Error.ERROR); 
		}
		
		processingOrders.add(order);
		return order;
	}*/
	
	/**
	 * Looks for an order by its id
	 * @return the next order
	 */
	public static synchronized Order getOrder(String _id)
	{		
		// first check the processing orders
		for (int i = 0; i < processingOrders.size(); i++)
		{
			if(processingOrders.get(i).getId() == _id) return processingOrders.get(i);
		}
		
		// then check the pending orders
		for (int j = 0; j < pendingOrders.size(); j++)
		{
			if(pendingOrders.get(j).getId() == _id) return pendingOrders.get(j);
		}
		
		// finally check the completed orders
		for (int k = 0; k < completedOrders.size(); k++)
		{
			if(completedOrders.get(k).getId() == _id) return completedOrders.get(k);
		}
		
		// order not found, so log an error, and return null
		ErrorLog el = ErrorLog.getInstance();
		el.addMessage("OrderManager", "getOrder", "No such order found", ErrorLog.Error.ERROR);
		
		return null;
	}
	
	public static synchronized void setOrderCompleted(Order _order)
	{
		getOrder(_order.getId()).setOrderCompleted();
		processingOrders.remove(0);
		completedOrders.add(_order);
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected OrderManager() {	}

	
	/**
	 * THE FOLLOWING CODE IS USED FOR TEST PURPOSES ONLY
	 * 
	 */
	public static void addTestOrders()
	{
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
		testOrders.add(new Order(null, CashierManager.getRandomCashier(), CustomerManager.getRandomCustomer()));
	}
	
	public static Order getRandomOrder()
	{
		return testOrders.get(Utils.generateRandomNumber(testOrders.size()));
	}
}
