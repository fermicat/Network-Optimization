/*
 * @description this class is designed for edge of graph
 */

public class Edge {
    private final int start;
    private final int end;
    private final int weight;

    // constructor
    public Edge(int start, int end, int weight) {
        if (start < 0 || end < 0 || weight <= 0) {
            throw new IllegalArgumentException("The vertex and weight are non-negative!");
        }
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    // the API to return properties of class Edge
    public int getWeight() {
        return weight;
    }

    public int getEnd(int x) {
        if (x == start) return end;
        else if (x == end) return start;
        else {
            throw new IllegalArgumentException("Not on this edge");
        }
    }
}
