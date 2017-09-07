//compile: javac -cp .;algs4_ts.jar Stack.java
//run: java -cp .;algs4_ts.jar Stack

public class Stack<Item>{
	private Node head = new Node();
	private Node middle = new Node();
	private Node tail = new Node();
	
	private class Node<Item>
	{
		Item item;
		Node next;
	}
	@SuppressWarnings("unchecked")
	public Stack(String item1, String item2, String item3){
		head.item = item1;
		head.next = middle;
		middle.item = item2;
		middle.next = tail;
		tail.item = item3;
	}
	@SuppressWarnings("unchecked")
	public void traverseList()
	{
		Node start;
		for (start = head; start != null; start = start.next)
		{
			StdOut.println("Node = "+start.item);
		}
		//StdOut.println("last startNode = "+start.item);
	}
	
	@SuppressWarnings("unchecked")
	public boolean push(){
		boolean value = false;
		Node selectedNode=new Node();
		Node start;
		for (start = head; start != null; start = start.next)
		{
			selectedNode = start;
		}
		try{
			//Step 1: Create new Last item
			Node newLast = new Node();
			newLast.item = "newlast";
			newLast.next = null;
			//Step 2: Set temp node to new node
			selectedNode.item = "newMiddle";
			selectedNode.next = newLast;
			//Step 3: Set tail node to new end node.
			tail = selectedNode.next;
			value = true;
		}
		catch (Exception e)
		{
			value = false;
		}
		
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public boolean pop(){
		boolean value = false;
		Node start;
		Node selectedNode = new Node();
		//Step 1: Stop loop before the last node (start.next != null)
		for (start = head; start.next != null; start = start.next)
		{
				selectedNode = start;			
		}
		try{	
			//Step 2: Delete the last node
			selectedNode.next.item = null;
			selectedNode.next.next = null;
			//Step 3: set current node to new last node
			selectedNode.item="New Last";
			selectedNode.next = null;
			//Step 4: Set tail to end node
			tail=selectedNode;
			StdOut.println("tail node is = "+tail.item);
			value = true;
		}
		catch(Exception e)
		{
			value =false;
			StdOut.println(e);
		}
		return value;
	}
	public boolean compare(Node node)
	{
		boolean value = false;
		Node compare=new Node();
		for(Node x=head; x!=null; x=x.next)
		{
			compare =x;
			try{
				if(compare == node)
				{
					value = true;
					StdOut.println("Is the pointer pointing to the middle node "+compare.item);
				}
			}
			catch(Exception e)
			{
				value = false;
			}
		}
		return value;
	}
	
	public static void main(String[] args)
	{
		Stack list = new Stack("first", "middle", "last");
		list.traverseList();
		boolean nodePushed =list.push();
		StdOut.println("Was node pushed? "+nodePushed);
		list.traverseList();
		
		//list.pop();
		boolean nodePopped = list.pop();
		StdOut.println("Was node popped? "+nodePopped);
		list.traverseList();
		//Does loop pointer equal middle
		boolean found=list.compare(list.middle);
		StdOut.println("Was middle found? "+found);
		//list.traverselist();
	}
}