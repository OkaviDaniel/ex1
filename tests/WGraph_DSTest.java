package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex1.src.WGraph_DS;


class WGraph_DSTest 
{	
	@Test
	public void mcCounterCheck()
	{
		WGraph_DS g = new WGraph_DS();
		int n=1000;
		for (int i = 0; i < n; i++) 
		{
			g.addNode(i);
		}		
		g.connect(3, 1, 3);		
		g.removeNode(3);
		assertEquals(1003, g.getMC());
	}
	@Test
	public void edgesCheck0()
	{
		WGraph_DS f = new WGraph_DS();
		int n = 10;
		for (int i = 0; i < n; i++)
		{
			f.addNode(i);
		}
		f.connect(3, 1, 2);
		f.removeEdge(3, 1);
		assertEquals(0, f.edgeSize());
	}
	
	@Test
	public void edgesCheck1()
	{
		WGraph_DS g = new WGraph_DS();
		int n=1000;
		for (int i = 0; i < n; i++) 
		{
			g.addNode(i);
		}		
		for (int i = 0; i < 25; i++) 
		{
			g.connect(i, i+1,i*10.0);			
		}
		for (int i = 0; i < 10; i++)
		{
			g.removeNode(i);
		}
		assertEquals(15, g.edgeSize());
	}
	
	@Test
	public void hasEdgeOrNot()
	{
		WGraph_DS g = new WGraph_DS();
		g.addNode(10);
		g.connect(10, 10, 5);
		assertFalse(g.hasEdge(10, 10));
	}	
	
	@Test
	public void hasEdgeOrNotAgain()
	{
		WGraph_DS g = new WGraph_DS();
		g.addNode(10);
		g.connect(10, 10, 0);
		assertTrue(g.hasEdge(10, 10));
	}	
	
	@Test
	public void negativeDistance()
	{
		WGraph_DS g = new WGraph_DS();
		g.addNode(15);
		g.addNode(1);
		g.connect(15, 1, -1);
		assertEquals(0,g.edgeSize());
	}
			
	@Test
	public void sameKey()
	{
		WGraph_DS g = new WGraph_DS();
		for (int i = 1; i < 6; i++) {
			g.addNode(i);
		}
		g.addNode(4);
		assertEquals(5, g.getV().size());
	}
	
	@Test
	public void lidrus()
	{
		WGraph_DS g = new WGraph_DS();
		g.addNode(15);
		g.addNode(1);
		g.connect(15, 1, 2);
		g.connect(15, 1, 5);
		assertEquals(5, g.getEdge(15, 1));
	}
}
