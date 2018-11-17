package ca.carleton.pvz;

import java.awt.Point;

import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.CooldownManager;
import ca.carleton.pvz.actor.PeaShooter;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.command.Presets;
import ca.carleton.pvz.level.Wave;
import javafx.scene.control.Alert.AlertType;

public class ActionProcessor {
	private int previousTurn;
	private boolean waveDefeated;
	public int turn;

	private Wave wave;
	private PlantsVZombies game;

	public ActionProcessor(PlantsVZombies game) {
		this.game = game;
		previousTurn = 0;
		turn = 0;
		wave = new Wave(1, 3);
		waveDefeated = false;
	}

	public boolean isGameOver() {
		if (turn > 6) { // searching for any zombies that made it to end game
			for (int j = 0; j < game.getWorld().getCurrentLevel().getDimension().width; ++j) {
				Actor o = game.getWorld().getCurrentLevel().getCell(0, j);
				if (o instanceof Zombie) {
					return true;
				}
			}

		}
		return false;
	}

	public void processNextTurn() {
		++turn;

		CooldownManager.decTimeOnCD();

		// wave logic
		if (wave.getNum() == 1 && waveDefeated) {

			return;
		}

		if (wave.getNum() == 2 && waveDefeated) {

			return;
		}

		if (wave.getNum() >= 3 && waveDefeated) {

			return;
		}

		// passive sun point logic -- every three turns, increase sun points by 25
		if (turn - previousTurn == 3) {
			previousTurn = turn;
			game.getWorld().getCurrentLevel().addToSunpoints(25);
		}

		if (wave.getNum() >= 1) {
			for (int i = 0; i < game.getWorld().getCurrentLevel().getDimension().height; ++i) {
				for (int j = 0; j < game.getWorld().getCurrentLevel().getDimension().width; ++j) {
					Actor o = game.getWorld().getCurrentLevel().getCell(i, j);
					if (o instanceof Sunflower) {
						if ((turn - ((Sunflower) o).getTurnPlaced()) % 2 == 0) {
							game.getWorld().getCurrentLevel().addToSunpoints(25);
							;
						}
					}
				}
			}
		}

		if (turn > 3) {
			PeaShooter.shootZombies(game.getWorld().getCurrentLevel());
			Zombie.moveZombies(game.getWorld().getCurrentLevel()); // shifting already-placed zombies one to the left
																	// each turn
		}

		if (wave.getNum() == 1 && turn >= 3 && wave.getRemainingZombies() > 0) { // zombies spawn after turn
																					// == 3 for first wave

			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getRemainingZombies() - 1);
		}

		if (wave.getNum() == 2 && turn >= 3 && wave.getRemainingZombies() > 0) {
			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getRemainingZombies() - 1);
		}

		if (wave.getNum() == 3 && turn >= 3 && wave.getRemainingZombies() > 0) {

			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getRemainingZombies() - 1);
		}

		isGameOver();

		if (isGameOver()) {
			game.setGameOver();
			return;
		}

		if ((wave.getNum() == 1 && turn >= 6) || (wave.getNum() == 2 && turn >= 8)
				|| (wave.getNum() == 3 && turn >= 10)) {
			waveDefeated = true;
			for (int i = 0; i < game.getWorld().getCurrentLevel().getDimension().height; ++i) {
				for (int j = 0; j < game.getWorld().getCurrentLevel().getDimension().width; ++j) {
					Actor o = game.getWorld().getCurrentLevel().getCell(i, j);
					if (o instanceof Zombie) {
						waveDefeated = false;
					}
				}
			}
		}



		if (game.isGameOver()) {
			game.setGameOver();
			return;
		}

		if (wave.getNum() == 1 && waveDefeated) {
			waveDefeated = false;
			wave.setRemainingZombies(5);
			turn = 0;
			wave.setWaveNumber(2);
			return;
		}

		if (wave.getNum() == 2 && waveDefeated) {
			waveDefeated = false;
			wave.setRemainingZombies(7);
			turn = 0;
			wave.setWaveNumber(3);
			return;
		}

		if (wave.getNum() >= 3 && waveDefeated) {
			game.getController().showAlert("You won!", null, "Congrats, you beat the first level of Plants VS Zombies!",
					AlertType.INFORMATION);
		}
	}

	public void processPlaceActor(Actor actor, int xPos, int yPos) {
		String plantType;
		if (game.getWorld().getCurrentLevel().getCell(xPos, yPos) == null) {

			if (actor instanceof PeaShooter) {
				plantType = "peashooter";
				if (CooldownManager.isPeaOnCD()) {

				} else if (game.getWorld().getCurrentLevel().getSunpoints() - 100 < 0) {
				} else {
					game.getWorld().getCurrentLevel().placeActor(new PeaShooter(), new Point(xPos, yPos));
					game.getWorld().getCurrentLevel().subtractFromSunpoints(100);
					CooldownManager.startPeaCD();

				}
			} else if (actor instanceof Sunflower) {
				plantType = "sunflower";
				if (CooldownManager.isSunOnCD()) {

				} else if (game.getWorld().getCurrentLevel().getSunpoints() - 50 < 0) {
					//game.print(Presets.NOT_ENOUGH_SUNPOINTS + plantType + "\n");

				} else {
					Sunflower plantToPlace = new Sunflower();
					plantToPlace.setTurnPlaced(turn);
					game.getWorld().getCurrentLevel().placeActor(plantToPlace, new Point(xPos, yPos));
					game.getWorld().getCurrentLevel().subtractFromSunpoints(50);
					CooldownManager.startSunCD();
				}
			}
		} else {
			// grid position already in use, show alert to user
			game.getController().showAlert("No room!", null, "There's already something placed here!",
					AlertType.INFORMATION);
		}
	}

	/**
	 * Temporary way to get wave from action processor
	 * 
	 * @return Returns action processor wave
	 */
	public Wave getWave() {
		// TODO When ActionProcessor is properly integrated to use Level's wave objects,
		// this won't be needed
		return wave;
	}
}
