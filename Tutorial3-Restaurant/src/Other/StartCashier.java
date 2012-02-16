package other;

import java.io.*;
import java.net.Socket;

import datatypes.DataPacket;
import other.Constants.Function;
import members.Cashier;

/**
 * 
 * Starts a random Cashier
 *
 * @author Tom
 * @version 0.1
 * @history Feb 16, 2012: Created class
 */
public class StartCashier implements Runnable
{
	private String host;
	private int port;
	private Cashier cashier;
	
	public static void main(String[] args)
	{
		new Thread(new StartCashier()).start();
	}
	
	public StartCashier()
	{
		// set the server details		
		setServerDetails();
	}
	
	private void getCashier()
	{
		System.out.println("StartCashier.getCashier");
		
		Cashier tempCashier = null;
		
		try
		{
			// the client socket
			Socket socket = new Socket(host, port);

			// create the output streams   			
			BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());  
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			// send the data
			oos.writeObject(new DataPacket(Function.GET_CASHIER));
			oos.flush();
			
			// create the input streams
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			// read the data
			tempCashier = (Cashier)ois.readObject();
						
			// close connections
			oos.close();  
			bos.close();  
			socket.close();  
		}
		catch(Exception e) { System.out.println("Client.getCashier: Error exception " + e.getMessage()); }
		
		if(tempCashier != null) 
		{
			cashier = tempCashier;
			cashier.logIn();
		}
		else 
		{
			try { Thread.sleep(1000); }
			catch(Exception e) { System.out.println("Client.getCashier: Error exception " + e.getMessage()); }
		}

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

	@Override
	public void run()
	{
		while(cashier == null) this.getCashier();
	}
}