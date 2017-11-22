package sorts;

//Import classes from algs4
import exercises.*;
import java.math.*;
//import sorts.Quicksort2317_DoublingTest2317;
//import Quicksort2317_DoublingTest2317.*;

 /**  <p><b>Requirement:</b> Class must be in <b>sorts</b> package and <b>exercises.*</b> and <b>java.lang.*</b> must be imported.</p>
  *compile Single: javac -cp .;algs4_sts.jar; DoublingTest2317.java
  *compile Multiple: javac -cp .;algs4_sts.jar; Quicksort2317_DoublingTest.java DoublingTest2317.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.DoublingTest2317
  **/
  
  /*1)DoublingTest2317.java: Results of 16 tests Without Sentinel Value.
	 N			Time
	********	*********
	15)4096000 4.55000000
	16)8192000 11.69700000
	
	2)DoublingTest2317.java:Results for 16 tests With Sentinel Value.
	 N			Time
	********	*********
	15)4096000 4.41000000
	16)8192000 11.99900000
	*/

public class DoublingTest2317
{
	public static final int MAX = 1000;
	public static int N;
	public static int[] a;
	public static Stopwatch timer;
	//public static final int loopLimit = 1;
	public static final int loopLimit = 16;
	public static DoublingTest2317 dt;
	
	//contructor
	public DoublingTest2317(int N)
	{
		DoublingTest2317.N = N;
		DoublingTest2317.a = new int[N];	
		DoublingTest2317.setArray(N);
	}
	public static void setArray(int N)
	{
		for (int i = 0; i < N; i++)
		{
			a[i] = StdRandom.uniform(-MAX, MAX);
		}
	}
	public static void printTime()
	{
		double time = timer.elapsedTime();
		StdOut.printf("%7d %5.8f\n", N, time);
	}
	public static void startTimer(int N)
	{ // Time ThreeSum.count() for N random 6-digit ints.
		//int MAX = 1000000;
		//int[] 
		DoublingTest2317.timer = new Stopwatch();
		//int cnt = ThreeSum.count(a);
		//time = BigDecimal.valueOf();
		//return time;
	}
	public static String[] convertIntArrayToString(int[] a)
	{
		String[] strArray = new String[a.length];
		for (int i=0; i<a.length; i++)
		{
			strArray[i] = String.valueOf(a[i]);
		}
		return strArray;
	}
	public static void printArray(Comparable[] array)
	{
		for (int i=0; i<array.length; i++)
		{
			StdOut.print(array[i]+" ");
		}
		//StdOut.println("");
	}
	public static void main(String[] args)
	{ 
	StdOut.printf("%7s %5s\n", "N", "Time");
	StdOut.printf("%7s %5s\n", "**", "****");
	int ctr =0;
		Quicksort2317_DoublingTest qs = new Quicksort2317_DoublingTest();
		//10/06: for (int N = 10; ctr<=loopLimit; N += N)
			for (int N = 250; ctr<loopLimit; N += N)
		{ // Print time for problem size N.
			DoublingTest2317 dt = new DoublingTest2317(N);
			dt.startTimer(N);
			//10/03/2017: Run QuickSort
			/*************************/
			String[] strInput = convertIntArrayToString(dt.a);
			//printArray(strInput);
			qs.startQS(strInput);
			//BigDecimal returnTime = new BigDecimal(
			dt.printTime();
			ctr++;
		}//for
	}
}//DoublingTest2317