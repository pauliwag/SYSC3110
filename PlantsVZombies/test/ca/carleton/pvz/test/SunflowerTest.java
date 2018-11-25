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
	 * Tests the "turns" functionality of the Sunflower
	 * @result The turns should be working as intended, where it starts at 0 initially
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
