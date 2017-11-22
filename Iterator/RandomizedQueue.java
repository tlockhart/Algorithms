package iterator;

//STEP 1 out of 5: Import Iterator library
import java.util.Iterator;
import exercises.*;
/**
 *  Written:       11/16/2017
 *  Last updated:  11/16/2017
 *
 *  <p><b>Requirement:</b> Class must be in <b>iterator</b> package and <b>exercises.*</b> must be imported.</p>
 *  <p><b>Javadoc:</b> javadoc -author -version -private -classpath .\algs4_sts.jar; -d .\javadoc RandomizedQueue.java</p>
 *  <p><b>Single Compilation:</b>     javac -cp .;algs4_sts.jar; RandomizedQueue.java
 *  <p><b>Execution 1:</b>    java -cp ../;.;algs4_sts.jar; iterator.RandomizedQueue &#60; distinct.txt</p>
 *  <p><b>Execution 2:</b>     java -cp ../;.;algs4_sts.jar; iterator.RandomizedQueue &#60; duplicates.txt</p>
 *
 *  <p><b>Summary:</b> RandomizedQueue is an abstract data type that implements the 
 *	functions of a queue.  However, the items manipulated by queue operations will be  
 *	random.  Since order is not a concern, but random data access is an array is the 
 *  best choice to implement the RandomQueue.  The program will read from a file, perform
 * 	the basic RandomizedQueue operations and print the queue to screen using an iterator.</p>
 *	
 *	<p><b>Note: The RandomizedQueue application is ran from a client (Percolation.java).<b></p>
 *
 *	<p><b>Files:</b> The files contain a series of strings, seperated by spaces.</p>
 *
 *  @version 1.7
 *  @author Tony Lockhart
 *  
 *  
 */
//STEP 2 out of 5: Create a class that implements Iterable.  Include the Generic type (Item) that it includes
public class RandomizedQueue<Item> implements Iterable<Item>
{
	/**
	* The size of the input array
	*/
	private int arraySize =10;
	/**
	* The array that holds the number of index values to be returned from the input array.
	*/
	private Item[] randomIndexArray;
	/**
	 * The number of elements the randonIndexArray can hold.  Entered from command prompt.
	 */
	private int randomArrayCapacity;
	//IMPORTANT NOTE: 
	/**
	 * The input array contains the data read from the external file and manipulated.  
	 * Since Item is a generic placeholder, it will be reduced to an object at runtime. 
	 * It can not be referenced by a static method, therefore, all methods are non-static.
	 */
	 private Item[]  input = (Item[]) new Comparable[arraySize];
	/**
	 * The total number of filled (data added) nodes in the array.
	 */
	 private int totalItems=0;
	/** 
	 *Returns value indicating if the randomized queue is empty.
	 */
	public boolean isEmpty() 
	{
		boolean returnValue=false;
		if(totalItems==0)
		{
			returnValue = true;
		}
		return returnValue;
	}
	/** 
	 *Declares the size of the randomIndexArray.
	 @param capacity The size of array entered via command line.
	 */
	public void setRandomIndex(int capacity)
	{
		this.randomArrayCapacity = capacity;
		randomIndexArray = (Item[]) new Comparable[this.randomArrayCapacity];
	}
	/** 
	 *Returns a reference to the randomIndex Array.
	 */
	public Item[] getRandomIndexArray()
	{
		return randomIndexArray;
	}
	/** 
	 *Returns a reference to the input Array.
	 */
	public Item[] getInput()
	{
		return this.input;
	}
	/** 
	 *Returns the number of elements (filled) in the inputArray.
	 */
	public int size()
	{
		return this.totalItems;
	}
	/** 
	 *Constructor.
	 */
	public RandomizedQueue(){
	}
	
	//P155 STEP 3 out of 5: Create Iterator Object when iterator method called automatically
	/** 
	 *The iterator method returns an object of the iterator class iteratorLoop
	 */
	public Iterator<Item> iterator()
	{
		return new iteratorLoop();
	}
	//STEP 4 out of 5: Declare Iterator Class, which defines the conditions for returning the next element with the next() method, after checking the conditions in the hasNext() method (p139)
	/** 
	 *Iterator Class contains hasNext() and next() methods, which are used to instantiate an iterator object.
	 */
	public class iteratorLoop implements Iterator<Item>
	{
		//REQUIREMENT: Make iterator mutually exclusive and random
		public Item[] input2 = (Item[]) new Comparable[arraySize];
		
		public iteratorLoop()
		{
			StdOut.println("Size = "+size());
		
			for(int i =0; i < totalItems; i++)
			{
				input2[i] = input[i];
			}
			shuffle(input2);
		}//constructor
		
		//P139 Nexted class can access members of the enclosing class
		//private Node current = head;
		int i = 0; //Substep 1: Starting index
		//private Item current = item[i];
		
		public boolean hasNext()
		{
			if(i > totalItems)
			{
				throw new java.util.NoSuchElementException();
			}
			return ((i >= 0)&&(input2[i] != null)&&(i < totalItems)); //Substep 2: Conditions to print next item
		}

		public Item next(){
			return input2[i++]; //Substep 3: Don't increment first, increment after check, so we print the first item.
		}

		public void remove()
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}
	//STEP 5 out of 5: Iterator implementation calls iterator object using an inhanced for loop(same as Iterator<String> 
	//i = this.iterator(); while(i.hasNext()){String s = i.next(); StdOut.println(s);}
	//STEP 5a: When passing a collection object, you must tell it what type of object it contains.
	/**
	 *loop iterates through the queue and prints out all the items in the list. 
	 @param dq A reference to a deque object.
	 */
	public void loop(RandomizedQueue<Item> dq)
	{
		StdOut.println("Print List With Iterator:");
		for(Item item: dq)
			StdOut.print(item+" ");
		StdOut.println("");
	}
	/**
	 *Return the default array value -1 as an Item to be stored in randomIndexArray
	 */
	public Item getRandomDefaultValue()
	{
		Integer value = -1;
		return (Item)value;
	}
	/**
	 *Returns a random item (but do not remove it)
	 */
	public Item sample()
	{
		Integer sampleIndex=RandomIndex();
		//int 
		Item sample= input[sampleIndex];
		// item = -1;
		//IMPORTANT NOTE: Generic methods only work with subtypes of Object. Integer is a sub type of Object. int is not an object but a primitive. So this is expected behaviour. This link is quite useful
		//http://mindprod.com/jgloss/intvsinteger.html
		Integer randomDefaultValue = -1;
			for (int i = 0; i <this.randomArrayCapacity; i++)
			{
				this.randomIndexArray[i] = (Item)randomDefaultValue;
			}
			for(int i = 0; i < this.randomArrayCapacity; i++)
			{
					if(this.randomIndexArray[i]== randomDefaultValue)
					{
						sampleIndex = RandomIndex();
						this.randomIndexArray[i] = (Item) sampleIndex;
						if(i > 0)
						{
							for (int j = 0; j < i; j++)
							{
								while(this.randomIndexArray[i] == this.randomIndexArray[j])
								{
									sampleIndex = RandomIndex();
									this.randomIndexArray[i] = (Item)sampleIndex;
									//StdOut.println("Index = "+this.randomIndexArray[i]);
								}//while
							}//for
						}
					}//else
								
			}//for 		
		sample= input[sampleIndex];
		return sample;
	}
	
	//Add to first and move them into a queue
	/**
	 *Adds generic item to the end of the queue. 
	 @param item The generic item to be added to the queue.
	 */
	public void enqueue(Item item)
	{
		if(item == null)
		{
			throw new java.lang.IllegalArgumentException();
		}

		int i = totalItems;
			//End of input array not reached
			if(i < this.arraySize)
			{
				if(this.input[i] == null)
				{
					this.input[i] = item;
				}
			}
			//End of input array reached.
			else if(this.totalItems >= this.arraySize)
			{
				resizeArray(2*this.arraySize);
				this.input[i] = item;
			}
			shuffle(input);
		this.totalItems++;
	}
	/**
	 *Doubles the size of the input array when the end is reached. 
	 @param newSize the size of the new array.
	 */
	public void resizeArray(int newSize)
	{
		Item[] tempArray = (Item[]) new Comparable[newSize];
		for(int i = 0; i<arraySize; i++)
		{
			tempArray[i] = this.input[i];
		}
		this.input = tempArray;
		this.arraySize = newSize;
	}
	/** 
	 *Prints the queue without an iterator.
	 */
	public void print(Item[] input)
	{
		StdOut.println("The results are:");
		//for(int i = 0; i < this.totalItems; i++)
		for(int i = 0; i < input.length; i++)
		{
			StdOut.print(input[i]+" ");
		}
		StdOut.println("");
	}
	
	//P32 Knuth Shuffle
	/** 
	 *Shuffles the location of the generic item added.
	 */
	public void shuffle(Item[] item)
	{
		int N = this.totalItems;
		for(int i = 0; i < N; i++)
		{//Exchange a[i] with random element in item[i..N-1]
			int r = i + StdRandom.uniform(N-i);
			Item temp = item[i];
			item[i] = item[r];
			item[r] = temp;
		}
	}
	/** 
	 *Selects a random index in the array, switches the value with the end index, before deleting the end index.
	 */
	public Item dequeue()
	{
		if(isEmpty())
		{
			throw new java.util.NoSuchElementException();
		}
		int randomIndex = RandomIndex();
		if(randomIndex != totalItems-1)
		{
			exch(randomIndex);
		}
		Item returnItem = input[totalItems-1];
		input[totalItems-1] = null;
		resizeArray(--arraySize);
		--totalItems;
		return returnItem;
	}
	//Keep values consistent
	/** 
	 *Returns a random index from the input array.  Since generics compile to Objects, 
	 *the elements (randomIndex) in the Object array (randomIndexArray) must be
	 *returned as Integers, not ints.  Because an object can be casted to an Integer 
	 *(see permutations.java).  Otherwise conversion methods woulds be required 
	 *(.intValue() or new Integer(i);). 
	 */
	public Integer RandomIndex()
	{
		int returnValue = StdRandom.uniform(totalItems);
		//StdOut.println("Random Index = "+returnValue);
		return returnValue;
	}
	/** 
	 *Exchanges the value of a random integer with the last index value in the input array.
	 @param i random array index value to be exchanged.	 
	 */
	public void exch(int i)
	{
		Item temp = input[i];
		input[i] = input[totalItems-1];
		input[totalItems-1] = temp;
	}
	public static void main(String[] args)
	{
		String[] input = StdIn.readAllStrings();
		RandomizedQueue dq = new RandomizedQueue();
		
		for(int i = 0; i<input.length; i++)
		{
			dq.enqueue(input[i]);
		}
		StdOut.println("Added chars at the BEGINNING:");
		dq.print(dq.input);
		String input2 = "S";

		StdOut.println("Call Iterator");
		dq.loop(dq);
		StdOut.println("Dequeued item = "+dq.dequeue());

		dq.loop(dq);
		StdOut.println("Sample item = "+dq.sample());
	}		
}
