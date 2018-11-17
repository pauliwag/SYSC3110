package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.level.Level;

public class LevelTest {

	private Level testLevel;
	
	@Before
	public void setUp() throws Exception {
		testLevel = new LevelOne(); //runs a "super." which fills in values of "Level One", 5, 5, 500.
	}

	@Test
	public void testCompletion() {
		assertFalse(testLevel.isWaveDefeated());
		assertFalse(testLevel.isBeat());
	}
	
	@Test
	public void testTurn() {
		assertEquals(0, testLevel.getTurn());
		testLevel.incTurn();
		assertEquals(1, testLevel.getTurn());
		assertEquals(0, testLevel.getPrevTurn());
	}
	
	@Test
	public void testCell() {
		//CELL TESTING
		assertEquals(null, testLevel.getCell(2,2));
		testLevel.placeActor(new Actor(), new Point(2, 2));
		assertNotEquals(null, testLevel.getCell(2, 2));
	}
	
	@Test
	public void testDimension() {
		assertEquals(5, (int)testLevel.getDimension().getHeight());
		assertEquals(5, (int)testLevel.getDimension().getWidth());
	}
	
	@Test
	public void testName() {
		assertEquals("Level 1", testLevel.getLevelName());
	}
	
	@Test
	public void testIsPointValid() {
		assertTrue(testLevel.isPointValid(new Point(3, 1)));
		assertFalse(testLevel.isPointValid(new Point(8, 8)));
	}
		
	@Test
	public void testSunpoints() {
		assertEquals(500, testLevel.getSunpoints());
		testLevel.addToSunpoints(100);
		assertEquals(600, testLevel.getSunpoints());
		testLevel.subtractFromSunpoints(50);
		assertEquals(550, testLevel.getSunpoints());
	}

	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however (like GUI).
}
