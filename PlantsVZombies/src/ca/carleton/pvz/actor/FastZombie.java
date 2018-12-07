package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that moves twice as fast as a normal zombie but has less
 * health.
 *
 */
public class FastZombie extends Zombie {

	private static final long serialVersionUID = -7849979833866862945L;
	public static final int FAST_ZOMBIE_HEALTH = 400;
	public static final int FAST_ZOMBIE_SPEED = 2;
	public static final String FAST_ZOMBIE_GRASS_SPRITE = "/ca/carleton/pvz/resources/fast_zombie_grass.png";
	public static final String FAST_ZOMBIE_SAND_SPRITE = "/ca/carleton/pvz/resources/fast_zombie_sand.png";
	public static final String FAST_ZOMBIE_ICE_SPRITE = "/ca/carleton/pvz/resources/fast_zombie_ice.png";

	/**
	 * Constructs a fast zombie.
	 */
	public FastZombie() {
		super(FAST_ZOMBIE_HEALTH, FAST_ZOMBIE_SPEED);
	}

	/**
	 * Gets the FastZombie sprite.
	 *
	 * @return The FastZombie sprite.
	 */
	@Override
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(FAST_ZOMBIE_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(FAST_ZOMBIE_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(FAST_ZOMBIE_ICE_SPRITE));
		return null;
	}

}
