package managers;

// Java imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import network.Server;
import clients.DisplayClient;
import datatypes.Menu;
import datatypes.MenuItem;
import datatypes.Order;
import datatypes.Order.OrderStatus;
import members.Cashier;
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
	// the OrderManager instance
	private static OrderManager instance = null;
	
	// start the order numbers at 1
	private static int orderCount = 1; 

	// The orders waiting to be cooked
	private static List<Order> pendingOrders = Collections.synchronizedList(new ArrayList<Order>());
	// The orders currently being cooked
	private static List<Order> processingOrders = Collections.synchronizedList(new ArrayList<Order>());
	//	Cooked orders awaiting delivery
	private static List<Order> deliveryOrders = Collections.synchronizedList(new ArrayList<Order>());
	//	Completed orders
	private static List<Order> completedOrders = Collections.synchronizedList(new ArrayList<Order>());
	
	// reference to the Server
	private static Server server;
	
	// reference to the display client
	private static DisplayClient displayClient;


	/**
	 * Returns the instance of the CashierManager
	 * @return the CashierManager instance
	 */
	public static synchronized OrderManager getInstance() 
	{
		if(instance == null) 
		{ 
			instance = new OrderManager();
		}
		return instance;
	}
	
	public void initGUI()
	{
		displayClient = new DisplayClient();
	}
	
	public void setServer(Server _server)
	{
		if(server == null) server = _server;
		else System.out.println("OrderManager.setServer: Error Server already defined");
	}
	
	/**
	 * Adds the passed order to the pending orders queue
	 * @param _order
	 */
	public synchronized void addOrder(Order _order)
	{		
		_order.setId("OR-" + orderCount);
		pendingOrders.add(_order);
		_order.setOrderStatus(OrderStatus.pending);
		_order.calculateTotal();
		_order.createReceipt();
		orderCount++;
		//System.out.println("OrderManager-Cashier[" + _order.getCashier().getSurname() + "] added order: " + _order.getId() + "..." + _order.calculatePreparationTime()/1000 + "s prep time");
		
		displayClient.update();
	}
	
	/**
	 * Gets the next order from pendingOrders, and adds to processingOrders
	 * @return the next order
	 */
	public synchronized Order getOrder()
	{
		if (pendingOrders.size() != 0)
		{
			Order order = pendingOrders.get(0);
			processingOrders.add(order);
			pendingOrders.remove(0);
			displayClient.update();
			return order;
		}
		else return null;
	}
	
	/**
	 * Looks for an order by its id
	 * @return the order
	 */
	public Order getOrder(String _id)
	{		
		// first check the processing orders
		for (int i = 0; i < processingOrders.size(); i++)
			if(processingOrders.get(i).getId() == _id) return processingOrders.get(i);
		
		// then check the pending orders
		for (int j = 0; j < pendingOrders.size(); j++)
			if(pendingOrders.get(j).getId() == _id) return pendingOrders.get(j);
		
		// ... and the orders waiting for delivery
		for (int k = 0; k < deliveryOrders.size(); k++)
			if(deliveryOrders.get(k).getId() == _id) return deliveryOrders.get(k);
		
		// finally check the completed orders
		for (int l = 0; l < completedOrders.size(); l++)
			if(completedOrders.get(l).getId() == _id) return completedOrders.get(l);
		
		// order not found, so display error, and return null
		System.out.println("OrderManager.getOrder: Error no such order found");
		return null;
	}
	
	/**
	 * Sets the passed order as cooked
	 * @param _order
	 */
	public synchronized void setOrderCooked(Order _order)
	{		
		processingOrders.remove(_order);		
		deliveryOrders.add(_order);
		_order.setOrderStatus(OrderStatus.cooked);
		displayClient.update();
		//System.out.println("Order: " + _order.getId() + " cooked" );
		
		/*
		 *  notify the cashier the order's cooked
		 *  If the cashier who took the order isn't available, get another
		 */
		if(_order.getCashier().loggedIn()) _order.getCashier().deliverOrder(_order);
		else 
		{
			Cashier newCashier = CashierManager.getInstance().getAvailableCashier();
			if(newCashier == null) System.out.println("Well this is embarrassing: all of the the waiting staff seem to have left");
			else newCashier.deliverOrder(_order);
		}
	}
	
	/**
	 * Sets the passed order as cooked
	 * @param _order
	 */
	public synchronized void setOrderDelivered(Order _order)
	{		
		//System.out.println("OrderManager: cashier[" + _order.getCashier().getSurname() + "] delivered order: " + _order.getId());
		deliveryOrders.remove(_order);
		completedOrders.add(_order);
		displayClient.update();
		_order.setOrderStatus(OrderStatus.delivered);
	}
	
	/**
	 * Creates a new random order, and adds to the system
	 * @param _cashier taking the order
	 * @return the new order
	 */
	public synchronized Order createRandomOrder(Cashier _cashier)
	{
		int count = Utils.generateRandomNumber(5) + 1;
				
		MenuItem[] orderItems = new MenuItem[count];
		
		for (int i = 0; i < count; i++)
			orderItems[i] = Menu.getInstance().getItem(Utils.generateRandomNumber(Menu.getInstance().getNumberOfItems()));
		
		return new Order(orderItems, _cashier, CustomerManager.getInstance().getRandomCustomer());
	}
	
	/**
	 * Returns an array of pending order ids
	 * @return the list 
	 */
	public String[] getPendingOrders()
	{	
		// create a copy of the list in case an items removed mid-loop
		Object[] pendingOrdersCopy = pendingOrders.toArray();
		
		String[] pendingOrdersArr = new String[pendingOrders.size()];
		
		for (int i = 0; i < pendingOrdersCopy.length; i++) pendingOrdersArr[i] = ((Order) pendingOrdersCopy[i]).getId();
		
		return pendingOrdersArr;
	}
	
	/**
	 * Returns an array of processing order ids
	 * @return the list 
	 */
	public String[] getProcessingOrders()
	{	
		// create a copy of the list in case an items removed mid-loop
		Object[] processingOrdersCopy = processingOrders.toArray();
		
		String[] processingOrdersArr = new String[processingOrders.size()];
		
		for (int i = 0; i < processingOrdersCopy.length; i++) processingOrdersArr[i] = ((Order)processingOrdersCopy[i]).getId();
		
		return processingOrdersArr;
	}
	
	/**
	 * Returns an array of delivery order ids
	 * @return the list 
	 */
	public String[] getDeliveryOrders()
	{		
		// create a copy of the list in case an items removed mid-loop
		Object[] deliveryOrdersCopy = deliveryOrders.toArray();
		
		String[] deliveryOrdersArr = new String[deliveryOrders.size()];
		
		for (int i = 0; i < deliveryOrdersCopy.length; i++) deliveryOrdersArr[i] = ((Order)deliveryOrdersCopy[i]).getId();
		
		return deliveryOrdersArr;
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected OrderManager() { /*System.out.println("-- ONLY " + ORDER_LIMIT + " ORDERS WILL BE TAKEN --");*/ }
}
