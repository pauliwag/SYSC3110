package ca.carleton.pvz.actor;

import java.io.InputStream;

import ca.carleton.pvz.level.Level.Climate;
import javafx.scene.image.Image;

/**
 * A class for the normal pea-shooting plant.
 *
 */
public class NormalPeaShooter extends PeaShooter {

	private static final long serialVersionUID = -5722345411858347989L;
	public static final int NORMAL_PEA_COST = 200;
	public static final int NORMAL_PEA_DAMAGE = 200;

	/**
	 * Creates a new normal pea-shooting plant. This type of plant can shoot and
	 * "kill" zombies.
	 */
	public NormalPeaShooter() {
		super(NORMAL_PEA_COST, NORMAL_PEA_DAMAGE);
	}

	/**
	 * Gets the peashooter sprite.
	 *
	 * @return The peashooter sprite.
	 */
	@Override
	public Image getSprite(Climate climate) {
		if(climate == Climate.NORMAL) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/peashooter.png"));
		if(climate == Climate.DESERT) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/peashooter_desert.png"));
		if(climate == Climate.WINTER) return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/peashooter_winter.png"));
		return null;
	}

}