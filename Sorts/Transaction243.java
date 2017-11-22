package sorts;
//TOny

//Import classes from algs4
//import exercises.*;

public class Transaction243 implements Comparable<Transaction243>
{
	private int number;
	
	public Transaction243(int num)
	{
		this.number = num;
	}
	public int getNumber()
	{
		return this.number;
	}
	public int compareTo(Transaction243 that)
	{
		return Integer.compare(this.number, that.number);
	}
}