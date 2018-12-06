package ca.carleton.pvz.test;

import static org.junit.Assert.*;
import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.World;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.LevelTwo;

public class WorldTest {

	World testWorld;
	LevelOne testLevel1;
	LevelTwo testLevel2;
	
	@Before
	public void setUp() throws Exception {
		testWorld = new World();
	}

	/**
	 * Tests the methods of World class, to ensure it works as intended
	 * @result Before the stack is populated, the expectation is that it returns an EmptyStackException. The other asserts should expect normal behaviour.
	 */
	@Test
	public void testWorld() {
		testWorld.addLevels(testLevel1, testLevel2);
		try {
			testWorld.getCurrentLevel();
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}

}
