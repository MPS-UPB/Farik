package view;

import java.awt.Color;

import javax.swing.JComboBox;

import org.w3c.dom.Node;

public class JComboBoxCoordinates {

	// va trebui si un parametru pentru pagina pe care se afla combobox-ul respectiv
	int id; // imi spune pe ce pagina ma aflu
	Node typeNode; // nodul din xml pe care il voi modifica
	String type;
	int left;
	int right;
	int top; 
	int bottom;
	String values[] = { "  N", "  A", "  T", "  S", "  B", "  P" };
	JComboBox jcb;
	
	public int getId() {
		return this.id;
	}

	public JComboBoxCoordinates(Node n, int id, String type, int left, int right, int top, int bottom) {
		
		this.typeNode = n;
		this.id = id;
		this.type =type;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		
		jcb = new JComboBox(values);
		jcb.setBackground(Color.WHITE);
		jcb.setForeground(Color.BLACK);
		jcb.setBounds(14, this.top + (this.bottom-this.top)/2 , 50, 50);
		
		if( this.type.equals("paragraph")) {
			jcb.setSelectedIndex(5);
		} else if( this.type.equals("title")) {
			jcb.setSelectedIndex(2);
		} else if (this.type.equals("subtitle")) {
			jcb.setSelectedIndex(3);
		} else if( this.type.equals("article")) {
			jcb.setSelectedIndex(1);
		} else if ( this.type.equals("body")) {
			jcb.setSelectedIndex(4);
		} else {
			jcb.setSelectedIndex(0);	
		}
	}
	
	public Node getNode() {
		return this.typeNode;
	}
	
	public void setNode() {
		if( this.jcb.getSelectedIndex()==5) {
			System.out.println(jcb.getSelectedIndex());
			this.typeNode.setNodeValue("paragraph");
		} else if( this.jcb.getSelectedIndex()==4) {
			this.typeNode.setNodeValue("body");
		} else if( this.jcb.getSelectedIndex()==3) {
			this.typeNode.setNodeValue("subtitle");
		} else if( this.jcb.getSelectedIndex()==2) {
			this.typeNode.setNodeValue("title");
		} else if( this.jcb.getSelectedIndex()==1) {
			this.typeNode.setNodeValue("article");
		} else  {
			this.typeNode.setNodeValue("page_number");
		}
	}
	
	public JComboBox getComboBox() {
		return this.jcb;
	}

}
