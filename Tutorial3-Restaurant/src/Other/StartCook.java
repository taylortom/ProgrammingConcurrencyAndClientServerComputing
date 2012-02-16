package other;

import managers.CookManager;
import members.Cook;

/**
 * 
 * Starts a random Cook
 *
 * @author Tom
 * @version 0.1
 * @history Feb 16, 2012: Created class
 */
public class StartCook
{
	public static void main(String[] args)
	{
		while(true)
		{
			Cook cook = CookManager.getInstance().getRandomCook();
			if(cook != null && !cook.loggedIn())
			{
				cook.logIn();
				return;
			}
			try { Thread.sleep(1000); }
			catch (InterruptedException e){ }
		}
	}

}
