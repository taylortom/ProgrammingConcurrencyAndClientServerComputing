package network;

import java.io.*;
import java.net.*;

import other.Constants;
import other.Setup;

import clients.ServerClient;
import datatypes.DataPacket;
import datatypes.Order;
import datatypes.Order.OrderStatus;

import managers.CashierManager;
import managers.CookManager;
import managers.OrderManager;
import members.Cashier;
import members.Cook;


/**
 * The remote server which handles the 
 * centralised ordering system
 *
 * @author Tom
 * @version 0.1
 * @history Feb 15, 2012: Created class
 */
public class Server implements Runnable
{	
	private String host = "localhost";
	private int port = Constants.COMMUNICATION_PORT;
	
	OrderManager orderManager = OrderManager.getInstance();
	CashierManager cashierManager = CashierManager.getInstance();
	CookManager cookManager = CookManager.getInstance();
	
	private boolean online = true;
	private ServerClient client;
	
	public static void main(String args[])
	{
		new Thread(new Server()).start();
	}
	
	/**
	 * Constructor
	 */
	public Server()
	{		
		this.initData();
		this.initGUI();
		
		this.setServerDetails();
	}
	
	/**
	 * Populates the manager classes with sample data
	 */
	private void initData()
	{
		Setup.addCashiers();
		Setup.addCooks();
		Setup.addCustomers();
	}
	
	/**
	 * Initialises the both the ServerClient and the DisplayClient
	 */
	private void initGUI()
	{
		//if(this.client == null) this.client = new ServerClient();	
		//else System.out.println("ServerClient.initGUI: Error client non-null");
		
		orderManager.setServer(this);
		orderManager.initGUI();
	}
	
	/**
	 * Takes input from the user, and sets the server host/port
	 */
	private void setServerDetails()
	{
		// keep looping until the user confirms details are correct
		boolean confirmed = false;
		
		while(!confirmed)
		{
			System.out.println("Initialising Server");
			
			System.out.print("Enter the host: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try { this.host = br.readLine(); } 
			catch (Exception e) { System.out.println("Exception reading input"); }

			System.out.print("Enter the port number: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			try { this.port = Integer.valueOf(br.readLine()); } 
			catch (Exception e) { System.out.println("Exception reading input"); }

			System.out.println("Connected to host: " + host + " at port " + port);
			
			System.out.print("Is this correct? (y/n): ");
			br = new BufferedReader(new InputStreamReader(System.in));
			try 
			{ 
				String response = br.readLine();				
				if(response.equals("y")) confirmed = true;
				else confirmed = false;
				System.out.println("Server settings confirmed");
			} 
			catch (Exception e) { System.out.println("Exception reading input"); }
		}
	}
	
	/**
	 * Adds the passed string to the sever client output window
	 * @param _output string
	 */
	public void output(String _output)
	{
		client.updateDisplayText(_output);
	}
	
   /**
    * Listens at the specified port for incoming connections.
    * Depending on the input, acts accordingly
    */
	public void listenAtPort(String _server, int _port) 
	{
		System.out.print("Server.listenAtPort: " + _server + ":" + _port);
		
		try
		{
			// set up the sockets
			ServerSocket serverSocket = new ServerSocket(_port);
			Socket socket = serverSocket.accept();
			serverSocket.setSoTimeout(Constants.CONNECTION_TIMEOUT);
			socket.setSoTimeout(Constants.CONNECTION_TIMEOUT);

			// create the streams
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());  
			ObjectInputStream ois = new ObjectInputStream(bis);

			// get the input
			DataPacket dataPacket = (DataPacket)ois.readObject();
			System.out.println(" Recieved DataPacket: " + dataPacket.function.toString());
									
			switch(dataPacket.function)
			{
				case SET_ORDER_COOKED:
					orderManager.setOrderCooked(dataPacket.order);
					//this.client.update();
					break;
					
				case SET_ORDER_DELIVERED:
					orderManager.setOrderDelivered(dataPacket.order);
					//this.client.update();
					break;

				case GET_NEXT_ORDER:
					Order nextOrder = orderManager.getOrder();
					dataPacket.order = nextOrder;
					//this.client.update();
					break;
									
				case ADD_ORDER:
					orderManager.addOrder(dataPacket.order);
					//this.client.update();
					break;
					
				case CREATE_RANDOM_ORDER:
					Order randomOrder = orderManager.createRandomOrder(dataPacket.cashier);
					dataPacket.order = randomOrder;
					break;
	
				case GET_CASHIER:
					Cashier cashier = cashierManager.getRandomCashier();
					// if the cashier's already logged in, get another 
					while(cashier.loggedIn()) cashier = cashierManager.getRandomCashier();
					dataPacket.cashier = cashier;
					//this.client.update();
					break;
					
				case GET_COOK:
					Cook cook = cookManager.getRandomCook();
					// if the cashier's already logged in, get another 
					while(cook.loggedIn()) cook = cookManager.getRandomCook();
					dataPacket.cook = cook;
					//this.client.update();
					break;
					
				default: 
					System.out.println("Error function not recognised: " + dataPacket.function.toString());
					this.output("Error function not recognised: " + dataPacket.function.toString());
					break;
			}
			
			if(dataPacket.returnTransmission)
			{
				BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(dataPacket);
				oos.flush();
				bos.close();
				oos.close();
			}
			
			// communication complete, close the connection
			bis.close(); 
			ois.close();
			socket.close();  
			serverSocket.close();
		}
		catch (Exception e) { System.out.println(" Server.listenAtPort: Error exception: " + e.getMessage()); }
	}
	
	@Override
	public void run()
	{
		while(online) 
		{
			listenAtPort(host, port);
		}
	}
}