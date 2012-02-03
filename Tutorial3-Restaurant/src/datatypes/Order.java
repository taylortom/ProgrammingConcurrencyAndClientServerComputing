package datatypes;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import members.Cashier;
import members.Cook;
import members.Customer;

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
	
	// an array of MenuItems with each order
	private MenuItem[] order;
	
	private OrderStatus status;
	
	private final String RECEIPT_FILENAME = "receipts/order_X.txt";
	private final int DESCRIPTION_MAX_LENGTH = 25;
	
	public enum OrderStatus
	{
		pending,
		inProgress,
		completed,
		delivered;
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
		
		// TODO slight hack: x prep time by 200
		preparationTime = (preparationTime > 7000) ? 7000 : preparationTime*200;
		return preparationTime;
	}
	
	public void setOrderCooked()
	{
		this.status = OrderStatus.completed;
		this.cashier.deliverOrder();
	}
	
	public void setOrderDelivered()
	{
		this.status = OrderStatus.delivered;
	}
	
	public void createReceipt()
	{	
		try
		{
			FileWriter fw = new FileWriter(Utils.stringSearchAndReplace(RECEIPT_FILENAME, "X", id));

			// add file header
			fw.append("--Sales Receipt--");
			fw.append("\n");
			fw.append("\nYou were served by: " + this.cashier.getFirstName() + " " + this.cashier.getSurname());
			fw.append("\n\n");
			fw.append("Order No. " + this.id);
			fw.append('\n');
			fw.append("Date: " + Utils.generateTimeStamp("dd.MM.yyyy"));
			fw.append(" Time: " + Utils.generateTimeStamp("HH:mm:ss"));
			fw.append("\n\n\nItems:\n\n");
				
			double total = 0.0;
			
			// add order items
			for (int i = 0; i < this.order.length; i++) 
			{
				MenuItem item = this.order[i];
				total += item.getPrice(); 
				
				fw.append(Utils.extendStringToLength(item.getDescription(), 25));				
				fw.append("£" + item.getPrice());
				fw.append("\n");
			}
			
			// round and add the total	
			fw.append("\nTotal: £" + Double.valueOf(new DecimalFormat("#.##").format(total)));

			// add footer and close FileWriter
			fw.append("\n\n\n--Thank you, call again!--");
			fw.flush();
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("Order.createReceipt: Error occurred while creating receipt");
		}
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
}
