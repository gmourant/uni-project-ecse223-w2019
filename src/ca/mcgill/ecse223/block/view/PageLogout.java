
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
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.view.PageLogin;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import static ca.mcgill.ecse223.block.controller.Block223Controller.getUserMode;

/**
 * Welcome page with log out option
 * @author Sofia Dieguez
 * */
public class PageLogout extends ContentPage {

	//data elements
	private static boolean isAdmin = isAdmin();
	
	private static boolean isAdmin() {
		switch (getUserMode().getMode()) {
		case Design:
			return true;
		case Play:
			return false;
		default:
			return false;
		}
	}
	
	//UI elements
    private static Font defaultFont = new Font("Consolas", Font.PLAIN, 14);
    private static Font titleFont = new Font("Consolas", Font.PLAIN, 40);

	//*******************
	//Constructor method
	//*******************
	public PageLogout(Block223MainPage parent) {
		super(parent);
		setLayout(new GridLayout(4,1));
		setBackground(Block223MainPage.getUIBackground());

		//******************
		//UI Logout elements
		//******************
		//Welcome page panel
		JPanel welcomePanel = new JPanelWithBackground(JPanelWithBackground.Background.header, new BorderLayout());
		JLabel welcomeLabel = new JLabel("Welcome to", JLabel.CENTER);
		JLabel welcomeLabel2 = new JLabel("Block223", JLabel.CENTER);
		welcomeLabel.setForeground(Block223MainPage.getForegroundForBackground());
		welcomeLabel.setFont(new Font("Consolas",Font.PLAIN,55));
		welcomeLabel2.setForeground(Block223MainPage.getForegroundForBackground());
		welcomeLabel2.setFont(titleFont);
		welcomePanel.add(welcomeLabel);
		welcomePanel.add(welcomeLabel2);

		//Buttons 
		//Create game button panel
		JPanel createGameBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		createGameBtnPanel.setBackground(Block223MainPage.getUIBackground());
		JButton createGameButton = createButton("Create Game");
		createGameButton.setFont(new Font("Consolas",Font.PLAIN,25));
		createGameBtnPanel.add(createGameButton);

		//Log out button panel
		JPanel logOutBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		logOutBtnPanel.setBackground(Color.WHITE);
		JButton logOutButton = createButton("Log out");
		logOutBtnPanel.add(logOutButton);

		//****************************
		//Adding panels to the screen
		//****************************
		add(welcomePanel);
		add(welcomePanel2);
		add(Box.createRigidArea(new Dimension(5,0)));
		//JList sideMenu = getSideMenuList();
		//sideMenu.setVisible(false);
		
		if(isAdmin) {
			add(createGameBtnPanel);
			//sideMenu.setVisible(true);
		}
		
		//add(logOutBtnPanel);

		//***********************
		//Adding ActionListeners 
		//***********************
		logOutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutButtonActionPerformed(evt);
			}
		});

		createGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createGameButtonButtonActionPerformed(evt);
			}
		});

	}//End of PageLogout constructor
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g); //ALWAYS call this method first!
	    //g.drawRect(60, 100, 50, 50); //Draws square
	    //g.setColor(new Color(179,141, 151));
	   // g.fillRect(290, 280, 30, 30); //Fills a square
	    
	    //g.setColor(new Color(227, 228, 219));
	    //g.fillRect(190, 280, 30, 30); //Fills a square
	    
	    //g.setColor(new Color(179,141, 151));
	   // g.fillRect(90, 280, 30, 30); //Fills a square
	  
	    //Middle Blocks Pattern
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(140, 280, 30, 30); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(240, 280, 30, 30); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(340, 280, 30, 30); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(40, 280, 30, 30); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(290, 230, 30, 30); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(190, 230, 30, 30); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(90, 230, 30, 30); //Fills a square
	    
	    //g.setColor(new Color(227, 228, 219));
	    //g.fillRect(140, 230, 30, 30); //Fills a square
	    
	    //g.setColor(new Color(179,141, 151));
	    //g.fillRect(240, 230, 30, 30); //Fills a square
	    
	   g.setColor(new Color(227, 228, 219));
	    g.fillRect(370, 230, 30, 30); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(5, 230, 30, 30); //Fills a square
	  
	    }


	//***********************
	//ActionPerformed methods
	//***********************
	private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//call to controller
		Block223Controller.logout();
		//Go back to login screen
		changePage(Block223MainPage.Page.login);
	}//End of logOutButtonActionPerformed

	private void createGameButtonButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//Change page to adminMenu
		changePage(Block223MainPage.Page.addGame);
	}//End of createGameButtonButtonActionPerformed

}//End of PageLogout class
