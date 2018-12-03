package ca.carleton.pvz.gui;

import java.util.Optional;

import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.PlantsVZombies;
import ca.carleton.pvz.level.CustomLevel;
import ca.carleton.pvz.level.Level.Terrain;
import ca.carleton.pvz.level.Wave;
import ca.carleton.pvz.level.Wave.Difficulty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class LevelBuilder extends Stage {
	private ObservableList<Wave> waves;
	
	public LevelBuilder(Stage owner) {
		initModality(Modality.APPLICATION_MODAL);
		initOwner(owner);
		Pane rootPane = new VBox();
		Pane levelNamePane = new HBox();
		Pane levelNumPane = new HBox();
		Pane sunPane = new HBox();
		Pane terrainPane = new HBox();
		
		ListView<Wave> list = new ListView<Wave>();
		waves = FXCollections.observableArrayList();
		list.setItems(waves);

		// setup menu bar
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Waves");
		MenuItem addWave = new MenuItem("Add Wave");
		MenuItem saveLevel = new MenuItem("Save Level");
		addWave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				WaveDialog dialog = new WaveDialog(waves.size() + 1);
				Optional<Wave> result = dialog.showAndWait();

				if (result.isPresent()) {

					waves.add(result.get());
				}
			}

		});
		
		menuFile.getItems().add(saveLevel);
		menuEdit.getItems().add(addWave);
		menuBar.getMenus().addAll(menuFile, menuEdit);
		
		Label sunPoint = new Label("Starting sunpoints: ");
		TextField sunPointField = new TextField();
		sunPointField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	sunPointField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		sunPane.getChildren().addAll(sunPoint, sunPointField);
		
		Label terrainLabel = new Label("Terrain Type: ");
		Spinner<Terrain> terrainPicker = new Spinner<Terrain>(FXCollections.observableArrayList(Terrain.values()));
		terrainPane.getChildren().addAll(terrainLabel, terrainPicker);
		
		Label numLabel = new Label("Level Number: ");
		Spinner<Integer> numPicker = new Spinner<Integer>(0, 50, 0);
		levelNumPane.getChildren().addAll(numLabel, numPicker);
		
		Label nameLabel = new Label("Level name: ");
		TextField nameField = new TextField();
		levelNamePane.getChildren().addAll(nameLabel, nameField);
		
		rootPane.getChildren().addAll(menuBar, levelNumPane, levelNamePane, sunPane, terrainPane, list);
		
		saveLevel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(!nameField.getText().equals("") && !sunPointField.getText().equals("")) {
					Level level = new CustomLevel(numPicker.getValue(), Integer.parseInt(sunPointField.getText()), terrainPicker.getValue(), waves);
					PlantsVZombies.saveObject(level, nameField.getText() + ".level");
				} else {
					GUIController.showAlert("Missing parameters!", null, "Please specify level name and number of sunpoints.", AlertType.ERROR);
				}
			}

		});
		
		Scene scene = new Scene(rootPane, 500, 500);
		setTitle("Level Builder");
		setScene(scene);
		setResizable(false);
		show();
	}
}

class WaveDialog extends Dialog<Wave> {
	public WaveDialog(int waveNum) {
		setTitle("Add Wave");
		setHeaderText("Add Wave");
		setResizable(false);
		GridPane grid = new GridPane();
		Label difficultyLabel = new Label("Difficulty: ");
		Label numNormalLabel = new Label("Normal Zombies: ");
		Label numShieldLabel = new Label("Shield Zombies: ");
		Label numFastLabel = new Label("Fast Zombies: ");
		Label numWizardLabel = new Label("Wizard Zombies: ");
		Label numFootballLabel = new Label("Football Zombies: ");
		Label numGigaLabel = new Label("Giga Zombies: ");
		Label numBossLabel = new Label("Boss Zombies: ");

		grid.add(difficultyLabel, 1, 1);
		grid.add(numNormalLabel, 1, 2);
		grid.add(numShieldLabel, 1, 3);
		grid.add(numFastLabel, 1, 4);
		grid.add(numWizardLabel, 1, 5);
		grid.add(numFootballLabel, 1, 6);
		grid.add(numGigaLabel, 1, 7);
		grid.add(numBossLabel, 1, 8);

		Spinner<Difficulty> difficultySpinner = new Spinner<Difficulty>(FXCollections.observableArrayList(Difficulty.values()));
		Spinner<Integer> numNormalSpinner = new Spinner<Integer>(0, 50, 0);
		Spinner<Integer> numShieldSpinner = new Spinner<Integer>(0, 50, 0);
		Spinner<Integer> numFastSpinner = new Spinner<Integer>(0, 50, 0);
		Spinner<Integer> numWizardSpinner = new Spinner<Integer>(0, 50, 0);
		Spinner<Integer> numFootballSpinner = new Spinner<Integer>(0, 50, 0);
		Spinner<Integer> numGigaSpinner = new Spinner<Integer>(0, 50, 0);
		Spinner<Integer> numBossSpinner = new Spinner<Integer>(0, 50, 0);

		grid.add(difficultySpinner, 2, 1);
		grid.add(numNormalSpinner, 2, 2);
		grid.add(numShieldSpinner, 2, 3);
		grid.add(numFastSpinner, 2, 4);
		grid.add(numWizardSpinner, 2, 5);
		grid.add(numFootballSpinner, 2, 6);
		grid.add(numGigaSpinner, 2, 7);
		grid.add(numBossSpinner, 2, 8);

		getDialogPane().setContent(grid);

		ButtonType buttonTypeOk = new ButtonType("Done", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().add(buttonTypeOk);

		setResultConverter(new Callback<ButtonType, Wave>() {
			@Override
			public Wave call(ButtonType b) {

				if (b == buttonTypeOk) {

					return new Wave(waveNum, difficultySpinner.getValue(), numNormalSpinner.getValue(),
							numShieldSpinner.getValue(), numFastSpinner.getValue(), numWizardSpinner.getValue(),
							numFootballSpinner.getValue(), numGigaSpinner.getValue(), numBossSpinner.getValue());
				}

				return null;
			}
		});
	}
}