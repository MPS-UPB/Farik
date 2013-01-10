package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.StringTokenizer;

/*
 * Momentan se incarca fisierul CEVA.xml si se va afisa imaginea CEVA.png.
 * Pentru a fi mai usoara testarea deocamdata sunt niste chestii hardcodate. Trebuie
 * revenit la cele vechi ( comentariile : DE FACUT si DE STERS de pe la linia 285 ) 
 */

/**
 * Aceasta clasa descrie prima fereastra aparuta in interfata grafica.
 */
public class ChooseFilesMenu extends JFrame {

	/**
	 * Ceruta pentru serializare de catre Eclipse
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField multipleXMLsField;
	private JTextField hierarchyFileField;
	private JTextField XMLfile;
	private JFileChooser fcXML;
	private JFileChooser fcXMLs;
	private JFileChooser fcFile;
	static Color blueBack = new Color(40, 80, 180);
	final JButton showHierarchy;

	/**
	 * Lansarea aplicatiei se face apeland metoda main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseFilesMenu frame = new ChooseFilesMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructorul clasei.
	 */
	public ChooseFilesMenu() {
		/* titlul afisat in bara de sus a primei ferestre */
		setTitle("Hierarchy analysis application - Farik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* dimensiunile primei ferestre : xstanga, ystanga, latime, inaltime */
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); /* pozitionarea obiectelor este absoluta */

		/* butonul radio de sus : selectie XML-uri + binar */
		final JRadioButton upRadioButton = new JRadioButton(
				"Load XMLs and binary");
		upRadioButton.setBounds(28, 51, 184, 21);
		upRadioButton.setBackground(blueBack);
		upRadioButton.setForeground(Color.WHITE);
		upRadioButton.setBorderPainted(false);
		upRadioButton.setBorder(null);
		contentPane.add(upRadioButton);

		/*
		 * Campul in care vor aparea fisierele XML selectate ( in cazul in care
		 * acestea necesita un binar )
		 */
		multipleXMLsField = new JTextField();
		multipleXMLsField.setBounds(236, 54, 161, 19);
		contentPane.add(multipleXMLsField);
		multipleXMLsField.setColumns(10);

		/* Campul in care va aparea binarul */
		hierarchyFileField = new JTextField();
		hierarchyFileField.setBounds(236, 85, 161, 19);
		contentPane.add(hierarchyFileField);
		hierarchyFileField.setColumns(10);

		/* Butonul care va selecta mai multe XML-uri ( cel din dreapta-sus ) */
		fcXMLs = new JFileChooser(".");
		MyFilter filter = new MyFilter("xml");
		fcXMLs.setFileFilter(filter);
		fcXMLs.setMultiSelectionEnabled(true);
		final JButton selectMultipleXMLs = new JButton("Select XMLs");
		selectMultipleXMLs.setBounds(409, 51, 143, 25);
		selectMultipleXMLs.setEnabled(false);
		selectMultipleXMLs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == selectMultipleXMLs) {
					int returnVal = fcXMLs.showOpenDialog(contentPane);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File[] files = fcXMLs.getSelectedFiles();
						String allTheFiles = "";
						for (int i = 0; i < files.length; ++i) {
							allTheFiles = allTheFiles
									+ files[i].getAbsolutePath();
							if (i < files.length - 1)
								allTheFiles += "; ";
						}
						multipleXMLsField.setText(allTheFiles);
					}
				}
			}
		});
		contentPane.add(selectMultipleXMLs);

		/* Butonul care va selecta binarul */
		fcFile = new JFileChooser(".");
		MyFilter execFilter = new MyFilter("exe");
		fcFile.setFileFilter(execFilter);
		final JButton selectHierarchyFile = new JButton("Select file");
		selectHierarchyFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == selectHierarchyFile) {
					int returnVal = fcFile.showOpenDialog(contentPane);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fcFile.getSelectedFile();
						hierarchyFileField.setText(file.getAbsolutePath());
					}
				}
			}
		});
		selectHierarchyFile.setBounds(409, 84, 143, 25);
		selectHierarchyFile.setEnabled(false);
		contentPane.add(selectHierarchyFile);

		/* Butonul care va genera ( din XML-uri si binar ) XML-ul final */
		final JButton generate = new JButton("Generate hierarchy file");
		generate.setBounds(236, 116, 316, 25);
		generate.setEnabled(false);
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (multipleXMLsField.getText() != null
						&& !multipleXMLsField.getText().equals("")
						&& hierarchyFileField.getText() != null
						&& !hierarchyFileField.getText().equals("")) {
						genereaza();
					JOptionPane.showMessageDialog(contentPane,
							"The file was generated.");
					showHierarchy.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Fill in all fields.");
				}
			}
		});
		contentPane.add(generate);

		/* Butonul radio de jos ( care va selecta varianta cu XML final ) */
		final JRadioButton downRadioButton = new JRadioButton(
				"Load the resulted XML");
		downRadioButton.setBounds(28, 178, 200, 23);
		downRadioButton.setSelected(true);
		downRadioButton.setForeground(Color.WHITE);
		downRadioButton.setBackground(blueBack);
		contentPane.add(downRadioButton);
		contentPane.setBackground(blueBack);
		contentPane.setToolTipText("contentPane");

		/* Campul in care va aparea numele fisierului XML final */
		XMLfile = new JTextField();
		XMLfile.setBounds(236, 180, 161, 19);
		contentPane.add(XMLfile);
		XMLfile.setColumns(10);
		XMLfile.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				if (XMLfile.getText().trim().length() > 3)
					showHierarchy.setEnabled(true);
			}
		});
		XMLfile.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (XMLfile.getText().trim().length() > 3)
					showHierarchy.setEnabled(true);
			}
		});

		/* Butonul care va selecta fisierul XML final */
		fcXML = new JFileChooser(".");
		/* adaug un filtru la fileChooser; pot fi selectate numai fisiere XML */
		fcXML.setFileFilter(filter);
		final JButton selectXML = new JButton("Select XML");
		selectXML.setBounds(409, 177, 143, 25);
		selectXML.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showHierarchy.setEnabled(true);
				if (e.getSource() == selectXML) {
					int returnVal = fcXML.showOpenDialog(contentPane);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fcXML.getSelectedFile();
						XMLfile.setText(file.getAbsolutePath());
					}
				}
			}
		});
		selectXML.setEnabled(true);
		contentPane.add(selectXML);

		/* doar un singur JRadioButton poate fi selectat la un moment dat */
		downRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (downRadioButton.isSelected()) {
					upRadioButton.setSelected(false);
					selectMultipleXMLs.setEnabled(false);
					selectHierarchyFile.setEnabled(false);
					multipleXMLsField.setText("");
					hierarchyFileField.setText("");
					generate.setEnabled(false);
					selectXML.setEnabled(true);
				} else {
					selectMultipleXMLs.setEnabled(true);
					selectHierarchyFile.setEnabled(true);
					generate.setEnabled(true);
				}
			}
		});

		upRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (upRadioButton.isSelected()) {
					downRadioButton.setSelected(false);
					XMLfile.setText("");
					selectMultipleXMLs.setEnabled(true);
					selectHierarchyFile.setEnabled(true);
					generate.setEnabled(true);
					selectXML.setEnabled(false);
				} else {
					selectXML.setEnabled(true);
				}
			}
		});

		/*
		 * Butonul care va face sa apara ce-a de-a doua fereastra in care se vor
		 * afla documentul descifrat si documentul in original
		 */
		showHierarchy = new JButton("Show hierarchy");
		final Frame frameTakeValues = new Frame();
		frameTakeValues.setBounds(0, 0, 100, 200);

		frameTakeValues.setExtendedState(Frame.MAXIMIZED_BOTH);

		showHierarchy.setBounds(236, 224, 161, 25);
		showHierarchy.setEnabled(true);
		// TODO : DE FACUT FALSE MAI SUS -> la final
		// showHierarchy.setEnabled(false);
		// !!!
		showHierarchy.addActionListener(new ActionListener() {
			/* se va deschide fereastra numarul 2 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//XMLfile.setText("capra1.xml"); // TODO : DE STERS LA FINAL !!!
				String s = XMLfile.getText();
				int l = s.length();
				/* daca fisierul este CEVA.xml este ok */
				if (l > 4 && s.substring(l - 4, l).equals(".xml")) {
					TheEditor theEditor = new TheEditor(s);
					theEditor.setVisible(true);
					theEditor.setBounds(0, 0, 1024, 690);
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Fill the box with the name of a xml file.");
				}
			}
		});
		contentPane.add(showHierarchy);

	}

	public void genereaza() {
		try {

			// We need a Document
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element root = doc.createElement("task");
			doc.appendChild(root);

			StringTokenizer st = new StringTokenizer(
					multipleXMLsField.getText(), ";");
			while (st.hasMoreTokens()) {
				Element child = doc.createElement("inputFile");
				child.setAttribute("name", st.nextToken().trim());
				root.appendChild(child);
			}

			Element output = doc.createElement("outputFile");
			output.setAttribute("name", "generated.xml");
			root.appendChild(output);

			// ///////////////
			// Output the XML

			// set up a transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.METHOD, "xml");
			// trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			String xmlString = sw.toString();

			// print xml
			System.out.println("Here's the xml:\n\n" + xmlString);
			BufferedWriter out = new BufferedWriter(new FileWriter("task.xml"));
			out.write(xmlString);
			out.close();
			Runtime.getRuntime().exec( hierarchyFileField.getText() + " task.xml"); 

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}