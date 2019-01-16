
public class Percolation {
	private final int[] grid, size;
	private final int map[][];
	private final boolean[] isOpen;
	private final int bound;
	private final int virtualTop = 0, virtualBottom;
	private int openSites = 0;

	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("N<=0");

		grid = new int[n * n + 2];
		map = new int[n][n];
		isOpen = new boolean[n * n + 2];
		size = new int[n * n + 2];

		bound = n;
		virtualBottom = bound * bound + 1;

		for (int i = 0; i < n * n + 2; i++) {
			grid[i] = i;
			size[i] = 1;
		}
		int value=1;
		for (int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j]=value++;
			}
		}
	}

	private void union(int p, int q) {
		int i = root(grid[p]);
		int j = root(grid[q]);

//		grid[i] = j;

		if (i == j)
			return;
		if (size[p] < size[q]) {
			grid[i] = j;
			size[j] += size[i];
		} else {
			grid[j] = i;
			size[i] += size[j];
		}
	}

	private boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	private int root(int i) {
		while (i != grid[i])
			i = grid[i];
		return i;
	}

	public void open(int row, int col) {
		int index = twoDtoOneD(row, col);
		if (row > bound || row <= 0 || col > bound || col <= 0) {
			throw new IllegalArgumentException("argument outside bounds");
		}
		if (!isOpen[index]) {
			isOpen[index] = true;
			openSites++;
			
			// left
			if (col > 1) {
				int left = index - 1;
				if (isOpen[left])
					union(grid[index], grid[left]);
			}
			// right
			if (col < bound) {
				int right = index + 1;
				if (isOpen[right])
					union(grid[index], grid[right]);
			}
			// up
			if (row == 1) {
				union(grid[index], grid[virtualTop]);
			} else {
				
				int up = index - bound;
				if (isOpen[up])
					union(grid[index], grid[up]);
			}
			// down
			if (row == bound) {
				union(grid[index], grid[virtualBottom]);
			} else {
				int down = index + bound;
				if (isOpen[down])
					union(grid[index], grid[down]);
			}
		}
	}

	public boolean isOpen(int row, int col) {
		if (row > bound || row <= 0 || col > bound || col <= 0) {
			throw new IllegalArgumentException("argument outside bounds");
		}
		return isOpen[twoDtoOneD(row, col)];
	}

	public boolean isFull(int row, int col) {
		if (row > bound || row <= 0 || col > bound || col <= 0) {
			throw new IllegalArgumentException("argument outside bounds");
		}
		return connected(twoDtoOneD(row, col), virtualTop);
	}

	public int numberOfOpenSites() {
		return openSites;
	}

	public boolean percolates() {
		return connected(virtualTop, virtualBottom);
	}

	private void print() {
		for (int i = 1; i < bound * bound + 1; i++) {
			if (i % bound - 1 == 0 && i != 1)
				System.out.println();
			System.out.print(grid[i] + " ");

		}
		System.out.println();
	}

	public static void main(String[] args) {
		int n = 200;
		Percolation percolation = new Percolation(n);

		percolation.print();
		System.out.println();

//		percolation.open(2, 2);
//		percolation.open(3, 2);
//		percolation.open(3, 3);
		percolation.open(1, 1);
		percolation.open(2, 1);

		percolation.print();
		System.out.println("open sites: " + percolation.numberOfOpenSites());
		System.out.println("percolates: " + percolation.percolates());

	} // test client (optional)

	private int twoDtoOneD(int row, int col) {
//		return col + (bound * (row - 1));
		return map[row-1][col-1];
	}
}
