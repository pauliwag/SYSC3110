package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.LevelOne;
import javafx.scene.image.Image;

public class ZombieTest {

	Zombie testZombie;
	LevelOne testLevel;
	
	@Before
	public void setUp() throws Exception {
		testZombie = new Zombie();
		testLevel = new LevelOne();
	}

	/**
	 * Tests the basic getter methods of the Zombie class
	 * @result All getter methods should be working as intended and returns the correct values
	 */
	@Test
	public void testGetters() {
		assertTrue (testZombie instanceof Zombie);
		assertEquals(600, testZombie.getHealth());
		testZombie.setHealth(200);
		assertEquals(200, testZombie.getHealth());
		
	}
	
	/**
	 * Tests the movement functionality of the Zombie on a sample level
	 * @result The zombie should move left one square PER call of the function
	 */
	@Test
	public void testMovement() {
		testLevel.placeActor(testZombie, new Point(2,2));
		Zombie.moveZombies(testLevel);
		assertEquals(null, testLevel.getCell(2, 2));
		assertEquals(null, testLevel.getCell(3, 2));
		assertTrue(testLevel.getCell(1, 2) instanceof Zombie);
	}
	
	/**
	 * Tests the Sprite method for Zombie
	 * @result The expected type for the Object returned by this method should be an Image type
	 */
	@Test
	public void testSprite() {
		assertTrue(testZombie.getSprite() instanceof Image);
	}

}
