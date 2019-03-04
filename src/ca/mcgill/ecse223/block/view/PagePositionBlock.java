package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

public class PagePositionBlock extends ContentPage {

	public PagePositionBlock(Block223MainPage parent) {
		super(parent);
		setLayout(new GridLayout(6,1));
		
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
	}
	
}
