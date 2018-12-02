import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {
	
	public PercolationBFS(int n) {
		super(n);
	}
	
	@Override 
	public void dfs(int row, int col) {
		
		if (! inBounds(row, col)) return;
		if (isFull(row, col) || ! isOpen(row, col)) return;
		
		myGrid[row][col] = FULL;
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(row*myGrid.length + col);
		
		while (que.size() != 0) {
			int helper = que.remove();
			int row1 = recover(helper, myGrid.length)[0];
			int col1 = recover(helper, myGrid.length)[1];
				
			if (inBounds(row1 - 1, col1) && isOpen(row1 - 1, col1) && ! isFull(row1 - 1, col1)) {
				myGrid[row1 - 1][col1] = FULL;
				que.add((row1 - 1) * myGrid.length + col1);
			}
			if (inBounds(row1 + 1, col1) && isOpen(row1 + 1, col1) && ! isFull(row1 + 1, col1)) {
				myGrid[row1 + 1][col1] = FULL;
				que.add(((row1 + 1) * myGrid.length) + col1);
			}
			if (inBounds(row1, col1 - 1) && isOpen(row1, col1 - 1) && ! isFull(row1, col1 - 1)) {
				myGrid[row1][col1 - 1] = FULL;
				que.add(row1*myGrid.length + col1 - 1);
			}
			if (inBounds(row1, col1 + 1) && isOpen(row1, col1 + 1) && ! isFull(row1, col1 + 1)) {
				myGrid[row1][col1 + 1] = FULL;
				que.add(row1*myGrid.length + col1 + 1);	
			}
			
		}
	}
	
	public int[] recover (int n, int m) {
		int row = n / m;
		int col = n % m;
		return new int[] {row, col};
	}
	
}