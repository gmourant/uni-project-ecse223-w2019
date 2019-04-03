package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

/**
 * Page where the user will have be able to register for an account.
 * @author Sofia Dieguez
 * */
public class PageSignUp extends ContentPage{

	//data elements
	
	
	//UI elements
	private static Font defaultFont = new Font("Century Gothic",Font.PLAIN,14);
	private static Font titleFont = new Font("Century Gothic",Font.BOLD,20);
	
	JTextField usernameTextField;
	JPasswordField playerPasswordPField;
	JPasswordField adminPasswordPField;
	
	//*******************
	//Constructor method
	//*******************
	public PageSignUp(Block223MainPage frame) {
		super(frame);
		setLayout(new GridLayout(8,1));
		setBackground(Color.WHITE);

		//*****************
	    //UI Sign Up elements
	    //*****************
		//Sign up elements 
		Border border = BorderFactory.createLineBorder(Block223MainPage.getHeaderBackground(), 3);

		//Title page panel
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(Block223MainPage.getHeaderBackground());
		JLabel titleLabel = new JLabel("BLOCK223", JLabel.CENTER);
		titleLabel.setFont(titleFont);
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		//Username label panel
	    JPanel usernameLabelPanel = new JPanel(new BorderLayout());
	    usernameLabelPanel.setBackground(Color.WHITE);
	    JLabel usernameLabel = new JLabel("         Username:");
	    usernameLabel.setFont(defaultFont);
	    usernameLabel.setHorizontalAlignment(JLabel.LEFT);
	    usernameLabelPanel.add(usernameLabel, BorderLayout.WEST);
	    
	    //Username text field panel
	    JPanel usernameTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    usernameTxtFieldPanel.setBackground(Color.WHITE);
	    usernameTextField = new JTextField();
	    usernameTextField.setPreferredSize(new Dimension(253, 27));
	    usernameTextField.setBorder(border);
	    usernameTextField.setFont(defaultFont);
	    usernameTxtFieldPanel.add(usernameTextField);
	    
	    //Password
	    //Player password label panel
	    JPanel playerPwordLabelPanel = new JPanel(new BorderLayout());
	    playerPwordLabelPanel.setBackground(Color.WHITE);
	    JLabel playerPasswordLabel = new JLabel("         Player password:");
	    playerPasswordLabel.setFont(defaultFont);
	    playerPasswordLabel.setHorizontalAlignment(JLabel.LEFT);
	    playerPwordLabelPanel.add(playerPasswordLabel, BorderLayout.WEST);
	    
	    //Player password password field panel
	    JPanel playerPwordPFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    playerPwordPFieldPanel.setBackground(Color.WHITE);
	    playerPasswordPField = new JPasswordField();
	    playerPasswordPField.setPreferredSize(new Dimension(253, 27));
	    playerPasswordPField.setBorder(border);
	    playerPwordPFieldPanel.add(playerPasswordPField);
	    
	    //Admin password label panel
	    JPanel adminPwordLabelPanel = new JPanel(new BorderLayout());
	    adminPwordLabelPanel.setBackground(Color.WHITE);
	    JLabel adminPasswordLabel = new JLabel("         Admin password (optional):");
	    adminPasswordLabel.setFont(defaultFont);
	    adminPasswordLabel.setHorizontalAlignment(JLabel.LEFT);
	    adminPwordLabelPanel.add(adminPasswordLabel, BorderLayout.WEST);
	    
	    //Admin password password field panel
	    JPanel adminPwordPFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    adminPwordPFieldPanel.setBackground(Color.WHITE);
	    adminPasswordPField = new JPasswordField();
	    adminPasswordPField.setPreferredSize(new Dimension(253, 27));
	    adminPasswordPField.setBorder(border);
	    adminPwordPFieldPanel.add(adminPasswordPField);
	    
	    //Sign up button panel
	    JPanel signUpBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    signUpBtnPanel.setBackground(Color.WHITE);
	    JButton signUpButton = createButton("Sign Up");
	    signUpBtnPanel.add(signUpButton);
	    
	    //****************************
      	//Adding panels to the screen
      	//****************************
	    add(titlePanel);
	    add(usernameLabelPanel);
	    add(usernameTxtFieldPanel);
	    add(playerPwordLabelPanel);
	    add(playerPwordPFieldPanel);
	    add(adminPwordLabelPanel);
	    add(adminPwordPFieldPanel);
	    add(signUpBtnPanel);
	    
	    //***********************
	  	//Adding ActionListener 
	  	//***********************
	    signUpButton.addActionListener(new java.awt.event.ActionListener() {
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		signUpButtonActionPerformed(evt);
	    	}
	    });
	    
	    
	}//End of PageSignUp constructor
	
	//*******************
	//RefreshData method
	//*******************
	private void refreshDataSignUp() {
		
		//populate page with data
		usernameTextField.setText("");
		playerPasswordPField.setText("");
		adminPasswordPField.setText("");
	}//End of refreshDataSignUp method
	
	//***********************
	//ActionPerformed methods
	//***********************
	private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//Convert array of characters into a String
		String playerPassword = new String(playerPasswordPField.getPassword());
		String adminPassword = new String(adminPasswordPField.getPassword());

		//call the controller
		try {
			Block223Controller.register(usernameTextField.getText(), playerPassword, adminPassword);
		} catch(InvalidInputException e) {
			displayError(e.getMessage(), false);
			refreshDataSignUp();
		}
		
		changePage(Block223MainPage.Page.login);
	}

}//End of PageSignUp class
