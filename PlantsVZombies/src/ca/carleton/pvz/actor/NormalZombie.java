package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that has no special abilities.
 *
 */
public class NormalZombie extends Zombie {

	private static final long serialVersionUID = 6255020074190195644L;

	public static final int NORMAL_ZOMBIE_HEALTH = 500;
	public static final int NORMAL_ZOMBIE_SPEED = 1;

	/**
	 * Constructs a normal zombie.
	 */
	public NormalZombie() {

		super(NORMAL_ZOMBIE_HEALTH, NORMAL_ZOMBIE_SPEED);

	}

	/**
	 * Gets the normal zombie sprite.
	 *
	 * @return The normal zombie sprite.
	 */
	public Image getSprite(Terrain terrain) {

		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/normal_zombie_grass.png"));

		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/normal_zombie_sand.png"));

		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/normal_zombie_ice.png"));

		return null;

	}

}
