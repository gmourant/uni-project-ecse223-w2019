package ca.mcgill.ecse223.block.controller;

import java.util.List;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
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
	
	/**
	 * 
	 * This method updates a block with new values. It requires a block ID,
	 * color values (RGB) and the point value of the block.
	 * 
	 * @param id	The ID of the desired block.
	 * @param red	The red component of the block color.
	 * @param green	The green component of the block color.
	 * @param blue	The blue component of the block color.
	 * @param points	The point value of the block.
	 * 
	 * @throws InvalidInputException	if red, green, or blue < 0 or > 255 or if 
	 * 									points < 0 or > 1000
	 * @throws InvalidInputException	if the block ID does not correspond to an
	 * 									existing entity.
	 * 
	 */

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		
		// Perform basic input validation to ensure the numeric values are valid.
		
		if (red > 255 || red < 0) {
			throw new InvalidInputException("Red value not valid");
		}
		else if (green > 255 || green < 0) {
			throw new InvalidInputException("Green value not valid");
		}
		else if (blue > 255 || blue < 0) {
			throw new InvalidInputException("Blue value not valid");
		}
		else if (point > 1000 || point < 1) {
			throw new InvalidInputException("Point value not valid");
		}
		
		// Get the block list for the selected game.
		
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("No game selected")
		}
		List<Block> blocks = game.getBlocks();
		
		// Find the desired block in the block list.
		
		Block foundBlock == null;
		for (Block block : blocks) {
			int blockID = block.getId();
			if (blockID == id) {
				foundBlock = block;
				break;
			}
		}
		
		if (foundBlock == null) {
			throw new InvalidInputException("Invalid block ID")
		}
		
		// Update block data
		
		foundBlock.setRed(red);
		foundBlock.setGreen(green);
		foundBlock.setBlue(blue);
		foundBlock.setPoints(points);
		
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

}