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
