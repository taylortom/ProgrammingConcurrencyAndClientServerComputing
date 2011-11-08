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
	private boolean run = true;
	
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
		while(this.run)
		{			
			this.addOrder(OrderManager.createRandomOrder(this));

			try { Thread.sleep(5000); }
			catch (InterruptedException e) { }
		}
	}
	
	public void stop()
	{
		this.run  = false;
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
	
	public void deliverOrder()
	{
		System.out.println("cashier[" + this.getSurname() + "].deliverOrder: " + this.getTotalOrders());
		try { Thread.sleep(DELIVERY_TIME); }
		catch (InterruptedException e) { }
	}
}
