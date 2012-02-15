package clients;

import java.awt.Container;
import javax.swing.*;

import managers.OrderManager;

/**
 * Displays the state of the system
 *
 * @author Tom
 * @version 0.1
 * @history 03.02.2012: Created class
 */
public class DisplayClient
{
	// references to the lists (used by update()) 
	private static JList pendingList;
	private static JList processingList;
	private static JList deliveryList;
	
	/**
	 * Constructor
	 */
	public DisplayClient()
	{
		initGUI();
	}
  
	/**
	 * Creates the components 
	 */
	public static void initGUI()
	{     
		// create the window
		Frame frame = new Frame("Restaurant Display Client", 600, 410);
		
		// create the container for the components
		Container container = new Container(); 
		container.setLayout(null);
	
		/**
		 * Pending List
		 */
		
		JLabel pendingTitle = new JLabel("Pending Orders", SwingConstants.LEFT);
		pendingTitle.setVerticalAlignment(SwingConstants.TOP);
		pendingTitle.setBounds(50, 23, 150, 300);
		container.add(pendingTitle);	
		
		pendingList = new JList();
		JScrollPane pendingScroller = new JScrollPane(pendingList);
		pendingScroller.setBounds(25, 60, 150, 300);
		container.add(pendingScroller);
		
		/**
		 * Processing List
		 */
		
		JLabel processingTitle = new JLabel("Processing Orders", SwingConstants.LEFT);
		processingTitle.setVerticalAlignment(SwingConstants.TOP);
		processingTitle.setBounds(245, 23, 150, 300);
		container.add(processingTitle);
		
		processingList = new JList();
		JScrollPane processingScroller = new JScrollPane(processingList);
		processingScroller.setBounds(225, 60, 150, 300);
		container.add(processingScroller);

		frame.add(container);		
		frame.setVisible(true);
		
		/**
		 * Delivery List
		 */
		
		JLabel deliveryTitle = new JLabel("Delivery Orders", SwingConstants.LEFT);
		deliveryTitle.setVerticalAlignment(SwingConstants.TOP);
		deliveryTitle.setBounds(445, 23, 150, 300);
		container.add(deliveryTitle);	
		
		deliveryList = new JList();
		JScrollPane deliveryScroller = new JScrollPane(deliveryList);
		deliveryScroller.setBounds(420, 60, 150, 300);
		container.add(deliveryScroller);
	}

	/**
	 * Updates the lists when they're changed
	 */
	public void update()
	{
		pendingList.setListData(OrderManager.getInstance().getPendingOrders());
		processingList.setListData(OrderManager.getInstance().getProcessingOrders());
		deliveryList.setListData(OrderManager.getInstance().getDeliveryOrders());
	}
}