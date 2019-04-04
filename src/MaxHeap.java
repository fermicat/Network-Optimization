public class MaxHeap {

    private int[] vertex;
    private int[] value;
    private int size;

    // constructor
    public MaxHeap(int totSize) {
        vertex = new int[totSize + 1];
        value  = new int[totSize + 1];
        size = 0;
    }

    public int maximum() {
        return vertex[1];
    }

    public void insert(int v, int bw) {
        size++;
        vertex[size] = v;
        value[size] = bw;
        heapfy(size);
    }

    public void delete(int index) {
        vertex[index] = vertex[size];
        value[index] = value[size];
        size--;
        heapfy(index);
    }
}