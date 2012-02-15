package members;

import clients.CookClient;
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
	
	// reference to the client GUI
	private CookClient client;
	
	private boolean endShift = false;
	
	/**
	 * Constructor
	 */
	public Cook(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);
	}
	
	@Override
	protected void initGUI()
	{
		if(this.client == null) this.client = new CookClient(this);
		else System.out.println("Cook.initGUI: Error client non-null");
	}
	
	/**
	 * Main loop
	 */
	@Override
	public void run()
	{			
		int sleepMultiplier = 1;
		
		while(this.loggedIn())
		{				
			// don't log out until the current order's finished
			if(!endShift) this.currentOrder = OrderManager.getInstance().getOrder();
			else super.logOut();
			
			if (this.currentOrder != null)
			{
				if(this.client != null) this.client.update("Cooking");
				
				this.currentOrder.setOrderStatus(OrderStatus.inProgress);
				//System.out.println("Cook[" + this.getSurname() + "] got order..." + this.currentOrder.getId());

				// sleep to imitate preparation of food
				try
				{ 
					Thread.sleep(this.currentOrder.calculatePreparationTime()); 
					OrderManager.getInstance().setOrderCooked(this.currentOrder);
					this.orders.add(this.currentOrder.getId());
					sleepMultiplier = 1;
				}
				catch (InterruptedException e) { System.out.println("Cook.run: InterruptedException"); }				
			}
			else 
			{		
				if(this.client != null) this.client.update("Waiting");
				
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
	 * Logs the employee out of the system
	 */
	public void logOut()
	{
		System.out.println("Cook.logOut: " + this.getFirstName() + " " + this.getSurname());
		this.endShift = true;
	}
	
	/**
	 * Returns the current order
	 * @return current order
	 */
	public Order getCurrentOrder()
	{
		return this.currentOrder;
	}
	
	/**
	 * Returns the number of orders taken
	 * @return orders taken
	 */
	public int getTotalOrders()
	{
		if(this.orders != null) return this.orders.size();
		else return 0;
	}
}
