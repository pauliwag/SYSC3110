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

	@Test
	public void testGetters() {
		assertEquals(1, testWave.getNum());
		assertEquals(3, testWave.getRemainingZombies());
		
		testWave.setWaveNumber(2);
		assertEquals (2, testWave.getNum());
		
		testWave.setRemainingZombies(5);
		assertEquals(5, testWave.getRemainingZombies());
	}
	
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
