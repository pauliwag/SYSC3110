package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.ActionProcessor;
import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.World;
import ca.carleton.pvz.actor.NormalPeaShooter;
import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.LevelOne;


public class UndoRedoTest {
	
	private Level testLevel;
	private NormalPeaShooter testPeaShooter;
	private Zombie testZombie;
	private PlantsVZombies testGame;
	private ActionProcessor actionProcessor;
	
	@Before
	public void setUp() throws Exception {
		testPeaShooter = new NormalPeaShooter();
	}

	/**
	 * Tests the methods of World class, to ensure it works as intended
	 * @result Before the stack is populated, the expectation is that it returns an EmptyStackException. The other asserts should expect normal behaviour.
	 */
	@Test
	public void testUndo() {
		testGame = new PlantsVZombies();
		testLevel = testGame.getWorld().getCurrentLevel();
		testZombie = new NormalZombie();
		actionProcessor = new ActionProcessor(testGame);
		
		testLevel.placeActor(testPeaShooter, new Point(0,2));
		testLevel.placeActor(testZombie, new Point(4,2));
		assertEquals(500, testZombie.getHealth());
		
		actionProcessor.shootZombies(testLevel);
		assertEquals(300, testZombie.getHealth());
		
		//add the current state of the game to the undoStack! Prepares it for testing.
		testGame.addToUndoStack(testGame.getWorld());
		
		//zombie is now at 100 hp. confirmed in next assert.
		
		actionProcessor.shootZombies(testLevel);
		assertEquals(100, testZombie.getHealth());
		
		//looking for undo now, should revert zombie to 300hp.

		testGame.undo();
		testLevel = testGame.getWorld().getCurrentLevel(); //updates the testLevel reference!
		testZombie = (Zombie)(testLevel.getCell(4, 2));
		assertEquals(300, testZombie.getHealth());
		
		testGame.redo();
		testLevel = testGame.getWorld().getCurrentLevel(); //updates the testLevel reference!
		testZombie = (Zombie)(testLevel.getCell(4, 2));
		assertEquals(100, testZombie.getHealth());
	}
}		
		