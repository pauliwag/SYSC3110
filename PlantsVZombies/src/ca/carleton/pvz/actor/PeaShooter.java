package ca.carleton.pvz.actor;

/**
 * Parent class of all pea-shooting plant subspecies.
 *
 */
public abstract class PeaShooter extends Plant {

	private static final long serialVersionUID = 1518342918068333369L;

	/** The damage a pea does per hit. */
	private int peaDamage;

	/**
	 * Initializes fields.
	 *
	 * @param cost The cost, in sunpoints, of this pea-shooting plant.
	 * @param peaDamage The damage a pea inflicts (each hit).
	 */
	public PeaShooter(int cost, int peaDamage) {
		super(cost);
		this.peaDamage = peaDamage;
	}

	/**
	 * Gets the damage inflicted by each pea shot by this plant.
	 *
	 * @return The damage inflicted by each pea shot by this plant.
	 */
	public int getPeaDamage() {
		return peaDamage;
	}

}
