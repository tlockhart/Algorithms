//Program add/removes items from a linked list.
//Compile: javac -cp .;algs4_ts.jar LinkedListRemoveInsertNodes1331.java
//Run: java -cp .;algs4_ts.jar LinkedListRemoveInsertNodes1331
//NOTE: NODE POINTER IS THE INDEX OF THE NODE
//NOTE: POINTERS START AT 0

/******************/
//IMPORTANT TIP ABOUT LINKEDLIST: The key to inserting a node in a linkedlist is 
//not to assign the targetNodeprevious and next property, until you have assigned
//the insertedNode previous and next property.

//STEP 1: extend Generic <Item>
public class LinkedListRemoveInsertNodes1331<Item>{
	Node first = new Node();
	Node second = new Node();
	Node third = new Node();
	/****for inserting Nodes after the targetNode******/
	Node targetNode = new Node();
	int toggle=0;
	
	//STEP 2: Constructor arguments have to have real values, they are not generic.
	//Surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")
	public LinkedListRemoveInsertNodes1331(String firstItem, String secondItem, String thirdItem){
		//Step1: Set ITEMS
		this.first.item = firstItem;//first;
		this.second.item = secondItem;//second;
		this.third.item = thirdItem;//third;
		
		//Step2: Set the next and previous node
		this.first.next = this.second;
		this.second.next = this.third;
		this.third.previous = this.second;
		this.second.previous = this.first;
	}
	//STEP 3: Define node abstraction
	private class Node<Item>
	{
		//All the instance variables are initialized to null
		Item item;
		Node next;
		Node previous;
	}
	//Inserts a new node at the start of the linkedlist
	//surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")
	private void insertFirstNode(Item item)
	{
		StdOut.println("Inserting New First Node");
		//copy empty first node into oldFirstNode
		Node oldFirst=this.first;
		//Re-initialize first Node
		this.first = new Node();
		//Set new first Node item to what was sent in the args
		this.first.item = item;
		//Set first Node pointer to oldFirstNode
		this.first.next = oldFirst;
		oldFirst.previous=this.first.next;
	}
	//Inserts a new node at the end of the linkedlist
	//surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")
	private void insertLastNode(Item item)
	{
		StdOut.println("Inserting New Last Node");
		//copy empty first node into oldFirstNode
		Node newLastNode=new Node();
		//Initialize first Node
		newLastNode.item = item;
		//set lastNode to the last node in the list
		Node oldLastNode = getSelectedNode(getSize()-1);
		//Set oldLastNode to Point to newLastNode
		oldLastNode.next = newLastNode;
		//STEP4: When inserting a node, make sure to
		//add a pointer back to the last node.
		newLastNode.previous=oldLastNode;
	}
	//STEP 5: How to return a target node
	public Node getSelectedNode(int nodePosition)
	{
		int position=0;
		Node selectedNode = new Node();
		for(Node x=this.first; x!=null; x=x.next)
		{
			if(position==nodePosition)
			{
				selectedNode = x;
				StdOut.println("Selected Node = "+selectedNode.item);
			}
			//lastNode=x;
			position++;
		}
		return selectedNode;
	}
	//Remove targetNode
	private void removeTargetNode(int i)
	{
		StdOut.println("Removing Target Node");
		//i is less than startNode
		if(i<2)
		{
			removeFirstNode(i);
		}
		//i is last nodePosition
		else if(i>=(getSize()))
		{
			removeEndNode();
			//removeTarget(i);
		}
		//i is between 1 and last Node
		else if((i>1)&&(i<getSize()))
		{
			Node oldTarget = getSelectedNode(i);
			Node previousNode = oldTarget.previous;
			Node nextNode = oldTarget.next;
			//Node newTarget = oldTarget.next;
			nextNode.previous = previousNode;
			previousNode.next=nextNode;
			oldTarget=null;
		}
	}	
	//Removes a node from the beginning of the linkedlist
	private void removeFirstNode(int i)
	{
		StdOut.println("Removing First Node");
		Node oldFirst = getSelectedNode(i);
		first = first.next;
		oldFirst=null;
	}	
	//Removes a node from the end of the linkedlist
	private void removeEndNode()
	{
		StdOut.println("Removing End Node");
		Node value = new Node();
		for(Node x=first; x !=null; x=x.next)
		{
			value =x;
		}
		if(valueCompare(value)){
			value.previous.next = null;
		}
	}	
	//Allows user to create a node and place it before or after an index position
	@SuppressWarnings("unchecked")
	private void createNode(Item item, int nodePosition, int positionToggle){
		this.toggle=positionToggle;
		//1 means insert node after
		int size = getSize();
		if(positionToggle ==1)
		{
			if(size>0)
			{
				if(nodePosition>=size)
				{
					insertLastNode(item);
				}
				else if(nodePosition<0){
					this.targetNode=getSelectedNode(0);
				}
				else
				this.targetNode=getSelectedNode(nodePosition);
			}
			Node newNode = new Node();
			newNode.item = item;//insertMe
			newNode.next=targetNode.next;
			this.targetNode.next = newNode;
		}
		//0 means insert node before
		else if(positionToggle ==0)
		{
			if(getSize()>1)
			{
				if(nodePosition>=getSize())
				{
					this.targetNode=getSelectedNode(getSize()-1);
				}
				else if(nodePosition<1){//first position
					insertFirstNode(item);//insert before first node
				}
				else
				{
					this.targetNode=getSelectedNode(nodePosition);
				}
			}
			Node newNode = new Node();
			//No manipulation is required if you are inserting 
			//at beginningCall insertFirstNode Instead
			if(!(nodePosition<1)){
				newNode.item=item;
				newNode.previous=targetNode.previous;
				targetNode.previous.next=newNode;
				newNode.next=this.targetNode;
				this.targetNode.previous = newNode;
			}
			StdOut.println("Inserted node");
		}
	}
	private boolean valueCompare(Node x)
	{
		StdOut.println("X Next = "+x.next);
		return (x.next == null);	
	}
	//STEP 6: How you traverse the Array
	private void traverseNode()
	{
		for(Node x=first; x !=null; x=x.next)
		{
		StdOut.println("X = "+x.item);
		}
	}
	private int getSize(){
		int size = 0;
		for(Node x=first; x !=null; x=x.next)
		{
			size++;	
		}
		StdOut.println("Original Size = "+size);
		return size;
	}
	public static void main(String[] args){	
		//STEP 7: The object declaration must declare the type of the generic
		LinkedListRemoveInsertNodes1331 <String> nodes = new LinkedListRemoveInsertNodes1331<String>("to", "be", "or");
		nodes.insertFirstNode("New First");
		nodes.traverseNode();
		StdOut.println("***************************************");
		nodes.removeEndNode();
		nodes.traverseNode();
		StdOut.println("***************************************");
		//nodes.createNode("insertMe", 8, 0);//Insert newNode before targetNode
		nodes.createNode("insert Me", 0, 1);//Insert newnode after targetNode
		//nodes.createNode("insertMe", 5, 0);//Insert newNode before targetNode
		nodes.traverseNode();
		StdOut.println("***************************************");
		nodes.insertLastNode("New Last");
		nodes.traverseNode();
		StdOut.println("***************************************");
		nodes.removeFirstNode(0);
		nodes.traverseNode();
		StdOut.println("***************************************");
		//Arguments (targetNode, toggle:before/after)
		nodes.removeTargetNode(4);
		nodes.traverseNode();
	}
}