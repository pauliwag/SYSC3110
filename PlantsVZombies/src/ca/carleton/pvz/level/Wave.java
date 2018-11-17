package ca.carleton.pvz.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.actor.Zombie;

/**
 * Stores a wave of zombies.
 *
 */
public class Wave {

	/**
	 * This wave's number; determines when this wave will be deployed via waves
	 * priority queue in Level class.
	 */
	private int waveNum;

	/** A collection of the zombies in this wave. */
	private ArrayList<Zombie> zombies;

	private static Random r;

	/**
	 * Creates a new wave comprising the specified number of zombies.
	 *
	 * @param waveNum This wave's sequence number.
	 * @param numZombies The number of zombies initially in this wave.
	 */
	public Wave(int waveNum, int numZombies) {

		this.waveNum = waveNum;

		zombies = new ArrayList<>(numZombies);
		for (int i = 0; i < numZombies; ++i) zombies.add(new NormalZombie());

		r = new Random();

	}

	/**
	 * Returns whether this wave is defeated.
	 *
	 * @return true if this wave is defeated, false otherwise.
	 */
	public boolean isDefeated() {
		return zombies.size() == 0;
	}

	/**
	 * Spawns zombies on game map according to waveNumber and numberofZombies.
	 *
	 * @param map The game map to be modified when zombies are spawning.
	 * @return The resulting game map after new zombies have spawned.
	 */
	public static Level spawnZombieOnLevel(Level level) {

		// TODO : Emigrate this method ...

		int randomRow = r.nextInt(5);
		Zombie zombie = new NormalZombie();
		level.placeActor(zombie, new Point(4, randomRow));
		return level;
	}

	/**
	 * Gets the number of zombies remaining from this wave.
	 *
	 * @return The number of zombies remaining from this wave.
	 */
	public int getRemainingZombies() {
		return zombies.size();
	}

	/**
	 * Gets this wave's number.
	 *
	 * @return This wave's number.
	 */
	public int getNum() {
		return waveNum;
	}

	/**
	 * Sets this wave's number.
	 *
	 * @param waveNum The number to be assigned to this wave.
	 */
	public void setWaveNumber(int waveNum) {
		this.waveNum = waveNum;
	}

	/**
	 * Sets the number of zombies remaining in this wave.
	 *
	 * @param numZombies The new number of zombies in this wave.
	 */
	public void setRemainingZombies(int numZombies) {

		while (zombies.size() != numZombies) {

			if (zombies.size() > numZombies) zombies.remove(0);

			if (zombies.size() < numZombies) zombies.add(new NormalZombie());

		}
	}
}
