package ca.carleton.pvz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.carleton.pvz.gui.GUIController;
import javafx.scene.control.Alert.AlertType;

public abstract class FileFactory {
	
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
