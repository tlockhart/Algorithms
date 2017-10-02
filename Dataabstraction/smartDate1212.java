//Compile:  javac -cp .;algs4_ts.jar smartDate1212.java
//Run command: java -cp .;algs4_ts.jar smartDate1212 05 31 2017
public class smartDate1212{
	private final int value;
	//constructor and nonstatic methods
	public smartDate1212(int m, int d, int y)
	{ 
		value = y*512 + m*32 + d; 
	}
	public int month()
	{ 
		return (value / 32) % 16; }
	public int day()
	{ 
		return value % 32; }
	public int year()
	{ 
		return value / 512; }
		
	//Calls Month, Day, and Year Methods
	public String toString()
	{ 
		return month() + "/" + day()+ "/" + year(); 
	}
	public static void validateDate(int m, int d, int y){
		try{
			if((d<1)||(d>31))
			{
				throw new Exception("Day is invalid");
			}
			if((m<1)||(m>12))
			{
				throw new Exception("Month is invalid");
			}
			if(((m==6)&&(d==31)&&(y==2009))||((m==2)&&(d==29)&&(y==2009)))
			{
				throw new Exception("Date is invalid");
			}
		}
		catch(Exception e)
		{
			StdOut.println(e);
		}
	}
	/**************************************************
	 * Caculates Day from 21rst Century Gregorian Date*	
	 **************************************************/
	
	public static int getDayOfTheWeekValue(int m, int d, int y)
	{
		String stringYear = Integer.toString(y);
		StdOut.println("Year Value = "+stringYear);
		
		String century = stringYear.substring(0,2);
		century = century +"00";
		StdOut.println("Century Substring Value = "+century);
		
		
		//1)Take the last 2 digits of the year
		String subStringYear = stringYear.substring(2,4);
		StdOut.println("Substring Value = "+subStringYear);
		
		//2)Divide by 4, discard any fraction
		int integerYear = Integer.parseInt(subStringYear);
		StdOut.println("Integer Value = "+integerYear);
		int intYearDiv4 =integerYear/4;
		int monthValue;
		
		switch(m)
		{
				case 1: monthValue = 1;
						break;
				case 2: monthValue = 4;
						break;
				case 3: monthValue = 4;
						break;
				case 4: monthValue = 0;
						break;
				case 5: monthValue = 2;
						break;
				case 6: monthValue = 5;
						break;
				case 7: monthValue = 0;
						break;
				case 8: monthValue = 3;
						break;
				case 9: monthValue = 6;
						break;
				case 10: monthValue = 1;
						break;
				case 11: monthValue = 4;
						break;
				case 12: monthValue = 6;
						break;
				default: monthValue = 0;
						break;
		}
		//3 Add 6 for century 2000
		if(century.equals("2000"))
		{
			intYearDiv4+=6;
		}
		
		//4)Add the day and monthKey Value and add last two digits in year
		intYearDiv4 = intYearDiv4+d+monthValue+integerYear/*intYRSec2LtDigit+intLastDigit*/;
		
		//5)Divide total by seven and take the remainder
		intYearDiv4=intYearDiv4%7;
		
		return intYearDiv4;
	}	
	public static String getDayOfTheWeek(int value)
	{
		String day;
		switch(value){
			case 1: day = "Sunday";
				break;
			case 2: day = "Monday";
				break;
			case 3: day = "Tuesday";
				break;
			case 4: day = "Wednesday";
				break;
			case 5: day = "Thursday";
				break;
			case 6: day = "Friday";
				break;
			case 7: day = "Saturday";
				break;
			default: day = "Invalid date";
				break;
		}
		return day;
	}
	public static void main(String[] args)
	{
		int m = Integer.parseInt(args[0]);
		int d = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		validateDate(m,d,y);
		int dayOfTheWeekValue = getDayOfTheWeekValue(m, d, y);
		StdOut.println("day of the week value = "+dayOfTheWeekValue);
		
		String dayOfTheWeek = getDayOfTheWeek(dayOfTheWeekValue);
		
		Date date = new Date(m, d, y);
		StdOut.println("Date = "+date+" Day = "+dayOfTheWeek);
	}
}