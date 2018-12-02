package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that has a crapton of health! :p
 *
 */
public class GigaZombie extends Zombie {

	private static final long serialVersionUID = 2794406455409047062L;

	public static final int GIGA_ZOMBIE_HEALTH = 2000;
	public static final int GIGA_ZOMBIE_SPEED = 1;

	/**
	 * Constructs a GigaZombie.
	 */
	public GigaZombie() {

		super(GIGA_ZOMBIE_HEALTH, GIGA_ZOMBIE_SPEED);

	}

	/**
	 * Gets the GigaZombie sprite.
	 *
	 * @return The GigaZombie sprite.
	 */
	public Image getSprite(Terrain climate) {

		if (climate == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/pig_zombie.png"));

		if (climate == Terrain.SAND)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/pyramid_head_zombie.png"));

		if (climate == Terrain.ICE)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/walrus_zombie.png"));

		return null;

	}

}
