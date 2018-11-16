package ca.carleton.pvz.gui;

import java.awt.Point;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.CooldownManager;
import ca.carleton.pvz.actor.PeaShooter;
import ca.carleton.pvz.actor.Plant;
import ca.carleton.pvz.actor.Sunflower;
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

	// @FXML
	// private Label sunpoints;

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
	private Label sunflowerCooldown;

	@FXML
	private GridPane gameGrid;

	@FXML
	private Group plantGroup;

	@FXML
	private MenuItem quitButton;
	
	@FXML
	private MenuItem aboutButton;
	
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
		
		setupMenuButtons();
		setupPlantSelectionButtons();
		setupNextTurnButton();
		initGameGrid();
		nextLevelButton.setDisable(true); // we only have one Level in this version, so disable button
	}

	/**
	 * This empty constructor is required for proper loading of the JavaFX GUI
	 * controller
	 */
	public GUIController() { }

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
				selectedPlant = new PeaShooter();
			}
		});

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
							game.getActionProcessor().processPlaceActor(selectedPlant, column, row);
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
		peashooterCooldown.setText(Integer.toString(CooldownManager.getCurrentPeaCD()));
		sunflowerCooldown.setText(Integer.toString(CooldownManager.getCurrentSunCD()));
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
		waveLabel.setText("  Wave: " + Integer.toString(game.getActionProcessor().getWave().getNum()));
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
		updateWaveNumber();
		updateSunpointLabel();
		updateCooldownDisplay();
	}

	public void notifyGameOver() {
		updateGameGrid();
		nextTurnButton.setDisable(true);
		peashooterButton.setDisable(true);
		sunflowerButton.setDisable(true);
		gameGrid.setDisable(true);
		plantGroup.setDisable(true);
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
}
