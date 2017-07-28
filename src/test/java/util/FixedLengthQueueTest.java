package util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FixedLengthQueueTest {

	@Test
	public void testShift() {
		List<String> s = new ArrayList<String>();
		s.add("a");
		s.add("b");
		s.add("c");
		
		FixedLengthQueue<String> q = new FixedLengthQueue<String>(s);
		assertTrue(q.shift("d") == "a");
		assertTrue(q.toList().size() == 3);
		assertTrue(q.toList().get(2) == "d");
	}
}
