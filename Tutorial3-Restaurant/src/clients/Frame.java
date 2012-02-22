package clients;

import java.awt.event.*;
import javax.swing.*;

import members.Cook;
import members.Employee;

/**
 * An extension of the JFrame with custom close function
 * Can be used with or without a reference to an employee
 * (i.e as a client or server frame)
 *
 * @author Tom
 * @version 0.1
 * @history 15.02.2012: Created class
 */
public class Frame extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;

	JLabel alert;

	// reference to the employee
	Employee employee;

	/**
	 * Constructor
	 * @param _employee
	 * @param _width
	 * @param _height
	 */
	public Frame(Employee _employee, JLabel _alert, int _width, int _height) 
	{
		super(_employee.getClass().getSimpleName().toString() + " Client: " + _employee.getFirstName() + " " + _employee.getSurname());
		this.employee = _employee;
		if(_alert != null) this.alert = _alert;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(_width, _height);
		addWindowListener(this);
	}

	/**
	 * Alternative Constructor
	 * Doesn't use employee
	 * @param _title
	 * @param _width
	 * @param _height
	 */
	public Frame(String _title, int _width, int _height) 
	{
		super(_title);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(_width, _height);
		addWindowListener(this);
	}

	public void windowClosing(WindowEvent e) 
	{
		employee.logOut();

		if(!employee.loggedIn()) 
		{
			this.dispose();
			System.exit(EXIT_ON_CLOSE);
		}
	}

	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}