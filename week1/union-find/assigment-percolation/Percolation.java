import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
// We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

public class Percolation {
    private int n;
    private WeightedQuickUnionUF uf;
    private boolean[][] sites;
    private int openCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n * n);
        this.sites = new boolean[n][n];
        this.openCount = 0;

        for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) {
            this.sites[i][j] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row -= 1;
        col -= 1;
        validateXY(row, col);

        if (!sites[row][col]) {
            sites[row][col] = true;
            this.openCount += 1;

            int ufPos = coordsToUf(row, col);
            if (row > 0 && sites[row - 1][col]) uf.union(ufPos, coordsToUf(row - 1, col));
            if (row < this.n - 1 && sites[row + 1][col]) uf.union(ufPos, coordsToUf(row + 1, col));
            if (col > 0 && sites[row][col - 1]) uf.union(ufPos, coordsToUf(row, col - 1));
            if (col < this.n - 1 && sites[row][col + 1]) uf.union(ufPos, coordsToUf(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row -= 1;
        col -= 1;
        validateXY(row, col);
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) return false;

        row -= 1;
        col -= 1;
        int ufPos = coordsToUf(row, col);
        int rootA = uf.find(ufPos);
        for (int i = 0; i < n; ++i) {
            int rootB = uf.find(coordsToUf(0, i));
            if (rootA == rootB) return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openCount;
    }

    // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
    public boolean percolates() {
        for (int i = 1; i <= n; ++i) {
            if (isFull(this.n, i)) return true;
        }
        return false;
    }

    private void validateXY(int row, int col) {
        if (row < 0 || col < 0 || row >= this.n || col >= this.n) {
            throw new IllegalArgumentException();
        }
    }

    private int coordsToUf(int row, int col) {
        return row * this.n + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        //
    }
}
