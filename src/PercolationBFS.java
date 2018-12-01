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
			row = recover(helper, myGrid.length)[0];
			col = recover(helper, myGrid.length)[1];
			
			if (inBounds(row - 1, col) && isOpen(row - 1, col) && ! isFull(row - 1, col)) {
				myGrid[row][col] = FULL;
				que.add((row - 1) * myGrid.length + col);
			}
			if (inBounds(row + 1, col) && isOpen(row + 1, col) && ! isFull(row + 1, col)) {
				myGrid[row][col] = FULL;
				que.add(((row + 1) * myGrid.length) + col);
			}
			if (inBounds(row, col - 1) && isOpen(row, col - 1) && ! isFull(row, col - 1)) {
				myGrid[row][col] = FULL;
				que.add(row*myGrid.length + col - 1);
			}
			if (inBounds(row, col + 1) && isOpen(row, col + 1) && ! isFull(row, col + 1)) {
				myGrid[row][col] = FULL;
				que.add(row*myGrid.length + col + 1);	
			}
		}
	}
	
	public int[] recover (int n, int m) {
		int row = n / m;
		int col = n % m;
		return new int[] {row, col};
	}
	
}