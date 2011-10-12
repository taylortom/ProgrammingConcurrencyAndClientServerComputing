/**
 * Class description
 *
 * @author Tom
 * @version 0.1
 * @history 12.10.2011: Created class
 */
public class LoopThread implements Runnable
{
	private Thread t = null;
	
	public static void main(String[] args)
	{
		Thread t = new Thread(new LoopThread());
		t.start();
	}

	@Override
	public void run()
	{
		for(int i = 0; i <= 5000; i++)
		{
			System.out.println("Loop: " + i);
			
			try
			{ 
				t.sleep(0); 
			}
			catch (InterruptedException e) 
			{ 
				System.err.println("Interrupted Exception");
			}
			
			System.out.println("LoopCount: " + getLoopCount(i));
		}
	}
	
	private int getLoopCount(int loop)
	{
		return loop++;
	}
}
