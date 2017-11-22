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
	//private static Transaction2433[] inputArray;
	private final int indexArrayStart = 0;
	private final int inverseIndexArrayStart = 1;
	private final int transArrayStart = 1;
	private int n=0;
	private int initialTransCapacity=0;
	private int initialIndexCapacity=0;
	//private int capacity=26;
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
		
		/*StdOut.println("insert: "+"IndexArrayIndices = "+index+" IndexArray Value = "+this.index[index]);*/
		this.transactions[priority] = this.inputArray[index];				
	}//insert
	public void insertSingleTransaction(Key insertNode)
	{
		//Note1: resizeSingle the array transactions
		/*System.out.println("Transactions before swim:");
		for(int i=1; i < transactions.length; i++)//Print index i=1 - 5
			{
				System.out.print(((Transaction2422)transactions[i]).getNumber()+" ");
			}
			System.out.println("");
		resizeSingleInsert(transactions.length+1, insertNode);
		//Note2: Swim up to reheapify
		StdOut.println("Check length in InsertSingleTransaction = "+transactions.length);
		swim(transactions.length-1);
		System.out.println("InsertSingleTransaction() Transaction List: Length = "+transactions.length);*/
		//int priority = this.index[this.index.length;
		//for(int priority = pq.transArrayStart; priority < initialTransCapacity; priority++)//pq.transArrayStart= 1; initialTransCapacity =11
		//{
			StdOut.println("VALUE Y INSERTED:");
			//Step 3-A: Increase the array size to add 1;
			this.increaseSize(++this.n);
			//Step 3-D: Fill the indexArray with priorities and transactionArray with transactions, Fills arrays one by one until last index reached
			//pq.insert(priority, indexArrayElement);
			this.resizeSingleInsert(transactions.length, insertNode);	
			//Set 3-E: Swim after insertion:
			//10/30/2016: this.swim(this.index[indexArrayElement]);
			this.swim(this.index[this.index.length-1]);//indexArray index.
		    System.out.println("InsertSingleTransaction() Transaction List: Length = "+transactions.length);
		//}
	}
	public boolean resizeSingleInsert(int max, Key insertNode)
	{
		int transLength = max;
		Key[] tempTrans = (Key[])new Comparable[transLength];
		int[] tempIndex = new int[transLength-1];
		boolean value = false;
		StdOut.println("resizeSingleInsert Length = "+max);
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
			/*StdOut.println("FillIndex = "+index);
			StdOut.println("FillIndex: Index length = "+this.index.length);
			StdOut.println("FillIndex: Transactions length = "+this.transactions.length);*/
			this.index[index] = -1;
	}
	@SuppressWarnings("unchecked")
	public void increaseSize(int max)
	{
		Key[] transTemp = (Key[])new Comparable[max+1];
		int[] indexTemp = new int[max];
		/*this.transactions = transTemp;
		this.index = indexTemp;
		StdOut.println("FillIndex: Index length = "+indexTemp.length);
		StdOut.println("resize: Index length = "+this.index.length);
		StdOut.println("resize: Transactions length = "+this.transactions.length);
		System.out.println("resize: Max number of elements in index = "+max);*/
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
		/*this.transactions = transTemp;
		this.index = indexTemp;
		StdOut.println("FillIndex: Index length = "+indexTemp.length);
		StdOut.println("resize: Index length = "+this.index.length);
		StdOut.println("resize: Transactions length = "+this.transactions.length);
		System.out.println("resize: Max number of elements in index = "+max);*/
		for (int i = this.transArrayStart; i < transLength; i++)
		{
			transTemp[i] = this.transactions[i];
			indexTemp[i-1] = this.index[i-1];
		}
		this.transactions = transTemp;
		this.index = indexTemp;
	}
	public boolean contains(int index)
	{
		boolean value;
		if(this.index[index-1] != -1)
			value = true;
		else 
			value = false;
		
		return value;
	}
	public void createInverseIndex(int capacity)
	{
		int length = capacity;
		int[] tempIndex = new int[length];
		/*System.out.println("Reverse Index:");*/
			for(int i=1; i<length; i++)
			{
				tempIndex[i] = i-1;
			}
			this.inverseIndex = tempIndex;
	}
	public Key maxKey()
	{
		//this.n = transactions.length;
		//System.out.println("MaxKey: Transaction2433s Size = "+transactions.length);
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
				for(int i = 2; i<this.n; i++)
				{
					if(transactions[i].compareTo(maxKey) > 0)
					{
						//(2) NOTE: We compare the whole array, and swap values when max is less.
						maxKey = transactions[i];
					}
				}///for
			}
		return maxKey;
	}
	public Key minKey()
	{
		//this.n = transactions.length;
		
		Transaction2433 dummyTransaction = new Transaction2433("d");
		Key minKey = (Key) dummyTransaction;//transactions[1];

		for(int i = 2; i<this.n; i++)
		{
			if(transactions[i].compareTo(minKey) < 0)
			{
				minKey = transactions[i];
			}
		}//
		return minKey;
	}
	/*public void printIndex()
	{
		System.out.println("PrintIndex() Index ARRAY: Length = "+(this.index.length));
		for(int priority = this.indexArrayStart; priority < this.index.length; priority++)//(transactions n = 11, index range:9-0)
		{
			System.out.print(this.index[priority]+" ");//System.out.println("Index value =  "+index[priority]+" inverseIndex value = "+inverseIndex[priority]+" Indexes = "+priority);
		}
		System.out.println("");
		//ADD INVERSE PRIORITY LATER 
		System.out.println("PrintIndex() Inverse ARRAY: Length = "+(this.n-1));
		
		for(int priority = indexArrayStart; priority < this.n-1; priority++)//Print index i=1 - 5
		{
			if(inverseIndex[priority] != -1)
				System.out.print(inverseIndex[priority]+" ");
		}*///for
		//System.out.println("");
	/*}*/
	public void printKey()
	{
		try{
			//printIndex();
				System.out.println("PrintKey() TRANSACTIONS ARRAY: Length = "+this.transactions.length);
				for(int j=this.transArrayStart; j < this.transactions.length; j++)//Print index i=1 - 5
				{
					System.out.print(((Transaction2433)transactions[j]).getNumber()+" ");
				}//for
				System.out.println("");
				System.out.println("PrintKey() INDEX ARRAY: Length = "+this.index.length);
				for(int j=this.indexArrayStart; j < this.index.length; j++)//Print index i=1 - 5
				{
					System.out.print(index[j]+" ");
				}//for
				System.out.println("");
				System.out.println("PrintKey() INVERSEINDEX ARRAY: Length = "+this.inverseIndex.length);
				for(int j = this.inverseIndexArrayStart; j < this.inverseIndex.length; j++)//Print index i=1 - 5
				{
					System.out.print(inverseIndex[j]+" ");
				}//for
				System.out.println("");
		}
		catch( Exception e)
		{
			System.out.println("Array out of Bounds "+e);
		}
	}
	public int size()
	{
		//return this.transactions.length;
		return transactions.length-1;
	}
	/*public boolean insertLast(int capacity, Key insertNode)
	{
		Key[] temp = (Key[])new Comparable[capacity];
		boolean value = false;
		StdOut.println("resizeSingleInsert Length = "+capacity);
		//***Step1: Copy all but the last array item into the temp array.  The insertedNode, will be added in step2 below
		for (int i=0; i<capacity-1; i++)
			{
				temp[i] = transactions[i];
			}
		transactions=temp;
		//***Step2: Place the inserted Node in the last position of the transaction array
		transactions[capacity-1] = insertNode;	
		value = true;
		
		StdOut.println("Confirm Transactions Length = "+capacity);
		return value;
	}*/
	/*@SuppressWarnings("unchecked")
	public void resize(int max)
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
	}*/
	
	/*@SuppressWarnings("unchecked")
	public boolean resize(int max)
	{
		Key[] temp = (Key[])new Comparable[max];
		int[] indexTemp = new int[max];
		boolean value = false;
		try
		{
			//transactions
			StdOut.println("resize Length = "+max);
				for (int i = this.transArrayStart; i < max; i++)
				{
					temp[i] = this.transactions[i];
					indexTemp[i-1] = this.index[i-1];
				}
				this.transactions = temp;
				this.index = indexTemp;
				System.out.println("Index Length = "+this.index.length);
				value = true;
				StdOut.println("Confirm: resize Length = "+max);
		}
		catch(Exception e)
		{
			value = false;
			StdOut.println("Exception occured "+e);
		}
		return value;
	}//resize*/
	public Key removeMax(Key target)
	{
		Key returnValue = target;//Set return Value to transaction searched for.
		try{
			for (int i=1; i<this.n; i++)
			{
				if(transactions[i].compareTo(target)== 0)
				{
					//(3) NOTE: Set the retun value to the value found
					returnValue = transactions[i];//decrement n counter
					//(4)NOTE: Set the found value to null after the return value been set.
					transactions[i] = null;
					//(4)NOTE: Swop index value of i position with last position. Because it is easier to
					//delete from the end of an array.
					//exch(i, this.n-1);
					exch(i, transactions.length-1);
					//inverseIndex?
					//(5) NOTE: Send the size of the new array after delete occurs
					reduceSize(--this.n);//the index length.
					//resize(this.n, index);
					//inverseIndex?
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
		//this.n = transactions.length;
		while(2*k <= this.n)
		{
			int j = 2*k;//child
			if(j < this.n && less(j, j+1)) j++;//compare children
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
		//{
			while(k >1 && less(k/2, k))//compare parent to child
			{
				exch(k/2, k);//exchange child will parent
				k=k/2;
				reheapifyExchCtrAfterAdd++;
			}
		//}
	}

	public void exch(int kDiv2, int k)
	{
		Key t = this.transactions[kDiv2]; this.transactions[kDiv2]=this.transactions[k]; this.transactions[k] = t;
		int indexT = k-1/2; kDiv2 = k-1; k = indexT;//Convert k back to indexArray values
		//int inverseIndexT = this.inverseIndex[i]; this.inverseIndex[i] = this.inverseIndex[j]; this.inverseIndex[j] = inverseIndexT;
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
		/*System.out.println("In emption THIS.N = "+this.n+" InitialCapacity = "+initialTransCapacity);
		if(this.n == initialTransCapacity) 
			value = true;
		else if(this.n != initialTransCapacity)
			value = false;*/
		if( this.n == 0)
		{
			value = true;
		}
		return value;
	}
	public void loadAndReverseSortInputArray(String[] args, int initialIndexCapacity)
	{
		//System.out.println("TransCapacity = "+initialTransCapacity);
		
		//Step4: Set index, inverse, and transaction array size, by calling contructor
		this.inputArray = (Key[]) new Transaction2433[initialIndexCapacity];//has 250(0-249) elements
		
		//Step3: Fill inputArray with 26 arg elements (0-25) from Doubling Test
		System.out.println("Print Input Array:");
		for(int i = this.indexArrayStart; i<initialIndexCapacity; i++)//0-9
		{
			this.inputArray[i] = (Key) new Transaction2433(args[i]);//Input array only has (0-249) 250 elements
			System.out.print(((Transaction2433)this.inputArray[i]).getNumber()+" ");
		}
		
		System.out.println("");
		
		//Step4: Sort inputArray a-z
		//Insertion.sort(this.inputArray);
		
		//Step5: Reverse input Array and input array in descending order (z-a)
		//this.reverseTransOrder();//changes index and inputArray
		
		/*System.out.println("Print: Initial Array from inputArray():");
		System.out.println("InputArray length = "+pq.inputArray.length);*/
		/*for(int i = pq.indexArrayStart; i<initialIndexCapacity; i++)
		{
			//inputArray[i] = new Transaction2433(args[i]);//Input array only has (0-249) 250 elements
			System.out.print(((Transaction2433)pq.inputArray[i]).getNumber()+" ");
		}*/
		System.out.println("");
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
		System.out.println("IndexCapacity = "+initialIndexCapacity);
		
		//Step3: Add 1 to initial length transCapacity: //To shift the array values to index 1-26, for a Heap, increase the capacity by 1 (26+1=27)
		initialTransCapacity = initialIndexCapacity+1;//There is nothing in the 0 Index of a priority queue, so add one to total capacity.
		
		//Step4: Load and Reverse Sort InputArray
		pq.loadAndReverseSortInputArray(args, initialIndexCapacity);
		
		System.out.println("*********************START********************************");
			
		//Step5: Insert inputArray transaction into transactions Array in Reverse order, so Highest priority is in position 1, Set priority arrays.
		if(pq.isEmpty())
		{
			//initializeArrays(initialTransCapacity);
			System.out.println("The priority = "+pq.n);
			//pq.n=11: 11-1=10 (9-1)
				
			for(int priority = pq.transArrayStart; priority < initialTransCapacity; priority++)//pq.transArrayStart= 1; initialTransCapacity =11
			{
				//Step 3-A: Increase the array size to add 1;
				pq.increaseSize(++pq.n);
				/*System.out.println("THIS N = "+pq.n);//(1-10=total 10)*/			
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
		System.out.println("Max Key = "+((Transaction2433)pq.maxKey()).getNumber());
		System.out.println("Min Key = "+((Transaction2433)pq.minKey()).getNumber());	
		System.out.println("Index Array Size: Before Remove = "+(pq.size()));
		System.out.println("Removed Key = "+((Transaction2433)pq.removeMax(pq.maxKey())).getNumber());
		System.out.println("Index Array Size: After Remove =  "+(pq.size()));	
		//Note2 Remove
		System.out.println("Index Array after Delete-Sink reheapify:");
		pq.printKey();
		System.out.println("Reheapify Ctr after Delete = "+pq.reheapifyExchCtrAfterDelete);
		//Note3 Add
		pq.insertSingleTransaction(new Transaction2433("y"));
		//System.out.println("Array Add-Swim reheapify:");
		pq.printKey();
		System.out.println("Reheapify Ctr after ADD = "+pq.reheapifyExchCtrAfterAdd);
	}
}