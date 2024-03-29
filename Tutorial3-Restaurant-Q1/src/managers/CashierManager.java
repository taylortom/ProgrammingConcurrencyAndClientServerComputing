package managers;

// Java imports
import java.util.ArrayList;
import members.Cashier;
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
	public static synchronized CashierManager getInstance() 
	{
		if(instance == null) { instance = new CashierManager(); }
		return instance;
	}
	
	/**
	 * Adds the passed customer to the cashiers list
	 * @param _cashier
	 */
	public synchronized void addCashier(Cashier _cashier)
	{
		if(Utils.arraySearch(cashiers, _cashier).equals(false)) cashiers.add(_cashier);
	}
	
	/**
	 * Gets the Cashier by index 
	 * @return the cashier
	 */
	public synchronized Cashier getCashier(int index)
	{
		return cashiers.get(index);
	}
	
	/**
	 * Gets a random cashier from the list using Utils.generateRandomNumber
	 * @return a random cashier
	 */
	public synchronized Cashier getRandomCashier()
	{
		int randomNumber = Utils.generateRandomNumber(cashiers.size()-1);
		return cashiers.get(randomNumber);
	}
	
	/**
	 * Gets the next available cashier from the list
	 * @return the next cashier, null if none
	 */
	public synchronized Cashier getAvailableCashier()
	{
		for (int i = 0; i < cashiers.size(); i++) if(cashiers.get(i).loggedIn()) return cashiers.get(i);
		return null;
	}
	
	/**
	 * Gets the total number of Cashiers 
	 * @return number of cashiers
	 */
	public synchronized int getNumberOfCashiers()
	{
		return cashiers.size();
	}
	
	/**
	 * Constructor - should never be called externally
	 */
	protected CashierManager() { }
}
