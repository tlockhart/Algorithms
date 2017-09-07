//compile: javac -cp .;algs4_ts.jar ReverseString.java
//run: java -cp .;algs4_ts.jar ReverseString

public class ReverseString
{
	public char[] strCharArray;
	public String str;
	public final static char space = ' ';
	
	//public reverseString(String str)
	public ReverseString(String str)
	{
		//int startIndex = 0;
		char temp = '\0';
		strCharArray = str.toCharArray();
		int startIndex = 0;
		for(int endIndex = strCharArray.length-1; endIndex >= startIndex; endIndex--)
		{
			temp = strCharArray[endIndex];
			strCharArray[endIndex] = strCharArray[startIndex];
			strCharArray[startIndex] = temp;
			startIndex++;
		}
	}
	public void printArray()
	{
		StdOut.println("***********************");
		//StdOut.println("");
		for (int i = 0; i<strCharArray.length; i++)
		{
			StdOut.print(strCharArray[i]);
		}
		StdOut.println("***********************");
	}
	public void traverseArray()
	{
		int startIndex=0;
		//Step1: Important to start your counter at 0;
		/********************************************/
		int ctr = 0;
		int beginWord = 0;
		int endWord = 0;
		//Step2:For loop must stop when EndIndex is greater that StartIndex, so when we check
		//endIndex-1 it does not hit the ArrayOutOfBounds (-1).
		for(int endIndex=strCharArray.length-1; endIndex>startIndex; endIndex--)
		{
			if(strCharArray[endIndex-1] == space)
			{
				beginWord = endIndex;
				endWord = endIndex+ctr;
				//*********************
					StdOut.println("BeginWord index = "+beginWord+" EndWord index = "+endWord+" Counter = "+ctr);
					StdOut.println("BeginWord value = "+strCharArray[beginWord]+" EndWord value = "+strCharArray[endWord]);
					reverseWord(strCharArray, beginWord, endWord);
				//Step3: Reset ctr to -1, so when it hits a space, the space is not counted
				/***********************************************************/
				ctr = -1;
			}
			else if(endIndex-1 == 0)
			{
				//Step4: Take care of the off by 1 error, since we are 
				//checking for the 0 index.  Instead of the space before 
				//the start of the last word
				/***********************************************************/
				endIndex=endIndex-1;//endIndex points to index (1), so subtract 1
				ctr++;//ctr = one less than the total count, so add 1
				beginWord = endIndex;
				endWord = endIndex+ctr;//Don't add +1 because we are starting with 0 index
				StdOut.println("BeginWord index = "+beginWord+" EndWord index = "+endWord+" Counter = "+ctr);
				StdOut.println("BeginWord value = "+strCharArray[beginWord]+" EndWord value = "+strCharArray[endWord]);
				reverseWord(strCharArray, beginWord, endWord);
				break;//beginning of string reached, stop loop
			}
			else
			{
				//Step5:Increment counter if 
				/***********************************************************/
				ctr++;
			}
		}//for
	}//traverseArray
	public void reverseWord(char[] strCharArray, int startPtr, int endPtr)
	{
		char temp = '\0';
		int endWord = endPtr;
		//Step6: You don't need to switch anything if beginWord = endWord (index 15) just 
		//leave it in place you will get ArrayIndexOutOfBoundsException in return to traverse
		/***********************************************************/
		for(int beginWord = startPtr; beginWord < endWord; beginWord++)
		{
			temp = strCharArray[endWord];
			strCharArray[endWord] = strCharArray[beginWord];
			strCharArray[beginWord] = temp;
			StdOut.println("StartWord value = "+strCharArray[beginWord]+" Start Index = "+beginWord+" EndWord value = "+strCharArray[endWord]+" End Index = "+endWord);
			endWord--;
		}
	}
	
	public static void main(String[] args)
	{
		ReverseString object = new ReverseString("in search of algorithmic elegance!");
		object.printArray();
		object.traverseArray();
		object.printArray();
	}
}//ReverseString