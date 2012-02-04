package members;

import java.util.ArrayList;

import datatypes.Order;
import datatypes.Order.OrderStatus;


import managers.OrderManager;

import utils.Utils;

/**
 * Class to contain cook info
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Cook extends Employee
{
	// a reference to the current order
	private Order currentOrder = null;
	
	// the ids of the completed orders
	private ArrayList<String> completedOrders;
	
	/**
	 * Constructor
	 */
	public Cook(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);
	}
	
	/**
	 * Main loop
	 */
	@Override
	public void run()
	{	
		// TODO Cook.run: implement log-in system
		
		int sleepMultiplier = 1;
		
		while(this.loggedIn())
		{				
			OrderManager.getInstance();
			this.currentOrder = OrderManager.getInstance().getOrder();
			
			if (this.currentOrder != null)
			{
				this.currentOrder.setOrderStatus(OrderStatus.inProgress);
				//System.out.println("Cook[" + this.getSurname() + "] got order..." + this.currentOrder.getId());

				// sleep to imitate preparation of food
				try
				{ 
					Thread.sleep(this.currentOrder.calculatePreparationTime()); 
					OrderManager.getInstance().setOrderCooked(this.currentOrder);
					sleepMultiplier = 1;
				}
				catch (InterruptedException e) { System.out.println("Cook.run: InterruptedException"); }				
			}
			else 
			{				
				try { Thread.sleep(Utils.generateRandomNumber(1000*sleepMultiplier)); }
				catch (InterruptedException e)
				{
					System.out.println("Cook.run: Error InterruptedException");
				}
				
				sleepMultiplier += 2;
			}
		}
	}
	
	/**
	 * Returns the number of orders
	 */
	@Override
	public int getTotalOrders()
	{
		return this.completedOrders.size();
	}
}
