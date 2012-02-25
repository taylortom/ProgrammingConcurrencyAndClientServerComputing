with Ada.Text_IO, Ada.Integer_Text_IO, Cashier, Cook, OrderList;
use Ada.Text_IO, Ada.Integer_Text_IO;

procedure Main is

	Cook1:Cook.CookRecord;
        Cook2:Cook.CookRecord;

        Cashier1:Cashier.CashierRecord;
        Cashier2:Cashier.CashierRecord;

begin

        Cook1.Name := "Nigel Richards ";
	--Cook.CookOrder(Cook1);

        Cashier1.Name := "Thomas Taylor  ";
        Cashier.TakeOrder(Cashier1);
        Cashier.TakeOrder(Cashier1);
        Cashier.TakeOrder(Cashier1);
        Cashier.TakeOrder(Cashier1);

        OrderList.PrintList;

end Main;
