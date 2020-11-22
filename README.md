file list-

	1) node_info.java	
		node header (Inner class)
	2) weighted_graph.java
		weighted graph header
	3) weighted_graph_algorithms.java
		weighted graph algorithms header
	4) WGraph_DS.java
		weighted graph and node implementations
	5) WGraph_Algo.java
		weighted graph algorithms implementation
	6) WGraph_AlgoTest.java
		test file for WGraph_Algo
	7) WGraph_DSTest.java
		test file for WGraph_DS
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
About - 

      Assignment number 1 (ex1) in object oriented programming - implementation of a weighted (and undirected graph) and set of algorithms.         
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Design - 

	# The code itself written with a lot of "if-else" statements to prevent errors.
    
	# The first half of the code(in the implementations) are the implements of the interfaces.    
			 And the second half of the code are implements of algorithms
			 and helpful methods. (for example : restoreNodes() , visitedAll() and more)
      
	# There are notes on the code that describes in short the functions and their meanings.
    
	# Also there is an Inner class inside WGraph_DS class called: NodeData.
    
	# Print format:
		<node> <neighbour> <weight>
		.
		.
		.
		.
		<node> <neighbour> <weight>
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Implementations - 

	1. WGraph_DS:
		The purpose of the class WGraph_DS is to implement a weighted and undirected graph.
		To be able to have different edges, nodes and neighbors on each graph,
		every graph have the following variables:
			   # nodesOnTheGraph
			   # neighbors
			   # edges
			   
		The HashMap data structure help us to achive time complexity of O(1) of getting
		a node from the graph or getting a collection of neighbors nodes or getting
		the weight of a specific edge.
		
		The implemenation:
			nodesOnTheGraph    HashMap<Integer, node_info>
					       	     ^		^
					             |		|
					          key value.	Node object.
													
			neighbors      	   HashMap<Integer, Collection<node_info>>
						      ^           ^
                                                      |           |
					      Key value 	  Collection of
					      of the 		  all the neighbors 
					     current node.	  nodes.
													
			edges		   HashMap<Integer, HashMap<Integer,Double>>
					  	    ^		  ^
					            |		  |
					      Key value		  all the keys
					      of the		  on this HashMap are
				              current node.	  neighbors of the current node
						                 and point to the weight of the edge.
								 
	      Static variables:
              	nodeCounter - It is an index for creating a new vertex with the default constructor.
              	nodeTaken   - The data structure to place all the taken nodes.
		
		
             (TC = time complexity)				
	     Functions:																						
	        setNumOfEdges(int a) 			|  Set the number of edges on the graph to be equals to a.       
								TC: O(1)
								
		getNode(int key)		        |  Returns the node associated with that key.		
								TC: O(1)
								
		getNi(int key) 			        |  Returns a collection of neighbors nodes of node key. 
								TC: O(1)
								
		hasEdge(int a, int b)		        |  Returns true if and only if there is an edge between 
							   node a and node b.
								TC: O(1)
								
		getEdge(int a , int b)			|  If there is an edge between node a and node b returns
							   the weight ,else returns -1.
								TC: O(1)
								
		addNode(int key)			|  If there is no node with that key on the graph add it.        				
							 In addition we need a data structor that will hold the                      
							 nodes,  because every node have a unique key. 				       
							 Therfore we use HashMap variable called NodesTaken 
							 that contains all the nodes that created.
								TC: O(1)
								
		connect(int a, int b, double c)	        |  Connect two nodes on the graph, if they are already
							   connected - update the weight.
								TC: O(1)
								
		getV()					|  Returns a collection of all the nodes on the graph. 
								TC: O(1)
								
		getV(int a)				|  Same as getNi.                                                  
								TC: O(1)
								
		removeNode(int a)			|  Remove the node that associated with key a from the
							   graph and all the connected edges.
                                                                TC: O(|E|) , when E is the number  of
									     neighbors edges.
                                                                                                                                               
		removeEdge(int a, int b)		|  Remove the edge between node a and node b.      
								TC: O(1)
								
		nodeSize() 				|  Returns the number of nodes on the graph.       
								TC: O(1)
								
		edgeSize()				|  Returns the number of edges on the graph.       
								TC: O(1)
								
		getMC()					|  Returns the number of operations on the graph.        
								TC: O(1)
		
    
	2. NodeData:
		The purpose of the inner class NodeData is to implement a node (vertex).
					
		Each node have:
		 # Key value   - Int		  
		 # Tag	       - Double	  By default is infinite
		 # Info        - String    By default is "NotVisited"
		 # Prevd       - NodeData  By default is null
						
		There are two constructors:
		   1) default constructor 
                   	 Create a new node with a key that equals to the nodeCounter.
                    	 If there is already a node with a key that equals to the nodeCounter, then we rise
			 the nodeCounter until there is an available key.
		   2) Constructor with value
                    Create a new node with a specific key
		    
		Functions:
			setPrevd(NodeData k)		| Set k as the previous node of the current node                                         
								TC: O(1)
								
			getPrevd()			| Returns the previous node of the current node                                          
								TC: O(1)
								
			getKey() 			| Returns the key value of the current node                                              
								TC: O(1)
								
			getInfo()			| Returns the info of the current node                                                   
								TC: O(1)
								
			setInfo(String s) 		| Set the info of the current node to be s                                              
								TC: O(1)
								
			getTag()			| Returns the tag of the current node                                                    
								TC: O(1)
								
			setTag(double b)		| Set the tag of the current node to be b                                               
								TC: O(1)
								
							
	3. WGraph_Algo:
		The purpose of the class WGraph_Algo is to implement a set pf algorithms
		On weighted and undirected graph.
		
		Functions:
			init(weighted_graph a)	        | Init the graph on which this set of algorithms operates on
								TC: O(1)
								
			getGraph()			| Returns the current initialized graph
								TC: O(1)
								
			copy()				| Deep copy of the current graph
                                                          Because every vertex is unique there is one copy of each node.
							  	TC: O(1)
								
			isConnected()			| Returns true if and only if there is a route from any
							  Vertex to al the other vertices
							     	Algorith used: BFS algorithm.
								TC: O(|V|+|E|)
								
			shortestPathDist(int a, int b) 	| Returns the shortest path (double) of the two vertices
							  If there is no path returns -1.
								Algorith used: Dijkstra's algorithm.
								TC: O(N^2)
								
			shortestPath(int a, int b)	| Returns a list of nodes that creates the shortest path. 
								TC: O(N^2)
								
			save(String a)			| Save the current graph on the disk.     
			
			load(String)			| Load a graph  from the disk.
			
			bfs(node_info src)		| Implementation of Breadth first search algorithm.
							  Implemented with queue, If the graph is connected then
							  The algorithm visit:
								All the vertices on the graph = N.
								All the edges on the graph    = E.
								Then the time complexity is O(|N| + |E|).
								
			restoreNodes()			| Restore the nodes on the graph to their default settings.						
							  Algorith used: BFS algorithm.
							   	TC: O(|V|+|E|)
								
			visitedAll()			| Checking if the bfs algorithm visited all the 
							  Nodes on the graph.
							  	TC: O(N) , when N is the number of
                                                                           vertices on the graph
                                                                           (N=V)
									   
			dijkstra(node_info src, node_info dest)	| Implementation of dijkstra algorithm.
								  Implemented with queue, In the worst case
								  All the nodes are connected,
								  Then there will be N nodes on the queue, 
								   And every dequeue the algorithm make (N-1) operations.
								   Then the time complexity is O(N^2).
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

How to use-

	First create a graph:
		$ WGraph_DS g = new WGraph_DS();
		
	Add some nodes:
		$ g.add(5);
		$ g.add(3);
		
	The output of this graph will be:
				                              		3
		                              				5
	Let's connect the two vertices:
		$ g.connect(3,5, 14.215);
		
	And now the output of the graph will be:
				                              		3 5 14.215
					                              	5 3 14.215
	To be able to use the algorithms we
  	need to create a new WGraph_Algo object:
		$ WGraph_Algo ga = new WGraph_Algo();
		
	Then we must init the graph we want to operate on:
		$ ga.init(g);
		
	An example of using a simple method:
		$ System.out.println(ga.isConnected());
	Output:
			                              			true
