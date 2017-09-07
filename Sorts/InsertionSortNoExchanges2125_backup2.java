//(2)insertionSort
//compile: javac -cp .;algs4_ts.jar InsertionSortNoExchanges2125.java
//run: java -cp .;algs4_ts.jar InsertionSortNoExchanges2125 <tiny.txt


/** This program modifies the traditional Insertion Sort algorithm, so the max 
 *	value is cached for every comparison in an array list.  When the inner loop 
 *	completes execution the max value is found, which can be swopped with the 
 *	element to its right.
 *
 * @author Tony Lockhart
 * @version 1.0
*/
public class InsertionSortNoExchanges2125
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
	//public static boolean isEnd = false;
	
	public static void insertionSort(Comparable[] a)
	{
		int N = a.length;
		int min = 0;
		//int count = 0;
		boolean exchange = false;
		//8/31/2017
		/************************/
		int index = N-1;
		
		//Set the first positions to the smallest value in the list.
		//That way when these values are reached if the less method is called,
		//It will never return true when we compare j with j-1.  Since the values
		//in index will stop the less(a[j], a[j-1]) in the condition for going past
		//the 0 index, it is called a sentinel value.
		while((index>0))
		{
			if(less(a[index], a[index-1]))
			{
			exch2(a, index, index-1);
			//9/01/2017: StdOut.println("Max Index value = "+a[index]+" Min Index Value = "+a[index-1]+" Min Index = "+(index-1));
			}
			index--;
		}
		/************************/
		for(int i = 1; i < N; i++)
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
			Comparable temp = a[i];
			/************************/
			//9/01/2017: StdOut.println("************i = "+i);
			for(int j=i; (/*j > 0 && */less(a[j],/*less(temp,*/ a[j-1]))/*||(j > 0 &&(equal(a[j], a[j-1])))*/; j--)
			{
				//Exchange the position of a[j] and a[j-1], since it is the new min value.
				exch2(a, j, j-1);
				
				//Sets Min, and move largest value right
				//8/31/2017: min = moveMaxValueRight(a, j);
				exchange = true;				
				//min = j-1;
				//9/01/2017: StdOut.println("************j = "+j);
			}//for
			//Print after the min value has been found
			//8/31/2017
			/************************/
			//a[j]=temp;
			/************************/
			insertionGraph(a, i, min, exchange);
			yOffset-=35;
		}//for
	}
	private static int moveMaxValueRight(Comparable[] a, int j)
	{
		/**********************************************************************/
		//8/30/2017: This gets the leftover values that are being held and 
		//sets the minIndex value, this makes certain there are no leftovers.
		/**********************************************************************/
		//8/30/2017: Closing the loop for when the a minValue is being held and 
		//needs  to be set, and when another value needs to be held right after.
		/**********************************************************************/
		if((alreadySwopped(a, j))&&(minValueHeld))
		{
			setMinIndex(a);
			moveRightAndHoldMin(a, j);
		}
		//8/30/2017: Closing the loop for when a min value is out of order and 
		//needs to be moved, and another min Value is set right after.
		else if(!minValueHeld)
		{			
			moveRightAndHoldMin(a, j);
			//8/30/2017: setMinIndex(a);
			StdOut.println("************MOVE TO RIGHT!");
		}
		//8/30/2017
		/**********/
		/*else if(minValueHeld)
		{
			setMinIndex(a);
			StdOut.println("************MIN VALUE HELD and Set");
			minValueHeld = false;
		}*/
		return minIndex;
	}
	private static void setMinIndex(Comparable[] a)
	{
		//Set array element to correct minIndex held value
			a[minIndex] = (Character.toString((char)(minIndexValue + 'A')));
			StdOut.println("minIndex = "+minIndex+" a[minIndex] = "+a[minIndex]);
			
			//set hold flag to false
			minValueHeld = false;
			//minIndex = j;	
	}
	private static void moveRightAndHoldMin(Comparable[] a, int j)
	{
		//hold the min value;
			char charValue = a[j].toString().charAt(0);//toCharArray();
			minIndexValue = (int)charValue-'A';
			StdOut.println("minIndex value = "+Character.toString((char)(minIndexValue+'A')));
			
			//move left value to right
			a[j]= a[j-1];

			//set minPosition
			minIndex = j-1;
			
			//set hold flag to true;
			minValueHeld = true;
	}
	private static boolean alreadySwopped(Comparable[] a, int j)
	{
		boolean value = false;
		if(/*(minValueHeld)&&*/((isCurrentComparisonEqual(j, a))||(isPreviousComparisonEqual(j, a))))
		{
			value = true;
		}
		return value;
	}
	
	private static boolean isCurrentComparisonEqual(int j, Comparable[] a)
	{
		boolean currentComparison = false;
		if(((j+1)<a.length)&&(j >= 0))
		{
			if(equal(a[j], a[j+1]))
			{
				currentComparison = true;
				//Set minIndex to minimum index position
				minIndex = j;
				StdOut.println("THE PREVIOUS Comparison is Equal: a[j] = "+a[j]+", a[j+1] = "+a[j+1]);
			}
		}
		return currentComparison;
	}
	
	private static boolean isPreviousComparisonEqual(int j, Comparable[] a)
	{
		boolean previousComparison = false;
		while(((j-2)>=0))
		{
			if(equal(a[j-1], a[j-2]))
			{
				previousComparison = true;
				//Set minIndex to minimum index position
				minIndex = j-2;
				StdOut.println("THE PREVIOUS Comparison is Equal: a[j-1] = "+a[j-1]+", a[j-2] = "+a[j-2]);
				StdOut.println("************minValueHeld= "+minValueHeld);
				break;
			}
			j--;
		}
		return previousComparison;
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
	private static boolean equal(Comparable v, Comparable w)
	{ 
		//P247
		//When v<w return -1
		//When v=w return 0
		//When v>w return +1
		
		//If the value before a[j] (a[j-1]) is  
		//greater than a[j] then call exchange.
		return v.compareTo(w) == 0; 
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
				//8/28/2017: StdOut.println("**i or i+1: 1 = "+(less(i,length)?(i+1):i));
				drawGray(InsertionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			else if((y2 == iValue))
			{
				if(exchange)
				{
					drawBlack(InsertionSortNoExchanges2125.squaredDimensionHigh);
				}
				else if(!exchange)
				{
					drawRed(InsertionSortNoExchanges2125.squaredDimensionHigh);
				}
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			else if((y2 == minValue)&&(k==min)&&(exchange))
			{
				drawRed(InsertionSortNoExchanges2125.squaredDimensionHigh);//red is the value that has been moved.
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			else if(exchange&&(!((i-min)>1)&&(k==i+1)&&(less(a[i+1], a[i]))))
			{
				drawBlue(InsertionSortNoExchanges2125.squaredDimensionHigh);
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
				drawBlack(InsertionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			//8/11/2017: If the distance between i and min is greater than 1 (meaning exchanges have occured)
			//and the value at a[i+1] is less than a[i] (value to the right of i is less than value in i, and
			//the current index position i the position to the right of i (i+1) are equal.
			else if(((i-min)>1)&&((exchange)&&(less(a[i+1], a[i]))&&(k==position)))
			{
				drawBlue(InsertionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			//8/11/2017: If a[i+1] is less than a[i] (value to the right of i is less than value in i, and
			//the current index position k is equal to the position to the right of i (i+1), and no exchange.
			else if((!exchange)&&(less(a[i+1], a[i]))&&(k==position))
			{
				drawBlue(InsertionSortNoExchanges2125.squaredDimensionHigh);
				//8/29/2017: StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				StdDraw.text(xCenter, yCenter, charConvert);
				
			}
			else
			{
				drawGray(InsertionSortNoExchanges2125.squaredDimensionHigh);
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
		//selectionSort(a);
		insertionSort(a);
		//shellSort(a);
		//Test3: shellSort(a);
		assert isSorted(a);
		//show(a);
	}
}//animated Sort class