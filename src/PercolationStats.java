import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final double[] totalOpens;
	private double dev, mean;
	private final int T;
	private static final double CONFIDENCE_95 = 1.96;

	public PercolationStats(int n, int T) {
		this.T = T;

		totalOpens = new double[T];

		for (int i = 0; i < T; i++) {
			Percolation percolation = new Percolation(n);

			while (!percolation.percolates()) {
				int row = StdRandom.uniform(n) + 1;
				int col = StdRandom.uniform(n) + 1;

				percolation.open(row, col);

			}
			totalOpens[i] = (double) percolation.numberOfOpenSites() / (double) (n * n);
		}
	}

	public double mean() {
		mean = StdStats.mean(totalOpens);
		return mean;

	}

	public double stddev() {
		dev = StdStats.stddev(totalOpens);
		return dev;
	}

	public double confidenceLo() {
		return mean - ((CONFIDENCE_95 * dev) / Math.sqrt(T));
	}

	public double confidenceHi() {
		return mean + ((CONFIDENCE_95 * dev) / Math.sqrt(T));
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		if (n < 1 || T < 1) {
			throw new IllegalArgumentException("argument outside bounds");
		}

		PercolationStats percolationStats = new PercolationStats(n, T);
		System.out.println("mean = " + percolationStats.mean());
		System.out.println("stddev = " + percolationStats.stddev());
		System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
				+ percolationStats.confidenceHi() + "]");

	}
}
