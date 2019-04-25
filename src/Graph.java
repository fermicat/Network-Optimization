import java.util.HashSet;

public class Graph {
    private final int V;
    private int E;
    public HashSet<Edge>[] adj;

    // constructor
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("The total vertex is non-negative!");
        this.V = V;
        this.E = 0;
        adj = new HashSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    /**
     * connect two end
     * @param start  one end
     * @param end    the other end
     * @param weight weight on this edge
     *
     */
    public void connect(int start, int end, int weight) {
        Edge e;
        e = new Edge(start, end, weight);
        adj[start].add(e);
        e = new Edge(end, start, weight);
        adj[end].add(e);
        E++;
    }

    /**
     * return vertices number
     * @return V total vertices number
     */
    public int V() {
        return V;
    }

    /**
     * return edges number
     * @return E total edges number
     */
    public int E() {
        return E;
    }

    /**
     * print the graph in the String form
     * @return the String form of the graph
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges \n");
        for (int i = 0; i < V; i++) {
            s.append(i + ": ");
            for (Edge e : adj[i]) {
                s.append(e.getEnd(i) + " (" + e.weight + "), ");
            }
            s.append("\n");
        }
        String result = s.toString();
        System.out.println(result);
        return result;
    }

}
