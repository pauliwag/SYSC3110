package ca.carleton.pvz.actor;

/**
 * The parent plant class, from which all plant species inherit.
 *
 */
public abstract class Plant extends Actor {

	/** The cost, in sunpoints, of this plant. */
	private int cost;

	/**
	 * Initializes the cost field to the given value.
	 *
	 * @param cost The cost, in sunpoints, of this plant.
	 */
	public Plant(int cost) {

		this.cost = cost;

	}

	/**
	 * Gets the cost, in sunpoints, of this plant.
	 *
	 * @return The cost, in sunpoints, of this plant.
	 */
	public int getCost() {

		return cost;

	}

}