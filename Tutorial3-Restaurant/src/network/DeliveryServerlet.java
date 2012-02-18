package network;

import java.net.*;
import other.Constants.Function;
import datatypes.DataPacket;
import datatypes.Order;
import members.Cashier;

public class DeliveryServerlet extends Thread
{
	private int port = 4444;

	private MulticastSocket socket;

	// reference to the cashier
	private Cashier cashier;

	private boolean online = true;

	public DeliveryServerlet(Cashier _cashier)
	{
		this.cashier = _cashier;

		try
		{
			System.out.println("DeliveryServerlet.DeliveryServerlet: Server started");
			this.socket = new MulticastSocket(port);
			InetAddress group = InetAddress.getByName("230.0.0.0");
			this.socket.joinGroup(group);
		}
		catch (Exception e) { System.out.println("DeliveryServerlet.DeliveryServerlet: Error, exception: " + e.getMessage()); }
	}

	private Order getOrderForDelivery(String _order)
	{
		System.out.println("DeliveryServerlet.getOrderForDelivery");
		
		// get order from server
		DataPacket packet = new DataPacket(Function.GET_ORDER);
		packet.data = _order;
		Order order = cashier.communicateWithServer(packet).order;
		
		System.out.println("DeliveryServerlet.getOrderForDelivery: " + order.getId());

		if(order.getCashier().getId().equals(this.cashier.getId())) 
		{
			System.out.println("My order!! collect");
			this.cashier.deliverOrder(order);
		}

		return null;
	}

	@Override
	public void run() 
	{
		while (this.online) 
		{
			try 
			{				
				if(socket == null) 
				{
					Thread.sleep(1000);
					continue; 
				}

				byte[] order = new byte[100];

				// receive request
				DatagramPacket packet = new DatagramPacket(order, order.length);
				socket.receive(packet);
				//String orderID = new String();
				
				// perform the necessary actions
				this.getOrderForDelivery(packet.getData().toString());
			} 
			catch (Exception e){}// { System.out.println("DeliveryServerlet.run: Error, exception: " + e.getMessage()); }
		}
	}

	public void goOffline()
	{
		this.online = false;
		this.socket.close();
	}
}