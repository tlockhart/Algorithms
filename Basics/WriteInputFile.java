//Program Reads a integers from a testfile and writes them to the command prompt
//Run the program with a textfile of integers as arguments 
//Compile: javac -cp .;algs4_ts.jar WriteInputFile.java
//Run: java -cp .;algs4_ts.jar WriteInputFile < largeT.txt

public class WriteInputFile{
	static int int1,int2,avg;
	static String name = new String();
	
	public static void main(String[] args){
		int[] whiteList;
		whiteList=StdIn.readAllInts();
		for(int i=0; i<whiteList.length; i++)
		{
			//StdOut.printf("%-15d",whiteList[i]);
			StdOut.print(whiteList[i]+" ");
		}
	}
}