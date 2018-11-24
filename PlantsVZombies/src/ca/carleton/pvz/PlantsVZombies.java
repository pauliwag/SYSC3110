package ca.carleton.pvz;

import java.util.Stack;

import ca.carleton.pvz.gui.GUIController;
import ca.carleton.pvz.level.LevelOne;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class manages the flow of the game.
 *
 */
public class PlantsVZombies extends Application {

	private World gameWorld; // stores the levels to be played
	private ActionProcessor actionProcessor;
	private boolean gameOver;
	private static GUIController controller;
	private Stack<World> undoStack;
	private Stack<World> redoStack;
	
	/**
	 * The start method for the JavaFX GUI. Loads GUI from fxml file and
	 * creates/shows a scene containing it.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(GUIController.class.getResource("PVZGUI.fxml"));
		BorderPane borderPane = loader.load();

		// Get the Controller from the FXMLLoader
		controller = loader.getController();
		controller.setGame(this);
		Scene scene = new Scene(borderPane, 1360.0, 615.0);
		primaryStage.setTitle("Plants VS Zombies Group 5");
		primaryStage.setResizable(false);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Gets this game's GUI controller.
	 *
	 * @return This game's GUI controller.
	 */
	public GUIController getController() {
		return controller;
	}

	/**
	 * Constructs a new game to be played.
	 */
	public PlantsVZombies() {
		gameWorld = new World();
		actionProcessor = new ActionProcessor(this);
		gameWorld.addLevel(new LevelOne());
		gameOver = false;
		undoStack = new Stack<World>();
		redoStack = new Stack<World>();
		addToUndoStack(gameWorld);
	}

	public World getWorld() {
		return gameWorld;
	}

	/**
	 * Sets gameOver to true.
	 */
	public void setGameOver() {
		gameOver = true;
	}

	/**
	 * Is game over?
	 *
	 * @return true if game is over, false otherwise.
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Get the game's action processor
	 *
	 * @return the game action processor
	 */
	public ActionProcessor getActionProcessor() {
		return actionProcessor;
	}
	
	
	public void addToUndoStack(World world) {
		System.out.println("undostack: " + undoStack.size());
		undoStack.push(World.copy(world));
	}
	
	public void undo() {
		addToRedoStack(getWorld());
		setGameWorld(undoStack.pop());
	}
	
	public void addToRedoStack(World world) {
		redoStack.push(World.copy(world));
		System.out.println("redostack: " + redoStack.size());
	}
	
	public void redo() {
		addToUndoStack(redoStack.peek());
		setGameWorld(redoStack.pop());
	}
	
	public boolean hasUndo() {
		return !undoStack.isEmpty();
	}
	
	public boolean hasRedo() {
		return !redoStack.isEmpty();
	}
	
	public void emptyUndoRedo() {
		redoStack.clear();
		undoStack.clear();
	}
	public void setGameWorld(World world) {
		gameWorld = world;
	}
}
