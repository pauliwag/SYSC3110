package ca.carleton.pvz.test;

import static org.junit.Assert.*;
 
import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.Sunflower;

public class PlantTest {

	Sunflower testPlant;
	
	@Before
	public void setUp() throws Exception {
		testPlant = new Sunflower();
	}

	/**
	 * Tests if the Plant is an instance of the Actor class
	 * @result As expected, the Plant should be an instance of the Actor class (it's parent class)
	 */
	@Test
	public void testPlant() {
		assertTrue(testPlant instanceof Actor);
	}

	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however (like GUI).
}
