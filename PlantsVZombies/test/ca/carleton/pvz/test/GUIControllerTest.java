package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.actor.Plant;
import ca.carleton.pvz.gui.GUIController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class GUIControllerTest {
	private PlantsVZombies game;
	private GUIController controller;
	private Plant testPlant;

	@Before
	public void setUp() throws Exception {
		// testActor = new NormalZombie();
		game = new PlantsVZombies();
		controller = new GUIController();
		controller.initialize();
		controller.pea
	}
	

}
