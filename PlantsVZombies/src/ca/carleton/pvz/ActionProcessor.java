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
			game.print(Presets.WAVE_COMPLETE);
			return;
		}

		if (wave.getNum() == 2 && waveDefeated) {
			game.print(Presets.WAVE_COMPLETE);
			return;
		}

		if (wave.getNum() >= 3 && waveDefeated) {
			game.print("Congrats! You finished the first level of Plants vs. Zombies");
			game.print("Please type \"restart\" if you wish to play again");
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
							game.getWorld().getCurrentLevel().addToSunpoints(25);;
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
			game.print(Presets.ZOMBIES_SPAWNING);
			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getRemainingZombies() - 1);
		}

		if (wave.getNum() == 2 && turn >= 3 && wave.getRemainingZombies() > 0) {
			game.print(Presets.ZOMBIES_SPAWNING);
			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getRemainingZombies() - 1);
		}

		if (wave.getNum() == 3 && turn >= 3 && wave.getRemainingZombies() > 0) {
			game.print(Presets.ZOMBIES_SPAWNING);
			game.getWorld().updateCurrentLevel(Wave.spawnZombieOnLevel(game.getWorld().getCurrentLevel()));
			wave.setRemainingZombies(wave.getRemainingZombies() - 1);
		}

		isGameOver();

		if (isGameOver()) {
			game.printGame();
			game.print(Presets.GAME_OVER);
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

		game.printGame();

		if (game.isGameOver()) {
			game.printGame();
			game.print(Presets.GAME_OVER);
			game.setGameOver();
			return;
		}

		if (wave.getNum() == 1 && waveDefeated) {
			waveDefeated = false;
			wave.setRemainingZombies(5);
			turn = 0;
			wave.setWaveNumber(2);
			game.print("Wave 2 will arrive shortly.");
			return;
		}

		if (wave.getNum() == 2 && waveDefeated) {
			waveDefeated = false;
			wave.setRemainingZombies(7);
			turn = 0;
			wave.setWaveNumber(3);
			game.print("Wave 3 will arrive shortly.");
			return;
		}

		if (wave.getNum() >= 3 && waveDefeated) {
			game.print("Congrats! You finished the first level of Plants vs. Zombies.");
			game.print("Please type 'restart' if you wish to play again.");
		}
	}

	public void processPlaceActor(Actor actor, int xPos, int yPos) {
		String plantType;
		if (game.getWorld().getCurrentLevel().getCell(xPos, yPos) == null) {
			
			if (actor instanceof PeaShooter) {
				plantType = "peashooter";
				if (CooldownManager.isPeaOnCD()) {
					game.print("\n" + plantType + Presets.PLANTTYPE_COOLDOWN); 
					
				} else if (game.getWorld().getCurrentLevel().getSunpoints() - 100 < 0) {
					game.print(Presets.NOT_ENOUGH_SUNPOINTS + plantType + "\n");
				} else {
					game.getWorld().getCurrentLevel().placeActor(new PeaShooter(), new Point(xPos, yPos));
					game.getWorld().getCurrentLevel().subtractFromSunpoints(100);
					CooldownManager.startPeaCD();
					game.printGame();
	
				}
			} else if (actor instanceof Sunflower) {
				plantType = "sunflower";
				if (CooldownManager.isSunOnCD()) {
					game.print("\n" + plantType + Presets.PLANTTYPE_COOLDOWN); 
	
				} else if (game.getWorld().getCurrentLevel().getSunpoints() - 50 < 0) {
					game.print(Presets.NOT_ENOUGH_SUNPOINTS + plantType + "\n");
	
				} else {
					Sunflower plantToPlace = new Sunflower();
					plantToPlace.setTurnPlaced(turn);
					game.getWorld().getCurrentLevel().placeActor(plantToPlace, new Point(xPos, yPos));
					game.getWorld().getCurrentLevel().subtractFromSunpoints(50);
					CooldownManager.startSunCD();
					game.printGame();
				}
			}
		} else {
			// grid position already in use, show alert to user
			game.getController().showAlert("No room!", null, "There's already something placed here!",
					AlertType.INFORMATION);
		}
	}
}
