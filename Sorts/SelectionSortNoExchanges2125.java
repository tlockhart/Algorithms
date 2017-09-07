//(2)insertionSort

//Executable Jar: jar cvfm SelectionSortNoExchanges2125.jar manifest.txt SelectionSortNoExchanges2125.class
//compile: javac -cp .;algs4_ts.jar SelectionSortNoExchanges2125.java
//run: java -cp .;algs4_ts.jar SelectionSortNoExchanges2125 <tiny.txt


/** This program modifies the traditional Insertion Sort algorithm, so the max 
 *	value is cached for every comparison in an array list.  When the inner loop 
 *	completes execution the max value is found, which can be swopped with the 
 *	element to its right.
 *
 * @author Tony Lockhart
 * @version 1.0
*/
public class SelectionSortNoExchanges2125
{
	public static Double yOffset = 370.0;//320
	public static final int squaredDimensionHigh = 400;//350
	public static final int halfSquaredDimensionHigh = 400/10;//350/10
	public static int iValue=0;
	public static int jValue=0;
	public static int minIndexValue = -1;
	public static int minIndex = 0;
	//public static boolean holdMinValue false;
	public static boolean minValueHeld = false;
	public static boolean secondGraphCalled = false;
	public static Comparable[] a;
	//public static boolean isEnd = false;
	
	public static void insertionSort(Comparable[] a)
	{
		SelectionSortNoExchanges2125.a = a;
		int N = SelectionSortNoExchanges2125.a.length;
		int min = 0;
		//int count = 0;
		boolean exchange = false;
		int index = N-1;
		
		//8/31/2017
		//2.1.24: Set the first positions to the smallest value in the list.
		//That way when these values are reached if the less method is called,
		//It will never return true when we compare j with j-1.  Since the values
		//in index will stop the less(a[j], a[j-1]) in the condition for going past
		//the 0 index, it is called a sentinel value.
		while((index>0))
		{
			if(less(SelectionSortNoExchanges2125.a[index], SelectionSortNoExchanges2125.a[index-1]))
			{
				exch2(SelectionSortNoExchanges2125.a, index, index-1);
			//9/01/2017: StdOut.println("Max Index value = "+a[index]+" Min Index Value = "+a[index-1]+" Min Index = "+(index-1));
			}
			index--;
		}
		/************************/
		for(int rtEndMinIndex = 2; rtEndMinIndex < N; rtEndMinIndex++)
		{
			//count=0;
			exchange = false;
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
			
			//j = i;
			Comparable temp = SelectionSortNoExchanges2125.a[rtEndMinIndex];
			int loopIndex = rtEndMinIndex;
			/************************/
			//9/01/2017: StdOut.println("************i = "+i);
			while(less(temp, SelectionSortNoExchanges2125.a[loopIndex-1]))
			{			
				//move largest value right
				a[loopIndex] = a[loopIndex-1];
				min = loopIndex;
				exchange = true;
				loopIndex--;				
			}//for
			//Assign min value to array maxIndex Position (loopIndex)
			/************************/
			a[loopIndex]=temp;
			/************************/
			//9/6/2017: Uncomment to Graph: insertionGraph(SelectionSortNoExchanges2125.a, rtEndMinIndex, min, exchange);
			yOffset-=35;
		}//for
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
	}

	private static void exch2(Comparable[] a, int i, int min)
	{ 
		Comparable temp = a[i]; 
		a[i] = a[min]; 
		a[min] = temp; 
	}
	private static void show(Comparable[] a)
	{ // Print the array, on a single line.
		for (int i = 0; i < a.length; i++)
		StdOut.println(a[i] + " ");
	}

	@SuppressWarnings("unchecked")
	private static void insertionGraph(Comparable[] a, int i, int min, boolean exchange)
	{
		char[] charArray;
		//Because comparable is a generic object that only has a 
		//compareTo method you must convert it to a String first.
		//charArray =	a.toString().toCharArray();
		
		charArray = a.toString().toCharArray();
		Double x1 = 0.0;
		Double y1 = 0.0;
		//iValue = is the position were the minimum value is moved to
		double iValue = Double.parseDouble(Integer.toString((int)(Math.abs(a[i].toString().charAt(0))-'<')));
		
		//minValue is the position the minValue was originall stored.
		double minValue = Double.parseDouble(Integer.toString((int)(Math.abs(a[min].toString().charAt(0))-'<')));
		
		int length = a.length;
		for (int k = 0; k< a.length; k++)
		{
			//IMPORTANT NOTE: When using comparable items, you must explicitly
			//convert the values
			String charConvert = a[k].toString();
			int test =(int)(Math.abs(charConvert.charAt(0)-'<'));//< ASCII Int is 60
			String test1 =Integer.toString(test);
			Double y2 = Double.parseDouble(test1);

			Double xCenter = (x1+x1)/2;
			Double yCenter = (y1+yOffset+y2+yOffset)/2;
			
			//9/01/2017: StdOut.println("KPOSITION = "+k+", Y2 = "+y2+", iPosition = "+i+", iValue = "+iValue+", A[i] = "+a[i]+", i+1 = "+(less(i,length)?(i+1):i)+", MinValue = "+minValue+", minPosition = "+min+", A[min] = "+a[min]+", EXCHANGE = "+exchange);
		
				int nextPosition =0;
			int position =0;
			if (less(i,length))
			{
				position =(i+1);
			}
			else
			{
				position =i;
			}
			//Make bar gray if the current posiition value (y2) is less than minValue
			//and greater than iValue, but not when the current position equals the
			//position after i (i+1)
			if(((y2<minValue)||(iValue<y2))&&(k!=position))
			{
				//8/9/17: Entries in Gray are in their final positions, because they have already been swopped by the exchange method
				drawGray(SelectionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			else if((y2 == iValue))
			{
				if(exchange)
				{
					drawBlack(SelectionSortNoExchanges2125.squaredDimensionHigh);
				}
				else if(!exchange)
				{
					drawRed(SelectionSortNoExchanges2125.squaredDimensionHigh);
				}
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			else if((y2 == minValue)&&(k==min)&&(exchange))
			{
				drawRed(SelectionSortNoExchanges2125.squaredDimensionHigh);//red is the value that has been moved.
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			else if(exchange&&(!((i-min)>1)&&(k==i+1)&&(less(a[i+1], a[i]))))
			{
				drawBlue(SelectionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			//if i Position minus min position is greater than 1
			//And Exchanges have been made and current index
			//position (k) is less than first char compare value 
			//(i)-- Make sure to highlight the characters between
			//i and min black
			else if(((i-min)>1)&&((exchange)&&(k<i)))
			{
				drawBlack(SelectionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			//8/11/2017: If the distance between i and min is greater than 1 (meaning exchanges have occured)
			//and the value at a[i+1] is less than a[i] (value to the right of i is less than value in i, and
			//the current index position i the position to the right of i (i+1) are equal.
			else if(((i-min)>1)&&((exchange)&&(less(a[i+1], a[i]))&&(k==position)))
			{
				drawBlue(SelectionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			//8/11/2017: If a[i+1] is less than a[i] (value to the right of i is less than value in i, and
			//the current index position k is equal to the position to the right of i (i+1), and no exchange.
			else if((!exchange)&&(less(a[i+1], a[i]))&&(k==position))
			{
				drawBlue(SelectionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
				
			}
			else
			{
				drawGray(SelectionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			x1+=3.0;//X offset
		}		 
		 //Wait for a short period
       StdDraw.pause(500);
	}
	
	public static void drawBlack(int squaredDimensionHigh)
	{
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.BLACK);
	}
	public static void drawWhite(int squaredDimensionHigh)
	{
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.WHITE);
	}
	public static void drawRed(int squaredDimensionHigh)
	{
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.RED);
	}
	public static void drawBlue(int squaredDimensionHigh)
	{
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.BLUE);
	}
	public static void drawGray(int squaredDimensionHigh)
	{
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.GRAY);
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
		selectionSort(a);
		//insertionSort(a);
		//shellSort(a);
		//Test3: shellSort(a);
		assert isSorted(a);
		//show(a);
	}
}//animated Sort class