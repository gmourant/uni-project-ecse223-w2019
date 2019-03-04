package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This page defines game settings for a game within Block223.
 * @author Kelly Ma
 */
public class PageDefineGame extends ContentPage {

	
	private static final long serialVersionUID = 5362736975311160954L;

	public PageDefineGame(Block223MainPage parent) {
		
		// Inherit features from ContentPage
	    super(parent);
	    setLayout(new GridLayout(10,1));
        add(createHeader("Define Game Settings"));
       
        // nrLevels Slider
        JPanel nrLevelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nrLevelsPanel.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                BorderFactory.createEmptyBorder(1, 0, 0, 0)));
        JSlider nrLevelsSlider = addSlider(nrLevelsPanel, "Number of Levels:", 1, 99, 30);
        nrLevelsPanel.setBackground(this.getBackground());
        add(nrLevelsPanel);
        
        
	}
	
	
}