package ca.mcgill.ecse223.block.view;

import java.awt.GridLayout;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

public class PagePlayGame extends ContentPage implements Block223PlayModeInterface {

	public PagePlayGame(Block223MainPage parent) {
		// Inherit features from ContentPage
		super(parent);
		setLayout(new GridLayout(10,1));
	    add(createHeader(Block223Application.getCurrentPlayableGame().getGame().getName())); 
	}
	
	public String takeInputs() {
		return null;
	}
	
	public void refresh() {
		
	}
	
}
