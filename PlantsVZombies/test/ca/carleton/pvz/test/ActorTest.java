package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;
import ca.carleton.pvz.actor.Actor;

public class ActorTest {

	private Actor testActor;

	@Before
	public void setUp() throws Exception {
		testActor = new NormalZombie();
	}

	/**
	 * Tests the image creator method in Actor.
	 * 
	 * @result The sprite is generated successfully using Actor subtype
	 */
	@Test
	public void testActor() {
		assertFalse(testActor.getSprite(Terrain.GRASS) == null);
		assertTrue(testActor.getSprite(Terrain.GRASS) instanceof Image);

	}

	// tearDown() is not necessary here, as garbage collection of objects
	// after the test class concludes. Other things that consume system
	// resources may need tearDown() however.

}
