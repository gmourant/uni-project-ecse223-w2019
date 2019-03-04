package ca.mcgill.ecse223.block.view;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;

/**
 * PageMoveBlock: UI for move block feature
 * the user selects a block from the JComboBox and writes it's new position
 * @author Sabrina
 *
 */
public class PageMoveBlock extends ContentPage {

	JComboBox<String> selectBlockList;// drop down menu
	JTextField selectFinalPositionX ; //write new position in X
	JTextField selectFinalPositionY ; //write new position in Y
	JLabel errorMessage; 			// error message label
	JButton moveBlockButton;		// button
	
	// data elements
	private HashMap<Integer, TOGridCell> selectBlock;  // represents index
	private int level;
	private String error = null;
	int finalPositionX;
	int finalPositionY;


	public PageMoveBlock(Block223MainPage frame, int aLevel){ //, int aLevel
		super(frame);
		setLayout(new GridLayout(7,1));
		add(createHeader("Move block"));
		level = aLevel;

		// move block
		//select initial block

		JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
		b.setBackground(this.getBackground());
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		b.add(new JLabel("Select block to move"));
		selectBlockList = createComboBox(); 
		b.add(selectBlockList);
		add(b);

		// JPanel for my horizontal position
		JPanel positionBoxX = new JPanel(new FlowLayout(FlowLayout.LEFT));
		positionBoxX.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(3, 0, 0, 10)));
		positionBoxX.setBackground(this.getBackground()); 
		positionBoxX.add(new JLabel("New horizontal position")); 
		add(positionBoxX);
		selectFinalPositionX = new JTextField(); //write new position in X
		add(selectFinalPositionX); //positionBoxX.
		
		// JPanel for my vertical position
		JPanel positionBoxY = new JPanel(new FlowLayout(FlowLayout.LEFT));
		positionBoxY.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(3, 0, 0, 10)));
		positionBoxY.setBackground(this.getBackground()); 
		positionBoxY.add(new JLabel("New vertical position"));
		add(positionBoxY);
		selectFinalPositionY = new JTextField();; // write new position in Y
		add(selectFinalPositionY); //positionBoxY.
		

		// JPanel for my button 
		JPanel button = new JPanel(new FlowLayout(FlowLayout.LEFT));
		button.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(3, 0, 0, 10)));
		button.setBackground(this.getBackground());
		moveBlockButton = createButton("Move");
		JButton cancelButton = createButton("Cancel");
		button.add(moveBlockButton);
		button.add(cancelButton);
		add(button);

//		// move block elements
//		selectBlockList = new JComboBox<String>();
//		selectFinalPositionX  = new JTextField();
//		selectFinalPositionY  = new JTextField();

		// adding action listeners to the select button to confirm choices
		moveBlockButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveBlockButtonActionPerformed(evt);
			}
		});
		
		//Cancel button listener
		cancelButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent evt) {
 				cancel();}
     });
	} // end of PageMoveBlock


	public void refreshData() {
		selectFinalPositionX.setText("");
		selectFinalPositionY.setText("");

		selectBlock = new HashMap<Integer, TOGridCell>();
		selectBlockList.removeAllItems(); // jcombobox
		int index = 0;
		try {
			for (TOGridCell cell : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level)) {
				selectBlock.put(index, cell);
				selectBlockList.addItem("R: " + cell.getRed() + "G: " + cell.getGreen() + "B: " + cell.getBlue() + "X: " + cell.getGridHorizontalPosition()
				+ "Y: " + cell.getGridVerticalPosition());
				index++;
			}
			 
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		selectBlockList.setSelectedIndex(-1);

	}
	private void moveBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		errorMessage.setText(error);
		int chosenBlock = selectBlockList.getSelectedIndex();
		if (chosenBlock < 0) {
			error = "No blocks have been chosen";
		//add(new JLabel("No blocks have been chosen"));
		}
		try {
			finalPositionX = Integer.parseInt(selectFinalPositionX.getText());
		}
		catch (NumberFormatException e) {
			error = "Needs to be a numerical value";
			//add(new JLabel("Needs to be a numerical value"));
		}
		try {
			finalPositionY = Integer.parseInt(selectFinalPositionY.getText());
		}
		catch (NumberFormatException e) {
			error = "Needs to be a numerical value!";
			//add(new JLabel("Needs to be a numerical value!"));
		}
		if (error.length() == 0) {
			// call the controller
			try {
				TOGridCell selectedCell = selectBlock.get(chosenBlock);
				Block223Controller.moveBlock(level, selectedCell.getGridHorizontalPosition(), selectedCell.getGridVerticalPosition(),
						finalPositionX, finalPositionY);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData(); // update visuals
	}// end of refreshData
} // end of class