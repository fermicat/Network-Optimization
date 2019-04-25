import java.util.Random;

public class GraphGeneration {

    private static Random r = new Random();
    /**
     * the procedure to generate the graph with average vertex degree
     * @param totalVertex total Vertex in the graph (default 5000)
     * @param vertexNum   vertex degree (default 6)
     * @return a constructed graph
     */
    public static Graph firstGraphGeneration(int totalVertex, int vertexNum) {
        Graph graph = cycleGraph(totalVertex);

        // average degree
        while (graph.E() < totalVertex * vertexNum / 2) {
            int i = r.nextInt(totalVertex);
            int j = r.nextInt(totalVertex);

            if (i != j) {
                int weight = r.nextInt(1000) + 1;
                Edge edge = new Edge(i, j, weight);
                if (!graph.adj[i].contains(edge)) {
                    graph.connect(i, j, weight);
                }
            }
        }

        System.out.println("first graph:\n" + graph.toString());
        return graph;
    }

    /**
     * the procedure to generate the graph with percent% connect to other point
     * @param totalVertex total Vertex in the graph (default 5000)
     * @param edgePercent the percent% to connect (default 20)
     * @return a constructed graph
     */
    public static Graph secondGraphGeneration(int totalVertex, int edgePercent) {
        Graph graph = new Graph(totalVertex);

        for (int i = 0; i < totalVertex; i++) {
            for (int j = i + 2; j < totalVertex; j++) {
                // generate random int [1, 100]
                int connect = r.nextInt(100) + 1;

                // ensure ~ 20% of edge will be connected
                if (connect <= edgePercent) {
                    int weight = r.nextInt(1000) + 1;
                    graph.connect(i, j, weight);
                }
            }
        }

        System.out.println("second graph:\n" + graph.toString());
        return graph;
    }

    // construct the cycle graph to maintain connected
    private static Graph cycleGraph(int totalVertex) {
        Graph graph = new Graph(totalVertex);
        Random r = new Random();

        for (int i = 0; i < totalVertex - 1; i++) {
            int weight = r.nextInt(1000) + 1;
            graph.connect(i, i + 1, weight);
        }

        // connect the last one to the first one
        int weight = r.nextInt(1000) + 1;
        graph.connect(totalVertex - 1, 0, weight);

        System.out.println("Initial cycle graph:\n" + graph.toString());
        return graph;
    }
}
