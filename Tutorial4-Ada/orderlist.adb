
with Ada.Command_Line, Ada.Text_IO, Utils;
use Ada.Command_Line, Ada.Text_IO;

package body OrderList is

   	--
   	-- Adds the passed order to the pending orders list
   	--
	procedure AddOrder (OrderToAdd:OrderAccess) is

	begin

      		Put_Line("OrderList.AddOrder: " & Integer'Image(OrderToAdd.OrderNo));

                -- need to set the head of the queue
      		if OrderTotal = 0 then
         		HeadOrder := OrderToAdd;
                else
                	TailOrder.NextOrder := OrderToAdd;
      		end if;

                -- set order as the tail of the queue
                TailOrder := OrderToAdd;

      		-- increment the order total
      		OrderTotal := OrderTotal + 1;

   	end AddOrder;

   	--
   	-- Creates a new order
   	--
   	function CreateOrder (Cashier:String) return OrderAccess is

      		NewOrder:OrderAccess;

   	begin

                NewOrder := new Order'(OrderNo => OrderID, Time => Utils.GetCurrentTime, Cashier => Cashier, NextOrder => null);
                OrderID := OrderID + 1;
      		return NewOrder;

      	end CreateOrder;

   	--
   	-- Returns the next order in the pending orders list
   	--
   	function GetOrder return OrderAccess is

      		-- set a local var with the next order
		OrderToReturn:OrderAccess := HeadOrder;

	begin

      		Put_Line("OrderList.GetOrder");

      		-- update the next order
      		HeadOrder := HeadOrder.NextOrder;
      		return OrderToReturn;

	end GetOrder;

        --
   	-- Prints each order to the console window
   	--
        procedure PrintList is

        	Order:OrderAccess := HeadOrder;

        begin

        	Put_Line(Integer'Image(HeadOrder.OrderNo) & " " & Integer'Image(TailOrder.OrderNo));

        	loop
        		Put_Line("Order " & Integer'Image(Order.OrderNo) & ": " & Order.Cashier & " " & Order.Time & " ");
                        exit when Order.OrderNo = OrderTotal;
                        Order := Order.NextOrder;
    		end loop;

        end PrintList;

end OrderList;
