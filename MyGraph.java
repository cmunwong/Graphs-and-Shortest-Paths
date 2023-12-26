/*
 * Name: Sze Mun Wong
 * Student ID: 146845
 * Class: CSE 373
 * Homework: 5
 */
 
import java.util.*;
 
/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    // you will need some private fields to represent the graph
    // you are also likely to want some private helper methods
 
    //TODO YOUR CODE HERE
    private List<Vertex> vertexList;    // collection of vertices
    private List<Edge> edgeList;        // collection of edges
 
    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
 
    // initialize the collection of vertices and edges with the given collections, ignore the redundant information
    // throw IllegalArgumentException if edge weight is negative
    // throw IllegalArgumentException if edge involves vertex with label that is not vertex of the graph
 
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
        //TODO YOUR CODE HERE
 
        // initialize vertexList with the given collection of vertices
        // ignore repeated vertices
        vertexList = new ArrayList<Vertex>();
        for(Vertex vertex:v){
            if (!vertexList.contains(vertex)){
                vertexList.add(vertex);
            }
        }
 
        // initialize edgeList with the given collection of edges
        // throw IllegalArgumentException if edge weight is negative
        // throw IllegalArgumentException if edge involves vertex with label that is not vertex of the graph
        // ignore repeated edges
        edgeList = new ArrayList<Edge>();
        for(Edge edge:e){
            if(edge.getWeight() < 0){
                throw new IllegalArgumentException("weight is negative:" + edge.toString());
            }
 
            if(!vertexList.contains(edge.getSource())){
                throw new IllegalArgumentException("from does not exist" + edge.getSource().toString());
            }
 
            if (!vertexList.contains(edge.getDestination())){
                throw new IllegalArgumentException("to does not exist" + edge.getDestination().toString());
            }
 
            if (!edgeList.contains(edge)){
                edgeList.add(edge);
            }
        }
    }
 
    /**
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
        //TODO YOUR CODE HERE
        return vertexList;
    }
 
    /**
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
        //TODO YOUR CODE HERE
        return edgeList;
    }
 
    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
        //TODO YOUR CODE HERE
 
        // throws IllegalArgumentException if v does not exist
        if(!vertexList.contains(v)){
            throw new IllegalArgumentException("v does not exist");
        }
 
        // initialize the collection of vertices adjacent to v
        List<Vertex> adjList = new ArrayList<Vertex>();
        for (Edge e:edgeList){
            if(e.getSource().equals(v)){
                adjList.add(e.getDestination());
            }
        }
 
        // return the collection of vertices adjacent to v
        return adjList;
    }
 
    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph,
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {
        //TODO YOUR CODE HERE
        // throws IllegalArgumentException if a or b does not exist
        if(!vertexList.contains(a)||!vertexList.contains(b)){
            throw new IllegalArgumentException("a or b do not exist");
        }
 
        // return cost of edge if there is a directed edge from a to b in the graph
        for (Edge e:edgeList){
            if(e.getSource().equals(a) && e.getDestination().equals(b)){
                return e.getWeight();
            }
        }
 
        // return -1 otherwise
        return -1;
    }
 
    /**
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path.  Assumes all edge weights are nonnegative.
     * Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *   and contains a (first) and b (last) and the cost is the cost of
     *   the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public Path shortestPath(Vertex a, Vertex b) {
        //TODO YOUR CODE HERE (you might comment this out this method while doing Part 1)
 
        List<Vertex> pathList = new ArrayList<Vertex>();    // list of path
 
        // throws IllegalArgumentException if a or b does not exist
        if(!vertexList.contains(a)||!vertexList.contains(b)){
            throw new IllegalArgumentException("a or b do not exist");
        }
 
        // case 1: the start and end vertex are equal
        else if(a.equals(b)){
            pathList.add(a);
            Path path = new Path(pathList, 0);
            return path;
        }
 
        else{
            HashMap<Vertex, Integer> cost = new HashMap<Vertex, Integer>();      // value is the shortest distance from a to the corresponding key
            HashMap<Vertex, Vertex> prev = new HashMap<Vertex, Vertex>();        // value is the previous vertex of the corresponding key according to the shortest path
            HashSet<Vertex> unknown = new HashSet<Vertex>();                     // set of unknown vertices
            Stack<Vertex> reversePath = new Stack<Vertex>();                     // helper to get the list of path in order
 
            // Dijkstra's algorithm
 
            // initialize cost with all values equal to infinity
            // initialize prev with all values equal to null
            // put all vertices to the unknown set
            for (Vertex v:vertexList){
                cost.put(v, Integer.MAX_VALUE);
                prev.put(v, null);
                unknown.add(v);
            }
            cost.put(a, 0);
 
            while(!unknown.isEmpty()){
                int smallestCost = Integer.MAX_VALUE;    // smallest distance from a in the unknown set
                Vertex temp = null;                      // vertex with smallest distance from a
                for (Vertex v:unknown){
                    if(cost.get(v) <= smallestCost){
                        smallestCost = cost.get(v);
                        temp = v;
                    }
                }
                unknown.remove(temp);
 
                for (Edge e:edgeList){
                    if(e.getSource().equals(temp) && unknown.contains(e.getDestination())){
                        if (cost.get(temp) + e.getWeight() < cost.get(e.getDestination())){        // <=
                            cost.put(e.getDestination(), cost.get(temp) + e.getWeight());          // update cost with shortest distance found
                            prev.put(e.getDestination(), temp);                                    // update prev
                        }
                    }
                }
            }
 
            // shortest path from b to a
            Vertex curr = b;
            reversePath.push(curr);
            while (!curr.equals(a)){
                // case 2: no path
                if (prev.get(curr) == null){
                    return null;
                }
                reversePath.push(prev.get(curr));
                curr = prev.get(curr);
            }
 
            // reverse the order back to the order they appear (from a to b)
            while(!reversePath.isEmpty()){
                pathList.add(reversePath.pop());
            }
 
            // case 3: the path contains at least two vertices
            Path path = new Path(pathList, cost.get(b));
            return path;
        }
    }
}
