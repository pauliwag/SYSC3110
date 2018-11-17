package ca.carleton.pvz.test;

import static org.junit.Assert.*;
import javafx.scene.image.Image;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.Sunflower;

public class SunflowerTest {
	
	Sunflower testSunflower;

	@Before
	public void setUp() throws Exception {
		testSunflower = new Sunflower();
	}

	@Test
	public void testTurns() {
		assertEquals(0, testSunflower.getTurnPlaced());
		testSunflower.setTurnPlaced(5);
		assertEquals(5,testSunflower.getTurnPlaced());
	}
	
	// not testing toString() method of testSunflower because GUI is implemented, and that will be removed
	// in future versions!
	
	@Test
	public void testSprite() {
		assertTrue(testSunflower.getSprite() instanceof Image);
	}

}
