package ca.carleton.pvz.actor;

import java.awt.Point;
import ca.carleton.pvz.level.Level;

/**
 * The parent zombie class, from which different zombie types inherit.
 *
 */
public abstract class Zombie extends Actor {

	private int health;

	/**
	 * Creates a new Zombie.
	 */
	public Zombie(int health) {
		this.health = health;
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