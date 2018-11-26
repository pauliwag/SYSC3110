package ca.carleton.pvz.gui;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.CooldownManager;
import ca.carleton.pvz.actor.GatlingPeaShooter;
import ca.carleton.pvz.actor.NormalPeaShooter;
import ca.carleton.pvz.actor.Plant;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.level.Level;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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
	private Button sunflowerButton;

	@FXML
	private Button nextLevelButton;

	@FXML
	private Button peashooterButton;

	@FXML
	private Button threepeaterButton;

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
	private MenuItem aboutButton;

	@FXML
	private CheckBox allowUndoRedo;

	@FXML
	public void initialize() {
		assert peashooterCooldown != null : "fx:id=\"peashooterCooldown\" was not injected: check your FXML file 'pvzgui.fxml'.";
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

		setupUndoRedo();
		setupMenuButtons();
		setupPlantSelectionButtons();
		setupNextTurnButton();
		updateCostDisplay();
		initGameGrid();
		nextLevelButton.setDisable(true); // we only have one Level in this
											// version, so disable button
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
	 * Sets up plant button event handlers. When button is pressed, sets currently
	 * selected plant object to correct type.
	 */
	private void setupPlantSelectionButtons() {
		selectedPlant = new Sunflower();
		sunflowerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				selectedPlant = new Sunflower();
			}
		});

		peashooterButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				selectedPlant = new NormalPeaShooter();
			}
		});

		threepeaterButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				selectedPlant = new GatlingPeaShooter();
			}
		});

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

		if (game.hasUndo()) {
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
				if (game.getActionProcessor().isGameOver()) {
					notifyGameOver();
					return;
				}

				updateGameGrid();
			}
		});
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
					redoButton.setDisable(false);
					undoButton.setDisable(false);
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
				showAlert("About", "Plants VS Zombies Group 5",
						"Created by Paul Roode, Abdillahi Nur, Sameed Mohammed, and Jacob Laboissonniere",
						AlertType.INFORMATION);
			}
		});
	}

	/**
	 * Initializes the game grid to grass images and adds event handlers to each
	 * grid slot.
	 */
	private void initGameGrid() {
		ObservableList<Node> children = gameGrid.getChildren();
		InputStream stream = getClass().getResourceAsStream("grass.png");
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
	 * Updates sunpoint label on UI
	 */
	private void updateSunpointLabel() {
		sunpointLabel.setText("  Sunpoints: " + Integer.toString(game.getWorld().getCurrentLevel().getSunpoints()));
	}

	/**
	 * Updates the wave label on UI
	 */
	private void updateWaveNumber() {
		Level lvl = game.getWorld().getCurrentLevel();
		if (lvl.getNumWaves() > 0) { // failsafe: prevent NPE
			waveLabel.setText("  Wave: " + Integer.toString(lvl.getHeadWave().getNum()));
		}
	}

	/**
	 * Sets up the game grid sprites to represent the current level's state.
	 */
	public void updateGameGrid() {
		ObservableList<Node> children = gameGrid.getChildren();
		InputStream stream = getClass().getResourceAsStream("grass.png");
		Image grass = new Image(stream);
		for (Node child : children) {
			if (child instanceof ImageView) {
				ImageView imgView = (ImageView) child;
				int row, column;

				if (GridPane.getRowIndex(imgView) != null && GridPane.getColumnIndex(imgView) != null) {
					row = GridPane.getRowIndex(imgView);
					column = GridPane.getColumnIndex(imgView);
					Actor actor = game.getWorld().getCurrentLevel().getCell(column, row);

					if (actor != null) {
						imgView.setImage(actor.getSprite());
					} else {
						imgView.setImage(grass);
					}
				}
			}
		}
		if (logMoves()) {
			updateUndoRedo();
		}
		updateWaveNumber();
		updateSunpointLabel();
		updateCooldownDisplay();
	}

	/**
	 * Called when game is over. Disables buttons we don't want pressed and alerts
	 * user.
	 */
	public void notifyGameOver() {
		updateGameGrid();
		nextTurnButton.setDisable(true);
		gameGrid.setDisable(true);
		plantGroup.setDisable(true);
		redoButton.setDisable(true);
		undoButton.setDisable(true);
		allowUndoRedo.setDisable(true);
		showAlert("Game Over", null, "Game over! You failed to protect your home from the zombies :(",
				AlertType.INFORMATION);
	}

	/**
	 * Shows the user a pop-up alert dialog
	 *
	 * @param title
	 *            Title of the alert dialog
	 * @param header
	 *            Header of the alert dialog (can be null for no header)
	 * @param content
	 *            Content of the alert dialog
	 * @param type
	 *            Type of alert - see AlertType documentation
	 */
	public void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
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
	 * @param game
	 *            to control
	 */
	public void setGame(PlantsVZombies game) {
		this.game = game;
	}

	public boolean logMoves() {
		return allowUndoRedo.isSelected();
	}

	public Plant getSelectedPlant() {
		return selectedPlant;
	}

	public Button getSunflowerButton() {
		return sunflowerButton;
	}

	public Button getNextLevelButton() {
		return nextLevelButton;
	}

	public Button getPeaShooterButton() {
		return peashooterButton;
	}

	public Button getThreepeaterButton() {
		return threepeaterButton;
	}

	public PlantsVZombies getGame() {
		return game;
	}
	
	public CheckBox getCheckbox() {
		return allowUndoRedo;
	}
	
	public Button getUndoButton() {
		return undoButton;
	}
	
	public Button getRedoButton() {
		return redoButton;
	}

}

/*
 * private Button sunflowerButton;
 * 
 * @FXML private Button nextLevelButton;
 * 
 * @FXML private Button peashooterButton;
 * 
 * @FXML private Button threepeaterButton;
 */
