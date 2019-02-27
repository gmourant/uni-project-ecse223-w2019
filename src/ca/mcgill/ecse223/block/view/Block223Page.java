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
// TODO UI will be implemented here
public class Block223Page extends JFrame {
	//UI Elements
  private JLabel errorMessage;
  // Update block
	private jButton updateButton;
  // addBlock
	private JTextField idTextField;
	private JLabel idLabel;
	private JButton addBlockButton;
	private JSlider redSlider;
	private JSlider blueSlider;
	private JSlider greenSlider;
	private JSlider nbrePointSlider;
	private JLabel redLabel;
	private JLabel blueLabel;
	private JLabel greenLabel;
	private JLabel nbrePointLabel;
	private JButton cancelButton;
	//deleteBlock
	private JComboBox<Integer> IdList;
	private JButton deleteBlockButton;
	private JLabel deleteLabel;
  
	//data elements
	private String error = null;
	//Available IDs for ComboBox
	private HashMap<Integer, Integer> ids;
  
  /** Creates new form Block223Page */
	public Block223Page() {
		initComponents();
		refreshData();
	}
  //Refresh methods and creating new from block223Page
	/** This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {
    // elements for error message
				errorMessage = new JLabel();
				errorMessage.setForeground(Color.RED);
				
		//elements for adding a block
				idTextField = new JTextField();
				idLabel = new JLabel();
				idLabel.setText("ID :");
				addBlockButton = new JButton();
				addBlockButton.setText("Add Block");
				redSlider = new JSlider(0, 255, 1);
				blueSlider = new JSlider(0, 255, 1);
				greenSlider = new JSlider(0, 255, 1);
				nbrePointSlider = new JSlider(1, 1000, 1);
				redLabel = new JLabel();
				redLabel.setText("RED :");
				redLabel.setForeground(Color.RED);
				blueLabel = new JLabel();
				blueLabel.setText("BLUE :");
				blueLabel.setForeground(Color.BLUE);
				greenLabel = new JLabel();
				greenLabel.setText("GREEN :");
				greenLabel.setForeground(Color.GREEN);
				nbrePointLabel = new JLabel();
				nbrePointLabel.setText("Number of Points :");
				cancelButton = new JButton();
				cancelButton.setText("Cancel");
				
		//elements for deleting a block
				deleteBlockButton = new JButton();
				deleteBlockButton.setText("Delete");
				deleteLabel = new JLabel();
				deleteLabel.setText("Choose the ID of the block to be deleted :");
				IdList = new JComboBox<Integer>(new Integer[0]);
    
		// TODO Elements for creating a block assignment.
			updateButton = new JButton();
			updateButton.setText("Update");
			
		// TODO Listeners for creating a block assignment.
			updateButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					updateButtonActionPerformed(evt);
				}
			});
    //Listeners for adding a block
				//Do we need listeners for the JSliders?or the combo box?
				addBlockButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						addBlockButtonActionPerformed(evt);
					}
				});
				cancelButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						cancelButtonActionPerformed(evt);
					}
				});
		//Listeners for deleting a block
				deleteBlockButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						deleteBlockButtonActionPerformed(evt);
					}
				});
    // horizontal line elements
			JSeparator horizontalLineTop = new JSeparator();
			JSeparator horizontalLineMiddle = new JSeparator();
			JSeparator horizontalLineBottom = new JSeparator();
	   // layout
			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
				layout.setHorizontalGroup(
					layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
								.addComponent(errorMessage)
							.addComponent(horizontalLineTop)
							.addComponent(horizontalLineMiddle)
							.addComponent(horizontalLineBottom)
							.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
											.addComponent(idLabel)
											.addComponent(redLabel)
											.addComponent(greenLabel)
											.addComponent(blueLabel)
											.addComponent(nbrePointLabel)
											.addComponent(addBlockButton)))
									.addGroup(layout.createParallelGroup()
											.addComponent(idTextField, 200, 200, 400)
											.addComponent(redSlider)
											.addComponent(greenSlider)
											.addComponent(blueSlider)
											.addComponent(nbrePointSlider)
											.addComponent(cancelButton))
									)
									);
					layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {idLabel, redLabel, greenLabel, blueLabel, nbrePointLabel});
					layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {redSlider, greenSlider, blueSlider, nbrePointSlider});
					layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {idLabel, idTextField});
					layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
							{redLabel, redSlider});
					layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
							{blueLabel, blueSlider});
					layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
							{greenLabel, greenSlider});
					layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
							{nbrePointLabel, nbrePointSlider});
					layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
							{addBlockButton, cancelButton});
					
					layout.setVerticalGroup(
							layout.createParallelGroup()
							.addGroup(layout.createSequentialGroup()
									.addComponent(errorMessage)
									.addGroup(layout.createParallelGroup()
											.addComponent(idTextField)
											.addComponent(idLabel))
									.addGroup(layout.createParallelGroup()
											.addComponent(redLabel)
											.addComponent(redSlider))
									.addGroup(layout.createParallelGroup()
											.addComponent(blueLabel)
											.addComponent(blueSlider))
									.addGroup(layout.createParallelGroup()
											.addComponent(greenLabel)
											.addComponent(greenSlider))
									.addGroup(layout.createParallelGroup()
											.addComponent(nbrePointLabel)
											.addComponent(nbrePointSlider))
									.addGroup(layout.createParallelGroup()
											.addComponent(addBlockButton)
											.addComponent(cancelButton))
						
									));
					//I also need to add the groups for delete block from game
							
							pack();		
	}
	private void refreshData() {
		// error
				errorMessage.setText(error);
				if (error == null || error.length() == 0) {
					// populate page with data
					
					// add a block
					idTextField.setText("");
					}
					//delete a block (how to implement scrollbars?)
			
				//How should I implement the added JSlider in the refresh data to show up in the
				//Image of a rectangle?//
				
				//pack();
	}
	private void updateButtonActionPerformed(ActionEvent evt) { // TODO
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
//add a block
	private void addBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;
		
		// call the controller
		try {
			Block223Controller.addBlock(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), nbrePointSlider.getValue());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
	//delete a block 
	private void deleteBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;
		
		// call the controller
		try {
			Block223Controller.deleteBlock(IdList.getSelectedIndex());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
	private void cancelButtonActionPerformed(ActionEvent evt) {
		// clear error message
				error = null;
				
				// call the controller
				try {
					Block223Controller.saveGame();//Goes back to main page
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
				
				// update visuals
				refreshData();
		
	}

}
