package other;

/**
 * Contains the constants used in the program
 *
 * @author Tom
 * @version 0.1
 * @history Feb 15, 2012: Created class
 */
public interface Constants
{
	// Network stuff
	public static final int COMMUNICATION_PORT = 1999;
	public static final int CONNECTION_TIMEOUT = 10000;
	
	// ID prefixes
	public static final String CASHIER_PREFIX = "CA"; 
	public static final String COOK_PREFIX = "CO"; 
	public static final String CUSTOMER_PREFIX = "CU"; 
	
	// Clients
	public static final int NUMBER_OF_COOKS = 2;
	public static final int NUMBER_OF_CASHIERS = 1;
	
	public enum Function 
	{ 
		ADD_ORDER, 
		GET_ORDER,
		GET_NEXT_ORDER, 
		SET_ORDER_COOKED,
		SET_ORDER_DELIVERED,
		CREATE_RANDOM_ORDER,
		GET_CASHIER
	}
}
