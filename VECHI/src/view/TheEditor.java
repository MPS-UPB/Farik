package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Creaza cadrul celei de-a doua ferestre importante a aplicatiei ( cea de
 * editare ).
 */
public class TheEditor extends JFrame {

	/*
	 * Linie ceruta de Eclipse pentru serializare.
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	// TODO : de facut pozitionarea absoluta a elementelor DINAMIC ( in functie
	// de x1,y1,w,h ) sa fie aranjate frumos in fereastra numarul 2
	// Alina / Andrei / Radu
	public TheEditor(int x1, int y1, int w, int h) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setExtendedState(MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// JScrollPane jsp1 = new JScrollPane();
		// jsp1.setBounds(10, 10, 656, 1000);
		// JScrollPane jscp = new
		// JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		// ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		Panou p1 = new Panou("\n\t\tMonster: Steve Harmon's Face Book Page", 10,
				11, 626, 50);
		Panou p2 = new Panou(
				"\tWhat if Steve Harmon were a real boy? Sound too much like the beginning of\nPinocchio tale? Oh well. I did the best I could to make this kid real, even did the math and\nfigured out that if he was 16 in 1999 when the novel was written, then he'd be 26 or 27\nchose to make his birthday the day he was acquitted, for what better birthdat present\non trial for his life get than another day of life?",
				10, 80, 626, 120);
		Panou p3 = new Panou(
				"\n\tHis parents were never named in the text, so I called his mother Mercy, since\nwilling to give him a second chance, and his father I named Steven Justice Harmon for if\ntechnically should have found Steve guilty based on the emotionless law. His father had\nSteve guilty, maybe not of the crime but of hanging out with a crowd he'd taught Steve was\nbelow his stature. His sense of justice could not be overcome, so he shut Steve out, locked\nup by walking away.",
				10, 214, 626, 150);

		// JScrollPane scroll = new JScrollPane(p);
		// scroll.setBounds(15,15,600,500);
		// scroll.setLayout(new GridLayout());
		// scroll.add(p);
		// scroll.add(p2);

		// jsp1.add(p);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		// contentPane.add(p2);

		/* panoul din partea dreapta a ecranului */
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(646, 11, 694, 708);
		contentPane.add(panel);
		panel.setLayout(null);

		/* panoul cu butoanele +/- */
		JPanel panel_1 = new JPanel();
//		panel_1.setBounds(10, 11, 658, 37);
		panel.add(panel_1);

		JButton button = new JButton("+");
//		panel_1.add(button);

		JButton button_1 = new JButton("-");
//		panel_1.add(button_1);

		ImageScrolledPane scrollPane_1 = new ImageScrolledPane();

		/* partea cu pozele din panoul din partea dreapta */
		scrollPane_1.setBounds(10, 5, 658, 627);
		scrollPane_1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setWidth(658 - 17);
		scrollPane_1.setHeight(627);

		scrollPane_1.setVisible(true);
		scrollPane_1.resize();
		panel.add(scrollPane_1);
	}
}