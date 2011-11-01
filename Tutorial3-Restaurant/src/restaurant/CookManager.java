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
//		cooks.add(_cook);
		if(Utils.arraySearch(cooks, _cook).equals(false)) cooks.add(_cook);
	}
	
	/**
	 * Gets the Cook from the id 
	 * @return the cook
	 */
	public static synchronized Cook getCook(String _id)
	{
		// TODO CookManager.getCook
		return null;
	}
	
	/**
	 * Gets the Cook by index 
	 * @return the cook
	 */
	public static synchronized Cook getCook(int index)
	{
		return cooks.get(index);
	}
	
	public static synchronized Cook getRandomCook()
	{
		int randomNumber = Utils.generateRandomNumber(cooks.size());
		return cooks.get(randomNumber);
	}
	
	/**
	 * Gets the total number of Cooks 
	 * @return number of cooks
	 */
	public static int getNumberOfCooks()
	{
		return cooks.size();
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected CookManager() {	}
}
