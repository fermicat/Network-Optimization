import java.util.*;



public class Kruskal {

    // WHITE - never visit; GREY - in queue; BLACK - out of queue
    enum Color {
        WHITE, GREY, BLACK
    }

    private static UnionFind record;
    private static int[] bw;
    private static int[] dad;
    private static Graph maxSpanTree;
    private static ArrayList<Edge> edgeList;

    /**
     * Kruskal Algorithm to find maximum bandwidth path
     * @param graph     the graph to find
     * @param source    source point s
     * @param terminal  terminal point t
     * @return the bandwidth of terminal t
     */
    public static int maxBandwidthPath(Graph graph, int source, int terminal) {

        int V = graph.V();
        maxSpanTree = new Graph(V);
        edgeList = new ArrayList<>();

        // sort edges with decreasing order
        sortEdge(graph);
        record = new UnionFind(V);

        // construct the Maximum Spanning Tree
        constructMST();

        // BFS
        dad = new int[V];
        bw  = new int[V];
        bfs(source, V);

        return bw[terminal];
    }

    // collect all edges and sorting
    private static void sortEdge(Graph graph) {
        // collect all edges
        for (int i = 0; i < graph.V(); i++) {
            for (Edge e : graph.adj[i]) {
                // require end > start, to avoid some edge in two form
                if (e.end > i) {
                    edgeList.add(e);
                }
            }
        }
        // sort edges in decreasing order
        /// @dev use lambda expression, require language level 8
        Collections.sort(edgeList, (a, b) -> (a.weight != b.weight ? b.weight - a.weight :
                (a.start != b.start ? a.start - b.start: a.end - b.end) ));
    }

    // construct Maximum Spanning Tree with Union-Find
    private static void constructMST() {
        for (Edge e : edgeList) {
            // find two parents of two edge endpoints
            int p1 = record.find(e.start);
            int p2 = record.find(e.end);
            if (p1 != p2) {
                maxSpanTree.connect(e.start, e.end, e.weight);
                record.union(e.start, e.end);
            }
        }
    }

    // BFS for the entire MST
    private static void bfs(int source, int V) {
        Color[] status = new Color[V];
        Queue<Integer> queue = new LinkedList<>();

        // initialize
        for (int i = 0; i < V; i++) {
            status[i] = Color.WHITE;
            dad[i] = -1;
            bw[i] = Integer.MAX_VALUE;
        }
        status[source] = Color.GREY;
        queue.offer(source);

        while(!queue.isEmpty()) {
            int v = queue.poll();
            HashSet<Edge> edgeSet = maxSpanTree.adj[v];
            for (Edge e : edgeSet) {
                int w = e.getEnd(v);
                if (status[w] == Color.WHITE) {
                    status[w] = Color.GREY;
                    queue.offer(w);
                    dad[w] = v;
                    bw[w] = Math.min(bw[v], e.weight);
                }
                else if (status[w] == Color.GREY && bw[w] < Math.min(bw[v], e.weight)) {
                    dad[w] = v;
                    bw[w] = Math.min(bw[v], e.weight);
                }
            }
            status[v] = Color.BLACK;
        }
    }

}
