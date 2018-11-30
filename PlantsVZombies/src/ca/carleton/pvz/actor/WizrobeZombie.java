package ca.carleton.pvz.actor;

import java.io.InputStream;

import ca.carleton.pvz.level.Level.Climate;
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
	public static final int WIZROBE_ZOMBIE_HEALTH = 300 + 300; // health + forcefield
	public static final int WIZROBE_ZOMBIE_SPEED = 1;

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
	public Image getSprite(Climate climate) {
		if(climate == Climate.NORMAL) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/wizard_zombie_normal.png"));
		if(climate == Climate.DESERT) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/wizard_zombie_desert.png"));
		if(climate == Climate.WINTER) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/wizard_zombie_winter.png"));
		return null;
	}

}
