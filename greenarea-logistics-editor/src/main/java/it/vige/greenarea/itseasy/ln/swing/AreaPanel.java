/******************************************************************************
 * Vige, Home of Professional Open Source Copyright 2010, Vige, and           *
 * individual contributors by the @authors tag. See the copyright.txt in the  *
 * distribution for a full listing of individual contributors.                *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may    *
 * not use this file except in compliance with the License. You may obtain    *
 * a copy of the License at http://www.apache.org/licenses/LICENSE-2.0        *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 ******************************************************************************/
package it.vige.greenarea.itseasy.ln.swing;

import java.awt.Color;
import java.awt.Dialog;

import javax.swing.JButton;

import com.mxgraph.model.mxCell;

import it.vige.greenarea.I18N.I18N;
import it.vige.greenarea.I18N.I18NObject;
import it.vige.greenarea.dto.GeoLocation;
import it.vige.greenarea.dto.GeoLocationInterface;
import it.vige.greenarea.geo.GisService;
import it.vige.greenarea.geo.GisService.GeoCodingException;
import it.vige.greenarea.itseasy.swing.editor.LogisticsNetEditor;
import it.vige.greenarea.ln.model.LNArea;
import it.vige.greenarea.ln.model.LNArea.AreaLevel;

public class AreaPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8555005018335339747L;
	private LNArea site;
	private mxCell editingCell;
	private GisService gis = LogisticsNetEditor.getGisService();
	private LNArea.AreaLevel level;
	private I18NObject[] choices = I18N.getI18NObjects(LNArea.AreaLevel.values());

	/** Creates new form TestTabPanel */
	public AreaPanel(Dialog parent) {
		site = new LNArea();
		level = site.getAreaLevel();
		initComponents();
		areaLevelCombo.setModel(new javax.swing.DefaultComboBoxModel(choices));
		refresh();
	}

	private void refresh() {
		areaLevelCombo.getModel().setSelectedItem(getChoice(level));
		switch (level) {
		case country:
			regionLabel.setVisible(false);
			regionField.setVisible(false);
		case adminAreaLevel1:
			provinceLabel.setVisible(false);
			provinceField.setVisible(false);
		case adminAreaLevel2:
			cityLabel.setVisible(false);
			cityField.setVisible(false);
		case city:
			zipCodeLabel.setVisible(false);
			zipCodeField.setVisible(false);
		case zipCode:

		}
		switch (level) {
		case zipCode:
			zipCodeLabel.setVisible(true);
			zipCodeField.setVisible(true);
		case city:
			cityLabel.setVisible(true);
			cityField.setVisible(true);
		case adminAreaLevel2:
			provinceLabel.setVisible(true);
			provinceField.setVisible(true);
		case adminAreaLevel1:
			regionLabel.setVisible(true);
			regionField.setVisible(true);
		case country:
		}
		this.validate();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		saveConfirmDialog = new javax.swing.JDialog();
		dialogMessage = new javax.swing.JLabel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		countryLabel = new javax.swing.JLabel();
		countryField = new javax.swing.JTextField();
		regionLabel = new javax.swing.JLabel();
		regionField = new javax.swing.JTextField();
		cityLabel = new javax.swing.JLabel();
		cityField = new javax.swing.JTextField();
		saveLocationChangeButton = new javax.swing.JButton();
		provinceField = new javax.swing.JTextField();
		provinceLabel = new javax.swing.JLabel();
		zipCodeField = new javax.swing.JTextField();
		zipCodeLabel = new javax.swing.JLabel();
		areaLevelCombo = new javax.swing.JComboBox();

		saveConfirmDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		saveConfirmDialog.setAlwaysOnTop(true);
		saveConfirmDialog.setResizable(false);

		dialogMessage.setText(I18N.getString("ConfirmSaveMessage"));

		okButton.setText(I18N.getString("OK"));
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText(I18N.getString("Cancel"));
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout saveConfirmDialogLayout = new javax.swing.GroupLayout(
				saveConfirmDialog.getContentPane());
		saveConfirmDialog.getContentPane().setLayout(saveConfirmDialogLayout);
		saveConfirmDialogLayout.setHorizontalGroup(saveConfirmDialogLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(saveConfirmDialogLayout.createSequentialGroup().addContainerGap()
						.addGroup(saveConfirmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(dialogMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										saveConfirmDialogLayout.createSequentialGroup().addComponent(cancelButton)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(okButton)))
						.addContainerGap()));
		saveConfirmDialogLayout.setVerticalGroup(saveConfirmDialogLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(saveConfirmDialogLayout.createSequentialGroup().addContainerGap().addComponent(dialogMessage)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
						.addGroup(
								saveConfirmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(okButton).addComponent(cancelButton))
						.addContainerGap()));

		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				formComponentShown(evt);
			}
		});

		countryLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		countryLabel.setText(I18N.getString("Country"));
		countryLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

		countryField.setText("jTextField2");
		countryField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				countryFieldKeyTyped(evt);
			}
		});

		regionLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		regionLabel.setText(I18N.getString("Region"));
		regionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

		regionField.setText("jTextField1");
		regionField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				regionFieldKeyTyped(evt);
			}
		});

		cityLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		cityLabel.setText(I18N.getString("City"));
		cityLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

		cityField.setText("jTextField1");
		cityField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				cityFieldKeyTyped(evt);
			}
		});

		saveLocationChangeButton.setText(I18N.getString("Save"));
		saveLocationChangeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveLocationChangeButtonActionPerformed(evt);
			}
		});

		provinceField.setText("jTextField1");
		provinceField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				provinceFieldKeyTyped(evt);
			}
		});

		provinceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		provinceLabel.setText(I18N.getString("Province"));

		zipCodeField.setText("jTextField1");

		zipCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		zipCodeLabel.setText(I18N.getString("ZIP"));

		areaLevelCombo.setModel(
				new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		areaLevelCombo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				areaLevelComboActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(25, 25, 25)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(saveLocationChangeButton, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(zipCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(regionLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(provinceLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(cityLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(countryLabel))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(regionField, javax.swing.GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE)
												.addComponent(countryField, javax.swing.GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE)
												.addComponent(provinceField, javax.swing.GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE)
												.addComponent(cityField, javax.swing.GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE)
												.addComponent(zipCodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE))))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(areaLevelCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 141,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(countryField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(countryLabel).addComponent(areaLevelCombo, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(regionField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(regionLabel))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(provinceField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(provinceLabel))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cityLabel))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(zipCodeField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(zipCodeLabel))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(saveLocationChangeButton).addContainerGap(32, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void addressChangedEvent() {
		enableButton(saveLocationChangeButton);
	}

	private void countryFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_countryFieldKeyTyped
		addressChangedEvent();
	}// GEN-LAST:event_countryFieldKeyTyped

	private void regionFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_regionFieldKeyTyped
		addressChangedEvent();
	}// GEN-LAST:event_regionFieldKeyTyped

	private void cityFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cityFieldKeyTyped
		addressChangedEvent();
	}// GEN-LAST:event_cityFieldKeyTyped

	private void saveLocationChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveLocationChangeButtonActionPerformed
		doConfirmDialog();
	}// GEN-LAST:event_saveLocationChangeButtonActionPerformed

	private void doConfirmDialog() {
		GeoLocation gl = new GeoLocation(0., 0.);
		GeoLocationInterface gli = null;
		saveLocation(gl);
		try {
			gli = gis.geoCode(gl);
		} catch (GeoCodingException ex) {

		}
		if (level == LNArea.AreaLevel.zipCode && gli != null)
			gli.setZipCode(gl.getZipCode());
		saveConfirmDialog.setSize(347, 101);
		saveConfirmDialog.setVisible(true);
		this.setEnabled(false);
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		// site.loadElement((Element) editingCell.getValue());
		site = (LNArea) editingCell.getValue();
		site.setAreaLevel((AreaLevel) ((I18NObject) areaLevelCombo.getModel().getSelectedItem()).getObject());
		saveLocation(site);
		// editingCell.setValue(site.toElement());
		saveConfirmDialog.setVisible(false);
		this.setEnabled(true);
		disableButton(saveLocationChangeButton);
	}// GEN-LAST:event_okButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		saveConfirmDialog.setVisible(false);
		this.setEnabled(true);
	}// GEN-LAST:event_cancelButtonActionPerformed

	private void provinceFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_provinceFieldKeyTyped
		this.addressChangedEvent();
	}// GEN-LAST:event_provinceFieldKeyTyped

	private void areaLevelComboActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_areaLevelComboActionPerformed
		level = (AreaLevel) ((I18NObject) areaLevelCombo.getModel().getSelectedItem()).getObject();
		refresh();
	}// GEN-LAST:event_areaLevelComboActionPerformed

	private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown

	}// GEN-LAST:event_formComponentShown

	private void disableButton(JButton b) {
		b.setEnabled(false);
		b.setForeground(Color.gray);
	}

	private void enableButton(JButton b) {
		b.setEnabled(true);
		b.setForeground(Color.black);
	}

	private void displayLocation(GeoLocationInterface site) {
		countryField.setText(site.getCountry());
		regionField.setText(site.getAdminAreaLevel1());
		provinceField.setText(site.getAdminAreaLevel2());
		cityField.setText(site.getCity());
		zipCodeField.setText(site.getZipCode());
	}

	private void saveLocation(GeoLocationInterface site) {
		site.setCountry(countryField.getText());
		site.setAdminAreaLevel1(regionField.getText());
		site.setAdminAreaLevel2(provinceField.getText());
		site.setCity(cityField.getText());
		site.setZipCode(zipCodeField.getText());
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox areaLevelCombo;
	private javax.swing.JButton cancelButton;
	private javax.swing.JTextField cityField;
	private javax.swing.JLabel cityLabel;
	private javax.swing.JTextField countryField;
	private javax.swing.JLabel countryLabel;
	private javax.swing.JLabel dialogMessage;
	private javax.swing.JButton okButton;
	private javax.swing.JTextField provinceField;
	private javax.swing.JLabel provinceLabel;
	private javax.swing.JTextField regionField;
	private javax.swing.JLabel regionLabel;
	private javax.swing.JDialog saveConfirmDialog;
	private javax.swing.JButton saveLocationChangeButton;
	private javax.swing.JTextField zipCodeField;
	private javax.swing.JLabel zipCodeLabel;

	// End of variables declaration//GEN-END:variables

	void startEditing(Object cell) {
		editingCell = (mxCell) cell;
		// site.loadElement((Element) editingCell.getValue());
		site = (LNArea) editingCell.getValue();
		areaLevelCombo.getModel().setSelectedItem(getChoice(site.getAreaLevel()));
		displayLocation(site);

		disableButton(saveLocationChangeButton);
	}

	private I18NObject getChoice(AreaLevel level) {
		for (I18NObject o : choices)
			if (o.getObject().equals(level))
				return o;
		return null;
	}
}
