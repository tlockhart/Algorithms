//NOTE: Run the program with no arguments 
//When program is running, the input stream will 
//wait for you to enter inputs seperated by space
//After the input is entered type enter to execute.

//Compile: javac -cp .;algs4_ts.jar PrintAVG1121.java
//Run: java -cp .;algs4_ts.jar PrintAVG1121 Tony 22 15
public class PrintAVG1121{
	static int int1,int2,avg;
	static String name = new String();
	public static void main(String[] args){
		/*name=StdIn.readString();
		int1=StdIn.readInt();
		int2=StdIn.readInt();*/
		name = args[0];
		int1 = Integer.parseInt(args[1]);
		int2 = Integer.parseInt(args[2]);
		avg=int1/int2;
		StdOut.printf("%-14s", name);
		StdOut.printf("%-14d", int1);
		StdOut.printf("%-14d", int2);
		StdOut.printf("%-14d\n", avg);
	}
}