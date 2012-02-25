
with Ada.Command_Line, Ada.Text_IO, Utils, OrderList;
use Ada.Command_Line, Ada.Text_IO;

package body Cook is

   	--
   	-- Simulates the cook cooking an order
   	--
   	procedure CookOrder (Cook:CookRecord) is

		OrderToCook:OrderList.OrderAccess;

   	begin

      		Put_Line("Cook.CookOrder");
                OrderToCook := OrderList.GetOrder;
                delay Utils.GenerateRandomNumber(0,7);
                Put("Order " & Integer'Image(OrderToCook.OrderNo) & " cooked by " & Cook.Name);

      	end CookOrder;

end Cook;
