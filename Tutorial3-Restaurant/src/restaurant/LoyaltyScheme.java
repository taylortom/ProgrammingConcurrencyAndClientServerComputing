package restaurant;

/**
 * A customer loyalty scheme class that applies discounts 
 * based on number of orders placed
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class LoyaltyScheme
{
	private int _numOrders = 0;

	private final double BRONZE_DISCOUNT = 0.05; 
	private final double SILVER_DISCOUNT = 0.10; 
	private final double GOLD_DISCOUNT = 0.15; 
	
	/**
	 * Constructor
	 */
	public LoyaltyScheme()
	{
		// TODO LoyaltyScheme constructor
	}
	
	public double applyDiscount(Order order)
	{
		double orderCost = order.getTotal();
		int orders = order.getCustomer().getTotalOrders();
		
		if(orders > 5)
		{
			if(orders > 10)
			{
				if(orders > 15) return orderCost * (1 + GOLD_DISCOUNT);
				return orderCost * (1 + SILVER_DISCOUNT);
			}
			return orderCost * (1 + BRONZE_DISCOUNT); 
		}
		return orderCost;
	}
}
