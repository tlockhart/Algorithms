/*----------------------------------------------------------------
 *  Author:        Tony Lockhart
 *  Written:       6/20/2017
 *  Last updated:  9/16/2017
 *
 *  Compilation:   javac -cp .;algs4_ts.jar PercolationTester2.java
 *  Execution:   java -cp .;algs4_ts.jar PercolationTester2
 *
 * Summary: 
 **********
 * The PercolationStats generates a 2_D grid based on inputs.  All squares are first opened, then the program randomly generates
 * blocked and filled squares.  Filled Squares start at the top and attempts to fill each row completely before moving to the
 * next row.  Blocked squares are generated randomly, and a blocked square must opened before it can be filled.  The program hardcodes
 * two inputs in the constructor.  NOTE: The first input is the dimensions of the 2D array.   The second input is the number of program
 * executions.  The program draws the n-by-n grid from the input, and computes the sample mean, sample standard deviation, and the 
 * 95% confidence interval for the percolation threshold.
 *
 * Method:
 **********
 * (A) createNode() creates random coordinates, so they can be flipped from (BLOCKED to OPENED), or (OPENED to FILLED).
 *
 * (B) The nodeChangerRowTracker() method changes the squares from [blocked (0) to open (1)], with flipBlockedNodesToOpen, and from [opened (1) 
 * to filled (2)] with the flipOpenNodeToFilled method. The flipSurroundingOpenSquaresToFilled recursively changes the color of open squares 
 * touching filled squares to Filled.
 * 
 * Input: Dimensions, Number of Executions
 *
 * Output: Prints Mean Average, Variance, Standard Deviation, and Confidence Interval statistics to the command prompt.
 *
 * End Condition: The program stops when the filled blocks reach the bottom of the grid.
 *----------------------------------------------------------------*/

//import java.util.Random;
import java.lang.*;
//import java.awt.Font;
//keep
//import edu.princeton.cs.algs4.*;

public class PercolationTester2{
	private int dimensionHigh;
	private int dimensionLow=0;
	private int statusHigh=27;
	private int statusLow=25;
	private int status;
	private int[][] grid;
	private int row;
	private int col;

	//reset values
	/******************/
	private int fillRow;
	private int filledRowCounter = 0;
	private int openedSquareCounter = 0;
	private int totalFilledSquares = 0;
	private double doubleThreshold=0;
	private boolean percolates = false;
	/***************************/
	float experiments=0;
	
	//Precise Numbers
	/****************************/
	private double mean=0;
	private double variance=0;
	private double deviation=0;
	private double[] doubleThresholds; 
	private double confidenceLow=0;
	private double confidenceHigh=0;
	private double[] runTimes;
	private double runTimeMean=0;
	private double runTimeVariance=0;
	private double runTimeDeviation=0;
	/***************************/
	//Constants
	/***************************/
	private final int BLOCKED = 0;
	private final int OPENED = 25;
	private final int FILLED = 26;
	/***************************/
	
	//QuickUnion
	/*****************************/
	private WeightedQuickUnionUF qf;
	private int squaredDimensionHigh;
	/*****************************/
	//private 
	
	public PercolationTester2(int dimensionHigh,int experiments)
	{
		/*Reset Values*/
		/***************************
		this.fillRow=dimensionHigh-1;
		this.filledRowCounter = 0;
		this.openedSquareCounter = 0;
		this.totalFilledSquares = 0;
		this.floatThreshold = 0;
		***************************/
		checkBound(dimensionHigh);
		//stores the mean value for the number of experiments
		this.doubleThresholds= new double[experiments];
		this.runTimes=new double[experiments];
		this.experiments=experiments;
		this.dimensionHigh = dimensionHigh;
		this.squaredDimensionHigh=this.dimensionHigh*this.dimensionHigh;
		this.grid = new int[this.dimensionHigh][this.dimensionHigh];
		//Plus 2 is for OPENED and FILLED NODES
		/**************************************/
		this.qf = new WeightedQuickUnionUF(this.squaredDimensionHigh+2);
		this.fillRow = dimensionHigh-1;
	}
	public void resetPercolationTester2Values()
	{
		/*Reset Values*/
		/**************/
		this.fillRow=dimensionHigh-1;
		this.filledRowCounter = 0;
		this.openedSquareCounter = 0;
		this.totalFilledSquares = 0;
		this.doubleThreshold = 0;
		this.percolates = false;
		this.status=0;
		this.dimensionHigh = dimensionHigh;
		this.squaredDimensionHigh=this.dimensionHigh*this.dimensionHigh;
		this.grid = new int[this.dimensionHigh][this.dimensionHigh];
		this.qf = new WeightedQuickUnionUF(this.squaredDimensionHigh+2);//two extra elements for OPENED and FILLED
	}
	//create random Nodes for the Linear Array Name and converts them to coordinates for the 2D Grid Array
	public int createNode()
	{
		//CREATE RANDOM COORDINATES
		int index = this.getRandomNum(this.dimensionLow, this.squaredDimensionHigh);
		this.status = this.getRandomNum(statusLow, statusHigh);
		
		//7/14/2017: Convert the linear array node to grid coordinates;
		this.convertLinearArrayNodeToGridCoord(index);
		/**********************************************************/
		//SET LINEAR NODE ARRAY TO OPENED, BUT GRID IS SET TO STATUS
		//FIRST PUT STATUS IN INDEX VALUE
		/**********************************************************/
		if(!this.qf.connected(this.status, index))
		{
			this.qf.union(this.status, index); //put random status (25 or 26) in index
		}
		//(P,Q): Put the value of P into Q (25 is OPENED)
		if(this.qf.connected(index, OPENED))
		{
			if(!this.qf.connected(OPENED, index))
			{
				//StdOut.println("NOT CONNECTED!");
				/********************************************************************************************************/
				//7/14/2017: Because Blocked has the most nodes, and union is going to make it have the value for blocked.
				//Set the Status of Linear Array to Opened
				/********************************************************************************************************/
				this.qf.union(OPENED, index);
			}
		}
		else if(this.qf.connected(index, FILLED))
		{
			if(!this.qf.connected(FILLED, index))
			{
				//StdOut.println("NOT CONNECTED!");
				/*****************************************************************************************************************/
				//7/14/2017: Because Blocked has the most nodes, and WeightedUnion is going to make it have the value for blocked.
				//We only use the Weighted UNION to change values from BLOCKED to OPENED, then we use the Grid to change from
				//OPENED to FILLED.
				/*****************************************************************************************************************/
				this.qf.union(FILLED, index);
			}
		}
		else
		{
			if(!this.qf.connected(BLOCKED, index))
			{
				this.qf.union(BLOCKED, index);
			}
		}
		//StdOut.println("**CREATE NODE: The col = "+this.col+" The row = "+this.row+" Index = "+index+" Actual Status = "+this.status+" INDEX VALUE = "+this.qf.find(index));
		return index;
	}
	//maps random coordinates from createNode to Linear Array in UF
	public int convertGridCoordToLinearNode(int col, int row)
	{
		int index = col+(row*this.dimensionHigh);		
		return index;
	}
	//map random linear array name to Grid coordinates
	public  void convertLinearArrayNodeToGridCoord(int index)
	{
		int col=0;
		int row=0;
		if((index>=0)&&(index<this.dimensionHigh))
		{
			col=index;
			row=0;
		}
		else
		{
			col=index%this.dimensionHigh;
			row=(index-col)/this.dimensionHigh;
		}
		this.col=col;
		this.row=row;
	}
	//Change square from blocked (0) to opened (1)
	public void open(int row, int col)
	{
		checkBounds(row, col);
		this.grid[col][row]=this.status;
		this.drawSquare(col, row, this.grid[col][row]);
		this.openedSquareCounter++;
	}

	//NOTE: [excluded, included)
	public int getRandomNum(int low, int high)
	{
		int value =StdRandom.uniform(high);
        return value;	
	}
	//Prints the grid with blocked (0) values
	public void printBlockedGrid(int source)
	{
			for(int row=0; row<this.dimensionHigh;row++)
			{
				for(int col=0; col<this.dimensionHigh; col++)
				{
					if((this.grid[col][row]>=0)&&(this.grid[col][row]<this.dimensionHigh))
					{
						this.drawSquare(col, row, source);
						this.grid[col][row]=source;
					}
				}
			}//for
	}
	//draws blocked (0), opened (1), and filled (2) squares
	public void drawSquare(int col, int row, int status)
	{
		StdDraw.setXscale(0, this.dimensionHigh*this.dimensionHigh);
		StdDraw.setYscale(0, this.dimensionHigh*this.dimensionHigh);
			if(status==BLOCKED)
			{
				StdDraw.setPenColor(StdDraw.BLACK);
			}
			else if(status==OPENED)
			{
				StdDraw.setPenColor(StdDraw.WHITE);
			}
			else if(status==FILLED)
			{
				StdDraw.setPenColor(StdDraw.GREEN);
			}
		StdDraw.filledSquare(((col*this.dimensionHigh)+(this.dimensionHigh/2)), ((row*this.dimensionHigh)+(this.dimensionHigh/2)), ((this.dimensionHigh)/2));
		StdDraw.show();
		
		/*****************************************************/
		//Set Linear Array to open, but grid set to status.
		/*****************************************************/
		int index =convertGridCoordToLinearNode(col, row);
		if(this.qf.connected(OPENED, index))
		{
			this.qf.union(OPENED, index);
			//StdOut.println("DRAWSQUARE: Row = "+this.row+" Col = "+this.col+" Status = "+this.status+" Index = "+index);
			
		}
		
		//Pause for 4 seconds
		/*try
		{
            Thread.sleep(4000);
		} 
		catch (InterruptedException e) {
		}*/
	}
	
	//Change Open Node To Filled
	public void flipOpenNodeToFilled(int col, int row)
	{
		int tidalWaveCol = col;
		int tidalWaveRow = row;
		
		//recursive call to flip squares from white to green
		flipSurroundingOpenSquaresToFilled(tidalWaveCol, tidalWaveRow);
	}
		//check Left, Above, Right, and Below for filled squares
	public boolean isSurroundingSquareFilled(int col, int row)
	{
		int value = 0;
		boolean isSurroundingSquareFilled = false;
		if((col<=(this.dimensionHigh-1))&&(col>0))
		{
			//Check if next square is on the grid.
			if((this.grid[col-1][row]== FILLED))
			{
				value++;
			}
		}//check if the square will is on the grid.
		if(((row>=0)&&(row<(this.dimensionHigh-1))))
		{
			if(this.grid[col][row+1] == FILLED)
			{
				value++;
			}
		}
		if((col>=0)&&(col<(this.dimensionHigh-1)))
		{
			if(this.grid[col+1][row] == FILLED)
			{
				value++;
			}
		}
		if((row<=(this.dimensionHigh-1))&&(row>0))
		{
			if(this.grid[col][row-1]== FILLED)
			{
				value++;
			}
		}
		
		if(value>0)
		{
			isSurroundingSquareFilled=true;
		}else
			isSurroundingSquareFilled=false;
		
		return isSurroundingSquareFilled;
	}
	public void checkBounds(int row, int col)
	{
		if ((row < 0 || row >= this.dimensionHigh)||(col < 0 || col >= this.dimensionHigh)) 
		{
			throw new IllegalArgumentException("Row = " + row +" Col = "+col+ " is not between 0 and " + (this.dimensionHigh-1));
		}
	}
	public void checkBound(int n)
	{
		if ((n <= 0))
		{
			throw new IndexOutOfBoundsException("index " + n + " is not Greater than 0.");
		}
	}
	public boolean isOpen(int row, int col)
	{
		checkBounds(row, col);
		boolean value=false;
		if(this.grid[col][row] ==this.OPENED)
		{
			value=true;
		}
		return value;
	}
	public boolean isFull(int row, int col)
	{
		checkBounds(row, col);
		boolean value = false;
		if(this.grid[col][row]==FILLED)
			value = true;
		return value;
	}
	public boolean percolates()
	{
		boolean value = false;
		if(this.percolates == true)
			value=true;
		return value;
	}
	//nodeChanger changes the colors from blocked (0) to open (1)and from opened (1) to filled (2)
	public void nodeChangerRowTracker (/*7/08/2017 int row*/)
	{
			//StdOut.println("B4 NODECHANGE: "+"openedSquareCounter = "+this.openedSquareCounter+"  filledRowCounter= "+this.filledRowCounter+" TotalFilledSquares = "+totalFilledSquares+" FillRow= "+this.fillRow+" Col = "+this.col+", Row = "+this.row+" Status = "+this.status+" GRID_Value = "+this.grid[this.col][this.row]);
			if((this.filledRowCounter==this.dimensionHigh)&&(this.fillRow==this.row)&&(this.status==FILLED))
			{
				this.filledRowCounter=0;
				this.fillRow--;
			}
			//(2)increment number of filledSquares (2), decrement opened Squares (1), when changing from blocked (0) to Filled (2), or Opened(1) to Filled(2)
			else if(isOpenNodeReadyToBeFilled(this.col, this.row))
			{				
				//Change all open(1) squares touching a filled (2)square to filled
				flipOpenNodeToFilled(this.col, this.row);
			}
			/******************************************************************************/
			//(3)increment num of openedSquares, when changing blocked (0) squares to open (1)
			/******************************************************************************/
			else if (isBlockedNodeReadyToBeOpened(this.col, this.row))
			{
				flipBlockedNodeToOpened(this.col, this.row);
			}
			//StdOut.println("*NodeChange END:   filledRowCounter= "+this.filledRowCounter+", FillRow= "+this.fillRow+", TotalFilledSquares = "+this.totalFilledSquares+", OpenedSquareCounter = "+this.openedSquareCounter+", Col = "+this.col+", Row = "+this.row+", Grid Status = "+this.grid[row][col]+", Status = "+this.status);
	}//nodeChangerRowTracker
	//check if target node is open (1), and instance var status is set to fill (2)
	public boolean isOpenNodeReadyToBeFilled(int col, int row)
	{
		boolean value = false;
		if((this.filledRowCounter<this.dimensionHigh)&&(this.fillRow==this.row)&&(this.status==this.FILLED)&&(this.grid[this.col][this.row]==this.OPENED))
		{
			value = true;
			//StdOut.println("ISOPENNODEREADYTOBEFILLED = True, Grid Value = 25 (open)");
		}
		return value;
	}
	//check if target node is blocked(0), and instance var status is set to Open (1)
	public boolean isBlockedNodeReadyToBeOpened(int col, int row)
		{
			boolean value = false;
			if((this.status==OPENED)&&(this.grid[col][row]==BLOCKED))
				value = true;
			return value;
		}
	public void flipBlockedNodeToOpened(int col, int row)
	{
		//Change square from blocked (0) to opened (1)
		open(row, col);
		
		//Check if there are any filled squares around the newest open (1) square.
		if(isSurroundingSquareFilled(col, row))
		{
			//Call flip method to flip OPENED squares surrounding the latest FILLED squares
			//If found change them to FILLED
			flipSurroundingOpenSquaresToFilled(col, row);
		}
	}
	public int numberOfOpenSites()
	{
		return this.openedSquareCounter+this.totalFilledSquares;
	}
	
	//Checks surrounding squares and changes colors as necessary
	public void flipSurroundingOpenSquaresToFilled(int col, int row)
	{
		//Change status to Filled
		int status=FILLED;
		//If the target Square is BLOCKED or OPENED
		while((col<this.dimensionHigh)&&(col>=0)&&(row>=0)&&(row<this.dimensionHigh)&&(isOpen(row, col)))
		{
			if(isOpen(row, col))//original square = OPENED, about to change to FILLED
			{
				/******************************************************/
				//07/08/2017
				if(this.fillRow==row)
				{
					//increment the count of filled squares in the row
					/*************************************************/
					this.filledRowCounter++;
				}
				//increment running total of filledSquares
				this.totalFilledSquares++;//keeps running total of filled squares
				
				//subtract openRowCounter instance when opened status is changed to Filled status (2)
				this.openedSquareCounter--;
				
				//Change status to filled
				this.grid[col][row]=status;
				
				//draw filled squares
				this.drawSquare(col, row, this.grid[col][row]);
				//StdOut.println("Flipping: "+"Col = "+col+" Row = "+row+" GRIDSTATUS = "+this.grid[col][row]+" TotalFilledSquares = "+totalFilledSquares+" FilledRowCounter = "+filledRowCounter);
			}//if
			//Executes recursive after grid value changed from 1 to 2
			if(isFull(row, col))
			{
				//check LEFT Square
				if((col<=(this.dimensionHigh-1))&&(col>0)){flipSurroundingOpenSquaresToFilled(col-1, row);}
				//CHECK ABOVE SQUARE
				if((row>=0)&&(row<(this.dimensionHigh-1))){flipSurroundingOpenSquaresToFilled(col, row+1);}
				//CHECK RIGHT
				if((col>=0)&&(col<(this.dimensionHigh-1))){flipSurroundingOpenSquaresToFilled(col+1, row);}
				//CHECK BELOW
				if((row<=(this.dimensionHigh-1))&&(row>0)){flipSurroundingOpenSquaresToFilled(col, row-1);}
				//7/08  - Condition for percolates being true
				/*********************************************************/
				if(row==0)
				{
					this.percolates=true;
				}
				
			}//if
		}//while
	}//flipSurroundingOpenSquaresToFilled
	public String threshold()
	{
		double PercolationTester2Threshold = (double)(this.totalFilledSquares+this.openedSquareCounter)/(double)(this.dimensionHigh*this.dimensionHigh);
		String formatedString = String.format(java.util.Locale.US,"%.2f",PercolationTester2Threshold);
		this.doubleThreshold = PercolationTester2Threshold;
		return formatedString;
	}
	public double mean(double[] thresholds)
	{
		double mean=StdStats.mean(thresholds);
		StdOut.println("The Mean Average = "+mean);
		return mean;
	}
	public double variance(double[] doubleThresholds)
	{
		double variance = StdStats.var(doubleThresholds);
		StdOut.println("The Variance = "+variance);
		return variance;
	}
	public double stddev(double[] doubleThresholds)
	{
		double deviation = StdStats.stddev(doubleThresholds);
		StdOut.println("The Standard Deviation = "+deviation);
		return deviation;
	}
	public double confidenceLo()
	{
		this.confidenceLow=this.mean-(1.96*(this.deviation)/(Math.sqrt(this.experiments)));
		return this.confidenceLow;
	}
	public double confidenceHi()
	{
		this.confidenceHigh=this.mean+(1.96*(this.deviation)/(Math.sqrt(this.experiments)));
		return this.confidenceHigh;
	}
	public void printConfidenceInterval()
	{
		this.confidenceLo();
		this.confidenceHi();
		StdOut.println("95% confidence interval = ["+this.confidenceLow+", "+this.confidenceHigh+"]");
	}
	
	public void drawText(String text)
	{
		 //Font font = new Font("Arial", Font.BOLD, 40);
		 StdDraw.setPenColor(StdDraw.MAGENTA);
		// StdDraw.setFont(font);
		 StdDraw.text(10, 1, "Threshold is: "+text);
		 //StdDraw.text(145, 10, "Threshold is: "+text);
	}
	public static void main(String[] args)
	{
		//7/11/2017: Read N For Test Data only
		/*********************/
		double runTime;
			//7/11/2017: For test Data Only: Args (dimensionHigh)
			//Args: (N, NumOfExperiments) - N,in N by N grid, Number of Experiments to run
			PercolationTester2 list= new PercolationTester2(5, 30);
			
			
		for (int i = 0; i<list.experiments; i++)
		{
			Stopwatch watch=new Stopwatch();
			//Reset the values for a new experiment
			list.resetPercolationTester2Values();
			//Prints Grid 2D Grid of BLOCKED (0) NODES
			list.printBlockedGrid(0);

			/*7/13/2017: 
			************************/
			//for (int i = 0; i<40; i++)
			while(!list.percolates())
			{
				//Picks a random linear Array element, and makes it open
				//The value is then converted to a 2D Grid and drawn to screen
				list.createNode();
				//StdOut.println("The value of 26 after = "+list.qf.find(26));
				list.nodeChangerRowTracker();//7/14/2017: Not going in here
			}//while
			list.drawText(list.threshold());
			list.doubleThresholds[i]=list.doubleThreshold;
			//Runtime Stats
			/**************/
			
			runTime=watch.elapsedTime();
			list.runTimes[i]=runTime;
		}//for
		StdOut.println("The number of open and filled sites are: "+list.numberOfOpenSites());
		StdOut.println("THRESHOLD STATISTICS");
		StdOut.println("********************");
		list.mean=list.mean(list.doubleThresholds);
		list.variance=list.variance(list.doubleThresholds);
		list.deviation=list.stddev(list.doubleThresholds);
		list.printConfidenceInterval();
		StdOut.println("");
		
		StdOut.println("RUNTIME STATISTICS");
		StdOut.println("******************");
		list.runTimeMean=list.mean(list.runTimes);
		list.runTimeVariance=list.variance(list.runTimes);
		list.runTimeDeviation = list.stddev(list.runTimes);
	}//main
}//PercolationTester2