package restaurant;

import java.util.ArrayList;

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
		while(true)
		{				
			OrderManager.getInstance();
			this.currentOrder = OrderManager.getOrder();
			
			// TODO Cook: cook current order (sleep)
			
			if (this.currentOrder != null)
			{
				System.out.println("Got an order...");
				// sleep to imitate preparation of food
				//			int preparationTime = order.complexity*1000;
				int preparationTime = 3000;
				try{ Thread.sleep(preparationTime); }
				catch (InterruptedException e){}
				OrderManager.setOrderCompleted(this.currentOrder);
			}
		}
	}
	
	private void setOrderCompleted()
	{
		OrderManager.getInstance();
		this.currentOrder.setOrderCompleted();
	}
	
	@Override
	public int getTotalOrders()
	{
		return this.completedOrders.size();
	}
}
