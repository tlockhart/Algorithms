package unionfind;


//No API's can be imported accept java.lang and the algs APIs.
import java.lang.*;
//Import classes from algs4
import exercises.*;
//import edu.princeton.cs.algs4.*;
 
 /**
 *  Written:       7/20/2017
 *  Last updated:  7/20/2017
 *
 *  <p><b>Requirement:</b> Class must be in <b>unionfind</b> package and <b>exercises.*</b> and <b>java.lang.*</b> must be imported.</p>
 *  <p><b>Javadoc:</b> javadoc -author -version -private -classpath .;algs4_uf.jar; -d .\javadoc Percolation.java</p>
 *  <p><b>Compilation:</b>   javac -cp .;algs4_uf.jar; Percolation.java</p>
 *  <p><b>Execution 1:</b>     java -cp ../;.;algs4_uf.jar; unionfind.Percolation &#60;input5.txt</p>
 *  <p><b>Execution 2:</b>     java -cp ../;.;algs4_uf.jar; unionfind.Percolation &#60;input10.txt</p>
 *  <p><b>Execution 3:</b>     java -cp ../;.;algs4_uf.jar; unionfind.Percolation &#60;jerry47.txt</p>
 *  <p><b>Execution 4:</b>     java -cp ../;.;algs4_uf.jar; unionfind.Percolation &#60;sedgewick60.txt</p>
 *  <p><b>Execution 5:</b>     java -cp ../;.;algs4_uf.jar; unionfind.Percolation &#60;wayne98.txt</p>
 *
 *  <p><b>Summary:</b> The Percolation Program reads a data file, into a 2D array.  The first
 *	number in the file is the dimensions of the 2D array.  The dimension is passed 
 *  into the percolation constructor.  In order to workaround accepting input with 
 *  a col and row starting at (1,1), a 1 is added to the dimension, to produce an 
 *  extra row and column, with nothing in the 0 row or 0 column.  The printBlockedGrid() 
 *  method is called to set the 2-D coordinates to BLOCKED and draw them. The open() 
 *  method then reads the row, col combination from the input file.  For each set of  
 *  coordinates read, they must be assigned to instance members this.col and this.row and the 
 *  this.status variable (grid[col][row] must be assigned the value BLOCKED before 
 *  calling the nodeChangerRowTracker() method.  The open() method (should use Weighted API)
 *  flips the row coordinates to map them to the StdDraw coordinates.  For example, to map 
 *  the top left coords. of StdDraw to (1,1) file coords, the input row has to be flipped, 
 *  to map the StdDraw coord map (0, 0) to the bottom left corner, instead of the top.  Since
 *  the 0 column is not used, the column values must be shifted to the right.  This requires an
 *  extra column at the end of the grid to shift the last column over one.</p>
 *
 *  <b>Methods:</b>
 *  
 *  <ol><li>createNode() is a mirror of searchForOpenedNodeByRow(), accept instead of reading in input,
 *  it creates random coordinates and converts them to a WeightedQuickUnionUF object, so
 *  they can be flipped from BLOCKED to OPENED.</li>
 *
 *  <li>The nodeChangerRowTracker() method opens all the BLOCKED squares that are read from 
 *  the file.  Once done, the searchForOpenedNodeByRow() method is called by checkPerculation(),
 *  to search for touching OPENED squares, and partially isolated OPENED squares in each 
 *  row (starting from top)and sets them to FILLED, then draws them. The color cascades down  
 *  to all touching or partially touching OPENED squares. </li> 
 *  </ol>
 *
 *  <p><b>Files:</b> The first element in the file is the dimension, the other numbers are rows and cols in sequence.</p>
 *
 *  @version 1.7
 *  @author Tony Lockhart
 *  
 *  
 */
public class Percolation{
	/**
	 * The dimension of the N by N grid, +1 to get rid of 0 offset
	 */
	private final int dimensionHigh;
	/**
	 * The lowest index of the N by N grid, starts at 0
	 */
	private static final int dimensionLow = 0;
		//QuickUnion
	
	private WeightedQuickUnionUF qf;
	/**
	 *The total number of squares in the grid.
	 */
	private int squaredDimensionHigh;
	/**
	 *The highest status that can be entered into an array index (Filled - this.squaredDimensionHigh+2).
	 */
	private static int statusHigh = 0;
	/**
	 *The highest status that can be entered into an array index (Blocked - this.squaredDimensionHigh).
	 */
	private static int statusLow = 0;
	
	/**
	 *The array index value of the grid, where squaredDimensionHigh = Blocked, squaredDimensionHigh+1= Opened, squaredDimensionHigh+2 = Filled.
	 */
	 private int status;
	/**
	 * The N by N grid array.
	 */	
	private int[][] grid;
	private int row;
	private int col;
	/**
	 *Tracks the number of inputs read from the external file, it is incremented by 2 because the coordinates come in pairs (col, row).  It is initially set at 1 because the 1rst integer read is the array dimension.
	 */
	private int count=1;
	/**
	 * The linear input array.
	 */
	private static int[] inputArray;

	/**
	*Keeps track of the number of squares Filled in the row (starting at 1).  Once the row is completely Filled, it will be incremented to the next row.
	*/
	private int fillRow;
	/**
	*Keeps track of the total number of Filled squares.
	*/
	private int filledRowCounter = 0;
	/**
	 * The total number of Opened squares
	 */
	private int openedSquareCounter = 0;
	/**
	 * The total number of Filled squares.
	 */ 
	private int totalFilledSquares = 0;
	/**
	 * The starting value (1) used to search for other opened squares in the same row, when an opened square is found.  The value is used to flip Opened squares to Filled.
	 */
	 private int openNodeSearchCol = 1;
	/*
	 *Instance variable that can count isolated open nodes.
	 */
	private double doubleThreshold = 0;
	/**
	 *Boolean value that returns true if the grid perculates (Filled value reaches the bottom of the grid).
	 */
	private boolean percolates = false;
	/**
	 *Flag that is set to true when the end of a row has been reached.  This value allows the fillRow to be incremented.
	 */
	private boolean endOfRowReached = false;
	/**
	 *The lowest status that can be entered into an array index (Blocked - this.squaredDimensionHigh).
	 */
	private int BLOCKED = 0;
	/**
	 *The middle status that can be entered into an array index (Opened - this.squaredDimensionHigh+1).
	 */
	private int OPENED = 0;
	/**
	 *The highest status that can be entered into an array index (Filled - this.squaredDimensionHigh+2).
	 */
	private int FILLED = 0;
	/***************************/
	
	/** 
	 *Percolation constructor intializes all the grid values based on the dimensions.
	 @param dimensionHigh the integer used to establish the dimensions of the N by N grid.
	 */
	public Percolation(int dimensionHigh)
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

		this.dimensionHigh = dimensionHigh+1;
		//the number of squares in the N by N array
		this.squaredDimensionHigh = this.dimensionHigh*this.dimensionHigh;
		this.OPENED = this.squaredDimensionHigh;//36, 49
		this.FILLED = this.squaredDimensionHigh+1;//37, 50
		
		statusHigh = this.squaredDimensionHigh+2;//38, 51
		statusLow = this.squaredDimensionHigh;
		
		this.grid = new int[this.dimensionHigh][this.dimensionHigh];
		int arraySize=this.squaredDimensionHigh+2;//38, 51
		this.qf = new WeightedQuickUnionUF(arraySize);
		this.fillRow = this.dimensionHigh-1;
		
	}
	
	/** 
	 *Creates random linear index from the dimensionLow and the squaredDimensionHigh instance variables.  Method converts the linear array index to the row, col for the N by N grid.
	 */
	private void createNode()
	{
		int index = this.getRandomNum(this.dimensionLow, this.squaredDimensionHigh);
		if(((index%dimensionHigh)!=0)&&(index>=dimensionHigh))/*(this.row!=0)&&(this.col!=0))*/
		{
			this.convertLinearArrayNodeToGridCoord(index);
			this.status = this.getRandomNum(statusLow, statusHigh);
			
			//**********************************************************
			//SET LINEAR NODE ARRAY TO OPENED, BUT GRID IS SET TO STATUS
			//FIRST PUT STATUS IN INDEX VALUE
			/**********************************************************/
			if(!this.qf.connected(this.status, index))
			{
				this.qf.union(this.status, index); //put random status (25 or 26) in index
			}
		}
	}//CreateNode
	/**
	 * When an opened node is found in the grid, the method searches the row of the Opened square of other Opened squares, to be Filled (starting at col 1, to take care of the 1 col offset).
	 */
	private void searchForOpenedNodeByRow(int col, int row)
	{
		this.status = this.FILLED;
		this.endOfRowReached = false;
		while(col<this.dimensionHigh)
		{
			while((!isOpenNodeReadyToBeFilled(col,row))&&(col<this.dimensionHigh-1))
			{
				col++;
			}
			//Set col, row values before nodeChangerRowTracker flips the node
			if(isOpen(row, col))
			{
				this.col=col;
				this.row=row;
				this.nodeChangerRowTracker();
			}
			//Stop While loop if Counter has reached the end of the row
			else if(col==this.dimensionHigh-1)
			{
				this.endOfRowReached = true;
				//StdOut.println("$$$$$$End of Row Reached, Row ="+this.row);
				break;
				
			}
		}
	}//SearchOpenNode
	/**
	 *Method maps the random coordinates from the createNode method to the Linear Array in UF
	 */
	private int convertGridCoordToLinearNode(int col, int row)
	{
		int index = col+(row*this.dimensionHigh);		
		return index;
	}
	/**
	 *Converts a linear array index to an N by N grid coordinate and stores the row and col instance. 
	 @param index The linear array index to be created to an N by N grid coordinate.
	 */
	private  void convertLinearArrayNodeToGridCoord(int index)
	{
		int col = 0;
		int row = 0;
		if((index >= 0)&&(index < this.dimensionHigh))
		{
			col = index;
			row = 0;
			//col = col;
			//row = index;
		}
		else
		{
			col = index%this.dimensionHigh;
			row = (index-col)/this.dimensionHigh;
		}
		this.col = col;
		this.row = row;
	}
	/**
	 *Method changes the N by N square coordinates(row, col) from blocked to opened
	 */
	private void openSquare(int row, int col)
	{
		checkBounds(row, col);
			this.grid[col][row] = this.status;
			this.drawSquare(col, row, this.grid[col][row]);
			this.openedSquareCounter++;
	}
	/**
	 *Sets the square at (col, row) to Opened, and flips the row , so coordinates (0,0) is at the top of the coordinate system, instead of at lower left.
	 *@param row the row coordinate in the N by N grid.
	 *@param col the col coordinate in the N by N grid.
	 */
	public void open(int row, int col)
	{
		count=count+2;
		checkBounds(row, col);
		this.row = this.dimensionHigh-row;//flip rows, so coordinates (0,0) is at the top of the coordinate system
		this.col = col;
		this.status=OPENED;
		this.grid[this.col][this.row]=this.status;
		openedSquareCounter++;
		this.drawSquare(this.col, this.row, this.status);
		if(this.count==inputArray.length)
		{
			this.checkPerculation();
		
		}
	}

	/**
	 *Returns a random integer uniformly in [a, b). NOTE: [excluded, included)
	 *@param low excluded integer value at the low end of the range.
	 *@param high included integer value at the high end of the range.
	 *@return value A randomly generated value between the arguments low and high.
	 */
	private int getRandomNum(int low, int high)
	{
		int value = StdRandom.uniform(low, high);
        return value;	
	}
	/**
	 *Prints the grid with initial Blocked values
	 */
	private void printBlockedGrid(int source)
	{
			for(int row = 0; row < this.dimensionHigh;row++)
			{
				for(int col = 0; col < this.dimensionHigh; col++)
				{
					//The Grid will be set to all 0's at first
					if((this.grid[col][row] >= 0)&&(this.grid[col][row] < this.dimensionHigh))
					{
						this.drawSquare(col, row, source);
						this.grid[col][row] = source;
					}
				}
			}//for
	}
	/**
	 * Draws the background color of the squares, based on its status
	 * @param col the col of the N by N grid.
	 * @param row the row of the N by N grid.
	 * @param status The grid coordinate value, which indicates the status of the grid[Blocked , Opened , and Filled squares]
	 */
	private void drawSquare(int col, int row, int status)
	{
		//StdDraw.setXscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setXscale(0, this.squaredDimensionHigh+5);
		//StdDraw.setXscale(1, this.squaredDimensionHigh+5);
		
		//StdDraw.setYscale(dimensionHigh-1, this.squaredDimensionHigh);
		StdDraw.setYscale(0, this.squaredDimensionHigh+5);
		//StdDraw.setYscale(1, this.squaredDimensionHigh+5);
			if(status == BLOCKED)
			{
				StdDraw.setPenColor(StdDraw.BLACK);
			}
			else if(status == OPENED)
			{
				StdDraw.setPenColor(StdDraw.WHITE);
			}
			else if(status == FILLED)
			{
				StdDraw.setPenColor(StdDraw.GREEN);
			}
		StdDraw.filledSquare(((col*this.dimensionHigh)+((double)this.dimensionHigh/2)), ((row*this.dimensionHigh)+((double)this.dimensionHigh/2)), (((double)this.dimensionHigh)/2));
		StdDraw.show();
		
		/*****************************************************/
		//Set Linear Array to open, but grid set to status.
		/*****************************************************/
		int index =convertGridCoordToLinearNode(col, row);
		if(this.qf.connected(OPENED, index))
		{
			this.qf.union(OPENED, index);
			
		}
	}
	/**
	 * Method changes the value in the square associated with the (col, row) coordinate in the N by N grid, from Opened to Filled.
	 * @param col the col of the N by N grid.
	 * @param row the row of the N by N grid.
	 */
	private void flipOpenNodeToFilled(int col, int row)
	{
		int tidalWaveCol = col;
		int tidalWaveRow = row;
		flipSurroundingOpenSquaresToFilled(tidalWaveCol, tidalWaveRow);
	}
	/**
	 *Method checks to the Left, Above, Right, and Below the current sqaure, for filled squares.
	 *@return value Returns boolean value.
	 */
	private boolean isSurroundingSquareFilled(int col, int row)
	{
		int value = 0;
		boolean isSurroundingSquareFilled = false;
		if((col <= (this.dimensionHigh-1))&&(col > 1))
		{
			//Check if next square is on the grid.
			if((this.grid[col-1][row] == FILLED))
			{
				value++;
			}
		}//check if the square will is on the grid.
		if(((row >= 1)&&(row < (this.dimensionHigh-1))))
		{
			if(this.grid[col][row+1] == FILLED)
			{
				value++;
			}
		}
		if((col>=1)&&(col<(this.dimensionHigh-1)))
		{
			if(this.grid[col+1][row] == FILLED)
			{
				value++;
			}
		}
		if((row<=(this.dimensionHigh-1))&&(row>1))
		{
			if(this.grid[col][row-1] == FILLED)
			{
				value++;
			}
		}
		
		if(value>0)
		{
			isSurroundingSquareFilled = true;
		}else
			isSurroundingSquareFilled = false;
		
		return isSurroundingSquareFilled;
	}
	private void checkBounds(int row, int col)
	{
		if ((row < 0 || row >= this.dimensionHigh)||(col < 0 || col >= this.dimensionHigh)) 
		{
			throw new IllegalArgumentException("Row = " + row +" Col = "+col+ " is not between 0 and " + (this.dimensionHigh-1));
		}
	}
	private void checkBound(int n)
	{
		if ((n <= 0))
		{
			throw new IllegalArgumentException("index " + n + " is not Greater than 0.");
		}
	}
	public boolean isOpen(int row, int col)
	{
		checkBounds(row, col);
		boolean value = false;
		if(this.grid[col][row] == this.OPENED)
		{
			value = true;
		}
		return value;
	}
	public boolean isFilled(int row, int col)
	{
		checkBounds(row, col);
		boolean value = false;
		if(this.grid[col][row] == FILLED)
			value = true;
		return value;
	}
	/**
	 *Determines if the grid percolates (Filled square reaches the bottom grid).
	 */
	public boolean percolates()
	{
		boolean value = false;
		if(this.percolates)
			value = true;
		return value;
	}
	/**
	 *Method changes the colors from Blocked to Opened, and from Opened to Filled.
	 */
	private void nodeChangerRowTracker (/*7/08/2017 int row*/)
	{
			if(((this.filledRowCounter == (this.dimensionHigh-1))&&(this.fillRow == this.row)&&(this.status == FILLED))||(this.endOfRowReached))
			{
				this.filledRowCounter = 0;
				this.fillRow--;
				StdOut.println("I am in filledRowCounter Reset Check");
			}
			//increment number of Filled Squares, decrement Opened Squares, when changing from Opened to Filled
			else if(isOpenNodeReadyToBeFilled(this.col, this.row))
			{				
				//Change all open squares touching a filled square to filled
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

	/**
	 *Method checks if target node is Opened and instance var status is set to Filled.
	 *@return value Returns boolean value.
	 */
	private boolean isOpenNodeReadyToBeFilled(int col, int row)
	{
		boolean value = false;
		if((this.filledRowCounter < this.dimensionHigh)&&(this.fillRow == row)&&(this.status == this.FILLED)&&(this.grid[col][row] == this.OPENED))
		{
			value = true;
		}
		return value;
	}
	/**
	 *Method checks if target square is Blocked, and instance var status is set to Opened.
	 *@return value Returns boolean value.
	 */
	private boolean isBlockedNodeReadyToBeOpened(int col, int row)
		{
			boolean value = false;
			if((this.status == this.OPENED)&&(this.grid[col][row] == this.BLOCKED))
				value = true;
			return value;
		}
	/**
	 *Method Flips Blocked Nodes to Opened, if surrounding squares are Filled.
	 */
	private void flipBlockedNodeToOpened(int col, int row)
	{
		//Change square from blocked to opened 
		openSquare(row, col);
		if(isSurroundingSquareFilled(col, row))
		{
			//Call flip method to flip OPENED squares surrounding the latest FILLED squares
			//If found change them to FILLED
			flipSurroundingOpenSquaresToFilled(col, row);
		}
	}
	public int numberOfOpenSites()
	{
		StdOut.println("NUMBEROFOPENSITES: Opened Squares = "+this.openedSquareCounter+" Filled Squares = "+this.totalFilledSquares);
		return this.openedSquareCounter+this.totalFilledSquares;
	}
	
	/**
	 *Method checks surrounding squares and changes colors as necessary
	 *Method is recursively called to flip squares from Opened (White) to Filled (Green) as necessary.
	 */
	private void flipSurroundingOpenSquaresToFilled(int col, int row)
	{
		//Change status to Filled
		int status=FILLED;
		//If the target Square is OPENED
		while((col < this.dimensionHigh)&&(col >= 1)&&(row >= 1)&&(row < this.dimensionHigh)&&(isOpen(row, col)))
		{
			if(isOpen(row, col))//original square = OPENED, about to change to FILLED
			{
				/******************************************************/
				//07/08/2017
				if(this.fillRow == row)
				{
					//increment the count of filled squares in the row
					/*************************************************/
					this.filledRowCounter++;
				}
				//increment running total of filledSquares
					this.totalFilledSquares++;//keeps running total of filled squares
				
				//subtract openSquareCounter instance when Opened status is changed to Filled status
					this.openedSquareCounter--;
				//Change status to filled
				this.grid[col][row] = status;
				//draw filled squares
				this.drawSquare(col, row, this.status);
				//}
			}//if
			
			//Executes recursively after grid value changed from OPENED to FILLED
			if(isFilled(row, col))
			{
				//check LEFT Square
				if((col <= (this.dimensionHigh-1))&&(col > 1)){flipSurroundingOpenSquaresToFilled(col-1, row);}
				
				//CHECK ABOVE SQUARE
				if((row>=1)&&(row < (this.dimensionHigh-1))){flipSurroundingOpenSquaresToFilled(col, row+1);}
				
				//CHECK RIGHT
				if((col>=1)&&(col < (this.dimensionHigh-1))){flipSurroundingOpenSquaresToFilled(col+1, row);}
				
				//CHECK BELOW
				if((row <= (this.dimensionHigh-1))&&(row > 1)){flipSurroundingOpenSquaresToFilled(col, row-1);}
				
				/*7/08  - Condition for percolates being true
				*********************************************************/
				if(row == 1)
				{
					this.percolates = true;
				}
				
			}//if
		}//while
	}//flipSurroundingOpenSquaresToFilled
	private String threshold()
	{
		double PercolationThreshold = (double)(this.totalFilledSquares+this.openedSquareCounter)/(double)((this.dimensionHigh-1)*(this.dimensionHigh-1));
		String formatedString = String.format(java.util.Locale.US,"%.2f",PercolationThreshold);
		this.doubleThreshold = PercolationThreshold;
		return formatedString;
	}

	private void drawText(String text)
	{
		 StdDraw.setPenColor(StdDraw.MAGENTA);
		 StdDraw.text(10, 1, "Threshold is: "+text);
	}
	/**
	 * Checks the entire grid, starting at col 1(opentNodeSearchCtr), to determine if the grid perculates (filled square has reached the bottom of the grid).
	 */
	private void checkPerculation()
	{
		while(!this.percolates())
			{
				this.searchForOpenedNodeByRow(this.openNodeSearchCol,this.dimensionHigh-1);
			}
	}
	public static void main(String[] args)
	{
			//Read input file
			Percolation.inputArray = StdIn.readAllInts();

			//Send the dimensions of the grid, stored in inputArray[0], to the constructor
			Percolation list = new Percolation(Percolation.inputArray[0]);
			/********************************************************/
			//Sets 2D Grid to BLOCKED; Takes time proportional to n^2
			/********************************************************/
			list.printBlockedGrid(0);
			int rowInput;
			int colInput;
			for(int i=1; i<Percolation.inputArray.length; i++)
			{
				rowInput = list.inputArray[i];
				colInput = list.inputArray[++i];
				list.open(rowInput, colInput);				
			}
			list.drawText(list.threshold());
		
	}//main
}//Percolation