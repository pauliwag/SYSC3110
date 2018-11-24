package ca.carleton.pvz;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Predicate;

import ca.carleton.pvz.actor.Actor;
import ca.carleton.pvz.actor.CooldownManager;
import ca.carleton.pvz.actor.GatlingPeaShooter;
import ca.carleton.pvz.actor.NormalPeaShooter;
import ca.carleton.pvz.actor.PeaShooter;
import ca.carleton.pvz.actor.Plant;
import ca.carleton.pvz.actor.Sunflower;
import ca.carleton.pvz.actor.Zombie;
import ca.carleton.pvz.level.Level;
import javafx.scene.control.Alert.AlertType;

/**
 * Updates the given game's model as dictated by GUIController.
 *
 */
public class ActionProcessor {

	private PlantsVZombies game;
	private Random r;

	/**
	 * Constructs an action processor.
	 *
	 * @param game The game whose model will be updated by this action
	 *            processor.
	 */
	public ActionProcessor(PlantsVZombies game) {
		this.game = game;
		r = new Random();
	}

	/**
	 * Called by GUIController to update game's model upon clicking the "Next
	 * Turn" button.
	 */
	public void processNextTurn() {
		if (game.getController().logMoves()) {
			game.addToUndoStack(game.getWorld());
		}

		Level lvl = game.getWorld().getCurrentLevel();

		// increment turn by one
		lvl.incTurn();

		// decrement global cooldowns by one
		CooldownManager.decTimeOnCD();

		// boost sunpoints every turn based on
		// the number of sunflowers on the map
		lvl.addToSunpoints(lvl.getNumSunflowers() * Sunflower.SUNFLOWER_SUNPOINT_BOOST);

		// passively boost sunpoints every other turn
		if (lvl.getTurn() % 2 == 0)
			lvl.addToSunpoints(Level.PASSIVE_SUNPOINT_BOOST);

		// actuate shooting by all shooting plants in the given level
		shootZombies(lvl);

		// actuate movement of all zombies in the given level
		moveZombies(lvl);

		// check if there are any zombies in the leftmost column
		if (isGameOver()) {
			game.setGameOver();
			return;
		}

		// normal difficulty: spawn a zombie every one or two turns
		if (!lvl.zombieSpawnedLastTurn()) {
			spawnZombie(lvl);
			lvl.zombieSpawned();
		} else if (r.nextBoolean()) {
			spawnZombie(lvl);
		} else {
			lvl.zombieNotSpawned();
		}

		// augment ramped difficulty
		if (lvl.getHeadWave().isRamped()) {
			spawnZombie(lvl);
			if (lvl.getHeadWave().isSuperRamped()) {
				spawnZombie(lvl);
			}
		}

		// level is beat if queued waves are empty and
		// there are no zombies on the map
		if (lvl.isBeat()) {
			game.getController().showAlert("You won!", null,
					"Congrats! You beat " + lvl.getLevelName() + " of Plants vs. Zombies!", AlertType.INFORMATION);
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
					// nullify occupied cell
					lvl.placeActor(null, new Point(x, y));
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
	private void spawnZombie(Level lvl) {

		if (!lvl.isHeadWaveEmpty()) { // failsafe: prevent IAE

			// get head wave's hash table of zombies
			HashMap<Class<? extends Zombie>, Integer> zombies = lvl.getHeadWave().getZombies();

			// create an array list of the keys
			ArrayList<Class<? extends Zombie>> keysAsArray = new ArrayList<>(zombies.keySet());

			// remove keys from array list with hash value of 0
			keysAsArray.removeIf((Predicate<Class<? extends Zombie>> & Serializable) z -> zombies.get(z) == 0);

			// randomly select a zombie type
			Class<? extends Zombie> zombieTypeToSpawn = keysAsArray.get(r.nextInt(keysAsArray.size()));

			// try placing a zombie of the randomly selected type
			// in the rightmost column and in a random row
			int rightmostCol = lvl.getNumCols() - 1;
			int randomRow = r.nextInt(lvl.getNumRows());
			while (lvl.getCell(rightmostCol, randomRow) instanceof Zombie) {
				randomRow = r.nextInt(lvl.getNumRows());
			}
			try {
				lvl.placeActor(zombieTypeToSpawn.newInstance(), new Point(rightmostCol, randomRow));
			} catch (InstantiationException | IllegalAccessException e) {
				return;
			}

			// decrement the hash value of the placed zombie type by one
			zombies.replace(zombieTypeToSpawn, zombies.get(zombieTypeToSpawn) - 1);

		}

		// if the head wave is void of zombies, dequeue it
		if (lvl.isHeadWaveEmpty() && lvl.getNumWaves() > 1) {
			lvl.dequeueHeadWave();
		}

	}

	/**
	 * Processes planting the given plant at the given coordinates.
	 *
	 * @param lvl The level on which to plant the given plant.
	 * @param plant The plant that will be planted.
	 * @param xPos The x-coordinate at which to plant the given plant.
	 * @param yPos The y-coordinate at which to plant the given plant.
	 */
	public void processPlanting(Level lvl, Plant plant, int xPos, int yPos) {
		if (game.getController().logMoves()) {
			game.addToUndoStack(game.getWorld());
		}

		if (lvl.getCell(xPos, yPos) == null) {

			if (plant.getClass() == Sunflower.class) {
				if (!CooldownManager.isSunOnCD() && lvl.getSunpoints() - Sunflower.SUNFLOWER_COST >= 0) {
					lvl.placeActor(new Sunflower(), new Point(xPos, yPos));
					lvl.subtractFromSunpoints(Sunflower.SUNFLOWER_COST);
					CooldownManager.startSunCD();
				}

			} else if (plant.getClass() == NormalPeaShooter.class) {
				if (!CooldownManager.isNormalPeaOnCD() && lvl.getSunpoints() - NormalPeaShooter.NORMAL_PEA_COST >= 0) {
					lvl.placeActor(new NormalPeaShooter(), new Point(xPos, yPos));
					lvl.subtractFromSunpoints(NormalPeaShooter.NORMAL_PEA_COST);
					CooldownManager.startNormalPeaCD();
				}

			} else if (plant.getClass() == GatlingPeaShooter.class) {
				if (!CooldownManager.isGatlingPeaOnCD()
						&& lvl.getSunpoints() - GatlingPeaShooter.GATLING_PEA_COST >= 0) {
					lvl.placeActor(new GatlingPeaShooter(), new Point(xPos, yPos));
					lvl.subtractFromSunpoints(GatlingPeaShooter.GATLING_PEA_COST);
					CooldownManager.startGatlingPeaCD();
				}

			} else { // alert the user that the specified cell is already
						// occupied
				game.getController().showAlert("No room!", null, "There's already something placed here!",
						AlertType.INFORMATION);
			}

		}

	}

}
