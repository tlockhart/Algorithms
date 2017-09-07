//compile: javac -cp .;algs4_ts.jar twoVarSort.java
//run: java -cp .;algs4_ts.jar twoVarSort <tiny.txt

public class twoVarSort{
	public static String[] array = {"B", "E", "S", "C","A"};
	public static int N = 5;
	public twoVarSort(){
		//setSentinelValue(array);
		/*for(int rtEndMinIndex = 2; rtEndMinIndex < N; rtEndMinIndex++)
		{
		}*/
	}//twoVarSort
	
	public void setSentinelValue(String[] array)
	{
		for(int i = N-1; i >= 1; i--)
		{
			if(less(array[i], array[i-1]))
			{
				String temp = array[i];
				array[i] = array[i-1];
				array[i-1]=temp;
			}
		}
	}
	public static void printArray(Comparable[] array)
	{
		String output = new String();
		for (int i = 0; i< N; i++)
		{
			output = output +array[i];
		}
		StdOut.println("Output = " +output);
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
	public static void twoVarInsertionSort(Comparable[] array, int N)
	{
		for (int i=2; i<N; i++)
		{
			Comparable holdRTMinValue = array[i];
			int maxIndex = i;
			//When the value of maxIndex-1 is less than or equal to holdRTMinValue,
			//maxIndex is the position to place the holdRTMinValue (see after while loop).
			while(less(holdRTMinValue, array[maxIndex-1]))
			{
				//1) Move max value right
				array[maxIndex] = array[maxIndex-1];
				//2) Decrement j value
				maxIndex--;
				//(3) Pring Array
				StdOut.println("");
				twoVarSort.printArray(array);
			}
			array[maxIndex] = holdRTMinValue;
			//4)Print Array
			StdOut.println("");
			twoVarSort.printArray(array);
		}//for	
	}
	
	public static void main(String[] args)
	{
		twoVarSort test  = new twoVarSort();
		test.printArray(test.array);
		test.setSentinelValue(test.array);
		test.printArray(test.array);
		test.twoVarInsertionSort(test.array, test.N);
	}
}