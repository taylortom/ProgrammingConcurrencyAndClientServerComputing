package restaurant;

// Java imports
import java.util.ArrayList;

// Java Library imports
import utils.ErrorLog;
import utils.Utils;

/**
 * Class to deal with all of the cashiers
 *
 * @author Tom
 * @version 0.1
 * @history 24.10.2011: Created class
 */
public class CashierManager
{
	private static ArrayList<Cashier> cashiers = new ArrayList<Cashier>();
	
	// the CashierManager instance
	private static CashierManager instance = null;

	/**
	 * Returns the instance of the CashierManager
	 * @return the CashierManager instance
	 */
	public static CashierManager getInstance() 
	{
		if(instance == null) { instance = new CashierManager(); }
		return instance;
	}
	
	/**
	 * Adds the passed customer to the cashiers list
	 * @param _cashier
	 */
	public static synchronized void addCashier(Cashier _cashier)
	{
		if(Utils.arraySearch(cashiers, _cashier) == null) cashiers.add(_cashier);
	}
	
	/**
	 * Finds a cashier by their id
	 * @param _id
	 * @return the cashier
	 */
	public static synchronized Cashier getCashier(String _id)
	{
		// TODO Cashier.getCashier
		return null;
	}
	
	/**
	 * Gets a random cashier from the list using Utils.generateRandomNumber
	 * @return a random cashier
	 */
	public static synchronized Cashier getRandomCashier()
	{
		int randomNumber = Utils.generateRandomNumber(cashiers.size()-1);
		return cashiers.get(randomNumber);
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected CashierManager() { }
}
