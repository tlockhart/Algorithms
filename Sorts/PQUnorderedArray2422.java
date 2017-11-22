/**compile Multiple: javac -cp .;algs4_sts.jar; Transaction2422.java PQUnorderedArray2422.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.PQUnorderedArray2422*/
 
 //READ THIS: http://docs.oracle.com/javase/tutorial/collections/interfaces/order.html
package sorts;

//Import classes from algs4
import exercises.*;
//Only a generic Key that inherits from the Comparable class can be used in this class
public class PQUnorderedArray2422<Key extends Comparable<Key>>
{
	private boolean emptyElementFound = false;
	//One priority queue can have multiple transactions
	//Have to cast Comparable to a Key array type
	private Key[] transactions;
	private static Transaction2422[] inputArray;
	private int n=0;
	private int capacity=0;
	private static int reheapifyExchCtrAfterDelete = 0;
	private static int reheapifyExchCtrAfterAdd = 0;
	
	//constructor
	public PQUnorderedArray2422(int capacity)
	{
		transactions = (Key[]) new Comparable[capacity];
		this.capacity = capacity;
	}
	public void reverseOrder(Key[] transactions, Key[] v, int i)
	{
		int transactionsReverseOrderIndex = transactions.length-i;
		transactions[transactionsReverseOrderIndex] = v[(i-1)];
	}
	//We have the generic in the signature and the actual type (Transaction2422) will be passed in.
	public void initialBulkInsert(Key[] v)
	{
		Insertion.sort(v);
		System.out.println("InsertTransaction() V List: Length = "+v.length);
		//Since v has 26 (0-25) elements and transactions has 27 (0-26), remap the elements in revereOrder.
		for(int i = transactions.length-1; i>0; i--)
		{
			int vChronologicalOrderIndex = v.length-i;
			System.out.print(((Transaction2422)v[vChronologicalOrderIndex]).getNumber()+" ");
			//Note: Store in Reverse Sort Order:
			/***********************************/
			reverseOrder(transactions, v, i);
		}
		System.out.println("");
		//(1)NOTE:	 Must shuffle the list to make the Keys unordered after the values are inserted.
		//StdRandom.shuffle(transactions);		
	}
	public void insertSingleTransaction(Key insertNode)
	{
		//Note1: resizeSingle the array transactions
		System.out.println("Transactions before swim:");
		for(int i=1; i < transactions.length; i++)//Print index i=1 - 5
			{
				System.out.print(((Transaction2422)transactions[i]).getNumber()+" ");
			}
			System.out.println("");
		resizeSingleInsert(transactions.length+1, insertNode);
		//Note2: Swim up to reheapify
		StdOut.println("Check length in InsertSingleTransaction = "+transactions.length);
		swim(transactions.length-1);
		System.out.println("InsertSingleTransaction() Transaction List: Length = "+transactions.length);
		/*for(int i >= 1; i<transactions.length; i++)
			
		{
		}*/
	}
	public Key maxKey()
	{
		//System.out.println("MaxKey: Transaction2422s Size = "+transactions.length);
		Key maxKey = transactions[1];//set max Key to initial value

		for(int i = 2; i<transactions.length; i++)
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
		Key minKey = transactions[1];

		for(int i = 2; i<transactions.length; i++)
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
		System.out.println("PrintKey() Transactions List: Length = "+transactions.length);
		try{
			//System.out.println("Transaction2422s in PQUnorderedArray2422:");
			for(int i=1; i < transactions.length; i++)//Print index i=1 - 5
			{
				System.out.print(((Transaction2422)transactions[i]).getNumber()+" ");
			}
			System.out.println("");
		}
		catch( ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Array out of Bounds "+e);
		}
	}
	public int size()
	{
		return this.transactions.length;
	}
	public boolean resizeSingleInsert(int max, Key insertNode)
	{
		Key[] temp = (Key[])new Comparable[max];
		boolean value = false;
		StdOut.println("resizeSingleInsert Length = "+max);
		//***Step1: Copy all but the last array item into the temp array.  The insertedNode, will be added in step2 below
		for (int i=0; i<max-1; i++)
			{
				temp[i] = transactions[i];
			}
		transactions=temp;
		//***Step2: Place the inserted Node in the last position of the transaction array
		transactions[transactions.length-1] = insertNode;	
		StdOut.println("Confirm Transactions Length = "+transactions.length);
		return value;
	}
	@SuppressWarnings("unchecked")
	public boolean resizeBulk(int max)
	{
		Key[] temp = (Key[])new Comparable[max];
		boolean value = false;
		try
		{
			StdOut.println("resizeBulkd Length = "+max);
				for (int i=0; i<max; i++)
				{
					temp[i] = transactions[i];
				}
				transactions=temp;
				value = true;
				StdOut.println("Confirm: resizeBulkd Length = "+transactions.length);
		}
		catch(Exception e)
		{
			value = false;
			StdOut.println("Exception occured "+e);
		}
		return value;
	}//resizeBulk
	public Key removeMax(Key target)
	{
		Key returnValue = target;//Set return Value to transaction searched for.
		try{
			for (int i=1; i<transactions.length; i++)
			{
				if(transactions[i].compareTo(target)== 0)
				{
					//(3) NOTE: Set the retun value to the value found
					returnValue = transactions[i];//decrement n counter
					//(4)NOTE: Set the found value to null after the return value been set.
					transactions[i] = null;
					//(4)NOTE: Swop index value of i position with last position. Because it is easier to
					//delete from the end of an array.
					exch(transactions, i, transactions.length-1);
					//(5) NOTE: Send the size of the new array after delete occurs
					resizeBulk(transactions.length-1);
					//(6) Print array after delete;
					System.out.println("Array after delete:");
					this.printKey();

					//(7) Call sink
					sink(1);// sink the largest item
					break;
				}
			}//for
		}//try
		catch(Exception e){
			System.out.println("Caught Exeception "+e);
		}
		return returnValue;
	}
	//P316 Sink
	private void sink(int k)
	{
		while(2*k <= transactions.length)
		{
			int j = 2*k;//child
			if(j < transactions.length && less(j, j+1)) j++;//compare children
			if(!less(k, j)) break;
			exch(transactions, k, j);
			k = j;
			reheapifyExchCtrAfterDelete++;
		}
	}
	//P316 Swim
	private void swim(int k)
	{
		while(k >1 && less(k/2, k))//compare parent to child
		{
			exch(transactions, k/2, k);//exchange child will parent
			k=k/2;
			reheapifyExchCtrAfterAdd++;
		}
	}
	/*public void exch(int k, int j)
	{
		Key temp;
		temp = transactions[k];
		transactions[k]= transactions[j];
		transactions[j]=temp;
	}*/
	
	public boolean less(int k, int j)
	{
		return transactions[k].compareTo(transactions[j]) < 0;
	}
	
	public void exch(Key[] a, int i, int j)
	{
		Key t = a[i]; a[i]=a[j]; a[j] = t;
	}
	//client
	@SuppressWarnings("unchecked")
	public void startPQ(String[] args)
	{		
		int capacity = args.length;
		int pqCapacity = capacity+1;//There is nothing in the 0 Index of a priority queue, so add one to total capacity.
				
		//int[] inputArray = new int[capacity];
		PQUnorderedArray2422.inputArray = new Transaction2422[capacity];//has 250(0-249) elements
		
        //Step1: Fill inputArray with 26 arg elements (0-25) from Doubling Test
		for(int i = 0; i<capacity; i++)
		{
			//PQUnorderedArray2422.inputArray[i] = new Transaction2422(Integer.parseInt(args[i]));//Input array only has (0-249) 250 elements
			PQUnorderedArray2422.inputArray[i] = new Transaction2422(args[i]);//Input array only has (0-249) 250 elements
		}		
		
		//Step2: To shift the array values to index 1-26, for a Heap, increase the capacity by 1 (26+1=27)
		PQUnorderedArray2422 pq = new PQUnorderedArray2422(pqCapacity);//PQUnorderedArray2422 can take up to 251 (0-250) transactions in the transaction array

		System.out.println("*********************START********************************");
		
		System.out.println("Print: Initial Array from strInput(v):");
		//Step1: Insert Transaction2422 array into PriortyQueue (PQUnorderedArray2422)
		pq.initialBulkInsert(PQUnorderedArray2422.inputArray);
		
		System.out.println("Print: Initial trasactional Array:");
		//Note1: Bulk Insert
		pq.printKey();
		
		//Return the maxKey as a casted Transaction2422
		System.out.println("Max Key = "+((Transaction2422)pq.maxKey()).getNumber());
		System.out.println("Min Key = "+((Transaction2422)pq.minKey()).getNumber());	
		System.out.println("Array Size: Before Remove = "+(pq.size()));
		System.out.println("Removed Key = "+((Transaction2422)pq.removeMax(pq.maxKey())).getNumber());
		System.out.println("Array Size: After Remove =  "+(pq.size()));
		//Note2 Remove
		System.out.println("Array after Delete-Sink reheapify:");
		pq.printKey();
		System.out.println("Reheapify Ctr after Delete = "+pq.reheapifyExchCtrAfterDelete);
		//Note3 Add
		pq.insertSingleTransaction(new Transaction2422("y"));
		System.out.println("Array Add-Swim reheapify:");
		pq.printKey();
		System.out.println("Reheapify Ctr after ADD = "+pq.reheapifyExchCtrAfterAdd);
		
	}
}