
package ca.mcgill.ecse223.block.view;

import static ca.mcgill.ecse223.block.view.Block223MainPage.TITLE_SIZE_INCREASE;
import static ca.mcgill.ecse223.block.view.Block223MainPage.UI_FONT;
import static ca.mcgill.ecse223.block.view.Block223MainPage.createButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Georges Mourant
 */
public class ViewError extends JFrame{

    public static final Color ERROR_HEADER_BACKGROUND = 
            new Color(255 + (255 - 255)*5/8, 204 + (255 - 204)*5/8, 204 + (255 - 204)*5/8);
    
    private final Block223MainPage framework;
    private final JPanel windowHolder;
    private JPanel topMenu;
    private JTextArea body;
    private final String errorMessage;
    private JButton exit;
    
    public ViewError(String message, Block223MainPage parent){
        framework = parent;
        this.setSize(300, 200); // Specifies the size should adjust to the needs for space
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specifies what the X to close does
        this.setLocationRelativeTo(null); // Places in the center of the screen
        this.setResizable(false); // stops user from resizing the dialog box
        this.setUndecorated(true);
        this.setVisible(true);
        errorMessage = message;
        windowHolder = new JPanel(new BorderLayout());
        windowHolder.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        
        setupTopMenu();
        add(windowHolder);
    }
    
    /**
     * This method initalises all the information for the top menu.
     * @author Georges Mourant
     */
    private void setupTopMenu() {
        topMenu = new JPanel(new GridLayout(1, 2));
        topMenu.setBorder(BorderFactory.createCompoundBorder(topMenu.getBorder(), 
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        topMenu.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/4));
        topMenu.setBackground(ERROR_HEADER_BACKGROUND);

        JLabel title = new JLabel("Caution"); // empty by default
        title.setFont(new Font(UI_FONT.getFamily(), Font.BOLD, UI_FONT.getSize() + TITLE_SIZE_INCREASE));
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
        
        ImageIcon icon = new ImageIcon(getClass().getResource("error_image.png"));
        JLabel iconHolder = new JLabel(icon);
        holder.add(iconHolder, BorderLayout.WEST);
        
        body = new JTextArea(errorMessage);
        body.setEditable(false);
        body.setLineWrap(true);
        body.setWrapStyleWord(true);
        holder.add(body, BorderLayout.CENTER);
        
        windowHolder.add(holder, BorderLayout.CENTER);
        
        exit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    framework.changePage(Block223MainPage.Page.adminMenu);
                    dispose(); // quit program
                }
        });
    }
}
