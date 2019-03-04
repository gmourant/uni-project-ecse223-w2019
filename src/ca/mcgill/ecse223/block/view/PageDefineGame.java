package ca.mcgill.ecse223.block.view;

import java.awt.GridLayout;


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
        add(addSlider("Number of Levels:", 1, 99, 30));
        
        
	}
	
	
}