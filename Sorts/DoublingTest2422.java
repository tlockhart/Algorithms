package sorts;

//Import classes from algs4
import exercises.*;
import java.math.*;

/*DoublingTest generates a random input sequence, starting with 250 numbers, then doubles the length for each subsequent sequence.  
The experiment runs 16 (0-15) and returns the array length and the run time of the called class (PQUnorderedArray2422), which finds 
the max and min values in the array.*/

 /**  <p><b>Requirement:</b> Class must be in <b>sorts</b> package and <b>exercises.*</b> and <b>java.lang.*</b> must be imported.</p>
  *compile Single: javac -cp .;algs4_sts.jar; DoublingTest2422.java
  *compile Multiple: javac -cp .;algs4_sts.jar; Transaction2422.java PQUnorderedArray2422.java DoublingTest2422.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.DoublingTest2422
  **/
  
  /*1)DoublingTest2317.java: Results of 16 tests Without Sentinel Value.
  1) Calculated Num of Compares for Removal of 1 node = 2Log(26) = 2.82,  Acutual Comparisons in Sink = 4;
  2) Calculated Num of Compares for Insertion of 1 node = 1Log(26) = 1.42,  Acutual Comparisons in Swim = 2;
  */

public class DoublingTest2422
{
	//public static final int MAX = 1000;
	public static final int ASCIIMin = 97;
	public static final int ASCIIMax = 123;
	public static int N;
	public static int[] a;
	public static Stopwatch timer;
	//public static final int numOfTrials = 16;
	public static final int numOfTrials = 1;//15;
	public static DoublingTest dt;
	
	//contructor
	public DoublingTest2422(int N)
	{
		DoublingTest2422.N = N;
		DoublingTest2422.a = new int[N];	
		DoublingTest2422.setArray(N);
	}
	public static void setArray(int N)
	{
		for (int i = 0; i < N; i++)
		{
			//a[i] = StdRandom.uniform(0, N);
			//a[i] = StdRandom.uniform(-MAX, MAX);
			a[i] = StdRandom.uniform(ASCIIMin, ASCIIMax);
		}
	}
	public static void printTime()
	{
		double time = timer.elapsedTime();
		StdOut.printf("%7s %5s\n", "N", "Time");
		StdOut.printf("%7s %5s\n", "**", "****");
		StdOut.printf("%7d %5.8f\n", N, time);
	}
	public static void startTimer(int N)
	{ 
		DoublingTest2422.timer = new Stopwatch();
	}
	public static String[] convertIntArrayToString(int[] a)
	{
		char[] charArray = new char[a.length];
		String[] strArray = new String[a.length];
		for (int i=0; i<a.length; i++)
		{
			//strArray[i] = String.valueOf(a[i]);
			charArray[i] = (char)a[i];
			strArray[i] = Character.toString(charArray[i]);
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
	
	int ctr =0;
	//int N = 250; //Total numbers generated is 250(0-249), then doubled from there.
	int N = 26;  //26 letter in the alphabet
		PQUnorderedArray2422 qs = new PQUnorderedArray2422(N);
		//for (int N = 10; ctr<numOfTrials; N += N)
		for (; ctr<numOfTrials; N += N)
		{ // contructor: Sets up Instance Variable a by calling setArray() method
			DoublingTest2422 dt = new DoublingTest2422(N);
			dt.startTimer(N);
			//Convert the random int array to a string array
			/*************************/
			String[] strInput = convertIntArrayToString(dt.a);
			/************************************************************/
			/*START the Priority Queue and pass the array as string args*/
			/************************************************************/
			qs.startPQ(strInput);
			/************************************************************/
			dt.printTime();
			ctr++;
			System.out.println("************************END*******************************");
		}//for
	}//main
}//DoublingTest