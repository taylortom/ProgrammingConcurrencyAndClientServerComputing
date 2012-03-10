package managers;

// Java imports
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import other.Constants;
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
	
	// reference to the display client
	private DisplayClient displayClient;


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
		this.displayClient = new DisplayClient();
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
		
		this.displayClient.update();
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
			this.displayClient.update();
			return order;
		}
		else return null;
	}
	
	/**
	 * Looks for an order by its id
	 * @return the order
	 */
	public Order getOrderForDelivery(String _id)
	{		
		for (int k = 0; k < deliveryOrders.size(); k++)
			if(deliveryOrders.get(k).getId().equals(_id.trim())) return deliveryOrders.get(k);
		
		// order not found, so display error, and return null
		System.out.println("OrderManager.getOrderForDelivery: Error no such order found: '" + _id + "'");
		return null;
	}

	/**
	 * Searches the deliveryOrders for any orders which have been
	 * waiting for longer than the specified timout constant 
	 * @return the undelivered order
	 */
	public Order getUndeliveredOrder()
	{
		for (int i = 0; i < deliveryOrders.size(); i++)
		{
			Order order = deliveryOrders.get(i);
			
			Calendar currentTime = Calendar.getInstance();
			
			// work out when the order times out
			Calendar timeoutTime = order.getTimeCooked();
			timeoutTime.add(Calendar.MILLISECOND, Constants.DELIVERY_TIMEOUT);

			if(currentTime.after(timeoutTime)) return order;
		}
		
		return null;
	}
	
	
	/**
	 * Sets the passed order as cooked
	 * @param _order
	 */
	public synchronized void setOrderCooked(Order _order)
	{						
		_order.setOrderStatus(OrderStatus.cooked);
		deliveryOrders.add(_order);

		for (int i = 0; i < processingOrders.size(); i++)
			if(processingOrders.get(i).getId().equals(_order.getId())) processingOrders.remove(i);
		
		this.displayClient.update();		
	}
	
	/**
	 * Sets the passed order as cooked
	 * @param _order
	 */
	public synchronized void setOrderDelivered(Order _order)
	{				
		for (int i = 0; i < deliveryOrders.size(); i++)
			if(deliveryOrders.get(i).getId().equals(_order.getId())) deliveryOrders.remove(i);
		
		completedOrders.add(_order);
		_order.setOrderStatus(OrderStatus.delivered);

		this.displayClient.update();
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
