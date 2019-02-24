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
	* @return A list of the GridCells objects associated to a level.
	*
	* @throws InvalidInputException        if the level doesn't exists.
	*
	*/

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
	}

	public static TOUserMode getUserMode() {
	}

}