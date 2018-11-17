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


	@Test
	public void testGetters() {
		assertTrue (testZombie instanceof Zombie);
		assertEquals(600, testZombie.getHealth());
		testZombie.setHealth(200);
		assertEquals(200, testZombie.getHealth());
		
	}
	
	@Test
	public void testMovement() {
		testLevel.placeActor(testZombie, new Point(2,2));
		Zombie.moveZombies(testLevel);
		assertEquals(null, testLevel.getCell(2, 2));
		assertEquals(null, testLevel.getCell(3, 2));
		assertTrue(testLevel.getCell(1, 2) instanceof Zombie);
	}
	
	@Test
	public void testSprite() {
		assertTrue(testZombie.getSprite() instanceof Image);
	}

}
