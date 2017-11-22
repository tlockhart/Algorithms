/**compile Multiple: javac -cp .;algs4_sts.jar; Transaction243.java PQUnorderedArray243.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.PQUnorderedArray243*/
 
 //READ THIS: http://docs.oracle.com/javase/tutorial/collections/interfaces/order.html
package sorts;

//Import classes from algs4
import exercises.*;
//Only a generic Key that inherits from the Comparable class can be used in this class
public class PQUnorderedArray243<Key extends Comparable<Key>>
{
	private boolean emptyElementFound = false;
	//One priority queue can have multiple transactions
	//Have to cast Comparable to a Key array type
	private Key[] transactions;
	private int n=0;
	private int capacity=0;
	
	//constructor
	public PQUnorderedArray243(int capacity)
	{
		transactions = (Key[]) new Comparable[capacity];
		this.capacity = capacity;
	}
	
	//We have the generic in the signature and the actual type (Transaction243) will be passed in.
	public void insertTransaction243s(Key[] v)
	{
		System.out.println("V length = "+v.length);
		for(int i=0; i<v.length; i++)//Print index i=1 - 5
		{
			transactions[i] = v[i];
		}
		//(1)NOTE:	 Must shuffle the list to make the Keys unordered after the values are inserted.
		StdRandom.shuffle(transactions);		
	}
	public Key maxKey()
	{
		System.out.println("MaxKey: Transaction243s Size = "+transactions.length);
		Key maxKey = transactions[0];//set max Key to initianl value

		for(int i = 1; i<transactions.length; i++)
		{
			if(transactions[i].compareTo(maxKey) > 0)
			{
				//(2) NOTE: We compare the whole array, and swap values when max is less.
				maxKey = transactions[i];
			}
		}
		return maxKey;
	}
	public Key minKey()
	{
		Key minKey = transactions[0];

		for(int i = 1; i<transactions.length; i++)
		{
			if(transactions[i].compareTo(minKey) < 0)
			{
				minKey = transactions[i];
			}
		}
		return minKey;
	}
	public void printKey()
	{
		try{
			//System.out.println("Transaction243s in PQUnorderedArray243:");
			for(int i=0; i< transactions.length; i++)//Print index i=1 - 5
			{
				System.out.println(((Transaction243)transactions[i]).getNumber());
			}
		}
		catch( ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Array out of Bounds "+e);
		}
	}
	public int size()
	{
		return this.n;
	}
	@SuppressWarnings("unchecked")
	public boolean resize(int max)
	{
		Key[] temp = (Key[])new Comparable[max];
		boolean value = false;
		try
		{
			StdOut.println("Max = "+max);
				for (int i=0; i<max; i++)
				{
					temp[i] = transactions[i];
				}
				transactions=temp;
				value = true;
		}
		catch(Exception e)
		{
			value = false;
		}
		return value;
	}//resize
	public Key removeMax(Key target)
	{
		Key returnValue = target;//Set return Value to transaction searched for.
		for (int i=0; i<transactions.length; i++)
		{
			if(transactions[i].compareTo(returnValue)== 0)
			{
				//(3) NOTE: Set the retun value to the value found
				returnValue = transactions[i];//decrement n counter
				//(4)NOTE: Set the found value to null after the return value been set.
				transactions[i] = null;
				//(5)NOTE: Swop index value of i position with last position. Because it is easier to
				//delete from the end of an array.
				exch(transactions, i, transactions.length-1);
				//(6) NOTE: Send the size of the new array after delete occurs
				resize(transactions.length-1);
				break;
			}

		}
		System.out.println("Final Results");
		this.printKey();
		return returnValue;
	}
	public void exch(Key[] a, int i, int j)
	{
		Key t = a[i]; a[i]=a[j]; a[j] = t;
	}
	//client
	public static void main(String[] args)
	{		
		int capacity = 5;//There is nothing in the 0 Index of a priority queue, so add one to total capacity.
		PQUnorderedArray243 pq = new PQUnorderedArray243(capacity);//PQUnorderedArray243 can take up to 10 transactions in the transaction array

		Transaction243 transaction1 = new Transaction243(111);
		Transaction243 transaction2 = new Transaction243(222);
		Transaction243 transaction3 = new Transaction243(333);
		Transaction243 transaction4 = new Transaction243(444);
		Transaction243 transaction5 = new Transaction243(555);
		
		Transaction243[] t2 = new Transaction243[capacity];  
		t2[pq.n++] = transaction1;//Store in index 1
		t2[pq.n++] = transaction2;//Store in index 2
		t2[pq.n++] = transaction3;//Store in index 3
		t2[pq.n++] = transaction4;//Store in index 4
		t2[pq.n++] = transaction5;//Store in index 5
		
		//Insert Transaction243s array into PriortyQueue (PQUnorderedArray243)
		pq.insertTransaction243s(t2);
		pq.printKey();
		System.out.println("The size = "+pq.size());
		//Return the maxKey as a casted Transaction243
		System.out.println("Max Key "+((Transaction243)pq.maxKey()).getNumber());
		System.out.println("Min Key "+((Transaction243)pq.minKey()).getNumber());	
		System.out.println("Removed Key "+((Transaction243)pq.removeMax(pq.maxKey())).getNumber());
	}
}