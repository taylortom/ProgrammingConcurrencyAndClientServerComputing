/**
 * Class description
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
		for(int i = 0; i <= 2000; i++)
		{			
			try { this.sleep(100); }
			catch (InterruptedException e) { }
			
			System.out.println(id + "- i: " + i + " count: " + getLoopCount(i) + " counter: " + counter);
		}
	}
	
	private int getLoopCount(int loop)
	{
		try { this.sleep(50); }
		catch (InterruptedException e) { }
		
		counter++;
		
		return ++loop;
	}
}
