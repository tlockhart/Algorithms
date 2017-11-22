package iterator;

//STEP 1 out of 5: Import Iterator library
import java.util.Iterator;
import exercises.*;
/**
 *  Written:       11/16/2017
 *  Last updated:  11/16/2017
 *
 *  <p><b>Requirement:</b> Class must be in <b>iterator</b> package and <b>exercises.*</b> must be imported.</p>
 *  <p><b>Javadoc:</b> javadoc -author -version -private -classpath .\algs4_sts.jar; -d .\javadoc Deque.java</p>
 *  <p><b>Single Compilation:</b>     javac -cp .;algs4_sts.jar; Deque.java
 *  <p><b>Execution 1:</b>    java -cp ../;.;algs4_sts.jar; iterator.Deque &#60; distinct.txt</p>
 *  <p><b>Execution 2:</b>     java -cp ../;.;algs4_sts.jar; iterator.Deque &#60; duplicates.txt</p>
 *
 *  <p><b>Summary:</b> Deque is an abstract data type that implements the functions of a
 *	double-ended queue.  It supports adding and removing items from the front or back of
 *	the data structure.  This implementation uses a linked list to store the queue, 
 *  because of the requirement to add remove data from the front and the end of the queue.
 *  Since a linked list stores a reference to the head and tail, this data type is
 * 	the best choice to implement the dequeue.  The program will read from a file, perform
 * 	the basic queue operations and print the queue to screen using an iterator.</p>
 *	
 *	<p><b>Note: Deque is a stand alone application.<b></p>
 *
 *	<p><b>Files:</b> The files contain a series of strings, seperated by spaces.</p>
 *
 *  @version 1.7
 *  @author Tony Lockhart
 *  
 *  
 */
//STEP 2 out of 5: Create a class that implements Iterable.  Don't forget to include the Generic type.
public class Deque<Item> implements Iterable<Item>
{
	/**
	 * The size of the input array
	 */
	 private int arraySize =10;
	/**
	 * The input array contains the data read from external file and manipulated.
	 */
	 private Item[] input = (Item[]) new Comparable[arraySize];
	/**
	 * The total number of nodes in the array.
	 */
	 private int totalItems=0;
	/**
	 * The first node in the queue.
	 */
	Node head = new Node();
	/**
	 * The last node in the queue.
	 */
	 Node tail = new Node();
	
	/** 
	 *Deque constructor intializes the linked list head and tail nodes to null.
	 */
	public Deque()
	{
		head.item = null;
		head.next = null;
		
		tail.item = null;
		tail.next = null;
	}
	/** 
	 *Node class defines the structure of the linked list.
	 */
	public class Node{
		Node next;
		Node previous;
		Item item;
	}
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
	 *Returns the number of items on the randomized queue.
	 */
	public int size()
	{
		return this.totalItems;
	}
	/**
	 *Items a generic item to the beginning of the queue. 
	 @param item The generic item to be added to the queue.
	 */
	public void addFirst(Item item)
	{
		if(item == null)
		{
			throw new java.lang.IllegalArgumentException();
		}

		if(this.isEmpty())
		{
			this.head.item = item;
			this.tail = this.head;
		}
		else{
			Node temp = createNode(item, this.head);
			temp.next = this.head;
			this.head = temp;
		}
		this.totalItems++;
	}
	/**
	 *Items a generic item to the end of the queue. 
	 @param item The generic item to be added to the queue.
	 */
	public void addLast(Item item)
	{
		if(item == null)
		{
			throw new java.lang.IllegalArgumentException();
		}
		
		if(this.isEmpty())
		{
			this.tail.item = item;
			this.head = this.tail;
		}
		else
		{
			Node temp = createNode(item, null);
			this.tail.next = temp;
			temp.previous = this.tail;
			this.tail = temp;
		}
		this.totalItems++;
	}
	/**
	 *Creates a new Node to be added to the queue. 
	 @param item The generic item to be added to the queue.
	 @param nodeValue The value that the .next pointer references.
	 */
	public Node createNode(Item item, Node nodeValue)
	{
		Node returnValue = new Node();
		returnValue.item = item;
		//returnValue.next = this.head;
		returnValue.next = nodeValue;
		arraySize++;
		return returnValue;
	}
	/** 
	 *Prints the queue without an iterator.
	 */
	public void printList()
	{
		StdOut.println("List Items:");
		for (Node x = head; x != null; x=x.next)
		{
			StdOut.print(x.item+" ");
		}
		StdOut.println("");
	}
	/** 
	 *Removes the first node in the queue.
	 */
	public Item removeFirst()
	{
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		Node temp = new Node();
		if(size() > 1)
			temp = this.head.next;
		this.head.next =  null;
		Item removedItem = this.head.item;
		this.head.item = null;
		this.head = temp;
		
		return removedItem;
	}
	/** 
	 *Removes the last node in the queue.
	 */
	public Item removeLast() 
	{
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		Node temp = new Node();
		if(size() > 1)
			temp = this.tail.previous;
		
		temp.next = null;
		Item returnItem = this.tail.item;
		this.tail.item = null;
		this.tail.next = null;
		
		return returnItem;
	}
	
	///P155 STEP 3 out of 5: Create Iterator Object when iterator method called automatically
	/** 
	 *The iterator method returns an object of the iterator class iteratorLoop
	 */
	public Iterator<Item> iterator()
	{
		return new iteratorLoop();
	}
	//STEP 4 out of 5: Define Iterator object Class, which contains hasNext() and next() methods (p139)
	/** 
	 *Iterator Class contains hasNext() and next() methods, which are used to instantiate an iterator object.
	 */
	public class iteratorLoop implements Iterator<Item>
	{
		//P139 Nexted class can access members of the enclosing class
		private Node current = head;
		
		public boolean hasNext()
		{
			return current != null;
		}

		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove()
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}
	//STEP 5 out of 5: Iterator implementation calls iterator object (same as Iterator<String> 
	//i = this.iterator(); while(i.hasNext()){String s = i.next(); StdOut.println(s);}
	//STEP 5a: When passing a collection object, you must tell it what type of object it contains.
	/**
	 *loop iterates through the queue and prints out all the items in the list. 
	 @param dq A reference to a deque object.
	 */
	public void loop(Deque<Item> dq)
	{
		StdOut.println("Print List With Iterator:");
		for(Item item: dq)
			StdOut.print(item+" ");
		StdOut.println("");
	}
	
	public static void main(String[] args)
	{
		String[] input = StdIn.readAllStrings();
		Deque dq = new Deque();
		for(int i = input.length-1; i >= 0; i--)
		{
			dq.addLast(input[i]);
		}
		
		for(int i = 0; i<input.length; i++)
		{
			dq.addFirst(input[i]);
		}
		StdOut.println("Removed Item at the Beginning = "+dq.removeFirst());
		StdOut.println("Added chars at the BEGINNING:");
		dq.printList();
		StdOut.println("Size = "+dq.size());
		StdOut.println("isEmpty = "+dq.isEmpty());
		StdOut.println("Removed Item at the End = "+dq.removeLast());
		dq.printList();
		StdOut.println("Call Iterator");
		dq.loop(dq);
	}
}//class