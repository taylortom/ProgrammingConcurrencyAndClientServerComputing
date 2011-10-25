package restaurant;

// Java imports
import java.util.ArrayList;

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
	public static synchronized void addCook(Cook _cook)
	{
		cooks.add(_cook);
	}
	
	/**
	 * Gets the Cook from the id 
	 * @return the next order
	 */
	public static synchronized Cook getCook(String _id)
	{
		// TODO CookManager.getCook
		return null;
	}
	
	public static synchronized Cook getRandomCook()
	{
		int randomNumber = Utils.generateRandomNumber(cooks.size());
		return cooks.get(randomNumber);
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected CookManager() {	}
}
