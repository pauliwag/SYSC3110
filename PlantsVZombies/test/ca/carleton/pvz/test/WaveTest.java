package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.FastZombie;
import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.actor.ShieldZombie;
import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.Wave;
import ca.carleton.pvz.level.Wave.Difficulty;

public class WaveTest {

	Wave testWave;
	LevelOne testLevel;

	@Before
	public void setUp() throws Exception {
		testWave = new Wave(1, Difficulty.NORMAL, 3, 1, 0);
	}

	/**
	 * Tests the basic getters of Wave class
	 * @result All the simple getters should be working as intended and returns the proper values
	 */
	@Test
	public void testGetters() {
		assertEquals(1, testWave.getNum());
		assertEquals(4, testWave.getTotalNumZombies());
		
		testWave.setWaveNum(2);
		assertEquals (2, testWave.getNum());
		assertEquals(4, testWave.getTotalNumZombies());
	}
	
	@Test
	public void testNumZombies() {
		assertEquals(3, testWave.getNumZombies(NormalZombie.class));
		assertEquals(1, testWave.getNumZombies(ShieldZombie.class));
		assertEquals(0, testWave.getNumZombies(FastZombie.class));
		
		testWave.setNumZombies(NormalZombie.class, 4);
		assertEquals(4, testWave.getNumZombies(NormalZombie.class));
		
		testWave.setNumZombies(ShieldZombie.class, 6);
		assertEquals(6, testWave.getNumZombies(ShieldZombie.class));
		
		testWave.setNumZombies(FastZombie.class, 5);
		assertEquals(5, testWave.getNumZombies(FastZombie.class));
		
		assertEquals(15, testWave.getTotalNumZombies());
	}
	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however (like GUI).

}