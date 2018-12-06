
public class PercolationDFSFast extends PercolationDFS {
	
	// Constructor
	// calls super(n) on the constructor for PercolationDFS
	// setting the PercolationDFSFast object just as a PercolationDFS
	// object would be set
	
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	// updateOnOpen(row, col)
	// updates whether something that has just been opened will be filled or not
	// one call of dfs(row, col) made
	
	@Override 
	public void updateOnOpen(int row, int col) {
		if (inBounds(row,col)) {	
			if ((row == 0) ||
					(inBounds(row - 1, col) && isFull(row - 1, col)) ||
					(inBounds(row + 1, col) && isFull(row + 1, col)) ||
					(inBounds(row, col - 1) && isFull(row, col - 1)) ||
					(inBounds(row, col + 1) && isFull(row, col + 1))) {
			
				dfs(row, col);
			}
		}
	}
	
}