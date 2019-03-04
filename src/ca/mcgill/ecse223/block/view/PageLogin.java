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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.view.Block223MainPage;

/**
 * Page where the user will have to log in.
 * @author Sofia Dieguez
 * */
public class PageLogin extends ContentPage {
	
	//UI elements
	private static Font defaultFont = new Font("Century Gothic",Font.PLAIN,14);
	private static Font titleFont = new Font("Century Gothic",Font.BOLD,20);
	
	JTextField usernameTextField;
	JPasswordField passwordPField;
	
	//*******************
	//Constructor method
	//*******************
	public PageLogin(Block223MainPage frame){
	    super(frame);
	    setLayout(new GridLayout(7,1));
	    setBackground(Color.WHITE);
	    
	    //*****************
	    //UI Login elements
	    //*****************
	    //Login elements 
	    Border border = BorderFactory.createLineBorder(Block223MainPage.HEADER_BACKGROUND, 3);
	   
	    //Title page panel
	    JPanel titlePanel = new JPanel(new BorderLayout());
	    titlePanel.setBackground(Block223MainPage.HEADER_BACKGROUND);
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
	    
	    //Password label panel
	    JPanel passwordLabelPanel = new JPanel(new BorderLayout());
	    passwordLabelPanel.setBackground(Color.WHITE);
	    JLabel passwordLabel = new JLabel("         Password:");
	    passwordLabel.setFont(defaultFont);
	    passwordLabel.setHorizontalAlignment(JLabel.LEFT);
	    passwordLabelPanel.add(passwordLabel, BorderLayout.WEST);
	
	    //Password password field panel
	    JPanel passwordPFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    passwordPFieldPanel.setBackground(Color.WHITE);
	    passwordPField = new JPasswordField();
	    passwordPField.setPreferredSize(new Dimension(253, 27));
	    passwordPField.setBorder(border);
	    passwordPFieldPanel.add(passwordPField);
	    
	    //Buttons
	    //Log in button panel
	    JPanel loginBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    loginBtnPanel.setBackground(Color.WHITE);
	    JButton logInButton = createButton("Log In");
	    loginBtnPanel.add(logInButton);
	    
	    //Sign up button panel
	    JPanel signUpBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signUpBtnPanel.setBackground(Color.WHITE);
        JButton signUpButton = new JButton("Sign Up!");
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setForeground(Color.BLUE);
        JLabel recommendSignUp = new JLabel("Don't have an account?");
        Border emptyBorder = BorderFactory.createEmptyBorder();
        signUpButton.setBorder(emptyBorder);
        signUpBtnPanel.add(recommendSignUp);
        signUpBtnPanel.add(signUpButton);
        
        //****************************
      	//Adding panels to the screen
      	//****************************
	    add(titlePanel);
	    add(usernameLabelPanel);
	    add(usernameTxtFieldPanel);
	    add(passwordLabelPanel);
	    add(passwordPFieldPanel);
	    add(loginBtnPanel);
	    add(signUpBtnPanel);
	    
	    JList sideMenu = getSideMenuList();
	    sideMenu.setVisible(false);
	    
	    //***********************
	  	//Adding ActionListeners 
	  	//***********************
        logInButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		logInButtonActionPerformed(evt);
        	}
        });//End of logInButton action listener
        
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		signUpButtonActionPerformed(evt);
        	}
        });//End of signUpButton action listener
       
	}//End of PageLogin constructor
	
	//*******************
	//RefreshData method
	//*******************
	private void refreshDataLogIn() {
		//populate page with data
		usernameTextField.setText("");
		passwordPField.setText("");
		//pack();
	}//End of refreshDataLogin
	
	//***********************
	//ActionPerformed methods
	//***********************
	private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//clear error message
		

		//Convert array of characters into a String
		String password = new String(passwordPField.getPassword());

		//call the controller
		try {
			Block223Controller.login(usernameTextField.getText(), password);
		} catch(InvalidInputException e) {
			displayError(e.getMessage(), false);
			refreshDataLogIn();
		}
		changePage(Block223MainPage.Page.logout);
	}//End of logInButtonActionPerformed method
	
	private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		changePage(Block223MainPage.Page.signUp);

	}//End of signUpActionPerformed method

	
}//End of the PageLogin class
