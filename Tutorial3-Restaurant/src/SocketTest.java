import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import utils.Utils;

public class SocketTest
{
	/**
	 * A class to test socket communication
	 * @param args
	 */
	public static void main(String[] args)
	{
		String host = "localhost";
		int port = 1999; 
		StringBuffer inString = new StringBuffer();
		System.out.println("SocketTest initialised");
		
		try
		{
			InetAddress address = InetAddress.getByName(host);
			Socket connection = new Socket(address, port);
			
			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
			
			String process = "Calling the Socket Server on " + host + " port " + port + " at " + Utils.generateTimeStamp("dd-mm-yy hh:mm:ss") + (char)13;
			osw.write(process);
			osw.flush();
		}
		catch (IOException i)
		{
			System.out.println("IOException: " + i);
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e);
		}
	}

}
