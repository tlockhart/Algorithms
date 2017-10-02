//Inserts a node at the end of a linkedlist
//javac -cp .;algs4_ts.jar DeleteLastNode1319.java
//java -cp .;algs4_ts.jar DeleteLastNode1319
public class DeleteLastNode1319<Item>{
	Node first = new Node();
	Node second = new Node();
	Node third = new Node();
	//NOTE: Constructor arguments have to have real values, they are not generic
	//surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")
	public DeleteLastNode1319(String firstItem, String secondItem, String thirdItem){
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
	//Step3: define node abstraction
	private class Node<Item>
	{
		//All the instance variables are initialized to null
		Item item;
		Node next;
		Node previous;
	}
	//STEP4: When inserting an item, pass the generic type
	//surpress the unchecked cast warnings for Java Generics
	@SuppressWarnings("unchecked")
	private void insertNode(Item item){
		//private void insertNode(String item){
		//copy empty first node into oldFirstNode
		Node oldFirst=this.first;
		
		//Re-initialize first Node
		this.first = new Node();
		
		//Set new first Node item to what was sent in the args
		this.first.item = item;
		
		//Set first Node pointer to oldFirstNode
		this.first.next = oldFirst;
	}
	private void deleteNode()
	{
		Node value = new Node();
		for(Node x=first; x !=null; x=x.next)
		{
			value =x;
		}
		if(valueCompare(value)){
			value.previous.next = null;
			//StdOut.println("Set value to null");
		}
	}	
	private boolean valueCompare(Node x)
	{
		StdOut.println("X Next = "+x.next);
		return (x.next == null);	
	}
	private void traverseNode()
	{
		for(Node x=first; x !=null; x=x.next)
		{
		StdOut.println("X = "+x.item);
		}
	}
	public static void main(String[] args){	
		//STEP 5: The object declaration must declare the type of the generic
		DeleteLastNode1319 <String> nodes = new DeleteLastNode1319<String>("to", "be", "or");
		nodes.insertNode("first");
		nodes.traverseNode();
		nodes.deleteNode();
		nodes.traverseNode();
	}
}