package sorts;
//TOny

//Import classes from algs4
//import exercises.*;

public class Transaction2433 implements Comparable<Transaction2433>
{
	/*private int number;
	
	public Transaction2433(int num)
	{
		this.number = num;
	}
	public int getNumber()
	{
		return this.Number;
	}
	public int compareTo(Transaction2433 that)
	{
		return Integer.compare(this.number, that.number);
	}*/
	private String job;
	public Transaction2433(String value)
	{
		this.job = value;
	}
	public String getJob()
	{
		return this.job;
	}
	public void setjob(String value)
	{
		this.job = value;
	}
	public int compareTo(Transaction2433 that)
	{
		int value = 0;
		try{
			if((that.job == null)&&(this.job==null))
			{
				value = 0;
			}
			else if(this.job==null)
			{
				value = 1;
			}
			else if(that.job==null)
			{
				value = -1;
			}
			else
				value = this.job.compareTo(that.job);
		}
		catch(Exception e)
		{
			value = 0;
		}
	return value;
	}
}