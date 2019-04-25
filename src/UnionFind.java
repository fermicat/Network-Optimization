public class UnionFind {
    private int[] parent;  // parent[i] = parent of i
    private int[] rank;    // rank[i] = number of sites in tree rooted at i

    // Constructor
    public UnionFind(int n) {
        if (n < 0) throw new IllegalArgumentException("negative size!");
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * Returns the component identifier for the component containing site p.
     *
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site p
     */
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        // compression
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }

    /**
     * Merges the component containing site p with the
     * the component containing site q.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // weighted: make smaller root point to larger one
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        }
        else {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        }
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " should between 0 and " + (n - 1));
        }
    }
}