# CSE 373: Graphs and Shortest Paths
### Background
For this assignment, you will develop a graph representation and use it to implement Dijk- stra’s algorithm for finding shortest paths. Unlike previous assignments, you will use some classes in the Java standard libraries, gaining valuable experience reading documentation and understanding APIs.

You may use anything in the Java standard collections (or anything else in the standard library) for any part of this assignment. Take a look at the Java API as you are thinking about your solutions. At the very least, look at the Collection and List inter- faces to see what operations are allowable on them and what classes implement those interfaces.

### Given Material
Six java files and two text files are provided for this assignment.
- Vertex.java: Vertex class. You may add methods and/or variables if you wish. Do not modify existing methods.
- Edge.java: Edge class. You may add methods and/or variables if you wish. Do not modify existing methods.
- Path.java: Class with two fields for returning the result of a shortest-path computa- tion. Do not modify.
- Graph.java: Graph interface. Do not modify.
- MyGraph.java: Implementation of the Graph interface: you will need to fill in code here.
- FindPaths.java: A client of the graph interface: Needs small additions.
- vertex.txt and edge.txt: An example graph in the correct input format.

### Part 1: Graph Implementation
In this part of the assignment, you will implement a graph representation that you will use in Part 2. The structures covered in the lectures (adjacency list and matrix) work efficiently when you can access a vertex in constant time. Note that vertices have string labels and it is not trivial to put them in an array and access them in constant-time. In order to utilize constant-time access (like an array or matrix), consider using hash tables (check out the classes in the Map interface in Java) instead of arrays in your implementation of the graph. Everything you would do with an array, you can do with a hash table.

Add code to the provided-but-incomplete MyGraph class to implement the Graph in- terface. Do not change the arguments to the constructor of MyGraph and do not add other constructors. Otherwise, you are free to add things to the Vertex, Edge, and MyGraph classes, but please do not remove code already there and do not modify Graph.java. You may also create other classes if you find it helpful.

As always, your code should be correct (implement a graph) and efficient (in partic- ular, good asymptotic complexity for the requested operations), so choose a good graph representation for computing shortest paths in Part 2.

We will also grade your graph representation on how well it protects its abstraction from bad clients. In particular this means:

The constructor should check that the arguments make sense and throw an appropriate exception otherwise. You can define your own exceptions if you see fit. A couple of possible places to check for exceptions:

- The edges should involve only vertices with labels that are in the vertices of the graph. That is, there should be no edge from or to a vertex labeled A if there is no vertex with label A.
- Edge weights should not be negative.
- Do not throw an exception if the collection of vertices has repeats in it: If two ver- tices in the collection have the same label, just ignore the second one encountered as redundant information.
- Do throw an exception if the collection of edges has the same directed edge more than once with a different weight. Remember in a directed graph an edge from A to B is not the same as an edge from B to A. Do not throw an exception if an edge appears redundantly with the same weight; just ignore the redundant edge information.

It should not be possible for clients of a graph to break the abstraction by adding edges, making illegal weights, etc.

Other useful information:

- The Vertex and Edge classes have already defined an appropriate equals method (they also define hashCode appropriately). If you need to decide if two Vertex objects are “the same”, you probably want to use the equals method and not ==.
- You will likely want some sort of Map in your program so you can easily and efficiently look up information stored about some Vertex. (This would be much more efficient than, for example, having a Vertex[] and iterating through it every time you needed to look for a particular Vertex.)
-  As you are debugging your program, you may find it useful to print out your data structures. There are toString methods for Edge and Vertex. Remember that things like ArrayLists and Sets can also be printed.
