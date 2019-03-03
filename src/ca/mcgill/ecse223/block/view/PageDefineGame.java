package ca.mcgill.ecse223.block.view;

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
	    setLayout(new GridLayout(7,1));
        add(createHeader("Define Game Settings"));
        
        // Set up nrLevels
     	JPanel nrLevelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
     	nrLevelsPanel.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                BorderFactory.createEmptyBorder(1, 0, 0, 0)));
     	JLabel nrLevelsLabel = new JLabel("Number of Levels:");
    	nrLevelsLabel.setFont(new Font("Century Gothic", Font.PLAIN, 9));
        JSlider nrLevelsSlider = new JSlider(1, 99, 30);
        
        // Change Listener for nrLevels
        nrLevelsSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                int nrLevels = nrLevelsSlider.getValue();
                JLabel display = new JLabel(""+nrLevels);
                nrLevelsPanel.add(display);
            }
        });
        
        // Add nrLevelsPanel
    	nrLevelsPanel.add(nrLevelsLabel);
        nrLevelsPanel.add(nrLevelsSlider);
        add(nrLevelsPanel);
       
        
	}
	
}