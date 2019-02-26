package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.Block223Controller;
// UI will be implemented here
public class Block223Page extends JFrame {
	// Update block
	private jButton updateButton;
	//data elements
	private String error = null;
	
	private void initComponents() {
		// Elements for updating a block.
			updateButton = new JButton();
			updateButton.setText("Update");
			
		// Listeners for updating a block.
			updateButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					updateButtonActionPerformed(evt);
				}
			});
	}
	
	private void updateButtonActionPerformed(ActionEvent evt) {
		// clear error message
				error = null;
				
				// call the controller
				try {
					// Update the selected block with the specified values.
					Block223Controller.updateBlock(IdList.getSelectedItem(), redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), nbrePointSlider.getValue());
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
				
				// update visuals
				refreshData();
		
	}

}
