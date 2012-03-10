package utils;

import other.Constants;
import datatypes.Order;

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
	/**
	 * Calculates if the customer earns a discount
	 * @param order to discount
	 * @return the amount to discount
	 */
	public static double calculateDiscount(Order order)
	{		
		double orderCost = order.getTotal();
		int orders = order.getCustomer().getTotalOrders();
		
		if(orders > 4)
		{
			if(orders > 9)
			{
				if(orders > 14) return orderCost * Constants.GOLD_DISCOUNT;
				return orderCost * Constants.SILVER_DISCOUNT;
			}
			return orderCost * Constants.BRONZE_DISCOUNT; 
		}
		return 0;
	}
}
