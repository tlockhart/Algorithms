package sorts;
//TOny

//Import classes from algs4
//import exercises.*;

public class Transaction2422 implements Comparable<Transaction2422>
{
	/*private int number;
	
	public Transaction2422(int num)
	{
		this.number = num;
	}
	public int getNumber()
	{
		return this.number;
	}
	public int compareTo(Transaction2422 that)
	{
		return Integer.compare(this.number, that.number);
	}*/
	private String number;
	public Transaction2422(String num)
	{
		this.number = num;
	}
	public String getNumber()
	{
		return this.number;
	}
	public int compareTo(Transaction2422 that)
	{
		return this.number.compareTo(that.number);
	}
}