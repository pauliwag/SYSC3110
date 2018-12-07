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
	public static final String NORMAL_ZOMBIE_GRASS_SPRITE = "/ca/carleton/pvz/resources/normal_zombie_grass.png";
	public static final String NORMAL_ZOMBIE_SAND_SPRITE = "/ca/carleton/pvz/resources/normal_zombie_sand.png";
	public static final String NORMAL_ZOMBIE_ICE_SPRITE = "/ca/carleton/pvz/resources/normal_zombie_ice.png";

	/**
	 * Constructs a normal zombie.
	 */
	public NormalZombie() {
		super(NORMAL_ZOMBIE_HEALTH, NORMAL_ZOMBIE_SPEED);
	}

	/**
	 * Gets the NormalZombie sprite.
	 *
	 * @return The NormalZombie sprite.
	 */
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(NORMAL_ZOMBIE_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(NORMAL_ZOMBIE_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(NORMAL_ZOMBIE_ICE_SPRITE));
		return null;
	}

}
