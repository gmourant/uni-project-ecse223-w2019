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

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

    /**
    *
    * This method assigns a block to a position in a game's level. It needs
    * a level index, a block ID and a x/y grid position.
    *
    * @param id    The ID of the desired block.
    * @param level The index of the desired level.
    * @param gridHorizontalPosition        The grid horizontal position where the block will be positioned.
    * @param gridVerticalPosition          The grid vertical position where the block will be positioned.
    *
    * @throws InvalidInputException        if the level index is < 0 or > 98.
    * @throws InvalidInputException        if the level index or the block ID
    *                                 	   do not correspond to an existing
    *                                      entity.
    *
    */

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition) throws InvalidInputException {
		
		// Perform basic input validation to ensure the numeric values are valid.
		
		if (level > 98 || level < 0) {
			throw new InvalidInputException("Level index not valid");
		}
		
		// Get the block list for the selected game.
		
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("No game selected")
		}
		
		// Get the desired level.
		
		Level level = game.getLevel(level);
		if (level == null) {
			throw new InvalidInputException("Level not found")
		}
		
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
		
		// Delete the block assignment at xy coords if it exists.
		
		List<BlockAssignment> assignments = foundLevel.getBlockAssignments();
		for (BlockAssignment block : assignments) {
			int x = block.getGridHorizontalPosition();
			int y = block.getGridVerticalPosition();
			if (x == gridHorizontalPosition && y == gridVerticalPosition) {
				block.delete();
			}
		}
		
		// Create a new BlockAssignment.
		
		foundLevel.addBlockAssignment(gridHorizontalPosition, gridVerticalPosition, foundBlock, game);
		
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

	/**
	*
	* This method returns a list of GridCells associated to a level.
	* It needs a level index.
	*
	* @param level The index of the desired level.
	*
	* @return A list of the GridCells transfert objects associated to a level.
	*
	* @throws InvalidInputException        if the level doesn't exists.
	*
	*/

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		
		// Perform basic input validation to ensure the numeric values are valid.
		
		if (level > 98 || level < 0) {
			throw new InvalidInputException("Level index not valid");
		}
		
		// Get the desired level.
		
		Level level = game.getLevel(level);
		if (level == null) {
			throw new InvalidInputException("Level not found")
		}
		
		// Get the list of block assignments of the level
		
		List<BlockAssignment> assignments = foundLevel.getBlockAssignments();
		
		// Create a list of TOGridCell objects and populate it.
		
		List<TOGridCell> result = new ArrayList<TOGridCell>;
		for (BlockAssignment assignment : assignments) {
			Block block = assignment.getBlock();\
			TOGridCell cell = new TOGridCell(	assignment.getGridHorizontalPosition(), 
												assignment.getGridVerticalPosition(), 
												block.getId(), 
												block.getRed(), 
												block.getGreen(), 
												block.getBlue(), 
												block.getPoints() );
			result.add(cell);
		}
		
		// Return the result.
		
		return result;
		
	}

	public static TOUserMode getUserMode() {
	}

}