package ca.mcgill.ecse223.block.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;


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
        Slider nrLevelsSlider = addSlider("Number of Levels:", 1, 99, 30);
        add(nrLevelsSlider.panel);
        
       
        ChangeListener slide = new ChangeListener() {
        	@Override
        	public void stateChanged(ChangeEvent e) {
        		JSlider source = (JSlider)e.getSource();
        		if (source == nrLevelsSlider.slider) {
        			int nrLevels = nrLevelsSlider.getIValue();
        			nrLevelsSlider.setISlider(nrLevels);
        		} 
        			
        	}
        			
           
        };
        
        // Add ChangeListeners to sliders
        nrLevelsSlider.slider.addChangeListener(slide);
        
	}
	
	
}