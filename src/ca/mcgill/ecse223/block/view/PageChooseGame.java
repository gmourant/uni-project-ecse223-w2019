package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;

public class PageChooseGame extends ContentPage {

	public PageChooseGame(Block223MainPage parent) {
		super(parent, JPanelWithBackground.Background.general);
		setLayout(new GridLayout(6, 4));

		// Welcome elements:
		JPanel Block223WelcomePane = new JPanelWithBackground(Background.transparent);
		JLabel Block223Welcome = new JLabel("Block 223");
		Block223Welcome.setFont(new Font("Consolas", Font.PLAIN, 65));
		Block223Welcome.setForeground(Block223MainPage.getForegroundForBackground());
		Block223WelcomePane.add(Block223Welcome, BorderLayout.CENTER);
		add(Block223WelcomePane);

		// Header message to choose a game :
		JPanel headerMessagePanel = new JPanelWithBackground(Background.general);
		JLabel headerMessage = createHeader("Choose a game to play!");
		headerMessage.setForeground(Block223MainPage.getLightColor());
		headerMessage.setFont(new Font("Consolas", Font.PLAIN, 30));
		headerMessagePanel.add(headerMessage, BorderLayout.CENTER);
		add(headerMessagePanel);

		// JPanels for the comboBoxes :
		JPanel newGamesPanel = new JPanel();
		JPanel pausedGamesPanel = new JPanel();
		newGamesPanel.setOpaque(false);
		pausedGamesPanel.setOpaque(false);

		// JPanel for the labels of games :

		JPanel newGameLabelPanel = new JPanelWithBackground(Background.general);
		JPanel pausedGameLabelPanel = new JPanelWithBackground(Background.general);
		JLabel newGameLabel = createHeader("Play a new Game?");
		newGameLabel.setForeground(Block223MainPage.getForegroundForBackground());
		JLabel pausedGameLabel = createHeader("Continue a paused Game?");
		pausedGameLabel.setForeground(Block223MainPage.getForegroundForBackground());
		newGameLabelPanel.add(newGameLabel, BorderLayout.WEST);
		pausedGameLabelPanel.add(pausedGameLabel, BorderLayout.WEST);
		add(newGameLabelPanel);

		// JComboBoxes :
		JComboBox<String> newGamesBox = createComboBox();
		JComboBox<String> pausedGamesBox = createComboBox();

		// New games to play controller methods :

		List<TOPlayableGame> games;
		try {
			games = Block223Controller.getPlayableGames();
		} catch (InvalidInputException e) {
			// stop now to prevent future errors based on this exception
			return;
		}
		// add this list
		int i = 0; // Store number of games
		for (TOPlayableGame game : games) {
			if (game.getNumber() != -1) {
				i++;
			}
		}
		int[] ids = new int[i]; // store array
		int k = 0;
		for (TOPlayableGame game : games) {
			if (game.getNumber() == -1) {
				newGamesBox.addItem(game.getName());
			} else {
				ids[k] = game.getNumber();
				pausedGamesBox.addItem(game.getName() + ", Level " + game.getCurrentLevel());
				k++;
			}
		}
		newGamesPanel.add(newGamesBox);
		add(newGamesPanel);
		// Paused Games to play controller methods :
		add(pausedGameLabelPanel);
		JPanel pausedPanel = new JPanel();
		pausedPanel.setBackground(Block223MainPage.getHeaderBackgroundFiller());
		pausedGamesPanel.add(pausedGamesBox);
		add(pausedGamesPanel);

		// Method to change listener for the comboBox for newGame:

		newGamesBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String newGame = (String) newGamesBox.getSelectedItem();
				int id = newGamesBox.getSelectedIndex();
				// Block223MainPage.currentGameDisplay.setText(game);
				try {
					Block223Controller.selectPlayableGame(newGame, id);// We need to have the id of the game
					changePage(Block223MainPage.Page.playGame);
					Block223MainPage.currentGameDisplay.setVisible(true);
					Block223MainPage.currentGameDisplay.setText(newGame);
				} catch (InvalidInputException e) {
					displayError(e.getMessage(), false);
					return;
				}
			}
		});

//Method to change listener for the comboBox for pausedGame: 

		pausedGamesBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String game = (String) pausedGamesBox.getSelectedItem();
				int idPaused = pausedGamesBox.getSelectedIndex();
				// currentGameDisplay.setText(game);
				try {
					Block223Controller.selectPlayableGame(game, ids[idPaused]);
					changePage(Block223MainPage.Page.playGame);
					Block223MainPage.currentGameDisplay.setVisible(true);
					Block223MainPage.currentGameDisplay.setText(game);
				} catch (InvalidInputException e) {
					displayError(e.getMessage(), false);
					return;
				}
			}
		});

	}

	// Method for drawing blocks :
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Block223MainPage.getLightColor());
		g.fillRect(480, 10, 20, 20); // Fills a square

		g.setColor(Block223MainPage.getDarkColor());
		g.fillRect(520, 10, 20, 20); // Fills a square

		g.setColor(Block223MainPage.getLightColor());
		g.fillRect(500, 30, 20, 20); // Fills a square

		g.setColor(Block223MainPage.getDarkColor());
		g.fillRect(520, 50, 20, 20); // Fills a square

		g.setColor(Block223MainPage.getLightColor());
		g.fillRect(30, 10, 20, 20); // Fills a square

		g.setColor(Block223MainPage.getDarkColor());
		g.fillRect(70, 10, 20, 20); // Fills a square

		g.setColor(Block223MainPage.getLightColor());
		g.fillRect(50, 30, 20, 20); // Fills a square

		g.setColor(Block223MainPage.getDarkColor());
		g.fillRect(30, 50, 20, 20); // Fills a square
	}
}
