//compile: javac -cp .;algs4_ts.jar StringConverter.java
//run: java -cp .;algs4_ts.jar StringConverter

public class StringConverter{
	public String strInput;
	public int intInput;
	public boolean isIntNegative = false;
	
	//Array used to turn string to integer array, then integer value
	public int[] intArray;
	
	//Array used to turn integer to character, then to String
	public char[] charArray;
	public final static int hornersValue = 10;
	
	//Set the int Length to 1 for the ones value and incremented if the string is negative
	public int intLength = 1;
	//Step1: You must set the String value to a default value, so it will not show as null
	//strWAppendedChars is the String whose value is the values of the charArray appended together.
	public String strWAppendedChars=new String();
	
	public StringConverter(String input)
	{
		this.strInput = input;
		//Step 2: Declare intArray, assumeing the value is positive
		intArray = new int[strInput.length()];
		//charArray = intInput.length()];
	}
	public StringConverter(int input)
	{
		this.intInput = input;
		
		//STEP3: In order to get the number of digits, you must first make sure the integer is negative
		//StdOut.println("Int input = "+this.intInput);
		if (this.intInput < 0)
		{
			isIntNegative = true;
			intLength++; //Step3: increment length for the negative value;
			//STEP4: Negate the number to get length
			this.intInput = negate(this.intInput);
			//StdOut.println("The value is no longer negative VALUE = "+this.intInput);
		}
		else 
			isIntNegative = false;
		
		/*************************************************/
		//THIS IS THE INTEGER
		/*************************************************/
		int intValue = this.intInput;
		//Step 5: Now get the number of digits, by counting the number of times you divide by ten,
		//However, since you can't count the ones place, initialize length to 1 in intLength declaration;
		while (intValue > 10)
		{
			intValue = intValue/10;
			//StdOut.println("Value = "+intValue);
			intLength ++;
		}
		//StdOut.println("Length of integer2 = "+intLength);
		charArray = new char[intLength];
	}
	public int negate(int value)
	{
		return value * -1;
	}
	public String intToStr(int input)
	{
		if (isIntNegative)
		{
			insertNegNumInCharArray(input);
		}
		else
		{
			insertPosNumInCharArray(input);
		}
		
		//Append character values (from string)together, and store in strWAppendedChars
		for (int i = 0; i<intLength; i++)
		{
			strWAppendedChars+=charArray[i];
		}
		return strWAppendedChars;
	}
	
	public void insertNegNumInCharArray(int input)
	{
		int value = input;
		int digit = 0;
		//StdOut.println("Input Value = "+value);
		for(int index = 0; value/10>0; index++)
		{
			digit = value%10;
			
			//STEP6: You MUST Subtract the index from the length to get the correct char index,
			//Since the digits will be generated backwards (last index first, first index last)
			charArray[(intLength-1)-index] = (char)(digit+'0');
			value = value/10;
		}
		digit =value;
		charArray[1] = (char)(digit+'0');
		charArray[0] = '-';
	}
	public void insertPosNumInCharArray(int input)
	{
		int value = input;
		int digit = 0;
		//StdOut.println("Input Value = "+value);
		for(int index = 0; value/10>0; index++)
		{
			digit = value%10;
			//STEP7: You MUST Subtract the index from the length to get the correct char index
			charArray[(intLength-1)-index] = (char)(digit+'0');
			//StdOut.println("Digit = "+digit);
			value = value/10;
			//StdOut.println("Value = "+value);
		}
		digit = value;
		
		//STEP8:turn the integer digits to character digits by adding the value
		//of '0' to the digit, then casting it to its ASCII value.
		charArray[0] = (char)(digit+'0');
	}
	
	//Multiply previous values in array by 10 to get weighted value for integers
	public void hornersAlgorithmForStrtoInt(int[] intArray, int index)
	{
		while (index>0)
		{
			//Step 9: Make sure you do not go past the ArrayBounds, 
			//or increment the digit in the ones position.
			if((index-1>=0)&&(index-1<intArray.length-1))
			{
				index--;
				intArray[index] = intArray[index]*10;
				StdOut.println("In hornersAlgorithm for negative number, Index = "+index+" Value = "+intArray[index]);
			}
		}
	}
	//Multiply previous values in array by 10 to get weighted value for integers
	/*public void hornersAlgorithmForIntToSTR(char[] charArray, int index)
	{
	}*/
	public int strToInt(String strInput)
	{
		int value = 0;
		for(int index = 0; index<strInput.length(); index++)
		{
			//Step10: If integer is negative and the loop is passed the first 
			//character, set intArray index to 1 less than the input index.
			//NOTE:isIntNegative toggle has to set negative value to true
			//before this if stmt can be executed.
			if(isIntNegative)
			{
					intArray[index-1] = (int)strInput.charAt(index)-'0';
					hornersAlgorithmForStrtoInt(intArray, index-1);
	
			}
			//Step11:(One Time Exceution): Set isIntNegative toggle to true if first value is '-',  
			//Set the length of intArray to 1 minus length, for array one off
			else if((strInput.charAt(index))== '-')
			{
				//declare int array with 1 less position as input.length() for Negative numbers
				intArray = new int[strInput.length()-1];
				isIntNegative = true;
				continue;
			}
			//STEP12: If integer is positive, set length to match the string length
			//Note you can not declare the intArray over and over and add to it.
			//Don't put declaration stmts in a loop, Because it will reset array to
			//default values.
			else if(!isIntNegative)
			{
				
				intArray[index] = (int)strInput.charAt(index)-'0';
				
				//Step 13: Use Horners Rule to multiply values by 10 to get place value;
				hornersAlgorithmForStrtoInt(intArray, index);
			}
		}
		
		//Sum all individually weighted integer values in intArray and return as an integer
		value = this.sumInteger();
		return value;
	}
	
	//Sum individually weighted intArray integer values, and store as an interger value
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
		String stringInput1 = "-456";
		String stringInput2 = "-12";
		String stringInput3 = "12";
		String stringInput4 = "-1";
		String stringInput5 = "0";
		
		//String to Int
		StringConverter strToIntObject = new StringConverter(stringInput1);
		int intValue = strToIntObject.strToInt(strToIntObject.strInput);
		StdOut.println("*The Integer value is "+intValue);
		
		//Int to String
		StringConverter intToStr = new StringConverter(intValue);
		String stringValue = intToStr.intToStr(intToStr.intInput);
		StdOut.println("*The string value = "+stringValue);

		
	}
}