package ex1.src;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * This class implements a weighted and undirected graph.
 * The used data structure is HashMap:
 * 1) NodeTaken - Contains all the created nodes
 * 2) NodesOnTheGraph - The vertices on the current graph
 * 3) Neighbors - Contains all the neighbors of all the vertices on the graph
 * 4) Edges - Contains all the edges and their length from one vertex to another.
 * Each graph have his own nodes,edges, and neighbors.
 * The time complexity of getting a vertex, neighbors and edge is O(1).
 * @author Daniel
 *
 */

public class WGraph_DS  implements weighted_graph, Serializable {   
	private static final long serialVersionUID = 1L;
	static int nodeCounter = 0;
    private int mcCounter=0;
    private int numOfEdges = 0;
    static  HashMap<Integer,node_info>               nodeTaken = new HashMap<Integer , node_info>();
    private HashMap<Integer,node_info>               nodesOnTheGraph;
    private HashMap<Integer,Collection<node_info>>  neighbors = new HashMap<Integer,Collection<node_info>>();
    private HashMap<Integer,HashMap<Integer,Double>> edges;
    
    /**
     * Default constructor
     */    
    public WGraph_DS()
    {
        nodesOnTheGraph = new HashMap<Integer, node_info>();
        neighbors = new HashMap<Integer, Collection<node_info>>();
        edges = new HashMap<Integer,HashMap<Integer, Double>>();
    }
    
    /**
     * Set n to be the number of edges on the graph.
     * @param n		The number of edges
     */
    public void setNumOfEdges(int n){
       numOfEdges = n;
    }
    
    public String toString()
    {
        String ans = "";
        for(node_info a : nodesOnTheGraph.values())
        {
            if(neighbors.get(a.getKey()).size()>0)
            {
                for(node_info b :neighbors.get(a.getKey()))
                {
                    ans = ans + a + " " + b + " " + edges.get(a.getKey()).get(b.getKey()) + "\n";
                }
            }
            else
            {
                ans = ans + a + "\n";
            }
        }
        return ans;
    }  
    
    /**
     * Return the node_info associated with the
     * given key.
     * @param key 	Given key
     * @return 		node_info
     */
    @Override
    public node_info getNode(int key)
    {   	
        if(nodesOnTheGraph.containsKey(key))
        {
            return nodesOnTheGraph.get(key);
        }
        System.out.println("No such vertex on the graph");
        return null;
    }
    
    /**
     * Return a collection of neighbors of vertex k
     * @param k		The key of the wanted node
     * @return		Collection of nodes
     */
    public Collection<node_info> getNi(int k)
    {
        if(nodesOnTheGraph.containsKey(k))
        {
            return neighbors.get(k);
        }
        return null;
    }
    
    /**
     * Returns true if and only if there is an edge between node1 and node2
     * @param node1		Key value of node1
     * @param node2		Key value of node2
     * @return			Boolean
     */
    @Override
    public boolean hasEdge(int node1, int node2)
    {
        if(nodesOnTheGraph.containsKey(node1) &&
           nodesOnTheGraph.containsKey(node2))
        {
            if(neighbors.get(node1).contains(nodesOnTheGraph.get(node2)) &&
               neighbors.get(node2).contains(nodesOnTheGraph.get(node1)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the weight if the edge (node1, node2). In case
     * there is no such edge - returns -1
     * @param node1		Key value of node1
     * @param node2		Key value of node2
     * @return			Double - the length of the edge
     */
    @Override
    public double getEdge(int node1, int node2)
    {
    	if(nodesOnTheGraph.containsKey(node1) &&
    	    nodesOnTheGraph.containsKey(node2))
    	{
    		if(!hasEdge(node1, node2))
                return -1;
            else
            {
                return edges.get(node1).get(node2);
            }
    	}   
    	return -1;
    }
    
    @Override
    public boolean equals(Object other)
    {
    	WGraph_DS temp = (WGraph_DS)other;
    	if(nodesOnTheGraph.keySet().size()!=temp.getV().size())
    	{
    		return false;
    	}
    	for(node_info a : nodesOnTheGraph.values())
    	{
    		if(getV(a.getKey()).size()!=temp.getV(a.getKey()).size())
    		{
    			return false;
    		}
    		for(Integer p : edges.get(a.getKey()).keySet())
    		{    			
    			if(edges.get(a.getKey()).get(p)!=temp.getEdge(a.getKey(), p))
    				return false;
    		}
    	}
    	return true;
    }
    /**
     * Add a new node to the graph with the given key.
     * @param key	Integer
     */
    @Override
    public void addNode(int key)
    {
        if(!nodesOnTheGraph.containsKey(key))
        {          
            // If there is no node with that key, at all..
            // Add it to the node container (NodeTaken)
            if(!nodeTaken.containsKey(key))
            {           
                node_info temp = new NodeData(key); // Calling to the constructor with a key
                nodeTaken.put(key,temp);            // Put it in a list that contains all the taken keys
                neighbors.put(key, new LinkedList<node_info>());
                nodesOnTheGraph.put(key,temp);      // Add it to the graph
                edges.put(key, new HashMap<Integer,Double>());
                mcCounter++;
            }
            // else there is already a node with that key and we just need to
            // return the node
            else
            {
            	node_info temp = new NodeData((NodeData)nodeTaken.get(key));
            	nodesOnTheGraph.put(key,temp);
            	edges.put(key, new HashMap<Integer,Double>());
                neighbors.put(key, new LinkedList<node_info>());               
                mcCounter++;
            }
        }
    }
    
    /**
     * Connect an edge between node1 and node2, with an edge with weight greater or equals to zero. 
     * @param node1		Key value of node1
     * @param node2		Key value of node2
     * @param w 		Weight
     */
    @Override
    public void connect(int node1, int node2, double w)
    {
    	if(w < 0 )
    	{
    		return;
    	}
    	else 
    	{
    		if(w!=0 && node1==node2)
    		{
    			return;
    		}
    		if(nodesOnTheGraph.containsKey(node1) && nodesOnTheGraph.containsKey(node2))
            {
                if(!hasEdge(node1,node2))
                {
                    if(node1==node2)
                    {
                        neighbors.get(node1).add(nodesOnTheGraph.get(node2));
                        edges.get(node1).put(node2, w);
                        mcCounter++;
                    }
                    else
                    {
                        neighbors.get(node1).add(nodesOnTheGraph.get(node2));
                        neighbors.get(node2).add(nodesOnTheGraph.get(node1));
                        edges.get(node1).put(node2, w);
                        edges.get(node2).put(node1, w);
                        numOfEdges++;
                        mcCounter++;
                    }
                }
                else
                {
                	edges.get(node1).put(node2, w);
                    edges.get(node2).put(node1, w);
                    mcCounter++;
                }
            }
    	}       
    }

    /**
     * This method return a Collection representing 
     * all the nodes in the graph.
     * @return Collection<node_info>  collection of nodes
     */
    @Override
    public Collection<node_info> getV()
    {
        return nodesOnTheGraph.values();
    }

    /**
    *
    * This method returns a Collection containing all the
    * nodes connected to node_id
    * @param node_id 				Key value of node_id
    * @return Collection<node_data> Collection of nodes
    */
    @Override
    public Collection<node_info> getV(int node_id)
    {
        // If the node_id exist in the graph
        if(nodesOnTheGraph.containsKey(node_id))
        {
            return neighbors.get(node_id);
        }
        else
        {
            return null;
        }
    }

    /**
     * Delete the given node from the graph.
     * @param key 			    Key value
     * @return node_info 		node_info.
     */
    @Override
    public node_info removeNode(int key)
    {
        if(nodesOnTheGraph.containsKey(key))
        {
            // Creating an Iterator object to iterate through the neighbors of the wanted vertex
            Iterator<node_info> f = neighbors.get(key).iterator();
            node_info[] arr = new node_info[neighbors.get(key).size()];
            int i=0;

            // Adding each neighbor to the new array
            while(f.hasNext()) {
                arr[i++] = f.next();
            }
            // For every neighbor Vertex remove the edge with the wanted Vertex
            for(int f1 = 0; f1<arr.length;f1++)
            {
                removeEdge(arr[f1].getKey(),key);              
            }
            // Remove the wanted vertex from the graph and return it(?)
            node_info temp = nodesOnTheGraph.get(key);
            neighbors.remove(key);
            nodesOnTheGraph.remove(key);
            mcCounter++;
            return temp;
        }else
        {
            return null;
        }
    }

    /**
     * Delete the edge from the graph.
     * @param node1		Key value of node1
     * @param node2		Key value of node2
     */
    @Override
    public void removeEdge(int node1, int node2)
    {
        if(nodesOnTheGraph.containsKey(node1) && nodesOnTheGraph.containsKey(node2))
        {
            if(hasEdge(node1,node2))
            {               
                neighbors.get(node1).remove(nodesOnTheGraph.get(node2));
                neighbors.get(node2).remove(nodesOnTheGraph.get(node1));
                edges.get(node1).remove(node2);
                edges.get(node2).remove(node1);               
                if(node1!=node2)
                {
                    numOfEdges--;
                }
                mcCounter++;
            }
        }
    }

    /**
     * Returns the number of the nodes on the graph.
     */
    @Override
    public int nodeSize()
    {
        return nodesOnTheGraph.keySet().size();
    }

    @Override
    public int edgeSize()
    {
        return numOfEdges;
    }

    @Override
    public int getMC()
    {
        return mcCounter;
    }
    /**
     *  Inner class NodeData that implements the node_info interface.
     *  
     * @author Daniel
     *
     */

     public class NodeData implements node_info,Serializable
     {      
		private static final long serialVersionUID = 1L;
		private int key;
        private String info;
        private double tag;
        private NodeData prevd = null;
        
        /**
         * Default constructor
         */
        public NodeData()
        {
        	// If the there is already a vertex with that key
        	if(nodeTaken.keySet().contains(nodeCounter))
        	{
        		while(nodeTaken.containsKey(nodeCounter)==true) 
        		{
        			nodeCounter++;
        		}
        		key=nodeCounter++;
        		info = "NotVisited";
                tag = Double.POSITIVE_INFINITY;
        	}else {
        		key=nodeCounter++;
                info = "NotVisited";
                tag = Double.POSITIVE_INFINITY;
        	}          
        }
        /**
         * Constructor with parameters.
         * @param k		Key value
         */
        public NodeData(int k)
        {
            this.key = k;
            info = "NotVisited";
            tag = Double.POSITIVE_INFINITY;;
        }
        
        public NodeData(NodeData a)
        {
        	
        	this.key = a.getKey();
        	this.info = a.getInfo();
        	this.tag = a.getTag();
        	this.prevd = a.getPrevd();
        }
        /**
         * Set node k as the previous of the current node
         * @param k		The given node
         */
        public void setPrevd(NodeData k)
        {
            this.prevd = k;
        }
        
        /**
         * Get the previous node
         * @return	The previous node
         */     
        public NodeData getPrevd()
        {
            return prevd;
        } 
        
        public String toString()
        {
            return "" + key;
        }
        
        @Override
        public int getKey()
        {
            return key;
        }

        @Override
        public String getInfo()
        {
            return info;
        }

        @Override
        public void setInfo(String s)
        {
            this.info = s;
        }

        @Override
        public double getTag()
        {
            return tag;
        }

        @Override
        public void setTag(double t)
        {
            this.tag = t;
        }


     }


}
