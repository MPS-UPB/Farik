package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ImageScrolledPane extends JScrollPane
{
	JLabel label2,label1;
	int height, width ;
	double ratio ;
	ImageIcon  image;
	
	
	public void setHeight(int height) {
		this.height = height;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public void setRatio(double ratio) {
		this.ratio = ratio;
	}


	public ImageScrolledPane()
	{
		
		super();
		setBackground( Color.gray );
		
		JPanel panel = new JPanel();
		this.image = new ImageIcon( "poate.png" );
		label1 = new JLabel(image);
	//	label1 = new JLabel(new BufferedImage(1, 1, 1));

		
		
//		if ( height == 0 )
//			height = 1 ;
//		ratio = width/height ;
//		label1.setPreferredSize(new Dimension(width, (int) (height*ratio)));
		label2 = new JLabel(image);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(label1);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel jlc = new JLabel();
		jlc.setPreferredSize(new Dimension(255,100));
//		label2.setPreferredSize(new Dimension(width, (int) (height*ratio)));
		panel.add(label2);

		System.out.println(height + "/##" + width);
		panel.setBackground(new Color(255,0,0));
		this.getViewport().add(panel);
	}
	public void resize(){
		if ( height == 0 )
		height = 1 ;
	/*
	 * aici
	 
	BufferedImage bi = new BufferedI
	label1.getIcon();
	
	*/
	
	// ratio = trebuie calculat : width_poza_initala / height_poza_initala
	// redimensionam poza la : width_emisfera_dreapta, ratio*width_emisfera_dreapta 
		
	ratio = (double) image.getIconWidth()/image.getIconHeight() ;

		
		
	Image img = image.getImage(); 
	Image newimg = img.getScaledInstance(width, (int) (width/ratio),  java.awt.Image.SCALE_SMOOTH);  
	System.out.println("ratio = " + ratio + " wp " + image.getIconWidth() +  " hp "+ image.getIconHeight() + " wpanel " + width + " hpanel "  + height  );
	System.out.println("new ratio = " + (width/(width/ratio) ) + "new wp" + width+  "hp "+ (width/ratio)  );

	ImageIcon newIcon = new ImageIcon(newimg);  
	label1.setIcon(newIcon);
	label2.setIcon(newIcon);

	//label1.setPreferredSize(new Dimension(width, (int) (height*ratio)));
	//label2.setPreferredSize(new Dimension(width, (int) (height*ratio)));
	}
}