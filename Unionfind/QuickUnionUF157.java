//Compile: javac -cp .;algs4_ts.jar QuickUnionUF157.java
//Run: java -cp .;algs4_ts.jar QuickUnionUF157 <tinyUF.txt
//
//Summary: 
//Program reads a file and stores the integer values in the corresponding array 
//index.  This way the indexes and the array values are the same by default.  
//The goal is to check if the array index values are the same (connected).  If
//so, do nothing, otherwise connect them using the quickUnion algorithm's union method. 
//
//Important: The number read will be represented as the array indexes, while
//the site id is the actual value of the array element.  Gradually the 
//program connects the numbers by site id, so we can determine if the 
//sites are linked.  
//
//Algorithms: The program implements the quick-union algorithms in the
//QuickUnionUF class.  Quick-union and quick-find are used the check
//if an array element is connected, or if they are not connected, it will connect them
//via the union operation.
//
//Explanation:
//See page 224 quick-union has a 10 digit array (parent)for each site.  Initially the
//10 digit array values are from 0-9.  Each time two sites (P and Q) are read,
//we check to see if the value of parent[p]==parent[q].  If they are equal we do nothing,
//if they are not equal, then change the value of parent[p], so parent[p] = parent[q].
//
//
//A contructor does not have a return type
	public class QuickUnionUF157
	{
		int count;
		int N;
		int[] parent;
		int siteCount=0;
		int change = 0;
		String connections = new String();
		public QuickUnionUF157(int N)
		{ 
		// Initialize component parent array.
			//this.count = N;
			/*this.N=N;
			this.parent=new int[N];
			for(int i = 0; i<N; i++)
			{
				this.parent[i]=i;
			}*/
			this.parent=new int[N];
			for(int i=0; i<N; i++) {this.parent[i]=i;}
		}
		public int count()
		{ 
			return this.count; 
		}
		/******************/
		//Quick Union p224
		/******************/
		public int find(int p)
		//public int root(int i)
		{
			//return this.parent[p]; 
			/*while(p!=parent[p]) p=parent[p];
			return p;*/
			//chase the parent node, whparent parent[i] = i
			validate(p);
			while(p!=this.parent[p]) {p=this.parent[p];}
			return p;
		}
		 private void validate(int p) {
			int n = parent.length;
			if (p < 0 || p >= n) {
				throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
			}
		}
		/******************/
		//Quick Union p224
		/******************/
		//Replace components containing two objects with their union.
		public void union(int p, int q)
		{ // Put p and q into the same component.
				/*int i = find(p);
				int j= find(q);
				if(i==j) return;
				this.parent[i] = j;
				this.count--;*/
				int rootP = find(p);
				int rootQ = find(q);
				//parent[i] set to value of root of j
				this.parent[rootP] = rootQ;
				this.count--;
		}
		/******************/
		//Quick Union p224
		/******************/
		//Check if two objects are in the same component
		public boolean connected(int p, int q)
		{ 
			return find(p) == find(q); 
		}
		//@Overrparente
		public void toString(int p, int q, int toggle)
		{
			//first time both toggle and siteCount will be 0
			if((toggle==0)&&(siteCount==0))
			{	//initialize sites before union 1-9
				for(int i = 0; i<this.parent.length; i++)
				{
					
					//connections+=" "+this.parent[i];
					connections+=" "+this.parent[i];
					StdOut.print("parent = "+this.parent[i]);
				}
				StdOut.println();
				StdOut.print("p"+" q");
				StdOut.print(connections);
			}
			//Second or more time siteCount will be 1 or greater, as site  
			//parents have been compared and siteCount value incremented.  The
			//value of sites should be the values of parent, not the original
			//instantiated value.  Just bring down what was already stored in
			//connections.
			else if((toggle==0)&&(siteCount>=1))
			{	//Update sites after Union
				connections = new String();
				for(int i = 0; i<this.parent.length; i++)
				{
					connections+=" "+this.parent[i];
					
				}
				StdOut.println();
				StdOut.print("p"+" q");
				StdOut.print(connections);
				siteCount++;
			}
			//Called after the union has been done to change the make
			//sites connected.  Print if component has been changed or not.
			if ((toggle==1)&&(this.change==0))
			{
				//Bring down what was stored in Site from P Q row
				StdOut.println();
				//StdOut.println("nochange");
				StdOut.print(p+" "+q);
				StdOut.print(connections);
				siteCount++;
			}
			else if(((toggle==1)&&(this.change==1)))
			{
				//Bring down what was stored in Site from P Q row
				StdOut.println();
				StdOut.print(p+" "+q);
				StdOut.print(connections);
				siteCount++;
			}	
		}	
		//See page 224 (quick-union)
		//Explanation:
		//See page 225 quick-union has a 10 digit array (parent)for each site.  Initially the
		//10 digit array values are from 0-9.  Each time two sites (P and Q) are read,
		//we check to see if the value of parent[p]==parent[q].  If they are equal we do nothing,
		//if they are not equal, then change the value of parent[p], so parent[p] = parent[q].
		public static void main(String[] args)
		{ // Solve dynamic connectivity problem on StdIn.
		  //Read the first entry from the external file
			int N = StdIn.readInt(); // Read number of sites.
			int p=0;
			int q=0;
			StdOut.println("The number of values = "+N);
			
			/*To instantiate an inner class, you must first instantiate the outer class. 
			  Then, create the inner object within the outer object with this syntax:
			  OuterClass.InnerClass innerObject = outerObject.new InnerClass();*/
			//UF157 u2 = new UF157(N);
			//QuickFindUF uf = this.new QuickFindUF(N); // Initialize N components.
			
			QuickUnionUF157 uf =new QuickUnionUF157(N);
			while (!StdIn.isEmpty())
			{
				//Read two integers
				//Reads the next token from standard input, parses it as an integer, and returns the integer.
				p = StdIn.readInt();
				q = StdIn.readInt(); // Read pair to connect.
				
				//toggle argument of 0 in toString indicates parent[] array before union
				uf.toString(p, q, 0);
				if (uf.connected(p, q)){
					uf.change=0;//site component 0 means no connection changed
					//uf.toString(p, q, 0);
					uf.toString(p, q, 1);
					continue; // Ignore if connected.
				}
				uf.change=1;//site component Changed
				uf.union(p, q); // Combine components
				//toggle argument of 1 in toString() indicates parent[] array after union
				uf.toString(p, q, 1);
			}
			StdOut.println();
			//StdOut.println(uf.count() + " components that sites are connected to");				
		}//main
}