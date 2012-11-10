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
		/* Va ramane de tip JScrollPane si se va corecta diagrama UML prin taierea clasei CentralScroll */
		JScrollPane centralScroll = new JScrollPane();
		centralScroll.setBounds(378, 0, 22, 508);
		contentPane.add(centralScroll);

		/* Panoul din partea stanga : documentul analizat */
		/* Acesta va deveni de tip ConvertedDocument */
		JPanel convertedDocument = new JPanel();
		convertedDocument.setBounds(12, 0, 366, 508);
		contentPane.add(convertedDocument);

		/* Panou din partea dreapta : documentul scanat */
		/* Va deveni de tip ScannedDocument */
		JPanel scannedDocument = new JPanel();
		scannedDocument.setBounds(402, 0, 366, 508);
		contentPane.add(scannedDocument);

		/* buton de marire */
		JButton zoomIn = new JButton("+");
		scannedDocument.add(zoomIn);

		/* buton de micsorare */
		JButton zoomOut = new JButton("-");
		scannedDocument.add(zoomOut);

		/*
		 * Scroll-ul din dreapta ecranului. Acesta regleaza doar pozitionarea in
		 * documentul original.
		 */
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(768, 0, 18, 508);
		contentPane.add(scrollPanel);
	}
}
