package restaurant;

import java.lang.reflect.Array;

import utils.Utils;

/**
 * A class to store order information
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Order
{
	private String id;
	// the customer who placed the order
	private Customer customer = null;
	// the cashier who took the order
	private Cashier cashier = null;
	// the cook cooking(!) the order
	private Cook cook = null;
	
	// the total cost of the order
	private double total;
	
	// an array of MenuItem with each order
	private MenuItem[] order;
	
	private OrderStatus status;
	
	public enum OrderStatus
	{
		pending,
		inProgress,
		completed;
	}
	
	/**
	 * Constructor
	 */
	public Order(MenuItem[] _order, Cashier _cashier, Customer _customer)
	{
		this.order = _order;
		this.cashier = _cashier;
		this.customer = _customer;
	}
	
	public int calculatePreparationTime()
	{
		int preparationTime = 0;
		
		for (int i = 0; i < this.order.length; i++)
		{
			preparationTime += this.order[i].getPreparationTime();
		}
		
		// TODO slight hack - *prep time by 200
		return preparationTime*200;
	}
	
	public void setOrderCompleted()
	{
		this.status = OrderStatus.completed;
		this.cashier.deliverOrder();
	}
	
	// getters/setters
	public String getId() { return id; }
	public void setId(String _id) 
	{ 
		if(_id != null) this.id = _id; 
	}
	
	public Cashier getCashier() { return this.cashier; }
	public Customer getCustomer() { return this.customer; }
	public MenuItem[] getOrder() { return this.order; }
	
	public double getTotal() {	return total;	}
	
	// TODO Order: set order to complete
}
