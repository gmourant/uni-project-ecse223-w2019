package ca.mcgill.ecse223.block.view;
import static ca.mcgill.ecse223.block.view.Block223MainPage.TITLE_SIZE_INCREASE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import ca.mcgill.ecse223.block.view.PagePositionBlock.LevelView;
import ca.mcgill.ecse223.block.view.PagePositionBlock.LevelView.CellPane;
import ca.mcgill.ecse223.block.view.PagePositionBlock.LevelView.Grid;

/**
 * PageMoveBlock: UI for move block feature
 * the user selects a block from the JComboBox and writes it's new position
 * @author Sabrina Chan
 * @author Imane Chafi
 *
 */
public class PageMoveBlock extends ContentPage {

	JComboBox<Integer> selectBlockList;// drop down menu
	JComboBox<Integer> selectLevelList;
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
		setLayout(new GridLayout(6,1));
		add(createHeader("Move block"));
		level = aLevel;

	//****************************
    //Move block panels
    //****************************	

		//Select level to move a block from
		JPanel selectLevel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		selectLevel.setBackground(this.getBackground());
		selectLevel.add(new JLabel("                 Select a level: "));
		selectLevelList = new JComboBox<Integer>();
		selectLevelList.setPreferredSize(new Dimension(150, 30));
		//selectLevelList.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectLevelList.setBackground(Block223MainPage.getButtonBackground());
		selectLevelList.setForeground(Color.DARK_GRAY);
		// Populate Level Combobox
        for (Integer i = 1; i < 100; i++) {
        	selectLevelList.addItem(i);
        }
        selectLevel.add(selectLevelList);
        selectLevel.setBackground(this.getBackground());
		add(selectLevel);
		selectLevel.add(selectLevelList);
		
		//select initial block JPanel
		JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
		b.setBackground(this.getBackground());
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		b.add(new JLabel("Select a block ID to move: "));
		selectBlockList = new JComboBox<Integer>();
		selectBlockList.setPreferredSize(new Dimension(150, 30));
		selectBlockList.setBackground(Block223MainPage.getButtonBackground());
		b.add(selectBlockList);
		add(b);

		// JPanel for my horizontal position
		JPanel positionBoxX = new JPanel(new FlowLayout(FlowLayout.LEFT));
		positionBoxX.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(3, 0, 0, 10)));
		positionBoxX.setBackground(this.getBackground()); 
		positionBoxX.add(new JLabel("          New X position: ")); 
		add(positionBoxX);
		selectFinalPositionX = new JTextField(); //write new position in X
		selectFinalPositionX.setPreferredSize(new Dimension(150, 23));
		positionBoxX.add(selectFinalPositionX); //positionBoxX.
		
		// JPanel for my vertical position
		JPanel positionBoxY = new JPanel(new FlowLayout(FlowLayout.LEFT));
		positionBoxY.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(3, 0, 0, 10)));
		positionBoxY.setBackground(this.getBackground()); 
		positionBoxY.add(new JLabel("          New Y position: "));
		add(positionBoxY);
		selectFinalPositionY = new JTextField();; // write new position in Y
		selectFinalPositionY.setPreferredSize(new Dimension(150, 23));
		positionBoxY.add(selectFinalPositionY); //positionBoxY.
		

		// JPanel for my buttons 
		JPanel button = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		button.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(3, 0, 0, 10)));
		button.setBackground(this.getBackground());
		moveBlockButton = createButton("Move");
		JButton cancelButton = createButton("Cancel");
		//View button @author Mathieu Bissonnette
        JButton viewButton = createButton("Level view");
        viewButton.setBackground(new Color(255 + (255 - 255)*5/8, 204 + (255 - 204)*5/8, 204 + (255 - 204)*5/8));
        viewButton.setPreferredSize(new Dimension(130,50));
        //viewButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        button.add(viewButton);
		button.add(moveBlockButton);
		button.add(cancelButton);
		add(button);
		
	//****************************
    //Action Listeners
    //****************************	

		//Action listeners to the select button to confirm choices
		moveBlockButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// call the controller
				int chosenBlock = (int) selectBlockList.getSelectedItem();
				if (chosenBlock < 0) {
					displayError("No blocks have been chosen", false);
				//add(new JLabel("No blocks have been chosen"));
				}
				try {
					finalPositionX = Integer.parseInt(selectFinalPositionX.getText());
				}
				catch (NumberFormatException e) {
					displayError("X position needs to be a numerical value", false);
					//add(new JLabel("Needs to be a numerical value"));
				}
				try {
					finalPositionY = Integer.parseInt(selectFinalPositionY.getText());
				}
				catch (NumberFormatException e) {
					displayError("Y position needs to be a numerical value", false);
					//error = "Needs to be a numerical value!";
					//add(new JLabel("Needs to be a numerical value!"));
				}
					try {
						List<TOGridCell> blocks = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame((int)selectLevelList.getSelectedItem());
						for(TOGridCell block : blocks) {
						Block223Controller.moveBlock((int)selectLevelList.getSelectedItem(), block.getGridHorizontalPosition(), block.getGridVerticalPosition(), finalPositionX, finalPositionY);
						}		
					} catch (InvalidInputException e) {
						displayError(e.getMessage(), false);
					}
				}
			
		});
		
		//Cancel button listener
		cancelButton.addActionListener(new ActionListener() {

 			public void actionPerformed(ActionEvent evt) {
 				cancel();}
     });
		//List adding blockIDs
        java.util.List<TOBlock> blocks;
        
        try{
        	blocks = Block223Controller.getBlocksOfCurrentDesignableGame();
        }
        catch(InvalidInputException e){
            displayError("A game must be selected to move a block.", true);
            // stop now to prevent future errors based on this exception
            return;
        }
        // add this list
        for(TOBlock block : blocks){
        	selectBlockList.addItem(block.getId());
        }
        
        // add dropdown to panel
        b.add(selectBlockList);
        
        //ViewButton listener  
        viewButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		
        		// Get the block assignments of the current level.
        		
        		List<TOGridCell> assignments = new ArrayList<TOGridCell>();
        		try {
        			assignments = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame((Integer)selectLevelList.getSelectedItem());
        			new LevelView(assignments, false,frame);
        		} catch (InvalidInputException e) {
        			error = e.getMessage();
					new ViewError(error, false, frame);
        		}
        		
        	}
        });
	} // end of PageMoveBlock

	//****************************
    //Refresh Data method
    //****************************	

	/*public void refreshData() {
		selectFinalPositionX.setText("");
		selectFinalPositionY.setText("");

		selectBlock = new HashMap<Integer, TOGridCell>();
		selectBlockList.removeAllItems(); // jcombobox
		int index = 0;
		try {
			for (TOGridCell cell : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level)) {
				selectBlock.put(index, cell);
				//selectBlockList.addItem("R: " + cell.getRed() + "G: " + cell.getGreen() + "B: " + cell.getBlue() + "X: " + cell.getGridHorizontalPosition()
				//+ "Y: " + cell.getGridVerticalPosition());
				index++;
			}
			 
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		selectBlockList.setSelectedIndex(-1);

	}*/
	
	//****************************
    //Move block button
    //****************************	
	private void moveBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		errorMessage.setText(error);
		
		//refreshData(); // update visuals
	}// end of refreshData
	
	//****************************
    //Grid cell method
    //****************************	

	private TOGridCell coordInList(int x, int y, List<TOGridCell> list) {
		for (TOGridCell cell : list) {
			if (x == cell.getGridHorizontalPosition() && y == cell.getGridVerticalPosition()) {
				return cell;
			}
		}
		return null;
	}
	
/**
 *  This class creates a new level grid view to visualize the designed levels.
 *  
 *  @author Georges Mourant modified by @author Mathieu Bissonnette
 *  
 */

public class LevelView extends JFrame{

    final Color HEADER_BACKGROUND = 
            new Color(255 + (255 - 255)*5/8, 204 + (255 - 204)*5/8, 204 + (255 - 204)*5/8);
    
    private final Block223MainPage framework;
    private final boolean errorRedirect;
    private final JPanel windowHolder;
    private JPanel topMenu;
    private JButton exit;
    
    public LevelView(List<TOGridCell> assignments, boolean errorRedirect,  Block223MainPage parent){
        framework = parent;
        this.errorRedirect = errorRedirect;
        this.setSize(900, 600); // Specifies the size should adjust to the needs for space
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specifies what the X to close does
        this.setLocationRelativeTo(null); // Places in the center of the screen
        this.setResizable(false); // stops user from resizing the dialog box
        this.setUndecorated(true);
        this.setVisible(true);
        windowHolder = new JPanel(new BorderLayout());
        windowHolder.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        windowHolder.add(new Grid(assignments));
        setupTopMenu();
        add(windowHolder);
    }
    
    class Grid extends JPanel {
    	
    	Grid(List<TOGridCell> assignments) {
        	
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    CellPane cellPane = new CellPane();
                    Border border = null;
                    if (row < 7) {
                        if (col < 7) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < 7) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                	TOGridCell cell = coordInList(col, row, assignments);
                	if (cell != null)
                		cellPane.setBackground(new Color(cell.getRed(), cell.getGreen(), cell.getBlue()));
                    cellPane.setBorder(border);
                    add(cellPane, gbc);
                }
            }
        }
    }

    class CellPane extends JPanel {

        private Color defaultBackground;
    
    }
    
    /**
     * This method initalises all the information for the top menu.
     * @author Georges Mourant
     */
    private void setupTopMenu() {
        topMenu = new JPanel(new GridLayout(1, 2));
        topMenu.setBorder(BorderFactory.createCompoundBorder(topMenu.getBorder(), 
                BorderFactory.createEmptyBorder(5, 10, 5, 5)));
        topMenu.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/4));
        topMenu.setBackground(HEADER_BACKGROUND);

        JLabel title = new JLabel("View (W.I.P.)"); // empty by default
        title.setFont(new Font(Block223MainPage.getUIFont().getFamily(), Font.BOLD, 
                Block223MainPage.getUIFont().getSize() + TITLE_SIZE_INCREASE));
        topMenu.add(title);

        JPanel exitMin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitMin.setBackground(topMenu.getBackground()); // match to background
        exit = createButton("X");
        exit.setBackground(exitMin.getBackground()); // match to background
        exitMin.add(exit);
        topMenu.add(exitMin);

        windowHolder.add(topMenu, BorderLayout.NORTH);
        
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBorder(BorderFactory.createCompoundBorder(holder.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        holder.setBackground(Color.WHITE);
        holder.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()*3/4));
        
        windowHolder.add(holder, BorderLayout.CENTER);
        
        exit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(errorRedirect)
                        framework.changePage(Block223MainPage.Page.adminMenu);
                    dispose(); // quit program
                }
        });
    }
}

}//End of class PageMoveBlock
