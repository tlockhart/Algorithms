//compile: javac -cp .;algs4_ts.jar MergeSort228.java
//run: java -cp .;algs4_ts.jar MergeSort228 <mergeSort.txt
public class MergeSort228
{
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a)
	{
		aux = new Comparable[a.length];
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi)
	{
		if(hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid);
		sort(a, mid+1, hi);
		//If left half and right half are sorted, no need to merge (16 counts without, 13 merges with)
		if(!less(a[mid], a[mid+1]))
		{
			merge(a, lo, mid, hi);
		}
	}
	
	public static void merge(Comparable[] a, int lo, int mid, int hi)
	{
		int i = lo;
		int j = mid+1;
		
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		for (int k = lo; k <= hi; k++)
		{
			if(i > mid) 					a[k] = aux[j++];
			else if(j > hi) 				a[k] = aux[i++];
			else if(less(aux[j], aux[i])) 	a[k] = aux[j++];
			else 							a[k] = aux[i++];
		}
		MergeSort228.printArray(a, a.length-1);
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
		MergeSort228.printArray(a, N);
		MergeSort228.sort(a);
		//MergeSort228.printArray(a, N);
	}
}