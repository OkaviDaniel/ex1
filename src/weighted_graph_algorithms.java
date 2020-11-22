package ex1.src;
import java.util.List;
/**
 * This interface represents an Undirected (positive) Weighted Graph Theory algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected();
 * 3. double shortestPathDist(int src, int dest);
 * 4. Listof nodes shortestPath(int src, int dest);
 * 5. Save(file);
 * 6. Load(file);
 *
 * @author boaz.benmoshe
 *
 */
public interface weighted_graph_algorithms {
    
    public void init(weighted_graph g);

    
    public weighted_graph getGraph();
    
    public weighted_graph copy();
    
    public boolean isConnected();
    
    public double shortestPathDist(int src, int dest);
    
    public List<node_info> shortestPath(int src, int dest);

    
    public boolean save(String file);

   
    public boolean load(String file);
}