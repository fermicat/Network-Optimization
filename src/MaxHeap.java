public class MaxHeap {

    // use array to store the tree
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


    private void heapfy(int k) {
        int left  = 2 * k;
        int right = 2 * k + 1;
        int large = k;
        int tempLarge = k;

        if (left <= size && value[left] > value[k]) {
            large = left;
        }
        if (right <= size && value[right] > value[k]) {
            tempLarge = right;
        }
        if (value[large] < value[tempLarge]) {
            large = tempLarge;
        }

        if (large != k) {
            swap(large, k);
            heapfy(large);
        }
    }

    private void swap(int i, int j) {
        int temp;
        temp = vertex[i];
        vertex[i] = vertex[j];
        vertex[j] = temp;
        temp = value[i];
        value[i] = value[j];
        value[j] = temp;
    }
}