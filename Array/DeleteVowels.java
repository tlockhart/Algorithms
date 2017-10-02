//compile: javac -cp .;algs4_ts.jar DeleteVowels.java
//run: java -cp .;algs4_ts.jar DeleteVowels

public class DeleteVowels{
	char[] strCharArray;
	char[] removeArray;
	int numOfCharsRemoved=0;
	public DeleteVowels(String str, String remove)
	{
		strCharArray = str.toCharArray();
		removeArray = remove.toCharArray();
	}
	public boolean removeChars()
	{
		boolean returnValue = false;
		try
		{
			for(int i = 0; i<strCharArray.length; i++)
			{
				for (int j = 0; j<removeArray.length; j++)
				{
					if(strCharArray[i]==removeArray[j])
					{
						StdOut.println("Match = "+strCharArray[i]+" Value = "+i);
						delete(strCharArray[i], i);
						numOfCharsRemoved++;
						returnValue = true;
						
						break;
					}
				}//for
			}//for
		}catch(Exception e)
		{}
		return returnValue;
	}//removeChar
	public boolean delete(char strChar, int index)
	{
		char temp = '\0';
		boolean returnValue = false;
		try{
			//Step1:Start loop where the value was found
			for (int i=index; i<strCharArray.length-1; i++)
			{
				temp = strChar;
				strCharArray[i] = strCharArray[i+1];
			}
			strCharArray[strCharArray.length-1]='\0';
			returnValue = true;
		}catch(Exception e)
		{
		}
		return returnValue;
	}
	public boolean printArray()
	{
		boolean returnValue = false;
		try{
			for (int i = 0; i<strCharArray.length; i++)
			{
				StdOut.print(strCharArray[i]);
				returnValue = true;
			}
		}catch(Exception e)
		{
		}
		return returnValue;
	}
	//Step2:To truncate copy the array elements and set the original array to the new array.
	public void resizeArray(int size)
	{
		//charToString;
		char[] temp = new char[size];
		for(int i = 0; i<size; i++)
		{
			temp[i] = strCharArray[i];
		}
		strCharArray=temp;
		temp = null;
		//return true;
		
	}
	
	public static void main(String[] args)
	{
		DeleteVowels object = new DeleteVowels("Peter Piper picked a pale of roses.", "aeiou");
		object.removeChars();
		object.printArray();
		StdOut.println("Array Size ="+object.strCharArray.length);
		int actualArraySize = object.strCharArray.length-object.numOfCharsRemoved;
		StdOut.println("Actual Array Size ="+actualArraySize);
		StdOut.println("***************************************");
		object.resizeArray(actualArraySize);
		
		//Step3: Use the valueof method to convert a char array back to a string.
		String backToString =String.valueOf(object.strCharArray);
		StdOut.println("String ="+backToString+" Size equals = "+backToString.length());
		object.printArray();
		
	}
}