package restaurant;

import java.util.ArrayList;

/**
 * Class to store all cashier-related code
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Cashier extends Member implements Runnable
{
	ArrayList<String> ordersTaken = new ArrayList<String>();
	
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
		while(true)
		{			
			try { Thread.sleep(1000); }
			catch (InterruptedException e) { }
			
			this.addOrder(OrderManager.getRandomOrder());
			// TODO Cashier: place order
			// TODO Cashier: take order (sleep)
		}
	}
	
	public void addOrder(Order _order)
	{
		super.addOrder(_order);
		this.ordersTaken.add(_order.getId());
	}

	public int getTotalOrders()
	{
		return this.ordersTaken.size();
	}
}
