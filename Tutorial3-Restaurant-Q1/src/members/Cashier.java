package members;

import clients.CashierClient;
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
	
	//	reference to the client GUI
	private CashierClient client;
	
	/**
	 * Constructor
	 */
	public Cashier(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);
	}
	
	@Override
	protected void initGUI()
	{
		if(this.client == null) this.client = new CashierClient(this);
		else System.out.println("Cashier.initGUI: Error client non-null");
	}
	
	@Override
	public void run()
	{
		while(this.loggedIn())
		{			
			this.addOrder(OrderManager.getInstance().createRandomOrder(this));
			
			int sleepAmount = (Utils.generateRandomNumber(4)+3)*1000;
			
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
		OrderManager.getInstance().addOrder(_order);
		this.orders.add(_order.getId());
		if(this.client != null) this.client.update("Added order");
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
