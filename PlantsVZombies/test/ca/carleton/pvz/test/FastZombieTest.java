package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.actor.FastZombie;
import javafx.scene.image.Image;

public class FastZombieTest {

	Zombie testZombie;

	@Before
	public void setUp() throws Exception {
		testZombie = new FastZombie();

	}

	/**
	 * Tests the basic getter methods of the NormalZombie class Subclasses of Zombie
	 * will be used to ensure methods are working as intended
	 * 
	 * @result All getter methods should be working as intended and returns the
	 *         correct values
	 */
	@Test
	public void testGetters() {
		assertTrue(testZombie instanceof Zombie);
		assertEquals(400, testZombie.getHealth());
		assertEquals(2, testZombie.getSpeed());

	}

	/**
	 * Tests the Sprite method for NormalZombie
	 * 
	 * @result The expected type for the Object returned by this method should be an
	 *         Image type
	 */
	@Test
	public void testSprite() {
		assertTrue(testZombie.getSprite() instanceof Image);
	}

}
