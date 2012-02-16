package clients;

import java.awt.Container;
import javax.swing.*;

import members.Cook;

/**
 * Displays the status of a cook
 *
 * @author Tom
 * @version 0.1
 * @history 15.02.2012: Created class
 */
public class CookClient
{
	private Frame frame;
	private JLabel orders;
	private JLabel status;
	private JLabel order;
	
	private Cook cook;
	
	/**
	 * Constructor
	 * @param the cashier the client represents 
	 */
	public CookClient(Cook _cook)
	{
		this.cook = _cook;
		this.initGUI();
	}
  
	/**
	 * Initialises the GUI, creates the labels
	 */
	public void initGUI()
	{     			  
		// create the window
		this.frame = new Frame(this.cook, 250, 165);
		
		// create the container for the components
		Container container = new Container(); 
		container.setLayout(null);
		
		// add name label
		JLabel cookName = new JLabel(this.cook.getFirstName() + " " + this.cook.getSurname());
		cookName.setBounds(25, 20, 250, 25);
		container.add(cookName);	
		
		// add orders label
		orders = new JLabel("Total Orders: " + this.cook.getTotalOrders());
		orders.setBounds(25, -90, 250, 300);
		container.add(this.orders);
		
		// add status label
		status = new JLabel("Status:");
		status.setBounds(25, -70, 250, 300);
		container.add(this.status);
		
		// add order label
		order = new JLabel("Order:");
		order.setBounds(25, -40, 250, 300);
		container.add(this.order);
		
		// add the container to the frame
		this.frame.add(container);		
		this.frame.setVisible(true);
	}
	
	/**
	 *	Updates the labels 
	 * @param the last performed action
	 */
	public void update(String _status)
	{
		this.orders.setText("Total Orders: " + this.cook.getTotalOrders());
		this.status.setText("Status: " + _status);
		if(this.cook.getCurrentOrder() != null) this.order.setText("Order: " + this.cook.getCurrentOrder().getId());
	}
}