//compile: javac -cp .;algs4_ts.jar StackDelete.java
//run: java -cp .;algs4_ts.jar StackDelete

@SuppressWarnings("unchecked")
public class StackDelete<Item>
{
		Node head = new Node();
		Node middle = new Node();
		Node tail = new Node();
	
	@SuppressWarnings("unchecked")
	public StackDelete(Item item1, Item item2, Item item3)
	{
		head.item = item1;
		head.next = middle;
		
		middle.item = item2;
		middle.next = tail;
		
		tail.item = item3;
		tail.next = null;
	}
	
	public class Node{
		Node next;
		Item item;
	}
	
	@SuppressWarnings("unchecked")
	public Node getNode(int index, Item item)
	{
		Node tracker = new Node();
		int counter = 1;
		for(Node start = head; start != null; start = start.next)
		{
			if(counter != index)
			{
				tracker = start;
				counter++;
			}
			else if(item == null)
			{
				tracker = head;
			}
		}//for
		return tracker;
	}//getNode
	
	@SuppressWarnings("unchecked")
	public boolean insertAfter(int index, Item item)
	{
		boolean value = false;
		Node targetNode =  new Node();
		Node newNode = new Node();
		newNode.item = item;
		if(newNode.item == null)
		{
			targetNode = head;
			newNode.next = targetNode;
			head = newNode;
			targetNode = null;
			value = true;
		}
		else 
		{
			targetNode = getNode(index, item);
			StdOut.println("Target node = "+targetNode.item);
			newNode.item = item;
			newNode.next = targetNode.next;
			targetNode.next = newNode;
			value = true;
		}//else
			return value;
	}//insertAfter
	
	public boolean delete(int index, Item item)
	{
		boolean value = false;
		Node targetNode = getNode(index, item);
		Node oldNode = targetNode.next;
		targetNode.next = targetNode.next.next;
		oldNode = null;
		
		value = true;
		return value;
	}//delete
	
	public void printList()
	{
		for (Node x = head; x != null; x=x.next)
		{
			StdOut.println("The Item is = "+x.item);
		}
	}
	
	public static void main(String[] args)
	{
		StackDelete list = new StackDelete("Node1", "Node2", "Node3");
		list.printList();
		
		boolean itemInserted = list.insertAfter(2, "NewNode2");
		StdOut.println("Was Item inserted "+itemInserted);
		list.printList();
		
		boolean itemDeleted = list.delete(2, "Node2");
		StdOut.println("Was Item Deleted "+itemDeleted);
		list.printList();
		
		boolean nullInsert = list.insertAfter(3, null);
		StdOut.println("Was Item inserted "+nullInsert);
		list.printList();
	}
}