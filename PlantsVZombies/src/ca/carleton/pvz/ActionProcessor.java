package ca.carleton.pvz;

import java.awt.Point;
import java.util.Random;

import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.CooldownManager;
import ca.carleton.pvz.actor.NormalPeaShooter;
import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.actor.PeaShooter;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
import ca.carleton.pvz.level.Wave;
import javafx.scene.control.Alert.AlertType;

/**
 * Updates the model as dictated by GUIController.
 *
 */
public class ActionProcessor {

	private boolean waveDefeated;

	private Wave wave;
	private PlantsVZombies game;

	private Random r;

	public ActionProcessor(PlantsVZombies game) {
		this.game = game;
		wave = new Wave(1, 3, 0, 0);
		waveDefeated = false;
		r = new Random();
	}

	public void processNextTurn() {

		Level lvl = game.getWorld().getCurrentLevel();

		// increment turn by one
		lvl.incTurn();

		// decrement global cooldowns by one
		CooldownManager.decTimeOnCD();

		// passively boost sunpoints every other turn
		// based on the number of sunflowers on the map
		if (lvl.getTurn() % 2 == 0) {
			lvl.addToSunpoints(lvl.getNumSunflowers() * Sunflower.PASSIVE_SUNPOINT_BOOST);
		}

		// actuate shooting by all shooting plants in the given level
		shootZombies(lvl);

		// actuate movement of all zombies in the given level
		moveZombies(lvl);

		// --------------------------------------------------------------------------------------
		// TODO : Refactor code below here ...
		// --------------------------------------------------------------------------------------

		if (wave.getNum() == 1 && lvl.getTurn() >= 3 && wave.getTotalNumZombies() > 0) { // zombies
			// spawn
			// after
			// lvl.getTurn()
			// ==
			// 3
			// for
			// first
			// wave

			game.getWorld().updateCurrentLevel(spawnZombie(lvl));
			wave.setNumZombies(NormalZombie.class, wave.getTotalNumZombies() - 1);
		}

		if (wave.getNum() == 2 && lvl.getTurn() >= 3 && wave.getTotalNumZombies() > 0) {
			game.getWorld().updateCurrentLevel(spawnZombie(lvl));
			wave.setNumZombies(NormalZombie.class, wave.getTotalNumZombies() - 1);
		}

		if (wave.getNum() == 3 && lvl.getTurn() >= 3 && wave.getTotalNumZombies() > 0) {

			game.getWorld().updateCurrentLevel(spawnZombie(lvl));
			wave.setNumZombies(NormalZombie.class, wave.getTotalNumZombies() - 1);
		}

		isGameOver();

		if (isGameOver()) {
			game.setGameOver();
			return;
		}

		if ((wave.getNum() == 1 && lvl.getTurn() >= 6) || (wave.getNum() == 2 && lvl.getTurn() >= 8)
				|| (wave.getNum() == 3 && lvl.getTurn() >= 10)) {
			waveDefeated = true;
			for (int i = 0; i < lvl.getNumRows(); ++i) {
				for (int j = 0; j < lvl.getNumCols(); ++j) {
					Actor o = lvl.getCell(i, j);
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
			wave.setNumZombies(NormalZombie.class, 5);
			wave.setWaveNum(2);
			return;
		}

		if (wave.getNum() == 2 && waveDefeated) {
			waveDefeated = false;
			wave.setNumZombies(NormalZombie.class, 7);
			wave.setWaveNum(3);
			return;
		}

		if (wave.getNum() >= 3 && waveDefeated) {
			game.getController().showAlert("You won!", null, "Congrats, you beat the first level of Plants VS Zombies!",
					AlertType.INFORMATION);
		}
	}

	/**
	 * Moves all zombies in the given level to the left by the appropriate
	 * number of cells; i.e., based on zombie speed, which varies across Zombie
	 * subtypes.
	 */
	private void moveZombies(Level lvl) {

		// traverse col-by-col starting top-left as (0, 0)
		for (int x = 0; x < lvl.getNumCols(); ++x) {
			for (int y = 0; y < lvl.getNumRows(); ++y) {
				Actor a = lvl.getCell(x, y);
				if (a instanceof Zombie) {
					Zombie z = (Zombie) a;
					int xNew = x - z.getSpeed();
					// failsafe: if xNew < 0, place zombie at (0, y)
					if (xNew < 0) {
						xNew = 0;
					}
					// constraint: one zombie per cell;
					// slower zombie gets pushed back one cell
					if (lvl.getCell(xNew, y) instanceof Zombie) {
						lvl.placeActor(lvl.getCell(xNew, y), new Point(xNew + 1, y));
					}
					lvl.placeActor(z, new Point(xNew, y));
					// ensure all non-zombie cells in zombie's path are
					// nullified
					for (int xAux = xNew + 1; xAux <= x; ++xAux) {
						if (!(lvl.getCell(xAux, y) instanceof Zombie))
							lvl.placeActor(null, new Point(xAux, y));
					}
				}
			}
		}

	}

	/**
	 * Actuates shooting by all shooting plants in the given level.
	 *
	 */
	private void shootZombies(Level lvl) {

		// traverse col-by-col starting top-left as (0, 0)
		for (int x1 = 0; x1 < lvl.getNumCols(); ++x1) {
			for (int y = 0; y < lvl.getNumRows(); ++y) {
				Actor a1 = lvl.getCell(x1, y);
				if (a1 instanceof PeaShooter) {
					PeaShooter pS = (PeaShooter) a1;
					// look for zombie to right of pea shooter
					for (int x2 = x1; x2 < lvl.getNumCols(); ++x2) {
						Actor a2 = lvl.getCell(x2, y);
						if (a2 instanceof Zombie) {
							Zombie z = (Zombie) a2;
							z.setHealth(z.getHealth() - pS.getPeaDamage());
							if (z.getHealth() <= 0) {
								lvl.placeActor(null, new Point(x2, y));
							}
							break; // so that trailing zombies (in same row)
									// don't also get hit
						}
					}
				}
			}
		}

	}

	/**
	 * Scans the leftmost column for any zombies. If there is a zombie in the
	 * leftmost column, it is game over.
	 *
	 * @return true if there is a zombie in the leftmost column, false
	 *         otherwise.
	 */
	public boolean isGameOver() {
		Level lvl = game.getWorld().getCurrentLevel();
		for (int y = 0; y < lvl.getNumRows(); ++y) {
			Actor a = lvl.getCell(0, y);
			if (a instanceof Zombie) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Spawns a zombie from the head wave on the given level. The type of zombie
	 * spawned is chosen at random.
	 *
	 * @param lvl The level on which to spawn a zombie.
	 * @return The level on which a zombie was spawned.
	 */
	private Level spawnZombie(Level lvl) {
		int randomRow = r.nextInt(lvl.getNumRows());
		Zombie zombie = new NormalZombie();
		lvl.placeActor(zombie, new Point(lvl.getNumCols() - 1, randomRow));
		return lvl;
	}

	/**
	 * Deploys the wave at the head of the waves queue.
	 */
	public void deployHeadWave(Level lvl) {
		Wave wave = lvl.getHeadWave();
		// TODO : implement ...
	}

	/**
	 * Places the given Actor object at the given coordinates.
	 *
	 * @param actor The Actor object to place.
	 * @param xPos The x-coordinate at which to place the given actor.
	 * @param yPos The y-coordinate at which to place the given actor.
	 */
	public void processPlaceActor(Level lvl, Actor actor, int xPos, int yPos) {

		if (lvl.getCell(xPos, yPos) == null) {

			if (actor instanceof NormalPeaShooter) {

				if (CooldownManager.isNormalPeaOnCD()) {

				} else if (lvl.getSunpoints() - 100 < 0) {
				} else {
					lvl.placeActor(new NormalPeaShooter(), new Point(xPos, yPos));
					lvl.subtractFromSunpoints(100);
					CooldownManager.startNormalPeaCD();

				}
			} else if (actor instanceof Sunflower) {

				if (CooldownManager.isSunOnCD()) {

				} else if (lvl.getSunpoints() - 50 < 0) {
					// game.print(Presets.NOT_ENOUGH_SUNPOINTS + plantType +
					// "\n");

				} else {
					Sunflower plantToPlace = new Sunflower();
					plantToPlace.setTurnPlaced(lvl.getTurn());
					lvl.placeActor(plantToPlace, new Point(xPos, yPos));
					lvl.subtractFromSunpoints(50);
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
