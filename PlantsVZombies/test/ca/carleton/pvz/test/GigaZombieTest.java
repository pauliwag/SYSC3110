package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level.Terrain;
import ca.carleton.pvz.actor.GigaZombie;
import javafx.scene.image.Image;

public class GigaZombieTest {

	Zombie testZombie;

	@Before
	public void setUp() throws Exception {
		testZombie = new GigaZombie();

	}

	/**
	 * Tests the basic getter methods of the GigaZombie class Subclasses of Zombie
	 * will be used to ensure methods are working as intended
	 * 
	 * @result All getter methods should be working as intended and returns the
	 *         correct values
	 */
	@Test
	public void testGetters() {
		assertTrue(testZombie instanceof Zombie);
		assertEquals(2000, testZombie.getHealth());
		assertEquals(1, testZombie.getSpeed());
		testZombie.setHealth(testZombie.getHealth() - 0);
		assertFalse(testZombie.getHealth() == 0);

	}

	/**
	 * Tests the Sprite method for GigaZombie
	 * 
	 * @result The expected type for the Object returned by this method should be an
	 *         Image type
	 */
	@Test
	public void testSprite() {
		assertFalse(testZombie.getSprite(Terrain.GRASS) == null);
		assertTrue(testZombie.getSprite(Terrain.GRASS) instanceof Image);
	}

}
