//91)selectionSort
//compile: javac -cp .;algs4_ts.jar VisualElementarySorts2118.java
//run: java -cp .;algs4_ts.jar VisualElementarySorts2118 <tiny.txt

//(2)insertionSort
//compile: javac -cp .;algs4_ts.jar VisualElementarySorts2118.java
//run: java -cp .;algs4_ts.jar VisualElementarySorts2118 <tiny.txt

//(3)ShellSort//compile
//compile: javac -cp .;algs4_ts.jar VisualElementarySorts2118.java
//run: java -cp .;algs4_ts.jar VisualElementarySorts2118 <tinyShellST.txt

public class VisualElementarySorts2118
{
	public static Double yOffset = 370.0;//320
	public static final int squaredDimensionHigh = 400;//350
	public static final int halfSquaredDimensionHigh = 400/10;//350/10
	public static int iValue=0;
	public static int jValue=0;
	public static boolean secondGraphCalled = false;
	
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
				//If a[j]<a[min] then make min == j
				if(less(a[j], a[min])) min = j;
				//show(a);
				//graph(a, min);
			}
			//Exchange the position of a[i] and a[min], since it is the new min value.
				exch(a, i, min);
			//graph(a, min);
			yOffset-=35;
		}//for
	}//selectionSort
	public static void insertionSort(Comparable[] a)
	{
		int N = a.length;
		int min = 0;
		//int count = 0;
		boolean exchange = false;
		for(int i = 1; i< N; i++)
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
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
			{
				//Exchange the position of a[j] and a[j-1], since it is the new min value.
				exch2(a, j, j-1);
				exchange = true;
				//count++;
				//graph(a, j-1);
				
				min = j-1;
			}//for
			//Print after the min value has been found
			insertionGraph(a, i, min, /*count, */exchange);
			yOffset-=35;
			/****************/
			//8/15/2017
			/****************/
			//exchange = false;
		}//for
	}
	public static void setI(int i)
	{
		VisualElementarySorts.iValue = i;
	}
	public static int getI()
	{
		
		return VisualElementarySorts.iValue;
	}
	public static void setJ(int j)
	{
		VisualElementarySorts.jValue = j;
	}
	public static int getJ()
	{
		
		return VisualElementarySorts.jValue;
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
	private static void exch(Comparable[] a, int i, int min)
	{ 
		Comparable temp = a[i]; 
		a[i] = a[min]; 
		a[min] = temp; 
		//graph(a, min);
		//you want to send the i index as a[min] was switched with a[i]
		selectionGraph(a, i, min);
	}
	private static void exch2(Comparable[] a, int i, int min)
	{ 
		Comparable temp = a[i]; 
		a[i] = a[min]; 
		a[min] = temp; 
		//graph(a, min);
		//you want to send the i index as a[min] was switched with a[i]
		//insertionGraph(a, i, min);
	}
	private static void show(Comparable[] a)
	{ // Print the array, on a single line.
		for (int i = 0; i < a.length; i++)
		StdOut.println(a[i] + " ");
		//StdOut.println();
	}
	@SuppressWarnings("unchecked")
	private static void selectionGraph(Comparable[] a, int i, int min)
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
		//Clear the offscreen canvas
		//8/8/2017: StdDraw.clear();
		//9/1/2017: StdOut.println("A Length = "+a.length);
		
		for (int k = 0; k< a.length; k++)
		{
			//IMPORTANT NOTE: When using comparable items, you must explicitly
			//convert the values
			String charConvert = a[k].toString();
			//Double y2=Integer.doubleValue(charConvert.charAt(0));
			//Double y2 = Double.parseDouble(((charConvert.charAt(0)).toString));
			int test =(int)(Math.abs(charConvert.charAt(0)-'<'));//< ASCII Int is 60
			String test1 =Integer.toString(test);
			Double y2 = Double.parseDouble(test1);
			//StdOut.println("Y2 = "+y2);
			//8/8/2017StdOut.println("String Char = "+a[i]+" Alphabet = "+charConvert+" CharArray = "+y2+" Subtracted Elements = "+test);//']' equals 91
			
			//StdDraw.line(x1, y1+yOffset, x1, y2+yOffset);
			Double xCenter = (x1+x1)/2;
			Double yCenter = (y1+yOffset+y2+yOffset)/2;
			//StdDraw.filledRectangle(x1, y1+yOffset, x1, y2+yOffset);
			
			//8/15/2017: StdOut.println("Y2 = "+y2+" I = "+iValue+" A[i] = "+a[i]+" A[min] = "+a[min]);
			if((y2== iValue)&&(i == k))//we compare the position(y2, iValue) and the index value (i, k)to see if they are equal, then we highlight that
			{
				//8/9/17: Entries in red are the new positions of minimum value elements after swopped by the exchange method
				drawRed(VisualElementarySorts.squaredDimensionHigh);
			}
			else if(y2==minValue)
			{
				//8/9/17: Entries in Blue are the original positions of minimum value elements before swopped by the exchange method
				drawBlue(VisualElementarySorts.squaredDimensionHigh);
			}
			//8/9/17: k is the for loop examining each index value in a,  color the bar gray for any index value k, that is not greater than or equal to the minimum value index [i].
			else if((y2<=iValue)&&(!(k>=i)))
			{
				//8/9/17: Entries in Gray are in their final positions, because they have already been swopped by the exchange method
				drawGray(VisualElementarySorts.squaredDimensionHigh);
			}
			else
				drawBlack(VisualElementarySorts.squaredDimensionHigh);
			StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			x1+=3.0;//X offset
		}
		//Copy the offscreen canvas to the onscreen canvas
		 //8/8/2017: StdDraw.show();
		 
		 //Wait for a short period
       StdDraw.pause(500);
		
		//9/1/2017: StdOut.println();
	}
	@SuppressWarnings("unchecked")
	private static void insertionGraph(Comparable[] a, int i, int min, /*int count,*/ boolean exchange)
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
		
		//double minValueFromIValueMinusCount = Double.parseDouble(Integer.toString((int)(Math.abs(a[i-count].toString().charAt(0))-'<')));
		//Clear the offscreen canvas
		//8/8/2017: StdDraw.clear();
		//9/1/2017: StdOut.println("A Length = "+a.length);
		int length = a.length;
		for (int k = 0; k< a.length; k++)
		{
			//IMPORTANT NOTE: When using comparable items, you must explicitly
			//convert the values
			String charConvert = a[k].toString();
			int test =(int)(Math.abs(charConvert.charAt(0)-'<'));//< ASCII Int is 60
			String test1 =Integer.toString(test);
			Double y2 = Double.parseDouble(test1);
			//StdOut.println("Y2 = "+y2);
			//8/8/2017StdOut.println("String Char = "+a[i]+" Alphabet = "+charConvert+" CharArray = "+y2+" Subtracted Elements = "+test);//']' equals 91
			
			//StdDraw.line(x1, y1+yOffset, x1, y2+yOffset);
			Double xCenter = (x1+x1)/2;
			Double yCenter = (y1+yOffset+y2+yOffset)/2;
			
			//9/1/2017: StdOut.println("KPOSITION = "+k+", Y2 = "+y2+", iPosition = "+i+", iValue = "+iValue+", A[i] = "+a[i]+", i+1 = "+(less(i,length)?(i+1):i)+", MinValue = "+minValue+", minPosition = "+min+", A[min] = "+a[min]+", EXCHANGE = "+exchange);
			
			//8/9/17: k is the for loop examining each index value in a,  color the bar gray for any index value k, that is not greater than or equal to the minimum value index [i].
			//else if((y2<=iValue)&&(!(k>=i)))
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
				//9/1/2017: StdOut.println("**i or i+1: 1 = "+(less(i,length)?(i+1):i));
				drawGray(VisualElementarySorts.squaredDimensionHigh);
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			}
			else if((y2 == iValue))
			{
				if(exchange)
				{
					drawBlack(VisualElementarySorts.squaredDimensionHigh);
				}
				else if(!exchange)
				{
					drawRed(VisualElementarySorts.squaredDimensionHigh);
				}
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			}
			else if((y2 == minValue)/*&&(y2 != iValue)*/&&(k==min)&&(exchange)/*8/10/2017:do not select more than one position containing the same value*//*(y2 == minValue)&&*//*(iValue<minValue)(y2 == minValueFromIValueMinusCount)*//*y2==iValue*/)
			{
				//drawBlack();
				drawRed(VisualElementarySorts.squaredDimensionHigh);//red is the value that has been moved.
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				//drawGray();
				//StdOut.println("**Y2 = "+y2+", iValue = "+iValue+", A[i] = "+a[i]+", MinValue = "+minValue+", A[min] = "+a[min]);
			}
			//else if(exchange &&(y2 == minValue))
				else if(exchange&&(!((i-min)>1)&&(k==i+1)&&(less(a[i+1], a[i]))))
			{
				drawBlue(VisualElementarySorts.squaredDimensionHigh);
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			}
			//if i Position minus min position is greater than 1
			//And Exchanges have been made and current index
			//position (k) is less than first char compare value 
			//(i)-- Make sure to highlight the characters between
			//i and min black
			else if(((i-min)>1)&&((exchange)&&(k<i)))
			{
				//drawGray();
				drawBlack(VisualElementarySorts.squaredDimensionHigh);
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				//drawGray();
			}
			//8/11/2017: If the distance between i and min is greater than 1 (meaning exchanges have occured)
			//and the value at a[i+1] is less than a[i] (value to the right of i is less than value in i, and
			//the current index position i the position to the right of i (i+1) are equal.
			else if(((i-min)>1)&&((exchange)/*&&(k<i)*/&&(less(a[i+1], a[i]))&&(k==position/*(nextPosition=k-(less(i,length)?(i+1):i))==0)*/)))
			{
				//9/1/2017: StdOut.println("**i+1 or i: 2 = "+(less(i,length)?(i+1):i));
				drawBlue(VisualElementarySorts.squaredDimensionHigh);
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				//drawGray();
			}
			//8/11/2017: If a[i+1] is less than a[i] (value to the right of i is less than value in i, and
			//the current index position k is equal to the position to the right of i (i+1), and no exchange.
			else if((!exchange)&&(less(a[i+1], a[i]))&&(k==position))
			{
				drawBlue(VisualElementarySorts.squaredDimensionHigh);
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				
			}
			else
			{
				//9/1/2017: StdOut.println("**NextPostion3 = "+!((k-(less(i,length)?(i+1):i))==0));
				drawGray(VisualElementarySorts.squaredDimensionHigh);
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			}
			
			
			
			x1+=3.0;//X offset
		}
		//Copy the offscreen canvas to the onscreen canvas
		 //8/8/2017: StdDraw.show();
		 
		 //Wait for a short period
       StdDraw.pause(500);
		
		//9/1/2017: StdOut.println();
	}
	public static void shellSort(Comparable[] a)
	{
		Double yOffset = 1200.0;//320
		//Sort a[] into increasing order
		int N = a.length;
		int min = 0;
		//int count = 0;
		boolean exchange = false;
		boolean hValueSet = false;
		VisualElementarySorts.secondGraphCalled = false;
		int h = 1;
		int count = 0;
		//int i = 0;
		
		//h value is the number of inputs divided by 3(11/3=3, if h is an int)
		while (h < N/3) 
		{
			//h starts at 1 so h = (3*1)+1 = 4
			h = 3*h + 1; //1, 4, 13, 40
			hValueSet = true;
		}
		while (h >= 1)
		{// h-sort the array.
			//INSERTION LOOP
			for(int i = h; i < N; i++)
			{
				//exchange = false;
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
				
				//count=0;
				//H-LOOP
				for(int j = i; j >= h && less(a[j], a[j-h]); j-=h)
				{
					//VisualElementarySorts.secondGraphCalled = true;
					exch2(a, j, j-h);
					exchange = true;
					//graph(a, j-h);
					min = j-h;
					//count++;
					//setI(i);
					//setJ(j);
					/**************************************************************/
					//8/14/2017:shellGraph(a, j, min, exchange, hValueSet, h, yOffset);	
					/****************************************************************/
					//shellGraph(a, i, j, min, exchange, hValueSet, h, yOffset/*, count*/);	
					
					/*****************/
					//ShellLetterGraph
					/*****************/
					shellLetterGraph(a, i, j, min, exchange, hValueSet, h, yOffset);					
					yOffset-=35;
					exchange = false;
				}	
				//exchange = false;	
				//8/20/2017: Call graph 3 again to change check if the red bar should be black.
				/*Look Back Condition: Each time the i variable value is less than min variable
				  value the i value will continue to swop places with the min value, and a new
				  new value will be output in the command window and the min value will be 
				  drawn red each time.  So we must call the insertionGraph function again when (less(a[j],a[j-h]<0)
				  */
				  //VisualElementarySorts.secondGraphCalled = true;
				//shellGraph(a, getI(), getJ(), min, exchange, hValueSet, h, yOffset/*, count*/);	
				//yOffset+=35;
				//VisualElementarySorts.secondGraphCalled = false;				
			}//for	
			hValueSet = false;			
			//When decrement is called, the program has ended one h-sorted value
			h = h/3;
			hValueSet = true;
			/*********8/16/2017***************
			INSERT CALL GO GRAPH3 HERE
			shellGraph(a, setI(), setJ(), min, exchange, hValueSet, h, yOffset, count);
			**********************************/
			//shellGraph(a, getI(), getJ(), min, exchange, hValueSet, h, yOffset/*, count*/);					
					//yOffset-=35;//yOffset-=35;
					//exchange = false;
		}//while
	}//shellSort
	private static void shellGraph(Comparable[] a, int i, int j, int min, boolean exchange, boolean hValueSet, int hValue, double yOffset/*,int count*/)
	{
		char[] charArray;
		//Because comparable is a generic object that only has a 
		//compareTo method you must convert it to a String first.
		//charArray =	a.toString().toCharArray();
		
		charArray = a.toString().toCharArray();
		Double x1 = 0.0;
		Double y1 = 0.0;
		int squaredDimensionHigh = 1230;
		//Double yOffset = 1170.0;//320
		
		//iValue = is the position were the minimum value is moved to
		double iValue = Double.parseDouble(Integer.toString((int)(Math.abs(a[i].toString().charAt(0))-'<')));
		
		//minValue is the position the minValue was originall stored.
		double minValue = Double.parseDouble(Integer.toString((int)(Math.abs(a[min].toString().charAt(0))-'<')));
		
		//double minValueFromIValueMinusCount = Double.parseDouble(Integer.toString((int)(Math.abs(a[i-count].toString().charAt(0))-'<')));
		//Clear the offscreen canvas
		//8/8/2017: StdDraw.clear();
		StdOut.println("A Length = "+a.length);
		int length = a.length;
		for (int k = 0; k< a.length; k++)
		{
			//IMPORTANT NOTE: When using comparable items, you must explicitly
			//convert the values
			String charConvert = a[k].toString();
			int test =(int)(Math.abs(charConvert.charAt(0)-'<'));//< ASCII Int is 60
			String test1 =Integer.toString(test);
			Double y2 = Double.parseDouble(test1);
			//StdOut.println("Y2 = "+y2);
			//8/8/2017StdOut.println("String Char = "+a[i]+" Alphabet = "+charConvert+" CharArray = "+y2+" Subtracted Elements = "+test);//']' equals 91
			
			//StdDraw.line(x1, y1+yOffset, x1, y2+yOffset);
			Double xCenter = (x1+x1)/2;
			Double yCenter = (y1+yOffset+y2+yOffset)/2;
			
			//9/1/2017: StdOut.println("SecondGraphCalled = "+VisualElementarySorts.secondGraphCalled+", KPOSITION = "+k+", J = "+j+", Y2 = "+y2+", iPosition = "+i+", iValue = "+iValue+", H-Value = "+hValue+", A[i] = "+a[i]+", i+1 = "+(less(i,length)?(i+1):i)+", MinValue = "+minValue+", minPosition = "+min+", A[min] = "+a[min]+", A[j] = "+a[j]/*+" Count = "+count*/+", EXCHANGE = "+exchange);
			
			//8/9/17: k is the for loop examining each index value in a,  color the bar gray for any index value k, that is not greater than or equal to the minimum value index [i].
			//else if((y2<=iValue)&&(!(k>=i)))
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
			if(VisualElementarySorts.isOutputBlue(k, i, j, min, hValue, a, exchange))
			{
				drawBlue2(squaredDimensionHigh);
				
				//8/25/2016: Print Letter
				//StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			}
			else if(VisualElementarySorts.isOutputBlack(k, j, min, hValue, a, exchange))
			{
				//8/21/2017:yOffset+=35;//must go back up the y Axis to redraw the bar as red instead of black, to symbolize the end.
				drawWhite2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				//StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				
				drawBlack2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				//StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				//StdOut.println("*****BLACK Draw Enabled, "+ "HVALUE = "+hValue);
			}
			else if(VisualElementarySorts.isOutputRed(k, j, min, hValue, a, exchange))
			{
				//8/22/2017: 
				//xCenter = (x1+x1)/2;
				//yOffset+=35;//must go back up the y Axis to redraw the bar as red instead of black, to symbolize the end.
				//yCenter = (y1+yOffset+y2+yOffset)/2;
				
				/************************************************
				1)minValue so this should draw the middle red bar
				*************************************************/
				drawWhite2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				//StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				
				drawRed2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				//StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				//StdOut.println("*****HVALUE < 1, RED Draw Enabled, "+ "HVALUE = "+hValue);
			}
			else if(isOutputGray(k, j, min, hValue, a, exchange))
			{
				drawWhite2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				//StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				
				drawGray2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				//StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			}
			/****************************************************************/
			//MIN VALUE PLACEMENT:  Draws the Diagonal consecutive min values as black, Note the last one needs to be red
			/****************************************************************/				
			/*8/23/2017
			else if((k != min)&&(k!=i))
			{
				drawGreen2(squaredDimensionHigh);
				StdDraw.text(xCenter, yCenter, charConvert);
				StdOut.println("******************IN GREEN PAINT***********************");
			}*/						
			x1+=3.0;//X offset
		}
		//Copy the offscreen canvas to the onscreen canvas
		 //8/8/2017: StdDraw.show();
		 
		 //Wait for a short period
       StdDraw.pause(500);
		
		StdOut.println();
	}//shellGraph
	
	private static void shellLetterGraph(Comparable[] a, int i, int j, int min, boolean exchange, boolean hValueSet, int hValue, double yOffset/*,int count*/)
	{
		char[] charArray;
		//Because comparable is a generic object that only has a 
		//compareTo method you must convert it to a String first.
		//charArray =	a.toString().toCharArray();
		
		charArray = a.toString().toCharArray();
		Double x1 = 0.0;
		Double y1 = 0.0;
		int squaredDimensionHigh = 1230;
		//Double yOffset = 1170.0;//320
		
		//iValue = is the position were the minimum value is moved to
		double iValue = Double.parseDouble(Integer.toString((int)(Math.abs(a[i].toString().charAt(0))-'<')));
		
		//minValue is the position the minValue was originall stored.
		double minValue = Double.parseDouble(Integer.toString((int)(Math.abs(a[min].toString().charAt(0))-'<')));
		
		//double minValueFromIValueMinusCount = Double.parseDouble(Integer.toString((int)(Math.abs(a[i-count].toString().charAt(0))-'<')));
		//Clear the offscreen canvas
		//8/8/2017: StdDraw.clear();
		//9/1/2017: StdOut.println("A Length = "+a.length);
		int length = a.length;
		for (int k = 0; k< a.length; k++)
		{
			//IMPORTANT NOTE: When using comparable items, you must explicitly
			//convert the values
			String charConvert = a[k].toString();
			int test =(int)(Math.abs(charConvert.charAt(0)-'<'));//< ASCII Int is 60
			String test1 =Integer.toString(test);
			Double y2 = Double.parseDouble(test1);
			//StdOut.println("Y2 = "+y2);
			//8/8/2017StdOut.println("String Char = "+a[i]+" Alphabet = "+charConvert+" CharArray = "+y2+" Subtracted Elements = "+test);//']' equals 91
			
			//StdDraw.line(x1, y1+yOffset, x1, y2+yOffset);
			Double xCenter = (x1+x1)/2;
			Double yCenter = (y1+yOffset+y2+yOffset)/2;
			
			//9/1/2017: StdOut.println("SecondGraphCalled = "+VisualElementarySorts.secondGraphCalled+", KPOSITION = "+k+", J = "+j+", Y2 = "+y2+", iPosition = "+i+", iValue = "+iValue+", H-Value = "+hValue+", A[i] = "+a[i]+", i+1 = "+(less(i,length)?(i+1):i)+", MinValue = "+minValue+", minPosition = "+min+", A[min] = "+a[min]+", A[j] = "+a[j]/*+" Count = "+count*/+", EXCHANGE = "+exchange);
			
			//8/9/17: k is the for loop examining each index value in a,  color the bar gray for any index value k, that is not greater than or equal to the minimum value index [i].
			//else if((y2<=iValue)&&(!(k>=i)))
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
			if(VisualElementarySorts.isOutputBlue(k, i, j, min, hValue, a, exchange))
			{
				drawBlue2(squaredDimensionHigh);
				
				//8/25/2016: Print Letter
				StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				//StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			}
			else if(VisualElementarySorts.isOutputBlack(k, j, min, hValue, a, exchange))
			{
				//8/21/2017:yOffset+=35;//must go back up the y Axis to redraw the bar as red instead of black, to symbolize the end.
				drawWhite2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				//StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				
				drawBlack2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				//StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				//StdOut.println("*****BLACK Draw Enabled, "+ "HVALUE = "+hValue);
			}
			else if(VisualElementarySorts.isOutputRed(k, j, min, hValue, a, exchange))
			{
				//8/22/2017: 
				//xCenter = (x1+x1)/2;
				//yOffset+=35;//must go back up the y Axis to redraw the bar as red instead of black, to symbolize the end.
				//yCenter = (y1+yOffset+y2+yOffset)/2;
				
				/************************************************
				1)minValue so this should draw the middle red bar
				*************************************************/
				drawWhite2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				//StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				
				drawRed2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				//StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				//StdOut.println("*****HVALUE < 1, RED Draw Enabled, "+ "HVALUE = "+hValue);
			}
			else if(isOutputGray(k, j, min, hValue, a, exchange))
			{
				drawWhite2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				//StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
				
				drawGray2(squaredDimensionHigh);
				//8/25/2016: Print Letter
				StdDraw.text(xCenter, yCenter, charConvert);
				
				//8/25/2016: Bar Graphs
				//StdDraw.filledRectangle(xCenter, yCenter, 1, (Math.abs(y2-y1))/2);
			}
			/****************************************************************/
			//MIN VALUE PLACEMENT:  Draws the Diagonal consecutive min values as black, Note the last one needs to be red
			/****************************************************************/				
			/*8/23/2017
			else if((k != min)&&(k!=i))
			{
				drawGreen2(squaredDimensionHigh);
				StdDraw.text(xCenter, yCenter, charConvert);
				StdOut.println("******************IN GREEN PAINT***********************");
			}*/						
			x1+=3.0;//X offset
		}
		//Copy the offscreen canvas to the onscreen canvas
		 //8/8/2017: StdDraw.show();
		 
		 //Wait for a short period
       StdDraw.pause(500);
		
		StdOut.println();
	}//shellLetterGraph
	
	public static boolean isIndexEQMin(int k, int min)
	{
		boolean value = false;
		if(k == min)
			value = true;
		return value;
	}
	public static boolean isIndexEQJ(int k, int j)
	{
		boolean value = false;
		if(k == j)
			value = true;
		return value;
	}
	public static boolean isHValGTEQ1(int hValue)
	{
		boolean value = false;
		if(hValue >= 1)
			value = true;
		return value;
	}
	public static boolean isJMinExchanged(int j, int min, Comparable[] a)
	{
		boolean value = false;
		/*if(min<0)
		{
			value = false;
		}
		else
		{*/
			if(less(a[j],a[min]))
				value = true;
		//}
		return value;
	}
	/*public static boolean isIndex!EQMin(int j, int min)
	{
		boolean value = false;
		if(j != min)
			value = true;
		return value;
	}*/
	public static boolean isKBetweenMinAndJ(int k, int j, int min)
	{
		boolean value = false;
		if((k>min)&&(k<j))
			value = true;
		return value;
	}
	public static boolean isLastMinExchangedOrMultSwops(int j, int min, int hValue, Comparable[] a)
	{
		boolean value = false;
		if(((min-hValue) < 0)||(!(isJMinExchanged(min, min-hValue, a))))
		{
			value = true;
		}
		return value;
	}
	public static boolean isLastMinExchangedOrSingleSwop(int j, int min, int hValue, Comparable[] a)
	{
		boolean value = false;
		//if(((min-hValue) < 0)||(isJMinExchanged(min, min-hValue, a)))
			if(((min-hValue) < 0)||(isMinLTNextMin(min, hValue, a))||(isJMinExchanged(j, min, a)))
		{
			value = true;
		}
		return value;
	}
	public static boolean isMinLTNextMin(int min, int hValue, Comparable[] a)
	{
		boolean value = false;
		int nextMin = min-hValue;
		if ((nextMin)>=0)
		{
			if(!(isJMinExchanged(min, nextMin, a)))
			{
				value = true;
			}
		}
		return value;
	}
	//Prints when a new hValue is set, not when it is used to decrease the min value.
	public static boolean isANewCompareStarted(int i, int j, int k, int min, int hValue, Comparable[] a)
	{
		boolean value = false;
		//int nextMin = min-hValue;
		//int nextCompareValue = j+
		//if ((nextMin)>=0)
		//{
			if((i==j)&&(VisualElementarySorts.isIndexEQJ(k, j))/*&&((VisualElementarySorts.isLastMinExchangedOrSingleSwop(j, min, hValue, a)))*/)
				value = true;
		return value;
	}
	public static boolean isOutputBlue(int k, int i, int j, int min, int hValue, Comparable[] a, boolean exchange)
	{
		boolean value = false;
			if(VisualElementarySorts.isANewCompareStarted(i, j, k, min, hValue, a))
				value = true;
		return value;
	}
	public static boolean isOutputBlack(int k, int j, int min, int hValue, Comparable[] a, boolean exchange)
	{
		boolean value = false;
			if(((VisualElementarySorts.isIndexEQJ(k, j))&&(exchange)&&(VisualElementarySorts.isLastMinExchangedOrSingleSwop(j, min, hValue, a)))||((VisualElementarySorts.isHValGTEQ1(hValue))&&(VisualElementarySorts.isIndexEQJ(k, j))&&(exchange)&&(!(VisualElementarySorts.isLastMinExchangedOrMultSwops(j, min, hValue, a))))||((VisualElementarySorts.isIndexEQMin(k, min))&&(exchange)&&(!(VisualElementarySorts.isLastMinExchangedOrMultSwops(j, min, hValue, a)))))
			value = true;
		return value;
	}
	public static boolean isOutputGray(int k, int j, int min, int hValue, Comparable[] a, boolean exchange)
	{
		boolean value = false;
		
			if(((VisualElementarySorts.isHValGTEQ1(hValue))&&VisualElementarySorts.isKBetweenMinAndJ(k, j, min))||((!(VisualElementarySorts.isIndexEQJ(k, j)))&&(!(VisualElementarySorts.isIndexEQMin(k, min)))))
			value = true;
		return value;
	}
	public static boolean isOutputRed(int k, int j, int min, int hValue, Comparable[] a, boolean exchange)
	{
		boolean value = false;
		//if(/*(!(VisualElementarySorts.isJMinExchanged(j, min, a)))*/!exchange||(exchange/*(VisualElementarySorts.isJMinExchanged(j, min, a)*/)&&(VisualElementarySorts.isLastMinExchanged(j, min, hValue)))
		if(((!exchange)&&(VisualElementarySorts.isIndexEQJ(k, j)))||(exchange&&(VisualElementarySorts.isLastMinExchangedOrMultSwops(j, min, hValue, a))&&(VisualElementarySorts.isIndexEQMin(k, min))))
			value = true;
		return value;
	}
	public static void drawBlack(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.BLACK);
		//StdDraw.setPenRadius(.05);
	}
	/*public static void drawBlue(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.BLUE);
		//StdDraw.setPenRadius(.05);
	}*/
	public static void drawWhite(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.WHITE);
		//StdDraw.setPenRadius(.05);
	}
	public static void drawRed(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.RED);
		//StdDraw.setPenRadius(.05);
	}
	public static void drawBlue(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.BLUE);
		//StdDraw.setPenRadius(.05);
	}
	public static void drawGray(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+5);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+5);
		StdDraw.setPenColor(StdDraw.GRAY);
		//StdDraw.setPenRadius(.05);
	}
	/**********************************************/
	public static void drawBlack2(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+10);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+10);
		StdDraw.setPenColor(StdDraw.BLACK);
		//StdDraw.setPenRadius(.05);
	}
	public static void drawWhite2(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+10);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+10);
		StdDraw.setPenColor(StdDraw.WHITE);
		//StdDraw.setPenRadius(.05);
	}
	public static void drawRed2(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+10);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+10);
		StdDraw.setPenColor(StdDraw.RED);
		//StdDraw.setPenRadius(.05);
	}
	public static void drawBlue2(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+10);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+10);
		StdDraw.setPenColor(StdDraw.BLUE);
		//StdDraw.setPenRadius(.05);
	}
	public static void drawGray2(int squaredDimensionHigh)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(-5, halfSquaredDimensionHigh+10);
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(-5, squaredDimensionHigh+10);
		StdDraw.setPenColor(StdDraw.GREEN);
		//StdDraw.setPenRadius(.05);
	}
	/*************************************************************/
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