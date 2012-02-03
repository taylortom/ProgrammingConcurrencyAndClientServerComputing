package clients;

import java.awt.Container;
import javax.swing.*;

/**
 * Displays the state of the system
 *
 * @author Tom
 * @version 0.1
 * @history 03.02.2012: Created class
 */
public class DisplayClient
{
	public DisplayClient()
	{
		initGUI();
	}
  
	public static void initGUI()
	{     
		System.out.println("DisplayClient.initGUI");
	  
		JFrame  window = new JFrame("Restaurant Display Client");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("Hi there");
		Container content = window.getContentPane();
		content.add(label);
		window.setSize(500, 400);
		window.setVisible(true);
	}
}