//compile: javac -cp .;algs4_ts.jar DynamicArray.java
//run: java -cp .;algs4_ts.jar DynamicArray
public class DynamicArray<Item>{
	private int size=4;
	private Item[] stack;
	private int tracker =0;
	
	@SuppressWarnings("unchecked")
	public DynamicArray(Item item1, Item item2, Item item3, Item item4)
	{
		stack = (Item[])new Object[size];
		StdOut.println("Push1 succeeded "+this.push(item1));
		StdOut.println("Push2 succeeded "+this.push(item2));
		StdOut.println("Push3 succeeded "+this.push(item3));
		printArray();
		StdOut.println("Push4 succeeded "+this.push(item4));
		printArray();
	}
	
	@SuppressWarnings("unchecked")
	public boolean resize(int max)
	{
		Item[] temp = (Item[])new Object[max];
		boolean value = false;
		try
		{
				for (int i=0; i<tracker; i++)
				{
					temp[i] = stack[i];
				}
				//7/27/2017:Should be outside of the loop
				stack=temp;
				value = true;
		}
		catch(Exception e)
		{
			value = false;
		}
		return value;
	}//resize
	@SuppressWarnings("unchecked")
	public boolean push (Item item)
	{
		boolean value = false;
		try
		{
			
			if(tracker == stack.length)
			{
				resize(2*stack.length);
				StdOut.println("Incrementing size");
			}
			//add item at end of stack, then increment after this statment;
			stack[tracker++]= item;
			value = true;
		}
		catch(Exception e)
		{
			value = false;
			StdOut.println("Exception = "+e);
		}
		return value;
	}//push
	@SuppressWarnings("unchecked")
	public Item pop()
	{
		Item returnValue=null;
		try{
			//Pre-decrement, because of 0 array index the last index is tracker-1
			returnValue = stack[--tracker];
			//set last item to null for garbage collection;
			stack[tracker]= null;
			if((tracker>0)&&(tracker==(1/4*(stack.length))))
			{
				resize(1/2*(stack.length));
			}
		}
		catch(Exception e)
		{
		}
		return returnValue;
	}//pop
	public void printArray()
	{
		for(int i = 0; i<tracker; i++)
		{
			StdOut.println("Array Element = "+stack[i]);
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		//When declaring the dynamic array object, if the values are reference types, you must 
		//specify the actual value after the type name, otherwise use autoboxing to convert primitives
		//DynamicArray<String> list = new DynamicArray<String>(1,2,3,4);
		DynamicArray<String> list = new DynamicArray<String>("1", "2", "3", "4");
		boolean pushResult = list.push("5");
		StdOut.println("Was push successfull : "+pushResult);
		list.printArray();
		
		StdOut.println("Popped item ="+list.pop());
		//StdOut.println("Was pop successfull: "+popResult);
		list.printArray();
	}
}