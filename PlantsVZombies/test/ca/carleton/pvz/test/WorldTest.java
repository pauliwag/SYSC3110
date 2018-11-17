package ca.carleton.pvz.test;

import static org.junit.Assert.*;
import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.World;
import ca.carleton.pvz.level.Level;

public class WorldTest {

	World testWorld;
	Level testLevel1;
	Level testLevel2;
	Level testLevel3;
	
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
		try {
			testWorld.getCurrentLevel();
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
		testWorld.addLevel(testLevel1);
		testWorld.addLevel(testLevel2);
	}

}
