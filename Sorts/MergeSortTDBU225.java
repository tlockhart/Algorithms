//compile: javac -cp .;algs4_ts.jar MergeSortTDBU225.java
//run: java -cp .;algs4_ts.jar MergeSortTDBU225 <mergeSort.txt
//run: java -cp .;algs4_ts.jar MergeSortTDBU225 <39Chars.txt
public class MergeSortTDBU225
{
	private static Comparable[] aux;
	private static double xCoord = 10;
	private static double dim = 1800;
	private static final double xDim = 800;
	private static final double yDim = 1800;
	private static int dec = 80;
	
	public static void tdSort(Comparable[] a)
	{
		int N = a.length;
		StdOut.println("Top Down MergeSort");
		printHeader(a);
		printArray(a);
		//StdOut.println("Top Down MergeSort");
		aux = new Comparable[a.length];
		tdSort(a, 0, a.length - 1);
		//StdOut.println("Top Down MergeSort");
	}
	
	private static void tdSort(Comparable[] a, int lo, int hi)
	{		
		if(hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		
		//StdOut.println(" SZ1 = "+(mid-lo)+" SZ2 = "+(hi-(mid+1)));
		tdSort(a, lo, mid);
		tdSort(a, mid+1, hi);
		//If left half and right half are tdSorted, no need to merge (16 counts without, 13 merges with)
		if(!less(a[mid], a[mid+1]))
		{
			//StdOut.print("Lo = "+lo+" Mid = " +mid+" hi = "+hi+" SZ1 = "+(mid-lo)+" SZ2 = "+(hi-(mid+1))+" ");
			//StdOut.println(" SZ1 = "+(mid-lo)+" SZ2 = "+(hi-(mid+1)));
			tdMerge(a, lo, mid, hi);
		}
		//No merge was made
		else if(less(a[mid], a[mid+1]))
		{
			StdOut.println(" substringSZ1 = "+((mid+1)-lo)+" substringSZ2 = "+((hi+1)-(mid+1)));
			//StdOut.println("Lo = "+lo+" Mid = " +mid+" hi = "+hi+" SZ1 = "+(mid-lo)+" SZ2 = "+(hi-(mid+1)));
			printArray(a, lo, mid, hi);
		}
	}
	private static void buSort(Comparable[] a)
	{
		StdOut.println("BottomUP MergeSort");
		int N = a.length;
		printHeader(a);
		printArray(a);
		
		aux = new Comparable[N];
		for(int sz = 1; sz < N; sz = sz+sz)
		{
			StdOut.println("Size = "+sz);
			for(int lo=0; lo < N-sz; lo += sz+sz)
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
		}//for
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
		MergeSortTDBU225.printArray(a, lo, mid, hi);
	}
	public static void tdMerge(Comparable[] a, int lo, int mid, int hi)
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
		//StdOut.println(" SZ1 = "+(mid-lo)+" SZ2 = "+(hi-(mid+1)));
		StdOut.println(" substringSZ1 = "+((mid+1)-lo)+" substringSZ2 = "+((hi+1)-(mid+1)));
		MergeSortTDBU225.printArray(a, lo, mid, hi);
		
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
		//MergeSortTDBU225.printArray(a, a.length-1);
	}
	
	public static void printArray(Comparable[] a, int lo, int mid, int hi)
	{
		int N = a.length;
		
		xCoord = 0;
		StdDraw.text(xCoord+=30, dim-=dec,Integer.toString(lo));
		StdDraw.text(xCoord+=40, dim,Integer.toString(mid));
		StdDraw.text(xCoord+=40, dim,Integer.toString(hi));
		StdOut.printf("%-3s",lo);
		StdOut.printf("%-3s",mid);
		StdOut.printf("%-3s",hi);
		for (int i = 0; i < N; i++)
		{
			//StdOut.printf("%-3s",lo,"%-3s",mid,"%-3s",hi);
			//stdOut.printf(
			StdOut.printf("%-3s",a[i]);
			if(((i>=lo)&&(i<=mid))||(i>=mid+1)&&(i<=hi))
			{
				drawRed();
			}
			else 
				drawBlack();
			StdDraw.text(xCoord+=40, dim, a[i].toString());
		}
		StdOut.println(" ");
	}
	public static void printArray(Comparable[] a)
	{
		int N = a.length;
		xCoord = 0;
		StdDraw.text(xCoord+=30, dim-=dec,Integer.toString(0));
		StdDraw.text(xCoord+=40, dim,Integer.toString(0));
		StdDraw.text(xCoord+=40, dim,Integer.toString(0));
		StdOut.printf("%-3s",0);
		StdOut.printf("%-3s",0);
		StdOut.printf("%-3s",0);
		for (int i = 0; i < N; i++)
		{
			//StdOut.printf("%-3s",+lo+"%-3s",+mid+"%-3s",+hi);
			StdOut.printf("%-3s",a[i]+" ");
			StdDraw.text(xCoord+=40, dim, a[i].toString());
		}
		StdOut.println(" ");
		StdOut.println("*******************************************************");	
	}
	public static void drawRed()
	{
		StdDraw.setXscale(0, xDim);
		StdDraw.setYscale(0, yDim);
		//StdDraw.setScale(-.05, 1.05);
		StdDraw.setPenColor(StdDraw.RED);
	}
	public static void drawBlack()
	{
		StdDraw.setXscale(0, xDim);
		StdDraw.setYscale(0, yDim);
		//StdDraw.setScale(-.05, 1.05);
		StdDraw.setPenColor(StdDraw.BLACK);
	}
	public static void printHeader(Comparable[] a)
	{
		drawBlack();
		StdDraw.text(xCoord+=20, dim-=80,"LO");
		StdDraw.text(xCoord+=40, dim,"MI");
		StdDraw.text(xCoord+=40, dim,"HI");
		StdOut.printf("%-3s","LO");
		StdOut.printf("%-3s","MI");
		StdOut.printf("%-3s","HI");
		//StdOut.print("LO "+"MI "+"HI ");
		for (int i =0; i<a.length; i++)
		{
			StdOut.printf("%-3s",i+" ");
			StdDraw.text(xCoord+=40, dim, Integer.toString(i));
		}
		StdOut.println("");
	}
	
	public static void main (String[] args)
	{
		//Read file
		String[] a = StdIn.readAllStrings();
		int N = a.length-1;
		StdOut.println("Length = "+N);
		/***********************************/
		//MergeSortTDBU225.tdSort(a);//Test1  
		MergeSortTDBU225.buSort(a);//Test 2
		/***********************************/
	}
}