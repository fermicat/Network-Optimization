import java.util.HashSet;

public class Dijkstra {
    // WHITE - never visit; GREY - in visiting; BLACK - visited
    enum Color {
        WHITE, GREY, BLACK
    }

    private static Color[] status;
    private static int[] dad;
    private static int[] bw;
    private static MaxHeap heap;
    private static HashSet<Integer> fringeSet;

    /**
     * the Dijkstra Algorithm to find max Bandwidth Path without heap
     * @param graph     the graph to find
     * @param source    source s
     * @param terminal  terminal t
     * @return the bandwidth of t
     */
    public static int maxBandwidthPathNoHeap(Graph graph, int source, int terminal) {
        int V = graph.V();
        status = new Color[V];
        dad = new int[V];
        bw  = new int[V];
        fringeSet = new HashSet<>();

        initialize(graph, source, false);

        // Main procedure of Dijkstra
        while (!fringeSet.isEmpty()) {
            // pick a fringe with max-bw
            int maxBwIdx = -1;
            int maxBwVal = Integer.MIN_VALUE;
            for (int v : fringeSet) {
                if (bw[v] > maxBwVal) {
                    maxBwIdx = v;
                    maxBwVal = bw[v];
                }
            }
            fringeSet.remove(maxBwIdx);
            status[maxBwIdx] = Color.BLACK;

            // visit its adj vertex
            for (Edge e : graph.adj[maxBwIdx]) {
                int w = e.getEnd(maxBwIdx);
                int minBwValue = Math.min(bw[maxBwIdx], e.weight);

                // un-visited vertex
                if (status[w] == Color.WHITE) {
                    dad[w] = maxBwIdx;
                    bw[w] = minBwValue;
                    status[w] = Color.GREY;     // put into fringe
                    fringeSet.add(w);
                }
                // in-fringe but optimal path
                else if (status[w] == Color.GREY && bw[w] < minBwValue) {
                    dad[w] = maxBwIdx;
                    bw[w] = minBwValue;
                }
            }
        }

        return bw[terminal];
    }

    /**
     * the Dijkstra Algorithm to find max Bandwidth Path with heap
     * @param graph     the graph to find
     * @param source    source s
     * @param terminal  terminal t
     * @return the bandwidth of t
     */
    public static int maxBandwidthPath(Graph graph, int source, int terminal) {
        int V = graph.V();
        status = new Color[V];
        dad = new int[V];
        bw  = new int[V];
        heap = new MaxHeap(V);

        initialize(graph, source, true);

        // Main procedure of Dijkstra
        while (!heap.isEmpty()) {
            // pick a fringe with max-bw
            int maxBwIdx = heap.maximum();
            status[maxBwIdx] = Color.BLACK;
            heap.delete(1);

            // visit its adj vertex
            for (Edge e : graph.adj[maxBwIdx]) {
                int w = e.getEnd(maxBwIdx);
                int minBwValue = Math.min(bw[maxBwIdx], e.weight);

                // un-visited vertex
                if (status[w] == Color.WHITE) {
                    dad[w] = maxBwIdx;
                    bw[w] = minBwValue;
                    status[w] = Color.GREY;
                    heap.insert(w, bw[w]);
                }
                // visited but optimal path
                else if (status[w] == Color.GREY && bw[w] < minBwValue) {
                    dad[w] = maxBwIdx;
                    bw[w] = minBwValue;
                    heap.deleteVertex(w);
                    heap.insert(w, bw[w]);
                }
            }
        }

        return bw[terminal];
    }

    // initialization for Dijkstra
    // isHeap - flag of using heap or not
    private static void initialize(Graph graph, int source, boolean isHeap) {
        for (int i = 0; i < graph.V(); i++) {
            status[i] = Color.WHITE;
            bw[i] = Integer.MAX_VALUE;
        }

        // put source point in-tree
        status[source] = Color.BLACK;
        dad[source] = -1;

        // visit the point adj to the source
        for (Edge e : graph.adj[source]) {
            int w = e.getEnd(source);
            status[w] = Color.GREY;
            dad[w] = source;
            bw[w] = e.weight;
            if (isHeap) heap.insert(w, bw[w]);
            else fringeSet.add(w);
        }
    }
}
