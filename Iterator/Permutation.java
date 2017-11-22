/**  <p><b>Requirement:</b> Class must be in <b>sorts</b> package and <b>exercises.*</b> and <b>java.lang.*</b> must be imported.</p>
  *compile Single: javac -cp .;algs4_sts.jar; Permutation.java
  *compile Multiple: javac -cp .;algs4_sts.jar; RandomizedQueue.java Permutation.java
  *run1: java -cp ../;.;algs4_sts.jar; iterator.Permutation 3 < distinct.txt
  *run2: java -cp ../;.;algs4_sts.jar; iterator.Permutation 3 < duplicates.txt
  **/
  package iterator;
  import exercises.*;
   /**
 *  Written:       11/16/2017
 *  Last updated:  11/16/2017
 *
 *  <p><b>Requirement:</b> Class must be in <b>iterator</b> package and <b>exercises.*</b> must be imported.</p>
 *  <p><b>Javadoc:</b> javadoc -author -version -private -classpath .\algs4_sts.jar; -d .\javadoc Permutation.java</p>
 *  <p><b>Single Compilation:</b>     javac -cp .;algs4_sts.jar; Permutation.java
 *  <p><b>Multiple Compilation:</b>     javac -cp .;algs4_sts.jar; RandomizedQueue.java Permutation.java
 *  <p><b>Execution 1:</b>     java -cp ../;.;algs4_sts.jar; iterator.Permutation 3 &#60; distinct.txt</p>
 *  <p><b>Execution 2:</b>     java -cp ../;.;algs4_sts.jar; iterator.Permutation 3 &#60; duplicates.txt</p>
 *
 *  <p><b>Summary:</b> Permutation is a client. It takes an integer k as a command-line 
 *	argument; reads in a sequence of strings from standard input using StdIn.readString(); 
 *  and prints exactly k of them, uniformly at random. Print each item from the sequence 
 *  at most once. </p>
 *  
 *	<p><b>Note: Percolation is a client that runs RandomizedQueue.java.<b></p>
 *
 *	<p><b>Files:</b> The files (distinct.txt and duplicates.txt) contain a series of strings, seperated by spaces.</p>
 *
 *  @version 1.7
 *  @author Tony Lockhart
 *  
 *  
 */
  public class Permutation {
   public static void main(String[] args)
   {
	    int subset;
	  try{
		  subset = Integer.parseInt(args[0]);
	  }
	  catch(ArrayIndexOutOfBoundsException e)
	  {
		  subset = 0;
	  }
	   String[] strInput = StdIn.readAllStrings();
		RandomizedQueue dq = new RandomizedQueue();
		dq.setRandomIndex(subset);
		
		for(int i = 0; i<strInput.length; i++)
		{
			dq.enqueue(strInput[i]);
		}
		StdOut.println("Added chars at the BEGINNING:");
		dq.print(dq.getInput());
		String input2 = "S";

		StdOut.println("Call Iterator");
		dq.loop(dq);
		StdOut.println("Dequeued item = "+dq.dequeue());

		dq.loop(dq);
		StdOut.println("Sample item = "+dq.sample());
	   
	   //Integer.parseInt(StdIn.readAll());
	   StdOut.println("Number of Random items to Print from List = "+subset);
	   StdOut.println("The Array Indexes Are As Follows:");
	   dq.print(dq.getRandomIndexArray());
	   StdOut.println("");
	   StdOut.println("The Values From the Input Array Are As Follows:");
	   /*****************************************************************************/
	   //Important: Since generics compile to Objects during compilation, we must 
	   //make sure the elements (randomIndex) in the Object array (randomIndexArray) 
	   //are Integers, not ints.  Because an object can be casted to an Integer 
	   //(see below).  Otherwise we would have to use .intValue() or new Integer(i);
	   //to convert the values back and forth.  Since the randomIndexArray contains the  
	   //randomly generated index values of the input array, the array must be given after  
	   //the getRandomIndexArray() method is called.  
	   //http://mindprod.com/jgloss/intvsinteger.html
	   /*****************************************************************************/
	   for(int i = 0; i < subset; i++)
	   {
		   StdOut.println(dq.getInput()[(Integer)dq.getRandomIndexArray()[i]]+" ");
	   }
   }
}