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
	private static Font defaultFont = new Font("Century Gothic",Font.PLAIN,14);
	private static Font titleFont = new Font("Century Gothic",Font.PLAIN,20);

	//*******************
	//Constructor method
	//*******************
	public PageLogout(Block223MainPage parent) {
		super(parent);
		setLayout(new GridLayout(4,1));
		setBackground(Color.WHITE);

		//******************
		//UI Logout elements
		//******************
		//Welcome page panel
		JPanel welcomePanel = new JPanel(new BorderLayout());
		welcomePanel.setBackground(Block223MainPage.getHeaderBackground());
		JLabel welcomeLabel = new JLabel("Welcome to BLOCK223", JLabel.CENTER);
		welcomeLabel.setFont(titleFont);
		welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

		//Buttons 
		//Create game button panel
		JPanel createGameBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		createGameBtnPanel.setBackground(Color.WHITE);
		JButton createGameButton = createButton("Create Game");
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
