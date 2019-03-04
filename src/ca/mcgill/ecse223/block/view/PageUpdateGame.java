package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

/**
 * This page updates the selected game within Block223.
 * @author Kelly Ma
 * @author Georges Mourant
 */
public class PageUpdateGame extends ContentPage {

	private static final long serialVersionUID = 3028089796932575154L;

	public PageUpdateGame(Block223MainPage parent) {
		
		// Inherit features from ContentPage
	    super(parent);
	    setLayout(new GridLayout(7,1));
        add(createHeader("Update a Game"));
        
        // Obtain list of games as Strings
        List<TOGame> games;
        try{
            games = Block223Controller.getDesignableGames();
        }
        catch(InvalidInputException e){
            displayError(e.getMessage(), false);
            return;
        }
        
        // Set game as "selected game"
        add(new JLabel("Please select a game to update:")); // Prompts user
        JPanel panel = new JPanel();
	        panel.setBackground(this.getBackground());
	        JComboBox<String> gamesMenu = new JComboBox<String>(); // Create a dropdown box of games
	        gamesMenu.setPreferredSize(new Dimension(150, 30));
	        gamesMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
	        gamesMenu.setBackground(Block223MainPage.HEADER_BACKGROUND);
	        gamesMenu.setForeground(Color.DARK_GRAY);
        
        // Get menu items from transfer object
        for(TOGame game : games){ 
            gamesMenu.addItem(game.getName());
        }
        
        // Add to screen
        panel.add(gamesMenu);
        add(panel);
        
        // Create Edit and Cancel buttons
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // holder panel
        exitButtons.setBackground(this.getBackground()); // set to white background
        JButton edit = createButton("Edit");
        JButton cancel = createButton("Cancel");
        exitButtons.add(edit); // add buttons to holder panel
        exitButtons.add(cancel);
        add(exitButtons);
        
        // Listener for Edit button
        edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
            	String gameName = (String) gamesMenu.getSelectedItem(); // Retrieves user input
                try{
                    Block223Controller.selectGame(gameName);
                    changePage(Block223MainPage.Page.defineGame);
                }
                catch(InvalidInputException e){
                    displayError(e.getMessage(), false);
                    return;
                }
            }
        });
        
        // Listener for Cancel button
        cancel.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    cancel();
                }
        });
        
	}

}
