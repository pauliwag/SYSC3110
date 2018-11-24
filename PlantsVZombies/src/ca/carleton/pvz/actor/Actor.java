package ca.carleton.pvz.actor;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * The class from which all plants and zombies inherit.
 *
 */
public abstract class Actor implements Serializable {

	private static final long serialVersionUID = -5281040453260756337L;

	public abstract Image getSprite();

}