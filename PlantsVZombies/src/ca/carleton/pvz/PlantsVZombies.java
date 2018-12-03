package ca.carleton.pvz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import ca.carleton.pvz.gui.GUIController;
import ca.carleton.pvz.level.LevelOne;
import ca.carleton.pvz.level.LevelThree;
import ca.carleton.pvz.level.LevelTwo;
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
	private Stage primaryStage;

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
		gameWorld = world;
	}

	public static void saveObject(Object o, String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + path + "\n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static Object loadObject(String path) {
		Object o = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = in.readObject();
			in.close();
			fileIn.close();
			return o;
		} catch (IOException i) {
			i.printStackTrace();
			return o;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return o;
		}
	}
}
