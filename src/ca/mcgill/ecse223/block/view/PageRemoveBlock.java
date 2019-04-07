package ca.mcgill.ecse223.block.view;

import static ca.mcgill.ecse223.block.view.Block223MainPage.TITLE_SIZE_INCREASE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;

/**
 * PageRemoveBlock: UI for the remove block feature
 * the user selects a block from a JComboBox and clicks the button to remove it
 * @author Sabrina
 *
 */
public class PageRemoveBlock extends ContentPage {
	
	private static final String Regex = "\\d+";
	private static final Pattern pattern = Pattern.compile(Regex);
	
	private String error = "";

	public PageRemoveBlock(Block223MainPage parent, int legacy) {
		super(parent);
		
		setLayout(new GridLayout(5,1));
		
		//Header
	    add(createHeader("Remove a Block from a level"));
        
        Color borderColor = new Color(207, 243, 238);
        Border border = BorderFactory.createLineBorder(borderColor, 3);

        //Level combobox
        JPanel levelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        levelPanel.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                 BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        JLabel levelLabel = new JLabel("Level : ");
        levelPanel.add(levelLabel);
        JComboBox<Integer> levelSelector = new JComboBox<Integer>();
        levelSelector.setPreferredSize(new Dimension(200, 30));
        //levelSelector.setBorder(border);
        // Populate combobox
        for (Integer i = 1; i < 100; i++) {
        	levelSelector.addItem(i);
        }
        levelPanel.add(levelSelector);
        levelPanel.setBackground(this.getBackground());
        add(levelPanel);
        
        //Coordinates panel
        JPanel coordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        coordPanel.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                 BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        JLabel coordLabel = new JLabel("X,Y : ");
        coordPanel.add(coordLabel);
        JTextField coordTextField = new JTextField();
        coordTextField.setPreferredSize(new Dimension(200, 30));
        //coordTextField.setBorder(border);
        coordPanel.add(coordTextField);
        coordPanel.setBackground(this.getBackground());
        add(coordPanel);
        
        //View button
        JButton viewButton = createButton("Level view");
        viewButton.setPreferredSize(new Dimension(200, 20));
        add(viewButton);
        

        //Button Panels
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitButtons.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                    BorderFactory.createEmptyBorder(1, 0, 0, 2)));
        exitButtons.setBackground(this.getBackground());
        JButton addButton = createButton("Remove Block");
        JButton cancelButton = createButton("Cancel");
        exitButtons.add(addButton);
        exitButtons.add(cancelButton);
        add(exitButtons);
        
        // addButton and CancelButton listeners
        addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				// Parse the coordinate textField.
				String coord = coordTextField.getText();
				Integer x = 0;
				Integer y = 0;
				Matcher matcher = null;
				
				try {
					matcher = pattern.matcher(coord);
					matcher.find();
					x = Integer.parseInt(matcher.group());
					matcher.find();
					y = Integer.parseInt(matcher.group());
				} catch(NumberFormatException e) {
					error = "The coordinate numeric values must have a valid format (i.e. 12,34).";
					new ViewError(error, false, parent);
				} catch(IllegalStateException e) {
					error = "Could not match coordinates.";
					new ViewError(error, false, parent);
				}
				
				// Call the controller.
				try {
					Block223Controller.removeBlock(
							(int)levelSelector.getSelectedItem(),
							x, y);
				} catch (InvalidInputException e) {
					error = e.getMessage();
					if (error.equals("A game must be selected to remove a block.")
							|| error.equals("Admin privileges are required to remove a block.")
							|| error.equals("Only the admin who created the game can remove a block.")) {
						new ViewError(error, true, parent);
					}
					new ViewError(error, false, parent);
				} catch (NumberFormatException e) {
					error = "The block ID must be a valid number.";
					new ViewError(error, false, parent);
				} catch (NullPointerException e) {
					error = "No block selected.";
					new ViewError(error, false, parent);
				}
				
				// update visuals
				//refreshData();
			}
			
		});
        
        // viewButton listener
        
        viewButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		
        		// Get the block assignments of the current level.
        		
        		List<TOGridCell> assignments = new ArrayList<TOGridCell>();
        		try {
        			assignments = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame((Integer)levelSelector.getSelectedItem());
        			new LevelView(assignments, false, parent);
        		} catch (InvalidInputException e) {
        			error = e.getMessage();
					new ViewError(error, false, parent);
        		}
        		
        	}
        });

	}
	
} // end of class

