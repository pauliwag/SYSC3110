package ca.carleton.pvz.actor;

/**
 * Manages global cooldowns. "Time" is currently represented by turns; i.e., one
 * unit of time = one turn.
 *
 */
public final class CooldownManager {

	private static final int SUNFLOWER_CD = 2;
	private static final int NORMAL_PEASHOOTER_CD = 2;
	private static final int GATLING_PEASHOOTER_CD = 2;

	private static int sunTimeLeftOnCD;
	private static int normalPeaTimeLeftOnCD;
	private static int gatlingPeaTimeLeftOnCD;

	// render this class as uninstantiable
	private CooldownManager() {}

	/**
	 * Decrements all global cooldowns by one.
	 */
	public static void decTimeOnCD() {

		if (sunTimeLeftOnCD > 0) --sunTimeLeftOnCD;
		if (normalPeaTimeLeftOnCD > 0) --normalPeaTimeLeftOnCD;
		if (gatlingPeaTimeLeftOnCD > 0) --gatlingPeaTimeLeftOnCD;

	}

	/**
	 * Resets all global cooldowns; e.g., for starting a new level.
	 */
	public static void resetCDs() {

		sunTimeLeftOnCD = 0;
		normalPeaTimeLeftOnCD = 0;
		gatlingPeaTimeLeftOnCD = 0;

	}

	/**
	 * Returns whether the sunflower cooldown is in effect.
	 *
	 * @return true if the sunflower cooldown is in effect, false otherwise.
	 */
	public static boolean isSunOnCD() {

		return sunTimeLeftOnCD > 0;

	}

	/**
	 * Returns whether the normal pea shooter cooldown is in effect.
	 *
	 * @return true if the normal pea shooter cooldown is in effect, false
	 *         otherwise.
	 */
	public static boolean isNormalPeaOnCD() {

		return normalPeaTimeLeftOnCD > 0;

	}

	/**
	 * Returns whether the gatling pea shooter cooldown is in effect.
	 *
	 * @return true if the gatling pea shooter cooldown is in effect, false
	 *         otherwise.
	 */
	public static boolean isGatlingPeaOnCD() {

		return gatlingPeaTimeLeftOnCD > 0;

	}

	/**
	 * Starts the sunflower cooldown.
	 */
	public static void startSunCD() {

		sunTimeLeftOnCD = SUNFLOWER_CD;

	}

	/**
	 * Starts the normal pea shooter cooldown.
	 */
	public static void startNormalPeaCD() {

		normalPeaTimeLeftOnCD = NORMAL_PEASHOOTER_CD;

	}

	/**
	 * Starts the gatling pea shooter cooldown.
	 */
	public static void startGatlingPeaCD() {

		gatlingPeaTimeLeftOnCD = GATLING_PEASHOOTER_CD;

	}

	/**
	 * Gets the remaining sunflower cooldown.
	 *
	 * @return The remaining sunflower cooldown.
	 */
	public static int getSunTimeLeftOnCD() {

		return sunTimeLeftOnCD;

	}

	/**
	 * Gets the remaining normal pea shooter cooldown.
	 *
	 * @return The remaining normal pea shooter cooldown.
	 */
	public static int getNormalPeaTimeLeftOnCD() {

		return normalPeaTimeLeftOnCD;

	}

	/**
	 * Gets the remaining gatling pea shooter cooldown.
	 *
	 * @return The remaining gatling pea shooter cooldown.
	 */
	public static int getGatlingPeaTimeLeftOnCD() {

		return gatlingPeaTimeLeftOnCD;

	}

}
