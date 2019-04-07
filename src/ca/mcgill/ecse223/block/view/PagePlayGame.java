package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Paddle;

/**
 * Page Welcome for the user
 * @author Imane Chafi
 * */

public class PagePlayGame extends ContentPage {
	//UI elements
		private static Font defaultFont = new Font("Bank Gothic",Font.PLAIN,14);
		private static Font titleFont = new Font("Futura",Font.PLAIN,60);
	

		//*******************
		//Constructor method
		//*******************
		public PagePlayGame(Block223MainPage frame) {
			super(frame, JPanelWithBackground.Background.general);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	
			//*****************
		    //UI elements
		    //*****************
		//Create a splitPane to have the elements of playing the game and the hall of fame
			
			//Borders : 
			Border blackline = BorderFactory.createLineBorder(new Color(62, 61,60));
			Border raised = BorderFactory.createRaisedBevelBorder();
			//Border redLine = BorderFactory.createLineBorder(new Color(167, 162, 169));
			//Create Panes :  
			JPanel playerPane = new JPanel();
			JPanel hallOfFamePane = new JPanel();
			JPanel nextLevelPane = new JPanel();
			
			//Define Pane backgrounds and colors : 
			playerPane.setOpaque(false);
			//playerPane.setBorder(redLine);
			hallOfFamePane.setBackground(new Color(179,141, 151));
			hallOfFamePane.setMaximumSize(new Dimension(400, 450));
			//hallOfFamePane.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			nextLevelPane.setBackground(new Color(219, 39, 99));
			
			//Define Labels : 
			JLabel Block223 = new JLabel(" Block 223 ");
			Block223.setFont( new Font("Consolas", Font.PLAIN, 50));
			//Block223.setBorder(blueline);
			Block223.setForeground(new Color(227, 228, 219 ));
			JLabel hallOfFameLabel = new JLabel(" Hall of Fame ");
			hallOfFameLabel.setBorder(blackline);
			hallOfFameLabel.setFont( new Font("Consolas", Font.PLAIN, 20));
			hallOfFameLabel.setBackground(new Color(179,141, 151));
			//hallOfFamePane.setLayout(new GridLayout(7, 1));
			hallOfFamePane.setOpaque(true);
			
			//Add labels to panels : 
			playerPane.add(Block223);
			hallOfFamePane.setLayout(new BorderLayout());
			hallOfFamePane.add(hallOfFameLabel, BorderLayout.NORTH);
			hallOfFamePane.add(nextLevelPane);
			JButton startGame = createButton("Start Game");
			JPanel startButtonPanel = new JPanel();
			startButtonPanel.setBackground(new Color(179,141, 151));
			startButtonPanel.add(startGame);
			hallOfFamePane.add(startButtonPanel, BorderLayout.SOUTH);
			
			//hallOfFamePane.add(next,previous);
			//hallOfFamePane.add(previous, BorderLayout.PAGE_END);
			//hallOfFamePane.add(next, BorderLayout.PAGE_END);
			//Create a split pane with the two scroll panes in it.
		
			
			//Provide minimum sizes for the two components in the split pane
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, hallOfFamePane,nextLevelPane);
			
			add(playerPane);
			add(hallOfFamePane);
			
			//Save and logout and quit buttons : 
			
			  //Start button panel
		    JPanel SaveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		    SaveButtonPanel.setLocation(250, 300);
		    
		    SaveButtonPanel.setBackground(Color.WHITE);
		    JButton StartButton = createButton(" Save ");
		    StartButton.setFont(new Font("Consolas",Font.PLAIN, 12));
		    StartButton.setBackground(new Color (62, 61, 60));
		    Border border1 = new LineBorder(Color.WHITE, 3);
		    StartButton.setBorder(border1);
		    StartButton.setForeground(Color.WHITE);
		    SaveButtonPanel.add(StartButton,BorderLayout.PAGE_END );
		   // StartButtonPanel.setLayout(null);
		    SaveButtonPanel.setLocation(250, 300);
		    SaveButtonPanel.setBackground(new Color (62, 61, 60));


		    
		    //***********************
		  	//Adding ActionListener 
		  	//***********************
		    StartButton.addActionListener(new java.awt.event.ActionListener() {
		    	public void actionPerformed(java.awt.event.ActionEvent evt) {
		    		changePage(Block223MainPage.Page.login);
		    		StartButton.setVisible(false);
		    	}
		    });
		  
		
}//End of constructor for PlayGame
		  @Override
			protected void paintComponent(Graphics g) {
			    super.paintComponent(g); //ALWAYS call this method first!
			    //g.drawRect(60, 100, 50, 50); //Draws square
			    g.setColor(new Color(179,141, 151));
			    g.fillRect(140,  Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE, 100 ,10); //Fills a square
				
			    
			 //Rectangles for PlayGame :  
	
	//First row:
			    //g.drawRect(360, 200, 50, 50); //Draws square
			    g.setColor(new Color(213, 172, 169));
			    g.fillRect(50, 100, 30, 30); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(new Color(179, 141, 151));
			    g.fillRect(100, 100, 30, 30); //Fills a square
			    
			    //Paddle : 
			    g.setColor(new Color(222, 195, 190));
			    g.fillRect(150, 100, 30, 30); //Fills a square
			    
			    //g.drawRect(360, 200, 50, 50); //Draws square
			    g.setColor(new Color(213, 172, 169));
			    g.fillRect(200, 100, 30, 30); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(new Color(179, 141, 151));
			    g.fillRect(250, 100, 30, 30); //Fills a square
			   
			    g.setColor(new Color(222, 195, 190));
			    g.fillRect(300, 100, 30, 30); //Fills a square
			    //g.drawRect(360, 200, 50, 50); //Draws square
	//Second row:
			  
			    g.setColor(new Color(213, 172, 169));
			    g.fillRect(50, 150, 30, 30); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(new Color(179, 141, 151));
			    g.fillRect(100, 150, 30, 30); //Fills a square
			    
			    //Paddle : 
			    g.setColor(new Color(222, 195, 190));
			    g.fillRect(150, 150, 30, 30); //Fills a square
			    
			    //g.drawRect(360, 200, 50, 50); //Draws square
			    g.setColor(new Color(213, 172, 169));
			    g.fillRect(200, 150, 30, 30); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(new Color(179, 141, 151));
			    g.fillRect(250, 150, 30, 30); //Fills a square
			    
			    //Paddle : 
			    g.setColor(new Color(222, 195, 190));
			    g.fillRect(300, 150, 30, 30); //Fills a square
			    
			  //Small drawing : 
			
			    //Left : 
			    //g.drawRect(360, 200, 50, 50); //Draws square
			    g.setColor(new Color(213, 172, 169));
			    g.fillRect(20, 370, 20, 20); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(new Color(179, 141, 151));
			    g.fillRect(35, 385, 20, 20); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(Block223MainPage.getButtonBackground());
			    g.fillRect(50, 400, 20, 20); //Fills a square
			    
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(Block223MainPage.getButtonBackground());
			    g.fillRect(20, 400, 20, 20); //Fills a square
			    
			    //Right : 
			    g.setColor(new Color(213, 172, 169));
			    g.fillRect(330, 370, 20, 20); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(new Color(179, 141, 151));
			    g.fillRect(315, 385, 20, 20); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(Block223MainPage.getButtonBackground());
			    g.fillRect(300, 400, 20, 20); //Fills a square
			    
			    //g.drawRect(460, 200, 50, 50); //Draws square
			    g.setColor(Block223MainPage.getButtonBackground());
			    g.fillRect(330, 400, 20, 20); //Fills a square
			    
			    //Ball : 
			    g.setColor(Block223MainPage.getButtonBackground());
			    g.fillOval(140 ,300 , 15, 15);
			    
		  }
		
		
		
}
