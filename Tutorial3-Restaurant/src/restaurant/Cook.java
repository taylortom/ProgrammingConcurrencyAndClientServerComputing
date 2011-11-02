package restaurant;

import java.util.ArrayList;

import utils.ErrorLog;
import utils.ErrorLog.Error;

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
		// TODO Cook.run: implement logging system
		while(true)
		{				
			OrderManager.getInstance();
			this.currentOrder = OrderManager.getOrder();
			
			// TODO Cook: cook current order (sleep)
			
			if (this.currentOrder != null)
			{
				System.out.println("Cook[" + this.getSurname() + "] got order..." + this.currentOrder.getId());
				
				// sleep to imitate preparation of food
				int preparationTime = this.currentOrder.calculatePreparationTime();
				try{ Thread.sleep(preparationTime); }
				catch (InterruptedException e){}
				
				OrderManager.getInstance();
				OrderManager.setOrderCompleted(this.currentOrder);
				System.out.println("Cook[" + this.getSurname() + "] completed order..." + this.currentOrder.getId());
			}
			else 
			{
				//System.out.println("Cook[" + this.getSurname() + "] oops...no orders available");
				try { Thread.sleep(1000); }
				catch (InterruptedException e)
				{
					ErrorLog.getInstance().addMessage("Cook", "run", "InterruptedException", ErrorLog.Error.ERROR);
				}
			}
		}
	}
	
	@Override
	public int getTotalOrders()
	{
		return this.completedOrders.size();
	}
}
