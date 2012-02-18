package network;

import java.io.*;
import java.net.*;

import managers.OrderManager;
import clients.ServerClient;
import other.Setup;

/**
 * The remote server which handles the 
 * centralised ordering system
 *
 * @author Tom
 * @version 0.1
 * @history Feb 15, 2012: Created class
 */
public class Server 
{	
	private String host = "";
	private int port = -1;
	
	private boolean online = true;
	private ServerClient client;
	
	public static void main(String args[])
	{
		new Server();
	}
	
	/**
	 * Constructor
	 */
	public Server()
	{		
		this.initData();
		this.initGUI();
		
		this.setServerDetails();
		
		this.acceptConnection(this.host, this.port);
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
		
		OrderManager.getInstance().initGUI();
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
			
			// for the user input
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			// get the host name
			System.out.print("Enter the host: ");
			try { this.host = br.readLine(); } 
			catch (Exception e) { System.out.println("Exception reading input"); }

			// get the port number 
			System.out.print("Enter the port number: ");
			try { this.port = Integer.valueOf(br.readLine()); } 
			catch (Exception e) { System.out.println("Exception reading input"); }

			try 
			{ 
				String response = "";
				
				while(!response.equals("y") && !response.equals("n"))
				{
					// get the user to confirm the details
					System.out.println("Connect to host: " + host + " at port " + port);
					System.out.print("Is this correct? (y/n): ");
					
					response = br.readLine();
					
					if(response.equals("y"))
					{
						confirmed = true;
						System.out.println("Server settings confirmed.");
					}
				}
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
    * Accepts connection at the specified port/host
    * @param the host name
    * @param the port number 
    */
	public void acceptConnection(String _server, int _port) 
	{		
		try
		{
			System.out.println("Server.acceptConnection: " + _server + ":" + _port);
			
			// set up the sockets
			ServerSocket serverSocket = new ServerSocket(_port);

			// accept incoming connections
			while(online) new Serverlet(serverSocket.accept()).start();
			  
			// close the server socket
			serverSocket.close();
		}
		catch (Exception e) { System.out.println("Server.listenAtPort: Error exception: " + e.getMessage()); }
	}
}