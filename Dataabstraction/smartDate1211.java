//Compile:c:\Users\tonyl\algs4\exercises>javac -cp .;algs4_ts.jar smartDate1211.java
//RUN:c:\Users\tonyl\algs4\exercises>java -cp .;algs4_ts.jar smartDate1211 6 7 2017

public class smartDate1211{
	private final int value;
	//constructor and nonstatic methods
	public smartDate1211(int m, int d, int y)
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
	public static void main(String[] args)
	{
		int m = Integer.parseInt(args[0]);
		int d = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		validateDate(m,d,y);
		Date date = new Date(m, d, y);
		StdOut.println(date);
	}
}