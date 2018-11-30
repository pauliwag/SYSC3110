package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Climate;
import javafx.scene.image.Image;

/**
 * Creates a sunflower, which accumulates sun points each turn. Sun points are
 * the in-game currency which the user spends on more plants to defeat the
 * zombies.
 *
 */
public class Sunflower extends Plant {

	private static final long serialVersionUID = 6879988409404943345L;

	public static final int SUNFLOWER_COST = 100;
	public static final int SUNFLOWER_SUNPOINT_BOOST = 50; // every turn

	/**
	 * Creates a new sunflower.
	 */
	public Sunflower() {

		super(SUNFLOWER_COST);

	}

	/**
	 * Gets the sunflower sprite.
	 *
	 * @return The sunflower sprite.
	 */
	@Override
	public Image getSprite(Climate climate) {

		if (climate == Climate.NORMAL)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/sunflower.png"));

		if (climate == Climate.DESERT)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/sunflower_desert.png"));

		if (climate == Climate.WINTER)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/sunflower_winter.png"));

		return null;

	}

}
