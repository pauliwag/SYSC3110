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

	/**
	 * Create a valid account.
	 * @result Account will be persisted without any errors,
	 *         and Account.getId() will no longer be <code>null</code>
	 */
	@Test
	public void testCompletion() {
		assertFalse(testLevel.isHeadWaveEmpty());
		assertFalse(testLevel.isBeat());
	}
	
	/**
	 * Tests the turns functionality of the Level class
	 * @result All turns increment correctly and as expected
	 */
	@Test
	public void testTurn() {
		assertEquals(0, testLevel.getTurn());
		testLevel.incTurn();
		assertEquals(1, testLevel.getTurn());
		assertEquals(0, testLevel.getPrevTurn());
	}
	
	/**
	 * Tests the cell functionality of the Level class (square on the board)
	 * @result All cells place and "get" as intended, whether there is an Actor on the cell or not
	 */
	@Test
	public void testCell() {
		//CELL TESTING
		assertEquals(null, testLevel.getCell(2,2));
		testLevel.placeActor(new Actor(), new Point(2, 2));
		assertNotEquals(null, testLevel.getCell(2, 2));
	}
	
	/**
	 * Tests the dimensions functionality of the board in Level
	 * @result the Dimension getters work as intended (it's only expecting 5 in this case because the default size is 5x5)
	 */
	@Test
	public void testDimension() {
		assertEquals(5, (int)testLevel.getDimension().getHeight());
		assertEquals(5, (int)testLevel.getDimension().getWidth());
	}
	
	/**
	 * Testing the name of Level
	 * @result The name of testLevelOne should be "Level 1"
	 */
	@Test
	public void testName() {
		assertEquals("Level 1", testLevel.getLevelName());
	}
	
	/**
	 * Testing the isPointValid() method
	 * @result The method works as intended, and returns true and false accordingly. Both are tested extensively.
	 */
	@Test
	public void testIsPointValid() {
		assertTrue(testLevel.isPointValid(new Point(3, 1)));
		assertFalse(testLevel.isPointValid(new Point(8, 8)));
	}
	
	/**
	 * Tests the sunpoints mechanic of Level
	 * @result All the sunpoints variations behave as intended, including adding or subtracting sunpoints
	 */
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
