package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
	String gameName;

	// *******************
	// Constructor method
	// *******************
	public PagePlayGame(Block223MainPage frame) {
		super(frame, JPanelWithBackground.Background.general);
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
		playerPane = new JPanel(new BorderLayout());
		JPanel hallOfFamePane = new JPanel();
		JPanel nextLevelPane = new JPanel();

		// Define Pane backgrounds and colors :
		playerPane.setOpaque(false);
		// playerPane.setBorder(redLine);
		hallOfFamePane.setBackground(new Color(179, 141, 151));
		hallOfFamePane.setMaximumSize(new Dimension(400, 450));
		// hallOfFamePane.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		nextLevelPane.setBackground(new Color(219, 39, 99));

		// Define Labels :
		try { // Obtain current level
			gameName = Block223Controller.getCurrentPlayableGame().getGamename();
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, true, frame);
		}
		JLabel Block223 = new JLabel(gameName);
		String level = "";
		try { // Obtain current level
			level = "Level: " + Block223Controller.getCurrentPlayableGame().getCurrentLevel() + " ";
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, true, frame);
		}
		JLabel currentLevel = new JLabel(level);
		String lives = "";
		try { // Obtain current level
			lives = "Lives: " + Block223Controller.getCurrentPlayableGame().getLives() + " ";
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, true, frame);
		}
		JLabel nrLives = new JLabel(lives);
		String score = "";
		try { // Obtain current level
			score = "Score: " + Block223Controller.getCurrentPlayableGame().getScore() + " ";
		} catch (InvalidInputException e) {
			String error = e.getMessage();
			new ViewError(error, true, frame);
		}
		JLabel currentScore = new JLabel(score);
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
		hallOfFameLabel.setBackground(new Color(179, 141, 151));
		// hallOfFamePane.setLayout(new GridLayout(7, 1));
		hallOfFamePane.setOpaque(true);

		// Add labels to panels :
		playerPane.add(Block223);
		playerPane.add(currentLevel);
		playerPane.add(nrLives);
		playerPane.add(currentScore);
		hallOfFamePane.setLayout(new BorderLayout());
		hallOfFamePane.add(hallOfFameLabel, BorderLayout.NORTH);
		hallOfFamePane.add(nextLevelPane);
		JButton startGame = createButton("Start Game");
		JPanel startButtonPanel = new JPanel();
		startButtonPanel.setBackground(new Color(179, 141, 151));
		startButtonPanel.add(startGame);
		hallOfFamePane.add(startButtonPanel, BorderLayout.SOUTH);

		// Adding Labels for the HallOfFame :
		TOHallOfFame hallOfFameEntries;
		try {
			hallOfFameEntries = Block223Controller.getHallOfFameWithMostRecentEntry(10);
		} catch (InvalidInputException e) {
			displayError(e.getMessage(), true);
			// stop now to prevent future errors based on this exception
			return;
		}
		// add this list
		List<TOHallOfFameEntry> hallOfFame = hallOfFameEntries.getEntries();
		for (TOHallOfFameEntry entry : hallOfFame) {
			String name = entry.getPlayername();
			int scoring = entry.getScore();
			int position = entry.getPosition();
			JLabel entryFinal = new JLabel(position + " " + name + ":" + scoring);
			hallOfFamePane.add(entryFinal, BorderLayout.CENTER);

		}

		JLabel test = new JLabel("test");
		JLabel test2 = new JLabel("test2");
		hallOfFamePane.add(test);
		hallOfFamePane.add(test2);

		// Provide minimum sizes for the two components in the split pane
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, hallOfFamePane, nextLevelPane);

		add(playerPane);
		add(hallOfFamePane);

		// Save and logout and quit buttons :

		// Start button panel
		JPanel SaveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		SaveButtonPanel.setLocation(250, 300);

		SaveButtonPanel.setBackground(Color.WHITE);
		JButton StartButton = createButton(" Save ");
		StartButton.setFont(new Font("Consolas", Font.PLAIN, 12));
		StartButton.setBackground(new Color(62, 61, 60));
		Border border1 = new LineBorder(Color.WHITE, 3);
		StartButton.setBorder(border1);
		StartButton.setForeground(Color.WHITE);
		SaveButtonPanel.add(StartButton, BorderLayout.PAGE_END);
		// StartButtonPanel.setLayout(null);
		SaveButtonPanel.setLocation(250, 300);
		SaveButtonPanel.setBackground(new Color(62, 61, 60));

		// ***********************
		// Adding ActionListener
		// ***********************
		startGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StartButton.setVisible(false); // Remove

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
							Block223Controller.startGame(PagePlayGame.this);

						} catch (InvalidInputException e) {
							new ViewError(e.getMessage(), true, frame);
						}
						StartButton.setVisible(true);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		// TODO Auto-generated method stub

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
			e1.printStackTrace();
		}

		blocks = game.getBlocks();

		for (TOCurrentBlock block : blocks) {
			Color color = new Color(block.getRed(), block.getGreen(), block.getBlue());
			g.setColor(color);
			g.fillRect(block.getX(), block.getY(), Block.SIZE, Block.SIZE);
		}

		// Paddle :
		g.setColor(Color.BLACK);
		g.fillRect((int) game.getCurrentPaddleX(), Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE,
				(int) game.getCurrentPaddleLength(), Paddle.PADDLE_WIDTH);

		// Ball
		g.setColor(Color.BLACK);
		g.fillOval((int) game.getCurrentBallX() - Ball.BALL_DIAMETER / 2,
				(int) game.getCurrentBallY() - Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER, Ball.BALL_DIAMETER);

	}

}
