/**
 * A simple class to test threads
 *
 * @author Tom
 * @version 0.1
 * @history 12.10.2011: Created class
 */
public class LoopThread extends Thread
{
	private String id = ""; 
	private int counter = 0;
	
	public LoopThread(String id)
	{
		this.id = id;
	}

	@Override
	public void run()
	{
		for(int i = 0; i <= 500; i++)
		{			
			try { this.sleep(1); }
			catch (InterruptedException e) { }
			
			System.out.println(id + "- i: " + i + " count: " + getLoopCount(i));
		}
	}
	
	private int getLoopCount(int loop)
	{
		try { this.sleep(1); }
		catch (InterruptedException e) { }
		
		counter++;
		
		return ++loop;
	}
}
