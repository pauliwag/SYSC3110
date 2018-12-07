package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that teleports, each turn, to the row with the least
 * amount of plant damage output. Only the damage outputs of plants to the left
 * of the WizrobeZombie are considered. A magic forcefield artificially boosts
 * its health.
 *
 */
public class WizrobeZombie extends TeleportingZombie {

	private static final long serialVersionUID = 2131337503393053904L;
	public static final int WIZROBE_ZOMBIE_HEALTH = 200 + 300; // health + forcefield
	public static final int WIZROBE_ZOMBIE_SPEED = 1;
	public static final String WIZROBE_ZOMBIE_GRASS_SPRITE = "/ca/carleton/pvz/resources/wizrobe_zombie_grass.png";
	public static final String WIZROBE_ZOMBIE_SAND_SPRITE = "/ca/carleton/pvz/resources/wizrobe_zombie_sand.png";
	public static final String WIZROBE_ZOMBIE_ICE_SPRITE = "/ca/carleton/pvz/resources/wizrobe_zombie_ice.png";

	/**
	 * Constructs a WizrobeZombie.
	 */
	public WizrobeZombie() {
		super(WIZROBE_ZOMBIE_HEALTH, WIZROBE_ZOMBIE_SPEED);
	}

	/**
	 * Gets the WizrobeZombie sprite.
	 *
	 * @return The WizrobeZombie sprite.
	 */
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(WIZROBE_ZOMBIE_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(WIZROBE_ZOMBIE_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(WIZROBE_ZOMBIE_ICE_SPRITE));
		return null;
	}

}
