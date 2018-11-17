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
		assertEquals(CooldownManager.getCurrentSunCD(), 0);
		assertFalse(CooldownManager.isSunOnCD());
		
		CooldownManager.startSunCD();
		assertEquals(CooldownManager.getCurrentSunCD(), 2);
		assertTrue(CooldownManager.isSunOnCD());
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getCurrentSunCD(), 1);
		assertTrue(CooldownManager.isSunOnCD());
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getCurrentSunCD(), 0);
		assertFalse(CooldownManager.isSunOnCD());
	}
	
	/**
	 * Tests the basic cooldowns of Peashooter
	 * @result All cooldowns will be working successfully without any errors
	 */
	@Test
	public void testPeashooter() {
		assertEquals(CooldownManager.getCurrentPeaCD(), 0);
		assertFalse(CooldownManager.isPeaOnCD());
		
		CooldownManager.startPeaCD();
		assertEquals(CooldownManager.getCurrentPeaCD(), 2);
		assertTrue(CooldownManager.isPeaOnCD());
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getCurrentPeaCD(), 1);
		assertTrue(CooldownManager.isPeaOnCD());
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getCurrentPeaCD(), 0);
		assertFalse(CooldownManager.isPeaOnCD());
	}
	
	/**
	 * Tests the cooldowns of both plants simultaneously
	 * @result Both cooldowns work successfully without any errors.
	 */
	@Test
	public void testBoth() {
		assertFalse(CooldownManager.isSunOnCD() || CooldownManager.isPeaOnCD());
		
		CooldownManager.startSunCD();
		CooldownManager.startPeaCD();
		assertEquals(CooldownManager.getCurrentSunCD(), 2);
		assertEquals(CooldownManager.getCurrentPeaCD(), 2);
		
		CooldownManager.decTimeOnCD();
		CooldownManager.decTimeOnCD();
		assertFalse(CooldownManager.isSunOnCD() || CooldownManager.isPeaOnCD());
	}
	
	/**
	 * Tests negative cooldowns, as this may be a possibility with some error in the main code
	 * @result The class should not allow negative cooldowns, so we should expect cooldown of 0 at minimum
	 */
	@Test
	public void testNegativeCD() {
		assertEquals(CooldownManager.getCurrentPeaCD(), 0);
		
		CooldownManager.decTimeOnCD();
		assertEquals(CooldownManager.getCurrentPeaCD(), 0);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		CooldownManager.resetCDs();
	}
}
