package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.actor.NormalZombie;

import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

public class NormalZombieTest {

	Zombie testZombie;
	LevelOne testLevel;

	@Before
	public void setUp() throws Exception {
		testZombie = new NormalZombie();
		testLevel = new LevelOne();
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
		assertEquals(500, testZombie.getHealth());
		assertEquals(1, testZombie.getSpeed());
		testZombie.setHealth(testZombie.getHealth() - 0);
		assertFalse(testZombie.getHealth() == 0);

	}

	/**
	 * Tests the Sprite method for NormalZombie
	 * 
	 * @result The expected type for the Object returned by this method should be an
	 *         Image type
	 */
	@Test
	public void testSprite() {
		assertFalse(testZombie.getSprite(Terrain.GRASS) == null);
		assertTrue(testZombie.getSprite(Terrain.GRASS) instanceof Image);
	}

	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however (like GUI).
}
