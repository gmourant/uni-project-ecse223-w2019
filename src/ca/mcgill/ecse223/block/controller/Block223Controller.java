package ca.mcgill.ecse223.block.controller;

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
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to create a game.");
		}
		
		// Verify that the game name is unique before proceeding
		Game foundGame = findGame(name);
		if (foundGame != null) throw new RuntimeException("The name of a game must be unique.");

		// Verify that the name entered is not empty
		if (name == null) throw new RuntimeException("The name of a game must be specified.");

		// If all else is good, create game
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
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
	}

	public static void deleteGame(String name) throws InvalidInputException {
	}

	public static void selectGame(String name) throws InvalidInputException {
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