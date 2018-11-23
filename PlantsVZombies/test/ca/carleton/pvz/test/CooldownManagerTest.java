package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.actor.CooldownManager;


public class CooldownManagerTest {

	@Before
	public void setUp() throws Exception {
		CooldownManager.resetCDs();
	}

	/**
	 * Tests the basic cooldowns of Sunflower
	 * @result All cooldowns will be working successfully without any errors
	 */
	@Test
	public void testSunflower() {
		assertEquals(CooldownManager.getSunTimeLeftOnCD(), 0);
		assertFalse(CooldownManager.isSunOnCD());
		
		CooldownManager.startSunCD();
		assertEquals(CooldownManager.getSunTimeLeftOnCD(), 2);
		assertTrue(CooldownManager.isSunOnCD());
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getSunTimeLeftOnCD(), 1);
		assertTrue(CooldownManager.isSunOnCD());
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getSunTimeLeftOnCD(), 0);
		assertFalse(CooldownManager.isSunOnCD());
	}
	
	/**
	 * Tests the basic cooldowns of Peashooter
	 * @result All cooldowns will be working successfully without any errors
	 */
	@Test
	public void testPeashooter() {
		assertEquals(CooldownManager.getNormalPeaTimeLeftOnCD(), 0);
		assertFalse(CooldownManager.isNormalPeaOnCD());
		
		CooldownManager.startNormalPeaCD();
		assertEquals(CooldownManager.getNormalPeaTimeLeftOnCD(), 2);
		assertTrue(CooldownManager.isNormalPeaOnCD());
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getNormalPeaTimeLeftOnCD(), 1);
		assertTrue(CooldownManager.isNormalPeaOnCD());
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getNormalPeaTimeLeftOnCD(), 0);
		assertFalse(CooldownManager.isNormalPeaOnCD());
	}
	
	/**
	 * Tests the cooldowns of both plants simultaneously
	 * @result Both cooldowns work successfully without any errors.
	 */
	@Test
	public void testBoth() {
		assertFalse(CooldownManager.isSunOnCD() || CooldownManager.isNormalPeaOnCD());
		
		CooldownManager.startSunCD();
		CooldownManager.startNormalPeaCD();
		assertEquals(CooldownManager.getSunTimeLeftOnCD(), 2);
		assertEquals(CooldownManager.getNormalPeaTimeLeftOnCD(), 2);
		
		CooldownManager.decTimeOnCD();
		CooldownManager.decTimeOnCD();
		assertFalse(CooldownManager.isSunOnCD() || CooldownManager.isNormalPeaOnCD());
	}
	
	/**
	 * Tests negative cooldowns, as this may be a possibility with some error in the main code
	 * @result The class should not allow negative cooldowns, so we should expect cooldown of 0 at minimum
	 */
	@Test
	public void testNegativeCD() {
		assertEquals(CooldownManager.getNormalPeaTimeLeftOnCD(), 0);
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getNormalPeaTimeLeftOnCD(), 0);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		CooldownManager.resetCDs();
	}
}
