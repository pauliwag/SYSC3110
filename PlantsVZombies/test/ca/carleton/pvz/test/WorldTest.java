package ca.carleton.pvz.test;

import static org.junit.Assert.*;
import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.World;
import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.LevelThree;
import ca.carleton.pvz.level.LevelTwo;

public class WorldTest {

	World testWorld;
	LevelOne testLevel1;
	LevelTwo testLevel2;
	LevelThree testLevel3;
	LevelThree testLevel3secondary;
	
	@Before
	public void setUp() throws Exception {
		testWorld = new World();
		testLevel1 = new LevelOne();
		testLevel2 = new LevelTwo();
		testLevel3 = new LevelThree();
		testLevel3secondary = new LevelThree();
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
		testWorld.addLevels(new LevelOne(), new LevelTwo());
	}
	
	@Test
	public void testWorldLevels() {
		assertEquals(null, testWorld.getCurrentLevel());
		assertEquals(0, testWorld.getNumOfLevels());
		
		testWorld.addLevels(testLevel1);
		assertEquals(testLevel1, testWorld.getCurrentLevel());
		
		testWorld.addLevels(testLevel2);
		assertEquals(testLevel1, testWorld.getCurrentLevel());
		testWorld.nextLevel();
		assertEquals(testLevel2, testWorld.getCurrentLevel());
		assertEquals(1, testWorld.getNumOfLevels());
		
		//testWorld.addLevels(testLevel1, testLevel2, testLevel3, testLevel3secondary);
		//assertEquals(2, testWorld.getNumOfLevels());
	}
	

}
