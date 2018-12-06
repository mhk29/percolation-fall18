
public class PercolationUF implements IPercolate {
	
	//Instance Variables 
	// myGrid[][] holds grid of booleans
	// myOpenCount holds the number of times that open is called
	// myFinder is a QuickUWPC() variable used to determine how 
	// myGrid[][] elements are connected
	// VTOP represents the top of the grid as an integer
	// VBOTTOM represents the bottom of the grid as an integer
	
	protected boolean myGrid[][];
	protected int myOpenCount;
	IUnionFind myFinder = new QuickUWPC();
	private final int VTOP;
	private final int VBOTTOM;

	//Constructor
	// Creates a new PercolationUF object by setting all of the 
	// above instance variables appropriately 
	// myGrid is a size x size grid
	// myOpenCount = 0
	// VTOP = size * size; VBOTTOM = size*size + 1 since these integers 
	// never occur in myGrid as a result of the convertTo() method below
	// IUnionFind finder is initialized and set equal to myFinder
	
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		myOpenCount = 0;
		VTOP = size * size;
		VBOTTOM = size * size + 1;
		finder.initialize(size*size + 2);
		myFinder = finder;
	}
	
	// open(row, col) is given a location in the grid
	// and in turn sets that location to true
	//
	// it then connects the grid appropriately using 
	// the union() method for IUnionFind objects
	
	@Override 
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col] == false) myOpenCount += 1;
		if (! isOpen(row, col)) {
			myGrid[row][col] = true;
			
			if (inBounds(row - 1, col) && 
					isOpen(row - 1, col)) {
				myFinder.union(convertTo(row, col), convertTo(row - 1, col));
			}
			if (inBounds(row + 1, col) && 
					isOpen(row + 1, col)) {
				myFinder.union(convertTo(row, col), convertTo(row + 1, col));
			}
			if (inBounds(row, col - 1) && 
					isOpen(row, col - 1)) {
				myFinder.union(convertTo(row, col), convertTo(row, col - 1));
			}
			if (inBounds(row, col + 1) && 
					isOpen(row, col + 1)) {
				myFinder.union(convertTo(row, col), convertTo(row, col + 1));
			}
			if (row == 0) {
				myFinder.union(VTOP, convertTo(row, col));
			}
			if (row == myGrid.length - 1) {
				myFinder.union(convertTo(row, col), VBOTTOM);
			}	
		}
	}
	
	// isOpen(row, col) 
	// @return true or false based on input location
	
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}
	
	// isFull(row, col)
	// @return true or false if water can flow into cell at input location
	
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));}		
		return myFinder.connected(VTOP, convertTo(row,col));
	}
	
	// percolates()
	// @return true or false if system percolates
	
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	// numberOfOpenSites()
	// @return the number of times open() has been called
	
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	// inBounts(row, col)
	// @return true or false if the location is located within the grid
	
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	// convertTo(row, col)
	// converts a row, col pair into a single location automatically

	public int convertTo(int row, int col) {
		return (row*myGrid.length + col);
	}
		
}