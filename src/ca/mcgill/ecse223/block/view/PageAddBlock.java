package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

/**
 * The page for creating a block.
 * @author Imane Chafi
 */
public class PageAddBlock extends ContentPage{

    //data elements
	private String error = null;
	
	public PageAddBlock(Block223MainPage parent){
	    super(parent);
	    setLayout(new GridLayout(8,1));
        
         
	    //Header
	    add(createHeader("Create a Block"));
	 
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
        JTextField idTextField = new JTextField();
        idTextField.setPreferredSize(new Dimension(253, 27));
        Color aqua = new Color(224, 249, 246);
        Color greenForest = new Color(50,205,50);
        Color borderColor = new Color(207, 243, 238);
        Border border = BorderFactory.createLineBorder(borderColor, 3);
        idTextField.setBorder(border);
        idPanel.add(idTextField);
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
        ChangeListener actionListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	int r;
            	int g;
            	int b;
            	r = redSlider.getValue();
                g = greenSlider.getValue();
                b = blueSlider.getValue();   
                System.out.println(((JSlider) e.getSource()).getValue());
                colorPatch.setBackground(new Color(r,g,b));
            }
        };
       
        //Slider listeners
        redSlider.addChangeListener(actionListener);
        blueSlider.addChangeListener(actionListener);
        greenSlider.addChangeListener(actionListener);
    
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
        JButton addBlock = createButton("Add Block");
        JButton cancel = createButton("Cancel");
        exitButtons.add(addBlock);
        exitButtons.add(cancel);
        add(exitButtons);
        
        //AddBlock and Cancel listeners
        addBlock.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// call the controller
				try {
					Block223Controller.addBlock(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(),(int) points.getValue());
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
				
				// update visuals
				//refreshData();
			}
			
		});
       //Side menu editing
       //JList sideMenu = getSideMenuList();
       //add(sideMenu);
        
	}
	}



