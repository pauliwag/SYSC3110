package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.LevelOne;
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
