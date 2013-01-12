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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 * Creaza cadrul celei de-a doua ferestre importante a aplicatiei ( cea de
 * editare ).
 */
@SuppressWarnings("serial")
public class TheEditor extends JFrame {

	private JPanel contentPane;

	String pozaScanata;
	int pagina = 0;
	Vector<ImageData> pagesData;
	public JPanel panel;
	int nrPag = 0;
	ArrayList<JComboBoxCoordinates> jcbList;
	
	public static JButton next = new JButton("Next");
	public static JButton prev = new JButton("Prev");

	private class ImageData {
		private String imagePath;
		private String imageDirection;
		private String imageId;

		public ImageData(String path, String direction, String id) {
			this.imagePath = path;
			this.imageDirection = direction;
			this.imageId = id;
		}

		private String getPath() {
			return this.imagePath;
		}

		private String getDirection() {
			return this.imageDirection;
		}

		private String getId() {
			return this.imageId;
		}

		public String toString() {
			return "[" + this.imagePath + "," + this.imageDirection + ","
					+ this.imageId + "]";
		}
	}

	/*
	 * s = ceva.xml page_number = N, article = A, title = T, subtitle = S, body
	 * = B, paragraph = P
	 */

	public TheEditor(String s) {

		/* partea de DOM = Document Object Model */
		/*
		 * 
		 * 
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

		document.normalize();
//		 afiseazaDOM(document);

		/*
		 * elementul radacina al arborelui DOM ; chiar in acesta se afla
		 * tag-urile care ne intereseaza pe noi
		 */
		Element root = document.getDocumentElement();

		// vectorul de imagini care compune fisierul ierarhie

		NodeList nl = document.getElementsByTagName("Document");
		this.pagesData = new Vector<ImageData>();
		for (int i = 0; i < nl.getLength(); i++) {
			// Get element
			Element element = (Element) nl.item(i);
			NamedNodeMap atributes = element.getAttributes();

			String direction = atributes.item(0).getTextContent();
			String id = atributes.item(1).getTextContent();
			String path = atributes.item(2).getTextContent();

			ImageData image = new ImageData(path, direction, id);
			this.pagesData.add(image);
		}

		nrPag = nl.getLength();

//		System.out.println(this.pagesData);

		if (root.hasChildNodes() == false) { // nu avem niciun bloc
			System.out.println("Documentul este gol.");
		}

		System.out.println("\n");
		// parcurg toti fii radacinii
		Node sibling = root.getFirstChild();
		while (sibling != null) {
//			System.out.println("copii radacina: " + sibling.getNodeName());
			// daca nu pun conditia de mai jos e obligatoriu ca xml-urile sa fie
			// cu tag-uri lipite </tag1><tag2>
			if (sibling.getNodeName().equals("#text") == false ) {
				// poate fi doar ComposedBlock sau TextBlock 
					if (sibling.getNodeName().equals("ComposedBlock") == false) {
						// TODO : se preiau valorile pentru a se pozitiona
						// JComboBox-ul si JRadioButton-ul
	
					} else {
						// TODO : se preiau valorile pentru a se pozitiona
						// JComboBox-ul si JRadioButton-ul
	
						// TODO : i se creeza lui TextBlock un parinte ComposedBlock
						// avand type="page_number"
	
					}
			}

			sibling = sibling.getNextSibling();

		}
		
		// iau toate elementele xml-ului care sunt copii ai elementului hierarchy_blocks
		NodeList elementsList = document.getElementsByTagName("hierarchy_blocks");
		NodeList hierarchyNodeElements = null;
		if( elementsList.getLength()==1) {
			hierarchyNodeElements = elementsList.item(0).getChildNodes();
		}
		
		for( int i=0; i<hierarchyNodeElements.getLength(); ++i) {
			NodeList childList = hierarchyNodeElements.item(i).getChildNodes();
			for( int j=0; j<childList.getLength(); ++j) {
				NodeList composedBlockComponents = childList.item(j).getChildNodes();
				for( int k=0; k<composedBlockComponents.getLength(); ++k ) {
					if( !composedBlockComponents.item(k).getNodeName().equals("#text")) {
						NamedNodeMap nodeAttr = composedBlockComponents.item(k).getAttributes();
						String id = nodeAttr.getNamedItem("refid").getNodeValue();
						char aux = id.charAt(id.length()-1);
						int pageId = Character.getNumericValue(aux);
						String type = childList.item(j).getAttributes().getNamedItem("type").getNodeValue();
						int left = Integer.parseInt(nodeAttr.getNamedItem("left").getNodeValue());
						int right = Integer.parseInt(nodeAttr.getNamedItem("right").getNodeValue());
						int top = Integer.parseInt(nodeAttr.getNamedItem("top").getNodeValue());
						int bottom = Integer.parseInt(nodeAttr.getNamedItem("bottom").getNodeValue());
						if( jcbList==null ) {
							jcbList = new ArrayList<JComboBoxCoordinates>();
						}
						Node tmp = childList.item(j).getAttributes().getNamedItem("type");
						jcbList.add(new JComboBoxCoordinates(tmp, pageId, type, left, right, top, bottom));
					}
				}
			}
			
		}
		
		// TODO : partea de afisare a JComboBox-urilor in functie de
		// JRadioButton-ul selectat

		// TODO : la modificarea unui JComboBox trebuie sa se modifice DOM-ul (
		// type = <<ce s-a selectat>> in loc de type = "page_number" )

		// TODO : se va crea un nou fisier ( nu se va suprascrie vechiul )
		// continand DOM-ul ( se poate folosi metoda afiseazaDOM de mai jos cu
		// mici adaptari ). Noul fisier va avea numele
		// <<numele_vechi>>_corectat.xml

		/* preluarea numelui pozei folosind numele xml-ului */
		// pozaScanata = s.substring(0, s.length() - 3) + "png"; // ceva.png
		// int pagina = 0;
		pozaScanata = this.pagesData.elementAt(pagina).getPath();
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
		
		//afisam JComboBox-urile
		for( final JComboBoxCoordinates jcbC : jcbList ) {
			JComboBox localCombobox = jcbC.getComboBox();
			localCombobox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jcbC.setNode();
					System.out.println("Nodul modificat : " + jcbC.getNode().getNodeValue());
				}
			});
			jspLeft.add(localCombobox);
//			localCombobox.setVisible(false);
		}
		
		for( JComboBoxCoordinates jcbC : jcbList ) {
			System.out.println("pagina: " + pagina);
		}

		/* RadioButton pentru panoul de sus */
//		JRadioButton jrb = new JRadioButton();
//		jrb.setBounds(40, 10, 20, 20);
//		jrb.setBackground(ChooseFilesMenu.blueBack);
//
//		jpUp.add(jrb);
		
		JButton generate = new JButton("Generate");
		generate.setBounds(40, 10, 100, 20);
		generate.setBackground(Color.GREEN);
		final Document document2 = document;
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				afiseazaDOM(document2);
			}
		});
		jpUp.add(generate);
//		jspLeft.add(jcb);

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
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(100, 65, 894, 540);
		contentPane.add(panel);
		panel.setLayout(null);

		/* partea cu pozele din panoul din partea dreapta */
		/*
		 * ImageScrolledPane scrollPan = new ImageScrolledPane(pozaScanata);
		 * scrollPan.setBounds(10, 5, 858, 530);
		 * scrollPan.setVerticalScrollBarPolicy(22); // 22 = scroll vertical
		 * scrollPan.width = 842; // mai mic decat 858, ca sa incapa si
		 * scroll-ul // vertical scrollPan.height = 500;
		 * 
		 * scrollPan.setVisible(true); panel.add(scrollPan);
		 */
		this.LoadPage(panel, pagina);

		/* Butoanele NEXT Page si Previous Page */
		next = new JButton("Next");
		next.setBounds(540, 610, 80, 40);
		next.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		next.setForeground(ChooseFilesMenu.blueBack);
		next.setFont(new Font("Verdana", Font.BOLD, 16));
		next.setBackground(Color.WHITE);
		next.setFocusable(false);
		next.addActionListener(new java.awt.event.ActionListener() {
	       public void actionPerformed(java.awt.event.ActionEvent e) {
	    	   pagina++;
           		LoadPage(panel, pagina);
           		prev.setEnabled(true);
           		if(pagina == nrPag - 1){
           			JButton button = (JButton)e.getSource();
           			button.setEnabled(false);
           		}
           }
       });  
		contentPane.add(next);

		prev = new JButton("Prev");
		prev.setBounds(430, 610, 80, 40);
		prev.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		prev.setForeground(ChooseFilesMenu.blueBack);
		prev.setFont(new Font("Verdana", Font.BOLD, 16));
		prev.setBackground(Color.WHITE);
		prev.setFocusable(false);
		prev.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pagina--;
				LoadPage(panel, pagina);
				next.setEnabled(true);
				if(pagina == 0){
					JButton button = (JButton)e.getSource();
					button.setEnabled(false);
				}
			}
		});  	
	
		contentPane.add(prev);
		this.LoadPage(panel,pagina);
	
		if(pagina == 0) {
			prev.setEnabled(false);
		}
		if( pagina == nrPag-1) {
			next.setEnabled(false);
		}
	}

	private void LoadPage(JPanel panel, int index) {
		panel.removeAll();
		String pagePath = ((ImageData) this.pagesData.get(index)).imagePath;
		ImageScrolledPane scrollPan = new ImageScrolledPane(pagePath);
		scrollPan.setBounds(10, 5, 858, 530);
		scrollPan.setVerticalScrollBarPolicy(22); // 22 = scroll vertical
		scrollPan.width = 842; // mai mic decat 858, ca sa incapa si scroll-ul
		// vertical
		scrollPan.height = 500;
		scrollPan.setVisible(true);
		panel.add(scrollPan);
		panel.revalidate();
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