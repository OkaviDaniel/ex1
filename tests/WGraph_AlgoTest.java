package ex1.tests;

import org.junit.jupiter.api.Test;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {

	@Test
	public void shortestPath1()
	{
		WGraph_DS g = new WGraph_DS();
		g.addNode(1);
		g.addNode(2);
		WGraph_Algo ga = new WGraph_Algo();
		ga.init(g);
		double a = ga.shortestPathDist(1, 2);
		assertEquals(-1, a);
	}
 
	@Test
	public void connectedOrNot()
	{
		WGraph_DS g = new WGraph_DS();
		WGraph_Algo ga = new WGraph_Algo();
		ga.init(g);
		for (int i = 0; i < 100; i++) {
			g.addNode(i);
		}
		for (int i = 0; i < 99; i++) {
			g.connect(i, i+1, i+10);
		}
		boolean t  =  ga.isConnected();
		assertTrue(t);
		g.removeEdge(89, 90);
		t =  ga.isConnected();
		assertFalse(t);
	}
	
	@Test
	public void shortestPath2()
	{
		WGraph_DS g = new WGraph_DS();
		WGraph_Algo ga = new WGraph_Algo();
		ga.init(g);
		 g.addNode(1);
	     g.addNode(2);
	     g.addNode(3);
	     g.addNode(4);
	     g.addNode(5);
	     g.addNode(6);
	     g.connect(1,2,7);
	     g.connect(1,3,9);
	     g.connect(1,6,14);
	     g.connect(2,3,10);
	     g.connect(2,4,15);
	     g.connect(3,4,11);
	     g.connect(3,6,2);
	     g.connect(4,5,6);
	     g.connect(5,6,9);
	     assertEquals(-1, ga.shortestPathDist(0,4));
	     assertEquals(20, ga.shortestPathDist(1,4));
	}
	
	@Test
	public void doubletest()
	{
		WGraph_DS g = new WGraph_DS();
		WGraph_DS r = new WGraph_DS();
		g.addNode(1);
		g.addNode(2);
		g.connect(1, 2, 15.1545);
		WGraph_Algo ga = new WGraph_Algo();
		ga.init(g);
		assertTrue(ga.isConnected());
		r.addNode(1);
		r.addNode(2);
		ga.init(r);
		assertFalse(ga.isConnected());	
	}
	
}