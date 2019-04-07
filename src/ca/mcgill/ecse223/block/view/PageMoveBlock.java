package ca.mcgill.ecse223.block.view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @author Sabrina Chan
 * @author Imane Chafi
 * @author Mathieu Bissonnette
 *
 */
public class PageMoveBlock extends ContentPage {
	
	private static final String Regex = "\\d+";
	private static final Pattern pattern = Pattern.compile(Regex);
	
	private String error = "";

	public PageMoveBlock(Block223MainPage parent, int legacy) {
		super(parent);
		
		ViewTheme theme = parent.currentTheme;
		
		setLayout(new GridLayout(6,1));
		
		//Header
	    add(createHeader("Move a Block"));
        
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
        JPanel currentCoordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        currentCoordPanel.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                 BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        JLabel currentCoordLabel = new JLabel("Current X,Y : ");
        currentCoordPanel.add(currentCoordLabel);
        JTextField currentCoordTextField = new JTextField();
        currentCoordTextField.setPreferredSize(new Dimension(200, 30));
        //coordTextField.setBorder(border);
        currentCoordPanel.add(currentCoordTextField);
        currentCoordPanel.setBackground(this.getBackground());
        add(currentCoordPanel);
        
        //Coordinates panel
        JPanel newCoordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newCoordPanel.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                 BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        JLabel newCoordLabel = new JLabel("New X,Y : ");
        newCoordPanel.add(newCoordLabel);
        JTextField newCoordTextField = new JTextField();
        newCoordTextField.setPreferredSize(new Dimension(200, 30));
        //coordTextField.setBorder(border);
        newCoordPanel.add(newCoordTextField);
        newCoordPanel.setBackground(this.getBackground());
        add(newCoordPanel);
        
        //View button
        JButton viewButton = createButton("Level view");
        viewButton.setPreferredSize(new Dimension(200, 20));
        add(viewButton);
        

        //Button Panels
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitButtons.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                    BorderFactory.createEmptyBorder(1, 0, 0, 2)));
        exitButtons.setBackground(this.getBackground());
        JButton moveButton = createButton("Move Block");
        JButton cancelButton = createButton("Cancel");
        exitButtons.add(moveButton);
        exitButtons.add(cancelButton);
        add(exitButtons);
        
        // addButton and CancelButton listeners
        moveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				// Parse the coordinate textField.
				String currentCoord = currentCoordTextField.getText();
				Integer currentX = 0;
				Integer currentY = 0;
				String newCoord = newCoordTextField.getText();
				Integer newX = 0;
				Integer newY = 0;
				Matcher matcher = null;
				
				try {
					matcher = pattern.matcher(currentCoord);
					matcher.find();
					currentX = Integer.parseInt(matcher.group());
					matcher.find();
					currentY = Integer.parseInt(matcher.group());
				} catch(NumberFormatException e) {
					error = "The coordinate numeric values must have a valid format (i.e. 12,34).";
					new ViewError(error, false, parent);
				} catch(IllegalStateException e) {
					error = "Could not match coordinates.";
					new ViewError(error, false, parent);
				}
				
				try {
					matcher = pattern.matcher(newCoord);
					matcher.find();
					newX = Integer.parseInt(matcher.group());
					matcher.find();
					newY = Integer.parseInt(matcher.group());
				} catch(NumberFormatException e) {
					error = "The coordinate numeric values must have a valid format (i.e. 12,34).";
					new ViewError(error, false, parent);
				} catch(IllegalStateException e) {
					error = "Could not match coordinates.";
					new ViewError(error, false, parent);
				}
				
				// Call the controller.
				try {
					Block223Controller.moveBlock(
								(int) levelSelector.getSelectedItem(),
								currentX,
								currentY,
								newX,
								newY
							);
				} catch (InvalidInputException e) {
					error = e.getMessage();
					if (error.equals("A game must be selected to move a block.")
							|| error.equals("Admin privileges are required to move a block.")
							|| error.equals("Only the admin who created the game can move a block.")) {
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
        
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		cancel();
        	}
        });
        
       //Side menu editing
       //JList sideMenu = getSideMenuList();
       //add(sideMenu);

	}
	
}