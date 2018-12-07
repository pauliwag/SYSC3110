package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.CooldownManager;


public class CooldownManagerTest {
	
	CooldownManager testCooldownManager;
	
	@Before
	public void setUp() throws Exception {
		testCooldownManager = new CooldownManager();
	}

	/**
	 * Tests the basic cooldowns of Sunflower
	 * @result All cooldowns will be working successfully without any errors
	 */
	@Test
	public void testSunflower() {
		assertEquals(testCooldownManager.getSunTimeLeftOnCD(), 0);
		assertFalse(testCooldownManager.isSunOnCD());
		
		testCooldownManager.startSunCD();
		assertEquals(testCooldownManager.getSunTimeLeftOnCD(), 2);
		assertTrue(testCooldownManager.isSunOnCD());
		
		testCooldownManager.decTimeOnCD();
		assertEquals(testCooldownManager.getSunTimeLeftOnCD(), 1);
		assertTrue(testCooldownManager.isSunOnCD());
		
		testCooldownManager.decTimeOnCD();
		assertEquals(testCooldownManager.getSunTimeLeftOnCD(), 0);
		assertFalse(testCooldownManager.isSunOnCD());
	}
	
	/**
	 * Tests the basic cooldowns of Peashooter
	 * @result All cooldowns will be working successfully without any errors
	 */
	@Test
	public void testPeashooter() {
		assertEquals(testCooldownManager.getNormalPeaTimeLeftOnCD(), 0);
		assertFalse(testCooldownManager.isNormalPeaOnCD());
		
		testCooldownManager.startNormalPeaCD();
		assertEquals(testCooldownManager.getNormalPeaTimeLeftOnCD(), 2);
		assertTrue(testCooldownManager.isNormalPeaOnCD());
		
		testCooldownManager.decTimeOnCD();
		assertEquals(testCooldownManager.getNormalPeaTimeLeftOnCD(), 1);
		assertTrue(testCooldownManager.isNormalPeaOnCD());
		
		testCooldownManager.decTimeOnCD();
		assertEquals(testCooldownManager.getNormalPeaTimeLeftOnCD(), 0);
		assertFalse(testCooldownManager.isNormalPeaOnCD());
	}
	
	/**
	 * Tests the cooldowns of both plants simultaneously
	 * @result Both cooldowns work successfully without any errors.
	 */
	@Test
	public void testBoth() {
		assertFalse(testCooldownManager.isSunOnCD() || testCooldownManager.isNormalPeaOnCD());
		
		testCooldownManager.startSunCD();
		testCooldownManager.startNormalPeaCD();
		assertEquals(testCooldownManager.getSunTimeLeftOnCD(), 2);
		assertEquals(testCooldownManager.getNormalPeaTimeLeftOnCD(), 2);
		
		testCooldownManager.decTimeOnCD();
		testCooldownManager.decTimeOnCD();
		assertFalse(testCooldownManager.isSunOnCD() || testCooldownManager.isNormalPeaOnCD());
	}
	
	/**
	 * Tests negative cooldowns, as this may be a possibility with some error in the main code
	 * @result The class should not allow negative cooldowns, so we should expect cooldown of 0 at minimum
	 */
	@Test
	public void testNegativeCD() {
		assertEquals(testCooldownManager.getNormalPeaTimeLeftOnCD(), 0);
		
		testCooldownManager.decTimeOnCD();
		assertEquals(testCooldownManager.getNormalPeaTimeLeftOnCD(), 0);
	}
}
