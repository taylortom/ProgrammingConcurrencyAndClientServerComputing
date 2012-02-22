package members;

import java.io.*;

import other.Constants;
import other.Constants.Function;

import clients.CashierClient;
import utils.Utils;

import datatypes.DataPacket;
import datatypes.Order;
import datatypes.Order.OrderStatus;

/**
 * Class to store all cashier-related code
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Cashier extends Employee implements Serializable
{
	private static final long serialVersionUID = 1L;
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
		try
		{
		while(this.loggedIn())
		{			
			DataPacket packet = new DataPacket(Function.CREATE_RANDOM_ORDER);
			packet.cashier = this;
			packet = this.communicateWithServer(packet);
			if(packet == null) continue;

			this.addOrder(packet.order);

			int sleepAmount = (Utils.generateRandomNumber(4)+3)*1000;

			try { Thread.sleep(sleepAmount); }
			catch (InterruptedException e) { }

			// poll the order manager for any undelivered orders floating about 
			DataPacket undeliveredOrderPacket = new DataPacket(Function.GET_UNDELIVERED_ORDER);
			DataPacket undeliveredOrderReturnPacket = this.communicateWithServer(undeliveredOrderPacket);
			if(undeliveredOrderReturnPacket.order != null) this.deliverOrder(undeliveredOrderReturnPacket.order);
		}
		}
		catch(Exception e) { System.out.println("Cashier.run: Error exception " + e.getMessage()); }
	}

	/**
	 * Adds the passed order to the system
	 * @param order to add
	 */
	public void addOrder(Order _order)
	{		  
		DataPacket packet = new DataPacket(Function.ADD_ORDER);
		packet.order = _order;
		this.communicateWithServer(packet);

		this.orders.add(_order.getId());

		System.out.println("Cashier[" + this.getFirstName().charAt(0) + "." + this.getSurname() + "] added new order");
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
			Thread.sleep(Constants.DELIVERY_TIME);

			DataPacket packet = new DataPacket(Function.SET_ORDER_DELIVERED);
			packet.order = _order;
			this.communicateWithServer(packet);

			this.client.update("Delivered order");
			System.out.println("Cashier[" + this.getFirstName().charAt(0) + "." + this.getSurname() + "] delivered order: " + _order.getId());
		}
		catch (Exception e) { System.out.println("Cashier.deliverOrder: Error exception " + e.getMessage()); }
	}

	@Override
	public void logIn()
	{		
		// let the server know the cashier's logged in
		DataPacket packet = new DataPacket(Function.EMPLOYEE_LOG_IN);
		packet.cashier = this;
		packet = this.communicateWithServer(packet);

		super.logIn();
	}

	@Override
	public void logOut()
	{
		// let the server know the cashier's logged in
		DataPacket packet = new DataPacket(Function.EMPLOYEE_LOG_OUT);
		packet.cashier = this;
		packet = this.communicateWithServer(packet);		

		super.logOut();
	}
}