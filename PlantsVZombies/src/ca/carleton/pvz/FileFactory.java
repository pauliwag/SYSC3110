package ca.carleton.pvz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.carleton.pvz.gui.GUIController;
import javafx.scene.control.Alert.AlertType;

/**
 * Abstract class to handle saving/loading from files.
 *
 */
public abstract class FileFactory {
	
	/**
	 * Serializes and saves an object 
	 * @param o Object to be saved
	 * @param path File path to save object at
	 */
	public static void saveObject(Object o, String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * Loads and unserializes a file.
	 * Shows an alert if file was not found.
	 * 
	 * @param path Path to load file from
	 * @return Unserialized object 
	 */
	public static Object loadObject(String path) {
		Object o = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = in.readObject();
			in.close();
			fileIn.close();
			return o;
		} catch (IOException i) {
			i.printStackTrace();
			GUIController.showAlert("File not found!", null, "The file at " + path + " was not found :(", AlertType.ERROR);
			return o;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return o;
		}
	}
}
