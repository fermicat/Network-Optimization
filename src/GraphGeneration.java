import java.util.Random;

public class GraphGeneration {

    private static final int totalVertex = 5000;
    private static final int vertrxNum   = 6;
    private static final int edgePercent = 20;


    public Graph firstGraphGenerator() {
        Graph graph = new Graph(totalVertex);
        Random r = new Random();

        return graph;
    }


    public Graph secondGraphGenerator() {
        Graph graph = new Graph(totalVertex);
        Random r = new Random();

        for (int i = 0; i < totalVertex; i++) {
            for (int j = i + 1; j < totalVertex; j++) {
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
