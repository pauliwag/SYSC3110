package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.ActionProcessor;
import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.actor.GatlingPeaShooter;
import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.LevelOne;

public class GatlingPeaShooterTest {
	
	private Level testLevel;
	private GatlingPeaShooter testPeaShooter;
	private Zombie testZombie;
	private PlantsVZombies testGame;
	private ActionProcessor actionProcessor;

	@Before
	public void setUp() throws Exception {
		testPeaShooter = new GatlingPeaShooter();
	}

	/**
	 * Tests the hits field of the PeaShooter
	 * @result Hits behaves as expected, including the newTurn() method, which resets the hits to 0.
	 */
	@Test
	public void testDamage() {
		assertEquals(350, testPeaShooter.getPeaDamage());
	}
	
	/**
	 * Tests the functionality of the shooting function
	 * @result Zombies health is reduced as expected, which is -400 per shot
	 */
	@Test
	public void testShoot() {
		testLevel = new LevelOne();
		testGame = new PlantsVZombies();
		testZombie = new NormalZombie();
		actionProcessor = new ActionProcessor(testGame);
		
		testLevel.placeActor(testPeaShooter, new Point(0,2));
		testLevel.placeActor(testZombie, new Point(4,2));
		assertEquals(500, testZombie.getHealth());
		
		actionProcessor.shootZombies(testLevel);
		assertEquals(150, testZombie.getHealth());
		
		actionProcessor.shootZombies(testLevel);
		assertEquals(-200, testZombie.getHealth());
	}
	
	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however (like GUI).

}