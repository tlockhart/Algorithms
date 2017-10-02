//Compile:  javac -cp .;algs4_ts.jar transactionEquality1214.java
//Run command1: java -cp .;algs4_ts.jar transactionEquality1214 05/31/2017,ACC0123,567.90
//Run command2: java -cp .;algs4_ts.jar transactionEquality1214 
public class transactionEquality1214{
	private String who;
	private double amount;
	private Date when;
	private int d;
	private int m;
	private int y;
	private String[] parsedTransaction;
	private String unparsedTransaction;
	private static CharSequence amountDelimiter = ".";
	private static String dateDelimiter = "/";
	private static String spaceDelimiter = ",";
	
	public transactionEquality1214(String transaction){
			//StdOut.println("In contructor");
			parseValue(transaction);
	}
	/*public transactionEquality1214(String who, Date when, double amount){	
	}*/
	
	public void parseValue(String value)
	{
		//1) Split data into components
		parsedTransaction = value.split(spaceDelimiter);
		//2) Determine which array element contains which data type
		for(int i=0; i<parsedTransaction.length; i++)
		{
			if(parsedTransaction[i].contains(dateDelimiter))
			{
				//Date Formate <mm/dd/yyyy>
				String[] parsedDate= parsedTransaction[i].split(dateDelimiter);
					m = Integer.parseInt(parsedDate[0]);
					d = Integer.parseInt(parsedDate[1]);
					y = Integer.parseInt(parsedDate[2]);
					when =new Date(m, d, y);
			}
			else if(parsedTransaction[i].contains(amountDelimiter))
			{
				amount = Double.parseDouble(parsedTransaction[i]);
			}
			else
				who = parsedTransaction[i];
		}//for
	}
	public boolean equals(transactionEquality1214 x)
	{
			if (this == x) return true;
			if(x == null) return false;
			if(this.getClass() != x.getClass()) {return false;}
			transactionEquality1214 that = (transactionEquality1214) x;
			if (this.who.equals(that.who)) {StdOut.println("Who1 = Who? "+(this.who.equals(that.who)));}
			else if (!(this.who.equals(that.who))){StdOut.println("Who2 = Who? "+(!(this.who.equals(that.who)))); return false;}
			if(this.amount != that.amount) {return false;}
			if(!(this.when.equals(that.when))) {StdOut.println("When != to When? "+(!(this.when.equals(that.when))));return false;}
			return true;
	}
	
	public static void main(String[] args)
	{
		/***************************************************/
		/*Create a transaction object from the constructor:
		/*When creating the object, it no longer matters if
		/*if the method (parseValue) it calls is static, 
		/*because there is a reference object*/
		/***************************************************/
		//Collect command line arguments 
		//transactionEquality1214 transaction = new transactionEquality1214(args[0]);
		
		//True Condition
		//transactionEquality1214 transaction1 = new transactionEquality1214("05/31/2017,ACC0123,567.91");
		
		//False Condition
		transactionEquality1214 transaction1 = new transactionEquality1214("05/31/2017,ACC2222,555.90");
		transactionEquality1214 transaction2 = new transactionEquality1214("05/31/2017,ACC0123,567.91");
		
		if(transaction1.equals(transaction2))
		{
			StdOut.println("Transaction is EQUAL to its arguments");
		}
		else
		{
			StdOut.println("Transaction is !EQUAL to its arguments");
		}
	
		StdOut.printf("Transaction1 %-15s", transaction1.who);
		StdOut.printf("Transaction1 %-15s", transaction1.when);
		StdOut.printf("Transaction1 %-15s", transaction1.amount);	
		StdOut.println();
		StdOut.printf("Transaction2 %-15s", transaction2.who);
		StdOut.printf("Transaction2 %-15s", transaction2.when);
		StdOut.printf("Transaction2 %-15s", transaction2.amount);	
		
	}
}