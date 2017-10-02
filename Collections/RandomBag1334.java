//Inserts/removes items from a randombag
//Compile: javac -cp .;algs4_ts.jar RandomBag1334.java
//Run: java -cp .;algs4_ts.jar RandomBag1334

//NOTE: POINTERS START AT 1

/******************/
//IMPORTANT TIP ABOUT LINKEDLIST: The key to inserting a node in a linkedlist is 
//not to assign the targetNodeprevious and next property, until you have assigned
//the insertedNode previous and next property.
//Step1:Import Random Generator
import java.util.Random;

//STEP 2: extend Generic <Item>
public class RandomBag1334<Item>{
	Node first = new Node();
	Node second = new Node();
	Node last = new Node();
	/****for inserting Nodes after the targetNode******/
	Node targetNode = new Node();
	int toggle=0;
	/***************************************************/
	//Second LinkedList
	Node bagNode = new Node();
	
	//STEP 3: Constructor arguments have to have real values, they are  
	//not generic
    //surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")
	public RandomBag1334(String firstItem, String secondItem, String lastItem){
	//Step1: Set ITEMS
		this.first.item = firstItem;//first;
		this.second.item = secondItem;//second;
		this.last.item = lastItem;//last;
		
		//STEP 4: Set the next and previous node
		this.first.next = this.second;
		this.second.next = this.last;
		this.last.previous = this.second;
		this.second.previous = this.first;	
	}//constructor
	//Inserts a new node at the start of the linkedlist
	//surpress the unchecked cast warnings for Java Generics
	/*******************************************************/
	/*InsertFirst is used when inserting nodes into bagNode*/
	/*  when the 3 argument is 3, or in first Node if 2)   */
	 /*******************************************************/ 
	@SuppressWarnings("unchecked")
	private void insertFirstNode(Item item, Node myNode, int toggle)
	{
		StdOut.println("Inserting New First Node"+" Node Item = "+item);
		//copy empty first node into oldFirstNode
		Node oldFirst=myNode;
		//Re-initialize first Node
		myNode = new Node();
		//Set new first Node item to what was sent in the args
		myNode.item = item;
		//Set first Node pointer to oldFirstNode
		myNode.next = oldFirst;
		oldFirst.previous=myNode.next;
		if(toggle==3)
		{
			this.bagNode=myNode;
		}
		StdOut.println("myNode Item = "+myNode.item);
		StdOut.println("bagNode Item = "+this.bagNode.item);
	}
	//STEP 5: Define node abstraction
	private class Node<Item>
	{
		//All the instance variables are initialized to null
		Item item;
		Node next;
		Node previous;
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
		Node oldLastNode = getSelectedNode(getSize(this.first));
		//Set oldLastNode to Point to newLastNode
		oldLastNode.next = newLastNode;
		//STEP4: When inserting a node, make sure to
		//add a pointer back to the last node.
		newLastNode.previous=oldLastNode;
	}
	//STEP 6: How to return a target node
	public Node getSelectedNode(int nodePosition)
	{
		int position=1;
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
	//check if last node is null
	private boolean isEmpty(Node x)
	{
		//StdOut.println("X Next = "+x.next);
		return (x.next == null);	
	}
	//STEP 7: How you traverse the Array
	private void printList(Node startNode)
	{
		for(Node x=startNode; x !=null; x=x.next)
		{
		StdOut.println("X = "+x.item);
		}
	//STEP 8: GetSize
	}//Printlist
	private int getSize(Node startNode){
		int size = 0;
		for(Node x=startNode; x !=null; x=x.next)
		{
			size++;	
		}
		StdOut.println("Original Size = "+size);
		return size;
	}//getSize
	//STEP 9: Get RandoNode
	private int getRandomNode(int length)
	{
		Random randomItem = new Random();
		//generate items from 1 to size of linkedlist(excluded)
		int searchItem = (randomItem.nextInt(getSize(this.first))+1);
		return searchItem;
	}//getRandomNode
	private void createRandomBag()
	{
		while(getSize(this.first)>=1)
		{
			int nodeIndex=getRandomNode(getSize(this.first));
			StdOut.println("Random Item = "+nodeIndex);
			StdOut.println("BAGNODE SIZE = "+getSize(this.bagNode));
			knuthShuffle(nodeIndex, getSize(this.bagNode),3);
		}
	}//createRandomBag
	//Step 10: Allows user to create a node and place it before or after an index position
	@SuppressWarnings("unchecked")
	private void addNode(Item item, int nodePosition, int positionToggle){
		this.toggle=positionToggle;
		//1 means insert node after
		int size = getSize(this.first);
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
			newNode.next=this.targetNode.next;
			this.targetNode.next = newNode;
		}
		//0 means insert node before
		else if(positionToggle ==0)
		{
			if(getSize(this.first)>1)
			{
				if(nodePosition>=getSize(this.first))
				{
					this.targetNode=getSelectedNode(getSize(this.first));
				}
				else if(nodePosition<1){//first position
					insertFirstNode(item, this.first,2);//insert before first node
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
				newNode.previous=this.targetNode.previous;
				this.targetNode.previous.next=newNode;
				newNode.next=this.targetNode;
				this.targetNode.previous = newNode;
			}
			StdOut.println("Inserted node");
		}
	}//addNode
	//STEP 11: Removes a node from the beginning of the linkedlist
	private void removeFirstNode(int i)
	{
		StdOut.println("Removing First Node");
		Node oldFirst = getSelectedNode(i);
		first = first.next;
		oldFirst=null;
	}
	//STEP 12: Remove targetNodeNode between first and last
	private void removeTargetNode(int i)
	{
		StdOut.println("Removing targetNode Node");
		//i is less than startNode
		if(i<2)
		{
			removeFirstNode(i);
		}
		//i is last nodePosition
		else if(i>=(getSize(this.first)))
		{ 
			//StdOut.println("bagnode = "+this.bagNode.item);
			removeEndNode();
			//removetargetNode(i);
		}
		//i is between 1 and last Node
		else 
		{
			Node oldtargetNode = getSelectedNode(i);
			Node previousNode = oldtargetNode.previous;
			Node nextNode = oldtargetNode.next;
			nextNode.previous = previousNode;
			previousNode.next=nextNode;
			oldtargetNode=null;
		}
	}	
	//STEP 13: Removes a node from the end of the linkedlist
	private void removeEndNode()
	{
		StdOut.println("Removing End Node");
		Node value = new Node();
		for(Node x=first; x !=null; x=x.next)
		{
			value =x;
		}
		//StdOut.println("BagNode item before remove = "+this.bagNode.item);
		//check if last value is null
		if(isEmpty(value)){
			Node previousNode=value.previous;
			previousNode.next= null;
			//StdOut.println("BagNode item after remove = "+this.bagNode.item);
		}
	}
	//STEP 14: Creates a new randomBag Linked list and removes items from original linkedList
	//surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")
	private void knuthShuffle(int nodeIndex, int size, int type)
	{
			//Item to insert
			Item item = (Item)getSelectedNode(nodeIndex).item;
			//When building the second node, the first
			//node will be filled with a value.
			
			//If bag Item is not null, insert a new node
			if(!(this.bagNode.item==null))
			{
				if(nodeIndex>(getSize(this.first)))//end
				{
					insertFirstNode((Item)item, this.bagNode,3);
					removeEndNode();
				}
				else if(nodeIndex<=1)//beginning
				{
					insertFirstNode((Item)item, this.bagNode, 3);
					removeFirstNode(nodeIndex);
				}
				//node between linkedlist size
				else //between
				{
					insertFirstNode(item, this.bagNode, 3);
					removeTargetNode(nodeIndex);
				}//else
			}//if
			//First Time bagNode item is called it will 
			//be null and has to be filled with a value
			else if (this.bagNode.item==null)
			{
				if(nodeIndex>(getSize(this.first)))//end
				{
					this.bagNode.item=item;
					removeEndNode();
				}
				else if(nodeIndex<=1)//beginning
				{
					this.bagNode.item=item;
					removeFirstNode(nodeIndex);
				}
				//node between linkedlist size
				else 
				{
					this.bagNode.item=item;
					removeTargetNode(nodeIndex);
				}
			}//else if
	}//knuthShuffle
	//STEP 15: The object declaration must declare the type of the generic
	public static void main(String[] args){
		RandomBag1334 <String> linkedList1 = new RandomBag1334<String>("to", "be", "or");
		linkedList1.printList(linkedList1.first);
		//Item,targetPosition, before/after toggle)
		linkedList1.addNode("not",8,1);
		linkedList1.printList(linkedList1.first);
		StdOut.println("********************************************************************");
		linkedList1.createRandomBag();
		StdOut.println("Nodes in LinkedList1:");
		StdOut.println("********************************************************************");
		linkedList1.printList(linkedList1.first);
		StdOut.println("********************************************************************");
		StdOut.println("Nodes in bagList:");
		StdOut.println("********************************************************************");
		linkedList1.printList(linkedList1.bagNode);
		StdOut.println("********************************************************************");
	}//main
}//RandomBag1334