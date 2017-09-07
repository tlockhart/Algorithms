//create a jar: jar cvfm InsertionSortNoExchanges2125.jar manifest.txt InsertionSortNoExchanges2125.class
//compile1: javac -cp .;algs4_ts.jar SortCompare2125.java
//compile2: javac -cp ".;algs4_ts.jar;InsertionSortNoExchanges2125.jar" SortCompare2125.java

//run: java -cp ".;algs4_ts.jar;InsertionSortNoExchanges2125.jar" SortCompare2125

// Run with 4 String arguments: (1)alg1 (2)alg2 (3)N - Array Length (4)T - Repeat times
//java -cp ".;algs4_ts.jar;InsertionSortNoExchanges2125.jar" SortCompare2125 Noexchange2125 Selection 10000 100

//java -cp ".;algs4_ts.jar;InsertionSortNoExchanges2125.jar" SortCompare2125 InsertionX Insertion 10000 100

//Error: NaN means "Not a Number" and is the result of undefined operations on floating point 
//numbers like for example dividing zero by zero. (Note that while dividing a non-zero 
//number by zero is also usually undefined in mathematics, it does not result in NaN but 
//in positive or negative infinity).
import java.math.*;
public class SortCompare2125
{
	public static double time(String alg, Double[] a)
	{ 
		//Calls the appropriate STD Sorting algorithm based on the string arguments
		Stopwatch timer = new Stopwatch();
		if (alg.equals("Insertion")) Insertion.sort(a);
		if (alg.equals("Selection")) Selection.sort(a);
		if (alg.equals("Shell")) Shell.sort(a);
		if (alg.equals("Merge")) Merge.sort(a);
		if (alg.equals("Quick")) Quick.sort(a);
		if (alg.equals("Heap")) Heap.sort(a);
		if (alg.equals("Noexchange2125")) InsertionSortNoExchanges2125.insertionSort(a);
		
		return timer.elapsedTime(); 
	}
	public static double timeRandomInput(String alg, int N, int T)
	{ // Use alg to sort T random arrays of length N.
		double total = 0.0;
		Double[] a = new Double[N];
		for (int t = 0; t < T; t++)
		{ 
			// Perform one experiment (generate and sort an array).
			for (int i = 0; i < N; i++)
				a[i] = StdRandom.uniform();
			total += time(alg, a);
		}
		return total;
	}
	public static void main(String[] args)
	{
		String alg1 = args[0];
		String alg2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T = Integer.parseInt(args[3]);
		BigDecimal t1 = new BigDecimal(timeRandomInput(alg1, N, T)); // total for alg1
		//double t1 = timeRandomInput(alg1, N, T);
		StdOut.println("T1 = "+t1);
		BigDecimal t2 = new BigDecimal(timeRandomInput(alg2, N, T)); // total for alg2
		//double t2 = timeRandomInput(alg2, N, T);
		StdOut.println("T2 = "+t2);
		StdOut.printf("For %d random Doubles\n %s is", N, alg1);
		StdOut.printf(" %.1f times faster than %s\n", (t2.divide(t1, 2, BigDecimal.ROUND_HALF_UP)), alg2);//t2/t1, alg2);
		//StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
	}
}