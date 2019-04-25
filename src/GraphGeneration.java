import java.util.Random;

public class GraphGeneration {

    private static int totalVertex = 5000;
    private static int vertexNum   = 6;
    private static int edgePercent = 20;


    public static Graph firstGraphGeneration() {
        Graph graph = cycleGraph();
        Random r = new Random();

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

        System.out.println("first graph:");
        graph.toString();
        return graph;
    }


    public static Graph secondGraphGeneration() {
        Graph graph = new Graph(totalVertex);
        Random r = new Random();

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

        System.out.println("first graph:");
        graph.toString();
        return graph;
    }


    private static Graph cycleGraph() {
        Graph graph = new Graph(totalVertex);
        Random r = new Random();

        for (int i = 0; i < totalVertex - 1; i++) {
            int weight = r.nextInt(1000) + 1;
            graph.connect(i, i + 1, weight);
        }

        // connect the last one to the first one
        int weight = r.nextInt(1000) + 1;
        graph.connect(totalVertex - 1, 0, weight);

        System.out.println("Initial cycle graph:");
        graph.toString();
        return graph;
    }
}
