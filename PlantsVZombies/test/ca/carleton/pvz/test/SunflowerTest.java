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

	/**
	 * Tests the "cost" functionality of the Sunflower
	 * @result The cost values should be working as intended... looking for expected values.
	 */
	@Test
	public void testCost() {
		assertEquals(100, testSunflower.getCost());
	}
	
	/**
	 * Testing Sprite method to make sure that it generates an Image
	 * @result The return type of the getSprite method should be an Image type
	 */
	@Test
	public void testSprite() {
		assertTrue(testSunflower.getSprite() instanceof Image);
	}

}
