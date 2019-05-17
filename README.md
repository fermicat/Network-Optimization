# Network-Optimization

In this project, we are going to implement a network routing protocol using the methods of Dijkstra and Kruskal algorithms with max-heap and gragh data structures.

The protocol is designed for varies network optimization problems, especially at the background of data optimization scenario and path route problems.


## Description of APIs
The details of each class are given.

The class `Edge` is designed for representing edges, the main properties directly given by the public final variables:
```
class Edge {
	public final int start;
	public final int end;
	public final int weight;
}
```

Similarly, the class `Graph` represents graphs. We use a `HashSet` array to store the adjacent edges of each vertex:
```
class Graph {
	public HashSet<Edge>[] adj;
	public int V();			// return total vertices 
	public int E();			// return total edges
  	public void connect(int start, int end, int weight); // connect two ends
	public String toString(); 	// return the String form
} 
```

The static class `GraphGeneration` is designed for two graph generators:
```
public static Graph firstGraphGeneration( int V, int degree);
public static Graph secondGraphGeneration(int V, int percent);
```

To ensure the graph is connected, we run a cycle procedure at first to ensure the graph is connected with random assigned weight edges.

The class `MaxHeap` is a max-heap data structure for Dijkstra Algorithms. In this class, we use an array `int[] name` to store the vertices in the heap, and an array `int[] value` to store the value of each vertex, which can be called as `value[name[i]]`. Besides, in order to find the certain vertex in the heap quickly, we use a mapping array `Integer[] nameToIndex` to store the index of heap for each vertex, a null value means that the vertex is out of heap. The APIs of this class are:
```
class MaxHeap {
	public int maximum();			// return the maximum, O(1)
	public void insert(int v, int bw);	// insert the edge, O(log n)
	public void delete(int index);		// delete by index, O(log n)
	public void deleteVertex(int v);	//delete by vertex name, O(log n)
	public boolean isEmpty();		// return true if heap is empty
}
```
The `UnionFind` class is for generating the Maximum Spanning Tree in Kruskal Algorithms, in this protocol, the Union-Find data structure is implemented with Weighted and Optimization (compare the rank when doing the union operation) and Path Compression Optimization (compress the path tree when doing the find operation), which makes both union and find operations in the time complexity `O(log*(N))`, very very nearly but not equal to `O(1)`:
```
class UnionFind{
	public int find(int p);		   // find parent, O(log*(N))
	public void union(int p, int q);   // union two ends, O(log*(N))
}
```
The static class `Dijkstra` and `Kruskal` are non-instantiable class with static methods, which are the implementations of our main routine algorithms: Dijkstra Algorithm without heap, Dijkstra Algorithm with heap and Kruskal Algorithm.


## Compile and Test
Compile and run the program in the following way:
```
$ javac Main.java
$ java Main 5000 6 20
# or directly run without arguments as they are default parameters:
$ java Main
```

The first command line argument is the total vertices number, second one is the average degree in the first graph, third one is the percentage that connected to other vertices in the second graph. If the argument is missing, we use the default value.

For the program, the JDK 8 or above version is required, because when sorting the edges in the Kruskal class, a lambda expression is applied to the Collections.sort(List, function) API.
