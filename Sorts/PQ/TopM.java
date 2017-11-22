 /**  <p><b>Compilation:</b>   javac -cp .;algs4_sts.jar; Transaction.java UnorderedArrayMaxPQ.java Stack.java TopM.java</p>
 *  <p><b>Execution 1:</b>     java -cp ../;.;algs4_sts.jar; pq.TopM 4 <tinyBatch.txt</p>
 */
 package pq;
 
 import exercises.*;
 
 public class TopM
{
	public static void main(String[] args)
	{
		int M = Integer.parseInt(args[0]);
		//MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
		UnorderedArrayMaxPQ<Transaction> pq = new UnorderedArrayMaxPQ<Transaction>(M + 1);
		while (StdIn.hasNextLine())
		{
			pq.insert(new Transaction(StdIn.readLine()));
			if(pq.size() > M)
				pq.delMin();
		}
		Stack<Transaction> stack = new Stack<Transaction>();
		while(!pq.isEmpty()) stack.push(pq.delMin());
		for(Transaction t: stack) StdOut.println(t);
	}
}