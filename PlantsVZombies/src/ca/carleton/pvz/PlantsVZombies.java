package ca.carleton.pvz;

import java.util.Stack;

import ca.carleton.pvz.gui.GUIController;
import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.LevelThree;
import ca.carleton.pvz.level.LevelTwo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
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
	private Stage primaryStage;
	private static World defaultWorld;

	/**
	 * The start method for the JavaFX GUI. Loads GUI from .fxml file and
	 * creates/shows a scene containing it.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(GUIController.class.getResource("PVZGUI.fxml"));
		BorderPane borderPane = loader.load();
		// get the controller from the FXMLLoader
		controller = loader.getController();
		controller.setGame(this);
		Scene scene = new Scene(borderPane, 1360.0, 615.0);
		primaryStage.setTitle("Plants VS Zombies Group 5");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		this.primaryStage = primaryStage;
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
		gameWorld.addLevels(new LevelOne(), new LevelTwo(), new LevelThree());
		if(defaultWorld == null) {
			defaultWorld = World.copy(gameWorld);
		}
		gameOver = false;
		undoStack = new Stack<World>();
		redoStack = new Stack<World>();
		addToUndoStack(gameWorld);
	}

	/**
	 * Gets the active game world, which represents the current game state.
	 *
	 * @return The active game world.
	 */
	public World getWorld() {
		return gameWorld;
	}

	/**
	 * Gets the default game world.
	 *
	 * @return The default game world.
	 */
	public World getDefaultWorld() {
		return defaultWorld;
	}

	/**
	 * Returns the primary stage. Used to add more stages on top of primary.
	 *
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Sets gameOver to true.
	 */
	public void setGameOver() {
		gameOver = true;
	}

	/**
	 * Sets gameOver to false.
	 */
	public void unsetGameOver() {
		gameOver = false;
	}

	/**
	 * Returns whether this game is over; i.e., whether the player has lost.
	 *
	 * @return true if this game is over, false otherwise.
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Gets this game's action processor.
	 *
	 * @return This game's action processor.
	 */
	public ActionProcessor getActionProcessor() {
		return actionProcessor;
	}

	/**
	 * Adds a world to the undo stack.
	 *
	 * @param The world to be added to the undo stack.
	 */
	public void addToUndoStack(World world) {
		undoStack.push(World.copy(world));
	}

	/**
	 * Undo a move - sets game world to top of undo stack, and adds to redo
	 * stack.
	 */
	public void undo() {
		addToRedoStack(getWorld());
		setGameWorld(undoStack.pop());
	}

	/**
	 * Adds a world to the redo stack.
	 *
	 * @param The world to be added to the redo stack.
	 */
	public void addToRedoStack(World world) {
		redoStack.push(World.copy(world));
	}

	/**
	 * Redoes a move that was undone.
	 */
	public void redo() {
		addToUndoStack(getWorld());
		setGameWorld(redoStack.pop());
	}

	/**
	 * Are there more states we can undo to?
	 *
	 * @return true if yes, false if no.
	 */
	public boolean hasUndo() {
		return !undoStack.isEmpty();
	}

	/**
	 * Are there more states we can redo to?
	 *
	 * @return true if yes, false if no.
	 */
	public boolean hasRedo() {
		return !redoStack.isEmpty();
	}

	/**
	 * Empties undo and redo stacks. Called when allow undo/redo checkbox is
	 * ticked.
	 */
	public void emptyUndoRedo() {
		redoStack.clear();
		undoStack.clear();
	}

	/**
	 * Updates the current game world.
	 *
	 * @param world The new game world.
	 */
	public void setGameWorld(World world) {
		if (world != null) {
			gameWorld = world;
		}
	}

	/**
	 * The protocol for level reload failure.
	 *
	 * @param e The caught exception.
	 */
	public void levelReloadFailureProtocol(Exception e) {
		System.out.println("Could not reload level; exception details below:");
		e.printStackTrace();
		GUIController.showAlert("Level Reload Failure", "Could not reload level",
				"You must load the level manually (via the menu).", AlertType.INFORMATION);
		controller.disableButtons();
	}

	/**
	 * Finalizes a level reload.
	 */
	public void finalizeLevelReload() {
		unsetGameOver();
		gameWorld.getCooldownManager().resetCDs();
		emptyUndoRedo();
		controller.updateGameGrid();
	}

}
