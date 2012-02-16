package datatypes;

import java.io.Serializable;

import other.Constants.Function;

import members.Cashier;
import members.Cook;

/**
 * A wrapper for an number of object s
 *
 * @author Tom
 * @version 0.1
 * @history Feb 16, 2012: Created class
 */
public class DataPacket implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public Order order = null;
	public Cashier cashier = null;
	public Cook cook = null;
	public Function function;
	
	public enum DataType
	{
		ORDER,
		CASHIER, 
		COOK,
		FUNCTION
	}
	public DataType type;
	
	public DataPacket(Order _order)
	{
		type = DataType.ORDER;
		this.order = _order;
	}
	
	public DataPacket(Cashier _cashier)
	{
		type = DataType.CASHIER;
		this.cashier = _cashier;
	}
	
	public DataPacket(Cook _cook)
	{
		type = DataType.COOK;
		this.cook = _cook;
	}
	
	public DataPacket(Function _function)
	{
		type = DataType.FUNCTION;
		this.function = _function;
	}
}