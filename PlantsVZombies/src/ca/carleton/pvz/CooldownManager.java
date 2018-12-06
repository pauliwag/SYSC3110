package ca.carleton.pvz;

import java.io.Serializable;

import ca.carleton.pvz.actor.GatlingPeaShooter;
import ca.carleton.pvz.actor.NormalPeaShooter;
import ca.carleton.pvz.actor.Sunflower;

/**
 * A utility class that manages global cooldowns. "Time" is currently
 * represented by turns; i.e., one unit of time = one turn.
 *
 */
public final class CooldownManager implements Serializable {

	private static final long serialVersionUID = 7139989008193865776L;
	private int sunTimeLeftOnCD;
	private int normalPeaTimeLeftOnCD;
	private int gatlingPeaTimeLeftOnCD;

	public CooldownManager() {
	}

	/**
	 * Decrements all cooldowns by one.
	 */
	public void decTimeOnCD() {
		if (sunTimeLeftOnCD > 0)
			--sunTimeLeftOnCD;
		if (normalPeaTimeLeftOnCD > 0)
			--normalPeaTimeLeftOnCD;
		if (gatlingPeaTimeLeftOnCD > 0)
			--gatlingPeaTimeLeftOnCD;
	}

	/**
	 * Resets all cooldowns; e.g., for starting a new level.
	 */
	public void resetCDs() {
		sunTimeLeftOnCD = 0;
		normalPeaTimeLeftOnCD = 0;
		gatlingPeaTimeLeftOnCD = 0;
	}

	/**
	 * Returns whether the sunflower cooldown is in effect.
	 *
	 * @return true if the sunflower cooldown is in effect, false otherwise.
	 */
	public boolean isSunOnCD() {
		return sunTimeLeftOnCD > 0;
	}

	/**
	 * Returns whether the normal pea shooter cooldown is in effect.
	 *
	 * @return true if the normal pea shooter cooldown is in effect, false
	 *         otherwise.
	 */
	public boolean isNormalPeaOnCD() {
		return normalPeaTimeLeftOnCD > 0;
	}

	/**
	 * Returns whether the gatling pea shooter cooldown is in effect.
	 *
	 * @return true if the gatling pea shooter cooldown is in effect, false
	 *         otherwise.
	 */
	public boolean isGatlingPeaOnCD() {
		return gatlingPeaTimeLeftOnCD > 0;
	}

	/**
	 * Starts the sunflower cooldown.
	 */
	public void startSunCD() {
		sunTimeLeftOnCD = Sunflower.SUNFLOWER_CD;
	}

	/**
	 * Starts the normal pea shooter cooldown.
	 */
	public void startNormalPeaCD() {
		normalPeaTimeLeftOnCD = NormalPeaShooter.NORMAL_PEA_CD;
	}

	/**
	 * Starts the gatling pea shooter cooldown.
	 */
	public void startGatlingPeaCD() {
		gatlingPeaTimeLeftOnCD = GatlingPeaShooter.GATLING_PEA_CD;
	}

	/**
	 * Gets the remaining sunflower cooldown.
	 *
	 * @return The remaining sunflower cooldown.
	 */
	public int getSunTimeLeftOnCD() {
		return sunTimeLeftOnCD;
	}

	/**
	 * Gets the remaining normal pea shooter cooldown.
	 *
	 * @return The remaining normal pea shooter cooldown.
	 */
	public int getNormalPeaTimeLeftOnCD() {
		return normalPeaTimeLeftOnCD;
	}

	/**
	 * Gets the remaining gatling pea shooter cooldown.
	 *
	 * @return The remaining gatling pea shooter cooldown.
	 */
	public int getGatlingPeaTimeLeftOnCD() {
		return gatlingPeaTimeLeftOnCD;
	}

}
