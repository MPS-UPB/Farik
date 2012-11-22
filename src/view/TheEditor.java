package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Color;

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
		//this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		System.out.println("h*:" + this.getSize().height);
		System.out.println("w*:" + this.getSize().width);
		//setBounds(0, 0, java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,  java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 626, 708);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(646, 11, 694, 708);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 658, 37);
		panel.add(panel_1);
		
		JButton button = new JButton("+");
		panel_1.add(button);
		
		JButton button_1 = new JButton("-");
		panel_1.add(button_1);
		
		
		ImageScrolledPane scrollPane_1 = new ImageScrolledPane();
		System.out.println("height: " + panel.getHeight());

		scrollPane_1.setBounds(10, 59, 658, 627);
		scrollPane_1.setVerticalScrollBarPolicy(
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scrollPane_1.setWidth(658-17);
		scrollPane_1.setHeight(627);
		
		scrollPane_1.setVisible(true);
		System.out.println(scrollPane_1.getHeight() + ".....");
		scrollPane_1.resize();
		panel.add(scrollPane_1);
	}
}
