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
	private static final int DELIVERY_TIME = 2000;
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
		while(true)
		{			
			try { Thread.sleep(500); }
			catch (InterruptedException e) { }
			
			this.addOrder(OrderManager.createRandomOrder());
		}
	}
	
	public void addOrder(Order _order)
	{
		System.out.println("Cashier added order: " + _order.getId());
		super.addOrder(_order);
		this.ordersTaken.add(_order.getId());
	}

	public int getTotalOrders()
	{
		return this.ordersTaken.size();
	}
	
	public void deliverOrder()
	{
		System.out.println("Cashier.deliverOrder");
		try { Thread.sleep(this.DELIVERY_TIME); }
		catch (InterruptedException e) { }
	}
}
