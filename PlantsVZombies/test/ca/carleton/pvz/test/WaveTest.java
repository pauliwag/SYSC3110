package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.Wave;

public class WaveTest {

	Wave testWave;
	LevelOne testLevel;

	@Before
	public void setUp() throws Exception {
		testWave = new Wave(1, 3);
	}

	/**
	 * Tests the basic getters of Wave class
	 * @result All the simple getters should be working as intended and returns the proper values
	 */
	@Test
	public void testGetters() {
		assertEquals(1, testWave.getNum());
		assertEquals(3, testWave.getTotalNumZombies());
		
		testWave.setWaveNum(2);
		assertEquals (2, testWave.getNum());
		
		testWave.setRemainingZombies(5);
		assertEquals(5, testWave.getTotalNumZombies());
	}
	
	/**
	 * Tests the complex method for spawning zombies
	 * @result A level is used in this test case, the level should have a zombie on the last column after the spawnZombieOnLevel() method is called, and the test ensures that the Actor found in the last column is an instance of Zombie
	 */
	@Test
	public void testSpawnZombie() {
		testLevel = new LevelOne();
		Wave.spawnZombieOnLevel(testLevel);
		
		boolean flag = false;
		for (int i = 0; i < 5; i++) {
			if (testLevel.getCell(4, i) instanceof Zombie) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	
	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however (like GUI).

}
