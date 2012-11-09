package view;

import java.io.File;

/**
 * Clasa contine metoda de obtinere a extensiei pentru un fisier.
 *
 */
public class UtilsExtension {

	/**
	 * Returneaza extensia fisierului. Pentru fisierele fara extensie se returneaza null.
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
