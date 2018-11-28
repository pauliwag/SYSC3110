package ca.carleton.pvz.actor;

import java.io.InputStream;

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
	public static final int BOSS_ZOMBIE_HEALTH = 900;
	public static final int BOSS_ZOMBIE_SPEED = 2;

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
	public Image getSprite() {
		InputStream stream = getClass().getResourceAsStream("boss_zombie.png");
		return new Image(stream);
	}

}
