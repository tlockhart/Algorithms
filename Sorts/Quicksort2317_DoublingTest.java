package sorts;

//No API's can be imported accept java.lang and the algs APIs.
import java.lang.*;
//Import classes from algs4
import exercises.*;

/** This program uses quicksort to sort an arraylist.
 *	
 *
 * @author Tony Lockhart
 * @version 1.0
*/
 /**
 *  Written:       9/30/2017
 *  Last updated:  9/30/2017
 *
 *  <p><b>Requirement:</b> Class must be in <b>sorts</b> package and <b>exercises.*</b> must be imported.<b>
 *        The class must be executed from the DoublingTest.java class to print the time of execution.  Note: <b>
 *		  PrintArray for cmd line output and Graph for java window has been disabled for testing:</p> 
 
   *Executable Jar: jar cvfm QuickSort2318_DoublingTest.jar manifest.txt QuickSort2318_DoublingTest.class
 *  <p><b>Javadoc:</b> javadoc -author -version -private -classpath .;algs4_sts.jar; -d .\javadoc Quicksort2317_DoublingTest.java</p>
 *  <p><b>Compilation:</b>   javac -cp .;algs4_sts.jar; Quicksort2317_DoublingTest.java</p>
 *  <p><b>Execution 1:</b>     java -cp ../;.;algs4_sts.jar; sorts.Quicksort2317_DoublingTest &#60;quicksort.txt</p>
 *  <p><b>Execution 2:</b>     java -cp ../;.;algs4_sts.jar; sorts.Quicksort2317_DoublingTest &#60;qsShuffled.txt</p>
 *  <p><b>Execution 3:</b>     java -cp ../;.;algs4_sts.jar; sorts.Quicksort2317_DoublingTest &#60;distinctKeys.txt</p>
 *
 *  <p><b>Summary:</b> QuickSort uses the divide and conquer method to sort a list of values.  However, 
 *	instead of dividing the array in half, it identifies a pivotValue, which is used to find a partition, 
 *	and enforce the rules below.  The partition is used to sort the left and right subarrays:
 *	<ol><li>The entry a[rtScanIndex] is in its final place in the array</li>
 *	<li>No entry on the leftside of the array (a[lo]-a[rtScanIndex-1]) is greater than a[rtScanIndex].</li>
 *	<li>No entry on the right side of the array (a[rtScanIndex+1] through a[hi]) is less than a[rtScanIndex].</li></ol></p>
 *
 *	<p>Initialize the leftScanIndex with the lo value, and initialize the rightScanIndex with the hi+1 value.   
 *	The pivotValue is initially set to the array's first index value (a[lo]), however, this is not the 
 *	partition value.  The pivotValue is the sentinel value that is exchanged anytime a value less than it 
 *	is found during the left or right scan.  The left scan starts from 0 and is preincremented.  The right 
 *	scan starts at hi+1 and is predecremented. The increment and decrement occur while the leftIndexScan 
 *	value is less than the pivotValue, and the pivotValue is less than the rightIndexScan value.  If the 
 *	leftIndexScan reaches the end of the array, or the rightIndexScan reaches the beginning of the array, 
 *	break out of the inner while loops.  However, if the rightIndexScan value is less than the pivotValue 
 *	or the leftIndexScan value is greater than the pivotValue, we know that the array elements are out of 
 *	order, and they should be exchanged, so the inner loop condition fails, and the program execution is 
 *	returned to the first condition after the inner while loops.  Condition: If (leftIndex crossed/equals 
 *	rightIndex) leftScanIndex >= rightScanIndex, then break out of the outer loop.  If the condition is 
 *	false, then only the value of a[leftScanIndex] is exchanged with a[rtScanIndex].  This is the
 *	isRTIndexExchanged and LTIndexExchanged conditions, which is highlighted in red and blue, respectively.  
 *	However, if the condition is true, the outer while loop is exited, and the pivotValue in a[lo] must be 
 *	swopped with the value in a[rightIndexScan], because all the values have been compared.  Thus, the lowest 
 *	value is pushed from the right side, and is currently in the rtScanIndex element and the highest value has 
 *	been pushed from the left side, and is currently in the ltScanIndex element.  This is the 
 *	hasRTCrossedLTIndex and hasLTCrossedRTIndex conditions, which are highlighted in magenta and cyan, 	
 *	respectively.  So, a[rtIndexScan] becomes the (new pivotValue) partitionValue. This is the 
 *	isPartitionFound condition, which is highlighted in orange (Note: the partitionValue will show as 
 *	orange/cyan because the two conditions overlap). Then the partition method returns the partitionValue to 
 *	the main program, which can then be used to recursively sort the left and right half of the array, and 
 *	since the rules above hold true, once both halves are sorted, the whole array is sorted.</p>
 *
 *  <p><b>Methods:</b>
 *  
 *  <ol><li>partion() method finds the partition for the array to be divided on and formats the array, so it complies
 *	with the 3 rules for quicksort.</li>
 *
 *  <li>sort() method sorts the subarray. </li> 
 *	<li>isRTIndexExchanged() and isLTIndexExchanged() - If the leftScanIndex is less than the rightScanIndex, a[lo] is 
 *	exchanged with a[rightScanIndex-1].  The output characters are hightlighted in the red and blue colors, 
 *	respectively.</li>
 *
 *	<li>hasRTCrossedLTIndex() and hasLTCrossedLTIndex() - If the leftScanIndex is greater than or equal to rightScanIndex, 
 *	a[leftScanIndex] is exchanged with a[rightScanIndex].  The output characters are hightlighted in magenta and cyan, 
 *	respectively.</li>
 *	<li>isPartitionFound() - Highlights the first partition found in orange, as it is the partition for the complete array.</li>
 *  </ol></p>
 *
 *  <p><b>Files:</b> Contains a 15+ char array to be sorted.</p>
 *
 *  @version 1.7
 *  @author Tony Lockhart
 *  
 *  
 */
public class Quicksort2317_DoublingTest
{
	
	public static final int tableHeight = 1300;//400
	public static double yOffset = Double.parseDouble(Integer.toString(tableHeight))-30.0;//470.0;//320
	public static final int tableWidth = 500/10;//400/10
	public static boolean exchange = false;
	public static boolean exchange2 = false;
	//public static boolean arrayLength;
	public static Comparable[] a;
	public static int leftScanIndex;
	public static int rightScanIndex;
	public static int leftIncrementCtr =0;
	public static int rightDecrementCtr = 0;
	public static int partitionCtr =0;
	public static int rtComparisonCtr =0;
	public static int ltComparisonCtr =0;
	
	public static void sort(Comparable[] a)
	{
		StdRandom.shuffle(a);
		/*****************************************/
		//Uncomment the values in the partition method if removing Sentinel Values:
		//Add sentinel value at the end of the array above
		for (int i=0; i<a.length-1; i++)
		{
			if(less(a[i+1], a[i]))
			{
				exch(a, i, (i+1));
			}
		}
		/*****************************************/
		//dualPrint(a, 0, 0, 0, exchange, exchange2, false, false, 0 , 0, 0);
		/*****************************************/
		sort(a, 0, a.length-1);
	}
	public static void sort(Comparable[] a, int lo, int hi)
	{
		if(hi <= lo) return;
		int j = partition(a, lo, hi);// sort lo, hi and partition
		/*StdOut.println("*****J = "+j+" a Value = "+a[j]);
		printArray2(a);*/
		Quicksort2317_DoublingTest.sort(a, lo, j-1);//sort left side
		Quicksort2317_DoublingTest.sort(a, j+1, hi);//sort right side
	}
	private static int partition(Comparable[] a, int lo, int hi)
	{
		boolean leftScanIncremented = false;
		boolean rightScanDecremented = false;
		partitionCtr++;
		leftScanIndex = lo;
		rightScanIndex=hi+1;
		Comparable pivot = a[lo];
		while(true)
		{
			exchange = false;
			leftIncrementCtr =leftScanIndex;
			rightDecrementCtr = rightScanIndex;
			while(less(a[++leftScanIndex], pivot)) 
			{
				leftScanIncremented = true;
				//******SENTINEL VALUE: if(leftScanIndex==hi) break;/*right scan, removing condition causes quadratic time, sentinel value is hi and sufficient*/
					//ltComparisonCtr++;
			}
			leftIncrementCtr = Math.abs(leftScanIndex-leftIncrementCtr);
			//StdOut.println("leftScanIndex = "+leftScanIndex+" LeftIncrementCtr = "+leftIncrementCtr+" Exchange = "+exchange+" leftScanIncremented = "+leftScanIncremented);
					/*****************************************/
					if(leftScanIncremented)
					{
						//dualPrint(a, lo, leftScanIndex, rightScanIndex, exchange, exchange2, leftScanIncremented, rightScanDecremented, leftIncrementCtr, rightDecrementCtr, partitionCtr);
					}
					/*****************************************/
					leftScanIncremented = false;
					//reset Counters
					rightDecrementCtr=0;
					leftIncrementCtr =0;
					/*****************************************/
			while(less(pivot, a[--rightScanIndex])) 
			{
				rightScanDecremented = true;
				//******SENTINEL VALUE: if(rightScanIndex == lo) break;/*left scan, removing condition causes quadratic time, sentinel value is lo and sufficient*/
					//rtComparisonCtr++;
			}
			rightDecrementCtr = Math.abs(rightDecrementCtr -rightScanIndex);
					/*****************************************/
					if(rightScanDecremented)
					{
						//dualPrint(a, lo, leftScanIndex, rightScanIndex, exchange, exchange2, leftScanIncremented, rightScanDecremented, leftIncrementCtr, rightDecrementCtr, partitionCtr);
					}
					rightScanDecremented=false;
					//reset Counters;
					rightDecrementCtr=0;
					leftIncrementCtr =0;
					/*****************************************/
			if (leftScanIndex >= rightScanIndex)break;/*lt/rt cros*/
			rightScanDecremented = false;
			leftScanIncremented = false;
			/*make sure no entries to left of the leftScanIndex are greater than pivot, 
			and no entries to the right of the rightScanIndex are less than the pivot.*/
			exch(a, leftScanIndex, rightScanIndex);	
			exchange = true;
			/*****************************************/
			if(exchange)
			{
				//dualPrint(a, lo, leftScanIndex, rightScanIndex, exchange, exchange2, leftScanIncremented, rightScanDecremented, leftIncrementCtr, rightDecrementCtr, partitionCtr);
			}
			exchange = false;
			/*****************************************/
					
		//graph							
		}//while
		exch(a, lo, rightScanIndex);/*exchange the partitioning item a[lo] with the rightmost entry a[j]*/
		/****9/27/2017:When and exchange occurs the rows prints the remaining elements in the list after the exchange*****/
		exchange2 = true;
		/*****************************************/
		if(exchange2)
		{
			//dualPrint(a, lo, leftScanIndex, rightScanIndex, exchange, exchange2, leftScanIncremented, rightScanDecremented, leftIncrementCtr, rightDecrementCtr, partitionCtr);
		}
		/*****************************************/
		exchange2 = false;
		int partitionValue = rightScanIndex;

		return partitionValue;
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

	private static void exch(Comparable[] a, int leftIndex, int rightIndex)
	{ 
		Comparable valueHolder = a[leftIndex]; 
		a[leftIndex] = a[rightIndex]; 
		a[rightIndex] = valueHolder; 
	}
	private static void show(Comparable[] a)
	{ // Print the array, on a single line.
		for (int i = 0; i < a.length; i++)
		{
		//StdOut.println(a[i] + " ");
		}
	}
	@SuppressWarnings("unchecked")
	private static void dualPrint(Comparable[] a, int lo, int ltToRtIndexPtr, int rtToLtIndexPtr, boolean exchange, boolean exchange2, boolean leftScanIncremented, boolean rightScanDecremented, int leftIncrementCtr,  int rightDecrementCtr, int partitionCtr)
	{
		/*****************************************/
		Quicksort2317_DoublingTest.printArray(a);
		Quicksort2317_DoublingTest.graph(a, lo, ltToRtIndexPtr, rtToLtIndexPtr, exchange, exchange2, leftScanIncremented, rightScanDecremented, leftIncrementCtr, rightDecrementCtr, partitionCtr);
		/*****************************************/
	}
	@SuppressWarnings("unchecked")
	private static void graph(Comparable[] a, int lo, int ltToRtIndexPtr, int rtToLtIndexPtr, boolean exchange, boolean exchange2, boolean leftScanIncremented, boolean rightScanDecremented, int leftIncrementCtr, int rightDecrementCtr, int partitionCtr)
	{
		boolean isInitialArraySet = false;
		boolean isMultLTIndexFound = false;
		boolean isMultRTIndexFound = false;
		boolean isRTIndexExchanged = false;
		boolean isLTIndexExchanged = false;
		boolean hasRTCrossedLTIndex = false;
		boolean hasLTCrossedRTIndex = false;
		boolean isPartitionFound = false;
		boolean isNoIndexFound  = false;
		boolean isRTIndexEqual2Zero = false;
		char[] charArray;
		//Because comparable is a generic object that only has a 
		//compareTo method you must convert it to a String first.
		//charArray =	a.toString().toCharArray();
		
		charArray = a.toString().toCharArray();
		double x1 = 0.0;
		double y1 = 0.0;
		//ltToRtIndexPtrValue = is the position were the rtToLtIndexPtrimum value is moved to
		if(ltToRtIndexPtr<0)
		{
			ltToRtIndexPtr=0;
		}
		double ltToRtIndexPtrValue= Double.parseDouble(Integer.toString((int)(Math.abs(a[ltToRtIndexPtr].toString().charAt(0))-'<')));
		
		//rtToLtIndexPtr is the position the rtToLtIndexPtrValue was originally stored.
		if(rtToLtIndexPtr>=a.length)
		{
			rtToLtIndexPtr=a.length-1;
		}
		double rtToLtIndexPtrValue = Double.parseDouble(Integer.toString((int)(Math.abs(a[rtToLtIndexPtr].toString().charAt(0))-'<')));
		double loIndexPtrValue = Double.parseDouble(Integer.toString((int)(Math.abs(a[lo].toString().charAt(0))-'<')));
		
		int length = a.length;
		for (int k = 0; k< a.length; k++)
		{
			//IMPORTANT NOTE: When using comparable items, you must explicitly
			//convert the values
			String charConvert = a[k].toString();
			int rowCtr = 0;
			int currCharAsInt =(int)(Math.abs(charConvert.charAt(0)-'<'));//< ASCII Int is 60
			String currCharAsString =Integer.toString(currCharAsInt);
			double currCharAsDouble = Double.parseDouble(currCharAsString);

			double xCenter = (x1+x1)/2;
			double yCenter = (y1+yOffset+currCharAsDouble+yOffset)/2;
			
			/*************************************************/
			/****9/28/2017: Conditional Variables*************/
			isInitialArraySet = isInitialArraySet(ltToRtIndexPtr, rtToLtIndexPtr, exchange, leftScanIncremented, rightScanDecremented);
			isMultLTIndexFound = isMultLTIndexFound(charConvert,k, leftIncrementCtr, ltToRtIndexPtr, exchange, leftScanIncremented);
			isMultRTIndexFound = isMultRTIndexFound(charConvert, k, rightDecrementCtr, rtToLtIndexPtr, exchange, rightScanDecremented);
			isRTIndexExchanged = isRTIndexExchanged(k, rtToLtIndexPtr, ltToRtIndexPtr, exchange, rightScanDecremented, rtToLtIndexPtrValue, ltToRtIndexPtrValue);
			isLTIndexExchanged = isLTIndexExchanged(k, rtToLtIndexPtr, ltToRtIndexPtr, exchange, leftScanIncremented, rtToLtIndexPtrValue, ltToRtIndexPtrValue);
			hasRTCrossedLTIndex = hasRtCrossedLtIndex(lo, rtToLtIndexPtr, k, ltToRtIndexPtr, isLTIndexExchanged, isRTIndexExchanged, rtToLtIndexPtrValue, loIndexPtrValue, exchange, exchange2);
			hasLTCrossedRTIndex = hasLtCrossedRtIndex(lo, loIndexPtrValue, rtToLtIndexPtrValue, rtToLtIndexPtr, k, ltToRtIndexPtr, isLTIndexExchanged, isRTIndexExchanged, exchange, exchange2);
			isPartitionFound = isPartitionFound(lo, loIndexPtrValue, rtToLtIndexPtrValue, rtToLtIndexPtr, k, ltToRtIndexPtr, isNoIndexFound, isMultLTIndexFound, isMultRTIndexFound, isRTIndexExchanged, partitionCtr);
			isNoIndexFound = isNoIndexFound(isInitialArraySet, isMultLTIndexFound, isMultRTIndexFound, isRTIndexExchanged, isLTIndexExchanged, hasRTCrossedLTIndex, hasLTCrossedRTIndex);
			isRTIndexEqual2Zero = isRTIndexEqual2Zero(k, rtToLtIndexPtr, exchange, exchange2, isInitialArraySet);
			/*************************************************/
		
			if(isInitialArraySet)
			{
				drawGreen(Quicksort2317_DoublingTest.tableHeight);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			if(isMultLTIndexFound)
			{
				for(int i = 0; i< leftIncrementCtr; i++)
				{
					if (i==0)
						k=k;
					else if (i>0)
						k=k+1;
					charConvert = a[k].toString();
					drawBlack(Quicksort2317_DoublingTest.tableHeight);
					StdDraw.text(xCenter, yCenter, charConvert);
					//StdOut.println("Left xCenter = "+xCenter+" Character = "+charConvert+" K = "+k);
					if(i<leftIncrementCtr-1)
					{
						x1+=3.0;//X offset
						xCenter = (x1+x1)/2;
					}
				}//for
			}
			if(isMultRTIndexFound)
			{
				for(int i= 0; i< rightDecrementCtr; i++)
				{
					if (i==0)
						k=k;
					else if (i>0)
						k=k+1;
					charConvert = a[k].toString();
					drawBlack(Quicksort2317_DoublingTest.tableHeight);
					StdDraw.text(xCenter, yCenter, charConvert);
					//StdOut.println("Right xCenter = "+xCenter+" Character = "+charConvert+" K = "+k);
					if(i<rightDecrementCtr-1)
					{
						x1+=3.0;//X offset
						xCenter = (x1+x1)/2;
					}
				}
			}
			if(isRTIndexExchanged)
			{
				drawRed(Quicksort2317_DoublingTest.tableHeight);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			if(isLTIndexExchanged)
			{
				drawBlue(Quicksort2317_DoublingTest.tableHeight);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			if(hasRTCrossedLTIndex)
			{
				drawMagenta(Quicksort2317_DoublingTest.tableHeight);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			if(hasLTCrossedRTIndex)
			{
				drawCyan(Quicksort2317_DoublingTest.tableHeight);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			if(isNoIndexFound)
			{
				drawGray(Quicksort2317_DoublingTest.tableHeight);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			if(isRTIndexEqual2Zero)
			{
				drawGray(Quicksort2317_DoublingTest.tableHeight);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			if(isPartitionFound&&!isInitialArraySet)
			{
				drawOrange(Quicksort2317_DoublingTest.tableHeight);
				StdDraw.text(xCenter, yCenter, charConvert);
			}
			x1+=3.0;//X offset
		}		 
		 //Wait for a short period
       StdDraw.pause(500);
	   Quicksort2317_DoublingTest.yOffset-=35;
	}
	public static boolean isRTIndexEqual2Zero(int k, int rtToLtIndexPtr, boolean exchange, boolean exchange2, boolean isInitialArraySet)
	{
		boolean value = false;
		if((k==0)&&(k==rtToLtIndexPtr)&&(!exchange)&&(!exchange2)&&(!isInitialArraySet))
			value = true;
		return value;
	}
	public static boolean isNoIndexFound(boolean isInitialArraySet, boolean isMultLTIndexFound, boolean isMultRTIndexFound, boolean isRTIndexExchanged, boolean isLTIndexExchanged, boolean hasRTCrossedLTIndex, boolean hasLTCrossedRTIndex)
	{
		boolean value = false;
		if(!isInitialArraySet&&!isMultLTIndexFound&&!isMultRTIndexFound&&!isRTIndexExchanged&&!isLTIndexExchanged&&!hasRTCrossedLTIndex&&!hasLTCrossedRTIndex)
		{
			value = true;
		}
		return value;
	}
	public static boolean isPartitionFound(int lo, double loIndexPtrValue, double rtToLtIndexPtrValue, int rtToLtIndexPtr, int k, int ltToRtIndexPtr, boolean isNoIndexFound, boolean isMultiLTIndexFound, boolean isMultiRTIndexFound, boolean isRTIndexExchanged, int partitionCtr)
	{
		boolean value = false;
		//if((k == rtToLtIndexPtr)&&(rtToLtIndexPtr==lo)&&(!exchange)&&(!isNoIndexFound)&&(!isMultiLTIndexFound)&&(!isMultiRTIndexFound))
			//if((k == rtToLtIndexPtr)&&(exchange2)&&(!exchange)&&(rtToLtIndexPtr>lo)&&(rtToLtIndexPtrValue>loIndexPtrValue)&&(!isRTIndexExchanged)&&(lo == 0)&&(partitionCtr == 1))
				if((k == rtToLtIndexPtr)&&(exchange2)&&(!exchange)&&(rtToLtIndexPtr>lo)/*&&(!isRTIndexExchanged)&&(lo == 0)*/&&(partitionCtr == 1))
		{
			value = true;
		}
		return value;
	}
	//blue: Switches leftScanIndex and rightScanIndex
	public static boolean isLTIndexExchanged(int k, int rtToLtIndexPtr, int ltToRtIndexPtr, boolean exchange, boolean leftScanIncremented, double rtToLtIndexPtrValue, double ltToRtIndexPtrValue)
	{
		boolean value = false;
		//if((k == rtToLtIndexPtr)&&(exchange)&&(rtToLtIndexPtr>=ltToRtIndexPtr)/*&&(rtToLtIndexPtrValue>ltToRtIndexPtrValue)*/)
			if((k == rtToLtIndexPtr)&&(exchange)&&(ltToRtIndexPtr<rtToLtIndexPtr)/*&&(rtToLtIndexPtrValue>ltToRtIndexPtrValue)*/)
		{
			value = true;
		}
		return value;
	}
	//cyan: Switches lo and rightScanIndex
	public static boolean hasLtCrossedRtIndex(int lo, double loIndexPtrValue, double rtToLtIndexPtrValue, int rtToLtIndexPtr, int k, int ltToRtIndexPtr, boolean isLTIndexExchanged, boolean isRTIndexExchanged, boolean exchange, boolean exchange2)
	{
		boolean value = false;
		//if((k == rtToLtIndexPtr)&&(exchange2)&&(!exchange)&&(rtToLtIndexPtr>lo)/*&&(rtToLtIndexPtrValue>loIndexPtrValue)*/&&(!isRTIndexExchanged))
			if((k == rtToLtIndexPtr)&&(exchange2)&&(!exchange)&&(ltToRtIndexPtr>=rtToLtIndexPtr)/*&&(rtToLtIndexPtrValue>loIndexPtrValue)*/&&(!isRTIndexExchanged))
		{
			value = true;
		}
		return value;
	}
		//red: Switches leftScanIndex and rightScanIndex
	public static boolean isRTIndexExchanged(int k, int rtToLtIndexPtr, int ltToRtIndexPtr, boolean exchange, boolean rightScanDecremented, double rtToLtIndexPtrValue, double ltToRtIndexPtrValue)
	{
		boolean value = false;
			if((k == ltToRtIndexPtr)&&(exchange)&&(ltToRtIndexPtr<rtToLtIndexPtr)/*&&(ltToRtIndexPtrValue<rtToLtIndexPtrValue)*/)
		{
			value = true;
		}
		return value;
	}
	//magenta: Switches lo and rightScanIndex
	public static boolean hasRtCrossedLtIndex(int lo, int rtToLtIndexPtr, int k, int ltToRtIndexPtr, boolean isLTIndexExchanged, boolean isRTIndexExchanged, double rtToLtIndexPtrValue, double loIndexPtrValue, boolean exchange, boolean exchange2)
	{
		boolean value = false;
		//if(((k == lo)&&(exchange2)&&(!exchange))&&(lo<rtToLtIndexPtr)&&(loIndexPtrValue<rtToLtIndexPtrValue)&&(!isLTIndexExchanged)&&(!isRTIndexExchanged))
			if(((k == lo)&&(exchange2)&&(!exchange))&&(ltToRtIndexPtr>=rtToLtIndexPtr)/*&&(loIndexPtrValue<rtToLtIndexPtrValue)*/&&(!isLTIndexExchanged)&&(!isRTIndexExchanged))
		{
			value = true;
		}
		return value;
	}
	public static boolean isInitialArraySet(int ltToRtIndexPtr, int rtToLtIndexPtr, boolean exchange, boolean leftScanIncremented, boolean rightScanDecremented)
	{
		boolean value = false;
		if((ltToRtIndexPtr==0)&&(rtToLtIndexPtr==0)&&(!exchange)&&((!leftScanIncremented)||(!rightScanDecremented)))
		{
			value = true;
		}
		return value;
	}
	public static boolean isMultRTIndexFound(String charConvert,int k, int rightDecrementCtr, int rtToLtIndexPtr, boolean exchange, boolean rightScanDecremented)
	{
		boolean value = false;
		if((k==(rtToLtIndexPtr-rightDecrementCtr))&&(!exchange)&&(rightScanDecremented))
			{
				//StdOut.println("K = "+k+" rtToLtIndexPtr = "+rtToLtIndexPtr+" Increment = "+rightDecrementCtr);
				k=k-1;
				value = true;					
			}
			return value;
	}
	public static boolean isMultLTIndexFound(String charConvert,int k, int leftIncrementCtr, int ltToRtIndexPtr, boolean exchange, boolean leftScanIncremented)
	{
		boolean value = false;
		if((k == (ltToRtIndexPtr-leftIncrementCtr))&&(!exchange)&&(leftScanIncremented))
			{
				//StdOut.println("K = "+k+" ltToRtIndexPtr = "+ltToRtIndexPtr+" Increment = "+leftIncrementCtr);
				value = true;
			}
		return value;
	}
	public static void drawBlack(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
		StdDraw.setPenColor(StdDraw.BLACK);
	}
	public static void drawWhite(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
		StdDraw.setPenColor(StdDraw.WHITE);
	}
	public static void drawRed(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
		StdDraw.setPenColor(StdDraw.RED);
	}
	public static void drawOrange(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
		StdDraw.setPenColor(StdDraw.ORANGE);
	}
	public static void drawCyan(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
		StdDraw.setPenColor(StdDraw.CYAN);
	}
	public static void drawBlue(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
		StdDraw.setPenColor(StdDraw.BLUE);
	}
	public static void drawGreen(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
		StdDraw.setPenColor(StdDraw.GREEN);
	}
	public static void drawMagenta(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
		StdDraw.setPenColor(StdDraw.MAGENTA);
	}
	public static void drawGray(int tableHeight)
	{
		StdDraw.setXscale(-5, tableWidth);
		StdDraw.setYscale(-5, tableHeight);
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
	public static void copyArray(Comparable[] destination, int[] source)
	{
		for (int i=0; i<source.length; i++)
		{
			destination[i] = source[i];
		}
	}
	public static void printArray(Comparable[] array)
	{
		for (int i=0; i<array.length; i++)
		{
			//StdOut.print(array[i]+" ");
		}
		//StdOut.println("");
	}
	public static void printArray2(Comparable[] array)
	{
		StdOut.println("");
		for (int i=0; i<array.length; i++)
		{
			StdOut.print(array[i]+" ");
		}
		StdOut.println("");
	}
	public static void setArrayLength(int arrayLength)
	{
		//int Quicksort2317_DoublingTest.arrayLength = a.length;
		Quicksort2317_DoublingTest.a = new Comparable[arrayLength];
	}
	@SuppressWarnings("unchecked")
	public static void startQS(String[] args)
	{ 
	// Read strings from standard input, sort them, and print.
		//String[] a = StdIn.readAllStrings();
		//StdOut.println("Length = "+a.length);
		//Comparable[] a;
		int[] inputArray = new int[args.length];
		setArrayLength(args.length);
		for(int i = 0; i<args.length; i++)
		{
			inputArray[i] = Integer.parseInt(args[i]);
		}
		
		copyArray(Quicksort2317_DoublingTest.a, inputArray);
		/*****************************************/
		//10/03/2017: PrintArray for cmd line output and Graph for java window has been disabled for testing:
		/****************************************************************************************/
		/*Quicksort2317_DoublingTest.printArray(Quicksort2317_DoublingTest.a);
		Quicksort2317_DoublingTest.graph(a, 0, 0, 0, exchange, exchange2, false, false, 0, 0, 0);*/
		/*****************************************/
		Quicksort2317_DoublingTest.sort(Quicksort2317_DoublingTest.a);
		//StdOut.println("***The left comparison ctr is = "+ltComparisonCtr+" The right comparison ctr is = "+rtComparisonCtr);
	}
}//class