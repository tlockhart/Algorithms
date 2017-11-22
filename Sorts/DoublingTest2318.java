package sorts;

//Import classes from algs4
import exercises.*;
import java.math.*;


 /**  <p><b>Requirement:</b> Class must be in <b>sorts</b> package and <b>exercises.*</b> and <b>java.lang.*</b> must be imported.</p>
  *compile Single: javac -cp .;algs4_sts.jar; DoublingTest2318.java
  *compile Multiple: javac -cp .;algs4_sts.jar; Quicksort2318_DoublingTest.java DoublingTest2318.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.DoublingTest2318
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

	
	3)DoublingTest2318.java:Medianof3 Partition
	 N			Time
	********	*********
	15)4096000 4.39200000
	16)8192000 11.82500000
	
	4)DoublingTest2319.java: Medianof5 Partition
	 N			Time
	********	*********
	15)4096000 4.45000000
	16)8192000 11.94300000
	*/

public class DoublingTest2318
{
	public static final int MAX = 1000;
	public static int N;
	public static int[] a;
	public static Stopwatch timer;
	public static final int numOfTrials = 16;
	//public static final int numOfTrials = 1;//15;
	public static DoublingTest dt;
	
	//contructor
	public DoublingTest2318(int N)
	{
		DoublingTest2318.N = N;
		DoublingTest2318.a = new int[N];	
		DoublingTest2318.setArray(N);
	}
	public static void setArray(int N)
	{
		for (int i = 0; i < N; i++)
		{
			//a[i] = StdRandom.uniform(0, N);
			a[i] = StdRandom.uniform(-MAX, MAX);
		}
	}
	public static void printTime()
	{
		double time = timer.elapsedTime();
		StdOut.printf("%7d %5.8f\n", N, time);
	}
	public static void startTimer(int N)
	{ 
		DoublingTest2318.timer = new Stopwatch();
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
		//for (int N = 10; ctr<numOfTrials; N += N)
		for (int N = 250; ctr<numOfTrials; N += N)
		{ // Print time for problem size N.
			DoublingTest2318 dt = new DoublingTest2318(N);
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