
package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public abstract class ContentPage extends JPanel{
    private final Block223MainPage framework;
    
    public ContentPage(Block223MainPage framework){
        this.framework = framework;
        setBackground(Color.WHITE);
    }
    
    /**
     * This method changes the page to Block223MainPage.Page.adminMenu.
     * Author: Georges Mourant
     */
    public void cancel(){
        framework.changePage(Block223MainPage.Page.adminMenu);
    }
    
    /**
     * This method changes the page using a Block223MainPage.Page enum.
     * Author: Georges Mourant
     * @param page the desired page
     */
    public void changePage(Block223MainPage.Page page){
        framework.changePage(page);
    }
    
    /**
     * Creates a formatted button calling it's Block223MainPage sister method.
     * Author: Georges Mourant
     * @param text String for the button
     * @return the formatted JButton
     */
    public static JButton createButton(String text){
        return Block223MainPage.createButton(text);
    }
    
    /**
     * Returns the JList object which is displayed on the side menu.
     * For editing the side menu. Author: Georges Mourant
     * @return the JList object of items on the side menu
     */
    public JList getSideMenuList(){
        return framework.getSideMenuItems();
    }
}