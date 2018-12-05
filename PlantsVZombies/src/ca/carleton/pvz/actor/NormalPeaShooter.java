package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
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
	public Image getSprite(Terrain terrain) {

		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/peashooter_grass.png"));

		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/peashooter_sand.png"));

		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream("/ca/carleton/pvz/resources/peashooter_ice.png"));

		return null;

	}

}