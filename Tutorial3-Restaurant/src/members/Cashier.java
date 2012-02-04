package members;

import java.util.ArrayList;

import utils.Utils;

import datatypes.Order;
import datatypes.Order.OrderStatus;


import managers.OrderManager;

/**
 * Class to store all cashier-related code
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Cashier extends Employee
{
	private static final int DELIVERY_TIME = 3000;
	private ArrayList<String> ordersTaken = new ArrayList<String>();
	
	/**
	 * Constructor
	 */
	public Cashier(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);
	}
	
	@Override
	public void run()
	{
		// TODO Cashier.run: implement log-in system
		while(this.loggedIn())
		{			
			this.addOrder(OrderManager.getInstance().createRandomOrder(this));
			
			int sleepAmount = (Utils.generateRandomNumber(4)+1)*1000;
			
			try { Thread.sleep(sleepAmount); }
			catch (InterruptedException e) { }
		}
	}
	
	/**
	 * Adds the passed order to the system
	 * @param order to add
	 */
	public void addOrder(Order _order)
	{
		super.addOrder(_order);
		this.ordersTaken.add(_order.getId());
	}

	/**
	 * Returns the number of orders taken
	 * @return orders taken
	 */
	public int getTotalOrders()
	{
		return this.ordersTaken.size();
	}
	
	/**
	 * Delivers an order to a customer
	 * @param _order to be delivered
	 */
	public void deliverOrder(Order _order)
	{
		if(_order.getOrderStatus() != OrderStatus.cooked)
		{
			System.out.println("Cashier.deliverOrder: Error order " + _order.getId() + " not completed " + _order.getOrderStatus());
			return;
		}
			
		try 
		{ 
			Thread.sleep(DELIVERY_TIME);
			OrderManager.getInstance().setOrderDelivered(_order);
		}
		catch (InterruptedException e) { }
	}
}
