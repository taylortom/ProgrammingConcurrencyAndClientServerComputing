package clients;

import java.awt.Container;
import java.awt.TextArea;

import javax.swing.*;

import managers.CashierManager;
import managers.CookManager;
import managers.OrderManager;

/**
 * Displays the state of the system
 *
 * @author Tom
 * @version 0.1
 * @history 03.02.2012: Created class
 */
public class ServerClient
{
	private JLabel cashiers;
	private JLabel cooks; 
	private JLabel orders;
	
	private static TextArea display;
	
	/**
	 * Constructor
	 */
	public ServerClient()
	{
		initGUI();
	}
  
	/**
	 * Creates the components 
	 */
	public void initGUI()
	{     
		// create the window
		Frame frame = new Frame("Server Client", 600, 410);
		
		// create the container for the components
		Container container = new Container(); 
		container.setLayout(null);
	
		// title
		JLabel title = new JLabel("Server Data");
		title.setBounds(50, 23, 250, 25);
		container.add(title);
		
		// connected cashiers
		cashiers = new JLabel("Connected cashiers: 0");
		cashiers.setBounds(50, 75, 250, 25);
		container.add(cashiers);

		/// connected cooks
		cooks = new JLabel("Connected cooks: 0");
		cooks.setBounds(50, 100, 250, 25);
		container.add(cooks);
		
		/// total orders
		orders = new JLabel("Total orders: 0");
		orders.setBounds(50, 150, 250, 25);
		container.add(orders);

		// text area
		display = new TextArea();
		display.setBounds(250, 70, 300, 275);
		container.add(display);
		
		// add the container to the frame
		frame.add(container);		
		frame.setVisible(true);
	}

	/**
	 * Updates the display
	 */
	public void updateDisplayText(String _string)
	{
		display.setText(_string + "\n" + display.getText());
	}
	
	public void update()
	{
		cashiers.setText("Connected cashiers: " + CashierManager.getInstance().getNumberOfAvailableCashiers());
		cooks.setText("Connected cooks: " + CookManager.getInstance().getNumberOfAvailableCooks());
		
		int totalOrders = OrderManager.getInstance().getPendingOrders().length + OrderManager.getInstance().getProcessingOrders().length;
		orders.setText("Total orders: " + totalOrders);
	}
}