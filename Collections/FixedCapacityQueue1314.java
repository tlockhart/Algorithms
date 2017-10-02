//Program enqueues text from and external file and dequeues items from the queue
//Compile: javac -cp .;algs4_ts.jar FixedCapacityQueue1314.java
//Run: java -cp .;algs4_ts.jar FixedCapacityQueue1314 < FCQ1314_data.txt
//Example:
	/*I don't like this job it blows and so do
	don't like this job it blows and so null
	like this job it blows and so null
	like    this     job      it        blows     and       so      null*/

public class FixedCapacityQueue1314<Item>{
	private Item[] a;
	private int size;
	private int position=0;
	
	//surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")
	public FixedCapacityQueue1314(int size){
		//initialize array a, by casting a new object to a generic array type
		this.size=size;
		a = (Item[]) new Object[this.size];
	}
	
	public boolean isEmpty()
	{return (this.size==0);}
	
	public int size()
	{return this.size;}
	
	public void enqueue(Item item)
	{
		if(position<this.size)
		{
			//Increment position after execution
			a[position++]= item;
		}
		//call resize if end array is reached
		else if(position==this.size)
		{
			resize(2*a.length);
			a[position++]= item;
		}
	}
	
	//surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")private void resize(int max)
	{ // Move stack to a new array of size max.
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < this.size; i++)
			temp[i] = a[i];
		a = temp;
	}
	
	public void dequeue()
	{
		for(int i=0; i<this.size-1; i++)
		{
			//Change references after item removed
			a[i]=a[i+1];
			a[size-1]=null;
		}
		--this.size;
	}
	
	public void iterate(){
		for(int i=0;i<size;i++)
		{
			StdOut.printf("%-14s", a[i]);
		}
	}
	
	//toString is returns a string representation of what is in the queue.
	@Override
	public String toString(){
		String queue = new String();
		for (int i=0; i<this.size; i++)
		{
			queue +=a[i]+" ";
		}
		return queue;
	}
	
	public static void main(String[] args)
	{
		//fixed size
		//int size=10;
		
		
		//The name of the string to input in the queue should be named item
		//readAllStrings is how you accept input from an external file
		String[] item = StdIn.readAllStrings();
		//dynamic size
		int size = item.length;
		
		//Enter the specific type before used
		FixedCapacityQueue1314 <String> queue = new FixedCapacityQueue1314<String>(size);
		
		for(int i=0; i<size; i++)
		{
			queue.enqueue(item[i]);
		}//for
		
		StdOut.println(queue.toString());
		queue.dequeue();

		StdOut.println(queue.toString());
		queue.dequeue();
		
		StdOut.println(queue.toString());
		queue.iterate();
	}
}