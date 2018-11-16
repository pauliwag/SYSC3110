package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import ca.carleton.pvz.actor.Actor;

public class ActorTest {
	
	private Actor testActor;

	@Before
	public void setUp() throws Exception {
		testActor = new Actor();
	}

	@Test
	public void testActor() {
		assertTrue(testActor.getSprite() == null);
	}

	
// tearDown() is not necessary here, as garbage collection of objects
// after the test class concludes. Other things that consume system
// resources may need tearDown() however.


}
