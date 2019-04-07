package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.model.Game;

public class PageChooseGame extends ContentPage {

	public PageChooseGame(Block223MainPage parent){
	    super(parent, JPanelWithBackground.Background.general);
	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	 //Welcome elements:
		JLabel Block223Welcome = new JLabel("   Block 223");
		Block223Welcome.setFont(new Font("Consolas",Font.PLAIN,65));
		Block223Welcome.setForeground(new Color(235, 207, 178));
		add(Block223Welcome);
		
		
	//Header message to choose a game : 
		JLabel headerMessage = createHeader("     Choose a game to play!");
		JLabel nothing = createHeader("  ");
		nothing.setFont(new Font("Consolas",Font.PLAIN,20));
		headerMessage.setForeground(Block223MainPage.getForegroundForBackground());
		headerMessage.setFont(new Font("Consolas",Font.PLAIN,30));
		headerMessage.setBounds(60, 50, 250, 200);
		JPanel headerPanel = new JPanel(new BorderLayout());
		add(nothing);
		add(headerMessage);
		JLabel blank = createHeader("  ");
		blank.setFont(new Font("Consolas",Font.PLAIN,20));
		add(blank);

		 //JPanels for the division of games : 
		JPanel myPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		secondPanel.setBackground(Block223MainPage.getHeaderBackgroundFiller());
		

	//Button listeners : 
	
	//New games to play : 
		
		//The buttons should come up when the game is created, and the names of the button
		//should be the names of the games created. 
		
		 List<TOPlayableGame> games;
	        try{
	            games = Block223Controller.getPlayableGames();
	        }
	        catch(InvalidInputException e){
	            // stop now to prevent future errors based on this exception
	            return;
	        }
	        // add this list
	        for(TOPlayableGame game : games){
	           JButton button =  createButton(game.getName());
	            secondPanel.add(button);
	        }
	 //Paused Games to play :
	        
	       JPanel pausedPanel = new JPanel();
	        pausedPanel.setBackground(Block223MainPage.getHeaderBackgroundFiller());
	        

			 List<TOPlayableGame> pausedGames;
		        try{
		        	pausedGames = Block223Controller.getPlayableGames();
		        }
		        catch(InvalidInputException e){
		            // stop now to prevent future errors based on this exception
		            return;
		        }
		        // add this list
		        for(TOPlayableGame pausedGame : pausedGames){
		        	JButton pausedGameButton;
		        if(pausedGame.getNumber()!= -1 && pausedGame.getCurrentLevel() !=0) {
		            pausedGameButton =  createButton(pausedGame.getName());
		            pausedPanel.add(pausedGameButton);
		        }
		        	}
	        
	     
	    
	   /* public void setCurrentGameDisplay(String txt){
	        if(currentGameDisplay == null) return;
	        currentGameDisplay.setText(txt);
	    }*/
		
		
		/*gameButton.setBackground(new Color(179, 141, 151));
		gameButton.setText("Game 1");
		gameButton.setFont(new Font("Consolas",Font.PLAIN,40));
		gameButton.setSize(100, 170);
		gameButton2.setText("Game 2");
		gameButton2.setFont(new Font("Consolas",Font.PLAIN,40));
		gameButton2.setBackground(new Color(213, 172, 169));
		gameButton2.setSize(100, 170);
		gameButton3.setText("Game 3");
		gameButton3.setFont(new Font("Consolas",Font.PLAIN,40));
		gameButton3.setBackground(new Color(235, 207, 178));
		gameButton3.setSize(100, 70);
		gameButton4.setText("Game 4");
		gameButton4.setFont(new Font("Consolas",Font.PLAIN,40));
		gameButton4.setBackground(new Color(227, 228, 219));
		gameButton4.setSize(100, 170);
		gameButton5.setText("Game 5");
		gameButton5.setFont(new Font("Consolas",Font.PLAIN,40));
		gameButton5.setBackground(new Color(179, 141, 151));
		gameButton5.setSize(100, 170);
		gameButton6.setText("Game 6");
		gameButton6.setFont(new Font("Consolas",Font.PLAIN,40));
		gameButton6.setBackground(new Color(235, 207, 178));
		gameButton5.setSize(100, 170);*/

		
		//Split properties
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, secondPanel ,myPanel);
		split.setDividerLocation(270);
		split.setDividerSize(0);//keeps the divider hidden
		split.setBorder(null);
		
		//JScrollPane of the screen
		JScrollBar scrollBar = new JScrollBar();
		JScrollPane scrollPane = new JScrollPane(myPanel);
		 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	       
	     add(split); 
	     add(pausedPanel);
	   
	     
	     /*  //Listeners : 
	    	gameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					gameButtonActionPerformed(evt);
				}
			});*/
	        
	    
	} 
	
	//Method to change listener for the buttons : 
	
	private void gameButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//Change page to adminMenu
		changePage(Block223MainPage.Page.playGame);
	}//End of createGameButtonButtonActionPerformed

	
	//Method for drawing blocks : 
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	  g.setColor(new Color(227, 228, 219));
	    g.fillRect(480, 10, 20, 20); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(520, 10, 20, 20); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(500, 30, 20, 20); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(520, 50, 20, 20); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(30, 10, 20, 20); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(70, 10, 20, 20); //Fills a square
	    
	    g.setColor(new Color(227, 228, 219));
	    g.fillRect(50, 30, 20, 20); //Fills a square
	    
	    g.setColor(new Color(179,141, 151));
	    g.fillRect(30, 50, 20, 20); //Fills a square
	}    
}
