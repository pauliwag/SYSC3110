package ca.carleton.pvz.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ActorTest.class, CooldownManagerTest.class, LevelOneTest.class, LevelTest.class, PeaShooterTest.class,
		PlantTest.class, SunflowerTest.class/*, WaveTest.class, WorldTest.class, ZombieTest.class*/ })

public class AllTests {

}
