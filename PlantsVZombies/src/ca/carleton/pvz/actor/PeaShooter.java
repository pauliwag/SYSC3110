package ca.carleton.pvz.actor;

/**
 * Parent class of all pea-shooting plant subspecies.
 *
 */
public abstract class PeaShooter extends Plant {

	private int peaDamage; // damage done per hit

	/**
	 * Initializes the peaDamage field.
	 *
	 * @param peaDamage The damage a pea inflicts each hit.
	 */
	public PeaShooter(int peaDamage) {

		this.peaDamage = peaDamage;

	}

}
