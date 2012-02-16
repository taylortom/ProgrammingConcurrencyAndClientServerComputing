package network;

import java.io.*;
import java.net.*;

import other.Constants;
import other.Setup;

import clients.ServerClient;
import datatypes.DataPacket;
import datatypes.DataPacket.DataType;

import managers.CashierManager;
import managers.CookManager;
import managers.OrderManager;
import members.Cashier;


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
	private ServerSocket serverSocket;
	
	public static void main(String args[])
	{
		new Thread(new Server()).start();
	}
	
	/**
	 * Constructor
	 */
	public Server()
	{
		this.serverSocket = null;
		
		this.initData();
		this.initGUI();
		
		this.setServerDetails();
	}
	
	private void initData()
	{
		Setup.addCashiers();
		Setup.addCooks();
		Setup.addCustomers();
	}
	
	private void initGUI()
	{
		if(this.client == null) this.client = new ServerClient();	
		else System.out.println("ServerClient.initGUI: Error client non-null");
		
		orderManager.setServer(this);
		orderManager.initGUI();
	}
	
	private void setServerDetails()
	{
		// keep looping until the user confirms details are correct
		boolean confirmed = false;
		
		while(!confirmed)
		{
			System.out.println("Initialising Cashier");
			
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
	
	public void output(String _output)
	{
		client.update(_output);
	}
	
   /**
    * Get the root web page from a website
    * 
    * @param the server name (e.g. "www.bton.ac.uk").
    * @param the server port number. The standard port number for web servers is 80.
    */
	public void listenAtPort(String _server, int _port) 
	{
		System.out.println("Server.listenAtPort");
		
		try
		{
			// set up the sockets
			serverSocket = new ServerSocket(_port);
			Socket socket = serverSocket.accept();
			serverSocket.setSoTimeout(Constants.CONNECTION_TIMEOUT);
			socket.setSoTimeout(Constants.CONNECTION_TIMEOUT);

			// create the streams
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());  
			BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(bis);
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			// get the input
			Object inputObject = ois.readObject();
			DataPacket dataPacket = (DataPacket)inputObject;
			boolean returnObject = false;
			Object object = null;
			
			System.out.print("Recieved " + dataPacket.type.toString());
			if(dataPacket.type == DataType.FUNCTION) System.out.print(": " + dataPacket.function.toString());
			System.out.println();
			
			switch(dataPacket.type)
			{
				case ORDER:
					break;
				case COOK:
					break;
				case CASHIER:
					break;
				case FUNCTION:
					switch(dataPacket.function)
					{
						case GET_CASHIER:
							Cashier cashier = cashierManager.getRandomCashier();
							// if the cashier's already logged in, get another 
							while(cashier.loggedIn()) object = cashierManager.getRandomCashier();
							object = cashier;
							returnObject = true;
							break;
					}
					break;
			}
			
			if(returnObject)
			{
				// send the output
				oos.writeObject(object);
				oos.flush();
				System.out.println("Object sent successfully");
			}
			
			// close the connection
			bis.close(); 
			ois.close();
			bos.close();
			oos.close();
			socket.close();  
			serverSocket.close();
		}
		catch (Exception e) { System.out.println("Server.listenAtPort: Error exception " + e.getMessage()); }
	}
	
	@Override
	public void run()
	{
		while(online) 
		{
			listenAtPort(host, port);
			
			// sleep for testing
			try { Thread.sleep(5000); }
			catch (InterruptedException e) { }
		}
	}
}