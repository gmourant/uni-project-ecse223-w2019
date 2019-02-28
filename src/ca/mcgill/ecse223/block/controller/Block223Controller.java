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
     * Author: Kelly Ma
     * @param name The unique name of the game
     * @throws InvalidInputException If the user is not an admin
     * @throws InvalidInputException If the name selected by the user is not
     * unique
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
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }

    }

    /**
     * This method defines game settings for a game in Block223
     * Author: Kelly Ma
     * @param nrLevels The number of levels available in the game
     * @param nrBlocksPerLevel The number of blocks per level in the game
     * @param minBallSpeedX The minimum ball speed in the x-direction
     * @param minBallSpeedY The minimum ball speed in the y-direction
     * @param ballSpeedIncreaseFactor The minimum factor by which ball speed
     * increases
     * @param maxPaddleLength The maximum length of the paddle
     * @param minPaddleLength The minimum length of the paddle
     * @throws InvalidInputException If the currentUserRole is not set to an
     * AdminRole
     * @throws InvalidInputException If the user is not the admin who created
     * the game
     * @throws InvalidInputException If a game is not selected to define game
     * settings
     * @throws InvalidInputException If the number of levels is not between [1,
     * 99]
     * @throws InvalidInputException If nrBlocksPerLevel is negative or zero
     * @throws InvalidInputException If minBallSpeedX is negative or zero
     * @throws InvalidInputException If minBallSpeedY is negative or zero
     * @throws InvalidInputException If ballSpeedIncreaseFactor is negative or
     * zero
     * @throws InvalidInputException If maxPaddleLength is negative or zero or
     * larger than the play area
     * @throws InvalidInputException If minPaddleLength is negative or zero
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
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }

        // Obtain ball
        Ball ball = game.getBall();

        // Set minBallSpeedX
        try {
            ball.setMinBallSpeedX(minBallSpeedX);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }

        // Set minBallSpeedY
        try {
            ball.setMinBallSpeedY(minBallSpeedY);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }

        // Set ballSpeedIncreaseFactor
        try {
            ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }

        // Obtain paddle
        Paddle paddle = game.getPaddle();

        // Set maxPaddleLength
        try {
            paddle.setMaxPaddleLength(maxPaddleLength);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }

        // Set minPaddleLength
        try {
            paddle.setMinPaddleLength(minPaddleLength);
        } catch (RuntimeException e) {
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
     * @throws InvalidInputException If the game does not exist
     * @throws InvalidInputException If the user is not an admin
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
     * in Block223Application. Authors: Georges Mourant & Kelly Ma
     *
     * @param name unique name of the game
     * @throws InvalidInputException If the game does not exist
     * @throws InvalidInputException If the user is not an admin
     * @throws InvalidInputException If the current admin is not the game
     * creator
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
     *                                  points < 0 or > 1000
     * @throws InvalidInputException	if the block ID does not correspond to an
     *                                  existing entity.
     */
    public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {

        // Perform basic input validation to ensure the numeric values are valid.
        if (red > 255 || red < 0) {
            throw new InvalidInputException("Red value not valid");
        } else if (green > 255 || green < 0) {
            throw new InvalidInputException("Green value not valid");
        } else if (blue > 255 || blue < 0) {
            throw new InvalidInputException("Blue value not valid");
        } else if (points > 1000 || points < 1) {
            throw new InvalidInputException("Point value not valid");
        }

        // Get the block list for the selected game.
        Game game = Block223Application.getCurrentGame();
        if (game == null) {
            throw new InvalidInputException("No game selected");
        }
        List<Block> blocks = game.getBlocks();

        // Find the desired block in the block list.
        Block foundBlock = null;
        for (Block block : blocks) {
            int blockID = block.getId();
            if (blockID == id) {
                foundBlock = block;
                break;
            }
        }

        if (foundBlock == null) {
            throw new InvalidInputException("Invalid block ID");
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

    /** 
		 * saveGame method: save root class upon user 's command
		 * @author Sofia Dieguez
		 * This method does not take any arguments.
		 * @throws InvalidInputException :If the currentUserRole is not an Admin role
		 * @throws InvalidInputException :If the currentGame is not set
		 * @throws InvalidInputException :If the currentUserRole is not the admin associated to this specific game
		 * @throws InvalidInputException :If saving the root class throws its own RuntimeException
		 * */
	    public static void saveGame() throws InvalidInputException {
	    	
	    	if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
	    		throw new InvalidInputException("Admin privileges are required to save a game.");
	    	}
	    	if(Block223Application.getCurrentGame() == null) {
	    		throw new InvalidInputException("A game must be selected to save it.");
	    	}
	    	if(Block223Application.getCurrentUserRole().getPassword() != Block223Application.getCurrentGame().getAdmin().getPassword()) {
			throw new InvalidInputException("Only the admin who created the game can save it.");
	    	}
	    	
	    	Block223 block223 = Block223Application.getBlock223();
	    	
	    	try {
	    		Block223Persistence.save(block223);
			}
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		}//End of saveGame method
	    
	    /** register method: creates a new account for a user
	     * @author Sofia Dieguez
	     * @param username :User's username
	     * @param playerPassword :User's password linked to an Player role
	     * @param adminPassword (optional) :User's password linked to an Admin role
	     * @throws InvalidInputException :If there is another user currently logged in
	     * @throws InvalidInputException :If playerPassword and adminPassword are the same
	     * @throws InvalidInputException :If playerPassword is null or empty
	     * @throws InvalidInputException :If the username provided already exists (it is not unique)
	     * @throws InvalidInputException :If username is null or empty
	     * */
		public static void register(String username, String playerPassword, String adminPassword) throws InvalidInputException {	
			
			Block223 block223 = Block223Application.getBlock223();
			if(playerPassword.equals(adminPassword)) {
				throw new InvalidInputException("The passwords have to be different");
			}
			if(Block223Application.getCurrentUserRole() != null) {
				throw new InvalidInputException("Cannot register a new user while a user is logged in.");
			}
			
			Player player;//Declare Player instance for scope
			try {
				player = new Player(playerPassword, block223);//Create new Player instance
			} catch (RuntimeException e){
				throw new InvalidInputException(e.getMessage());
			}
			
			User user = null;//Declare User instance for scope
			try {
				user = new User(username, block223, player);//Create User instance
			} catch(RuntimeException e){
				if(e.getMessage().equals("The username has already been taken.")) {//check for generic error message
					player = null;// delete player instance 
					throw new InvalidInputException("The username must be specified.");//specific error message
				}
			}
			
			if((adminPassword!= null) && (adminPassword!="")) {
				Admin admin = new Admin(adminPassword, block223);//Create Admin instance
				user.addRole(admin);//add admin role
			}
			Block223Persistence.save(block223);
		}//End of register method

		/**login method : logs user into a game session
		 * @author Sofia Dieguez
		 * @param username :User's username
		 * @param password :User's password linked to the specific UserRole 
		 * @throws InvalidInputException :If there is another user currently logged in
		 * @throws InvalidInputException :If the username provided doesn't exist
		 * @throws InvalidInputException :If a UserRole with password does not exist for the user
		 * */
		public static void login(String username, String password) throws InvalidInputException {
			if(Block223Application.getCurrentUserRole() != null) {
				throw new InvalidInputException("Cannot login a user while a user is already logged in.");
			}
			
			Block223Application.resetBlock223();
			
			User user = null; 
			if(User.getWithUsername(username) == null) {
				throw new InvalidInputException("The username and password do not match.");
			} else {
				user = User.getWithUsername(username);
			}
			
			List<UserRole> roles = user.getRoles();
			
			for( UserRole role : roles) {
				String rolePassword = role.getPassword();
				if(rolePassword == password) {
					Block223Application.setCurrentUserRole(role);
				}//End of if
			}//End of foreach loop
			
			if(Block223Application.getCurrentUserRole() == null) {
				throw new InvalidInputException("The username and password do not match.");
			}
			
		}//End of login method

		/**logout method: log user out of the game session
		 * This method does not take any arguments.
		 * This method does not throw any InvalidInputExceptions
		 * */
		public static void logout() {
			Block223Application.setCurrentUserRole(null);
		}//End of logout method

    // ****************************
    // Query methods
    // ****************************
    /**
     * This method returns a list of designable games for the current admin.
     * Author: Georges Mourant
     *
     * @return list of designable games
     * @throws InvalidInputException
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
     * Returns the transfer object of a game. Author: Georges Mourant
     *
     * @return the currently played game
     * @throws InvalidInputException If the user is not an admin
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

    public static TOUserMode getUserMode() {
			UserRole userRole = Block223Application.getCurrentUserRole();
			if(userRole == null) {
				TOUserMode to = new TOUserMode(TOUserMode.Mode.None);
				return to;
			}
			if(userRole instanceof Player) {
				TOUserMode to = new TOUserMode(TOUserMode.Mode.Play);
				return to;
			}
			if(userRole instanceof Admin) {
				TOUserMode to = new TOUserMode(TOUserMode.Mode.Design);
				return to;
			}
			return null;
	}//End of getUserMode method

    // ****************************
    // Private Helper Methods
    // ****************************
    /**
     * This method does what Umple's Game.getWithName(…) method would do if it
     * worked properly aka get the game using the name. Authors: Georges Mourant
     * & Kelly Ma
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
