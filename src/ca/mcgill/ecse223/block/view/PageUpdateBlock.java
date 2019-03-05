package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.model.Block;

/**
 * The page for updating a block. Modified by Mathieu Bissonnette
 * 
 * @author Imane Chafi
 * @author Mathieu Bissonnette
 * 
 */


public class PageUpdateBlock extends ContentPage{

    //data elements
	private String error = null;
	
	public PageUpdateBlock(Block223MainPage parent){
	    super(parent);
	    setLayout(new GridLayout(8,1));
        
         
	    //Header
	    add(createHeader("Update a Block"));
	    
	    //Rectangle changes color with slider
        JPanel colorPatch; 
        colorPatch = new JPanel();
        JPanel gridbagPanel = new JPanel();
        gridbagPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //colorPatch.setBounds(230,40,50,50);
        gridbagPanel.setPreferredSize(new Dimension(20, 20));
        gridbagPanel.setLocation(230,40);
        add(gridbagPanel);
        colorPatch.setPreferredSize(new Dimension(40,37));
        gridbagPanel.add(colorPatch);
        gridbagPanel.setBackground(this.getBackground());
        colorPatch.setBackground(Color.black);
        Color borderColorBlock = new Color(0, 0, 0);
        Border blockBorder = BorderFactory.createLineBorder(borderColorBlock, 1);
        colorPatch.setBorder(blockBorder);

        //ID panel
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                 BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        JLabel idLabel = new JLabel("ID : ");
        idPanel.add(idLabel);
        JComboBox<Integer> idComboBox = new JComboBox<Integer>();
        idComboBox.setPreferredSize(new Dimension(253, 27));
        // Color aqua = new Color(224, 249, 246);
        Color greenForest = new Color(50,205,50);
        Color borderColor = new Color(207, 243, 238);
        // Populate the ID combobox.
        List<TOBlock> blocks = new ArrayList<TOBlock>();
        try {
        blocks = Block223Controller.getBlocksOfCurrentDesignableGame();
        } catch (InvalidInputException e) {
        	error = e.getMessage();
        	new ViewError(error, false, parent);
        }
        for (TOBlock block : blocks) {
        	idComboBox.addItem(block.getId());
        }
        // Set visuals and add ID panel.
        Border border = BorderFactory.createLineBorder(borderColor, 3);
        idComboBox.setBorder(border);
        idPanel.add(idComboBox);
        idPanel.setBackground(this.getBackground());
        add(idPanel);
        
        
        //RGB Panel
        JPanel redColorSlider = new JPanel(new FlowLayout(FlowLayout.LEFT));
        redColorSlider.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                BorderFactory.createEmptyBorder(1, 0, 0, 0)));
        
        JPanel blueColorSlider = new JPanel(new FlowLayout(FlowLayout.LEFT));
        blueColorSlider.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                BorderFactory.createEmptyBorder(1, 0, 0, 0)));
        
        JPanel greenColorSlider = new JPanel(new FlowLayout(FlowLayout.LEFT));
        greenColorSlider.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                BorderFactory.createEmptyBorder(1, 0, 0, 0)));
        
        //Red slider
        JLabel redLabel = new JLabel("RED:      ");
        redLabel.setForeground(Color.RED);
        redColorSlider.add(redLabel);
        JSlider redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        redColorSlider.add(redSlider);
        redColorSlider.setBackground(this.getBackground());
        add(redColorSlider);
        
        //blueSlider
        JLabel blueLabel = new JLabel("BLUE:     ");
        blueLabel.setForeground(Color.BLUE);
        blueColorSlider.add(blueLabel);
        JSlider blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        blueColorSlider.add(blueSlider);
        blueColorSlider.setBackground(this.getBackground());
        add(blueColorSlider);
        
        //Green slider
        JLabel greenLabel = new JLabel("GREEN: ");
        greenLabel.setForeground(greenForest);
        greenColorSlider.add(greenLabel);
        JSlider greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        greenColorSlider.add(greenSlider);
        greenColorSlider.setBackground(this.getBackground());
        add(greenColorSlider);
       
         
        //Action listener colorChooser 
        //@author http://math.hws.edu/eck/cs124/javanotes4/source/RGBColorChooser.java
        ChangeListener actionListenerColor = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	int r;
            	int g;
            	int b;
            	r = redSlider.getValue();
                g = greenSlider.getValue();
                b = blueSlider.getValue();   
//                System.out.println(((JSlider) e.getSource()).getValue());
                colorPatch.setBackground(new Color(r,g,b));
            }
        };
        
        //Action listener idComboBox 
        //@author http://math.hws.edu/eck/cs124/javanotes4/source/RGBColorChooser.java
        ActionListener actionListenerId = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int r,g,b;
				Integer id = (Integer) idComboBox.getSelectedItem();
				Block block = Block223Controller.findBlock(id);
				r = block.getRed();
				redSlider.setValue(r);
				g = block.getGreen();
				greenSlider.setValue(g);
				b = block.getBlue();
				blueSlider.setValue(b);
				colorPatch.setBackground(new Color(r,g,b));
			}
        };
       
        //Slider listeners
        redSlider.addChangeListener(actionListenerColor);
        blueSlider.addChangeListener(actionListenerColor);
        greenSlider.addChangeListener(actionListenerColor);
        
        //ID Combobox listener
        idComboBox.addActionListener(actionListenerId);
    
        //Number of points panel
        JPanel pointSliders = new JPanel(new GridLayout(1,1));
        pointSliders.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                BorderFactory.createEmptyBorder(1, 0, 0, 10)));
        pointSliders.add(new JLabel("Number of Points:"));
        SpinnerNumberModel points = new SpinnerNumberModel(1,1,1000,1);
        JSpinner spinner = new JSpinner(points);
        pointSliders.add(spinner);
        pointSliders.setBackground(this.getBackground());
        add(pointSliders);

        //Button Panels
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitButtons.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                    BorderFactory.createEmptyBorder(1, 0, 0, 2)));
        exitButtons.setBackground(this.getBackground());
        JButton updateButton = createButton("Update Block");
        JButton cancelButton = createButton("Cancel");
        exitButtons.add(updateButton);
        exitButtons.add(cancelButton);
        add(exitButtons);
        
        // UpdateButton and CancelButton listeners
        updateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// call the controller
				try {
					Block223Controller.updateBlock(
							(int)idComboBox.getSelectedItem(), 
							redSlider.getValue(),
							greenSlider.getValue(),
							blueSlider.getValue(),
							(int) points.getValue());
				} catch (InvalidInputException e) {
					error = e.getMessage();
					new ViewError(error, false, parent);
				} catch (NumberFormatException e) {
					error = "The block ID must be a valid number.";
					new ViewError(error, false, parent);
				}
				
				// update visuals
				//refreshData();
			}
			
		});
        
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		cancel();
        	}
        });
        
       //Side menu editing
       //JList sideMenu = getSideMenuList();
       //add(sideMenu);
        
	}
}