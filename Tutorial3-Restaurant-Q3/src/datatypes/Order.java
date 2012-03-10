package datatypes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

import other.Constants;

import members.Cashier;
import members.Customer;

import utils.LoyaltyScheme;
import utils.Utils;

/**
 * A class to store order information
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Order implements Serializable
{
	// the order details
	private String id;
	private String date;
	private String time;
	private Calendar timeCooked; 
	
	// the customer who placed the order
	private Customer customer = null;
	// the cashier who took the order
	private Cashier cashier = null;
	
	private OrderStatus status;
	
	// the total cost of the order
	private double total = 0.0;
	private double discount = 0.0;
	
	// an array of MenuItems with each order
	private MenuItem[] order;
	
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
		
		this.customer.addOrder(this);
	}
	
	/**
	 * Calculates the total order price
	 */
	public void calculateTotal()
	{
		for (int i = 0; i < this.order.length; i++) 
			this.total += this.order[i].getPrice();	
		
		this.discount = LoyaltyScheme.calculateDiscount(this);
	}

	/**
	 * Calculates the total preparation time of the order
	 * (Caps the preparation time at 7 seconds)
	 * @return the prep time
	 */
	public int calculatePreparationTime()
	{
		int preparationTime = 0;
		
		for (int i = 0; i < this.order.length; i++) 
			preparationTime += this.order[i].getPreparationTime();
		
		// TODO slight hack: x prep time by 200
		preparationTime = (preparationTime > 7000) ? 7000 : preparationTime*200;
		return preparationTime;
	}
	
	/**
	 * Exports a receipt file at the location specified in RECEIPT_FILENAME
	 */
	public void createReceipt()
	{	
		try
		{
			FileWriter fw = new FileWriter(Utils.stringSearchAndReplace(Constants.RECEIPT_FILENAME, "X", id));

			// add file header
			fw.append("--Sales Receipt--");
			fw.append("\n");
			fw.append("\nYou were served by: " + this.cashier.getFirstName() + " " + this.cashier.getSurname());
			fw.append("\n\n");
			fw.append("Order No. " + this.id);
			fw.append('\n');
			fw.append("Date: " + this.date);
			fw.append(" Time: " + this.time);
			fw.append("\n\n\nItems:\n\n");
			
			// add order items
			for (int i = 0; i < this.order.length; i++) 
			{
				MenuItem item = this.order[i];
				
				fw.append(Utils.extendStringToLength(item.getDescription(), 25));				
				fw.append("£" + item.getPrice());
				fw.append("\n");
			}
						
			// round and add the totals	
			if(discount > 0.0) 
			{
				fw.append("\nSubtotal: £" + Double.valueOf(new DecimalFormat("#.##").format(total)));
				fw.append("\nDiscount: £" + Double.valueOf(new DecimalFormat("#.##").format(discount)));
			}
			fw.append("\nTotal: £" + Double.valueOf(new DecimalFormat("#.##").format(total-discount)));

			// add footer and close FileWriter
			fw.append("\n\n\n--Thank you, call again!--");
			fw.flush();
			fw.close();
		}
		catch(IOException e)
		{
			System.out.println("Order.createReceipt: Error occurred while creating receipt " + e.getMessage());
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
	public Calendar getTimeCooked() { return this.timeCooked; }
	public void setOrderStatus(OrderStatus _status) 
	{ 
		if(this.status != _status) 
		{
			this.status = _status;
			if(_status == OrderStatus.cooked) this.timeCooked = Calendar.getInstance(); 
		}
	}
	public double getTotal() {	return total;	}	
}
