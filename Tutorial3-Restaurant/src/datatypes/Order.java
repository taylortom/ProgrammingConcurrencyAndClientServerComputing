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
	private String date;
	private String time;
	
	// the customer who placed the order
	private Customer customer = null;
	// the cashier who took the order
	private Cashier cashier = null;
	
	private OrderStatus status;
	
	// the total cost of the order
	private double total = 0.0;
	
	// an array of MenuItems with each order
	private MenuItem[] order;
		
	private final String RECEIPT_FILENAME = "receipts/order_X.txt";
	
	public enum OrderStatus
	{
		pending,
		inProgress,
		cooked,
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
		
		this.date = Utils.generateTimeStamp("dd.MM.yyyy");
		this.time = Utils.generateTimeStamp("HH:mm:ss");
		
		this.calculateOrderTotal();
	}
	
	private void calculateOrderTotal()
	{
		for (int i = 0; i < this.order.length; i++) 
		{
			MenuItem item = this.order[i];
			total += item.getPrice(); 
		}
		
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
			fw.append("Date: " + this.date);
			fw.append(" Time: " + date);
			fw.append("\n\n\nItems:\n\n");
			
			// add order items
			for (int i = 0; i < this.order.length; i++) 
			{
				MenuItem item = this.order[i];
				
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
	public OrderStatus getOrderStatus() { return this.status; }
	public void setOrderStatus(OrderStatus _status) 
	{ 
		if(this.status != _status) this.status = _status; 
	}
	public double getTotal() {	return total;	}	
}
