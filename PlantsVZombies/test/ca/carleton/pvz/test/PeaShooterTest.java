package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ca.carleton.pvz.actor.NormalPeaShooter;

public class PeaShooterTest {
	
	NormalPeaShooter testPeaShooter;

	@Before
	public void setUp() throws Exception {
		testPeaShooter = new NormalPeaShooter();
	}

	/**
	 * Tests the cost functionality of PeaShooter class
	 * @result All the simple getters should be working as intended and returns the proper values
	 */
	@Test
	public void testCost() {
		assertEquals(200, testPeaShooter.getCost());
	}
	
	/**
	 * Tests the damage of the PeaShooter class
	 * @result All the simple getters should be working as intended and returns the proper values
	 */
	@Test
	public void testPeaDamage() {
		assertEquals(200, testPeaShooter.getPeaDamage());
	}

}
