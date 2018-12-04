package ca.carleton.pvz.gui;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.World;
import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.CooldownManager;
import ca.carleton.pvz.actor.GatlingPeaShooter;
import ca.carleton.pvz.actor.NormalPeaShooter;
import ca.carleton.pvz.actor.Plant;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.level.CustomLevel;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.Level.Terrain;
import ca.carleton.pvz.level.Wave;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class controls the JavaFX fxml user interface.
 *
 * @author Group 5
 *
 */
public class GUIController {

	private PlantsVZombies game;
	private Plant selectedPlant;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label peashooterCooldown;

	@FXML
	private Label levelLabel;

	@FXML
	private Label waveLabel;

	@FXML
	private Label sunpointLabel;

	@FXML
	private Button nextTurnButton;

	@FXML
	private HBox sunflowerButton;

	@FXML
	private Button nextLevelButton;

	@FXML
	private HBox peashooterButton;

	@FXML
	private HBox threepeaterButton;

	@FXML
	private Button undoButton;

	@FXML
	private Button redoButton;

	@FXML
	private Label sunflowerCooldown;

	@FXML
	private Label threepeaterCooldown;

	@FXML
	private Label threepeaterCost;

	@FXML
	private Label sunflowerCost;

	@FXML
	private Label peashooterCost;

	@FXML
	private GridPane gameGrid;

	@FXML
	private Group plantGroup;

	@FXML
	private MenuItem quitButton;

	@FXML
	private MenuItem openLevelBuilder;

	@FXML
	private MenuItem newWorldButton;

	@FXML
	private MenuItem aboutButton;

	@FXML
	private MenuItem addCustomLevel;

	@FXML
	private CheckBox allowUndoRedo;

	@FXML
	private VBox plantVBox;

	@FXML
	private ImageView threepeaterIcon;

	@FXML
	private ImageView peashooterIcon;

	@FXML
	private ImageView sunflowerIcon;

	@FXML
	public void initialize() {

		assert addCustomLevel != null : "fx:id=\"addCustomLevel\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert newWorldButton != null : "fx:id=\"newWorldButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert peashooterCooldown != null : "fx:id=\"peashooterCooldown\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert openLevelBuilder != null : "fx:id=\"openLevelBuilder\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert nextTurnButton != null : "fx:id=\"nextTurnButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert sunflowerCooldown != null : "fx:id=\"sunflowerCooldown\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert peashooterButton != null : "fx:id=\"peashooterButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert sunflowerButton != null : "fx:id=\"sunflowerButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert gameGrid != null : "fx:id=\"gameGrid\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert plantGroup != null : "fx:id=\"gameGrid\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert levelLabel != null : "fx:id=\"levelLabel\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert waveLabel != null : "fx:id=\"waveLabel\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert sunpointLabel != null : "fx:id=\"sunpointLabel\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert nextLevelButton != null : "fx:id=\"nextLevelButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert aboutButton != null : "fx:id=\"aboutButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert sunflowerCost != null : "fx:id=\"sunflowerCost\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert peashooterCost != null : "fx:id=\"peashooterCost\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert threepeaterCost != null : "fx:id=\"threepeaterCost\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert threepeaterCooldown != null : "fx:id=\"threepeaterCooldown\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert threepeaterButton != null : "fx:id=\"threepeaterButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert redoButton != null : "fx:id=\"redoButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert allowUndoRedo != null : "fx:id=\"allowUndoRedo\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert plantVBox != null : "fx:id=\"plantVBox\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert threepeaterIcon != null : "fx:id=\"threepeaterIcon\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert peashooterIcon != null : "fx:id=\"peashooterIcon\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert sunflowerIcon != null : "fx:id=\"sunflowerIcon\" was not injected: check your FXML file 'pvzgui.fxml'.";

		setupUndoRedo();
		setupMenuButtons();
		setupPlantSelectionButtons();
		setupNextTurnButton();
		updateCostDisplay();
		initGameGrid();

		redoButton.setDisable(true);
		undoButton.setDisable(true);

	}

	/**
	 * This empty constructor is required for proper loading of the JavaFX GUI
	 * controller
	 */
	public GUIController() {
	}

	/**
	 * Sets up plant button event handlers. When button is pressed, sets
	 * currently selected plant object to correct type.
	 */
	private void setupPlantSelectionButtons() {
		sunflowerIcon
				.setImage(new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/sunflower_icon.png")));
		peashooterIcon
				.setImage(new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/peashooter_icon.png")));
		threepeaterIcon
				.setImage(new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/threepeater_icon.png")));

		selectedPlant = new Sunflower();
		sunflowerButton.setOnMouseClicked((e) -> {
			sunflowerButton.requestFocus();
			selectedPlant = new Sunflower();
		});

		peashooterButton.setOnMouseClicked((e) -> {
			peashooterButton.requestFocus();
			selectedPlant = new NormalPeaShooter();
		});

		threepeaterButton.setOnMouseClicked((e) -> {
			threepeaterButton.requestFocus();
			selectedPlant = new GatlingPeaShooter();
		});

		for (Node child : plantVBox.getChildren()) {
			if (child instanceof HBox) {
				HBox button = (HBox) child;
				button.opacityProperty().bind(Bindings.when(button.focusedProperty()).then(1).otherwise(0.75));
				button.styleProperty().bind(Bindings.when(button.hoverProperty()).then("-fx-background-color: #dbdbdb")
						.otherwise("-fx-background-color: #f4f4f4"));
			}
		}
	}

	/**
	 * Update the undo/redo buttons to represent if undo/redo is possible.
	 */
	private void updateUndoRedo() {
		if (game.hasRedo()) {
			redoButton.setDisable(false);
		} else {
			redoButton.setDisable(true);
		}
		if (game.hasUndo() && logMoves()) {
			undoButton.setDisable(false);
		} else {
			undoButton.setDisable(true);
		}
	}

	/**
	 * Sets up the next turn button's event handler
	 */
	private void setupNextTurnButton() {
		nextTurnButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				game.getActionProcessor().processNextTurn();
				updateGameGrid();
				if (game.isGameOver()) {
					notifyGameOver();
					return;
				}
				if (game.getWorld().isCurrentLevelBeat()) {
					notifyLevelBeat();
				}
			}
		});
	}

	/**
	 * Called when the current level is beat.
	 */
	private void notifyLevelBeat() {

		World gameWorld = game.getWorld();
		int beatLevelNum = gameWorld.getCurrentLevel().getNum();

		// remove the current (beat) level from the head of the
		// priority queue and check whether the next level is null
		if (gameWorld.nextLevel() == null) {
			notifyGameBeat();
		}

		else {
			showAlert("Level Beat!", "Woohoo! You beat Level " + beatLevelNum + "!",
					"Click \"OK\" to proceed to the next level.", AlertType.INFORMATION);
			CooldownManager.resetCDs();
			game.emptyUndoRedo();
			updateGameGrid(); // reset grid for next level
		}

	}

	/**
	 * Called when the user loses. The user can then either retry the current
	 * level or quit.
	 */
	public void notifyGameOver() {

		showAlert("Game Over", "Game over! You failed to protect your home from the zombies :(", "Retry level?",
				AlertType.CONFIRMATION).ifPresent(response -> {

					if (response == ButtonType.OK) {

						World gameWorld = game.getWorld();

						// store active level before polling levels queue
						Level currLevel = gameWorld.getCurrentLevel();
						Class<? extends Level> currLevelClass = (Class<? extends Level>) currLevel.getClass();

						gameWorld.nextLevel(); // poll levels queue

						// accommodate custom 4-arg constructor
						if (currLevelClass == CustomLevel.class) {
							CustomLevel ctomLevel = (CustomLevel) currLevel;
							Class<?>[] cArg = { int.class, int.class, Terrain.class, Collection.class };
							int levelNum = ctomLevel.getNum();
							int startingSunpoints = ctomLevel.getStartingSunpoints();
							Terrain terrain = ctomLevel.getTerrain();
							Collection<Wave> wavesClone = ctomLevel.getWavesClone();
							try {
								gameWorld.addLevels(currLevelClass.getDeclaredConstructor(cArg).newInstance(levelNum,
										startingSunpoints, terrain, wavesClone));
							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
									| InvocationTargetException | NoSuchMethodException | SecurityException e) {
								game.levelReloadFailureProtocol(e);
							}
							game.finalizeLevelReload();
						}

						else { // can invoke 0-arg constructor
							try {
								gameWorld.addLevels(currLevelClass.newInstance());
							} catch (InstantiationException | IllegalAccessException e) {
								game.levelReloadFailureProtocol(e);
							}
							game.finalizeLevelReload();
						}

					} else { // user clicked cancel
						disableButtons();
					}

				});

	}


	/**
	 * Disable buttons; e.g., for a game over.
	 */
	public void disableButtons() {
		nextTurnButton.setDisable(true);
		gameGrid.setDisable(true);
		redoButton.setDisable(true);
		undoButton.setDisable(true);
		allowUndoRedo.setDisable(true);
	}

	/**
	 * Enable those buttons disabled via disableButtons().
	 */
	private void enableButtons() {
		nextTurnButton.setDisable(false);
		gameGrid.setDisable(false);
		redoButton.setDisable(false);
		undoButton.setDisable(false);
		allowUndoRedo.setDisable(false);
	}

	/**
	 * Called when all levels in the game are beat.
	 */
	private void notifyGameBeat() {
		disableButtons();
		showAlert("You Won!", "Wow! You beat all levels in the game! :)", null, AlertType.INFORMATION);
	}

	private void setupUndoRedo() {
		undoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (game.hasUndo()) {
					game.undo();
					updateGameGrid();
				}
			}
		});
		redoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (game.hasRedo()) {
					game.redo();
					updateGameGrid();
				}
			}
		});
		allowUndoRedo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (allowUndoRedo.isSelected()) {
					updateUndoRedo();
				} else {
					redoButton.setDisable(true);
					undoButton.setDisable(true);
					game.emptyUndoRedo();
				}
			}
		});
	}

	/**
	 * Sets up menu button actions
	 */
	private void setupMenuButtons() {
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});
		aboutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showAlert("About", "Plants vs. Zombies v4.0 by Team 5",
						"Authors: Paul Roode, Abdillahi Nur, Sameed Mohammed, Jacob Laboissonniere",
						AlertType.INFORMATION);
			}
		});
		openLevelBuilder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				new LevelBuilder(game.getPrimaryStage());
			}
		});
		newWorldButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				CooldownManager.resetCDs();
				game.setGameWorld(new World());
				game.emptyUndoRedo();
				game.setGameOver();
				disableButtons();
				emptyGameGrid();
				updateLevelLabel();
				updateSunpointLabel();
				updateWaveNumber();
				updateCooldownDisplay();
			}
		});
		addCustomLevel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Load Custom Level");
				dialog.setContentText("Please enter the level name:");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()) {
					Object o = PlantsVZombies.loadObject(result.get() + ".level");
					if (o instanceof Level) {
						Level lvl = (Level) o;
						game.getWorld().addLevels(lvl);
						enableButtons();
						updateGameGrid();
						game.unsetGameOver();
					}
				}
			}
		});
	}

	/**
	 * Initializes the game grid to grass images and adds event handlers to each
	 * grid slot.
	 */
	private void initGameGrid() {
		gameGrid.setDisable(false);
		ObservableList<Node> children = gameGrid.getChildren();
		InputStream stream = getClass().getResourceAsStream("/ca/carleton/pvz/resources/grass.png");
		Image grass = new Image(stream);
		for (Node child : children) {
			if (child instanceof ImageView) {
				ImageView imgView = (ImageView) child;
				imgView.setImage(grass);
				imgView.setPreserveRatio(false);
				imgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						int row, column;
						if (GridPane.getRowIndex(imgView) != null && GridPane.getColumnIndex(imgView) != null) {
							row = GridPane.getRowIndex(imgView);
							column = GridPane.getColumnIndex(imgView);
							game.getActionProcessor().processPlanting(game.getWorld().getCurrentLevel(), selectedPlant,
									column, row);
						}
						updateGameGrid();
						event.consume();
					}
				});
			}
		}
	}

	private void emptyGameGrid() {
		gameGrid.setDisable(true);
		ObservableList<Node> children = gameGrid.getChildren();
		for (Node child : children) {
			if (child instanceof ImageView) {
				ImageView imgView = (ImageView) child;
				imgView.setImage(null);
			}
		}
	}

	/**
	 * Update the plant cooldown labels to represent CooldownManager values
	 */
	private void updateCooldownDisplay() {
		peashooterCooldown.setText(Integer.toString(CooldownManager.getNormalPeaTimeLeftOnCD()));
		sunflowerCooldown.setText(Integer.toString(CooldownManager.getSunTimeLeftOnCD()));
		threepeaterCooldown.setText(Integer.toString(CooldownManager.getGatlingPeaTimeLeftOnCD()));
	}

	/**
	 * Update the plant cooldown labels to represent CooldownManager values
	 */
	private void updateCostDisplay() {
		peashooterCost.setText(Integer.toString(NormalPeaShooter.NORMAL_PEA_COST));
		sunflowerCost.setText(Integer.toString(Sunflower.SUNFLOWER_COST));
		threepeaterCost.setText(Integer.toString(GatlingPeaShooter.GATLING_PEA_COST));
	}

	/**
	 * Updates sunpoint label on UI.
	 */
	private void updateSunpointLabel() {
		if (game.getWorld().getCurrentLevel() != null) {
			sunpointLabel.setText("  Sunpoints: " + Integer.toString(game.getWorld().getCurrentLevel().getSunpoints()));
		} else {
			sunpointLabel.setText("  Sunpoints: none");
		}
	}

	/**
	 * Updates level label on UI.
	 */
	private void updateLevelLabel() {
		if (game.getWorld().getCurrentLevel() != null) {
			int levelNum = game.getWorld().getCurrentLevel().getNum();
			levelLabel.setText("  Level: " + levelNum);
		} else {
			levelLabel.setText("  Level: none");
		}
	}

	/**
	 * Updates the wave label on UI.
	 */
	private void updateWaveNumber() {
		Level lvl = game.getWorld().getCurrentLevel();
		if (lvl != null) {
			if (lvl.getNumWaves() > 0) { // failsafe: prevent NPE
				waveLabel.setText("  Wave: " + Integer.toString(lvl.getHeadWave().getNum()));
			}
		} else {
			waveLabel.setText("  Wave: none");
		}
	}

	/**
	 * Sets up the game grid sprites to represent the current level's state.
	 */
	public void updateGameGrid() {
		ObservableList<Node> children = gameGrid.getChildren();
		Image texture = game.getWorld().getCurrentLevel().getSprite();
		for (Node child : children) {
			if (child instanceof ImageView) {
				ImageView imgView = (ImageView) child;
				int row, column;
				if (GridPane.getRowIndex(imgView) != null && GridPane.getColumnIndex(imgView) != null) {
					row = GridPane.getRowIndex(imgView);
					column = GridPane.getColumnIndex(imgView);
					Actor actor = game.getWorld().getCurrentLevel().getCell(column, row);
					if (actor != null) {
						imgView.setImage(actor.getSprite(game.getWorld().getCurrentLevel().getTerrain()));
					} else {
						imgView.setImage(texture);
					}
				}
			}
		}
		updateUndoRedo();
		updateWaveNumber();
		updateSunpointLabel();
		updateLevelLabel();
		updateCooldownDisplay();
	}

	/**
	 * Shows the user a pop-up alert dialog.
	 *
	 * @param title Title of the alert dialog
	 * @param header Header of the alert dialog (can be null for no header)
	 * @param content Content of the alert dialog
	 * @param type Type of alert - see AlertType documentation
	 * @return The user's selection.
	 */
	public static Optional<ButtonType> showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		return alert.showAndWait();
	}

	/**
	 * Disable the next turn button
	 */
	public void disableNextTurn() {
		nextTurnButton.setDisable(true);
	}

	/**
	 * Enable the next turn button
	 */
	public void enableNextTurn() {
		nextTurnButton.setDisable(false);
	}

	/**
	 * Set the game that this controller controls
	 *
	 * @param game to control
	 */
	public void setGame(PlantsVZombies game) {
		this.game = game;
	}

	/**
	 * Returns boolean letting user know if allowUndoRedo checkbox is selected
	 *
	 * @return allowUndoRedo.isSelected(); Boolean letting user know if
	 *         allowUndoRedo checkbox is selected
	 */
	public boolean logMoves() {
		return allowUndoRedo.isSelected();
	}

	/**
	 * Returns plant selected by user
	 *
	 * @return selectedPlant plant selected by user
	 */
	public Plant getSelectedPlant() {
		return selectedPlant;
	}

	/**
	 * Returns sunflower button on GUI
	 *
	 * @return sunflowerButton sunflower button on GUI
	 */
	public HBox getSunflowerButton() {
		return sunflowerButton;
	}

	/**
	 * Returns next turn button on GUI
	 *
	 * @return nextTurnButton sunflower button on GUI
	 */
	public Button getNextTurnButton() {
		return nextTurnButton;
	}

	/**
	 * Returns PeaShooter button on GUI
	 *
	 * @return peashooterButton sunflower button on GUI
	 */
	public HBox getPeaShooterButton() {
		return peashooterButton;
	}

	/**
	 * Returns Threepeater button on GUI
	 *
	 * @return peashooterButton sunflower button on GUI
	 */
	public HBox getThreepeaterButton() {
		return threepeaterButton;
	}

	/**
	 * Returns game modified by controller
	 *
	 * @return game game modified by controller
	 */
	public PlantsVZombies getGame() {
		return game;
	}

	/**
	 * Returns undo/redo checkbox on GUI
	 *
	 * @return allowUndoRedo undo/redo checkbox on GUI
	 */
	public CheckBox getCheckbox() {
		return allowUndoRedo;
	}

	/**
	 * Returns undo button on GUI
	 *
	 * @return undoButton undo button on GUI
	 */
	public Button getUndoButton() {
		return undoButton;
	}

	/**
	 * Returns redo button on GUI
	 *
	 * @return redoButton undo button on GUI
	 */
	public Button getRedoButton() {
		return redoButton;
	}

}
