package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that teleports, each turn, to the row with the least
 * amount of plant damage output. Only the damage outputs of plants to the left
 * of the BossZombie are considered. A BossZombie has a speed of 2 and the most
 * health of all zombie types.
 *
 */
public class BossZombie extends TeleportingZombie {

	private static final long serialVersionUID = 3104194636098611570L;

	public static final int BOSS_ZOMBIE_HEALTH = 1500;
	public static final int BOSS_ZOMBIE_SPEED = 1;

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
	public Image getSprite(Terrain climate) {

		if (climate == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/boss_zombie_normal.png"));

		if (climate == Terrain.SAND)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/sand_boss_zombie.png"));

		if (climate == Terrain.ICE)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/zamboni_zombie.png"));

		return null;

	}

}
