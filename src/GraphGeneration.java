import java.util.Random;

public class GraphGeneration {

    private static final int totalVertex = 5000;
    private static final int vertexNum   = 6;
    private static final int edgePercent = 20;


    public Graph firstGraphGeneration() {
        Graph graph = new Graph(totalVertex);
        Random r = new Random();
        int totalEdge = 0;

        // average degree
        while (totalEdge < totalVertex * vertexNum / 2) {
            int i = r.nextInt(totalVertex);
            int j = r.nextInt(totalVertex);

            if (i != j) {
                int weight  = r.nextInt(100) + 1;
                Edge edge = new Edge(i, j, weight);
                if (!graph.adj[i].contains(edge)) {
                    graph.connect(i, j, weight);
                    totalEdge++;
                }
            }
        }


        return graph;
    }


    public Graph secondGraphGeneration() {
        Graph graph = new Graph(totalVertex);
        Random r = new Random();

        for (int i = 0; i < totalVertex; i++) {
            for (int j = i + 1; j < totalVertex; j++) {
                // generate random int [1, 100]
                int weight  = r.nextInt(100) + 1;
                int connect = r.nextInt(100) + 1;

                // ensure ~ 20% of edge will be connected
                if (connect <= edgePercent) {
                    graph.connect(i, j, weight);
                }
            }
        }

        return graph;
    }
}
