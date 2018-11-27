package ca.carleton.pvz.actor;

import java.io.InputStream;

import ca.carleton.pvz.level.Level;
import javafx.scene.image.Image;

/**
 * A subtype of Zombie that teleports, each turn, to the row with the least
 * amount of plant damage output. Only the damage outputs of plants to the left
 * of the WizrobeZombie are considered. A magic forcefield artificially boosts
 * its health.
 *
 */
public class WizrobeZombie extends Zombie {

	private static final long serialVersionUID = 2131337503393053904L;

	public static final int WIZROBE_ZOMBIE_HEALTH = 300 + 300; // health +
																// forcefield
	public static final int WIZROBE_ZOMBIE_SPEED = 1;

	/**
	 * Constructs a WizrobeZombie.
	 */
	public WizrobeZombie() {
		super(WIZROBE_ZOMBIE_HEALTH, WIZROBE_ZOMBIE_SPEED);
	}

	/**
	 * Auxiliary of WizrobeZombie behavior. Compares plant damage outputs across
	 * all rows and returns the index of the row with the least plant damage
	 * output. Only the damage outputs of plants to the left of this
	 * WizrobeZombie's next x-coordinate (i.e., the x-coordinate it will have
	 * after it moves) are considered. That is the row to which this
	 * WizrobeZombie should teleport.
	 *
	 * @param lvl The active level.
	 * @param x The next x-coordinate of this WizrobeZombie; i.e., its current
	 *            x-coordinate minus one.
	 * @param y The current y-coordinate of this WizrobeZombie.
	 * @return The row to which this WizrobeZombie should teleport.
	 */
	public int rowToTeleportTo(Level lvl, int x, int y) {

		// get plant damage output (to the left of this
		// WizrobeZombie's next x-coordinate) in current row
		int row = y;
		int rowDmg = 0;
		for (int xAux = 0; xAux < x; ++xAux) {
			if (lvl.getCell(xAux, y) instanceof PeaShooter) {
				rowDmg += ((PeaShooter) lvl.getCell(xAux, y)).getPeaDamage();
			}
		}

		/*
		 * Compare plant damage outputs across all rows and store the index of
		 * the row with the least plant damage output. Note that only the damage
		 * outputs of plants to the left of this WizrobeZombie's next
		 * x-coordinate (i.e., the x-coordinate it will have after it moves) are
		 * considered.
		 */
		for (int yAux = 0; yAux < lvl.getNumRows(); ++yAux) {
			if (yAux != y) { // optimization
				int rowDmgAux = 0;
				for (int xAux = 0; xAux < x; ++xAux) {
					if (lvl.getCell(xAux, yAux) instanceof PeaShooter) {
						rowDmgAux += ((PeaShooter) lvl.getCell(xAux, yAux)).getPeaDamage();
					}
				}

				/*
				 * If there is more than one least-damage row, that whose cell
				 * at this WizrobeZombie's next x-coordinate (i.e., in the
				 * column immediately to the left of this WizrobeZombie) does
				 * not contain a zombie, is prioritized.
				 */
				if (rowDmgAux < rowDmg || (rowDmgAux == rowDmg && !(lvl.getCell(x, yAux) instanceof Zombie)
						&& (lvl.getCell(x, row) instanceof Zombie))) {
					row = yAux;
					rowDmg = rowDmgAux;
				}

			}
		}

		return row;

	}

	/**
	 * Gets the WizrobeZombie sprite.
	 *
	 * @return The WizrobeZombie sprite.
	 */
	public Image getSprite() {
		InputStream stream = getClass().getResourceAsStream("fast_zombie.png");
		return new Image(stream);
	}

}
