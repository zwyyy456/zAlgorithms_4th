import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] parent;
    private int[] size;
    private int count;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        count = n * n;
        parent = new int[n * n - 1];
        size = new int[n * n - 1];
        for (int i = 0; i < n * n; i++) {
            parent[i] = 0;
            size[i] = 1;
        }
    }
}
