package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.actor.Plant;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.gui.GUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class GUIControllerTest {
	private PlantsVZombies game;
	private static GUIController controller;
	private Plant testPlant;
	private Stage testStage;

	@Before
	public void setUp() throws Exception {
		// testActor = new NormalZombie();
		game = new PlantsVZombies();
		
		game.start(testStage);
		//FXMLLoader loader = new FXMLLoader(GUIController.class.getResource("PVZGUI.fxml"));
		//controller = loader.getController();
		//controller.setGame(game);
		//controller.initialize();
		controller = game.getController();
		controller.initialize();
	}
	
	@Test
	public void testSunflowerButton() {
		controller.getSunflowerButton().fire();
		assertTrue(controller.getSelectedPlant() instanceof Sunflower);

	}
	
	
	
}
