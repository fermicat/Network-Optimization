/*
 * @description this class is designed for edge of graph
 */

public class Edge {
    private int start;
    private int end;
    private int weight;

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
    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}
