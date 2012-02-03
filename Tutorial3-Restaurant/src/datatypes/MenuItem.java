package datatypes;

/**
 * An individual menu item
 *
 * @author Tom
 * @version 0.1
 * @history Oct 27, 2011: Created class
 */
public class MenuItem
{
	private String id = "";
	private String name = "";
	private double price = 0.0;
	private Course course = null;
	
	public enum Course { STARTER, MAIN, DESSERT }
	
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
	
	public String getDescription()
	{
		return this.name;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
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
