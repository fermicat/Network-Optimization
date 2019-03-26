public class Graph {
    private int nVertex;
    private int nEdge;

    // constructor
    public Graph(int nVertex) {
        if (nVertex < 0) throw new IllegalArgumentException("The total vertex is non-negative!");
        this.nVertex = nVertex;


    }


    public void connect(int start, int end, int weight) {
        Edge e = new Edge(start, end, weight);
    }
}
