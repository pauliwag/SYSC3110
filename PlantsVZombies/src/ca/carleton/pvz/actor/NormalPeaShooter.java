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
	public static final int NORMAL_PEA_CD = 2; // cooldown
	public static final String NORMAL_PEA_ICON = "/ca/carleton/pvz/resources/peashooter_icon.png";
	public static final String NORMAL_PEA_GRASS_SPRITE = "/ca/carleton/pvz/resources/peashooter_grass.png";
	public static final String NORMAL_PEA_SAND_SPRITE = "/ca/carleton/pvz/resources/peashooter_sand.png";
	public static final String NORMAL_PEA_ICE_SPRITE = "/ca/carleton/pvz/resources/peashooter_ice.png";

	/**
	 * Creates a new normal pea-shooting plant. This type of plant can shoot and
	 * "kill" zombies.
	 */
	public NormalPeaShooter() {
		super(NORMAL_PEA_COST, NORMAL_PEA_DAMAGE);
	}

	/**
	 * Gets the NormalPeaShooter sprite.
	 *
	 * @return The NormalPeaShooter sprite.
	 */
	@Override
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(NORMAL_PEA_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(NORMAL_PEA_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(NORMAL_PEA_ICE_SPRITE));
		return null;
	}

}