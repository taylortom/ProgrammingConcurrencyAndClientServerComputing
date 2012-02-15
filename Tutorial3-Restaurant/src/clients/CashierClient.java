package clients;

import java.awt.Container;
import javax.swing.*;

import members.Cashier;

/**
 * Displays the status of a cashier
 *
 * @author Tom
 * @version 0.1
 * @history 03.02.2012: Created class
 */
public class CashierClient
{
	private Frame frame;
	private JLabel orders;
	private JLabel status;
	
	private Cashier cashier;
	
	/**
	 * Constructor
	 * @param the cashier the client represents 
	 */
	public CashierClient(Cashier _cashier)
	{
		this.cashier = _cashier;
		this.initGUI();
	}
  
	/**
	 * Initialises the GUI, creates the labels
	 */
	public void initGUI()
	{     			  
		// create the window
		this.frame = new Frame(this.cashier, 250, 150);
		
		// create the container for the components
		Container container = new Container(); 
		container.setLayout(null);
		
		// add name label
		JLabel cashierName = new JLabel(this.cashier.getFirstName() + " " + this.cashier.getSurname());
		cashierName.setBounds(25, -120, 250, 300);
		container.add(cashierName);	
		
		// add orders label
		orders = new JLabel("Total Orders: " + this.cashier.getTotalOrders());
		orders.setBounds(25, -90, 250, 300);
		container.add(this.orders);
		
		// add status label
		status = new JLabel("Status:");
		status.setBounds(25, -70, 250, 300);
		container.add(this.status);
		
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
		this.orders.setText("Total Orders: " + this.cashier.getTotalOrders());
		this.status.setText("Status: " + _status);
	}
}