package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level.Terrain;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that moves twice as fast as a normal zombie and has high
 * health.
 *
 */
public class FootballZombie extends Zombie {

	private static final long serialVersionUID = 6214923633339415829L;
	public static final int FOOTBALL_ZOMBIE_HEALTH = 900;
	public static final int FOOTBALL_ZOMBIE_SPEED = 2;
	public static final String FOOTBALL_ZOMBIE_GRASS_SPRITE = "/ca/carleton/pvz/resources/football_zombie_grass.png";
	public static final String FOOTBALL_ZOMBIE_SAND_SPRITE = "/ca/carleton/pvz/resources/football_zombie_sand.png";
	public static final String FOOTBALL_ZOMBIE_ICE_SPRITE = "/ca/carleton/pvz/resources/football_zombie_ice.png";

	/**
	 * Constructs a FootballZombie.
	 */
	public FootballZombie() {
		super(FOOTBALL_ZOMBIE_HEALTH, FOOTBALL_ZOMBIE_SPEED);
	}

	/**
	 * Gets the FootballZombie sprite.
	 *
	 * @return The FootballZombie sprite.
	 */
	@Override
	public Image getSprite(Terrain terrain) {
		if (terrain == Terrain.GRASS)
			return new Image(getClass().getResourceAsStream(FOOTBALL_ZOMBIE_GRASS_SPRITE));
		if (terrain == Terrain.SAND)
			return new Image(getClass().getResourceAsStream(FOOTBALL_ZOMBIE_SAND_SPRITE));
		if (terrain == Terrain.ICE)
			return new Image(getClass().getResourceAsStream(FOOTBALL_ZOMBIE_ICE_SPRITE));
		return null;
	}

}
