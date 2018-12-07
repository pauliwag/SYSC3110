package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that has a shield (i.e., more health than normal).
 *
 */
public class ShieldZombie extends Zombie {

	private static final long serialVersionUID = 1946845095016310284L;
	public static final int SHIELD_ZOMBIE_HEALTH = 800;
	public static final int SHIELD_ZOMBIE_SPEED = 1;
	public static final String SHIELD_ZOMBIE_GRASS_SPRITE = "/ca/carleton/pvz/resources/shield_zombie_grass.png";
	public static final String SHIELD_ZOMBIE_SAND_SPRITE = "/ca/carleton/pvz/resources/shield_zombie_sand.png";
	public static final String SHIELD_ZOMBIE_ICE_SPRITE = "/ca/carleton/pvz/resources/shield_zombie_ice.png";

	/**
	 * Constructs a ShieldZombie.
	 */
	public ShieldZombie() {
		super(SHIELD_ZOMBIE_HEALTH, SHIELD_ZOMBIE_SPEED);
	}

	/**
	 * Gets the ShieldZombie sprite.
	 *
	 * @return The ShieldZombie sprite.
	 */
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(SHIELD_ZOMBIE_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(SHIELD_ZOMBIE_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(SHIELD_ZOMBIE_ICE_SPRITE));
		return null;
	}

}
