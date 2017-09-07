//compile1: javac -cp .;algs4_ts.jar SortCompare2127.java

// Run with 4 String arguments: (1)alg1 (2)alg2 (3)N - Array Length (4)T - Repeat times
//java -cp .;algs4_ts.jar; SortCompare2125 Shell Insertion 128 100
/*
 *T1 = 0.005000000000000000104083408558608425664715468883514404296875
 *T2 = 0
 *For 128 random Doubles
 *Shell is 0.0 times faster than Insertion*/
 
//java -cp .;algs4_ts.jar; SortCompare2125 Shell Insertion 16384 100
/*
 *T1 = 0.409000000000000196731519963577738963067531585693359375
 *T2 = 30.191999999999982406961862579919397830963134765625
 *For 16384 random Doubles
 *Shell is 73.8 times faster than Insertion*/

//java -cp .;algs4_ts.jar; SortCompare2125 Shell Selection 128 100
/*
 *T1 = 0.00600000000000000012490009027033011079765856266021728515625
 *T2 = 0.003000000000000000062450045135165055398829281330108642578125
 *For 128 random Doubles
 *Shell is 0.5 times faster than Selection*/
 
//java -cp .;algs4_ts.jar; SortCompare2125 Shell Selection 16384 100
/*
 *T1 = 0.389000000000000178967951569575234316289424896240234375
 *T2 = 22.2300000000000039790393202565610408782958984375
 *For 16384 random Doubles
 *Shell is 57.2 times faster than Selection*/

//Error: NaN means "Not a Number" and is the result of undefined operations on floating point 
//numbers like for example dividing zero by zero. (Note that while dividing a non-zero 
//number by zero is also usually undefined in mathematics, it does not result in NaN but 
//in positive or negative infinity).
import java.math.*;
public class SortCompare2127
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