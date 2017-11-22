package sorts;

//Import classes from algs4
import exercises.*;
import java.math.*;
//import sorts.Quicksort2318_DoublingTest;
//import Quicksort2318_DoublingTest.*;

 /**  <p><b>Requirement:</b> Class must be in <b>sorts</b> package and <b>exercises.*</b> and <b>java.lang.*</b> must be imported.</p>
  *compile Single: javac -cp .;algs4_sts.jar; DoublingTest.java
  *compile Multiple: javac -cp .;algs4_sts.jar; Quicksort2318_DoublingTest.java DoublingTest.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.DoublingTest
  **/
  
  /*With Sentinel Value in Quicksort2318 Partition Method: Best Results for 16 Tests 
	 N			Time
	********	*********
	15)4096000 	4.51600000
	16)8192000 	11.99800000
  
	Without Sentinel Value in Quicksort2318 Partition Method: Worse Results for 16 Tests 
	 N			Time
	********	*********
	15)4096000 	4.65500000
	16)8192000 	12.25300000
	
	3 Median Partition
	 N			Time
	********	*********
	15)4096000 	3.39200000
	16)8192000 	9.58600000
	*/

public class DoublingTest
{
	public static final int MAX = 1000;
	public static int N;
	public static int[] a;
	public static Stopwatch timer;
	public static final int loopLimit = 15;
	public static DoublingTest dt;
	
	//contructor
	public DoublingTest(int N)
	{
		DoublingTest.N = N;
		DoublingTest.a = new int[N];	
		DoublingTest.setArray(N);
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
		DoublingTest.timer = new Stopwatch();
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
		Quicksort2318_DoublingTest qs = new Quicksort2318_DoublingTest();
		for (int N = 250; ctr<=loopLimit; N += N)
		{ // Print time for problem size N.
			DoublingTest dt = new DoublingTest(N);
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
}//DoublingTest