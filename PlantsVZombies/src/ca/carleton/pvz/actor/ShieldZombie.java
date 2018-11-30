package ca.carleton.pvz.actor;


import ca.carleton.pvz.level.Level.Climate;
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
	public Image getSprite(Climate climate) {
		if(climate == Climate.NORMAL) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/shield_zombie.png"));
		if(climate == Climate.DESERT) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/shield_zombie_desert.png"));
		if(climate == Climate.WINTER) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/shield_zombie_winter.png"));
		return null;
	}

}
