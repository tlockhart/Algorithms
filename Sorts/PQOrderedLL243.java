/**compile Multiple: javac -cp .;algs4_sts.jar; Transaction243.java PQOrderedLL243.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.PQOrderedLL243*/
  
package sorts;

import exercises.*;

//Only a generic Key that inherits from the Comparable class can be used in this class
public class PQOrderedLL243<Key extends Comparable<Key>>
{
	Node head = new Node();
	Node tail = new Node();
	Node insertTarget = new Node();
	Transaction243[] transactions;
	int capacity;
	
	public class Node
	{
		Key transaction;
		Node next;
	}
	
	//Constructor: Setup head and tail node
	public PQOrderedLL243(int capacity)
	{
		//transactions = new Transaction243[capacity];
		this.capacity = capacity;
		head.transaction = null;
		head.next = tail;
		tail.transaction = null;
		tail.next = null;
	}
	
	public void insertArray(Key[] v)
	{
		for(int i=0; i < v.length; i++)
		{
			if(isHeadEmpty(i, v.length))
			{
				updateHeadNode(v[i]);
			}//if
			else if((i < v.length-1)&&(v.length>2))
			{
				insertNode(v[i], i , v.length);
			}
			else if(i==v.length-1)
			{
				//insertLastNode(v[i]);
				insertNode(v[i], i , v.length);
			}
		}//for
	}//insertArray
	
	public void updateHeadNode(Key key)
	{
		head.transaction = key;
		head.next = tail;
		insertTarget = head;
	}
	
	public void insertNode(Key key, int i, int length)
	{
		Node newNode = new Node();
		newNode.transaction = key;
		if(i == 1)
		{
			insertTarget.next = newNode;
			newNode.next = tail;
			insertTarget = newNode;
		}
		else if((i>1)&&(i<length-1))
		{
			insertTarget.next = newNode;
			newNode.next = tail;
			insertTarget = newNode;
		}
		else if(i==length-1)
		{
			tail.transaction = key;
			tail.next = null;
			insertTarget = tail;
			
		}
	}
	public boolean isHeadEmpty(int i, int length)
	{ 
		boolean value = false;
		if((head.transaction==null)&&(i==0)&&(length>0))
		{
			value = true;
		}
		return value;
	}//isHeadEmpty
	
	public void printPQ()
	{
		for(Node x=head; x!=null; x=x.next)
		{
			StdOut.println("The Item is = "+((Transaction243)x.transaction).getNumber());
		}
	}//printPQ
	public Key getMaxPQ()
	{		
			//(1)NOTE: maxTransaction243 must be set outside of the loop, so it will not continue to be reset inside.
			Key maxTransaction243 = head.transaction;
			for(Node x = head; x!=null && x.next!=null; /*&& x.next!=null && x.transaction!=null && x.next.transaction!=null && capacityCtr<capacity-3;*/ x=x.next)
			{
				if((maxTransaction243).compareTo((x.next).transaction)<0) 
					maxTransaction243 = x.next.transaction;
			}
			//Problem  x.transaction is null, because it is past the bounds.
			StdOut.println("Max Account = "+((Transaction243)(maxTransaction243)).getNumber());
			//return (((Transaction243)maxTransaction243).getNumber());
			return maxTransaction243;
	}//printPQ
	
	public void exch(Key[] a, int i, int j)
	{
		Key t = a[i]; a[i]=a[j]; a[j] = t;
	}
	
	public void deleteMax(Key maxTransaction243)
	{
		int ctr =0;
		Node previous = head;
		for(Node x=head; x!=null; x=x.next)
		{
			if((ctr==1)||(ctr==0))
			{
				previous = head;
			}
			else if(ctr>1)
			{
				previous = previous.next;
			}
			
			if(x.transaction.compareTo(maxTransaction243)==0)
			{
				if(ctr==0)
				{
					head = head.next;
					previous.next = null;
				}
				else if((ctr>0)&&(x!=tail))
				{
					previous.next = x.next;
					x.transaction = null;
					x.next = null;
				}
				else if(x == tail)
				{
					previous.next = null;
					tail.transaction = null;
					tail.next = null;
					tail=previous;
				}//else
			}//
		ctr++;
		}//for
	}//deleteMax
	
	public static void main(String[] args)
	{
		Transaction243 transaction1 = new Transaction243(111);
		Transaction243 transaction2 = new Transaction243(222);
		Transaction243 transaction3 = new Transaction243(333);
		Transaction243 transaction4 = new Transaction243(444);
		Transaction243 transaction5 = new Transaction243(555);
		
		Transaction243[] t1 = {transaction1, transaction2, transaction3, transaction4, transaction5};
		PQOrderedLL243 pq = new PQOrderedLL243(5);
		//(1) Sort array prior to insert
		Insertion.sort(t1);
		pq.insertArray(t1);
		pq.printPQ();
		pq.getMaxPQ();
		pq.deleteMax(pq.getMaxPQ());
		pq.printPQ();
	}
}