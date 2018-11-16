package ca.carleton.pvz.actor;

/**
 * Manages global cooldowns.
 *
 */
public final class CooldownManager {

	private static final int SUNFLOWER_CD = 2;
	private static final int PEASHOOTER_CD = 2;

	private static int sunTimeLeftOnCD;
	private static int peaTimeLeftOnCD;

	// render this class as uninstantiable
	private CooldownManager() {}

	/**
	 * Decrements all global cooldowns by one.
	 */
	public static void decTimeOnCD() {

		if (sunTimeLeftOnCD > 0) --sunTimeLeftOnCD;
		if (peaTimeLeftOnCD > 0) --peaTimeLeftOnCD;

	}

	/**
	 * Resets all global cooldowns; e.g., for starting a new level.
	 */
	public static void resetCDs() {

		sunTimeLeftOnCD = 0;
		peaTimeLeftOnCD = 0;

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
	 * Returns whether the peashooter cooldown is in effect.
	 *
	 * @return true if the peashooter cooldown is in effect, false otherwise.
	 */
	public static boolean isPeaOnCD() {

		return peaTimeLeftOnCD > 0;

	}

	/**
	 * Starts the sunflower cooldown.
	 */
	public static void startSunCD() {

		sunTimeLeftOnCD = SUNFLOWER_CD;

	}

	/**
	 * Starts the peashooter cooldown.
	 */
	public static void startPeaCD() {

		peaTimeLeftOnCD = PEASHOOTER_CD;

	}
	
	/** 
	 * Get the current time left on Sunflower cooldown
	 * @return returns the sunflower cooldown time remaining
	 */
	public static int getCurrentSunCD() {
		return sunTimeLeftOnCD;
	}
	
	/** 
	 * Get the current time left on Peashooter cooldown
	 * @return returns the peashooter cooldown time remaining
	 */
	public static int getCurrentPeaCD() {
		return peaTimeLeftOnCD;
	}
}
