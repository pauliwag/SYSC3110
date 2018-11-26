package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.Wave;
import ca.carleton.pvz.level.Wave.Difficulty;

public class LevelOneTest {
	
	private LevelOne testLevelOne;

	@Before
	public void setUp() throws Exception {
		testLevelOne = new LevelOne();
	}
	
	/**
	 * Tests the method for initial waves
	 * @result All initial waves are created successfully, and for LevelOne, that entails 3 waves
	 */
	@Test
	public void testInitWaves() {
		Wave testWave;
		
		testLevelOne.initWaves();
		testWave = new Wave(1, Difficulty.NORMAL, 3, 1, 0);
		assertEquals(testWave.getNum(), testLevelOne.getHeadWave().getNum());
		assertEquals(testWave.getTotalNumZombies(), testLevelOne.getHeadWave().getTotalNumZombies());
		
		testWave = new Wave(2, Difficulty.RAMPED, 2, 2, 0);
		testLevelOne.dequeueHeadWave();
		assertEquals(testWave.getNum(), testLevelOne.getHeadWave().getNum());
		assertEquals(testWave.getTotalNumZombies(), testLevelOne.getHeadWave().getTotalNumZombies());
		
		testWave = new Wave(3, Difficulty.SUPER_RAMPED, 0, 2, 20);
		testLevelOne.dequeueHeadWave();
		assertEquals(testWave.getNum(), testLevelOne.getHeadWave().getNum());
		assertEquals(testWave.getTotalNumZombies(), testLevelOne.getHeadWave().getTotalNumZombies());
	}

	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however.

}
