package parse;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import util.FixedLengthQueue;

public class MarkovTest {

	@Test
	public void testAddSeq() {
		Markov<String> m = new Markov<String>(3);
		
		LinkedList<String> s = new LinkedList<String>();
		s.add("a");
		s.add("b");
		s.add("c");
		FixedLengthQueue<String> q = new FixedLengthQueue<String>(s);
		
		m.addSeq(q.toList(), "d");
		assertTrue(m.getNext(q.toList()) == "d");
	}
}
