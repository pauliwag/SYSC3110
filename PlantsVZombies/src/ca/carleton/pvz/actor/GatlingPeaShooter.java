package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A class for the gatling pea shooter, which has a high damage output.
 *
 */
public class GatlingPeaShooter extends PeaShooter {

	private static final long serialVersionUID = -9009721810436148422L;
	public static final int GATLING_PEA_COST = 300;
	public static final int GATLING_PEA_DAMAGE = 350;
	public static final int GATLING_PEA_CD = 2; // cooldown
	public static final String GATLING_PEA_ICON = "/ca/carleton/pvz/resources/threepeater_icon.png";
	public static final String GATLING_PEA_GRASS_SPRITE = "/ca/carleton/pvz/resources/threepeater_grass.png";
	public static final String GATLING_PEA_SAND_SPRITE = "/ca/carleton/pvz/resources/threepeater_sand.png";
	public static final String GATLING_PEA_ICE_SPRITE = "/ca/carleton/pvz/resources/threepeater_ice.png";

	/**
	 * Constructs a new gatling pea-shooting plant, which deals more damage per
	 * shot than a normal pea shooter.
	 */
	public GatlingPeaShooter() {
		super(GATLING_PEA_COST, GATLING_PEA_DAMAGE);
	}

	/**
	 * Gets the GatlingPeaShooter sprite.
	 *
	 * @return The GatlingPeaShooter sprite.
	 */
	@Override
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(GATLING_PEA_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(GATLING_PEA_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(GATLING_PEA_ICE_SPRITE));
		return null;
	}

}
