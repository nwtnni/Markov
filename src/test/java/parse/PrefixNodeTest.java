package parse;

import static org.junit.Assert.*;
import org.junit.Test;

import parse.PrefixNode;

public class PrefixNodeTest {
	
	@Test
	public void testAddNext() {
		PrefixNode<String> n = new PrefixNode<String>("word");
		assertTrue(n.getTotal() == 0);
		n.addNext("hello");
		assertTrue(n.getTotal() == 1);
		assertTrue(n.getCount().containsKey("hello"));
	}
	
	@Test
	public void testGetRand() {
		PrefixNode<String> n = new PrefixNode<String>("word");
		n.addNext("a");
		n.addNext("b");
		n.addNext("c");
		
		float count = 0;
		long TRIALS = 50000000;
		
		for (long i = 0; i < TRIALS; i++) {
			if (n.getRand() == "a") {
				count++;
			}
		}
		
		System.out.println(count / TRIALS);
		assertTrue(Math.abs(count / TRIALS - 0.33) < 0.01);
	}
}
