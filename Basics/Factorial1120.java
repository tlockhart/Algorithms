//Compile: javac -cp .;algs4_ts.jar Factorial1120.java
//Run: java -cp .;algs4_ts.jar Factorial1120 5
import java.lang.Math;
import java.lang.Double;
	public class Factorial1120 //no args on class definition
	{
		public static double factorial(double n)
		{
			if (n==0) {return 1;}
			if (n>0){return n*factorial(n-1);}
			return -1;
		}
		public static void main(String[] args)
		{
			double number = factorial(Double.parseDouble(args[0]));
			StdOut.println();
			StdOut.println("Factorial of "+args[0]+" = "+number);
			StdOut.println("Log = "+Math.log10(number));
		}
	}
