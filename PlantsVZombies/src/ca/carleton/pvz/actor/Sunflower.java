package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * Creates a sunflower, which accumulates sunpoints each turn. Sunpoints are the
 * in-game currency which the user spends on more plants to defeat the zombies.
 *
 */
public class Sunflower extends Plant {

	private static final long serialVersionUID = 6879988409404943345L;
	public static final int SUNFLOWER_COST = 100;
	public static final int SUNFLOWER_SUNPOINT_BOOST = 50; // every turn
	public static final int SUNFLOWER_CD = 2; // cooldown
	public static final String SUNFLOWER_ICON = "/ca/carleton/pvz/resources/sunflower_icon.png";
	public static final String SUNFLOWER_GRASS_SPRITE = "/ca/carleton/pvz/resources/sunflower_grass.png";
	public static final String SUNFLOWER_SAND_SPRITE = "/ca/carleton/pvz/resources/sunflower_sand.png";
	public static final String SUNFLOWER_ICE_SPRITE = "/ca/carleton/pvz/resources/sunflower_ice.png";

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
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(SUNFLOWER_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(SUNFLOWER_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(SUNFLOWER_ICE_SPRITE));
		return null;
	}

}
