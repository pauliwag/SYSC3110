package ca.carleton.pvz.actor;

import java.awt.Point;
import ca.carleton.pvz.level.Level;

/**
 * The parent zombie class, from which different zombie types inherit.
 *
 */
public abstract class Zombie extends Actor {

	/** The health points of this zombie. */
	private int health;

	/**
	 * The speed of this zombie; i.e., how many tiles it moves to the left per
	 * turn.
	 */
	private int speed;

	/**
	 * Creates a new Zombie.
	 */
	public Zombie(int health, int speed) {

		this.health = health;

		this.speed = speed;

	}

	/**
	 * Gets the current health of this zombie.
	 *
	 * @return The current health of this zombie.
	 */
	public int getHealth() {

		return health;

	}

	/**
	 * Gets the speed of this zombie; i.e., how many tiles it moves to the left
	 * per turn.
	 *
	 * @return The speed of this zombie; i.e., how many tiles it moves to the
	 *         left per turn.
	 */
	public int getSpeed() {

		return speed;

	}

	/**
	 * Sets the health of this zombie.
	 *
	 * @param health This zombie's health will be assigned the given value.
	 */
	public void setHealth(int health) {

		this.health = health;

	}

	public static void moveZombies(Level level) {

		// TODO : Emigrate this method ...

		for (int i = 0; i < level.getDimension().height; ++i) {
			for (int j = 0; j < level.getDimension().width; ++j) {
				Actor o = level.getCell(i, j);
				if (o instanceof Zombie) {
					Actor z1 = level.getCell(i, j);
					level.placeActor(z1, new Point(i - 1, j));
					level.placeActor(null, new Point(i, j));
				}
			}
		}
	}

}