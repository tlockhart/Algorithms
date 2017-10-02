//Compile:  javac -cp .;algs4_ts.jar transaction1213.java 
//Run command: java -cp .;algs4_ts.jar transaction1213 05/31/2017,ACC0123,567.90
public class transaction1213{
	private static String who;
	private static double amount;
	private static Date when;
	private static String[] parsedTransaction;
	private static String unparsedTransaction;
	private static CharSequence amountDelimiter = ".";
	private static String dateDelimiter = "/";
	//private static String spaceDelimiter = "[ ]";
	private static String spaceDelimiter = ",";
	
	public transaction1213(String transaction){
			//StdOut.println("In contructor");
			parseValue(transaction);
	}
	/*public transaction1213(String who, Date when, double amount){	
	}*/
	
	public void parseValue(String value)
	{
		//StdOut.println("In parseValue");
		
		//1) Split data into components
		parsedTransaction = value.split(spaceDelimiter);
		//2) Determine which array element contains which data type
		for(int i=0; i<parsedTransaction.length; i++)
		{
			if(parsedTransaction[i].contains(dateDelimiter))
			{
				//Date Formate <mm/dd/yyyy>
				String[] parsedDate= parsedTransaction[i].split(dateDelimiter);
				//for(j=0; j<when.length; j++)
				//{
					int m = Integer.parseInt(parsedDate[0]);
					int d = Integer.parseInt(parsedDate[1]);
					int y = Integer.parseInt(parsedDate[2]);
					when =new Date(m, d, y);
				//}
			}
			else if(parsedTransaction[i].contains(amountDelimiter))
			{
				amount = Double.parseDouble(parsedTransaction[i]);
			}
			else
				who = parsedTransaction[i];
		}//for
	}
	public static void main(String[] args)
	{
		/***************************************************/
		/*Create a transaction object from the constructor:
		/*When creating the object, it no longer matters if
		/*if the method (parseValue) it calls is static, 
		/*because there is a reference object*/
		/***************************************************/
		transaction1213 transaction = new transaction1213(args[0]);
	
		StdOut.printf("%-15s", who);
		StdOut.printf("%-15s", when);
		StdOut.printf("%-15s", amount);	
	}
}