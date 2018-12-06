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

	/**
	 * Constructs a new gatling pea-shooting plant, which deals more damage per
	 * shot than a normal pea shooter.
	 */
	public GatlingPeaShooter() {

		super(GATLING_PEA_COST, GATLING_PEA_DAMAGE);

	}

	/**
	 * Gets the gatling pea shooter sprite.
	 *
	 * @return The gatling pea shooter sprite.
	 */
	@Override
	public Image getSprite(Terrain terrain) {

		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/threepeater_grass.png"));

		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/threepeater_sand.png"));

		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/threepeater_ice.png"));

		return null;

	}

}
