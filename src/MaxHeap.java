public class MaxHeap {

    // use array to store the tree
    private int[] name;        // store vertex name
    private int[] value;       // store vertex bandwidth
    private Integer[] nameToIndex; // a mapping from name to index
    private int size;
    private final int total;

    // constructor
    public MaxHeap(int totSize) {
        total = totSize;
        name  = new int[totSize + 1];
        value = new int[totSize + 1];
        nameToIndex = new Integer[totSize];
        size  = 0;
        // virtual node with bw - infinity
        name[1] = total;
        value[total] = Integer.MIN_VALUE;
    }

    // return the max element (name)
    public int maximum() {
        return name[1];
    }

    // insert certain element and re-construct the heap
    public void insert(int v, int bw) {
        size++;
        name[size] = v;
        value[v] = bw;
        nameToIndex[v] = size;

        heapify(size);
    }

    // delete element by index
    public void delete(int index) {
        if (index > size || index < 1) {
            throw new IndexOutOfBoundsException("Heap out of bound");
        }
        nameToIndex[name[index]] = null;
        name[index] = name[size];
        nameToIndex[name[index]] = index;

        name[size] = total; // virtual node
        size--;
        heapify(index);
    }

    // delete element by name
    public void deleteVertex(int vertex) {
        int index = nameToIndex[vertex];
        delete(index);
    }

    // to see if heap is empty
    public boolean isEmpty() {
        return (size == 0);
    }

    // procedure for construct heap
    private void heapify(int k) {
        int left   = 2 * k;
        int right  = 2 * k + 1;
        int parent = k / 2;

        // pushing up
        if (k > 1 && value[name[k]] > value[name[parent]]) {
            int cur = k;
            while (cur > 1 && value[name[cur]] > value[name[parent]]) {
                swap(cur, parent);
                cur = parent;
                parent = cur / 2;
            }
        }
        // pushing down
        else if (!isLeaf(k) && value[name[k]] < Math.max(value[name[left]], value[name[right]])) {
            int cur = k;
            while (!isLeaf(cur) && value[name[cur]] < Math.max(value[name[left]], value[name[right]])) {
                if (right > size) {
                    swap(cur, left);
                    break;
                }
                else if (value[name[left]] > value[name[right]]) {
                    swap(cur, left);
                    cur = left;
                }
                else {
                    swap(cur, right);
                    cur = right;
                }
                left = 2 * cur;
                right = 2 * cur + 1;
            }
        }
    }

    // helper function
    private void swap(int i, int j) {
        int temp;
        temp = name[i];
        name[i] = name[j];
        name[j] = temp;

        nameToIndex[name[i]] = i;
        nameToIndex[name[j]] = j;
    }

    private boolean isLeaf(int index) {
        return (index > size / 2);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("The heap: \n");
        for (int i = 1; i <= size; i++) {
            s.append(name[i] + " (" + value[name[i]] + "), ");
        }
        s.append("\n");

        return s.toString();
    }
}