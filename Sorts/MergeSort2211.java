//compile: javac -cp ".;algs4_ts.jar;InsertionSort2211.jar" MergeSort2211.java
//run: java -cp ".;algs4_ts.jar;InsertionSort2211.jar" MergeSort2211 <mergeSortLong.txt
public class MergeSort2211
{
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a)
	{
		aux = new Comparable[a.length];
		int lo = 0;
		int hi = a.length-1;
		sort(a, aux, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
	{
		if(hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		int loLength = mid;
		//StdOut.println("LoLength = "+loLength);
		int hiLength = hi - (mid+1);
		//StdOut.println("HiLength = "+hiLength);
		//2.2.11 PartA: If substring is less than or equal to 15 use insertion sort(Printed 7 rows b4, 31 after using mergeSortLong.txt.
		if(loLength <= 15)
		{
			//For Part A, the problem was we had 0 instead of "lo" as the argument
			Insertion.sort(a, lo, mid);
			//InsertionSort2211.insertionSort(a, 0, mid);
			//StdOut.println("InsertionSort Lt True");
		}
		else
		{
			//2.2.11 PART C: SWOPPED A Array with AUX ARRAY
			sort(a, aux, lo, mid);
		}
		//2.2.11 PartA: If substring is less than or equal to 15 use insertion sort(Printed 7 rows b4, 31 after using mergeSortLong.txt.
		if(hiLength <= 15)
		{
			//For Part A, the problem was we had 0 instead of "lo" as the argument
			Insertion.sort(a, lo, hi);
			//InsertionSort2211.insertionSort(a, mid+1, hi);
			//StdOut.println("InsertionSort Rt True");
		}
		else
		{
			//2.2.11 PART C: SWOPPED A Array with AUX ARRAY
			sort(a, aux, mid+1, hi);
		}

		//2.2.8 Part B: If left half and right half are sorted, no need to merge (16 counts without, 13 merges with, using mergeSort.txt)
		if(!(less(a[mid], a[mid+1])))
		{
			//2.2.11 PART C: SWOPPED A Array with AUX ARRAY
			merge(a, aux, lo, mid, hi);
		}
		//2.2.11 Part B: Stop if array is already sorted
		else if (less(a[mid], a[mid+1]))
			return;
		//StdOut.println("MergeSort True");
	}
	
	public static void merge(Comparable[] a, Comparable[] aux, 
	int lo, int mid, int hi)
	{
		int i = lo;
		int j = mid+1;
		//2.2.11 Part C: Eliminate the aux array and use a instead
		/*************************************************/
		/*for (int k = lo; k <= hi; k++)
			aux[k] = a[k];*/
		/*************************************************/
		
		/*************************************************/
		/*2.2.11 Part C: Eliminate the copy above from a to aux array.   
		Instead, just copy the A array elements to the aux array, when 
		sorting the array.  But copy the elements from the a array
		into the aux array, when merging the left or right side of the a Array
		to the right side of the Aux Array*/
		/**************************************************/
		for (int k = lo; k <= hi; k++)
		{
			if(i > mid) 					aux[k] = a[j++];/*aux[j++];*/
			else if(j > hi) 				aux[k] = a[i++];/*aux[i++];*/
			else if(less(a[j], a[i])) 	aux[k] = a[j++];/*right side of a array*/
			else 							aux[k] = a[i++];/*left side of a array*/
		}
		MergeSort228.printArray(a, a.length-1);
		/*************************************************/
	}
	
	private static boolean less(Comparable v, Comparable w)
	{ 
		//P247
		//When v<w return -1
		//When v=w return 0
		//When v>w return +1
		
		//If the value before a[j] (a[j-1]) is  
		//greater than a[j] then call exchange.
		return v.compareTo(w) < 0; 
		//MergeSort228.printArray(a, a.length-1);
	}
	
	public static void printArray(Comparable[] a, int N)
	{
		for (int i = 0; i <= N; i++)
		{
			StdOut.print(a[i]+" ");
		}
		StdOut.println(" ");
	}
	
	public static void main (String[] args)
	{
		//Read file
		String[] a = StdIn.readAllStrings();
		int N = a.length-1;
		StdOut.println("Length = "+N);
		//mid = N/2;
		MergeSort2211.printArray(a, N);
		MergeSort2211.sort(a);
	}
}