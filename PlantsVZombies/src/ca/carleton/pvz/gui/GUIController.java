package ca.carleton.pvz.gui;

import java.awt.Point;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.PeaShooter;
import ca.carleton.pvz.actor.Plant;
import ca.carleton.pvz.actor.Sunflower;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class controls the JavaFX fxml user interface.
 *
 * @author Group 5
 *
 */
public class GUIController {
	private PlantsVZombies game;
	private Plant selectedPlant;

	private int sunflowerCooldownInt = 0;
	private int peashooterCooldownInt = 0;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	// @FXML
	// private Label sunpoints;
	
	@FXML
	private Label peashooterCooldown;

	@FXML
	private Button nextTurnButton;

	@FXML
	private Button sunflowerButton;

	@FXML
	private Button peashooterButton;

	@FXML
	private Label sunflowerCooldown;

	@FXML
	private GridPane gameGrid;

	@FXML
	private Group plantGroup;

	@FXML
	public void initialize() {
		assert peashooterCooldown != null : "fx:id=\"peashooterCooldown\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert nextTurnButton != null : "fx:id=\"nextTurnButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert sunflowerCooldown != null : "fx:id=\"sunflowerCooldown\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert peashooterButton != null : "fx:id=\"peashooterButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert sunflowerButton != null : "fx:id=\"sunflowerButton\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert gameGrid != null : "fx:id=\"gameGrid\" was not injected: check your FXML file 'pvzgui.fxml'.";
		assert plantGroup != null : "fx:id=\"gameGrid\" was not injected: check your FXML file 'pvzgui.fxml'.";
		setupPlantSelectionButtons();
		initGameGrid();
		nextTurnButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (game.getActionProcessor().isGameOver()) {

					updateGameGrid();
					nextTurnButton.setDisable(true);
					peashooterButton.setDisable(true);
					sunflowerButton.setDisable(true);
					gameGrid.setDisable(true);
					plantGroup.setDisable(true);
					Label label = new Label("Game over! You failed to protect your home from the zombies :(");

					StackPane secondaryLayout = new StackPane();
					secondaryLayout.getChildren().add(label);

					Scene secondScene = new Scene(secondaryLayout, 400, 100);

					// New window (Stage)
					Stage newWindow = new Stage();
					newWindow.setTitle("Game Over");
					newWindow.setScene(secondScene);

					// Specifies the modality for new window.
					newWindow.initModality(Modality.WINDOW_MODAL);

					newWindow.show();

				}

				if (sunflowerCooldownInt > 0) {
					sunflowerCooldownInt = sunflowerCooldownInt - 1;
					sunflowerCooldown.setText(Integer.toString(sunflowerCooldownInt));
				}
				if (peashooterCooldownInt > 0) {
					peashooterCooldownInt = peashooterCooldownInt - 1;
					peashooterCooldown.setText(Integer.toString(sunflowerCooldownInt));
				}
				
				//Updating Sunpoints
				//sunpoints.setText(Integer.toString(game.getActionProcessor().getSunpoints()));
				game.getActionProcessor().processNextTurn();
				updateGameGrid();

			}
		});

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
				selectedPlant = new PeaShooter();
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
							if (game.getWorld().getCurrentLevel().getCell(column, row) == null) {
								if (selectedPlant instanceof PeaShooter) {
									if (peashooterCooldownInt == 0
											&& (game.getActionProcessor().getSunpoints() >= 100)) {
										System.out.println(peashooterCooldownInt);
										game.getWorld().getCurrentLevel().placeActor(selectedPlant,
												new Point(column, row));
										peashooterCooldownInt = 2;
										game.getActionProcessor().setSunpoints(-100);
										peashooterCooldown.setText(Integer.toString(peashooterCooldownInt));
										//Updating Sunpoints
										//sunpoints.setText(Integer.toString(game.getActionProcessor().getSunpoints()));

									}
								}

								if (selectedPlant instanceof Sunflower
										&& (game.getActionProcessor().getSunpoints() >= 50)) {
									if (sunflowerCooldownInt == 0) {
										game.getWorld().getCurrentLevel().placeActor(selectedPlant,
												new Point(column, row));
										sunflowerCooldownInt = 2;
										game.getActionProcessor().setSunpoints(-50);
										sunflowerCooldown.setText(Integer.toString(sunflowerCooldownInt));
										//Updating Sunpoints
										//sunpoints.setText(Integer.toString(game.getActionProcessor().getSunpoints()));
									}
								}

							} else {

								Label label = new Label("There's already something placed here!");

								StackPane secondaryLayout = new StackPane();
								secondaryLayout.getChildren().add(label);

								Scene secondScene = new Scene(secondaryLayout, 230, 100);

								// New window (Stage)
								Stage newWindow = new Stage();
								newWindow.setTitle("Error");
								newWindow.setScene(secondScene);

								// Specifies the modality for new window.
								newWindow.initModality(Modality.WINDOW_MODAL);

								newWindow.show();

							}
						}
						updateGameGrid();
						event.consume();

					}

				});
			}
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
