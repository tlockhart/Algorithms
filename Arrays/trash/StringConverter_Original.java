//compile: javac -cp .;algs4_ts.jar StringConverter_Original.java
//run: java -cp .;algs4_ts.jar StringConverter_Original

public class StringConverter_Original{
	public String input;
	public boolean isIntNegative = false;
	public int[] intArray;
	public final static int hornersValue = 10;
	
	public StringConverter_Original(String input)
	{
		this.input = input;
		//Step 1: Declare intArray, assumeing the value is positive
		intArray = new int[input.length()];
	}
	//Multiply previous values in array by 10 to get weighted value for integers
	public void hornersAlgorithmForStrtoInt(int[] intArray, int index)
	{
		while (index>0)
		{
			//Step 2: Make sure you do not go past the ArrayBounds, 
			//or increment the digit in the ones position.
			if((index-1>=0)&&(index-1<intArray.length-1))
			{
				index--;
				intArray[index] = intArray[index]*10;
				StdOut.println("In hornersAlgorithm for negative number, Index = "+index+" Value = "+intArray[index]);
			}
		}
	}
	public int strToInt()
	{
		int value = 0;
		for(int index = 0; index<input.length(); index++)
		{
			//Step3: If integer is negative and the loop is passed the first 
			//character set intArray index to 1 less than the input index.
			if(isIntNegative)
			{
					intArray[index-1] = (int)input.charAt(index)-'0';
					
					
						//multiply by 10 to get place value;
						hornersAlgorithmForStrtoInt(intArray, index-1);
						//isIntNegative = false;
						StdOut.println("Int Value Negative = "+intArray[index-1]);
					//}
			}
			//Step4:(One Time Exceution): Set isIntNegative toggle to true if first value is '-',  
			//executes if once.  Set the length of intArray to 1 minus length
			else if((input.charAt(index))== '-')
			{
				
				//StdOut.println("Length of input = "+input.length());
				//declare int array with 1 less position as input.length() for Negative numbers
				intArray = new int[input.length()-1];
				isIntNegative = true;
				//intArray = new int[input.length()-1];
				StdOut.println("Set inIntNegative = "+isIntNegative);
				//If you don't fill this space, it will contain a 0;
				continue;
			}
			//Step5: If integer is positive, set length to match the string length
			//Note you can not declare the intArray over and over and add to it.
			//Don't put declaration stmts in a loop
			else if(!isIntNegative)
			{
				
				intArray[index] = (int)input.charAt(index)-'0';
				
				//multiply by 10 to get place value;
				hornersAlgorithmForStrtoInt(intArray, index);
				
				//isIntNegative = false;
				StdOut.println("Int Value Not Negative = "+intArray[index]);
			}
		}
		return value;
	}
	public void printArray()
	{
		StdOut.println("Print Value = ");
		for(int index = 0; index < intArray.length; index++)
		{
			StdOut.println(intArray[index]);
		}
		StdOut.println("");
	}
	
	public String intToStr()
	{
		String value = new String();
		
		
		return value;
	}
	
	public int sumInteger()
	{
		int value = 0;
		for(int index =0; index<intArray.length; index++)
		{
			value += intArray[index];
		}
		if(isIntNegative)
		{
			value = value *-1;
		}
		return value;
	}
	
	public static void main(String[] args)
	{
		String input1 = "-456";
		String input2 = "-12";
		String input3 = "12";
		String input4 = "-1";
		String input5 = "0";
		
		//String to Int
		StringConverter_Original object = new StringConverter_Original(input1);
		object.strToInt();
		object.printArray();
		StdOut.println("The Integer value is "+object.sumInteger());
		int intValue = object.sumInteger();
		
		//Int to String
		
	}
}