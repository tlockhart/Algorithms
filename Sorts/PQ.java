/**compile Multiple: javac -cp .;algs4_sts.jar; Transaction.java PQ.java
  *run: java -cp ../;.;algs4_sts.jar; sorts.PQ*/
 
 //READ THIS: http://docs.oracle.com/javase/tutorial/collections/interfaces/order.html
package sorts;

//Import classes from algs4
//import exercises.*;
//Only a generic Key that inherits from the Comparable class can be used in this class
public class PQ<Key extends Comparable<Key>>
{
	private boolean emptyElementFound = false;
	//One priority queue can have multiple transactions
	//Have to cast Comparable to a Key array type
	private Key[] transactions;
	private int n=0;
	private int capacity=0;
	
	//constructor
	public PQ(int capacity)
	{
		transactions = (Key[]) new Comparable[capacity];
		this.capacity = capacity;
	}
	
	/*public void insert(Key[] v)
	{
		//transactions[++n] = v;
		if(((Transaction)transactions[n]) != null){
		System.out.println(" Inserted "+((Transaction)transactions[n]).getNumber()+" at: "+n);
		}
		System.out.println("*******************");
		System.out.println("Found "+((Transaction)transactions[1]).getNumber()+ " at "+1);
		System.out.println("Found "+((Transaction)transactions[2]).getNumber()+ " at "+2);
		System.out.println("*******************");
	}*/
	//We have the generic in the signature and the actual type (Transaction) will be passed in.
	public void insertTransactions(Key[] v)
	{
		System.out.println("V length = "+v.length);
		for(int i=1; i<v.length; i++)//Print index i=1 - 5
		{
			transactions[i] = v[i];
			//System.out.println(((Transaction)transactions[i]).getNumber());
		}		
	}
	public Key maxKey()
	{
		//System.out.println("N = "+this.n);
		System.out.println("MaxKey: Transactions Size = "+transactions.length);
		Key maxKey = transactions[1];
		//Have to subtract 1 from index position, 
		//because we are looking in i+1 position
		for(int i = 1; i<transactions.length-1; i++)
		{
			maxKey = transactions[i];
			if(transactions[i+1].compareTo(maxKey) > 0)
				maxKey = transactions[i+1];
		}
		return maxKey;
	}
	public Key minKey()
	{
		//System.out.println("N = "+this.n);
		//System.out.println("MinKey: Transactions Size = "+transactions.length);
		Key minKey = transactions[1];
		//Have to subtract 1 from index position, 
		//because we are looking in i+1 position
		for(int i = 1; i<transactions.length-1; i++)
		{
			minKey = transactions[i];
			if(transactions[i+1].compareTo(minKey) < 0)
			{
				minKey = transactions[i+1];
			}
		}
		return minKey;
	}
	public void printKey()
	{
		try{
			System.out.println("Transactions in PQ:");
			for(int i=1; i< transactions.length; i++)//Print index i=1 - 5
			{
				//transactions[i] = v[i];
				System.out.println(((Transaction)transactions[i]).getNumber());
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
				for (int i=0; i<max; i++)
				{
					temp[i] = transactions[i];
				}
				//7/27/2017:Should be outside of the loop
				transactions=temp;
				value = true;
		}
		catch(Exception e)
		{
			value = false;
		}
		return value;
	}//resize
	public Key removeMax(Key v)
	{
		Key returnValue = transactions[1];
		for (int i=1; i<transactions.length+1; i++)
		{
			if(transactions[i].compareTo(v)== 0)
			{
				returnValue = transactions[i];//decrement n counter
				
				//returnValue = transactions[--n];
				transactions[i] = null;
				resize(this.n);
				//transactions[i] = null;
			}
		}
		this.printKey();
		return returnValue;
	}
	//client
	public static void main(String[] args)
	{		
		int capacity = 6;//There is nothing in the 0 Index of a priority queue, so add one to total capacity.
		PQ pq = new PQ(capacity);//PQ can take up to 10 transactions in the transaction array

		Transaction transaction1 = new Transaction(111);
		Transaction transaction2 = new Transaction(222);
		Transaction transaction3 = new Transaction(333);
		Transaction transaction4 = new Transaction(444);
		Transaction transaction5 = new Transaction(555);
		
		Transaction[] t2 = new Transaction[capacity];  
		t2[++pq.n] = transaction1;//Store in index 1
		t2[++pq.n] = transaction2;//Store in index 2
		t2[++pq.n] = transaction3;//Store in index 3
		t2[++pq.n] = transaction4;//Store in index 4
		t2[++pq.n] = transaction5;//Store in index 5
		
		//Insert Transactions array into PriortyQueue (PQ)
		pq.insertTransactions(t2);
		pq.printKey();
		System.out.println("The size = "+pq.size());
		//Return the maxKey as a casted Transaction
		System.out.println("Max Key "+((Transaction)pq.maxKey()).getNumber());
		System.out.println("Min Key "+((Transaction)pq.minKey()).getNumber());	
		System.out.println("Removed Key "+((Transaction)pq.removeMax(pq.maxKey())).getNumber());
	}
}