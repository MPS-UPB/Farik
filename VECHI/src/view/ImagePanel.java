package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ImagePanel extends JLabel {

	private BufferedImage image;

	public ImagePanel(String img) {
		try {
			image = ImageIO.read(new File(img));
		} catch (IOException ex) {
			// handle exception...
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}