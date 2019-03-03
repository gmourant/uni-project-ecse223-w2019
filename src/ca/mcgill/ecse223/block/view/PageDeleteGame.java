
package ca.mcgill.ecse223.block.view;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import ca.mcgill.ecse223.block.controller.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * The page for deleting the game.
 * @author Georges Mourant
 */
public class PageDeleteGame extends ContentPage {
    
    public PageDeleteGame(Block223MainPage parent){
        super(parent);
        
        // set layout of this panel
        // 7 columns, 1 row
        setLayout(new GridLayout(7,1));
        add(createHeader("Delete a Game")); // add content to panel
        add(new JLabel("Select a Game:"));
        
        // create panel to hold dropdown
        //      (this is needed to prevent dropdown from consuming all available space)
        JPanel b = new JPanel();
        b.setBackground(this.getBackground()); // set to white background
        
        // create game dropdown
        JComboBox<String> gameList = createComboBox();
        
        // get list of games as Strings
        List<TOGame> games;
        try{
            games = Block223Controller.getDesignableGames();
        }
        catch(InvalidInputException e){
            displayError(e.getMessage(), true);
            // stop now to prevent future errors based on this exception
            return;
        }
        // add this list
        for(TOGame game : games){
            gameList.addItem(game.getName());
        }
        
        // add dropdown to panel
        b.add(gameList);
        
        // add panel to page
        add(b);
        
        add(new JLabel("This action is not reverable.")); // add text to page
        add(new JLabel("Are you sure you want to delete this game?"));
        
        // add checkbox to page
        JCheckBox question = createCheckBox("I am certain I want to delete this game.");
        add(question);
        
        // create and add exit buttons
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // holder panel
        exitButtons.setBackground(this.getBackground()); // set to white background
        JButton delete = createButton("Delete");
        JButton cancel = createButton("Cancel");
        exitButtons.add(delete); // add buttons to holder panel
        exitButtons.add(cancel);
        
        add(exitButtons); // add holder panel to page
        
        // listeners
        cancel.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    cancel();
                }
        });
    }
}
