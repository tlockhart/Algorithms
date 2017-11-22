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
	private static int[] index;// index of transactions Key values
	private static int[] inverseIndex;
	//private static Transaction2433[] inputArray;
	private int indexArrayStart = 0;
	private int transArrayStart = 1;
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
	public void initializeArrays(int initialTransCapacity)
	{
		this.n = initialTransCapacity;
		this.initialTransCapacity = initialTransCapacity;
		this.initialIndexCapacity = initialTransCapacity-1;
		//Step 4b: Initialize array elements
		this.initializeArrays();
		System.out.println("**********THIS N = "+this.n);
		
		/*********************/
		//this.n=capacity;
		this.transactions = (Key[]) new Comparable[this.n];
		this.index = new int[this.n];
		this.inverseIndex = new int[this.n];
		//4c: fill indexArray with -1
		fillIndexPtrArray();
	}
	public void insert(int priority, Key insertNode)// 1, index[9]=
	{
		//this.initializeArrays();
		//this.n = transactions.length;
		//Note1: resizeSingle the array transactions
		//System.out.println("Transactions before swim:");

		//**10/24/2017: preint index values
		//this.printKey(); //**10/24/2017: preint index values
		
		if((this.n < 1)&&(!this.contains(priority)))
		{
			this.index[priority] = priority;//if this starts at 0
			this.transactions[priority] = insertNode;
			//System.out.println("$$$$$1 index = -1 "+index[priority]);
		}
		//BulkInsert
		else if((this.n > 0)&&(!this.contains(priority)))
		{
			//10/24/2015: Determine if there is an entry in the index array with the index position
			//if(!this.contains(priority))
			//{
				int indexArrayCalculation = priority-1; //0-9
				int inverseIndexCalculation = (this.n-1)-(priority);//10-(1-1)=10..10-(10-1)=1
				this.index[indexArrayCalculation] = priority;//Starts filling indexArray at index 0 (prority = 1-10)
				this.inverseIndex[inverseIndexCalculation] = priority;//reverse the array 10-(1-1=0)...10-(10-1=9) (priority 1-10)
				//System.out.println("$$$$$1 indexArrayIndex 0-9=  "+indexArrayCalculation+" index value=  "+index[indexArrayCalculation]+" inverseIndex = 2-11 "+inverseIndexCalculation+" inverseIndex value = "+inverseIndex[inverseIndexCalculation]);
				//System.out.println("$$$$$2 index value=  "+index[indexArrayCalculation]+" inverseIndex value = "+inverseIndex[inverseIndexCalculation]+" Priority = "+priority);
				this.transactions[priority] = insertNode;
				//insert(priority, insertNode);
				//10/26/2017: transactions[transactionsReverseOrderIndex] = insertNode;
				//Note2: Swim up to reheapify
				//StdOut.println("Check length in InsertSingleTransaction = "+this.n);
				swim(priority);
				System.out.println("After Swim");
				printIndex();
				//System.out.println("InsertSingleTransaction() Transaction List: Length = "+this.n);
			//}//if
		}//else if
	}//insert
	public boolean contains(int priority)
	{
		boolean value;
		if(index[priority] != -1)
			value = true;
		else 
			value = false;
		
		return value;
	}
	public void fillIndexPtrArray()
	{
		for (int i = 0; i < this.n; i++)
		{
			this.index[i] = -1;
		}
	}
	public void reverseOrder(Key[] transactions, Key[] v, int i)
	{
		//this.n = transactions.length;
		int transactionsReverseOrderIndex = this.n-i;
		transactions[transactionsReverseOrderIndex] = v[(i-1)];
		this.index[i] = transactionsReverseOrderIndex;//since transactions are reversed. The index will contain ptrs to the reverse order of transactions	
	}
	//We have the generic in the signature and the actual type (Transaction2433) will be passed in.
	/*10/24/2017 
	public void initialBulkInsert(Key[] v)
	{
		this.n = transactions.length;
		Insertion.sort(v);
		System.out.println("InsertTransaction() V List: Length = "+v.length);
		//Since v has 26 (0-25) elements and transactions has 27 (0-26), remap the elements in revereOrder.
		for(int i = this.n-1; i>0; i--)
		{
			int vChronologicalOrderIndex = v.length-i;
			System.out.print(((Transaction2433)v[vChronologicalOrderIndex]).getNumber()+" ");
			inverseIndex[i] = i;//since pq is in reverse order, the inverse would be in normal order;
			//Note: Store in Reverse Sort Order:
			reverseOrder(transactions, v, i);
		}
		System.out.println("");
		System.out.println("Print: Index values:");		
		//**10/24/2017: preint index values
		printArray(this.index);
		
		System.out.println("Print: InverseIndex Values:");
		//**10/24/2017: int inverseindex values
		printArray(this.inverseIndex);
		
		System.out.println("");
		//(1)NOTE:	 Must shuffle the list to make the Keys unordered after the values are inserted.
		//StdRandom.shuffle(transactions);		
	}*/
	public Key maxKey()
	{
		//this.n = transactions.length;
		//System.out.println("MaxKey: Transaction2433s Size = "+transactions.length);
		Transaction2433 dummyTransaction = new Transaction2433("d");
		Key maxKey = (Key) dummyTransaction;// = new Key[this.n];
		/*try
		{*/
		
			if(this.n < 1)
			{
				//throw new NullPointerException();
			}
			else if(this.n == 2)
			{
			/*********1/24/2017************************/
			maxKey = transactions[1];//set max Key to initial value*/
			/*********1/24/2017************************/
			}
			else{// if(this.n > 1){
				maxKey = transactions[1];//set max Key to initial value
				for(int i = 2; i<this.n; i++)
				{
					if(transactions[i].compareTo(transactions[1]) > 0)
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
	public void printIndex()
	{
		System.out.println("PrintIndex() Index ARRAY: Length = "+(this.n-1));
		for(int priority = indexArrayStart; priority < this.n-1; priority++)//(transactions n = 11, index range:9-0)
		{
			if(index[priority] != null)
				System.out.print(index[priority]+" ");//System.out.println("Index value =  "+index[priority]+" inverseIndex value = "+inverseIndex[priority]+" Indexes = "+priority);
		}
		System.out.println("");
		System.out.println("PrintIndex() Inverse ARRAY: Length = "+(this.n-1));
		for(int priority = indexArrayStart; priority < this.n-1; priority++)//Print index i=1 - 5
		{
			if(inverseIndex[priority] != null)
				System.out.print(inverseIndex[priority]+" ");
		}//for
		System.out.println("");
	}
	
	/*public void printArray(int[] array)
	{
		try{
			for(int i=1; i < this.n; i++)//Print index i=1 - 5
			{
				System.out.print(array[i]+" ");
			}
			System.out.println("");
		}
		catch(Exception e)
		{
			System.out.println("Array out of Bounds "+e);
		}
	}*/
	public void printKey(Key[] key, int[] index)
	{
		//this.n = key.length;
		System.out.println("PrintKey() INDEX ARRAY: Length = "+this.n);
		try{
			//System.out.println("Transaction2433s in PQUnorderedArray2433:");
			/*for(int i=indexArrayStart; i < this.n-1; i++)//Print index i=1 - 5
			{
				//System.out.print(((Transaction2433)key[i]).getNumber()+" ");
				System.out.print(index[i]+" ");
				//System.out.print(" Index Value = "+index[i]+" index I = "+i);//index[10], i = 10-1
			}//for
			System.out.println("");*/
			printIndex();
			if(key.length>=this.initialTransCapacity)
			{
				System.out.println("PrintKey() TRANSACTIONS ARRAY: Length = "+this.n);
				for(int j=transArrayStart; j < this.n; j++)//Print index i=1 - 5
				{
					System.out.print(((Transaction2433)key[j]).getNumber()+" ");
				}//for
				System.out.println("");
			}//if
		}
		catch( Exception e)
		{
			System.out.println("Array out of Bounds "+e);
		}
	}
	public int size()
	{
		//return this.transactions.length;
		return this.n;
	}
	public boolean insertLast(int capacity, Key insertNode)
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
	}
	@SuppressWarnings("unchecked")
	public boolean resize(int max, Key[] key, int[] index)
	{
		Key[] temp = (Key[])new Comparable[max];
		int[] indexTemp = new int[max];
		boolean value = false;
		try
		{
			StdOut.println("resize Length = "+max);
				for (int i=0; i<max; i++)
				{
					temp[i] = key[i];
					indexTemp[i] = index[i];
				}
				key=temp;
				index = indexTemp;
				value = true;
				StdOut.println("Confirm: resize Length = "+max);
		}
		catch(Exception e)
		{
			value = false;
			StdOut.println("Exception occured "+e);
		}
		return value;
	}//resize
	public Key removeMax(Key target)
	{
		Key returnValue = target;//Set return Value to transaction searched for.
		//this.n = transactions.length;
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
					exch(transactions, index, i, this.n-1);
					//inverseIndex?
					//(5) NOTE: Send the size of the new array after delete occurs
					//resize(--this.n, transactions, index);
					//resize(this.n, index);
					//inverseIndex?
					//(6) Print array after delete;
					System.out.println("Array after delete:");
					this.printKey(this.transactions, this.index);

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
			exch(transactions, index, k, j);
			k = j;
			reheapifyExchCtrAfterDelete++;
		}
	}
	//P316 Swim
	private void swim(int k)
	{
		if(k>=2)//k is the index of the indexArray, if >2, it has at least 2 elements
		{
			while(k >1 && less(k/2, k))//compare parent to child
			{
				exch(this.transactions, this.index, k/2, k);//exchange child will parent
				k=k/2;
				reheapifyExchCtrAfterAdd++;
			}
		}
	}

	
	public boolean less(int k, int j)
	{
		boolean value = false;
		if((transactions[k] == null)||(transactions[j] == null))
		{
			value = false;
		}
		else{
			value = transactions[k].compareTo(transactions[j]) < 0;
		}
		return value;
	}
	
	public void exch(Key[] a, int[] index, int i, int j)
	{
		Key t = a[i]; a[i]=a[j]; a[j] = t;
		int indexT = index[i]; index[i]=index[j]; index[j] = indexT;
	}
	public boolean isEmpty()
	{
		boolean value = false;
		/*System.out.println("In emption THIS.N = "+this.n+" InitialCapacity = "+initialTransCapacity);
		if(this.n == initialTransCapacity) 
			value = true;
		else if(this.n != initialTransCapacity)
			value = false;*/
		if( index.n == 0)
		{
			value = true;
		}
		return value;
	}
	//client
	@SuppressWarnings("unchecked")
	public void startPQ(String[] args)
	{		
		//Step1: Set the Initial length
		initialIndexCapacity = args.length; //take capacity from input
		System.out.println("IndexCapacity = "+initialIndexCapacity);
		
		//Step2: Add 1 to initial length transCapacity: //To shift the array values to index 1-26, for a Heap, increase the capacity by 1 (26+1=27)
		initialTransCapacity = initialIndexCapacity+1;//There is nothing in the 0 Index of a priority queue, so add one to total capacity.
		System.out.println("TransCapacity = "+initialTransCapacity);
		Transaction2433[] inputArray = new Transaction2433[initialIndexCapacity];//has 250(0-249) elements
		//Step3: Fill inputArray with 26 arg elements (0-25) from Doubling Test
		System.out.println("Print Input Array:");
		for(int i = indexArrayStart; i<initialIndexCapacity; i++)//0-9
		{
			inputArray[i] = new Transaction2433(args[i]);//Input array only has (0-249) 250 elements
			System.out.print(((Transaction2433)inputArray[i]).getNumber()+" ");
		}
		System.out.println("");
		//Step4: Sort input Array a-z
		Insertion.sort(inputArray);
		System.out.println("Print: Initial Array from inputArray():");
		for(int i = indexArrayStart; i<initialIndexCapacity; i++)
		{
			//inputArray[i] = new Transaction2433(args[i]);//Input array only has (0-249) 250 elements
			System.out.print(((Transaction2433)inputArray[i]).getNumber()+" ");
		}
		System.out.println("");
		
		//Step 5: Instantiate Class object, set initial array sizes
		PQUnorderedArray2433 pq = new PQUnorderedArray2433(initialTransCapacity);//PQUnorderedArray2433 can take up to 251 (0-250) transactions in the transaction array
		
		/*******************************************************************************************************************/
		
		//Transactions[] created in constructor, set N to pqCapacity
		//pq.insertSingleTransaction(new Transaction2433("y"));

		System.out.println("*********************START********************************");
		
		//Step 6: Print initial array from input Array:
		
		
		//Step 7: Insert input Array transaction into Transactions Array in Reverse order, so Highest priority is in position 1
		System.out.println("Is pq empty ? "+pq.isEmpty());
		if(pq.isEmpty())
		{
			/*for(int i = 1; i < pq.n; i++)
			{
				
				
			}*/
			System.out.println("The priority = "+pq.n);
			//pq.n=11: 11-1=10 (9-1)
			for(int priority = pq.n-1; priority>0; priority--)//priority = 11-1 = 10 (10 to 1)
			{
				//Step1: Insert reverse inputArray order(9 through 0), placed in Transactions chronological order(1-10)
				int chronologicalTransIndexOrder = initialTransCapacity-priority;//cronTransIndex=11-10=1...11-1=10 (1-10)
				pq.insert(chronologicalTransIndexOrder, inputArray[priority-1]);// map inputArray index 0-9 (total 10 index) to transaction Array 1-10 (Total 11 index);
				System.out.println("chron = "+chronologicalTransIndexOrder);
				//int vChronologicalOrderIndex = pq.n-i;
				//System.out.print(((Transaction2433)PQUnorderedArray2433.inputArray[i]).getNumber()+" ");
				//10/26/2017:inverseIndex[priority] = priority;//since pq is in reverse order, the inverse would be in normal order;
				//Note: Store in Reverse Sort Order:
				
				//reverseOrder(transactions, v, i);
				//this.n = transactions.length;
				
				/*10/25/2017int transactionsReverseOrderIndex = this.n-i;
				transactions[transactionsReverseOrderIndex] = v[(i-1)];*/
				
				//this.index[i] = transactionsReverseOrderIndex;//since transactions are reversed. The index will contain ptrs to the reverse order of transactions
			}
			pq.printIndex();
		}
		System.out.println("Print: Initial trasactional Array:");
		//Note1: Bulk Insert
		pq.printKey(pq.transactions, pq.index);//index = 1, index = 1
		
		/*10/25/2017:
		pq.printKey(pq.index)*/;
		
		//Return the maxKey as a casted Transaction2433
		/*10/26/2017: System.out.println("Max Key = "+((Transaction2433)pq.maxKey()).getNumber());
		System.out.println("Min Key = "+((Transaction2433)pq.minKey()).getNumber());	
		System.out.println("Array Size: Before Remove = "+(pq.size()));
		System.out.println("Removed Key = "+((Transaction2433)pq.removeMax(pq.maxKey())).getNumber());*/
		System.out.println("Array Size: After Remove =  "+(pq.size()));
		//Note2 Remove
		System.out.println("Array after Delete-Sink reheapify:");
		pq.printKey(pq.transactions, pq.index);
		System.out.println("Reheapify Ctr after Delete = "+pq.reheapifyExchCtrAfterDelete);
		//Note3 Add
		/*10/24/2017 pq.insertSingleTransaction(new Transaction2433("y"));*/
		System.out.println("Array Add-Swim reheapify:");
		pq.printKey(pq.transactions, pq.index);
		System.out.println("Reheapify Ctr after ADD = "+pq.reheapifyExchCtrAfterAdd);
		
	}
}