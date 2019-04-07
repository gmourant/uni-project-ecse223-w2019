package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.application.Block223Application;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import static ca.mcgill.ecse223.block.controller.Block223Controller.getUserMode;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.view.Block223MainPage.Page;


/**
 * Page where the user will have to log in.
 *
 * @author Sofia Dieguez
 *
 */
public class PageLogin extends ContentPage {

    //UI elements
    private static Font defaultFont = new Font("Consolas", Font.PLAIN, 14);
    private static Font titleFont = new Font("Consolas", Font.PLAIN, 50);

    JTextField usernameTextField;
    JPasswordField passwordPField;

    //*******************
    //Constructor method
    //*******************
    public PageLogin(Block223MainPage frame) {
        super(frame, JPanelWithBackground.Background.general);
        
        refreshDataLogIn();
        
        setLayout(new GridLayout(7, 1));

        //*****************
        //UI Login elements
        //*****************
        //Login elements 
        Border border = BorderFactory.createLineBorder(new Color(179,141, 151), 3);

        //Title page panel
        JPanel titlePanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
        JLabel titleLabel = new JLabel("Block 223", JLabel.CENTER);
        titleLabel.setForeground(new Color(227, 228, 219));
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Block223MainPage.getForegroundForBackground());
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        //Username label panel
        JPanel usernameLabelPanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
        JLabel usernameLabel = new JLabel("         Username:");
        usernameLabel.setFont(defaultFont);
        usernameLabel.setForeground(Block223MainPage.getForegroundForBackground());
        usernameLabel.setHorizontalAlignment(JLabel.LEFT);
        usernameLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
        usernameLabelPanel.add(usernameLabel, BorderLayout.WEST);

        //Username text field panel
        JPanel usernameTxtFieldPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
        usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(253, 27));
        usernameTextField.setBorder(border);
        usernameTextField.setFont(defaultFont);
        usernameTxtFieldPanel.add(usernameTextField);

        //Password label panel
        JPanel passwordLabelPanel = new JPanelWithBackground(Background.transparent, new BorderLayout());
        JLabel passwordLabel = new JLabel("         Password:");
        passwordLabel.setFont(defaultFont);
        passwordLabel.setForeground(Block223MainPage.getForegroundForBackground());
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        passwordLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
        passwordLabelPanel.add(passwordLabel, BorderLayout.WEST);

        //Password password field panel
        JPanel passwordPFieldPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
        passwordPField = new JPasswordField();
        passwordPField.setPreferredSize(new Dimension(253, 27));
        passwordPField.setBorder(border);
        passwordPFieldPanel.add(passwordPField);

        //Buttons
        //Log in button panel
        JPanel loginBtnPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
        JButton logInButton = createButton("Log In");
        logInButton.setFont(new Font("Consolas", Font.PLAIN, 20));
        loginBtnPanel.add(logInButton);

        //Sign up button panel
        JPanel signUpBtnPanel = new JPanelWithBackground(Background.transparent, new FlowLayout(FlowLayout.CENTER));
        JButton signUpButton = new JButton("Sign Up!");
        signUpButton.setForeground(new Color(179,141, 151));
        signUpButton.setFont(new Font("Consolas", Font.PLAIN, 20));
        signUpButton.setBackground(Block223MainPage.getDefaultForeground());
        //signUpButton.setForeground(Color.BLUE);
        JLabel recommendSignUp = new JLabel("Don't have an account?");
        recommendSignUp.setFont(new Font("Consolas", Font.PLAIN, 18));
        recommendSignUp.setForeground(new Color(227, 228, 219 ));
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
    @Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g); //ALWAYS call this method first!
	    //g.drawRect(60, 100, 50, 50); //Draws square
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(30, 60, 30, 30); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(60, 30, 30, 30); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(90, 0, 30, 30); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(30, 0, 30, 30); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(480, 360, 30, 30); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(510, 330, 30, 30); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(450, 390, 30, 30); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(510, 390, 30, 30); //Fills a square
	    }

    //*******************
    //RefreshData method
    //*******************
    private void refreshDataLogIn() {
        //populate page with data
        Block223Application.resetBlock223();
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
        } catch (InvalidInputException e) {
            displayError(e.getMessage(), Block223MainPage.Page.login);
            return;

        }
        
       switch (getUserMode().getMode()) {
            case Design:
                changePage(Block223MainPage.Page.logout);
                break;
            case Play:
               changePage(Block223MainPage.Page.chooseGame);
                break;
            default:
       }
    }//End of logInButtonActionPerformed method

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        changePage(Block223MainPage.Page.signUp);

    }//End of signUpActionPerformed method
    
    
    

}//End of the PageLogin class
