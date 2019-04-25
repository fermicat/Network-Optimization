import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("The Network Optimization demo begins:...");
        Random r = new Random();
        int V = 5000, degree = 6, edgePercent = 20;
        long startTime, endTime;
        int maxbandwidth;

        for (int i = 0; i < 5; i++) {
            System.out.println("The " + i + "pairs of graph:");
            Graph g1 = GraphGeneration.firstGraphGeneration(V, degree);
            Graph g2 = GraphGeneration.secondGraphGeneration(V, edgePercent);
            for (int j = 0; j < 5; j++) {
                int source   = r.nextInt(V);
                int terminal = r.nextInt(V);
                System.out.println("s = " + source + ", t = " + terminal);

                startTime = System.currentTimeMillis();
                System.out.println("Dijkstra without heap:");
                maxbandwidth = Dijkstra.maxBandwidthPathNoHeap(g1, source, terminal);
                System.out.print("  first graph: bw = " + maxbandwidth);
                endTime = System.currentTimeMillis();
                System.out.println("  Running time: " + (endTime - startTime) + "ms");

                startTime = System.currentTimeMillis();
                maxbandwidth = Dijkstra.maxBandwidthPathNoHeap(g2, source, terminal);
                System.out.print("  second graph: bw = " + maxbandwidth);
                endTime = System.currentTimeMillis();
                System.out.println("  Running time: " + (endTime - startTime) + "ms");
            }
        }
    }
}
