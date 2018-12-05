package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that has a shield (more health than normal).
 *
 */
public class ShieldZombie extends Zombie {

	private static final long serialVersionUID = 1946845095016310284L;

	public static final int SHIELD_ZOMBIE_HEALTH = 800;
	public static final int SHIELD_ZOMBIE_SPEED = 1;

	/**
	 * Constructs a shield zombie.
	 */
	public ShieldZombie() {

		super(SHIELD_ZOMBIE_HEALTH, SHIELD_ZOMBIE_SPEED);

	}

	/**
	 * Gets the shield zombie sprite.
	 *
	 * @return The shield zombie sprite.
	 */
	public Image getSprite(Terrain terrain) {

		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/shield_zombie_grass.png"));

		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/shield_zombie_sand.png"));

		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/shield_zombie_ice.png"));

		return null;

	}

}
