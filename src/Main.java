import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("The Network Optimization demo begins:...");

        int V = 5000, degree = 6, edgePercent = 20; // assigned the default value
        Random r = new Random();

        switch(args.length) {
            case 3:
                edgePercent = Integer.valueOf(args[2]);
            case 2:
                degree = Integer.valueOf(args[1]);
            case 1:
                V = Integer.valueOf(args[0]);
        }

        long startTime, endTime;
        int maxBandwidth;

        for (int i = 1; i <= 5; i++) {
            System.out.println("\n\n\t\tThe " + i + " th pairs of graph:");
            Graph g1 = GraphGeneration.firstGraphGeneration(V, degree);
            Graph g2 = GraphGeneration.secondGraphGeneration(V, edgePercent);

            for (int j = 0; j < 5; j++) {
                int source   = r.nextInt(V);
                int terminal = r.nextInt(V);
                System.out.println("\n\ts = " + source + ", t = " + terminal);

                System.out.println("Dijkstra without heap:");

                startTime = System.currentTimeMillis();
                maxBandwidth = Dijkstra.maxBandwidthPathNoHeap(g1, source, terminal);
                System.out.print("  first graph: bw = " + maxBandwidth);
                endTime = System.currentTimeMillis();
                System.out.println("  Running time: " + (endTime - startTime) + "ms");

                startTime = System.currentTimeMillis();
                maxBandwidth = Dijkstra.maxBandwidthPathNoHeap(g2, source, terminal);
                System.out.print("  second graph: bw = " + maxBandwidth);
                endTime = System.currentTimeMillis();
                System.out.println("  Running time: " + (endTime - startTime) + "ms");

                System.out.println("\nDijkstra with heap:");

                startTime = System.currentTimeMillis();
                maxBandwidth = Dijkstra.maxBandwidthPath(g1, source, terminal);
                System.out.print("  first graph: bw = " + maxBandwidth);
                endTime = System.currentTimeMillis();
                System.out.println("  Running time: " + (endTime - startTime) + "ms");

                startTime = System.currentTimeMillis();
                maxBandwidth = Dijkstra.maxBandwidthPath(g2, source, terminal);
                System.out.print("  second graph: bw = " + maxBandwidth);
                endTime = System.currentTimeMillis();
                System.out.println("  Running time: " + (endTime - startTime) + "ms");

                System.out.println("\nKruskal:");

                startTime = System.currentTimeMillis();
                maxBandwidth = Kruskal.maxBandwidthPath(g1, source, terminal);
                System.out.print("  first graph: bw = " + maxBandwidth);
                endTime = System.currentTimeMillis();
                System.out.println("  Running time: " + (endTime - startTime) + "ms");

                startTime = System.currentTimeMillis();
                maxBandwidth = Kruskal.maxBandwidthPath(g2, source, terminal);
                System.out.print("  second graph: bw = " + maxBandwidth);
                endTime = System.currentTimeMillis();
                System.out.println("  Running time: " + (endTime - startTime) + "ms");
            }
        }
    }
}
