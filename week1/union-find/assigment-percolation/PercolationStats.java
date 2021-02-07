import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private int t;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int t) {
        this.n = n;
        this.t = t;
        this.results = new double[t];
        runTrials();
    }

    private void runTrials() {
        for (int i = 0; i < this.t ; ++i) {
            Percolation percolation = new Percolation(n);
            double openSites = 0;

            while (!percolation.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(y, x)) {
                    percolation.open(y, x);
                    openSites++;
                }
            }
            results[i] = openSites / (this.n * this.n);
        }
    }

    /**
     * Sample mean of percolation threshold
     * @return double  mean
     */
    public double mean() {
        return StdStats.mean(this.results);
    }

    /**
     * Sample standard deviation of percolation threshold
     * @return double  standard deviation
     */
    public double stddev() {
        return StdStats.stddev(this.results);
    }

    /**
     * Low  endpoint of 95% confidence interval
     * @return double  low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(this.t);
    }

    /**
     * High  endpoint of 95% confidence interval
     * @return double  high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(this.t);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(200, 1000);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("interval = " + ps.confidenceLo() + " " + ps.confidenceHi());
    }

}
