/*----------------------------------------------------------------
 *  Author:        Tony Lockhart
 *  Written:       6/20/2017
 *  Last updated:  9/16/2017
 *
 *  Compilation:   javac -cp .;algs4_ts.jar PercolationVisualizer.java
 *  Execution:   java -cp .;algs4_ts.jar PercolationVisualizer <input5.txt
 *
 * Summary: 
 **********
 * The PercolationVisualizer Program reads a data file, and generates a 2D array, based on inputs.  All squares are first opened, 
 * then the program randomly generates blocked and filled squares.  Filled Squares start at the top and attempts to fill each row 
 * completely before moving to the next row.  Blocked squares are generated randomly, and a blocked square must opened before it can be filled.  
 * The program hardcodes two inputs in the constructor.  NOTE: The first input is the dimensions of the 2D array.   The second input is the 
 * number of program executions.  The program draws the n-by-n grid from the input, and computes the percolation threshold.
 *
 * Method:
 **********
 * (A) createNode() creates random coordinates, so they can be flipped from (BLOCKED to OPENED), or (OPENED to FILLED).
 *
 * (B) The nodeChangerRowTracker() method changes the squares from [blocked (0) to open (1)], with flipBlockedNodesToOpen, and from [opened (1) 
 * to filled (2)] with the flipOpenNodeToFilled method. The flipSurroundingOpenSquaresToFilled recursively changes the color of open squares 
 * touching filled squares to Filled.
 * 
 * Input: Hardcoded constructor inputs: Dimensions, Number of Executions
 *
 * Output: Prints Mean Average, Variance, Standard Deviation, and Confidence Interval statistics to the command prompt.
 *
 * Problems: Green squares are not generated often, because filled squares are generated randomly.
 *
 * End Condition: The program stops when the filled blocks reach the bottom of the grid.
 *
 * Files: The first element in the file is the dimension, the other numbers are rows and cols in sequence.
 *----------------------------------------------------------------*/

import java.util.Random;
import java.lang.Math;
import java.awt.Font;

public class PercolationVisualizer{
	private int dimensionHigh;
	private int dimensionLow=0;
	private int statusHigh=3;
	private int statusLow=1;
	private int status;
	private int[][] grid;
	private int row;
	private int col;
	private int virtualTop=1;
	private int virtualBottom=0;
	
	
	//private int count=0;
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
	private double confidenceInterval1=0;
	private double confidenceInterval2=0;
	
	/***************************/
	//Constants
	/***************************/
	private final int BLOCKED = 0;
	private final int OPENED = 1;
	private final int FILLED = 2;
	//private 
	
	public PercolationVisualizer(int dimensionHigh)
	{
		/*Reset Values*/
		/***************************
		this.fillRow=dimensionHigh-1;
		this.filledRowCounter = 0;
		this.openedSquareCounter = 0;
		this.totalFilledSquares = 0;
		this.doubleThreshold = 0;
		***************************/
		//stores the mean value for the number of experiments
		//this.doubleThresholds= new double[experiments];
		//this.experiments=experiments;
		this.dimensionHigh = dimensionHigh;
		/*this.dimensionLow = dimensionLow;
		this.statusHigh = statusHigh;
		this.statusLow = statusLow;*/
		this.grid = new int[this.dimensionHigh][this.dimensionHigh];
		this.fillRow = dimensionHigh-1;
		//this.printBlockedGrid();
	}
	public void resetPercolationVisualizerValues()
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
		/***************************/
		//this.grid = new int[this.dimensionHigh][this.dimensionHigh];
		
		this.dimensionHigh = dimensionHigh;
		this.grid = new int[this.dimensionHigh][this.dimensionHigh];
		//this.fillRow = dimensionHigh-1;
	}
	//createNodes creates random nodes
	public void createNode()
	{
		this.row = this.getRandomNum(dimensionHigh, dimensionLow);
		this.col = this.getRandomNum(dimensionHigh, dimensionLow);
		this.status = this.getRandomNum(statusHigh, statusLow);
		
		//StdOut.println("CREATE NODE CALLED: The col = "+col+" The row = "+row+" The status = "+status);
	}
	public void open(int row, int col)
	{
		this.row = (this.dimensionHigh-1)-row;//flip rows, so coordinates (0,0) is at the top of the coordinate system
		this.col = col;
		//this.status = this.getRandomNum(statusHigh, statusLow);
		this.status=OPENED;
		StdOut.println("CREATE NODE CALLED: The col = "+col+" The row = "+this.row+" The status = "+status);
		openedSquareCounter++;
		this.drawSquare(col, row, this.status);
	}
	//creates random number from 0-4
	public int getRandomNum(int high, int low)
	{
		Random randomItem = new Random();
		//generate numbers from 1-length
		int searchItem = (randomItem.nextInt(high-low)+low);
		return searchItem;
	}//getRandomNode
	//Prints the grid with blocked (0) values
	public void printBlockedGrid()
	{
			int status =0;
			StdDraw.setXscale(0, this.dimensionHigh*this.dimensionHigh);
			StdDraw.setYscale(0, this.dimensionHigh*this.dimensionHigh);
			StdDraw.setPenColor(StdDraw.BLACK);
			for(int row=0; row<this.dimensionHigh; row++)
			{
				for (int col=0; col<this.dimensionHigh; col++)
				{
					this.drawSquare(col, row, this.status);
					this.grid[col][row]=status;
					//StdOut.println("The col = "+col+" The row = "+row+" The status = "+grid[col][row]);
				}//for
			}//for
	}
	//draws blocked (0), opened (1), and filled (2) squares
	public void drawSquare(int col, int row, int status)
	{
		StdDraw.setPenColor(StdDraw.BLACK);
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
		
		//Pause for 4 seconds
		/*try
		{
            //Thread.sleep(4000);
		} 
		catch (InterruptedException e) {
		}*/
	}
	//check if target node is open (1), and instance var status is set to fill (2)
	public boolean isOpenNodeReadyToBeFilled(int col, int row)
	{
		boolean value = false;
		if((this.filledRowCounter<this.dimensionHigh)&&(this.fillRow==this.row)&&(this.status==2)&&(this.grid[this.col][this.row]==1))
		{
			value = true;
		}
		return value;
	}
	//Change Open Node To Filled
	public void flipOpenNodeToFilled(int col, int row)
	{
		/************************************************************/
		int tidalWaveCol = col;
		int tidalWaveRow = row;
		
		//recursive call to flip squares from white to green
		flipSurroundingOpenSquaresToFilled(tidalWaveCol, tidalWaveRow);
		/************************************************************/
		
		//StdOut.println("GRID = 1, going to 2: "+" Grid = "+this.grid[this.col][this.row]+" Dimension = "+this.dimensionHigh+" TotalFilledSquares = "+this.totalFilledSquares);
		//StdOut.println("GRID = 1, going to 2:"+" filledRowCounter= "+this.filledRowCounter+" TotalFilledSquares = "+totalFilledSquares+" FillRow= "+this.fillRow+" Col = "+this.col+", Row = "+this.row+" Status = "+this.status);
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
	public boolean isOpen(int row, int col)
	{
		boolean value=false;
		if(this.grid[col][row] ==OPENED)
			value=true;
		return value;
	}
	public boolean isFull(int row, int col)
	{
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
		this.grid[col][row]=this.status;
		this.drawSquare(col, row, this.grid[col][row]);
		
		this.openedSquareCounter++;
		//StdOut.println("GRID = 0, changing to open (1): Does grid = 2; "+" Grid Status= "+this.grid[col][row]+" Dimension = "+this.dimensionHigh+" TotalFilledSquares = "+this.totalFilledSquares);
		//StdOut.println("GRID = 0, changing to open (1):  *openedSquareCounter= "+this.openedSquareCounter+" TotalFilledSquares = "+totalFilledSquares+" FillRow= "+this.fillRow+" Col = "+col+", Row = "+row+" GRID = "+this.grid[col][row]);
		
		//Check if there are any filled squares around the newest open (1) square.
		if(isSurroundingSquareFilled(col, row))
		{
			//Call flip method to flip opened (1) squares surrounding the latest opened square(1) for Filled (2) squares
			//If found change them to opened.
			flipSurroundingOpenSquaresToFilled(col, row);
		}
	}
	//nodeChanger changes the colors from blocked (0) to open (1)and from opened (1) to filled (2)
	public void nodeChangerRowTracker (/*7/08/2017 int row*/)
	{
			//StdOut.println("NEW GENERATED NUM: Does grid = 2; "+" Grid = "+this.grid[this.col][this.row]+" Dimension = "+this.dimensionHigh);
			//StdOut.println("NEW GENERATED NUM: "+"openedSquareCounter = "+this.openedSquareCounter+"  filledRowCounter= "+this.filledRowCounter+" TotalFilledSquares = "+totalFilledSquares+" FillRow= "+this.fillRow+" Col = "+this.col+", Row = "+this.row+" Status = "+this.status);
			
			//(1)reset filledRow Once row is full of open squares
			if((this.filledRowCounter==this.dimensionHigh)&&(this.fillRow==this.row)&&(this.status==FILLED))
			{
				this.filledRowCounter=0;
				this.fillRow--;
				//StdOut.println("DECR FillRowCounter: Does grid = 2; "+" Grid = "+this.grid[this.col][this.row]+" Dimension = "+this.dimensionHigh+" TotalFilledSquares = "+this.totalFilledSquares);
				//StdOut.println("DECR FillRowCounter:  filledRowCounter= "+this.filledRowCounter+" TotalFilledSquares = "+totalFilledSquares+" FillRow= "+this.fillRow+" Col = "+this.col+", Row = "+this.row+" Status = "+this.status);
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
	}//nodeChangerRowTracker
	
	//Checks surrounding squares and changes colors as necessary
	public void flipSurroundingOpenSquaresToFilled(int col, int row)
	{
		//Change status to Filled
		int status=2;
		//If the target Square is blocked (0) or open (1)
		//StdOut.println("BEFORE WHILE: "+" GRID = "+this.grid[col][row]+" Dimension = "+this.dimensionHigh);
		//StdOut.println("BEFORE WHILE: "+"openedSquareCounter = "+this.openedSquareCounter+"  filledRowCounter= "+this.filledRowCounter+" FillRow= "+this.fillRow+" COL = "+col+", ROW = "+row+" GRID = "+this.grid[this.col][this.row]);
		while((col<this.dimensionHigh)&&(col>=0)&&(row>=0)&&(row<this.dimensionHigh)&&(isOpen(row, col)))
		{
			if(isOpen(row, col))//original square =1, about to change to 2
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
					//StdOut.println("After Status 1 flipped to 2: "+"Col = "+col+" Row = "+row+" GRIDSTATUS = "+this.grid[col][row]+" TotalFilledSquareCounter = "+this.totalFilledSquares);
				}
				
			}//if
		}//while
	}//flipSurroundingOpenSquaresToFilled
	public String threshold()
	{
		double PercolationVisualizerThreshold = (double)(this.totalFilledSquares+this.openedSquareCounter)/(double)(this.dimensionHigh*this.dimensionHigh);
		//int PercolationVisualizerModulus = (this.totalFilledSquares+this.openedSquareCounter)%(this.dimensionHigh*this.dimensionHigh);
		String formatedString = String.format(java.util.Locale.US,"%.2f",PercolationVisualizerThreshold);
		//this.doubleThreshold = Double.parseDouble(formatedString);
		this.doubleThreshold = PercolationVisualizerThreshold;
		return formatedString;
	}
	public double mean()
	{
		double sum =0;
		for(int i=0; i<this.experiments; i++)
		{
			sum+=this.doubleThresholds[i];
		}
		this.mean=sum/experiments;
		StdOut.println("The Mean Average = "+this.mean);
		return this.mean;
	}
	public double variance()
	{
		double sum=0;
		for (int i=0; i<this.experiments; i++)
		{
			sum+=Math.pow((this.doubleThresholds[i]-this.mean), 2);
			//StdOut.println(" (threshold) "+this.doubleThresholds[i]+" (-mean)"+this.mean+" ^2 = ");
		}
		this.variance = sum/(this.experiments-1);
		StdOut.println("The Variance = "+this.variance);
		return this.variance;
	}
	public double deviation()
	{
		this.deviation=Math.sqrt(this.variance);
		StdOut.println("The Standard Deviation = "+this.deviation);
		return this.deviation;
	}
	public void confidenceInterval()
	{
		this.confidenceInterval1=this.mean-(1.96*(this.deviation)/(Math.sqrt(this.experiments)));
		this.confidenceInterval2=this.mean+(1.96*(this.deviation)/(Math.sqrt(this.experiments)));
		StdOut.println("The Confidence Interval = ["+this.confidenceInterval1+", "+this.confidenceInterval2+"]");
	}
	public void drawText(String text)
	{
		 Font font = new Font("Arial", Font.BOLD, 40);
		 StdDraw.setPenColor(StdDraw.MAGENTA);
		 StdDraw.setFont(font);
		 //StdDraw.text(10, 1, "Threshold is: "+Float.toString(text));
		 //StdDraw.text(35, 10, "Threshold is: "+text);
		 StdDraw.text(145, 10, "Threshold is: "+text);
	}
	public static void main(String[] args)
	{
		//WeightedQuickUnion 
		/*******************/
		//WeightedQuickUnionUF uf=new WeightedQuickUnionUF(list.dimensionHigh);
		
		//7/11/2017: Read N For Test Data only
		/*********************/
		int dimensions = StdIn.readInt();
		

			//7/11/2017: For test Data Only: Args (dimensionHigh)
			//Args: (N, NumOfExeperiments) - N,in N by N grid, Number of Experiments to run
			PercolationVisualizer list= new PercolationVisualizer(dimensions);
			
			//list.resetPercolationVisualizerValues();
			list.printBlockedGrid();
			
			//7/11/2017 for Random input: while(!list.percolates())
			//Phase 1:  Read in the test grid TEST DATA ONLY
			while(!StdIn.isEmpty())
			{
				int p = StdIn.readInt()-1;
				int q = StdIn.readInt()-1;
				
				//createNodes creates random nodes, does not change this.grid values, only sets instance var col, row, and status
				//7/11/2017 for random sites: list.createNode();
				list.open(p, q);
				
				//nodeChangerRowTracker changes the colors from black to white and white to green
				list.nodeChangerRowTracker();
			}
			
			StdOut.println("Perculates = TRUE");
			list.drawText(list.threshold());
			StdOut.println("The float threshold is: "+list.doubleThreshold);
	}//main
}//PercolationVisualizer