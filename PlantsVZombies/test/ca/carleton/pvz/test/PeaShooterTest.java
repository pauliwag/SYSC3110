package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.PeaShooter;
import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.LevelOne;

public class PeaShooterTest {
	
	private Level testLevel;
	private PeaShooter testPeaShooter;
	private Zombie testZombie;

	@Before
	public void setUp() throws Exception {
		testPeaShooter = new PeaShooter();
	}

	/**
	 * Tests the hits field of the PeaShooter
	 * @result Hits behaves as expected, including the newTurn() method, which resets the hits to 0.
	 */
	@Test
	public void testHits() {
		assertEquals(0, testPeaShooter.getHits());
		testPeaShooter.addHit();
		assertEquals(1, testPeaShooter.getHits());
		testPeaShooter.addHit();
		testPeaShooter.newTurn();
		assertEquals(0, testPeaShooter.getHits());
	}
	
	/**
	 * Tests the functionality of the shooting function
	 * @result Zombies health is reduced as expected, which is -400 per shot
	 */
	@Test
	public void testShoot() {
		testLevel = new LevelOne();
		testZombie = new Zombie();
		
		testLevel.placeActor(testPeaShooter, new Point(0,2));
		testLevel.placeActor(testZombie, new Point(4,2));
		assertEquals(600, testZombie.getHealth());
		
		PeaShooter.shootZombies(testLevel);
		assertEquals(200, testZombie.getHealth());
		
		PeaShooter.shootZombies(testLevel);
		assertEquals(-200, testZombie.getHealth());
	}
	
	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however (like GUI).

}
