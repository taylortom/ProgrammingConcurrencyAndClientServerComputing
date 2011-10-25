package restaurant;

import java.util.ArrayList;

import utils.Utils;

/**
 * A main class to kick everything off
 *
 * @author Tom
 * @version 0.1
 * @history 19.10.2011: Created class
 */
public class Main
{
	private static ArrayList<Cook> cooks  = new ArrayList<Cook>();
	private static ArrayList<Cashier> cashiers  = new ArrayList<Cashier>();
	private static ArrayList<Customer> customers  = new ArrayList<Customer>();
	
	/**
	 * The kick-off point...
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Main.main");
		
		addCooks();
		addCashiers();
		
		startThreads();
	}

	/**
	 * Starts the cashier/customer threads
	 */
	// TODO how to start threads implementing runnable
	private static void startThreads()
	{
		System.out.println("Main.startThreads");
		
		for (int i = 0; i < cashiers.size(); i++)
		{
			Thread cashier = new Thread(cashiers.get(i));
			cashier.start();
		}
		
		System.out.println("cooks.size(): " + cooks.size());
		
		for (int j = 0; j < cooks.size(); j++)
		{
			Thread cook = new Thread(cooks.get(j));
			cook.start();
		}
	}

	/**
	 * Create a few new cooks
	 */
	private static void addCooks()
	{	
		System.out.println("Main.addCooks");
		cooks.add(new Cook("Raymond", "Slater", Utils.generateUniqueId("CO")));
		cooks.add(new Cook("Laura", "Conner", Utils.generateUniqueId("CO")));
		cooks.add(new Cook("Grace", "Stafford", Utils.generateUniqueId("CO")));
		cooks.add(new Cook("Joe", "Stevens", Utils.generateUniqueId("CO")));
		cooks.add(new Cook("Paul", "McBride", Utils.generateUniqueId("CO")));
	}
	
	/**
	 * Create a few new cashiers
	 */
	private static void addCashiers()
	{
		cashiers.add(new Cashier("Emma", "Fitzgerald", Utils.generateUniqueId("CA")));
		cashiers.add(new Cashier("John", "Price", Utils.generateUniqueId("CA")));
		cashiers.add(new Cashier("Sarah", "Simpson", Utils.generateUniqueId("CA")));
		cashiers.add(new Cashier("Steve", "Spears", Utils.generateUniqueId("CA")));
		cashiers.add(new Cashier("Nicole", "Black", Utils.generateUniqueId("CA")));
	}
}
