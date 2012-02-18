package network;

import java.io.*;
import java.net.*;

import other.Constants;

import managers.CashierManager;
import managers.CookManager;
import managers.OrderManager;
import members.Cashier;
import members.Cook;
import datatypes.DataPacket;
import datatypes.Order;

public class Serverlet extends Thread
{
	Socket socket;

	public Serverlet(Socket _socket)
	{
		this.socket = _socket;
		
		// set timeout
		try{ this.socket.setSoTimeout(Constants.CONNECTION_TIMEOUT); }
		catch (Exception e) { System.out.println("Serverlet.Severlet: Error, exception: " + e.getMessage()); }
	}

	@Override
	public void run()
	{
		try
		{
			// create the streams
			BufferedInputStream bis = new BufferedInputStream(this.socket.getInputStream());  
			ObjectInputStream ois = new ObjectInputStream(bis);

			// get the input
			DataPacket dataPacket = (DataPacket)ois.readObject();
			System.out.println("Serverlet recieved DataPacket: " + dataPacket.function.toString());

			switch(dataPacket.function)
			{
				case SET_ORDER_COOKED:
					OrderManager.getInstance().setOrderCooked(dataPacket.order);
					//this.client.update();
					break;

				case SET_ORDER_DELIVERED:
					OrderManager.getInstance().setOrderDelivered(dataPacket.order);
					//this.client.update();
					break;

				case GET_NEXT_ORDER:
					Order nextOrder = OrderManager.getInstance().getOrder();
					dataPacket.order = nextOrder;
					//this.client.update();
					break;
					
				case GET_ORDER:
					Order order = OrderManager.getInstance().getOrderForDelivery(dataPacket.data);
					dataPacket.order = order;
					//this.client.update();
					break;

				case ADD_ORDER:
					OrderManager.getInstance().addOrder(dataPacket.order);
					//this.client.update();
					break;

				case CREATE_RANDOM_ORDER:
					Order randomOrder = OrderManager.getInstance().createRandomOrder(dataPacket.cashier);
					dataPacket.order = randomOrder;
					break;

				case GET_CASHIER:
					Cashier cashier = CashierManager.getInstance().getRandomCashier();
					// if the cashier's already logged in, get another 
					while(cashier.loggedIn()) cashier = CashierManager.getInstance().getRandomCashier();
					dataPacket.cashier = cashier;
					//this.client.update();
					break;

				case GET_COOK:
					Cook cook = CookManager.getInstance().getRandomCook();
					// if the cashier's already logged in, get another 
					while(cook.loggedIn()) cook = CookManager.getInstance().getRandomCook();
					dataPacket.cook = cook;
					//this.client.update();
					break;

				default: 
					System.out.println("Error function not recognised: " + dataPacket.function.toString());
					break;
			}

			if(dataPacket.returnTransmission)
			{
				BufferedOutputStream bos = new BufferedOutputStream(this.socket.getOutputStream());
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(dataPacket);
				oos.flush();
				bos.close();
				oos.close();
			}

			// communication complete, close the connection
			bis.close(); 
			ois.close();
			this.socket.close();			
		}
		catch (Exception e) { System.out.println("Serverlet.run: Error, exception: " + e.getMessage()); }
	}
}
