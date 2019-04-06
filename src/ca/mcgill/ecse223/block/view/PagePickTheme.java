
package ca.mcgill.ecse223.block.view;

import static ca.mcgill.ecse223.block.view.ContentPage.createButton;
import static ca.mcgill.ecse223.block.view.ContentPage.createComboBox;
import static ca.mcgill.ecse223.block.view.ContentPage.createHeader;
import static ca.mcgill.ecse223.block.view.Block223MainPage.THEMES;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Page for selecting theme.
 * @author Georges Mourant
 */
public class PagePickTheme extends ContentPage {
    
    public PagePickTheme(Block223MainPage frame){
        super(frame);
        
        // set layout of this panel
        // 7 columns, 1 row
        setLayout(new GridLayout(7,1));
        add(createHeader("Pick a theme")); // add content to panel
        add(new JLabel("Select a theme:"));
        
        // create panel to hold dropdown
        //      (this is needed to prevent dropdown from consuming all available space)
        JPanel b = new JPanel();
        b.setBackground(this.getBackground()); // set to white background
        
        // create game dropdown
        JComboBox<String> themeList = createComboBox();
        
        // add this list
        for(ViewTheme theme : THEMES) {
            themeList.addItem(theme.name);
        }
        
        // add dropdown to panel
        b.add(themeList);
        
        // add panel to page
        add(b);
        
        // create and add exit buttons
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // holder panel
        exitButtons.setBackground(this.getBackground()); // set to white background
        JButton submit = createButton("Change Theme");
        JButton cancel = createButton("Cancel");
        exitButtons.add(submit); // add buttons to holder panel
        exitButtons.add(cancel);
        
        add(exitButtons); // add holder panel to page
        
        // listeners
        cancel.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    cancel();
                }
        });
        submit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(themeList.getSelectedItem() == null) return;
                    try {
                        frame.defineTheme(themeList.getSelectedItem().toString());
                        changePage(Block223MainPage.Page.logout);
                    } catch (Exception ev){
                        displayError(ev.getMessage(), false);
                    }
                }
        });
    }
}
