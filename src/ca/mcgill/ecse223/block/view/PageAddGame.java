package ca.mcgill.ecse223.block.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

/**
 * This page adds a game to Block223.
 * @author Kelly Ma
 */
public class PageAddGame extends ContentPage {

	private static final long serialVersionUID = 4199681302846123272L;
	
	public PageAddGame(Block223MainPage parent){
		
		// Inherit features from ContentPage
	    super(parent);
	    setLayout(new GridLayout(7,1));
        add(createHeader("Add a Game"));
        
        // Add a new Game
        add(new JLabel("Please enter the name of a new game:")); // Prompts user
        RandomNameGenerator rgen = new RandomNameGenerator(); // Offers name suggestions
        JTextField newGame = new JTextField("How about... " + rgen.generateName() + "?");
        add(newGame);
        
        // AddGame and Cancel buttons
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitButtons.setBackground(this.getBackground());
        JButton addGame = createButton("Add Game");
        JButton cancel = createButton("Cancel");
        exitButtons.add(addGame);
        exitButtons.add(cancel);
        add(exitButtons);
        
        // Listener for Cancel button
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cancel(); // Returns to the admin menu
            }
        });
        
        // Listener for addGame button
        addGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
            	String gameName = newGame.getText(); // Retrieves user input
                try{
                    Block223Controller.createGame(gameName);
                }
                catch(InvalidInputException e){
                    displayError(e.getMessage());
                    return;
                }
            }
        });
	}
	
}
