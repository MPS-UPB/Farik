package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ImageScrolledPane extends JScrollPane {
	JLabel label2, label1;
	int height, width;
	double ratio;
	ImageIcon image;

	public ImageScrolledPane(String pozaScanata) {
		super();

		JPanel panel = new JPanel();
		this.image = new ImageIcon(pozaScanata);
		label1 = new JLabel(image);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(label1);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel jlc = new JLabel();
		jlc.setPreferredSize(new Dimension(255, 100));

		this.getViewport().add(panel);
	}

	public void resize() {

		height = (height == 0) ? 1 : height;

		ratio = (double) image.getIconWidth() / image.getIconHeight();

		Image img = image.getImage();
		width = width - 10;
		// 4 = Image.SCALE_SMOOTH
		Image newimg = img.getScaledInstance(width, (int) (width / ratio), 4);

		label1.setIcon(new ImageIcon(newimg));
	}
}