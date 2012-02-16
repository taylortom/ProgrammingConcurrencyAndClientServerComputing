package datatypes;

import java.io.Serializable;

/**
 * An individual menu item
 *
 * @author Tom
 * @version 0.1
 * @history Oct 27, 2011: Created class
 */
public class MenuItem implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	// various pieces of information about the item
	private String id = "";
	private String name = "";
	private double price = 0.0;
	private Course course = null;
	
	// which course the item is
	public enum Course { STARTER, MAIN, DESSERT }
	
	// preparation times
	private static final int STARTER_PREP_TIME = 10;
	private static final int MAIN_PREP_TIME = 25;
	private static final int DESSERT_PREP_TIME = 10;

	/**
	 * Constructor
	 */
	public MenuItem(String _id, String _name, double _price, Course _course)
	{
		this.id = _id;
		this.name = _name;
		this.price = _price; 
		this.course = _course;
	}
	
	/**
	 * Gets the text description of the item
	 * @return the description
	 */
	public String getDescription()
	{
		return this.name;
	}
	
	/**
	 * Gets the id of the item
	 * @return the id
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * Gets the price of the item
	 * @return the price
	 */
	public double getPrice()
	{
		return this.price;
	}
	
	/**
	 * Gets the preparation time of the item
	 * @return the prep time 
	 */
	public int getPreparationTime()
	{
		switch(this.course)
		{
			case STARTER:
				return STARTER_PREP_TIME;
			case MAIN:
				return MAIN_PREP_TIME;
			case DESSERT:
				return DESSERT_PREP_TIME;
			default:
				return 0;
		}
	}
}
