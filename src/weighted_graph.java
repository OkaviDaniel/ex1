package ex1.src;
import java.util.Collection;

/**
 * This interface represents an undirectional weighted graph.
 * It should support a large number of nodes (over 10^6, with average degree of 10).
 * The implementation should be based on an efficient compact representation
 * (should NOT be based on a n*n matrix).
 *
 */
public interface weighted_graph {
   
    public node_info getNode(int key);
   
    public boolean hasEdge(int node1, int node2);
    
    public double getEdge(int node1, int node2);
    
    public void addNode(int key);
   
    public void connect(int node1, int node2, double w);
    
    public Collection<node_info> getV();
    
    public Collection<node_info> getV(int node_id);
    
    public node_info removeNode(int key);
    
    public void removeEdge(int node1, int node2);
    /** return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     * @return
     */
    public int nodeSize();
    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     * @return
     */
    public int edgeSize();
    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     * @return
     */
    public int getMC();
}