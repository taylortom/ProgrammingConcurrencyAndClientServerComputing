package other;

/**
 * Contains constants used in the program
 *
 * @author Tom
 * @version 0.1
 * @history Feb 15, 2012: Created class
 */
public interface Constants
{
	// Network stuff
	public static final int CONNECTION_TIMEOUT = 5000;
	
	// ID prefixes
	public static final String CASHIER_PREFIX = "CA"; 
	public static final String COOK_PREFIX = "CO"; 
	public static final String CUSTOMER_PREFIX = "CU"; 
	
	// preparation times
	public static final int STARTER_PREP_TIME = 10;
	public static final int MAIN_PREP_TIME = 25;
	public static final int DESSERT_PREP_TIME = 10;
	
	// order discounts
	public static final double BRONZE_DISCOUNT = 0.05; 
	public static final double SILVER_DISCOUNT = 0.10; 
	public static final double GOLD_DISCOUNT = 0.15;

	public static final int DELIVERY_TIME = 3000;
	public final String RECEIPT_FILENAME = "receipts/order_X.txt";
	
	/* 
	 * enumerations
	 */
	
	public enum Function 
	{ 
		ADD_ORDER, 
		CREATE_RANDOM_ORDER,
		DELIVER_ORDER,
		EMPLOYEE_LOG_IN,
		EMPLOYEE_LOG_OUT,		
		GET_CASHIER,
		GET_COOK,
		GET_ORDER,
		GET_NEXT_ORDER,
		SET_ORDER_COOKED,
		SET_ORDER_DELIVERED
	}
	
	public enum Course 
	{ 
		STARTER, 
		MAIN, 
		DESSERT 
	}	
}
