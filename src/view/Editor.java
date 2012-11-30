/*
 *
NU IL VOM MAI FOLOSI. Probleme de nume 
 *
CINE POATE SA IL STEARGA ... SA O FACA CU CURAJ
)
 *
 *
 *
 *
 */
package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class Editor extends JFrame {

	/*
	 * Linie ceruta de Eclipse pentru serializare.
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Lanseaza ce-a de-a doua fereastra mare a aplicatiei ( zona de editare ).
	 */

	/**
	 * Creaza cadrul.
	 */
	public Editor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
