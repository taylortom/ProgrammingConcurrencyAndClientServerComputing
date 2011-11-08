package restaurant;

import java.util.ArrayList;

import utils.ErrorLog;
import utils.ErrorLog.Error;
import utils.Utils;

/**
 * Class to contain cook info
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Cook extends Member implements Runnable
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
	
	@Override
	public void run()
	{	
		// TODO Cook.run: implement log-in system
		
		int sleepMultiplier = 1;
		
		while(true)
		{				
			OrderManager.getInstance();
			this.currentOrder = OrderManager.getOrder();
			
			if (this.currentOrder != null)
			{
				System.out.println("Cook[" + this.getSurname() + "] got order..." + this.currentOrder.getId());

				// sleep to imitate preparation of food
				try{ Thread.sleep(this.currentOrder.calculatePreparationTime()); }
				catch (InterruptedException e) 
				{ 
					System.out.println("Cook.run: InterruptedException");
					// TODO Cook.run: ErrorLog.addMessage
				}
				
				OrderManager.getInstance();
				OrderManager.setOrderCompleted(this.currentOrder);
				System.out.println("Cook[" + this.getSurname() + "] completed order..." + this.currentOrder.getId());
				
				sleepMultiplier = 1;
			}
			else 
			{
				//System.out.println("Cook[" + this.getSurname() + "] oops...no orders available");
				
				try { Thread.sleep(Utils.generateRandomNumber(1000*sleepMultiplier)); }
				catch (InterruptedException e)
				{
					ErrorLog.getInstance().addMessage("Cook", "run", "InterruptedException", ErrorLog.Error.ERROR);
				}
				
				sleepMultiplier += 2;
			}
		}
	}
	
	@Override
	public int getTotalOrders()
	{
		return this.completedOrders.size();
	}
}
