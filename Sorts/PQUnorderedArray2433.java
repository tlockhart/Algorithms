/**compile Multiple: javac -cp .;algs4_sts.jar; Transaction2433.java PQUnorderedArray2433.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.PQUnorderedArray2433*/
 
 //READ THIS: http://docs.oracle.com/javase/tutorial/collections/interfaces/order.html
package sorts;

//Import classes from algs4
import exercises.*;
//Only a generic Key that inherits from the Comparable class can be used in this class
public class PQUnorderedArray2433<Key extends Comparable<Key>>
{
	private boolean emptyElementFound = false;
	//One priority queue can have multiple transactions
	//Have to cast Comparable to a Key array type
	private Key[] transactions;// contains actual values of transactions
	private Key[] inputArray;
	private static int[] index;// index of transactions Key values
	private static int[] inverseIndex;
	private final int indexArrayStart = 0;
	private final int inverseIndexArrayStart = 1;
	private final int transArrayStart = 1;
	private int n=0;//indexArray size
	private int initialTransCapacity=0;
	private int initialIndexCapacity=0;
	private static int reheapifyExchCtrAfterDelete = 0;
	private static int reheapifyExchCtrAfterAdd = 0;
	
	
	//constructor
	public PQUnorderedArray2433()
	{		
	}

	public void insert(int priority, int index)// 1, index[9]=
	{
		//**10/24/2017: preint index values
		//this.printKey(); //**10/24/2017: preint index values
		/*StdOut.println("insert: Index length = "+this.index.length);
		StdOut.println("insert: Transactions length = "+this.transactions.length);
		StdOut.println("insert: Priority = "+priority);
		StdOut.println("insert: IndexArrayIndices = "+index);*/
		this.index[index] = priority;//Starts filling indexArray at index 0 (prority = 1-10)
		this.transactions[priority] = this.inputArray[index];				
	}//insert
	public void insertSingleTransaction(Key insertNode)
	{
		//Note1: resizeSingle the array transactions
			StdOut.println("INSERTING A NEW VALUE:");
			//Step 3-A: Increase the array size to add 1;
			this.increaseSize(++this.n);//index size increased
			//Step 3-D: Fill the indexArray with priorities and transactionArray with transactions, Fills arrays one by one until last index reached
			//pq.insert(priority, indexArrayElement);
			this.insertSingleKeyInNewTransaction(transactions.length, insertNode);
			//increase inverseIndex
			this.createInverseIndex(transactions.length);//inverseIndex size is the same as transaction.
			//Set 3-E: Swim after insertion:
			//10/30/2016: this.swim(this.index[indexArrayElement]);
			this.swim(this.index[this.index.length-1]);//indexArray index.
		    StdOut.println("InsertSingleTransaction() Transaction List: Length = "+transactions.length);
	}
	public boolean insertSingleKeyInNewTransaction(int max, Key insertNode)
	{
		int transLength = max;
		Key[] tempTrans = (Key[])new Comparable[transLength];
		int[] tempIndex = new int[transLength-1];
		boolean value = false;
		StdOut.println("insertSingleKeyInNewTransaction Length = "+max);
		//***Step1: Copy all but the last array item into the temp array.  The insertedNode, will be added in step2 below
		for (int i=transArrayStart; i<transLength; i++)
			{
				tempTrans[i] = transactions[i];
				tempIndex[i-1] = index[i-1];
			}
		transactions=tempTrans;
		index = tempIndex;
		//***Step2: Place the inserted Node in the last position of the transaction array
		transactions[transactions.length-1] = insertNode;	
		index[index.length-1] = index.length;
		
		StdOut.println("Confirm Transactions Length = "+transactions.length);
		StdOut.println("Confirm Index Length = "+index.length);
		return value;
	}
	public void fillIndexPtrArray(int index)//0-9)
	{
		this.index[index] = -1;
	}
	@SuppressWarnings("unchecked")
	public void increaseSize(int max)
	{
		Key[] transTemp = (Key[])new Comparable[max+1];
		int[] indexTemp = new int[max];
		for (int i = this.transArrayStart; i < max; i++)
		{
			transTemp[i] = this.transactions[i];
			indexTemp[i-1] = this.index[i-1];
		}
		this.transactions = transTemp;
		this.index = indexTemp;
	}
	public void reduceSize(int max)
	{
		int transLength = max+1;//max is indexArray Length, need transLength
		Key[] transTemp = (Key[])new Comparable[transLength];
		int[] indexTemp = new int[max];
		for (int i = this.transArrayStart; i < transLength; i++)
		{
			transTemp[i] = this.transactions[i];
			indexTemp[i-1] = this.index[i-1];
		}
		this.transactions = transTemp;
		this.index = indexTemp;
	}
	public String keyOf(int index)
	{
		int priority = index;
		String returnValue = "No Value Found";
		if(this.contains(priority))
		{
			StdOut.println("Index Value = "+this.index[this.inverseIndex[priority]]);
			Transaction2433 targetTransaction = (Transaction2433)this.transactions[this.index[this.inverseIndex[priority]]];
			returnValue = targetTransaction.getJob();
		}
		return returnValue;
	}
	public String changeKey(int index, Key key)
	{
		int priority = index;
		String returnValue = "No Value Found";
		if(contains(priority))
		{
			StdOut.println("Priority "+index+" was found in PQ");
			this.transactions[this.index[this.inverseIndex[priority]]] = key;//(Key) new Transaction2433(key);
			Transaction2433 targetTransaction = (Transaction2433)this.transactions[this.index[this.inverseIndex[priority]]];
			
			//(6)Run sink and swim because we don't know if parent is larger than children.
			//11/04/2017:dualReheapify(priority);
			//11/04/2017:
			floydReheap(priority);
			
			//11/01: sink(1);
			returnValue = targetTransaction.getJob();
		}
		else
			StdOut.println("Priority "+index+" was NOT found in PQ");
			
	   return returnValue;
	}
	public boolean contains(int index)
	{
		boolean value;
		if((index < inverseIndex.length)&&(inverseIndex.length != 0)&&(index > 0))
			value = true;
		else
			value = false;
		return value;
	}
	public void createInverseIndex(int capacity)
	{
		int length = capacity;
		int[] tempIndex = new int[length];
		/*StdOut.println("Reverse Index:");*/
			for(int i=1; i<length; i++)
			{
				tempIndex[i] = i-1;
			}
			this.inverseIndex = tempIndex;
	}
	public Key maxKey()
	{
		Transaction2433 dummyTransaction = new Transaction2433("d");
		Key maxKey = (Key) dummyTransaction;// = new Key[this.n];
			if(this.n <= 1)
			{
				//throw new NullPointerException();
			}
			else if(this.n == 2)
			{
			/*********1/24/2017************************/
			maxKey = transactions[1];//set max Key to the value in the first position (transactions[1])*/
			/*********1/24/2017************************/
			}
			else{// if(this.n > 1){
				maxKey = transactions[1];//set max Key to initial value
				for(int i = 2; i<transactions.length; i++)
				{
					if(transactions[i].compareTo(maxKey) > 0)
					{
						//(2) NOTE: We compare the whole array, and swap values when max is less.
						maxKey = transactions[i];
					}
				}///for
			}//else
		return maxKey;
	}
	public Key minKey()
	{
		Transaction2433 dummyTransaction = new Transaction2433("d");
		Key minKey = (Key) dummyTransaction;//transactions[1];
		if(this.n <= 1)
		{
			//throw new NullPointerException();
		}
		else if(this.n == 2)
		{
			minKey = transactions[1];//set max Key to the value in the first position (transactions[1])*/
		}
		else
		{// if(this.n > 1){
			minKey = transactions[1];//set max Key to initial value
			for(int i = 2; i<transactions.length; i++)
			{
				if(transactions[i].compareTo(minKey) < 0)
				{
					minKey = transactions[i];
				}
			}//for
		}//else
		return minKey;
	}
	public void printKey()
	{
		try{
			//printIndex();
			StdOut.println("PrintKey() TRANSACTIONS ARRAY: Length = "+this.transactions.length);
			for(int j=this.transArrayStart; j < this.transactions.length; j++)//Print index i=1 - 5
			{
				StdOut.print(((Transaction2433)this.transactions[j]).getJob()+" ");
			}//for
			StdOut.println("");
			StdOut.println("PrintKey() INDEX ARRAY: Length = "+this.index.length);
			for(int j=this.indexArrayStart; j < this.index.length; j++)//Print index i=1 - 5
			{
				StdOut.print(index[j]+" ");
			}//for
			StdOut.println("");
			StdOut.println("PrintKey() INVERSEINDEX ARRAY: Length = "+this.inverseIndex.length);
			for(int j = this.inverseIndexArrayStart; j < this.inverseIndex.length; j++)//Print index i=1 - 5
			{
				StdOut.print(inverseIndex[j]+" ");
			}//for
			StdOut.println("");
		}
		catch( Exception e)
		{
			StdOut.println("Array out of Bounds "+e);
		}
	}
	public int size()
	{
		//return this.transactions.length;
		return this.n;
	}
	
	public Key delMax()
	{
		Key returnValue = this.maxKey();//Set return Value to transaction searched for.
		try{
			for (int i=1; i<transactions.length; i++)
			{
				if(transactions[i].compareTo(returnValue)== 0)
				{
					//(1) NOTE: Set the retun value to the value found
					returnValue = transactions[i];//decrement n counter
					//(4)NOTE: Set the found value to null after the return value been set.
					this.transactions[i] = null;
					//(2)NOTE: Swop index value of i position with last position. Because it is easier to
					//delete from the end of an array.
					
					//(3) NOTE: exhange max at index 1(Max Value) with end.
					this.exch(i, transactions.length-1);
					
					//(4) NOTE: Send the size of the new array after delete occurs
					this.reduceSize(--this.n);//the index length.
			
					//(5) Call sink
					sink(1);// sink the largest item
					
					//(6) NOTE: Adjust inversIindex Size
					this.createInverseIndex(transactions.length);//inverseIndex has the same size as transactions.
					break;
				}//if
			}//for
		}//try
		catch(Exception e)
		{
			StdOut.println("Caught Exeception "+e);
		}
		return returnValue;
	}
	public Key delMin()
	{
		Key target = this.minKey();
		Key returnValue = target;//Set return Value to transaction searched for.
		try{
			for (int i=1; i<transactions.length; i++)
			{
				if(transactions[i].compareTo(target)== 0)
				{
					//(3) NOTE: Set the retun value to the value found
					returnValue = transactions[i];//decrement n counter
					
					//(4)NOTE: Set the found value to null after the return value been set.
					this.transactions[i] = null;
					
					//(5)NOTE: Swop index value of i position with last position. Because it is easier to remove elements at the end of an array
					this.exch(i, transactions.length-1);//exhange top with end and delete, keep highest on top always
					
					//(6) NOTE: Send the size of the new array after delete occurs
					this.reduceSize(--this.n);//the index length.
					
					//(7)Run sink and swim because we don't know if parent is larger than children.
					//11/04/2017: dualReheapify(i);
					//11/04/2017:
					floydReheap(i);
			
					//(7) Call sink
					//11/07: sink(1);// sink the largest item
					
					//(8) NOTE: Adjust inversIindex Size
					this.createInverseIndex(transactions.length);//inverseIndex has the same size as transactions
					break;
				}
			}//for
		}//try
		catch(Exception e){
			StdOut.println("Caught Exeception "+e);
		}
		return returnValue;
	}
	public void dualReheapify(int i)
	{
		//(7) if delete or change is not at end or begining you must call both.
		if((i>1)&&(i<=transactions.length-1))
		{
			//call swim(i) when nodes's key becomes larger than its parents, then exchange the node until the parent is larger or largest value reached.
			swim(i);//11/1
		}
		if((i>=1)&&(i<transactions.length-1))
		{
			//call sink when a parent node becomes bigger than its children keys.  Exchange the parent 
			//with the larger if its children until we reach a child that is smaller or we reach the bottom.
			sink(i);//11/01
		}
	}
	//P323 Describes the Floyd Reheap Method:The scans start halfway back through the array because
	//we can skip the subheaps of size 1.  The scan ends at position 1, when we finish building the
	//heap with a call to sink();
	//P374 Most items reinserted into the heap during sortdown go all the way to the bottom.
	//We can save time by avoiding the check for whether the item has reached its position, 
	//by simply promoting the larger of the two children, with the Sink method until the bottom
	//is reached, then moving back up the heap to the proper position.  This cuts the number of 
	//compares by a factor of 2 asymptotically.
	public void floydReheap(int i)
	{
		for(int k = this.n/2; k >= 1; k--)
		{
			sink(k);
		}
	}
	public void delete(int index)
	{
		int priority = index;
		if(contains(priority))
		{
			StdOut.println("Deleting Jobs Now:");
			Key targetTransaction = this.transactions[this.index[this.inverseIndex[priority]]];
			Key returnValue = targetTransaction;//Set return Value to transaction searched for.
			try{
				for (int i=1; i<transactions.length; i++)
				{
					if(transactions[i].compareTo(targetTransaction)== 0)
					{
						//(3) NOTE: Set the retun value to the value found
						returnValue = transactions[i];//decrement n counter
						
						//(4)NOTE: Set the found value to null after the return value been set.
						this.transactions[i] = null;
						//(4)NOTE: Swop index value of i position with last position. Because it is easier to
						
						this.exch(i, transactions.length-1);//exhange top with end and delete, keep highest on top always
						
						//(5) NOTE: Send the size of the new array after delete occurs
						this.reduceSize(--this.n);//the index length.
						
						//(6)Run sink and swim because we don't know if parent is larger than children.
						//11/04/2017:dualReheapify(i);
						//11.04.2017
						floydReheap(i);
						
						//11/01 sink(1);// sink the largest item
						//adjust inversIindex Size
						this.createInverseIndex(transactions.length);//inverseIndex has the same size as transactions.

						break;
					}
				}//for
			}//try
			catch(Exception e)
			{
				StdOut.println("Caught Exeception "+e);
			}//catch
		}//if
		//return returnValue;
	}
	
	//P316 Sink
	private void sink(int k)
	{
		//this.n = transactions.length;
		while(2*k <= this.n)//while child node less than or equal to length of array
		{
			int j = 2*k;//child
			if(j < this.n && less(j, j+1)) j++;//compare children
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
			reheapifyExchCtrAfterDelete++;
		}
	}
	//HeapSort P324
	private void sort()
	{
		int N = this.n;//Make the Sort in place: Use a copy of the last index in transactions array, so we do not change the size of the array.
		//P323 and 374: R.W. Floyd Reheapify method
		for (int k = N/2; k >= 1; k--)
		{
			heapSortSink(k, N);
		}
		//Sort array by removing the Max element from the heap, exchanging it with the end element & decreasing N.
		//The Max element in the list is now at the end of the list, and since we decremented N in the exchange, 
		//it won't be compared again.  Next call the sink method for range 1 and the next to the last index(N), 
		//then repeat the exchange with the last element again, continue while N > 1.
		while(N > 1)
		{
			exch(1, N--);
			StdOut.println("N = "+N);
			heapSortSink(1, N);
		}
	}
	//P324 Switch the value in index 1 with the value in n-1, Target index k = 1)
	private void heapSortSink(int k, int length)
	{
		//int k=1;//Target Node: Parent
		//this.n = transactions.length;
		while(2*k <= length)
		{
			int j = 2*k;//child
			if(j < length && less(j, j+1)) j++;//compare children
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
			reheapifyExchCtrAfterDelete++;	
		}
		
	}
	
	//P316 Swim
	private void swim(int k)
	{
		//10/29/2017 K is the value in the index: index[a]=k, which points to the transaction index.
		//if(k>0)//k is the index of the indexArray, if >0, it has at least 2 elements (because the indexes start at 0 (0-1))
		while(k >1 && less(k/2, k))//compare parent to child
		{
			exch(k/2, k);//exchange child will parent
			k=k/2;
			reheapifyExchCtrAfterAdd++;
		}
	}
	//Arguments are the parent and child repectively
	public void exch(int kDiv2, int k)
	{
		Key t = this.transactions[kDiv2]; this.transactions[kDiv2]=this.transactions[k]; this.transactions[k] = t;
		int indexT = k-1/2; kDiv2 = k-1; k = indexT;//Convert k back to indexArray values
	}
	
	public boolean less(int kDiv2, int k)
	{
		boolean value = false;
		if((this.transactions[kDiv2] == null)||(this.transactions[k] == null))
		{
			value = false;
		}
		else if((this.transactions[kDiv2].compareTo(this.transactions[k])) < 0){
			value = true;
		}
		return value;
	}
	
	public boolean isEmpty()
	{
		boolean value = false;
		if( this.n == 0)
		{
			value = true;
		}
		return value;
	}
	public void loadAndReverseSortInputArray(String[] args, int initialIndexCapacity)
	{
		//Step4: Set index, inverse, and transaction array size, by calling contructor
		this.inputArray = (Key[]) new Transaction2433[initialIndexCapacity];//has 250(0-249) elements
		
		//Step3: Fill inputArray with 26 arg elements (0-25) from Doubling Test
		StdOut.println("Print Input Array:");
		for(int i = this.indexArrayStart; i<initialIndexCapacity; i++)//0-9
		{
			this.inputArray[i] = (Key) new Transaction2433(args[i]);//Input array only has (0-249) 250 elements
			StdOut.print(((Transaction2433)this.inputArray[i]).getJob()+" ");
		}
		
		StdOut.println("");
	}
	//client
	@SuppressWarnings("unchecked")
	public void startPQ(String[] args)
	{		
		
		//Step1: Instantiate Class object, set initial array sizes
		PQUnorderedArray2433 pq = new PQUnorderedArray2433();
		int argsLength = args.length;
		
		//Step2: Set the Initial length
		initialIndexCapacity = args.length; //take capacity from input
		StdOut.println("IndexCapacity = "+initialIndexCapacity);
		
		//Step3: Add 1 to initial length transCapacity: //To shift the array values to index 1-26, for a Heap, increase the capacity by 1 (26+1=27)
		initialTransCapacity = initialIndexCapacity+1;//There is nothing in the 0 Index of a priority queue, so add one to total capacity.
		
		//Step4: Load and Reverse Sort InputArray
		pq.loadAndReverseSortInputArray(args, initialIndexCapacity);
		
		StdOut.println("*********************START********************************");
			
		//Step5: Insert inputArray transaction into transactions Array in Reverse order, so Highest priority is in position 1, Set priority arrays.
		if(pq.isEmpty())
		{
			//initializeArrays(initialTransCapacity);
			StdOut.println("The priority = "+pq.n);
			//pq.n=11: 11-1=10 (9-1)
				
			for(int priority = pq.transArrayStart; priority < initialTransCapacity; priority++)//pq.transArrayStart= 1; initialTransCapacity =11
			{
				//Step 3-A: Increase the array size to add 1;
				pq.increaseSize(++pq.n);
			
				//Step 3-B: Get the index values of the indexArray (0-9), one off
				int indexArrayElement = priority-1;//(0-9=total)
				
				//Step 3-C: Fill the indexArray with -1
				pq.fillIndexPtrArray(indexArrayElement);//
				
				//Step 3-D: Fill the indexArray with priorities and transactionArray with transactions, Fills arrays one by one until last index reached
				pq.insert(priority, indexArrayElement);
				
				//Set 3-E: Swim after insertion:
				pq.swim(pq.index[indexArrayElement]);
			}//for
		}//if
		//Step 4: Set the inverseIndex array to be the reverse of indexArray
		pq.createInverseIndex(initialTransCapacity);	
		
		//Step 9: Print Arrays
		pq.printKey();	

        //Return the maxKey as a casted Transaction2433
		StdOut.println("Max Key = "+((Transaction2433)pq.maxKey()).getJob());
		StdOut.println("Min Key = "+((Transaction2433)pq.minKey()).getJob());	
		StdOut.println("Index Array Size: Before Remove = "+(pq.size()));
		StdOut.println("Removing Max Key = "+((Transaction2433)pq.delMax()).getJob());//((Transaction2433)pq.delMax(pq.maxKey())).getJob())

		//Note2 Remove
		StdOut.println("Max Key Deleted-Sink reheapify Complete: Length = "+pq.size());
		pq.printKey();
		StdOut.println("Reheapify Ctr after Delete = "+pq.reheapifyExchCtrAfterDelete);
		//Note3 Add
		pq.insertSingleTransaction(new Transaction2433("y"));

		pq.printKey();
		StdOut.println("Removing Min Key = "+((Transaction2433)pq.delMin()).getJob());//((Transaction2433)pq.delMax(pq.maxKey())).getJob())
		StdOut.println("Min Key Deleted-Sink reheapify Complete: Length = "+pq.size());
		
		pq.printKey();
		StdOut.println("Reheapify Ctr after ADD = "+pq.reheapifyExchCtrAfterAdd);
		
		//CHECK IF AN INDEX IS IN THE Priority Heap
		int checkPriority = 9;
		StdOut.println("InverseIndex length = "+inverseIndex.length);
		if(contains(checkPriority))
		{
			StdOut.println("Priority "+checkPriority+" was found in PQ");
			StdOut.println("The Key found = "+pq.keyOf(checkPriority));
		}
		else
			StdOut.println("Priority "+checkPriority+" was NOT found in PQ");
		
		Transaction2433 transaction1 = new Transaction2433("o");
		
		StdOut.println(pq.keyOf(checkPriority)+" Key Changed To: "+pq.changeKey(checkPriority, transaction1));
		pq.printKey();
		pq.delete(checkPriority);
		pq.printKey();
		StdOut.println("HeapSorting the Array");
		pq.sort();
		pq.printKey();
	}//startpq
}//class