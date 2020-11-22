package ex1.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * This class implements an Undirected (positive) Weighted Graph Theory algorithms.
 * The used algorithms are:
 * 		Breadth first search (BFS) algorithm.
 * 		Dijkstra's algorithm.
 * The purpose of using them:
 * 		BFS- 				 To be able to travel on the graph and check if the graph
 * 								 is connected or not.
 * 		Dijkstra's algorithm-To be able to find the shortest path between two vertices. 
 * @author Daniel
 *
 */
public class WGraph_Algo implements weighted_graph_algorithms {
	private weighted_graph g;

	public WGraph_Algo(){
		g = new WGraph_DS();
	}
	
	/**
     * Init the graph on which this set of algorithms operates on.
     * @param g  	The wanted graph
     */
	@Override
	public void init(weighted_graph g) {
		this.g = g;
	}

	/**
     * Return the underlying graph of which this class works.
     * @return      weighted_graph
     */
	@Override
	public weighted_graph getGraph() {
		return g;
	}

	/**
     * Compute a deep copy of this weighted graph.
     * @return 		weighted_graph
     */
	@Override
	public weighted_graph copy()
	{
		WGraph_DS ans = new WGraph_DS();
		for(node_info n : g.getV())
		{			
			ans.addNode(n.getKey());
			if(g.getV(n.getKey()).size()>0)
			{
				for(node_info a : g.getV(n.getKey()))
				{
					ans.connect(n.getKey(),a.getKey(),g.getEdge(n.getKey(),a.getKey()));
				}
			}
		}
		ans.setNumOfEdges(g.edgeSize());
		return ans;
	}

	/**
     * Returns true if and only if there is a valid path from every node to each
     * other.
     * Algorithm used: Breadth first search.
     * @return   boolean
     */
	@Override
	public boolean isConnected()
	{		
		if(g.getV().size() == 0 || g.getV().size() == 1)    return true;
		else
		{			
			bfs(g.getV().iterator().next());
			boolean a = visitedAll();
			restoreNodes();
			return a;
		}
	}

	/**
     * returns the length of the shortest path between src to dest
     * If no such path returns -1.
     * @param src  start node
     * @param dest  end (target) node
     * @return  the distance
     */
	@Override
	public double shortestPathDist(int src, int dest)
	{    
		if(!(g.getV().contains(g.getNode(src))) ||  !(g.getV().contains(g.getNode(dest))))
		{
			return -1;
		}
		if(src == dest)
		{
			return 0;
		}
		else 
		{
			dijkstra(g.getNode(src),g.getNode(dest));
			double a = g.getNode(dest).getTag();
			if(a==Double.POSITIVE_INFINITY)
			{
				restoreNodes();
				return -1;
			}
			else
			{
				restoreNodes();
				return a;
			}
		}           
	}

	
	/**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src to n1 to n2 to ...dest
     * @param src - start node
     * @param dest - end (target) node
     * @return  	A list of the shortest path
     */
	@Override
	public List<node_info> shortestPath(int src, int dest)
	{			
			List<node_info> a = dijkstra(g.getNode(src),g.getNode(dest));
			restoreNodes();
			return a;		      
	}

	/**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
	@Override
	public boolean save(String file) {
		try{
			FileOutputStream myFile = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(myFile);
			oos.writeObject(this.g);
			oos.close();
			myFile.close();
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	 /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
	@Override
	public boolean load(String file) {
		try
		{
			FileInputStream myFile = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(myFile);
			this.g = (WGraph_DS)ois.readObject();
			System.out.println(g);
			ois.close();
			myFile.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}


	// ****************Private methods****************

	/**
	 * The breadth first search algorithm.
	 * The algorithm get a source node, and then move to the other nodes.
	 * If the graph is connected the algorithm visit all the nodes(vertices).
	 * @param src	node_info
	 */
	private void bfs(node_info src)
	{
		restoreNodes();
		if(g.getV().contains(src))
		{
			LinkedList<node_info> q = new LinkedList<node_info>();
			src.setInfo("Visited");
			q.add(src);
			while(!q.isEmpty()) {
				node_info temp = q.removeFirst();
				Collection<node_info> ni = g.getV(temp.getKey());
				for(node_info f : ni)
				{
					if(!f.getInfo().equals("Visited"))
					{
						f.setInfo("Visited");
						q.add(f);
					}
				}
			}
		}
	}

	/**
	 * Restore the nodes on the graph to their default settings.
	 */
	private void restoreNodes()
	{
		if(g.getV().size()>0)
		{
			Iterator<node_info> f = g.getV().iterator();
			while(f.hasNext())
			{
				node_info temp = f.next();
				temp.setTag(Double.POSITIVE_INFINITY);
				if(!temp.getInfo().equals("NotVisited"))
				{
					temp.setInfo("NotVisited");
				}
			}
		}
	}
	/**
	 * Returns true if the used algorithm visited all
	 * the nodes on the graph.
	 * @return  Boolean
	 */
	private boolean visitedAll()
	{
		Iterator<node_info> tmp = g.getV().iterator();
		while(tmp.hasNext())
		{
			if(!tmp.next().getInfo().equals("Visited"))
				return false;
		}
		return  true;
	}

	/**
	 * The dijkstra algorithm implemented by queue (That implemented by LinkedList)
	 * The algorithm get source node and destination node and find the
	 * 		Shortest path between them.
	 * If there is no path
	 * 		 returns null.
	 * Else - 
	 * 		returns a list of nodes contains {src,...,dest}.
	 * @param src			The source node
	 * @param dest			The destination node
	 * @return				List of node_info
	 */
	private List<node_info> dijkstra(node_info src, node_info dest)
	{
		restoreNodes();		
		//Creating a new queue
		LinkedList<WGraph_DS.NodeData> q = new LinkedList<>();
		//set the distance of the src to be zero
		src.setTag(0);
		q.add((WGraph_DS.NodeData)src);
		while(!q.isEmpty())
		{
			WGraph_DS.NodeData u = q.poll();

			for(node_info i : g.getV(u.getKey()))
			{
				WGraph_DS.NodeData v = (WGraph_DS.NodeData)i;
				if(!v.getInfo().equals("Visited"))
				{

					double t = u.getTag() + g.getEdge(u.getKey(), v.getKey());
					if(v.getTag() > t)
					{
						v.setTag(t);
						v.setPrevd(u);
						q.add(v);
					}
				}else{
					double t = u.getTag() + g.getEdge(u.getKey(), v.getKey());
					if(v.getTag() > t)
					{
						v.setTag(t);
						v.setPrevd(u);
					}
				}
			}
			u.setInfo("Visited");
		}
		LinkedList<node_info> ans = new LinkedList<>();	
		WGraph_DS.NodeData temp = (WGraph_DS.NodeData)dest;
		if(temp.getPrevd()!=null)
		{
			while(temp!=src)
			{
				ans.add(temp);
				temp = temp.getPrevd();
			}
		}		
		ans.add(temp);
		Collections.reverse(ans);
		return ans;
	}
}
