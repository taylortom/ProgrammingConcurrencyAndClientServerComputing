package datatypes;

import java.util.ArrayList;
import other.Constants.Course;

/**
 * A class to store the menu items
 *
 * @author Tom
 * @version 0.1
 * @history Oct 27, 2011: Created class
 */
public class Menu
{
	// the list of items
	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>(); 
	
	// the Menu instance
	private static Menu instance = null;

	/**
	 * Returns the instance of the Menu
	 * @return the Menu instance
	 */
	public static Menu getInstance() 
	{
		if(instance == null) { instance = new Menu(); }
		return instance;
	}

	/**
	 * Finds the passed item
	 * @param the id of the item to get
	 * @return the item
	 */
	public MenuItem getItem(String _id)
	{		
		for (MenuItem item : menuItems)
		{
	      if (item.getId() == _id) System.out.println("We got a match!!");
		}
		
		return null;
	}
	
	/**
	 * Returns a menu item from its index
	 * @param index
	 * @return the item
	 */
	public MenuItem getItem(int _index)
	{		
		return menuItems.get(_index);
	}
	
	/**
	 * The number of items in the menu
	 * @return the number of items
	 */
	public int getNumberOfItems()
	{
		return this.menuItems.size();
	}
	
	/**
	 * Adds an item to the list
	 * @param item to add
	 */
	public void addItem(MenuItem _item)
	{
		if(_item != null) this.menuItems.add(_item);
	}
	
	/**
	 * Populates the menu with some test items
	 */
	private void addTestData()
	{
		// starters
		this.addItem(new MenuItem("m-01", "Braised Greens",    3.95, Course.STARTER));
		this.addItem(new MenuItem("m-02", "Artichoke Risotto", 4.95, Course.STARTER));
		this.addItem(new MenuItem("m-03", "Fried Halloumi",    3.95, Course.STARTER));
		this.addItem(new MenuItem("m-04", "Baked Mushrooms", 	 4.95, Course.STARTER));
		this.addItem(new MenuItem("m-05", "Bruschetta", 		 3.95, Course.STARTER));

		// mains
		this.addItem(new MenuItem("m-01", "Margharita Pizza",	   8.95, Course.MAIN));
		this.addItem(new MenuItem("m-02", "Spaghetti Bolognese", 9.95, Course.MAIN));
		this.addItem(new MenuItem("m-03", "Chilli Con Carne", 	9.95, Course.MAIN));
		this.addItem(new MenuItem("m-04", "Chargrilled Steak",  14.95, Course.MAIN));
		this.addItem(new MenuItem("m-05", "Beef Lasagne", 		  10.95, Course.MAIN));
		this.addItem(new MenuItem("m-06", "Baked Cannelloni",   10.95, Course.MAIN));
		this.addItem(new MenuItem("m-07", "Thai Green Curry",   10.95, Course.MAIN));
		this.addItem(new MenuItem("m-08", "Chicken Chow Mein",  10.95, Course.MAIN));
		this.addItem(new MenuItem("m-09", "Chicken Soup",	     10.95, Course.MAIN));
		this.addItem(new MenuItem("m-10", "Red Apple Salad",    10.95, Course.MAIN));
		
		// desserts
		this.addItem(new MenuItem("d-01", "Banoffee Pie", 			 5.95, Course.DESSERT));
		this.addItem(new MenuItem("d-02", "Chocolate Fudge Cake", 5.95, Course.DESSERT));
		this.addItem(new MenuItem("d-03", "Tiramasu", 				 4.95, Course.DESSERT));
		this.addItem(new MenuItem("d-04", "Lemon Cheesecake", 	 4.95, Course.DESSERT));
		this.addItem(new MenuItem("d-05", "Sorbet Selection", 	 3.95, Course.DESSERT));
	}

	/**
	 * Constructor - should not be called externally
	 */
	private Menu() { addTestData(); }
}
