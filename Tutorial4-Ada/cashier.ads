
--
-- Author: Thomas Taylor
--
-- Performs cashier-specific functions.
--

package Cashier is

   	--
   	-- Types/variables
   	--

        type CashierRecord is record
  		Name:String(1..15);
      		OrdersTaken:Integer := 0;
	end record;

   	--
   	-- Procedures/functions
   	--

   	-- simulates the taking of an order
   	procedure TakeOrder (Cashier:CashierRecord);

end Cashier;
