package view;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

/**
 * Creaza cadrul celei de-a doua ferestre importante a aplicatiei ( cea de
 * editare ).
 */
@SuppressWarnings("serial")
public class TheEditor extends JFrame {

	private JPanel contentPane;
	String pozaScanata;

	/*
	 * s = ceva.xml page_number = N, article = A, title = T, subtitle = S, body
	 * = B, paragraph = P
	 */
	public TheEditor(String s) {
		pozaScanata = s.substring(0, s.length() - 3) + "png"; // ceva.png
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setToolTipText("contentPane");

		/* ComboBox-urile */
		JScrollPane jspCombo = new JScrollPane();
		jspCombo.setLayout(null);
		jspCombo.setBorder(new LineBorder(Color.BLACK, 2));
		jspCombo.setBounds(10, 15, 80, 600);
		jspCombo.setToolTipText("jspCombo");
		jspCombo.setBackground(Color.WHITE);

		String values[] = { "  N", "  A", "  T", "  S", "  B", "  P" };
		JComboBox jcb = new JComboBox(values);
		jcb.setBackground(ChooseFilesMenu.blueBack);
		jcb.setForeground(Color.WHITE);
		jcb.setBounds(14, 55, 52, 28);
		jcb.setToolTipText("jcb");
		jcb.setSelectedItem("  P");

		jspCombo.add(jcb);

		contentPane.add(jspCombo);

		/* panoul din partea dreapta a ecranului */
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(100, 15, 894, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setToolTipText("panel");

		/* partea cu pozele din panoul din partea dreapta */
		ImageScrolledPane scrollPan = new ImageScrolledPane(pozaScanata);
		scrollPan.setBounds(10, 5, 858, 590);
		scrollPan.setVerticalScrollBarPolicy(22); // 22 = scroll vertical
		scrollPan.width = 842; // mai mic decat 858, ca sa incapa si scroll-ul
		// vertical
		scrollPan.height = 590;

		scrollPan.setVisible(true);
		scrollPan.resize();
		scrollPan.setToolTipText("scrollPan");
		panel.add(scrollPan);
	}
}