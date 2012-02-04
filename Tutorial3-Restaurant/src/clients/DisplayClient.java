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
public class DisplayClient extends Thread
{
	private static JList pendingList;
	private static JList processingList;
	private static JPanel panel;
	
	public DisplayClient()
	{
		initGUI();
		this.start();
	}
  
	public static void initGUI()
	{     			  
		JFrame frame = new JFrame("Restaurant Display Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 410);

		Container container = new Container(); 
		container.setLayout(null);
		
		panel = new JPanel();
		container.add(panel);
	
		/**
		 * Pending List
		 */
		
		JLabel pendingTitle = new JLabel("Pending Orders", SwingConstants.LEFT);
		pendingTitle.setVerticalAlignment(SwingConstants.TOP);
		pendingTitle.setBounds(100, 23, 250, 300);
		container.add(pendingTitle);	
		
		pendingList = new JList(OrderManager.getInstance().getPendingOrders());
		JScrollPane pendingScroller = new JScrollPane(pendingList);
		pendingScroller.setBounds(25, 60, 250, 300);
		container.add(pendingScroller);
		
		
		/**
		 * Processing List
		 */
		
		JLabel processingTitle = new JLabel("Processing Orders", SwingConstants.LEFT);
		processingTitle.setVerticalAlignment(SwingConstants.TOP);
		processingTitle.setBounds(380, 23, 250, 300);
		container.add(processingTitle);
		
		processingList = new JList(OrderManager.getInstance().getProcessingOrders());
		JScrollPane processingScroller = new JScrollPane(processingList);
		processingScroller.setBounds(315, 60, 250, 300);
		container.add(processingScroller);

		frame.add(container);		
		frame.setVisible(true);
	}

	@Override
	public void run()
	{
		while(true)
		{
			pendingList.setListData(OrderManager.getInstance().getPendingOrders());
			processingList.setListData(OrderManager.getInstance().getProcessingOrders());
			//panel.revalidate();
		}
	}
}