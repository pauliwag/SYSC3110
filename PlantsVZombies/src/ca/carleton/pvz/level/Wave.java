package ca.carleton.pvz.level;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import ca.carleton.pvz.actor.BossZombie;
import ca.carleton.pvz.actor.FastZombie;
import ca.carleton.pvz.actor.FootballZombie;
import ca.carleton.pvz.actor.GigaZombie;
import ca.carleton.pvz.actor.NormalZombie;
import ca.carleton.pvz.actor.ShieldZombie;
import ca.carleton.pvz.actor.WizrobeZombie;
import ca.carleton.pvz.actor.Zombie;

/**
 * Comprises a hash table of zombies and has a difficulty setting which dictates
 * zombie spawn rate.
 *
 */
public class Wave implements Serializable {

	private static final long serialVersionUID = -2718072975646634711L;

	/**
	 * This wave's number; determines when this wave will be deployed via the
	 * waves priority queue in the Level class.
	 */
	private int waveNum;

	/** A hash table of the numbers of different zombie types in this wave. */
	private HashMap<Class<? extends Zombie>, Integer> zombies;

	/**
	 * The possible difficulty levels of a wave. Harder difficulty entails
	 * greater zombie spawn rate.
	 */
	public enum Difficulty {
		NORMAL, RAMPED, SUPER_RAMPED
	};

	/** This wave's difficulty level. */
	private Difficulty difficulty;

	/**
	 * Creates a new wave comprising the specified numbers of zombies.
	 *
	 * @param waveNum This wave's sequence number.
	 * @param difficulty This wave's difficulty level.
	 * @param numNormalZombies The number of normal zombies initially in this
	 *            wave.
	 * @param numShieldZombies The number of shield zombies initiallty in this
	 *            wave.
	 * @param numFastZombies The number of fast zombies initially in this wave.
	 * @param numWizrobeZombies The number of WizrobeZombies initially in this
	 *            wave.
	 * @param numFootballZombies The number of FootballZombies initially in this
	 *            wave.
	 * @param numGigaZombies The number of GigaZombies initially in this wave.
	 * @param numBossZombies The number of BossZombies initially in this wave.
	 */
	public Wave(int waveNum, Difficulty difficulty, int numNormalZombies, int numShieldZombies, int numFastZombies,
			int numWizrobeZombies, int numFootballZombies, int numGigaZombies, int numBossZombies) {

		this.waveNum = waveNum;
		this.difficulty = difficulty;

		zombies = new HashMap<>();
		zombies.put(NormalZombie.class, numNormalZombies);
		zombies.put(ShieldZombie.class, numShieldZombies);
		zombies.put(FastZombie.class, numFastZombies);
		zombies.put(WizrobeZombie.class, numWizrobeZombies);
		zombies.put(FootballZombie.class, numFootballZombies);
		zombies.put(GigaZombie.class, numGigaZombies);
		zombies.put(BossZombie.class, numBossZombies);

	}

	/**
	 * Get the wave's difficulty
	 *
	 * @return The wave's difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * Gets whether this wave's difficulty is ramped.
	 *
	 * @return true if this wave is ramped, false otherwise.
	 */
	public boolean isRamped() {
		return difficulty != Difficulty.NORMAL;
	}

	/**
	 * Gets whether this wave's difficulty is super ramped.
	 *
	 * @return true if this wave is super ramped, false otherwise.
	 */
	public boolean isSuperRamped() {
		return difficulty == Difficulty.SUPER_RAMPED;
	}

	/**
	 * Returns whether this wave is defeated.
	 *
	 * @return true if this wave is defeated, false otherwise.
	 */
	public boolean isEmpty() {
		return getTotalNumZombies() == 0;
	}

	/**
	 * Gets the total number of zombies in this wave.
	 *
	 * @return The total number of zombies in this wave.
	 */
	public int getTotalNumZombies() {
		int totalNumZombies = 0;
		for (int num : zombies.values())
			totalNumZombies += num;
		return totalNumZombies;
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
	 * Gets this wave's hash table of zombies.
	 *
	 * @return This wave's hash table of zombies.
	 */
	public HashMap<Class<? extends Zombie>, Integer> getZombies() {
		return zombies;
	}

	/**
	 * Sets this wave's number.
	 *
	 * @param waveNum The number to be assigned to this wave.
	 */
	public void setWaveNum(int waveNum) {
		this.waveNum = waveNum;
	}

	/**
	 * Gets the number of the specified zombie type in this wave.
	 *
	 * @param zombieType The zombie type whose quantity will be returned.
	 */
	public int getNumZombies(Class<? extends Zombie> zombieType) {
		return zombies.containsKey(zombieType) ? zombies.get(zombieType) : 0;
	}

	/**
	 * Sets the number of the specified zombie type in this wave.
	 *
	 * @param zombieType The zombie type whose quantity will be set.
	 * @param num The new quantity of the specified zombie type.
	 */
	public void setNumZombies(Class<? extends Zombie> zombieType, int num) {
		if (zombies.containsKey(zombieType))
			zombies.replace(zombieType, num);
	}

	/**
	 * Returns a String representation of this wave incl. its number; used by
	 * LevelBuilder in displaying a ListView of user-specified waves.
	 *
	 * @return A String representation of this wave incl. its number.
	 */
	public String toString() {
		return "Wave " + waveNum;
	}

	/**
	 * Returns a clone (i.e., deep copy) of this wave, or null if this wave
	 * cannot be serialized.
	 */
	public Wave clone() {
		Wave clone = null;
		try {
			// write this wave out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(this);
			out.flush();
			out.close();
			// make an input stream from the byte array
			// and read a copy of this wave back in
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			clone = (Wave) in.readObject();
			in.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return clone;
	}

}