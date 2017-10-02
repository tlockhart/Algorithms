//Compile: javac -cp .;algs4_ts.jar IntegerInput1215.java
//Run: java -cp .;algs4_ts.jar IntegerInput1215

public class IntegerInput1215{//no arguments
	public static int[] intArray;
	public static String startString;
	public static char[] convertedString;
	public static String[] returnValue;
	public static int stringLength;
	public static int numberLength;
	
	public IntegerInput1215(String value, int numberLength){
		startString=value;
		stringLength =value.length();//10
		convertedString=startString.toCharArray();
		this.numberLength=numberLength;
		returnValue=new String[this.numberLength];
	}
	public static void readInts(char delimeter)
	{
		String appendValue=new String();
		int j=0;
		
		for (int i=0; i<=convertedString.length; i++)
		{
			if(!(i>=convertedString.length))
			{
				if(convertedString[i] != delimeter){
					appendValue+=convertedString[i];
				}
				else if((convertedString[i] == delimeter))
				{
					returnValue[j] = appendValue;
					Integer s = new Integer(j);
					appendValue = "";
					j++;
				}
			}
			//Required to catch the end condition, when j equals the number of integers in the string
			//and when i is at the end of the char array convertedString
			else if ((i==convertedString.length)&&(j==numberLength-1))
			{
					returnValue[j] = appendValue;
					Integer s = new Integer(j);
					appendValue = "";
					break;
			}
			
		}
	}
	public static void writeInts()
	{
			for(int i=0; i<returnValue.length;i++)
			{
				StdOut.println("String Length = "+stringLength);
				StdOut.println("ReturnValue Length = "+returnValue.length);
				StdOut.println(returnValue[i]);
			}
	}
	public static void main(String[] args){
		IntegerInput1215 parseString = new IntegerInput1215("0 45 22 33", 4);
		parseString.readInts(' ');
		parseString.writeInts();
	}
}