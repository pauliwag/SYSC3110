package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.Wave;

public class LevelOneTest {
	
	private LevelOne testLevelOne;

	@Before
	public void setUp() throws Exception {
		testLevelOne = new LevelOne();
	}
	
	@Test
	public void testInitWaves() {
		Wave testWave;
		
		testLevelOne.initWaves();
		testWave = new Wave(1,2);
		assertEquals(testWave.getNum(), testLevelOne.getWave().getNum());
		assertEquals(testWave.getRemainingZombies(), testLevelOne.getWave().getRemainingZombies());
		
		testWave = new Wave(2,3);
		testLevelOne.dequeueWave();
		assertEquals(testWave.getNum(), testLevelOne.getWave().getNum());
		assertEquals(testWave.getRemainingZombies(), testLevelOne.getWave().getRemainingZombies());
		
		testWave = new Wave(3,4);
		testLevelOne.dequeueWave();
		assertEquals(testWave.getNum(), testLevelOne.getWave().getNum());
		assertEquals(testWave.getRemainingZombies(), testLevelOne.getWave().getRemainingZombies());
	}

	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however.

}
