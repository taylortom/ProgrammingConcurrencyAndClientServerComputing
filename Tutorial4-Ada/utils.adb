with GNAT.Calendar, Ada.Calendar, Ada.Command_Line, Ada.Numerics.Discrete_Random;
use GNAT.Calendar, Ada.Calendar, Ada.Command_Line;

package body Utils is

	function GetCurrentTime return String is
		Time_And_Date:Time;
		Year,Month,Day,Hour,Minute,Seconds:Integer;
		Subsecond:Day_Duration;
		MonthString,DayString,HourString,MinuteString,SecondString:String := "00";
		YearString:String := "0000";
		DateString:String := "00/00/0000";
		TimeString:String := "00:00:00";
	begin
		Time_And_Date := Clock;
		GNAT.Calendar.Split(Time_And_Date, Year, Month, Day, Hour, Minute, Seconds, Subsecond);

		-- DAY
		if Day < 10 then
			DayString := "0" & Integer'Image(Day)(2);
		else
			DayString := Integer'Image(Day)(1) & Integer'Image(Day)(2);
		end if;
		-- MONTH
		if Month < 10 then
			MonthString := "0" & Integer'Image(Month)(2);
		else
			MonthString := Integer'Image(Month);
		end if;
		-- YEAR
		YearString := Integer'Image(Year)(2..5);

		-- HOUR
		if Hour = 0 then
			HourString := "00";
		elsif Hour < 10 then
			HourString := "0" & Integer'Image(Hour)(2);
		else
			HourString := Integer'Image(Hour)(2..3);
		end if;

		-- MINUTE
		if Minute = 0 then
			MinuteString := "00";
		elsif Minute < 10 then
			MinuteString := "0" & Integer'Image(Minute)(2);
		else
			MinuteString := Integer'Image(Minute)(2..3);
		end if;

		-- SECOND
		if Seconds = 0 then
			SecondString := "00";
		elsif Seconds < 10 then
			SecondString := "0" & Integer'Image(Seconds)(2);
		else
			SecondString := Integer'Image(Seconds)(2..3);
		end if;

		-- Set the time string
		TimeString := HourString & ":" & MinuteString & ":" & SecondString;
		-- Set the date string
		DateString := DayString & "/" & MonthString & "/" & YearString;

		-- Assign the output variable
		return DateString & " " & TimeString;
	end GetCurrentTime;

	function GenerateRandomNumber (LowerBound,UpperBound:Integer) return Standard.Duration is

		subtype NumberRange is Integer range LowerBound..UpperBound;
		package RandomInteger is new Ada.Numerics.Discrete_Random(NumberRange);
		Seed:RandomInteger.Generator;

	begin

		RandomInteger.Reset(Seed);
		return Duration(RandomInteger.Random(Seed));

	end GenerateRandomNumber;

end Utils;
