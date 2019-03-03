package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ca.mcgill.ecse223.block.controller.TOGame;

/**
 * The page for updating a block. Modified by Mathieu Bissonnette
 * 
 * @author Imane Chafi
 * 
 */
public class PageUpdateBlock extends ContentPage{

	public PageUpdateBlock(Block223MainPage parent){
	    super(parent);
	    setLayout(new GridLayout(7,1));
        
	    //Id panel
	    add(createHeader("Update a Block"));
        Rectangle viewBlock = new Rectangle(20, 20);
        add(new JLabel("ID : "));
        JTextField idTextField = new JTextField();
        idTextField.setPreferredSize(new Dimension(10, 3));
        Color aqua = new Color(224, 249, 246);
        Color borderColor = new Color(207, 243, 238);
        Border border = BorderFactory.createLineBorder(borderColor, 3);
        idTextField.setBorder(border);
        add(idTextField);
        
        //Color slider panel
        JPanel colorSlider = new JPanel(new FlowLayout(FlowLayout.LEFT));
        colorSlider.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                BorderFactory.createEmptyBorder(1, 0, 0, 0)));
        colorSlider.add(new JLabel("Colors: "));
        colorSlider.add(new JSlider(0, 255, 2));
        colorSlider.setBackground(this.getBackground());
        add(colorSlider);
        
        //Number panel
        JPanel sliders = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sliders.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                BorderFactory.createEmptyBorder(1, 0, 0, 10)));
        sliders.add(new JLabel("Points: "));
        sliders.add(new JSlider(1, 1000, 1));
        sliders.setSize(50, 100);
        sliders.setBackground(this.getBackground());
        add(sliders);
//      try{
//      games = Block223Controller.getDesignableGames();
//  }
//  catch(InvalidInputException e){
//      javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
//  }
//  for(TOGame game : games){
//      gameList.addItem(game.getName());
//  }
        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitButtons.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
                    BorderFactory.createEmptyBorder(3, 0, 0, 10)));
        exitButtons.setBackground(this.getBackground());
        JButton updateBlock = createButton("Update block");
        JButton cancel = createButton("Cancel");
        exitButtons.add(updateBlock);
        exitButtons.add(cancel);
        
        add(exitButtons);
	}
	//Code to create a rectangle as a view
	//Taken from : https://stackoverflow.com/questions/31097287/paintcomponent-inside-function
	   protected void paintComponent(Graphics g) {
           super.paintComponent(g);  
           g.drawRect(230,40,50,50);  
           g.setColor(Color.RED);  
           g.fillRect(230,40,50,50);
           
         }
	
}
