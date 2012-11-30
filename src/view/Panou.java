package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.*;

public class Panou extends JPanel {

	public Panou(String s, int x, int y, int w, int h) {
		this.setBorder(new LineBorder(new Color(0, 100, 0), 2));
		this.setBounds(x, y, w, h);
		this.setLayout(null);

		JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setHorizontalScrollBar(null);
		JPanel jPanel1 = new javax.swing.JPanel();
		JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
		JTextArea jTextArea1 = new javax.swing.JTextArea();
		JComboBox jComboBox1 = new javax.swing.JComboBox();

		jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		jPanel1.setBackground(new Color(255,255,180));

		jTextArea1.setBackground(Color.WHITE);
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jTextArea1.setText(s);
		jScrollPane2.setViewportView(jTextArea1);

		jComboBox1.setBackground(new java.awt.Color(170, 170, 255));
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"p", "t", "s", "a" }));
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												580,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												0, Short.MAX_VALUE)
										.addComponent(
												jComboBox1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298,
				Short.MAX_VALUE).addGroup(
				jPanel1Layout.createSequentialGroup().addGap(5, 5, 5)
						.addComponent(jComboBox1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(144, Short.MAX_VALUE)));

		jScrollPane1.setViewportView(jPanel1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 35,
				h));
	}
}
