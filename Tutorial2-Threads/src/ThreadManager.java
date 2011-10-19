
/**
 * For running multiple threads
 *
 * @author tom
 * @version 0.1
 * @history 12.10.2011: Created class
 */
public class ThreadManager
{
	public static void main(String[] args)
	{
		LoopThread lt1 = new LoopThread("lt1");
		LoopThread lt2 = new LoopThread("lt2");
		LoopThread lt3 = new LoopThread("lt3");
		
		lt1.start();
		lt2.start();
		lt3.start();
	}

}
