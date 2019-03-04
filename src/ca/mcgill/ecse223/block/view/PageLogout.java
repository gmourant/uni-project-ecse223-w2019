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

/**
 * Welcome page with log out option
 * @author Sofia Dieguez
 * */
public class PageLogout extends ContentPage {

	//data elements
	private String error = null;
	private static boolean isAdmin = (Block223Application.getCurrentUserRole() instanceof Admin);
	private static Font defaultFont = new Font("Century Gothic",Font.PLAIN,14);
	private static Font titleFont = new Font("Century Gothic",Font.PLAIN,20);

	public PageLogout(Block223MainPage parent) {
		super(parent);
		setLayout(new GridLayout(4,1));
		setBackground(Color.WHITE);

		//Welcome page panel
		JPanel welcomePanel = new JPanel(new BorderLayout());
		welcomePanel.setBackground(new Color(229, 248, 255));
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

		//Add panels to the screen
		add(welcomePanel);
		add(Box.createRigidArea(new Dimension(5,0)));
		if(isAdmin) {
			add(createGameBtnPanel);
		}
		add(logOutBtnPanel);

		logOutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//clear error message
				error=null;
				
				Block223Controller.logout();
				//refreshDataLogIn();//TODO
			}
		});//End of logOutButton action listener
		
		createGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//clear error message
				error=null;
				
				//displayPage(Block223MainPage.Page.signUp);//TODO
				//refreshDataLogIn();//TODO
			}
		});//End of createGameButton action listener
		
	}//End of PageLogout constructor

}//End of PageLogout class
