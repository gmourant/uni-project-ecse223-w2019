package ca.mcgill.ecse223.block.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * The page for displaying the admin menu.
 * @author Georges Mourant
 */
public class PageAdminMenu extends ContentPage {

    JButton addGame, defineGame,
            deleteGame, updateGame,
            addBlock, deleteBlock,
            updateBlock, positionBlock,
            moveBlock, removeBlock;

    public PageAdminMenu(Block223MainPage frame) {
        super(frame);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel l = createHeader("Welcome!", Component.CENTER_ALIGNMENT, true);
        add(l);
        
        addGame = createButton("Add a Game");
        defineGame = createButton("Define a Game's Settings");
        deleteGame = createButton("Delete a Game");
        updateGame = createButton("Update a Game");
        addBlock = createButton("Add a Block to a Game");
        deleteBlock = createButton("Delete a Block from a Game");
        updateBlock = createButton("Update a Block");
        positionBlock = createButton("Position a Block");
        moveBlock = createButton("Move a Block");
        removeBlock = createButton("Remove a Block from a Level");
        add(addGame);
        add(defineGame);
        add(deleteGame);
        add(updateGame);
        add(addBlock);
        add(deleteBlock);
        add(updateBlock);
        add(positionBlock);
        add(moveBlock);
        add(removeBlock);
        
        addGame.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.addGame);
                }
        });
        defineGame.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.defineGame);
                }
        });
        deleteGame.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.deleteGame);
                }
        });
        updateGame.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.updateGame);
                }
        });
        addBlock.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.addBlock);
                }
        });
        deleteBlock.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.deleteBlock);
                }
        });
        updateBlock.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.updateBlock);
                }
        });
        positionBlock.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.positionBlock);
                }
        });
        moveBlock.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.moveBlock);
                }
        });
        removeBlock.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    changePage(Block223MainPage.Page.removeBlock);
                }
        });
    }
    
    public static JButton createButton(String text){
        JButton b = ContentPage.createButton(text);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        return b;
    }
}
