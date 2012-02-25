
--
-- Author: Thomas Taylor
--
-- Performs cook-specific functions.
--

package Cook is

   	--
   	-- Types/variables
   	--

   	type CookRecord is record
  		Name:String(1..15);
      		OrdersCooked:Integer := 0;
	end record;

   	--
   	-- Procedures/functions
   	--

   	-- simulates the cooking of an order
   	procedure CookOrder (Cook:CookRecord);

end Cook;
