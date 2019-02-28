
package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public abstract class ContentPage extends JPanel{
    public ContentPage(){
        setBackground(Color.WHITE);
    }
    
    /**
     * This method changes the page to Block223MainPage.Page.adminMenu.
     * Author: Georges Mourant
     */
    public void cancel(){
        Block223MainPage.getInstance().changePage(Block223MainPage.Page.adminMenu);
    }
    
    /**
     * This method changes the page using a Block223MainPage.Page enum.
     * Author: Georges Mourant
     * @param page the desired page
     */
    public void changePage(Block223MainPage.Page page){
        Block223MainPage.getInstance().changePage(page);
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
     * Returns the JList object which is displayed on the side menu. For editing
     * the side menu. Author: Georges Mourant
     * @return the JList object of items on the side menu
     */
    public static JList getSideMenuList(){
        return Block223MainPage.getInstance().getSideMenuItems();
    }
}