package ca.carleton.pvz.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.carleton.pvz.actor.GatlingPeaShooter;
import ca.carleton.pvz.actor.PeaShooter;
import ca.carleton.pvz.actor.Plant;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.gui.GUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;

public class GUIControllerTest {
	private static GUIController controller;

	public static class FakeApp extends Application {
		@Override
		public void start(Stage primaryStage) throws IOException {
			FXMLLoader loader = new FXMLLoader(GUIController.class.getResource("PVZGUI.fxml"));
			loader.load();
			controller = loader.getController();
		}
	}

	@BeforeClass
	public static void initJFX() throws InterruptedException {
		Thread t = new Thread("JavaFX Init Thread") {
			public void run() {
				Application.launch(FakeApp.class);
			}
		};
		t.setDaemon(true);
		t.start();
		Thread.sleep(1000);
	}

	/**
	 * Tests if sunflower button behaves as intended
	 * 
	 * @result Behaves as expected, returns only sunflower subtype, no other plant
	 *         subtype
	 */

	@Test
	public void testSunflowerButton() {
		controller.getSunflowerButton().fire();
		assertTrue(controller.getSelectedPlant() instanceof Plant);
		assertTrue(controller.getSelectedPlant() instanceof Sunflower);
		assertFalse(controller.getSelectedPlant() instanceof PeaShooter);
		assertFalse(controller.getSelectedPlant() instanceof GatlingPeaShooter);

	}

	/**
	 * Tests if peashooter button behaves as intended
	 * 
	 * @result Behaves as expected, returns only peashooter subtype, no other plant
	 *         subtype
	 */
	@Test
	public void testPeaShooterButton() {
		controller.getPeaShooterButton().fire();
		assertTrue(controller.getSelectedPlant() instanceof Plant);
		assertTrue(controller.getSelectedPlant() instanceof PeaShooter);
		assertFalse(controller.getSelectedPlant() instanceof Sunflower);
		assertFalse(controller.getSelectedPlant() instanceof GatlingPeaShooter);

	}

	/**
	 * Tests if threepeater (gatling peashooter) button behaves as intended
	 * 
	 * @result Behaves as expected, returns only (gatling peashooter) subtype, no
	 *         other plant subtype
	 */
	@Test
	public void testThreepeaterButton() {
		controller.getThreepeaterButton().fire();
		assertTrue(controller.getSelectedPlant() instanceof Plant);
		assertTrue(controller.getSelectedPlant() instanceof PeaShooter);
		assertTrue(controller.getSelectedPlant() instanceof GatlingPeaShooter);
		assertFalse(controller.getSelectedPlant() instanceof Sunflower);

	}

}
