
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
        setLayout(new GridLayout(7,1));
        add(createHeader("Delete a Game"));
        add(new JLabel("Select a Game:"));
        JPanel b = new JPanel();
        b.setBackground(this.getBackground());
        JComboBox<String> gameList = createComboBox();
        List<TOGame> games;
        try{
            games = Block223Controller.getDesignableGames();
        }
        catch(InvalidInputException e){
            displayError(e.getMessage());
            return;
        }
        for(TOGame game : games){
            gameList.addItem(game.getName());
        }
        gameList.addItem("Test game");
        b.add(gameList);
        add(b);
        
        add(new JLabel("This action is not reverable."));
        add(new JLabel("Are you sure you want to delete this game?"));
        JCheckBox question = createCheckBox("I am certain I want to delete this game.");
        add(question);
        
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitButtons.setBackground(this.getBackground());
        JButton delete = createButton("Delete");
        JButton cancel = createButton("Cancel");
        exitButtons.add(delete);
        exitButtons.add(cancel);
        
        add(exitButtons);
        
        cancel.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    cancel();
                }
        });
    }
}
