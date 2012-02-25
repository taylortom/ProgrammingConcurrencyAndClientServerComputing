
with Ada.Command_Line, Ada.Text_IO, Utils, OrderList;
use Ada.Command_Line, Ada.Text_IO;

package body Cashier is

   	--
   	-- Simulates the cashier taking a new order
   	--
   	procedure TakeOrder (Cashier:CashierRecord) is

   	begin

      		Put_Line("Cashier.TakeOrder");
                OrderList.AddOrder(OrderList.CreateOrder(Cashier.Name));
                --delay Utils.GenerateRandomNumber(0,5);

      	end TakeOrder;

end Cashier;
