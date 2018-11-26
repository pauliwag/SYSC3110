package ca.carleton.pvz.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ActorTest.class, CooldownManagerTest.class, LevelOneTest.class, LevelTest.class, NormalPeaShooterTest.class,
		PlantTest.class, SunflowerTest.class, WaveTest.class, WorldTest.class, ZombieTest.class, PeaShooterTest.class, GatlingPeaShooterTest.class, 
		FastZombieTest.class, NormalZombieTest.class, ShieldZombieTest.class, GUIControllerTest.class, })

public class AllTests {

}
