//compile: javac -cp .;algs4_ts.jar AnimatedElementarySorts2117.java
//run: java -cp .;algs4_ts.jar AnimatedElementarySorts2117 <tiny.txt
public class AnimatedElementarySorts2117
{
	public static final int squaredDimensionHigh = 25;
	public static void selectionSort(Comparable[] a)
	{ 
		//Sort a[] into increasing order, by selecting the smallest remaining item
		int N = a.length;
		for(int i = 0; i < N; i++)
		{
			//ITEM:
			//P248: Find the smallest item in the array and exchange 
			//it with the first entry.  Then find the next smallest 
			//item and exchange it with the second entry.
			
			//Exhange a[i] with smallest entry in a[i+1..N].
			int min = i;
			for(int j = i+1; j < N; j++)
			{
				if(less(a[j], a[min])) min = j;
				exch(a, i, min);
				//show(a);
				graph(a);
			}
		}//for
	}//selectionSort
	public static void insertionSort(Comparable[] a)
	{
		int N = a.length;
		for(int i = 1; i< N; i++)
		{
			//IDEA:
			//P250: Make space to insert the current item by moving
			//larger items one position to the right, before inserting
			//the current item into the vacated position.  
			
			//As in selection
			//sort, the items to the left of the current index are in 
			//sorted order during the sort, but they are not in their
			//final position, as they may have to be moved to make room
			//for smaller items encountered later.  If the array is large 
			//and its entries are already in order, then insertion sort is
			//faster than if the entries are randomly ordered or in reverse 
			//order.
			
			//P258: Insertion sort is slow for large unordered arrays because
			//the only exchanges it does involves adjacent entries, so items 
			//can move through the array only one place at a time.
			
			//Insert a[i] among a[i-1], a[i-2], a[i-3]
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
			{
				exch(a, j, j-1);
				graph(a);
			}
		}
	}
	public static void shellSort(Comparable[] a)
	{
		//Sort a[] into increasing order
		int N = a.length;
		int h = 1;
		
		//h value is the number of inputs divided by 3(11/3=3, if h is an int)
		while (h < N/3) 
		{
			//h starts at 1 so h = (3*1)+1 = 4
			h = 3*h + 1; //1, 4, 13, 40
		}
		while (h >= 1)
		{// h-sort the array.
			for(int i = h; i < N; i++)
			{
				//P258: Shellsort is a simple extension of insertion sort that
				//gains speed by allowing exchanges of array entries that are
				//far apart, to produce partially sorted arrays that can be
				//efficiently sorted, eventually be insertion sort.
				
				/*IDEA:
				 *The idea is to rearrange the array to give it the property that
				 *taking every hth entry (starting anywhere) yields a sorted
				 *subsequence.  Such an array is said to be h-sorted.  By h-sorting
				 *for some large values of h, we can move items in the array long
				 *distances and thus make it easier to h-sort for smaller values
				 *of h.*/
				
				//Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]..
				
				//IF a[j-h] is greater than a[j] then exchange them.
				//and decrement j by h.
				for(int j = i; j >= h && less(a[j], a[j-h]); j-=h)
				{
					exch(a, j, j-h);
					graph(a);
				}
			}//for
			h = h/3;
		}//while
	}//shellSort
	
	private static boolean less(Comparable v, Comparable w)
	{ 
		//P247
		//When v<w return -1
		//When v=w return 0
		//When v>w return +1
		
		//If the value before a[j] (a[j-1]) is  
		//greater than a[j] then call exchange.
		return v.compareTo(w) < 0; 
	}
	private static void exch(Comparable[] a, int i, int j)
	{ 
		Comparable temp = a[i]; 
		a[i] = a[j]; 
		a[j] = temp; 
	}
	/*private static void show(Comparable[] a)
	{ // Print the array, on a single line.
		for (int i = 0; i < a.length; i++)
		StdOut.println(a[i] + " ");
		//StdOut.println();
	}*/
	@SuppressWarnings("unchecked")
	private static void graph(Comparable[] a)
	{
		char[] charArray;
		//Because comparable is a generic object that only has a 
		//compareTo method you must convert it to a String first.
		//charArray =	a.toString().toCharArray();
		
		charArray = a.toString().toCharArray();
		Double x1 = 0.0;
		Double y1 = 0.0;
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, squaredDimensionHigh+5);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.BLACK);
		
		//Clear the offscreen canvas
		StdDraw.clear();
		
		for (int i = 0; i< a.length; i++)
		{
			//IMPORTANT NOTE: When using comparable items, you must explicitly
			//convert the values
			String charConvert = a[i].toString();
			//Double y2=Integer.doubleValue(charConvert.charAt(0));
			//Double y2 = Double.parseDouble(((charConvert.charAt(0)).toString));
			int test =(int)(Math.abs(charConvert.charAt(0)-'<'));//< ASCII Int is 60
			String test1 =Integer.toString(test);
			Double y2 = Double.parseDouble(test1);
			//9/6/2017: StdOut.println("String Char = "+a[i]+" Alphabet = "+charConvert+" CharArray = "+y2+" Subtracted Elements = "+test);//']' equals 91
			
			StdDraw.line(x1, y1, x1, y2);
			x1+=2.0;
		}
		//Copy the offscreen canvas to the onscreen canvas
		 StdDraw.show();
		 
		 //Wait for a short period
       StdDraw.pause(500);
		
		//StdOut.println();
	}
	public static boolean isSorted(Comparable[] a)
	{ // Test whether the array entries are in order.
		for (int i = 1; i < a.length; i++)
		{
			if (less(a[i], a[i-1])) return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{ // Read strings from standard input, sort them, and print.
		String[] a = StdIn.readAllStrings();
		//Test1: selectionSort(a);
		//Test2: insertionSort(a);
		insertionSort(a);
		//Test3: shellSort(a);
		assert isSorted(a);
		//show(a);
	}
}//animated Sort class