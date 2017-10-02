//Compile: javac -cp .;algs4_ts.jar Fibonacci1119.java
//Run: java -cp .;algs4_ts.jar Fibonacci1119

public class Fibonacci1119
{
	public static long F(int N)
	{
		if(N == 0) return 0;
		if(N == 1) return 1;
		return F(N-1) + F(N-2);
	}
	public static void main(String[] args)
	{
		for(int N=0; N<100; N++)
			StdOut.println(N+""+F(N));
	}
}