package ca.mcgill.ecse223.block.controller;

import java.util.List;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.*;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************

	/**
	 * This method creates a new game within the Block223 Application
	 * @param name The unique name of the game
	 * @throws InvalidInputException If the user is not an admin
	 * @throws InvalidInputException If the name selected by the user is not unique
	 */
	public static void createGame(String name) throws InvalidInputException {

		// Verify that the user is an admin before proceeding
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to create a game.");
		}
		
		// Get block223 and admin
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		
		// Create game and catch runtime exceptions
		// Exceptions are specified in the injected UMPLE code
		try {
			Game game = new Game(name, 1, admin, 1, 1, 1, 10, 10, block223);
			block223.addGame(game);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

		// Obtain the selected game
		Game game = Block223Application.getCurrentGame();
		
		// Verify that the user is an admin
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
				
		// Verify that a game is selected 
		if (game == null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		}
				
		// Verify that the admin is the same user who created the game
		if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");
		}
		
		// Verify the nrLevels is between [1, 99]
		if (nrLevels < 1 || nrLevels > 99) {
			throw new InvalidInputException("The number of levels must be between 1 and 99.");
		}
		
		// Set nrBlocksPerLevel
		try {
			game.setNrBlocksPerLevel(nrBlocksPerLevel);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		// Obtain ball
		Ball ball = game.getBall();
		
		// Set minBallSpeedX
		try {
			ball.setMinBallSpeedX(minBallSpeedX);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		// Set minBallSpeedY
		try {
			ball.setMinBallSpeedY(minBallSpeedY);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		// Set ballSpeedIncreaseFactor
		try {
			ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		// Obtain paddle
		Paddle paddle = game.getPaddle();
		
		// Set maxPaddleLength
		try {
			paddle.setMaxPaddleLength(maxPaddleLength);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		// Set minPaddleLength
		try {
			paddle.setMinPaddleLength(minPaddleLength);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		// Obtain the number of levels and size of list
		List<Level> levels = game.getLevels();
		int size = levels.size();
		
		// Modify nrLevels
		while (nrLevels > size) {
			game.addLevel();
			size = levels.size();
		}
		while (nrLevels < size) {
			Level level = game.getLevel(size - 1);
			level.delete();
			size = levels.size();
		}
		
	}

	public static void deleteGame(String name) throws InvalidInputException {
	}

	public static void selectGame(String name) throws InvalidInputException {
		
		// Verify the game exists
		Game foundGame = findGame(name);
		
		// Verify that the game exists
		if (foundGame == null) throw new InvalidInputException("This game does not exist.");
		
		// Verify that the user is an admin
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to select a game.");
		}
		
		// If all else is good, select the game
		Block223Application.setCurrentGame(foundGame);
		
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void deleteBlock(int id) throws InvalidInputException {
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
	}

	public static void login(String username, String password) throws InvalidInputException {
	}

	public static void logout() {
	}

	// ****************************
	// Query methods
	// ****************************

	public static List<TOGame> getDesignableGames() {
	}

	public static TOGame getCurrentDesignableGame() {
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
	}

	public static TOUserMode getUserMode() {
	}

	// ****************************
	// Private Helper Methods
	// ****************************

	private static Game findGame(String name) {
		Game foundGame = null;
		for (Game game : Block223Application.getBlock223().getGames()) {
			if (game.getName().equals(name)) {
				foundGame = game;
				break;
			}
		}
		return foundGame;
	}

}