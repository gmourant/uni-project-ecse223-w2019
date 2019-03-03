
package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * This class is the superclass of each individual page class.
 * It provides inherited methods which each page can use uniformly.
 */
public abstract class ContentPage extends JPanel{
    private final Block223MainPage framework; // the instance of it's parent
    
    public ContentPage(Block223MainPage framework){
        this.framework = framework;
        setBackground(Color.WHITE); // background
        // adding padding
        setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)));
    }
    
    /**
     * This method changes the page to Block223MainPage.Page.adminMenu.
     * @author Georges Mourant
     */
    public void cancel(){
        framework.changePage(Block223MainPage.Page.adminMenu);
    }
    
    /**
     * This method changes the page using a Block223MainPage.Page enum.
     * @author Georges Mourant
     * @param page the desired page
     */
    public void changePage(Block223MainPage.Page page){
        framework.changePage(page);
    }
    
    /**
     * Creates a formatted button calling it's Block223MainPage sister method.
     * @author Georges Mourant
     * @param text String for the button
     * @return the formatted JButton
     */
    public static JButton createButton(String text){
        return Block223MainPage.createButton(text);
    }
    
    /**
     * Returns the JList object which is displayed on the side menu.
     * For editing the side menu.
     * @author Georges Mourant
     * @return the JList object of items on the side menu
     */
    public JList getSideMenuList(){
        return framework.getSideMenuItems();
    }
    
    /**
     * This method creates a JLabel formatted as header for the application design.
     * @author Georges Mourant
     * @param txt The text of the JLabel
     * @return the formatted JLabel
     */
    public static JLabel createHeader(String txt){
        JLabel heading = new JLabel(txt);
        heading.setFont(new Font(Block223MainPage.UI_FONT.getFontName(), Font.BOLD, 
                Block223MainPage.UI_FONT.getSize() + Block223MainPage.TITLE_SIZE_INCREASE));
        return heading;
    }
    
    /**
     * Same as other createHeader but accepts alignment using Component's constants.
     * @author Georges Mourant
     * @param txt The text of the JLabel
     * @param alignment must be a Component.CENTER_ALIGNMENT, Component.LEFT_ALIGNMENT or Component.RIGHT_ALIGNMENT
     * @return the formatted JLabel
     */
    public static JLabel createHeader(String txt, float alignment){
        JLabel heading = createHeader(txt);
        
        if(alignment != Component.CENTER_ALIGNMENT && alignment != Component.LEFT_ALIGNMENT
                && alignment != Component.RIGHT_ALIGNMENT)
            throw new java.security.InvalidParameterException("Second parameter "
                    + "must be a Component.CENTER_ALIGNMENT "
                    + "Component.LEFT_ALIGNMENT or Component.RIGHT_ALIGNMENT");
        
        heading.setAlignmentX(alignment);
        
        return heading;
    }
    
    /**
     * Same as other createHeader but accepts alignment using Component's constants
     * and padding as a boolean.
     * @author Georges Mourant
     * @param txt The text of the JLabel
     * @param alignment must be a Component.CENTER_ALIGNMENT, Component.LEFT_ALIGNMENT or Component.RIGHT_ALIGNMENT
     * @param padding boolean specifying if padding is desired
     * @return the formatted JLabel
     */
    public static JLabel createHeader(String txt, float alignment, boolean padding){
        JLabel heading = createHeader(txt, alignment);
        if(padding)
            heading.setBorder(BorderFactory.createCompoundBorder(heading.getBorder(), 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        return heading;
    }
    
    /**
     * Creates a formatted JComboBox<String>.
     * @author Georges Mourant
     * @return the formatted JComboBox
     */
    public static JComboBox<String> createComboBox(){
        JComboBox<String> box = new JComboBox();
        box.setPreferredSize(new Dimension(150, 30));
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.setBackground(Block223MainPage.BUTTON_BACKGROUND);
        box.setForeground(Color.DARK_GRAY);
        return box;
    }
    
    /**
     * Formats a passed JComboBox.
     * @author Georges Mourant
     * @param box the JComboBox to be formatted
     */
    public static void formatComboBox(JComboBox box){
        box.setPreferredSize(new Dimension(150, 30));
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.setBackground(Block223MainPage.BUTTON_BACKGROUND);
        box.setForeground(Color.DARK_GRAY);
    }
    
    /**
     * Creates a formatted JCheckBox.
     * @author Georges Mourant
     * @param txt takes a String for the text next to the check box
     * @return the formatted JCheckbox
     */
    public static JCheckBox createCheckBox(String txt){
        JCheckBox cb = new JCheckBox(txt);
        cb.setBackground(Block223MainPage.HEADER_BACKGROUND);
        cb.setForeground(Color.BLACK);
        cb.setFont(new Font(Block223MainPage.UI_FONT.getFontName(),Font.BOLD,
                Block223MainPage.UI_FONT.getSize()));
        return cb;
    }
    
    public void displayError(String message){
        new ViewError(message, framework);
    }
}
