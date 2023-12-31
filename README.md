# CSE 373: Graphs and Shortest Paths
### Objective
Develop a graph representation and use it to implement Dijkstra’s algorithm for finding shortest paths. 

### Material
Six java files:
- [Vertex.java](https://github.com/cmunwong/Graphs-and-Shortest-Paths/blob/main/Vertex.java): Vertex class. 
- [Edge.java](https://github.com/cmunwong/Graphs-and-Shortest-Paths/blob/main/Edge.java): Edge class. 
- [Path.java](https://github.com/cmunwong/Graphs-and-Shortest-Paths/blob/main/Path.java): Class with two fields for returning the result of a shortest-path computation.
- [Graph.java](https://github.com/cmunwong/Graphs-and-Shortest-Paths/blob/main/Graph.java): Graph interface. 
- [MyGraph.java](https://github.com/cmunwong/Graphs-and-Shortest-Paths/blob/main/MyGraph.java): Implementation of the Graph interface.
- [FindPaths.java](https://github.com/cmunwong/Graphs-and-Shortest-Paths/blob/main/FindPaths.java): A client of the Graph interface.

Two text files: An example graph in the correct input format.
- [vertex.txt](https://github.com/cmunwong/Graphs-and-Shortest-Paths/blob/main/vertex.txt)
- [edge.txt](https://github.com/cmunwong/Graphs-and-Shortest-Paths/blob/main/edge.txt)

### Part 1: Graph Implementation
MyGraph class: Implement a graph representation for computing shortest paths in Part 2 that protects its abstraction from bad clients.
- Ignores repeated vertices in the collection. 
- Ignores if an edge appears redundantly with the same weight.
- Throws IllegalArgumentException if edge weight is negative.
- Throws IllegalArgumentException if edge involves vertex with label that is not in the vertices of the graph.
- Clients of a graph cannot break the abstraction by adding edges, making illegal weights, etc.

### Part 2: Dijkstra’s Shortest Path Algorithm
MyGraph class: Implement the shortestPath method to return the lowest-cost path from its first argument to its second argument.
- CASE 1: If the start and end vertex are equal, return a path containing one vertex and a cost of 0.
- CASE 2: If there is no path, return null.
- CASE 3: If the path contains at least two vertices – the start and end vertices and any other vertices along the lowest-cost path, reverses vertices in the order they appear on the path.
  
FindPaths.java:  
- Reads two data files and creates a representation of the graph.
- Prints out the graph’s vertices and edges.
- Loops the graph repeatedly and allows the user to ask shortest-path questions by entering two vertex names.

The part you need to add is to take these vertex names, call shortestPath, and print out the result. Your output should be as follows:
- If the start and end vertices are X and Y, first print a line Shortest path from X to Y:
- If there is no path from the start to end vertex, print exactly one more line does not exist
- Else, print exactly two more lines. On the first additional line, print the path with vertices separated by spaces. On the second additional line, print the cost of the path.

The FindPaths code expects two input files in a particular format. The names of the files are passed as command-line arguments. 

The provided files vertex.txt and edge.txt have the right format to serve as one (small) example data set where the vertices are 3-letter airport codes. 
Here is the file format:
- The file of vertices (the first argument to the program) has one line per vertex and each line contains a string with the name of a vertex.
- The file of edges (the second argument to the program) has three lines per directed edge (so lines 1-3 describe the first edge, lines 4-6 describe the second edge, etc.):
- The first line gives the source vertex.
- The second line gives the destination vertex.
- The third line is a string of digits that give the weight of the edge (this line should be converted to a number to be stored in the graph).

Note: data files represent directed graphs
- If there is an edge from A to B there may or may not be an edge from B to A.
- If there is an edge from A to B and an edge from B to A, the edges may or may not have the same weight.
