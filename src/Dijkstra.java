import java.util.HashSet;


public class Dijkstra {
    enum Status {
        UNSEEN, FRINGE, INTREE
    }

    private static Status[] status;
    private static int[] dad;
    private static int[] bw;
    private static MaxHeap heap;

    public static int maxBandwidthPathNoHeap(Graph graph, int source, int terminal) {
        // initialize the Dijkstra Algorithm
        int V = graph.V();
        status = new Status[V];
        dad = new int[V];
        bw  = new int[V];

        for (int i = 0; i < V; i++) {
            status[i] = Status.UNSEEN;
            bw[i] = Integer.MAX_VALUE;
        }

        // put source point in-tree
        status[source] = Status.INTREE;
        dad[source] = -1;

        // visit the point adj to the source
        HashSet<Edge> sourceEdgeSet = graph.adj[source];
        for (Edge e : sourceEdgeSet) {
            int w = e.getEnd(source);
            status[w] = Status.FRINGE;
            dad[w] = source;
            bw[w] = e.weight;
        }

        // Main procedure of Dijkstra
        while (status[terminal] != Status.INTREE) {

            // pick a fringe with max-bw
            int maxBwIdx = -1;
            int maxBwVal = Integer.MIN_VALUE;
            for (int i = 0; i < V; i++) {
                if (status[i] == Status.FRINGE && bw[i] > maxBwVal) {
                    maxBwVal = bw[i];
                    maxBwIdx = i;
                }
            }

            try {
                status[maxBwIdx] = Status.INTREE;
            }
            catch (Exception e) {
                System.out.println("maxBwIdx = " + maxBwIdx);
                throw new IllegalArgumentException("max Bw index illegal!");
            }

            HashSet<Edge> maxBwEdgeSet = graph.adj[maxBwIdx];
            for (Edge e : maxBwEdgeSet) {
                int w = e.getEnd(maxBwIdx);
                int minBwValue = Math.min(bw[maxBwIdx], e.weight);

                // un-visited vertex
                if (status[w] == Status.UNSEEN) {
                    dad[w] = maxBwIdx;
                    status[w] = Status.FRINGE;
                    bw[w] = minBwValue;
                }
                // visited but optimal path
                else if (status[w] == Status.FRINGE && bw[w] < minBwValue) {
                    dad[w] = maxBwIdx;
                    bw[w] = minBwValue;
                }
            }
        }

        return bw[terminal];
    }


    public static int maxBandwidthPath(Graph graph, int source, int terminal) {
        // initialize the Dijkstra Algorithm
        int V = graph.V();
        status = new Status[V];
        dad = new int[V];
        bw  = new int[V];
        heap = new MaxHeap(V);

        for (int i = 0; i < V; i++) {
            status[i] = Status.UNSEEN;
            bw[i] = Integer.MAX_VALUE;
        }

        // put source point in-tree
        status[source] = Status.INTREE;
        dad[source] = -1;

        // visit the point adj to the source and put into heap
        HashSet<Edge> sourceEdgeSet = graph.adj[source];
        for (Edge e : sourceEdgeSet) {
            int w = e.getEnd(source);
            status[w] = Status.FRINGE;
            dad[w] = source;
            bw[w] = e.weight;
            heap.insert(w, bw[w]);
        }

        // Main procedure of Dijkstra
        while (status[terminal] != Status.INTREE) {
            // pick a fringe with max-bw
            int maxBwIdx = heap.maximum();
            status[maxBwIdx] = Status.INTREE;
            heap.delete(1);

            HashSet<Edge> maxBwEdgeSet = graph.adj[maxBwIdx];
            for (Edge e : maxBwEdgeSet) {
                int w = e.getEnd(maxBwIdx);
                int minBwValue = Math.min(bw[maxBwIdx], e.weight);

                // un-visited vertex
                if (status[w] == Status.UNSEEN) {
                    dad[w] = maxBwIdx;
                    status[w] = Status.FRINGE;
                    bw[w] = minBwValue;
                    heap.insert(w, bw[w]);
                }
                // visited but optimal path
                else if (status[w] == Status.FRINGE && bw[w] < minBwValue) {
                    heap.delete(w);         /// @dev w is not index?

                    dad[w] = maxBwIdx;
                    bw[w] = minBwValue;

                    // need to remove the element in pre-insert heap
                    /*
                    for (int i = 1; i <= heap.getSize(); i++) {
                        if (heap.get(i) == w) {
                            heap.delete(i);
                            break;
                        }
                    }*/
                    heap.insert(w, bw[w]);
                }
            }
        }

        return bw[terminal];
    }
}
