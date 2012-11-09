package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.io.File;

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
		fcXMLs = new JFileChooser();
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
							System.out.println(i);
							System.out.println(files[i].getAbsolutePath());
							allTheFiles = allTheFiles + files[i].getAbsolutePath() ;
							if ( i < files.length - 1)
								allTheFiles += "; ";
						}
						multipleXMLsField.setText(allTheFiles);
					}
				}
			}
		});
		contentPane.add(selectMultipleXMLs);

		/* Butonul care va selecta binarul */
		fcFile = new JFileChooser();
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
				// TODO Auto-generated method stub
				if (multipleXMLsField.getText() != null
						&& !multipleXMLsField.getText().equals("")
						&& hierarchyFileField.getText() != null
						&& !hierarchyFileField.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane,
							"The file was generated.");
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Please Fill in all fields.");
				}
			}
		});
		contentPane.add(generate);

		/* Butonul radio de jos ( care va selecta varianta cu XML final ) */
		final JRadioButton downRadioButton = new JRadioButton(
				"Load the resulted XML");
		downRadioButton.setBounds(28, 178, 200, 23);
		contentPane.add(downRadioButton);

		/* Campul in care va aparea numele fisierului XML final */
		XMLfile = new JTextField();
		XMLfile.setBounds(236, 180, 161, 19);
		contentPane.add(XMLfile);
		XMLfile.setColumns(10);

		/* Butonul care va selecta fisierul XML final */
		fcXML = new JFileChooser();
		/* adaug un filtru la fileChooser; pot fi selectate numai fisiere XML */
		fcXML.setFileFilter(filter);
		final JButton selectXML = new JButton("Select XML");
		selectXML.setBounds(409, 177, 143, 25);
		selectXML.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == selectXML) {
					int returnVal = fcXML.showOpenDialog(contentPane);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fcXML.getSelectedFile();
						XMLfile.setText(file.getAbsolutePath());
					}
				}
			}
		});
		selectXML.setEnabled(false);
		contentPane.add(selectXML);

		/* doar un singur JRadioButton poate fi selectat la un moment dat */
		downRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
		final JButton showHierarchy = new JButton("Show hierarchy");
		final Frame frameTakeValues = new Frame();
		frameTakeValues.setExtendedState(Frame.MAXIMIZED_BOTH);
		showHierarchy.setBounds(236, 224, 161, 25);
		showHierarchy.setEnabled(true);
		showHierarchy.addActionListener(new ActionListener() {
			TheEditor theEditor = new TheEditor(0,0,frameTakeValues.getWidth(), frameTakeValues.getHeight());
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				theEditor.setVisible(true);
				theEditor.setExtendedState(Frame.MAXIMIZED_BOTH);
				theEditor.setExtendedState(Cursor.E_RESIZE_CURSOR);
			}
		});
		contentPane.add(showHierarchy);

		/* Bara de status ( aflata in partea de jos a ferestrei ) */
		JLabel statusBar = new JLabel("");
		statusBar.setBounds(0, 345, 548, 25);
		contentPane.add(statusBar);

	}
}
