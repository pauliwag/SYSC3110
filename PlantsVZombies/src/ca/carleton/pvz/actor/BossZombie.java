package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that teleports, each turn, to the row with the least
 * amount of plant damage output. Only the damage outputs of plants to the left
 * of the BossZombie are considered. A BossZombie has a speed of 1 but the
 * 2nd-most health of all zombie types.
 *
 */
public class BossZombie extends TeleportingZombie {

	private static final long serialVersionUID = 3104194636098611570L;
	public static final int BOSS_ZOMBIE_HEALTH = 1500;
	public static final int BOSS_ZOMBIE_SPEED = 1;
	public static final String BOSS_ZOMBIE_GRASS_SPRITE = "/ca/carleton/pvz/resources/boss_zombie_grass.png";
	public static final String BOSS_ZOMBIE_SAND_SPRITE = "/ca/carleton/pvz/resources/boss_zombie_sand.png";
	public static final String BOSS_ZOMBIE_ICE_SPRITE = "/ca/carleton/pvz/resources/boss_zombie_ice.png";

	/**
	 * Constructs a BossZombie.
	 */
	public BossZombie() {
		super(BOSS_ZOMBIE_HEALTH, BOSS_ZOMBIE_SPEED);
	}

	/**
	 * Gets the BossZombie sprite.
	 *
	 * @return The BossZombie sprite.
	 */
	@Override
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(BOSS_ZOMBIE_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(BOSS_ZOMBIE_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(BOSS_ZOMBIE_ICE_SPRITE));
		return null;
	}

}
