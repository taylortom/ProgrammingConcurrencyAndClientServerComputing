package managers;

// Java imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import datatypes.Menu;
import datatypes.MenuItem;
import datatypes.Order;

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
	private static final int ORDER_LIMIT = 10;

	private static List<Order> processingOrders = Collections.synchronizedList(new ArrayList<Order>());

	// the OrderManager instance
	private static OrderManager instance = null;
	
	private static int orderCount = 1; 

	/*
	 * The orders waiting to be cooked
	 * essentially a random access Queue
	 */
	private static List<Order> pendingOrders = Collections.synchronizedList(new ArrayList<Order>());
	// The orders currently being cooked
	private static List<Order> completedOrders = Collections.synchronizedList(new ArrayList<Order>());

	/**
	 * Returns the instance of the CashierManager
	 * @return the CashierManager instance
	 */
	public static synchronized OrderManager getInstance() 
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
		_order.setId("OR-" + orderCount);
		orderCount++;
		pendingOrders.add(_order);
		_order.createReceipt();
		System.out.println("OrderManager-Cashier[" + _order.getCashier().getSurname() + "] added order: " + _order.getId() + "..." + _order.calculatePreparationTime()/1000 + "s prep time");
		
		// stop cashiers after specified number of orders
		if(orderCount >= ORDER_LIMIT && ORDER_LIMIT != 0) for (int i = 0; i < CashierManager.getNumberOfCashiers(); i++) { CashierManager.getCashier(i).logOut(); }
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
	
	/**
	 * Looks for an order by its id
	 * @return the order
	 */
	public static Order getOrder(String _id)
	{		
		// first check the processing orders
		for (int i = 0; i < processingOrders.size(); i++)
			if(processingOrders.get(i).getId() == _id) return processingOrders.get(i);
		
		// then check the pending orders
		for (int j = 0; j < pendingOrders.size(); j++)
			if(pendingOrders.get(j).getId() == _id) return pendingOrders.get(j);
		
		// finally check the completed orders
		for (int k = 0; k < completedOrders.size(); k++)
			if(completedOrders.get(k).getId() == _id) return completedOrders.get(k);
		
		// order not found, so display error, and return null
		System.out.println("OrderManager.getOrder: Error no such order found");
		return null;
	}
	
	public static synchronized void setOrderCooked(Order _order)
	{		
		_order.setOrderCooked();
		processingOrders.remove(_order);
		completedOrders.add(_order);
	}
	
	public static void setOrderDelivered(Order _order)
	{		
		_order.setOrderDelivered();
	}
	
	public static synchronized Order createRandomOrder(Cashier _cashier)
	{
		int count = Utils.generateRandomNumber(5);
		if(count == 0) count = 1;
				
		MenuItem[] orderItems = new MenuItem[count];
		
		for (int i = 0; i < count; i++)
		{
			orderItems[i] = Menu.getInstance().getItem(Utils.generateRandomNumber(Menu.getInstance().getNumberOfItems()));
		}
		
		return new Order(orderItems, _cashier, CustomerManager.getRandomCustomer());
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected OrderManager() { System.out.println("-- ONLY " + ORDER_LIMIT + " ORDERS WILL BE TAKEN --"); }
}
