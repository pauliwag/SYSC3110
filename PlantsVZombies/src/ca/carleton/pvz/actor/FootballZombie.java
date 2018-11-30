package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Climate;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that moves twice as fast as a normal zombie and has high
 * health.
 *
 */
public class FootballZombie extends Zombie {

	private static final long serialVersionUID = 6214923633339415829L;

	public static final int FOOTBALL_ZOMBIE_HEALTH = 900;
	public static final int FOOTBALL_ZOMBIE_SPEED = 2;

	/**
	 * Constructs a FootballZombie.
	 */
	public FootballZombie() {

		super(FOOTBALL_ZOMBIE_HEALTH, FOOTBALL_ZOMBIE_SPEED);

	}

	/**
	 * Gets the FootballZombie sprite.
	 *
	 * @return The FootballZombie sprite.
	 */
	public Image getSprite(Climate climate) {

		if (climate == Climate.NORMAL)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/football_zombie_normal.png"));

		if (climate == Climate.DESERT)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/football_zombie_desert.png"));

		if (climate == Climate.WINTER)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/football_zombie_winter.png"));

		return null;

	}

}
