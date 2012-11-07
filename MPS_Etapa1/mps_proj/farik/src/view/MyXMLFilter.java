package view;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyXMLFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
	    String extension = Utils.getExtension(f);
	    if (extension != null) {
	        if (extension.equals(Utils.xml))
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
	
	class Utils{
	    public final static String xml = "xml";
	    public final static String exe = "exe";
	    
	/*
	* Get the extension of a file.
	*/
	    public static String getExtension(File f){
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');
	        if (i > 0 && i < s.length() - 1){
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }
	}
