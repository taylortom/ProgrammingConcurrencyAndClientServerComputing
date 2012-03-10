package network;

import java.net.*;
import other.Constants.Function;
import datatypes.DataPacket;
import datatypes.Order;
import members.Cashier;

public class DeliveryServerlet extends Thread
{
	private int port = 4444;

	// reference to the cashier
	private Cashier cashier;

	DatagramPacket packet;

	public DeliveryServerlet(Cashier _cashier)
	{
		this.cashier = _cashier;

		try
		{
			System.out.println("DeliveryServerlet.DeliveryServerlet: Server started");


		}
		catch (Exception e) { System.out.println("DeliveryServerlet.DeliveryServerlet: Error, exception: " + e.getMessage()); }
	}

	private Order getOrderForDelivery(String _order)
	{
		// get order from server
		DataPacket packet = new DataPacket(Function.GET_ORDER);
		packet.data = _order;
		Order order = cashier.communicateWithServer(packet).order;

		if(order.getCashier().getId().equals(this.cashier.getId())) this.cashier.deliverOrder(order);

		return null;
	}

	@Override
	public void run() 
	{
		try 
		{		
			// create the socket
			MulticastSocket socket = new MulticastSocket(port);
			InetAddress group = InetAddress.getByName("230.0.0.0");
			socket.joinGroup(group);

			byte[] order = new byte[6];

			// receive request
			packet = new DatagramPacket(order, order.length);

			while (this.cashier.loggedIn()) 
			{
				socket.receive(packet);

				// perform the necessary actions
				this.getOrderForDelivery(new String(packet.getData()));
			} 
			
			socket.close();
		}
		catch (Exception e) {}// System.out.println("DeliveryServerlet.run: Error, exception: " + e.getMessage()); }
	}
}