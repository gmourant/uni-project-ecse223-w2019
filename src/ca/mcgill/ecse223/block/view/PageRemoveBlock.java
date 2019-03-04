package ca.mcgill.ecse223.block.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;

/**
 * PageRemoveBlock: UI for the remove block feature
 * the user selects a block from a JComboBox and clicks the button to remove it
 * @author Sabrina
 *
 */
public class PageRemoveBlock extends ContentPage {

	JComboBox<String> removeBlockList; 
	JLabel errorMessage; 
	JButton removeBlockButton;
	private HashMap<Integer, TOGridCell> aRemoveBlock;
	

	// data elements 
	private HashMap<Integer, TOGridCell> selectBlock;  // represents index
	private int level;
	private String error = null;


	public PageRemoveBlock(Block223MainPage frame, int aLevel){
		super(frame);
		setLayout(new GridLayout(7,1));
		add(createHeader("Remove Block"));
		level = aLevel;

		JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
		b.setBackground(this.getBackground());
		// ERROR LABEL
		b.add(new JLabel("Select block to remove"));
		removeBlockList = createComboBox(); //(imane has the code)
		b.add(removeBlockList);
		add(b);

		// JPanel for my button 
		JPanel button = new JPanel(new FlowLayout(FlowLayout.LEFT));
		button.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(3, 0, 0, 10)));
		button.setBackground(this.getBackground());
		removeBlockButton = createButton("Delete");
		JButton cancel = createButton("Cancel");
		button.add(removeBlockButton);
		button.add(cancel);
		add(button);

		// adding action listeners to the select button to confirm choices
		removeBlockButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeBlockButtonActionPerformed(evt);
			}
		});
		 cancel.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent evt) {
 				cancel();}
     });

	} // end of PageRemoveBlock

	public void refreshData() {
		errorMessage.setText(error);
		aRemoveBlock = new HashMap<Integer, TOGridCell>();
		removeBlockList.removeAllItems(); // jcombobox
		int index = 0;
		try {
			for (TOGridCell cell : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level)) {
				aRemoveBlock.put(index, cell);
				removeBlockList.addItem("R: " + cell.getRed() + "G: " + cell.getGreen() + "B: " + cell.getBlue() + "X: " + cell.getGridHorizontalPosition()
				+ "Y: " + cell.getGridVerticalPosition());
				index++;
			}
			// if index invalid
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		removeBlockList.setSelectedIndex(-1);
	} // end of RefreshData


	private void removeBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int chosenBlock = removeBlockList.getSelectedIndex();
		if (chosenBlock < 0) {
			error = "No blocks have been chosen";
			//add(new JLabel("No blocks have been chosen"));
		}
		if (error.length() == 0) {
			// call the controller
			try {
				TOGridCell selectedCell = selectBlock.get(chosenBlock);
				Block223Controller.removeBlock(level, selectedCell.getGridHorizontalPosition(), selectedCell.getGridVerticalPosition());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}// end of if
		refreshData(); // update visuals
	}// end of refreshData
} // end of class

