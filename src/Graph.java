import java.util.HashSet;

public class Graph {
    private final int V;
    private int E;
    private HashSet<Edge>[] adj;

    // constructor
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("The total vertex is non-negative!");
        this.V = V;
        this.E = 0;
        adj = new HashSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<Edge>();
        }
    }


    public void connect(int start, int end, int weight) {
        Edge e = new Edge(start, end, weight);
    }
}
