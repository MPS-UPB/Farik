package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.xml.parsers.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.IOException;

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

		/* partea de DOM = Document Object Model */
		/*
		 * 1) Se vor adauga tab-uri cu atribut de numar de pagina si la
		 * blocurile nemarcate. 2) Se vor reprezenta in interfata grafica
		 * JComboBox-uri si JRadioButton-uri pentru fiecare bloc din xml 3)
		 */
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document document = null; // arborele DOM
		try {
			document = builder.parse(s);
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// System.out.println(document.getDocumentElement().getAttribute("image"));
		document.normalize();
		afiseazaDOM(document);

		/*
		 * elementul radacina al arborelui DOM ; chiar in acesta se afla
		 * tag-urile care ne intereseaza pe noi
		 */
		Element root = document.getDocumentElement();
		if ( root.hasChildNodes() == false ){	// nu avem niciun bloc
			System.out.println("Documentul este gol.");
		}
		
		
		
		/* preluarea numelui pozei folosind numele xml-ului */
		pozaScanata = s.substring(0, s.length() - 3) + "png"; // ceva.png
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* Panoul din stanga */
		JScrollPane jspLeft = new JScrollPane();
		jspLeft.setLayout(null);
		jspLeft.setBorder(new LineBorder(Color.BLACK, 2));
		jspLeft.setBounds(10, 65, 80, 540);
		jspLeft.setBackground(ChooseFilesMenu.blueBack);

		/* Panoul de sus */
		JPanel jpUp = new JPanel();
		jpUp.setLayout(null);
		jpUp.setBorder(new LineBorder(Color.BLACK, 2));
		jpUp.setBounds(100, 15, 852, 40);
		jpUp.setBackground(ChooseFilesMenu.blueBack);

		/* ComboBox pentru panoul din stanga */
		String values[] = { "  N", "  A", "  T", "  S", "  B", "  P" };
		JComboBox jcb = new JComboBox(values);
		jcb.setBackground(Color.WHITE);
		jcb.setForeground(Color.BLACK);
		jcb.setBounds(14, 55, 52, 28);
		jcb.setSelectedItem("  P");

		/* RadioButton pentru panoul de sus */
		JRadioButton jrb = new JRadioButton();
		jrb.setBounds(40, 10, 20, 20);
		jrb.setBackground(ChooseFilesMenu.blueBack);

		jpUp.add(jrb);
		jspLeft.add(jcb);

		contentPane.add(jspLeft);
		contentPane.add(jpUp);

		/* Buton pentru instructiuni de utilizare a interfetei numarul 2 */
		JButton jb = new JButton("Help");
		jb.setBounds(11, 15, 80, 40);
		jb.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		jb.setForeground(ChooseFilesMenu.blueBack);
		jb.setFont(new Font("Verdana", Font.BOLD, 16));
		jb.setBackground(Color.WHITE);
		jb.setFocusable(false);
		contentPane.add(jb);

		/* panoul din partea dreapta a ecranului */
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(100, 65, 894, 540);
		contentPane.add(panel);
		panel.setLayout(null);

		/* partea cu pozele din panoul din partea dreapta */
		ImageScrolledPane scrollPan = new ImageScrolledPane(pozaScanata);
		scrollPan.setBounds(10, 5, 858, 530);
		scrollPan.setVerticalScrollBarPolicy(22); // 22 = scroll vertical
		scrollPan.width = 842; // mai mic decat 858, ca sa incapa si scroll-ul
		// vertical
		scrollPan.height = 500;

		scrollPan.setVisible(true);
		panel.add(scrollPan);
	}

	/* --------------------------------------------------------------------- */
	/* ------------------------ functie care afiseaza DOM-ul --------------- */
	/* --------------------------------------------------------------------- */
	public void afiseazaDOM(Document document) {
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerFactoryConfigurationError e1) {
			e1.printStackTrace();
		}
		Source source = new DOMSource(document);
		Result output = new StreamResult(System.out);
		try {
			transformer.transform(source, output);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}