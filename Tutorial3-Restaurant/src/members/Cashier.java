package members;

import java.io.*;
import java.util.Calendar;

import network.DeliveryServerlet;

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
	private DeliveryServerlet deliveryServerlet;

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
			DataPacket packet = new DataPacket(Function.CREATE_RANDOM_ORDER);
			packet.cashier = this;
			packet = this.communicateWithServer(packet);
			if(packet == null) continue;

			this.addOrder(packet.order);

			int sleepAmount = (Utils.generateRandomNumber(4)+3)*1000;

			try { Thread.sleep(sleepAmount); }
			catch (InterruptedException e) { }


			// poll the order manager for orders not delivered 

			/*// see if the order's passed the timeout
			Calendar timeCooked = order.getTimeCooked();

			if(timeCooked != null)
			{
				Calendar currentTime = Calendar.getInstance();
				timeCooked.add(Calendar.MILLISECOND, Constants.DELIVERY_TIMEOUT);
				if(currentTime.after(timeCooked)) System.out.println("Order timeout, deliver this order");
				{
					System.out.println("Order timeout, deliver this order");
					this.cashier.deliverOrder(order);
				}
			}*/
		}
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
		System.out.println("Cashier.deliverOrder");		

		if(_order.getOrderStatus() != OrderStatus.cooked)
		{
			System.out.println("Cashier.deliverOrder: Error order " + _order.getId() + " not completed " + _order.getOrderStatus());
			return;
		}

		try 
		{ 
			Thread.sleep(Constants.DELIVERY_TIME);

			while(_order.getOrderStatus() != OrderStatus.delivered)
			{
				DataPacket packet = new DataPacket(Function.SET_ORDER_DELIVERED);
				packet.order = _order;
				this.communicateWithServer(packet);

				if(_order.getOrderStatus() != OrderStatus.delivered) Thread.sleep(1000);
			}
			System.out.println("Cashier.deliverOrder -------> 4");
			this.client.update("Delivered order");
		}
		catch (Exception e) { System.out.println("Cashier.deliverOrder: Error exception " + e.getMessage()); }
	}

	public void logOut()
	{
		super.logOut();
		this.deliveryServerlet.goOffline();
	}
}