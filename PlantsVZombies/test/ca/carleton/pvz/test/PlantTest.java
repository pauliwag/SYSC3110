package ca.carleton.pvz.test;

import static org.junit.Assert.*;
 
import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.Plant;

public class PlantTest {

	Plant testPlant;
	
	@Before
	public void setUp() throws Exception {
		testPlant = new Plant();
	}

	@Test
	public void testPlant() {
		assertTrue(testPlant instanceof Actor);
	}

	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however (like GUI).
}
