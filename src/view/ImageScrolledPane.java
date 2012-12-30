package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ImageScrolledPane extends JScrollPane {
	JLabel label2, label1;
	int height, width;
	double ratio;
	ImageIcon image;

	public ImageScrolledPane() {
		super();
		setBackground(Color.gray);

		JPanel panel = new JPanel();
		this.image = new ImageIcon("poate.png");
		label1 = new JLabel(image);
		label2 = new JLabel(image);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(label1);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel jlc = new JLabel();
		jlc.setPreferredSize(new Dimension(255, 100));
//		panel.add(label2);

		panel.setBackground(new Color(255, 200, 200));
		this.getViewport().add(panel);
	}

	public void resize() {
		if (height == 0)
			height = 1;

		// ratio = trebuie calculat : width_poza_initala / height_poza_initala
		// redimensionam poza la : width_emisfera_dreapta,
		// ratio*width_emisfera_dreapta

		ratio = (double) image.getIconWidth() / image.getIconHeight();

		Image img = image.getImage();
		width = width - 10;
		Image newimg = img.getScaledInstance(width, (int) (width / ratio),
				java.awt.Image.SCALE_SMOOTH);

		ImageIcon newIcon = new ImageIcon(newimg);
		label1.setIcon(newIcon);
		label2.setIcon(newIcon);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

}