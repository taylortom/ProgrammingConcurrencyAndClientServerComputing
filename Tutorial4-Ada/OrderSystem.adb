with Ada.Text_IO, Ada.Integer_Text_IO, Utils;
use Ada.Text_IO, Ada.Integer_Text_IO;

--
-- OrderSystem
--
-- Author: Thomas Taylor
--
-- Allows Orders to be added to the system by a Cashier,
-- received by a Cook and cooked.
--

procedure OrderSystem is

        --
        -- ProtectedOrderList
        --

		--
                -- Order record
                --
           	-- stores information about an order: order number, time
                -- of order and cashier taking the order
                --
                -- Also has a reference to the next order, which forms
                -- a linked list data structure
                --
           	type Order;
            	type OrderAccess is access Order;
           	type Order is record
          		OrderNo:Integer;
          		Time:String(1..19);
              		Cashier:String(1..15);
              		NextOrder:OrderAccess;
        	end record;

		--
                -- Protected orderlist - ensures data integrity when
                -- being accessed by multiple tasks
                --
                protected type ProtectedOrderList is

               		-- adds an order to the list
           		procedure AddOrder (orderToAdd:OrderAccess);

        		-- creates a new order with the passed cashier
        		procedure CreateOrder (Cashier: in String; NewOrder: out OrderAccess);

           		-- gets the next order, and removes from the list
           		entry GetOrder (Order: out OrderAccess);

                        -- gets the number of orders currently pending
                        function GetCurrentOrders return Integer;

               		-- prints out each item to the console window
                	procedure PrintList;

                private

         		-- the next order in the list
           		HeadOrder:OrderAccess;
           		-- ...and the final order
                        TailOrder:OrderAccess;

           		-- total number of orders
           		OrderTotal:Integer := 0;

                        -- current number of pending orders
                        CurrentOrders:Integer := 0;

                        -- the next order id
           		OrderID:Integer := 1;

                end ProtectedOrderList;

        	protected body ProtectedOrderList is

                	--
                	-- adds an order to the list
                        --
           		procedure AddOrder (OrderToAdd:OrderAccess) is
                        begin

              			--Put_Line("OrderList.AddOrder: OrderNo: " & Integer'Image(OrderToAdd.OrderNo) & " OrderTotal: " & Integer'Image(OrderTotal) & " CurrentOrders: " & Integer'Image(CurrentOrders));
				Put_Line("Order " & Integer'Image(OrderToAdd.OrderNo) & " taken by " & OrderToAdd.Cashier & " at " & OrderToAdd.Time);

              			if CurrentOrders = 0 then
                 			-- need to set the head of the queue
                                        HeadOrder := OrderToAdd;
                       		elsif CurrentOrders = 1 then
                                	-- one order, so set the head's next order
                        		HeadOrder.NextOrder := OrderToAdd;
                                elsif CurrentOrders > 1 then
                                	-- more than one order, so set the tail of the queue
                        		TailOrder := OrderToAdd;
              			end if;

              			-- increment the order total
              			OrderTotal := OrderTotal + 1;
                                -- increment the current orders
                                CurrentOrders := CurrentOrders + 1;
                        end AddOrder;

        		--
                        -- Creates a new order with the given cashier
                        --
                        procedure CreateOrder (Cashier: in String; NewOrder: out OrderAccess) is
           		begin
				-- set the output
                        	NewOrder := new Order'(OrderNo => OrderID, Time => Utils.GetCurrentTime, Cashier => Cashier, NextOrder => null);
                                -- increment the order id
                                OrderID := OrderID + 1;

                        end CreateOrder;

        		--
           		-- gets the next order, and removes from the list
                        --
           		entry GetOrder (Order: out OrderAccess) when CurrentOrders /= 0 is

        		begin
                                -- set the output
        			Order := HeadOrder;
        			-- update the next order
              			HeadOrder := HeadOrder.NextOrder;
                                -- decrement the current orders
                                CurrentOrders := CurrentOrders - 1;
                        end GetOrder;

			--
                        -- Returns the number of orders currently pending in the system
                        --
                        function GetCurrentOrders return Integer is
                        begin
                        	return CurrentOrders;
                        end GetCurrentOrders;

        		--
               		-- loops through each item in the list, and
                        -- prints out each item to the console window
                        --
                	procedure PrintList is
                        	OrderToPrint:OrderAccess := HeadOrder;
               		begin
        			Put_Line("OrderList.PrintList: " & Integer'Image(GetCurrentOrders));

                		for I in 0..GetCurrentOrders loop
                			Put_Line("Order " & Integer'Image(OrderToPrint.OrderNo) & ": " & OrderToPrint.Cashier & " " & OrderToPrint.Time & " ");
                                	OrderToPrint := OrderToPrint.NextOrder;
            			end loop;
                        end PrintList;

                end ProtectedOrderList;

        -- The OrderList
        LocalOrderList:ProtectedOrderList;

        --
        -- Cashier
        --

              	--
                -- Cashier record
                --
           	-- stores information about a cashier: name, # orders taken
                --
        	type CashierRecord is record
          		Name:String(1..15);
              		OrdersTaken:Integer := 0;
        	end record;

		--
           	-- Simulates the cashier taking a new order
           	--
           	procedure TakeOrder (Cashier:CashierRecord);
           	procedure TakeOrder (Cashier:CashierRecord) is
                        -- the delay time to take the order
                        Random:Duration := Utils.GenerateRandomNumber(0,5);
                        -- the order taken
                        NewOrder:OrderAccess;
           	begin
			-- create the order
                        LocalOrderList.CreateOrder(Cashier.Name, NewOrder);
                        -- add the order to the system
                        LocalOrderList.AddOrder(NewOrder);
                        -- delay to simulate the time taken to take the order
                        delay Random;
              	end TakeOrder;

                --
                -- The main task - adds orders infinitely
                --
                task type CashierTask is
                	entry SetCashier (CashierInput : in  CashierRecord);
                end CashierTask;

        	task body CashierTask is
                	-- a reference to the cashier
                	Cashier:CashierRecord;
                begin
                	-- set the cashier
                	accept SetCashier (CashierInput : in  CashierRecord) do
                   		Cashier := CashierInput;
                                Put_Line("Cashier.CashierTask: Set Cashier to " & Cashier.Name);
                	end SetCashier;
			-- the main loop
          		loop
            			TakeOrder(Cashier);
          		end loop;
                end CashierTask;

        --
        -- Cook
        --

		--
                -- Cook record
                --
           	-- stores information about a cook: name, # orders cooked
                --
                type CookRecord is record
          		Name:String(1..15);
              		OrdersCooked:Integer := 0;
        	end record;

		--
           	-- Simulates the cook cooking an order
           	--
           	procedure CookOrder (Cook:CookRecord);
           	procedure CookOrder (Cook:CookRecord) is
                	-- the amount of time to delay
        		Random:Duration := Utils.GenerateRandomNumber(1,7);
        		-- the order currently being cooked
                        OrderToCook:OrderAccess;
           	begin
                	-- if the cook's unable to get an order, delay for 3 seconds
                        select
                        	-- gets an order from the list
                 		LocalOrderList.GetOrder(OrderToCook);
                                -- delays to simulate the cooking process
                                delay Random;
                                Put_Line("Order " & Integer'Image(OrderToCook.OrderNo) & " cooked and delivered by " & Cook.Name & " in " & Duration'Image(Random) & " seconds");
			or
      				delay 3.0;
                                Put_Line("OrderList has been busy for 3 seconds, giving up");
			end select;
              	end CookOrder;

                --
                -- The main task - infinitely looks for new orders to cook
                --
                task type CookTask is
                	entry SetCook (CookInput : in  CookRecord);
                end CookTask;

        	task body CookTask is
                	-- reference to the cook record
                	Cook:CookRecord;
                begin
                	-- set the cook
                	accept SetCook (CookInput : in  CookRecord) do
                   		Cook := CookInput;
                                Put_Line("Cook.CookTask: Set Cook to " & Cook.Name);
                	end SetCook;
			-- the main loop
          		loop
                        	-- look for an order (providing there are any)
                        	if LocalOrderList.GetCurrentOrders > 0 then
					CookOrder(Cook);
                                end if;
          		end loop;
                end CookTask;

        --
	-- References to the cashier and cook tasks and their corresponding records
        --

                Cashier1:CashierRecord;
                Cashier2:CashierRecord;

                Cashier1Task:CashierTask;
                Cashier2Task:CashierTask;

		Cook1:CookRecord;
       		Cook2:CookRecord;

        	Cook1Task:CookTask;
        	Cook2Task:CookTask;

begin
	-- Set the names of the cooks & cashiers
        Cook1.Name := "Chuck Bartowski";
        Cook2.Name := "Morgan Grimes  ";
        Cashier1.Name := "John Casey     ";
        Cashier2.Name := "Sarah Walker   ";

	-- Set the tasks' cashiers/cooks as appropriate
     	Cook1Task.SetCook(Cook1);
        Cook2Task.SetCook(Cook2);
        Cashier1Task.SetCashier(Cashier1);
        Cashier2Task.SetCashier(Cashier2);

end OrderSystem;
