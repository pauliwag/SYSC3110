package ca.carleton.pvz.actor;

import java.io.InputStream;

import ca.carleton.pvz.level.Level.Climate;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that moves thrice as fast as a normal zombie and has lots
 * of health.
 *
 */
public class GigaZombie extends Zombie {

	private static final long serialVersionUID = 2794406455409047062L;
	public static final int GIGA_ZOMBIE_HEALTH = 900;
	public static final int GIGA_ZOMBIE_SPEED = 3;

	/**
	 * Constructs a GigaZombie.
	 */
	public GigaZombie() {
		super(GIGA_ZOMBIE_HEALTH, GIGA_ZOMBIE_SPEED);
	}

	/**
	 * Gets the GigaZombie sprite.
	 *
	 * @return The GigaZombie sprite.
	 */
	public Image getSprite(Climate climate) {
		if(climate == Climate.NORMAL) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/giga_zombie_normal.png"));
		if(climate == Climate.DESERT) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/giga_zombie_desert.png"));
		if(climate == Climate.WINTER) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/giga_zombie_winter.png"));
		return null;
	}

}
