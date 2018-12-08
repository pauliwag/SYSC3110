package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.ActionProcessor;
import ca.carleton.pvz.FileFactory;
import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.World;
import ca.carleton.pvz.actor.NormalPeaShooter;
import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.LevelOne;


public class SaveLoadTest {
	
	private Level testLevel;
	private NormalPeaShooter testPeaShooter;
	private Zombie testZombie;
	private Zombie testZombie2;
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
	public void testSaveLoad() {
		testGame = new PlantsVZombies();
		testLevel = testGame.getWorld().getCurrentLevel();
		testZombie = new NormalZombie();
		testZombie2 = new NormalZombie();
		actionProcessor = new ActionProcessor(testGame);
		
		testLevel.placeActor(testPeaShooter, new Point(0,2));
		testLevel.placeActor(testZombie, new Point(4,2));
		assertEquals(500, testZombie.getHealth());
		FileFactory.saveObject(testGame.getWorld(), "test.level");
		
		actionProcessor.shootZombies(testLevel);
		assertEquals(300, testZombie.getHealth());

		//loads the original world!
		testGame.setGameWorld((World)FileFactory.loadObject("test.level"));
		
		//tests if the zombie has the same hp as it did when the save occurred.
		testLevel = testGame.getWorld().getCurrentLevel(); //updates the testLevel reference!
		testZombie = (Zombie)(testLevel.getCell(4, 2));
		assertEquals(500, testZombie.getHealth()); //checks if the zombie is at saved hp
	}
	
	@Test
	public void testOverwrite() {
		testGame = new PlantsVZombies();
		testLevel = testGame.getWorld().getCurrentLevel();
		testZombie = new NormalZombie();
		testZombie2 = new NormalZombie();
		actionProcessor = new ActionProcessor(testGame);
		
		testLevel.placeActor(testPeaShooter, new Point(0,2));
		testLevel.placeActor(testZombie, new Point(4,2));
		FileFactory.saveObject(testGame.getWorld(), "test2.level");
		
		testLevel.placeActor(testZombie2, new Point(3, 3));
		actionProcessor.shootZombies(testLevel);
		assertEquals(300, testZombie.getHealth());
		FileFactory.saveObject(testGame.getWorld(), "test2.level");
		
		actionProcessor.shootZombies(testLevel);
		actionProcessor.shootZombies(testLevel);
		assertEquals(-100, testZombie.getHealth());
		
		// first zombie has now died. We will see that the overwrite save has successfully executed if we load the file
		// and we have a zombie with 300 hp (rather than 500 hp from first save) at (4,2)... and a zombie with full hp at (3,3).
		
		testGame.setGameWorld((World)FileFactory.loadObject("test2.level"));
		testLevel = testGame.getWorld().getCurrentLevel(); //updates the testLevel reference!
		testZombie = (Zombie)(testLevel.getCell(4, 2));
		testZombie2 = (Zombie)(testLevel.getCell(3, 3));
		assertEquals(300, testZombie.getHealth());
		assertEquals(500, testZombie2.getHealth());
		assertEquals(2, testLevel.getNumZombies());
	}
}		
		