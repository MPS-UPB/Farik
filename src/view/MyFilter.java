package view;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Defineste metoda care accepta doar fisierele .xml si directoarele
 */
public class MyFilter extends FileFilter {

	String ext = "";

	/* constructor din care particularizam filtrul pentru .exe / .xml */
	public MyFilter(String ext) {
		this.ext = ext;
	}

	@Override
	public boolean accept(File f) {
		String extension = null;
		/* daca f este director, se va afisa chiar daca nu contine extensia */
		if (!f.isFile())
			return true;
		extension = UtilsExtension.getExtension(f);
		if (extension == null)
			return false;
		else if (extension.equals(ext))
			return true;
		return false;
	}

	@Override
	public String getDescription() {
		return ext;
	}
}