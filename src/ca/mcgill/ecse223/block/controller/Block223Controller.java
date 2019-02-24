package ca.mcgill.ecse223.block.controller;

import ca.mcgill.ecse223.block.model.*;
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
        
        private static Game findGame(String name){
            // get the block
            Block223 block = Block223Application.getBlock223();
            
            // finding game
            List<Game> games = block.getGames();
            Game foundGame = null;
            for(Game game : games){
                if(game.getName().equals(name)){
                    foundGame = game;
                    break;
                }
            }
            return foundGame;
        }

	public static void deleteGame(String name) throws InvalidInputException {
            Game foundGame = findGame(name);
            
            // error if not an Admin
            if(!(Block223Application.currentUser instanceof Admin))
                throw InvalidInputException("Admin privileges are required to delete a game.");

            // make sure the game exists
            if(foundGame != null){
                // error if it's the wrong admin
                if(Block223Application.currentUser != foundGame.getAdmin())
                    throw InvalidInputException("Admin privileges are required to delete a game.");
                
                // delete the game
                foundGame.delete();
            }
	}

	public static void selectGame(String name) throws InvalidInputException {
            Game game = findGame(name);
            
            // error if game does not exist
            if(game == null)
                throw InvalidInputException("A game with the name " + name + " does not exist.");
            // error if not an Admin
            if(!(Block223Application.currentUser instanceof Admin))
                throw InvalidInputException("Admin privileges are required to delete a game.");
            // error if it's the wrong admin
            if(Block223Application.currentUser != foundGame.getAdmin())
                throw InvalidInputException("Admin privileges are required to delete a game.");
            
            Block223Application.setCurrentGame(game);
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
            // getting current game's name
            Game game = Block223Application.getCurrentGame();
            String currentName = game.getName();
            
            // updating name
            if(currentName != name){
                game.setName(name);
            }
            
            // updating all other information
            setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
			ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
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

}