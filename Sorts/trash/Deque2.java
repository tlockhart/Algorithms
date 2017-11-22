//javac -cp .;algs4_sts.jar; Deque.java
//java -cp ../;.;algs4_sts.jar; sorts.Deque < distinct.txt
package sorts;

import java.util.Iterator;
import exercises.*;

public class Deque<Item> implements Iterable<Item>
{
	private int arraySize =10;
	private Item[] input = (Item[]) new Comparable[arraySize];
	private int totalItems=0;
	//private int size;
	
	public boolean isEmpty() 
	{
		boolean returnValue=false;
		if(totalItems==0)
		{
			returnValue = true;
		}
		return returnValue;
	}
	
	public int size()
	{
		return this.totalItems;
	}
	public Deque(){
		//this.input = a;
	}
	public boolean hasNext()
	{
		return totalItems > 0;
	}

	public Item next(){
		return input[--this.totalItems];
	}

	public void remove()
	{
	}
	
	//Add to first and move them into a queue
	public void addFirst(Item item)
	{
		if(item == null)
		{
			throw new java.lang.IllegalArgumentException();
		}

		int i = 0;
		
		//{
			if(this.input[i]==null)
			{
				this.input[i] = item;
				if(this.totalItems == this.arraySize)
				{
					resizeArray(2*this.arraySize);
					//this.input[0] = item;
				}
			}
			if((this.input[i]!=null)&&(this.input[this.totalItems]== null)&&(this.totalItems<arraySize))
			{
				shiftItemsLeft(item, this.totalItems, 0);
			}
			this.totalItems++;
		//}
		
	}
	public void addLast(Item item)
	{
		if(item == null)
		{
			throw new java.lang.IllegalArgumentException();
		}
		
		int i = this.totalItems;
		if(i == this.arraySize)
		{
			resizeArray(2*this.arraySize);
			//this.input[i] = item;
		}
		if((this.input[i]==null)&&(i<this.arraySize))
		{
			this.input[i] = item;
		}
		this.totalItems++;
	}
	public void shiftItemsLeft(Item item, int newIndex, int insertIndex)
	{
		StdOut.println("In Shift");
		Item[] temp = (Item[])new Comparable[arraySize];
		for(int i = newIndex; i>0; i--)
		{
			//Item[] temp = (Item[]) new Comparable[arraySize];
			temp[i] = this.input[i-1];
			StdOut.println("Value "+i+" = "+temp[i]+" Temp = "+item);
		}		
		temp[insertIndex] = item;
		this.input = temp;
	}
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
	
	/*public void addLast(Item item)
	{
	}
	
	public Item removeFirst()
	{
	}
	
	public Item removeLast()  
	{
	}*/
	
	public void print(Item[] input)
	{
		StdOut.println("The results are:");
		for(int i = 0; i < input.length; i++)
		{
			StdOut.print(input[i]+" ");
		}
		StdOut.println("");
	}
	
	public static void main(String[] args)
	{
		String[] input = StdIn.readAllStrings();
		Deque dq = new Deque();
		//this.input
		//dq.print(dq.input);
		
		for(int i = 0; i<input.length; i++)
		{
			dq.addFirst(input[i]);
		}
		StdOut.println("Added chars at the BEGINNING:");
		dq.print(dq.input);
		String input2 = "S";
		String input3 = null;
		dq.addLast(input3);
		StdOut.println("Added char at the END:");
		dq.print(dq.input);
		StdOut.println("Added chars at the END:");
		for(int i = 0; i<input.length; i++)
		{
			dq.addLast(input[i]);
		}
		dq.print(dq.input);
	}
	
	public Iterator<Item> iterator()
	{
		return (Iterator) new Deque();
	}
	
	
}
/*private class ReverseArrayIterator implements Iterator<Item>
	{
		private int i = 10;
		
		public boolean hasNext(){
			return i > 0;
		}
		
		public Item next(){
			return input[--i];
		}
		
		public void remove()
		{
		}
	}//Reverse*/