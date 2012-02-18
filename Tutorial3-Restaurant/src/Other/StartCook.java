package other;

import java.io.*;
import java.net.Socket;

import datatypes.DataPacket;
import other.Constants.Function;
import members.Cook;

/**
 * 
 * Starts a random cook
 *
 * @author Tom
 * @version 0.1
 * @history Feb 16, 2012: Created class
 */
public class StartCook implements Runnable
{
	private String host;
	private int port;

	private Cook cook;

	public static void main(String[] args)
	{
		new Thread(new StartCook()).start();
	}

	public StartCook()
	{
		// set the server details		
		setServerDetails();
	}

	private void getCook()
	{
		try
		{
			// the client socket
			Socket socket = new Socket(this.host, this.port);

			// create the output streams   			
			BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());  
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			// send the data
			DataPacket packet = new DataPacket(Function.GET_COOK);
			oos.writeObject(packet);
			oos.flush();

			// create the input streams
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(bis);

			// read the data
			packet = (DataPacket)ois.readObject();
			this.cook = packet.cook;

			// close connections
			bis.close();
			ois.close();
			bos.close();  
			oos.close();
			socket.close();  
		}
		catch(Exception e) { System.out.println("StartCook.getCook: Error exception " + e.getMessage()); }

		if(this.cook != null) 
		{
			this.cook.setServerDetails(this.host, this.port);
			this.cook.logIn();
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
			System.out.println("Initialising Cook");

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
						System.out.println("Server settings confirmed");
					}
				}
			} 
			catch (Exception e) { System.out.println("Exception reading input"); }
		}
	}

	@Override
	public void run()
	{
		while(this.cook == null) this.getCook();
	}
}