//compile: javac -cp .;algs4_ts.jar FindFirstNonRepeatChar.java
//run: java -cp .;algs4_ts.jar FindFirstNonRepeatChar

//Since lowercase a starts at 97(a), if you just add 97 to the index, you will get the lowercase ASCII value.
//There are 26 chars in the alphabets, so the array is 26 chars?

public class FindFirstNonRepeatChar
{
	private String str = new String();
	private char[] charArray;//str converted to char array
	private int[] ctrArray = new int[26];//store value of the array
	private final static int numToRepeat = 1;
	public FindFirstNonRepeatChar(String str)
	{
		this.str = str;
		charArray = str.toCharArray();
		//ctrArray = new Char[this.str.length];
	}
	public int convertCharToInt(char i)
	{
		//subtract the value of i by ASCII  char 'a' value to get index
		int value = (int) i- 'a';
		//StdOut.println("The letter convert = "+i+", The value = "+value);
		return value;
	}
	/*public char convertIntToChar(int i)
	{
		char value = (char)(i+'a');
		StdOut.println("Value = "+i+" The letter convert = "+value+", The value = "+value);
		return value;
	}*/
	public void countLetters()
	{
		for (int i = 0; i<this.charArray.length; i++)
		{
			int j =0;
			//The ctrArray index is the ASCII value of the char subtracted by ASCII value 'a' (97),  
			//the number of times the letter appears is the value of the index (j)
			this.ctrArray[this.convertCharToInt(this.charArray[i])]+=++j; 
			
		}
	}
	public char findNonRepeatChar()
	{
		char charToReturn = '\0';
		//int count = 0;
		for ( int i =0; i<this.charArray.length; i++)
		{
			//The counter array index is the ASCII value of the char,  you can convert this value to int to check the index value
			if((this.ctrArray[this.convertCharToInt(this.charArray[i])])==FindFirstNonRepeatChar.numToRepeat)
			{
				StdOut.println("The first nonrepeating letter "+this.charArray[i]+" appears "+this.ctrArray[this.convertCharToInt(this.charArray[i])]+ " times.");
				//The counter array index is the ASCII value of the char,  you can convert this value to int to check the index value
				//All you need to do to change the int value to char is cast the ASCII value
				charToReturn = (char)this.charArray[i];
				break;
			}
			
		}
		return charToReturn;
	}
	public static void main(String[] args)
	{
		//FindFirstNonRepeatChar object = new FindFirstNonRepeatChar("letter");
		FindFirstNonRepeatChar object = new FindFirstNonRepeatChar("already");
		object.countLetters();
		
		StdOut.println("The first nonrepeating letter = "+object.findNonRepeatChar());
		
	}
	
}