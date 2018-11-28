package ca.carleton.pvz.actor;

import ca.carleton.pvz.level.Level;

/**
 * An abstract class extended by Zombie subtypes that can teleport.
 *
 */
public abstract class TeleportingZombie extends Zombie {

	private static final long serialVersionUID = 7991368923001714676L;

	/**
	 * Initializes this TeleportingZombie.
	 *
	 * @param health The health of this zombie.
	 * @param speed The speed of this zombie.
	 */
	public TeleportingZombie(int health, int speed) {
		super(health, speed);
	}

	/**
	 * Auxiliary of TeleportingZombie behavior. Compares plant damage outputs
	 * across all rows and returns the index of the row with the least plant
	 * damage output. Only the damage outputs of plants to the left of this
	 * TeleportingZombie's next x-coordinate (i.e., the x-coordinate it will
	 * have after it moves) are considered. That is the row to which this
	 * TeleportingZombie should teleport.
	 *
	 * @param lvl The active level.
	 * @param x The next x-coordinate of this TeleportingZombie; i.e., its
	 *            current x-coordinate minus one.
	 * @param y The current y-coordinate of this TeleportingZombie.
	 * @return The row to which this TeleportingZombie should teleport.
	 */
	public int rowToTeleportTo(Level lvl, int x, int y) {

		// get plant damage output (to the left of this
		// TeleportingZombie's next x-coordinate) in current row
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
		 * outputs of plants to the left of this TeleportingZombie's next
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
				 * at this TeleportingZombie's next x-coordinate (i.e., in the
				 * column immediately to the left of this TeleportingZombie)
				 * does not contain a zombie, is prioritized.
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

}
