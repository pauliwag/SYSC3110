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

	@Test
	public void testCost() {
		assertEquals(200, testPeaShooter.getCost());
	}
	
	@Test
	public void testPeaDamage() {
		assertEquals(200, testPeaShooter.getPeaDamage());
	}

}
