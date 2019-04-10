package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentBlock;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Paddle;

/**
 * Page Welcome for the user
 * 
 * @author Imane Chafi
 * @author Kelly Ma
 */

public class PagePlayGame extends ContentPage implements Block223PlayModeInterface {
	// UI elements
	private static Font defaultFont = new Font("Bank Gothic", Font.PLAIN, 14);
	private static Font titleFont = new Font("Futura", Font.PLAIN, 60);
	private String userString = ""; // Stores input queue from paddle
	private JPanel playerPane;
	private String lives;
	private String score;
	private String level;
	private JLabel currentLevel;
	private JLabel currentScore;
	private JLabel nrLives;
	private int hallOfFamePage = 1; // Start with the first page
	String gameName;
        private final boolean testGame;
        private boolean gameStarted;
	Border border = BorderFactory.createLineBorder(new Color(179, 141, 151), 3);

	// *******************
	// Constructor method
	// *******************
        public PagePlayGame(Block223MainPage frame) {
            this(frame, false);
        }
        
	public PagePlayGame(Block223MainPage frame, boolean testGame) {
		super(frame, JPanelWithBackground.Background.general);
                
                this.testGame = testGame;
                
                if(testGame) frame.setSize(750, frame.getHeight());
                
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                
		// *****************
		// UI elements
		// *****************
		// Create a splitPane to have the elements of playing the game and the hall of
		// fame

		// Borders :
		Border blackline = BorderFactory.createLineBorder(new Color(62, 61, 60));
		Border raised = BorderFactory.createRaisedBevelBorder();
		// Border redLine = BorderFactory.createLineBorder(new Color(167, 162, 169));
		// Create Panes :
		playerPane = new JPanel();
		JPanel hallOfFamePane = new JPanel();
		JPanel nextLevelPane = new JPanel();

		// Define Pane backgrounds and colors :
		playerPane.setOpaque(false);
		// playerPane.setBorder(redLine);
		hallOfFamePane.setBackground(Block223MainPage.getDarkColor());
		hallOfFamePane.setMaximumSize(new Dimension(400, 450));
		// hallOfFamePane.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		nextLevelPane.setBackground(new Color(219, 39, 99));
                
		// Define Labels :
		try { // Obtain current game name
			if(testGame && !gameStarted){
				gameName = Block223Controller.getCurrentDesignableGame().getName();
			} else {
				gameName = Block223Controller.getCurrentPlayableGame().getGamename();
			}
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, testGame, frame);
		}
		JLabel Block223 = new JLabel(gameName);
		try { // Obtain current level
                    if(testGame && !gameStarted){
                        level = "Level: ";
                    } else {
			level = "Level: " + Block223Controller.getCurrentPlayableGame().getCurrentLevel() + " ";
                    }
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, testGame, frame);
		}
		currentLevel = new JLabel(level);
		String lives = "";
		try { // Obtain current nr lives
			if(testGame && !gameStarted){
				lives = "Lives: ";
			} else {
				lives = "Lives: " + Block223Controller.getCurrentPlayableGame().getLives() + " ";
			}
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, testGame, frame);
		}
		nrLives = new JLabel(lives);
		String score = "";
		try { // Obtain current score
			if(testGame && !gameStarted){
				score = "Score: ";
			} else {
				score = "Score: " + Block223Controller.getCurrentPlayableGame().getScore() + " ";
			}
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, testGame, frame);
		}
		currentScore = new JLabel(score);
		Block223.setFont(new Font("Consolas", Font.PLAIN, 40));
		currentLevel.setFont(new Font("Consolas", Font.PLAIN, 20));
		currentLevel.setForeground(new Color(227, 228, 219));
		nrLives.setFont(new Font("Consolas", Font.PLAIN, 20));
		nrLives.setForeground(new Color(227, 228, 219));
		currentScore.setFont(new Font("Consolas", Font.PLAIN, 20));
		currentScore.setForeground(new Color(227, 228, 219));
		// Block223.setBorder(blueline);
		Block223.setForeground(new Color(227, 228, 219));
		JLabel hallOfFameLabel = new JLabel(" Hall of Fame ");
		hallOfFameLabel.setBorder(blackline);
		hallOfFameLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		hallOfFameLabel.setBackground(Block223MainPage.getLightColor());
		hallOfFameLabel.setForeground(Block223MainPage.getDefaultForeground());
		// hallOfFamePane.setLayout(new GridLayout(7, 1));
		hallOfFamePane.setOpaque(true);

		// Add labels to panels :
		// playerPane.add(Block223);
		playerPane.add(currentLevel);
		playerPane.add(nrLives);
		playerPane.add(currentScore);
		hallOfFamePane.setLayout(new GridLayout(13,4)); // Only displays the first ten scores 
		hallOfFamePane.add(nextLevelPane);
		hallOfFamePane.add(hallOfFameLabel);
		JButton startGame = createButton("Start Game");
		startGame.setBackground(Block223MainPage.getButtonBackground());
		JPanel startButtonPanel = new JPanel();
		startButtonPanel.setBackground(Block223MainPage.getDarkColor());
		startButtonPanel.add(startGame);
		hallOfFamePane.add(startButtonPanel, BorderLayout.SOUTH);
		// Adding Labels for the HallOfFame :
                if(!(testGame && !gameStarted)){
                    TOHallOfFame hallOfFameEntries;
                    try {
                            hallOfFameEntries = Block223Controller.getHallOfFameWithMostRecentEntry(10);
                    } catch (InvalidInputException e) {
                            displayError(e.getMessage(), testGame);
                            // stop now to prevent future errors based on this exception
                            return;
                    }
                    // add this list
                    List<TOHallOfFameEntry> hallOfFame = hallOfFameEntries.getEntries();
                    int position = 1;
                    for (TOHallOfFameEntry entry : hallOfFame) {
                            String name = entry.getPlayername();
                            int scoring = entry.getScore();
                            JLabel entryFinal = new JLabel(position + " " + name + ": " + scoring);
                            hallOfFamePane.add(entryFinal, BorderLayout.CENTER);
                            entryFinal.setForeground(Block223MainPage.getDefaultForeground());
                            position++;
                    }
                }
        JButton viewMore = createButton("View More");
        viewMore.setBackground(Block223MainPage.getButtonBackground());
        JPanel nextButtonPanel = new JPanel();
        nextButtonPanel.setBackground(Block223MainPage.getDarkColor());
        nextButtonPanel.add(viewMore);
        hallOfFamePane.add(nextButtonPanel, BorderLayout.SOUTH);
		JLabel test = new JLabel("test");
		JLabel test2 = new JLabel("test2");
		//hallOfFamePane.add(test);
		//hallOfFamePane.add(test2);
		
		// Provide minimum sizes for the two components in the split pane
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, hallOfFamePane, nextLevelPane);

		// Add border
		add(playerPane);
		add(hallOfFamePane);

		// Save and logout and quit buttons :

		// Start button panel
		JPanel SaveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		SaveButtonPanel.setLocation(250, 300);

		SaveButtonPanel.setBackground(Color.WHITE);
		JButton StartButton = createButton(" Save ");
		StartButton.setFont(new Font("Consolas", Font.PLAIN, 12));
		StartButton.setBackground(Block223MainPage.getButtonBackground());
		Border border1 = new LineBorder(Color.WHITE, 3);
		StartButton.setBorder(border1);
		StartButton.setForeground(Color.WHITE);
		SaveButtonPanel.add(StartButton, BorderLayout.PAGE_END);
		// StartButtonPanel.setLayout(null);
		SaveButtonPanel.setLocation(250, 300);
		SaveButtonPanel.setBackground(Block223MainPage.getHeaderBackgroundFiller());

		// ***********************
		// Adding ActionListener
		// ***********************
		startGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startGame.setVisible(false); // Remove

				// Thread for the game loop
				KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
					@Override
					public synchronized boolean dispatchKeyEvent(final KeyEvent e) {
						int nextMove = e.getKeyCode();
						switch (nextMove) {
						case KeyEvent.VK_LEFT:
							userString += "l"; // Add left to the input queue
							break;
						case KeyEvent.VK_RIGHT:
							userString += "r"; // Add right to the input queue
							break;
						case KeyEvent.VK_SPACE:
							userString += " "; // Add a pause to the input queue
							break;
						}
						// Pass the KeyEvent to the next KeyEventDispatcher in the queue
						return false;
					}
				};
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

				Runnable play = new Runnable() {
					@Override
					public void run() {
						try {
                                                    if(testGame && !gameStarted){
                                                        Block223Controller.testGame(PagePlayGame.this);
                                                        gameStarted = true;
                                                    } else {
                                                        Block223Controller.startGame(PagePlayGame.this);
                                                    }
						} catch (InvalidInputException e) {
							new ViewError(e.getMessage(), testGame, frame);
						}
						startGame.setVisible(true);
					}
				};
				Thread t = new Thread(play);
				t.start();
			}
		});

	}// End of constructor for PlayGame

	// Implement method to take user inputs
	@Override
	public String takeInputs() {
		String passString = userString;
		userString = "";
		return passString;
	}

	@Override
	public void refresh() {
		playerPane.repaint();
		try { // Obtain current level
                    if(testGame && !gameStarted){
                        level = "Level: ";
                    } else {
			level = "Level: " + Block223Controller.getCurrentPlayableGame().getCurrentLevel() + " ";
                    }
                } catch (InvalidInputException e) {
			e.printStackTrace();
		}
		currentLevel.setText(level);
		try { // Obtain current nr lives
                    if(testGame && !gameStarted){
                        lives = "Lives: ";
                    } else {
                        lives = "Lives: " + Block223Controller.getCurrentPlayableGame().getLives() + " ";
                    }
                } catch (InvalidInputException e) {
			e.printStackTrace();
		}
		nrLives.setText(lives);
		try { // Obtain current score
                    if(testGame && !gameStarted){
                        score = "Score: ";
                    } else {
                        score = "Score: " + Block223Controller.getCurrentPlayableGame().getScore() + " ";
                    }
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		currentScore.setText(score);
	}

	@Override
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		int positionHigh = 0;
		int positionLow;
		
	//Switching the entries for the hallOfFame
		TOHallOfFame hallOfFameEntries;
		try {
			hallOfFameEntries = Block223Controller.getHallOfFameWithMostRecentEntry(10);
			List<TOHallOfFameEntry> hallOfFame = hallOfFameEntries.getEntries();
			for (TOHallOfFameEntry entry : hallOfFame) {
			
			if(hof.getScore() > entry.getScore()) {
				positionHigh = entry.getPosition();
			    entry.setPosition(hof.getPosition());
			    hof.setPosition(positionHigh);}
			}	
			
			} catch (InvalidInputException e) {
			displayError(e.getMessage(), true);
			// stop now to prevent future errors based on this exception
			return;
		}

		//Setting the numberOfLives lower : 
		nrOfLives--;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // ALWAYS call this method first!

		// Add grid cell elements.

		List<TOCurrentBlock> blocks = new ArrayList<TOCurrentBlock>();
		TOCurrentlyPlayedGame game = null;
		try {
			game = Block223Controller.getCurrentPlayableGame();
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
                        return;
		}

		blocks = game.getBlocks();
		
		int xOffset = 20;
		int yOffset = 10;

		for (TOCurrentBlock block : blocks) {
			Color color = new Color(block.getRed(), block.getGreen(), block.getBlue());
			g.setColor(color);
			g.fillRect(block.getX() + xOffset, block.getY() + yOffset, Block.SIZE, Block.SIZE);
		}

		// Paddle :
		g.setColor(Block223MainPage.getPaddleColor());
		g.fillRect((int) game.getCurrentPaddleX() + xOffset, Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH + yOffset,
				(int) game.getCurrentPaddleLength(), Paddle.PADDLE_WIDTH);

		// Ball
		g.setColor(Block223MainPage.getBallColor());
		g.fillOval((int) game.getCurrentBallX()+ xOffset - Ball.BALL_DIAMETER / 2,
				(int) game.getCurrentBallY() + yOffset - Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER, Ball.BALL_DIAMETER);

		// Outline :
		g.setColor(Block223MainPage.getForegroundForBackground());
		g.drawRect(xOffset, yOffset, Game.PLAY_AREA_SIDE, Game.PLAY_AREA_SIDE);
	}

}
