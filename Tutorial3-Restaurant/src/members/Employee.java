package members;

import java.io.*;
import java.net.Socket;
import datatypes.DataPacket;

/**
 * Class to store all employee-related code
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Employee extends Member implements Runnable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String host;
	private int port;
		
	// if the employee is currently logged in
	private boolean loggedIn = false;
	
	/**
	 * Constructor
	 */
	public Employee(String _firstName, String _surname, String _id)
	{
		super(_firstName, _surname, _id);
	}
	
	/**
	 * Creates the GUI for the employee
	 */
	protected void initGUI()
	{
		// should be overridden in subclass
	}
	
	/**
	 * Sets data relevant for connecting to the servers
	 * @param _host
	 * @param _port
	 */
	public void setServerDetails(String _host, int _port)
	{
		this.host = _host;
		this.port = _port;
	}
	
	/**
	 * Sends a message to the server. Exactly what is sent depends on the message
	 * @param _function
	 */
	public DataPacket communicateWithServer(DataPacket _packet)
	{
		System.out.print("Employee.communicateWithServer: " + _packet.function.toString() + " at " + this.host + ":" + this.port);
				
		try
		{
			System.out.println();
			
			// the client socket
			Socket socket = new Socket(this.host, this.port);

			// create the output streams   			
			BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());  
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			// send the data
			oos.writeObject(_packet);
			oos.flush();
			
			// whether we also need to wait for a response
			if(_packet.returnTransmission)
			{
				// create the input streams
				BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
				ObjectInputStream ois = new ObjectInputStream(bis);

				// read the data
				_packet = (DataPacket)ois.readObject();
				
				bis.close();
				ois.close();
			}
						
			// close connections
			oos.close();  
			bos.close();  
			socket.close();  

			return _packet;
		}
		catch(Exception e) 
		{ 
			System.out.print(" Error exception " + e.getMessage()); 
			System.out.println();
			return null;
		}	
	}
	
	/**
	 * Logs the employee into the system
	 */
	public void logIn()
	{
		initGUI();
		new Thread(this).start();
		loggedIn = true;
	}
	
	/**
	 * Logs the employee out of the system
	 */
	public void logOut()
	{
		System.out.println("Employee.logOut: " + this.getFirstName() + " " + this.getSurname());
		loggedIn = false;
	}
	
	/**
	 * Whether the employee is currently logged in
	 * @return logged in
	 */
	public boolean loggedIn()
	{
		return this.loggedIn;
	}
	
	@Override
	public void run()
	{
		// Should be overridden in subclass
	}
}
