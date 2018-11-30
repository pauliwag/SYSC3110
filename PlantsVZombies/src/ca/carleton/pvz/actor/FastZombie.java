package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Climate;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that moves twice as fast as a normal zombie but is
 * weaker.
 *
 */
public class FastZombie extends Zombie {

	private static final long serialVersionUID = -7849979833866862945L;

	public static final int FAST_ZOMBIE_HEALTH = 400;
	public static final int FAST_ZOMBIE_SPEED = 2;

	/**
	 * Constructs a fast zombie.
	 */
	public FastZombie() {

		super(FAST_ZOMBIE_HEALTH, FAST_ZOMBIE_SPEED);

	}

	/**
	 * Gets the fast zombie sprite.
	 *
	 * @return The fast zombie sprite.
	 */
	public Image getSprite(Climate climate) {

		if (climate == Climate.NORMAL)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/fast_zombie.png"));

		if (climate == Climate.DESERT)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/fast_zombie_desert.png"));

		if (climate == Climate.WINTER)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/fast_zombie_winter.png"));

		return null;

	}

}
