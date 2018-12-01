
public class PercolationUF implements IPercolate {
	
	boolean myGrid[][];
	int myOpenCount;
	IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	public PercolationUF(int size, IUnionFind finder) {
		VTOP = size * size;
		VBOTTOM = size * size + 1;
		finder.initialize(size*size + 2);
		myFinder = finder;
	}
	
	@Override 
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		myOpenCount += 1;
		if (! isOpen(row, col)) {
			myGrid[row][col] = true;
			
			if (isOpen(row - 1, col)) {
				myFinder.union(convertTo(row, col), convertTo(row - 1, col));
			}
			if (isOpen(row + 1, col)) {
				myFinder.union(convertTo(row, col), convertTo(row + 1, col));
			}
			if (isOpen(row, col - 1)) {
				myFinder.union(convertTo(row, col), convertTo(row, col - 1));
			}
			if (isOpen(row, col + 1)) {
				myFinder.union(convertTo(row, col), convertTo(row, col + 1));
			}
			if (row == 0) {
				myFinder.union(convertTo(row, col), VTOP);
			}
			if (row == myGrid.length) {
				myFinder.union(convertTo(row, col), VBOTTOM);
			}	
		}
	}
	
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}
	
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));}		
		return myFinder.connected(VTOP, convertTo(row,col));
	}
	
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}

	public int convertTo(int row, int col) {
		return (row*myGrid.length + col);
	}
		
}