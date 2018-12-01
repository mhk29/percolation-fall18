
public class PercolationDFSFast extends PercolationDFS {
	
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	@Override 
	public void updateOnOpen(int row, int col) {
		if (row == 0) {
			dfs(row, col);
		}
		
		else if (inBounds(row, col) &&
				(inBounds(row, col) && isFull(row - 1, col)) ||
				(inBounds(row, col) && isFull(row + 1, col)) ||
				(inBounds(row, col) && isFull(row, col - 1)) ||
				(inBounds(row, col) && isFull(row, col + 1))) {
			
			myGrid[row][col] = FULL;
			dfs(row, col);
		}
		
	}
	
}