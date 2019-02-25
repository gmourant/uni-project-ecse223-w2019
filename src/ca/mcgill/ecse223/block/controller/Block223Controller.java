package ca.mcgill.ecse223.block.controller;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import java.util.ArrayList;
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
        
        /**
         * This method does what Umple's Game.getWithName(…) method would do if
         * it worked properly aka get the game using the name.
         * Author: Georges Mourant
        */
        private static Game findGame(String name){
            // finding game
            Game foundGame = null;
            for(Game game : Block223Application.getBlock223().getGames()){
                if(game.getName().equals(name)){
                    foundGame = game;
                    break;
                }
            }
            return foundGame;
        }

        /**
         * This method deletes a game.
         * Author: Georges Mourant
         * @param name name of the game
         * @throws ca.mcgill.ecse223.block.controller.InvalidInputException
        */
	public static void deleteGame(String name) throws InvalidInputException {
            Game foundGame = findGame(name);
            
            // error if not an Admin
            if(!(Block223Application.getCurrentUser() instanceof Admin))
                throw new InvalidInputException("Admin privileges are required to delete a game.");

            Block223 block;
            
            // make sure the game exists
            if(foundGame != null) {
                // error if it's the wrong admin
                if (Block223Application.getCurrentUser() != foundGame.getAdmin()) {
                    throw new InvalidInputException("Admin privileges are required to delete a game.");
                }

                // get Block223 so can save
                block = foundGame.getBlock223();

                // delete the game
                foundGame.delete();

                // save
                Block223Persistence.save(block);
            }
	}

        /**
         * This method takes finds a game, and sets it as the currently played 
         * game in Block223Application.
         * Author: Georges Mourant
         * @param name name of the game
         * @throws ca.mcgill.ecse223.block.controller.InvalidInputException
        */
	public static void selectGame(String name) throws InvalidInputException {
            Game game = findGame(name);
            
            // error if game does not exist
            if(game == null)
                throw new InvalidInputException("A game with the name " + name + " does not exist.");
            // error if not an Admin
            if(!(Block223Application.getCurrentUser() instanceof Admin))
                throw new InvalidInputException("Admin privileges are required to delete a game.");
            // error if it's the wrong admin
            if(Block223Application.getCurrentUser() != game.getAdmin())
                throw new InvalidInputException("Admin privileges are required to delete a game.");
            
            Block223Application.setCurrentGame(game);
	}

        /**
         * This method updates game information.
         * Author: Georges Mourant
         * @param name name of the game
         * @param nrLevels number of levels in the game
         * @param nrBlocksPerLevel number of blocks per level
         * @param minBallSpeedX minimum speed of the ball in X coordinates
         * @param minBallSpeedY minimum speed of the ball in Y coordinates
         * @param ballSpeedIncreaseFactor the increase factor of the ball's speed
         * @param maxPaddleLength Maximum length of the paddle
         * @param minPaddleLength Minimum length of the paddle
         * @throws ca.mcgill.ecse223.block.controller.InvalidInputException
        */
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
        
        /**
         * Descrition.
         * Author: Georges Mourant
         * @return list of games
         * @throws ca.mcgill.ecse223.block.controller.InvalidInputException
        */
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
            Block223 block = Block223Application.getBlock223();
            
            // error if not an Admin, save Admin
            Admin admin;
            if(Block223Application.getCurrentUser() instanceof Admin){
                admin = (Admin)Block223Application.getCurrentUser();
            } else {
                throw new InvalidInputException("Admin privileges are required to delete a game.");
            }
            
            List<TOGame> result = new ArrayList();
            
            List<Game> games = block.getGames();
            
            for(Game game : games){
                Admin gameAdmin = game.getAdmin();
                if(gameAdmin.equals(admin)){
                    // create TOGame
                    // add to list
                }
            }
	}

        /**
         * Descrition.
         * Author: Georges Mourant
         * @return the currently played game
         * @throws ca.mcgill.ecse223.block.controller.InvalidInputException
        */
	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
            
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