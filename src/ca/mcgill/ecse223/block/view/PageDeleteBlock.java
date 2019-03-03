package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The page for deleting a block.
 * @author Imane Chafi
 */
public class PageDeleteBlock extends ContentPage {

	public PageDeleteBlock(Block223MainPage framework) {
		super(framework);
		 setLayout(new GridLayout(7,1));
	        add(createHeader("Delete a Block"));
	        add(new JLabel("Choose the ID of the block to be deleted:"));
	        JPanel b = new JPanel();
	        b.setBackground(this.getBackground());
	        JComboBox<String> ids = createComboBox();
	        ids.addItem("Block id");
	        b.add(ids);
	        add(b);
	        
	        add(new JLabel ("View:"));
	   
	        JPanel exitButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        exitButtons.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), 
	                    BorderFactory.createEmptyBorder(3, 0, 0, 10)));
	        exitButtons.setBackground(this.getBackground());
	        JButton delete = createButton("Delete");
	        JButton cancel = createButton("Cancel");
	        exitButtons.add(delete);
	        exitButtons.add(cancel);
	        
	        add(exitButtons);
	        
	}
	 protected void paintComponent(Graphics g) {
         super.paintComponent(g);  
         g.drawRect(230,150,50,50);  
         g.setColor(Color.BLUE);  
         g.fillRect(230,150,50,50);
         
       }

}
