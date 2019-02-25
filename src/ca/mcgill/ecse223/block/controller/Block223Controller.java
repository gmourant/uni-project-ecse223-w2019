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

    /**
	 * This method defines game settings for a game in Block223
	 * @param nrLevels The number of levels available in the game
	 * @param nrBlocksPerLevel The number of blocks per level in the game
	 * @param minBallSpeedX The minimum ball speed in the x-direction
	 * @param minBallSpeedY The minimum ball speed in the y-direction
	 * @param ballSpeedIncreaseFactor The minimum factor by which ball speed increases
	 * @param maxPaddleLength The maximum length of the paddle
	 * @param minPaddleLength The minimum length of the paddle
	 * @throws InvalidInputException If the currentUserRole is not set to an AdminRole
	 * @throws InvalidInputException If the user is not the admin who created the game
	 * @throws InvalidInputException If a game is not selected to define game settings
	 * @throws InvalidInputException If the number of levels is not between [1, 99]
	 * @throws InvalidInputException If nrBlocksPerLevel is negative or zero
	 * @throws InvalidInputException If minBallSpeedX is negative or zero
	 * @throws InvalidInputException If minBallSpeedY is negative or zero
	 * @throws InvalidInputException If ballSpeedIncreaseFactor is negative or zero
	 * @throws InvalidInputException If maxPaddleLength is negative or zero or larger than the play area
	 * @throws InvalidInputException  If minPaddleLength is negative or zero
	 */
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

    /**
     * This method deletes a game. Author: Georges Mourant
     *
     * @param name name of the game
     * @throws ca.mcgill.ecse223.block.controller.InvalidInputException If the game does not exist
	 * @throws ca.mcgill.ecse223.block.controller.InvalidInputException If the user is not an admin
     */
    public static void deleteGame(String name) throws InvalidInputException {
        Game foundGame = findGame(name);

        // error if not an Admin
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to delete a game.");
        }

        Block223 block;

        // make sure the game exists
        if (foundGame != null) {
            // error if it's the wrong admin
            if (Block223Application.getCurrentUserRole() != foundGame.getAdmin()) {
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
     * This method takes finds a game, and sets it as the currently played game
     * in Block223Application.
     * Authors: Georges Mourant & Kelly Ma
     *
     * @param name unique name of the game
     * @throws ca.mcgill.ecse223.block.controller.InvalidInputException If the game does not exist
	 * @throws ca.mcgill.ecse223.block.controller.InvalidInputException If the user is not an admin
	 * @throws ca.mcgill.ecse223.block.controller.InvalidInputException If the current admin is not the game creator
     */
    public static void selectGame(String name) throws InvalidInputException {
        Game game = findGame(name);

        // error if game does not exist
        if (game == null) {
            throw new InvalidInputException("A game with the name " + name + " does not exist.");
        }
        // error if not an Admin
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to delete a game.");
        }
        // error if it's the wrong admin
        if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can delete the game.");
        }

        // If all else is good, select the game
        Block223Application.setCurrentGame(game);
    }

    /**
     * This method updates game information. Author: Georges Mourant
     *
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
        if (currentName != name) {
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
     * This method returns a list of designable games for the current admin.
     * Author: Georges Mourant
     *
     * @return list of designable games
     * @throws ca.mcgill.ecse223.block.controller.InvalidInputException
     */
    public static List<TOGame> getDesignableGames() throws InvalidInputException {
        // get the Block223
        Block223 block = Block223Application.getBlock223();

        // error if not an Admin, save Admin
        Admin admin; // holder
        if (Block223Application.getCurrentUserRole() instanceof Admin) {
            admin = (Admin) Block223Application.getCurrentUserRole(); // set val
        } else { // throw error
            throw new InvalidInputException("Admin privileges are required to delete a game.");
        }

        // create transfer object list
        List<TOGame> result = new ArrayList();

        // get games list
        List<Game> games = block.getGames();

        for (Game game : games) {
            // get game admin
            Admin gameAdmin = game.getAdmin();
            // if current admin is game admin, allow game to be added to list
            if (gameAdmin.equals(admin)) {
                TOGame to = new TOGame(game.getName(), game.numberOfLevels(),
                        game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(),
                        game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(),
                        game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
                result.add(to);
            }
        }
        return result; // return result
    }

    /**
     * Returns the transfer object of a game.
     * Author: Georges Mourant
     *
     * @return the currently played game
     * @throws ca.mcgill.ecse223.block.controller.InvalidInputException If the user is not an admin
     */
    public static TOGame getCurrentDesignableGame() throws InvalidInputException {
        // get current game
        Game game = Block223Application.getCurrentGame();
        // return game as transfer object
        return new TOGame(game.getName(), game.numberOfLevels(),
                game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(),
                game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(),
                game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
    }

    public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
    }

    public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
    }

    public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
    }

    public static TOUserMode getUserMode() throws InvalidInputException {
    }

    
	// ****************************
	// Private Helper Methods
	// ****************************

    /**
     * This method does what Umple's Game.getWithName(…) method would do if it
     * worked properly aka get the game using the name.
     * Authors: Georges Mourant & Kelly Ma
     */
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
