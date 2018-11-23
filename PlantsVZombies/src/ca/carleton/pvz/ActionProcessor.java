package ca.carleton.pvz;

import java.awt.Point;

import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.CooldownManager;
import ca.carleton.pvz.actor.NormalPeaShooter;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
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
		wave = new Wave(1, 3, 0, 0);
		waveDefeated = false;
	}

	public boolean isGameOver() {
		if (turn > 6) { // searching for any zombies that made it to end game
			for (int j = 0; j < game.getWorld().getCurrentLevel().getNumCols(); ++j) {
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

		// passive sun point logic -- every three turns, increase sun points by
		// 25
		if (turn - previousTurn == 3) {
			previousTurn = turn;
			game.getWorld().getCurrentLevel().addToSunpoints(25);
		}

		if (wave.getNum() >= 1) {
			for (int i = 0; i < game.getWorld().getCurrentLevel().getNumRows(); ++i) {
				for (int j = 0; j < game.getWorld().getCurrentLevel().getNumCols(); ++j) {
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
			NormalPeaShooter.shootZombies(game.getWorld().getCurrentLevel());
			moveZombies();
		}

		if (wave.getNum() == 1 && turn >= 3 && wave.getTotalNumZombies() > 0) { // zombies
																				// spawn
																				// after
																				// turn
																				// ==
																				// 3
																				// for
																				// first
																				// wave

			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getTotalNumZombies() - 1);
		}

		if (wave.getNum() == 2 && turn >= 3 && wave.getTotalNumZombies() > 0) {
			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getTotalNumZombies() - 1);
		}

		if (wave.getNum() == 3 && turn >= 3 && wave.getTotalNumZombies() > 0) {

			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getTotalNumZombies() - 1);
		}

		isGameOver();

		if (isGameOver()) {
			game.setGameOver();
			return;
		}

		if ((wave.getNum() == 1 && turn >= 6) || (wave.getNum() == 2 && turn >= 8)
				|| (wave.getNum() == 3 && turn >= 10)) {
			waveDefeated = true;
			for (int i = 0; i < game.getWorld().getCurrentLevel().getNumRows(); ++i) {
				for (int j = 0; j < game.getWorld().getCurrentLevel().getNumCols(); ++j) {
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
			wave.setWaveNum(2);
			return;
		}

		if (wave.getNum() == 2 && waveDefeated) {
			waveDefeated = false;
			wave.setRemainingZombies(7);
			turn = 0;
			wave.setWaveNum(3);
			return;
		}

		if (wave.getNum() >= 3 && waveDefeated) {
			game.getController().showAlert("You won!", null, "Congrats, you beat the first level of Plants VS Zombies!",
					AlertType.INFORMATION);
		}
	}

	/**
	 * Moves all zombies on the board to the left by the appropriate number of
	 * tiles; i.e., based on zombie speed, which varies across Zombie subtypes.
	 */
	private void moveZombies() {

		Level lvl = game.getWorld().getCurrentLevel();
		int numRows = lvl.getNumRows();
		int numCols = lvl.getNumCols();

		for (int x = 0; x < numRows; ++x) {
			for (int y = 0; y < numCols; ++y) {
				Actor a = lvl.getCell(x, y);
				if (a instanceof Zombie) {
					Zombie z = (Zombie) a;
					lvl.placeActor(z, new Point(x - z.getSpeed(), y));
					lvl.placeActor(null, new Point(x, y));
				}
			}
		}

	}

	public void processPlaceActor(Actor actor, int xPos, int yPos) {

		if (game.getWorld().getCurrentLevel().getCell(xPos, yPos) == null) {

			if (actor instanceof NormalPeaShooter) {

				if (CooldownManager.isNormalPeaOnCD()) {

				} else if (game.getWorld().getCurrentLevel().getSunpoints() - 100 < 0) {
				} else {
					game.getWorld().getCurrentLevel().placeActor(new NormalPeaShooter(), new Point(xPos, yPos));
					game.getWorld().getCurrentLevel().subtractFromSunpoints(100);
					CooldownManager.startNormalPeaCD();

				}
			} else if (actor instanceof Sunflower) {

				if (CooldownManager.isSunOnCD()) {

				} else if (game.getWorld().getCurrentLevel().getSunpoints() - 50 < 0) {
					// game.print(Presets.NOT_ENOUGH_SUNPOINTS + plantType +
					// "\n");

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
		// TODO When ActionProcessor is properly integrated to use Level's wave
		// objects,
		// this won't be needed
		return wave;
	}
}
