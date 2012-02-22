// TODO Utils: add arraySearchAndRemove

package utils;

// Java imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Basic tools class with static methods to carry out various functions
 * 
 * @author Tom
 * @version 0.1
 * @history Aug 29, 2011: Created class
 */
public class Utils 
{   
	/**
	 * Checks if the supplied object is empty according its type
	 * Currently supports: 
	 * - String
	 * - ArrayList
	 * @param objToCheck
	 * @return
	 */
	public static Boolean isEmpty(Object objToCheck)
	{
		if (objToCheck instanceof String) 
		{		
			String s = (String)objToCheck;
			if (s == "" || s == " " || s.isEmpty()) return true;
		}
		else if (objToCheck instanceof ArrayList) 
		{			
			ArrayList al = (ArrayList)objToCheck;
			if (al.size() == 0) return true;
		}

		return false; 
	}

	/**
	 * Searches the passed ArrayList to see if it contains searchObject
	 * @param arrayToSearch of type ArrayList
	 * @param searchObject
	 * @return Boolean depending on whether the list contains searchObject
	 */
	public static Object arraySearch(ArrayList arrayToSearch, Object searchObject)
	{	
		for (int i = 0; i < arrayToSearch.size(); i++) 
		{
			if (arrayToSearch.get(i).equals(searchObject)) return arrayToSearch.get(i);
		}

		return false;
	}

	/**
	 * Randomly shuffles the passed array
	 * @param arrayToShuffle of type ArrayList
	 * @return the shuffled array
	 */
	public static List arrayShuffle(List arrayToShuffle)
	{
		Collections.shuffle(arrayToShuffle);
		return arrayToShuffle;
	}

	/**
	 * Searches the passed string for the and replaces with a new value
	 * @param string to search
	 * @param string to search for
	 * @param string to replace searchString
	 * @return the amended string
	 */
	public static String stringSearchAndReplace(String stringToSearch, String searchString, String replacementString)
	{
		int pos = stringToSearch.indexOf(searchString);

		while (pos >= 0)
		{
			stringToSearch = stringToSearch.substring(0, pos) + replacementString + stringToSearch.substring(pos + searchString.length());
			pos = stringToSearch.indexOf(searchString, pos + replacementString.length());
		}

		return stringToSearch;
	}

	/**
	 * Splits up the passed string into an ArrayList of string objects
	 * @param stringToSplit
	 * @param delimiter the value used to split the string
	 * @return ArrayList of string objects
	 */
	public static ArrayList<String> stringSplit(String stringToSplit, String delimiter) 
	{
		if (delimiter == null) delimiter = " ";
		ArrayList<String> stringList = new ArrayList<String>();
		String currentWord = "";

		for (int i = 0; i < stringToSplit.length(); i++) 
		{
			String s = Character.toString(stringToSplit.charAt(i));

			if (s.equals(delimiter)) 
			{
				stringList.add(currentWord);
				currentWord = "";
			} 
			else { currentWord += stringToSplit.charAt(i); }

			if (i == stringToSplit.length() - 1) stringList.add(currentWord);
		}

		return stringList;
	}

	/**
	 * Removes any leading/trailing spaces from passed string
	 * @param stringToTrim
	 * @return trimmed string
	 */
	public static String stringTrim(String stringToTrim) 
	{
		// remove any leading whitespace
		for (int i = 0; i < stringToTrim.length(); i++) 
		{
			if (stringToTrim.charAt(i) == ' ') continue;
			else 
			{
				stringToTrim = stringToTrim.substring(i);
				break;
			}
		}

		// ...remove any trailing whitespace
		for (int i = stringToTrim.length()-1; i > 0; i--) 
		{
			if (stringToTrim.charAt(i) == ' ') continue;
			else 
			{
				stringToTrim = stringToTrim.substring(0, i+1);
				break;
			}
		}

		return stringToTrim;
	}

	/**
	 * Converts the passed string to its equivalent boolean value
	 * @param stringToConvert
	 * @return the boolean equivalent
	 * @todo convert to use switch statement
	 */
	public static Boolean stringToBool(String stringToConvert) 
	{
		stringToConvert = stringToConvert.toLowerCase();

		if (stringToConvert.equals("y")) return true;
		else if (stringToConvert.equals("yes")) return true;
		else if (stringToConvert.equals("true")) return true;
		else if (stringToConvert.equals("n")) return false;
		else if (stringToConvert.equals("no")) return false;
		else if (stringToConvert.equals("false")) return false;
		else 
		{
			System.out.println("Utils.stringToBool: Error '" + stringToConvert + "' not recognised");
			return false;
		}	
	}

	/**
	 * Converts the passed string into its equivalent integer value
	 * @param stringToConvert
	 * @return equivalent integer value
	 */
	public static int stringToInt(String stringToConvert) 
	{
		try 
		{
			return Integer.parseInt(stringToConvert);
		} 
		catch (NumberFormatException nfe) 
		{
			System.out.println("Utils.stringToInt: Error '" + stringToConvert + "' cannot be converted to an integer");
			return 0;
		}
	}

	/**
	 * Gets the current date/time and formats according to the passed param
	 * Accepted values as per the SimpleDateFormat:
	 * 
	 * G	Era designator			Text				AD
	 * y	Year						Year				1996; 96
	 * M	Month in year			Month				July; Jul; 07
	 * w	Week in year			Number			27
	 * W	Week in month			Number			2
	 * D	Day in year				Number			189
	 * d	Day in month			Number			10
	 * F	Day of week in month	Number			2
	 * E	Day in week				Text				Tuesday; Tue
	 * a	Am/pm marker			Text				PM
	 * H	Hour in day (0-23)	Number			0
	 * k	Hour in day (1-24)	Number			24
	 * K	Hour in am/pm (0-11)	Number			0
	 * h	Hour in am/pm (1-12)	Number			12
	 * m	Minute in hour			Number			30
	 * s	Second in minute		Number			55
	 * S	Millisecond				Number			978
	 * 
	 * @param the date/time format 
	 * @return the date in the correct format (e.g. "yyyy/MM/dd HH:mm:ss")
	 */
	public static String generateTimeStamp(String format)
	{
		if (Utils.isEmpty(format)) format = "yyyy/MM/dd HH:mm:ss";

		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * Formats a date using the passed format (see above)
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format)
	{
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * Returns a unique id based on the current time
	 * @param stringToConvert
	 * @return equivalent integer value
	 */
	public static synchronized String generateUniqueId(String prefixString) 
	{
		prefixString += "-" + generateTimeStamp("yyMM");
		return String.format("%s%d%d%d%d", prefixString, generateRandomNumber(10), generateRandomNumber(10), generateRandomNumber(10), generateRandomNumber(10));
	}
	
	/**
	 * Generates a random integer between 0 and topBoundary
	 * @return random number
	 */
	public static int generateRandomNumber(int topBoundary)
	{
		Random r = new Random();
		return r.nextInt(topBoundary);
	}
	
	/**
	 * Generates a random integer (full range)
	 * @return random number
	 */
	public static int generateRandomNumber()
	{
		Random r = new Random();
		return r.nextInt();
	}

	/**
	 * Extends the passed string to the desired length
	 * Warning: trims strings which are longer
	 * @param stringToExtend
	 * @param length to extend to
	 * @return the new string
	 */
	public static String extendStringToLength(String stringToExtend, int length)
	{
		if(stringToExtend.length() >= length) return stringToExtend.substring(0, length-1);
		else 
		{
			for (int i = stringToExtend.length(); i < length; i++) stringToExtend += " ";
			return stringToExtend;
		}
	}
}