
--
-- Author: Thomas Taylor
--
-- Keeps track of all the orders.
-- Rather than using an array, it uses a linked list style,
-- where each order has a reference to the next order.
--

package OrderList is

   	--
   	-- Types/variables
   	--

   	-- stores information about an order
   	type Order;
    	type OrderAccess is access Order;
   	type Order is record
  		OrderNo:Integer;
  		Time:String(1..19);
      		Cashier:String(1..15);
      		NextOrder:OrderAccess;
	end record;

   	-- the next and last orders
   	HeadOrder:OrderAccess;
   	TailOrder:OrderAccess;

   	-- total number of orders
   	OrderTotal:Integer := 0;

   	-- the next order id
   	OrderID:Natural := 1;

   	--
   	-- Procedures/functions
   	--

   	-- adds an order to the list
   	procedure AddOrder (orderToAdd:OrderAccess);

   	-- creates a new order
   	function CreateOrder (Cashier:String) return OrderAccess;

   	-- gets the next order, and removes from the list
   	function GetOrder return OrderAccess;

        -- prints out each item to the console window
        procedure PrintList;

end OrderList;
