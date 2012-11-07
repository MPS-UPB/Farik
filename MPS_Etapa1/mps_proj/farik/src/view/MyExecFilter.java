package view;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyExecFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
	    String extension = Utils.getExtension(f);
	    if (extension != null) {
	        if (extension.equals(Utils.exe))
	                return true;
	        } else {
	            return false;
	        }

	    return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
