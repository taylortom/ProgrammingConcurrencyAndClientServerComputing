package managers;

// Java imports
import java.util.ArrayList;
import members.Cook;
import utils.Utils;

/**
 * Class to deal with all of the cooks
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class CookManager
{
	private static ArrayList<Cook> cooks = new ArrayList<Cook>();
	
	// the CookManager instance
	private static CookManager instance = null;

	/**
	 * Returns the instance of the CookManager
	 * @return the CookManager instance
	 */
	public static CookManager getInstance() 
	{
		if(instance == null) { instance = new CookManager(); }
		return instance;
	}
	
	/**
	 * Adds the passed Cook to the cooks list
	 * @param _order
	 */
	public synchronized void addCook(Cook _cook)
	{
		if(Utils.arraySearch(cooks, _cook).equals(false)) cooks.add(_cook);
	}
	
	/**
	 * Gets the Cook by index 
	 * @return the cook
	 */
	public synchronized Cook getCook(int index)
	{
		return cooks.get(index);
	}
	
	public synchronized Cook getRandomCook()
	{
		int randomNumber = Utils.generateRandomNumber(cooks.size()-1);
		return cooks.get(randomNumber);
	}
	
	/**
	 * Gets the total number of Cooks 
	 * @return number of cooks
	 */
	public int getNumberOfCooks()
	{
		return cooks.size();
	}
	
	/**
	 * Gets the total number of Cooks available
	 * (i.e. logged in) 
	 * @return number of cooks
	 */
	public synchronized int getNumberOfAvailableCooks()
	{
		int count = 0;
		
		for (int i = 0; i < cooks.size(); i++)
		{
			Cook cook = cooks.get(i);
			if(cook.loggedIn()) count++;
		}
		
		return count;
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected CookManager() {	}
}
