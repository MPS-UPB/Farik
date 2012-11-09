package view;

import javax.swing.*;
import javax.swing.border.*;

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
		setBounds(0, 0, w, h);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* scroll-ul central ( care va derula simultan ambele ferestre ) */
		JScrollPane CentralScroll = new JScrollPane();
		CentralScroll.setBounds(378, 0, 22, 508);
		contentPane.add(CentralScroll);

		/* Panoul din partea stanga : documentul analizat */
		JPanel ConvertedDocument = new JPanel();
		ConvertedDocument.setBounds(12, 0, 366, 508);
		contentPane.add(ConvertedDocument);

		/* Panou din partea dreapta : documentul scanat */
		JPanel ScannedDocument = new JPanel();
		ScannedDocument.setBounds(402, 0, 366, 508);
		contentPane.add(ScannedDocument);

		/* buton de marire */
		JButton zoomIn = new JButton("+");
		ScannedDocument.add(zoomIn);

		/* buton de micsorare */
		JButton zoomOut = new JButton("-");
		ScannedDocument.add(zoomOut);

		/*
		 * Scroll-ul din dreapta ecranului. Acesta regleaza doar pozitionarea in
		 * documentul original.
		 */
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(768, 0, 18, 508);
		contentPane.add(scrollPanel);
	}
}
