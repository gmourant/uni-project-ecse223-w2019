package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import javax.management.RuntimeErrorException;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;


public class Block223Controller {

    // ****************************
    // Modifier methods
    // ****************************
    /**
     * This method creates a new game within the Block223 Application
     * @author Kelly Ma
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
        
        // Check for empty name
        if (name == null) throw new InvalidInputException("The name of a game must be specified.");
        if (name.isEmpty()) throw new InvalidInputException("The name of a game must be specified.");
        
        // Get block223 and admin
        Block223 block223 = Block223Application.getBlock223();
        Admin admin = (Admin) Block223Application.getCurrentUserRole();
        
        // Check for uniqueness of game name
        boolean unique = true;
        for (Game aGame : block223.getGames()) {
   	     	if (aGame.getName().equals(name)) {
   	     		unique = false;
   	     	}
   	  	}
        
        if (!unique) throw new InvalidInputException("The name of a game must be unique.");
        
        // Create then add game
        try {
        	Game game = new Game(name, 1, admin, 1, 1, 1, 10, 10, block223);
            block223.addGame(game);
        } catch (RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }
        
        // Save to persistence
        try {
    		Block223Persistence.save(block223);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

    }

    /**
     * This method defines game settings for a game in Block223
     * @author Kelly Ma
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
            double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

        // Obtain the selected game and block223
        Game game = Block223Application.getCurrentGame();
        Block223 block223 = Block223Application.getBlock223();

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
        
        // Verify that the ball speed isn't 0
        if (minBallSpeedX <= 0 && minBallSpeedY <= 0) throw new InvalidInputException("The minimum speed of the ball must be greater than zero.");

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
        
        // Save to persistence
        try {
    		Block223Persistence.save(block223);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
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
    	
    	Block223 block = Block223Application.getBlock223();
    	
        Game foundGame = findGame(name);
        
        // make sure the game exists
        if(foundGame == null) return;
        
        if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to delete a game.");
        }

        // error if not the Admin
        if (Block223Application.getCurrentUserRole() != foundGame.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can delete the game.");
        }
        
        // error if the game is published
        if (foundGame.isPublished()) throw new InvalidInputException("A published game cannot be deleted.");
        
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

    /**
     * This method takes finds a game, and sets it as the currently played game
     * in Block223Application. 
     * @author Kelly Ma
     * @author Georges Mourant
     * @param name unique name of the game
     * @throws InvalidInputException If the game does not exist
     * @throws InvalidInputException If the user is not an admin
     * @throws InvalidInputException If the current admin is not the game
     * creator
     */
    public static void selectGame(String name) throws InvalidInputException {
        Game game = findGame(name);
        
        // error if game does not exist
        if(game == null)
            throw new InvalidInputException("A game with name " + name + " does not exist.");

        // error if not an Admin
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to select a game.");
        }
        // error if it's the wrong admin
        if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can select the game.");
        }
        
        // error is the game is published
        if (game.isPublished()) 
        	throw new InvalidInputException("A published game cannot be changed.");
    
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
  
        // getting current game
        Game game = Block223Application.getCurrentGame();

        if(name == null || name.isEmpty())

            throw new InvalidInputException("The name of a game must be specified.");
        
        if (Block223Application.getCurrentGame() == null) {
            throw new InvalidInputException("A game must be selected to define game settings.");
        }
        
        if (game.isPublished()) throw new InvalidInputException("A published game cannot be changed");
        
        
        // getting current game's name
        String currentName = game.getName();
        
    	if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to define game settings.");
        }
    	if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can define its game settings.");
        }
    	
        if(name.equals(""))
            throw new InvalidInputException("The name of a game must be specified.");
       
        // updating name
        if (!currentName.equals(name)) {
            // Check for uniqueness of game name
            boolean unique = true;
            for (Game aGame : Block223Application.getBlock223().getGames()) {
                if (aGame.getName().equals(name)) {
                    unique = false;
                }
            }

            if (!unique) {
                throw new InvalidInputException("The name of a game must be unique.");
            }

            game.setName(name);
        }
        
        // updating all other information
        setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
                ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
    }
   /**
     * This method creates a block in a game. Author: Imane Chafi
     *
     * @param RGB values
     * @param number of points
     * @throws InvalidInputException If the game is not selected
     * @throws InvalidInputException If the user is not an admin
     * @throws InvalidInputException If the user is not the admin who created
     * the game
     * @throws InvalidInputException If the block used already exists
     * @throws InvalidInputException If the color values are not between 0 and
     * 255
     * @throws InvalidInputException If the user if the points are not between 1
     * and 1000
     */
    public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
        // Obtain the selected game
        Game game = Block223Application.getCurrentGame();
        String error = "";
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to add a block.");
        }
        if (Block223Application.getCurrentGame() == null) {
            throw new InvalidInputException("A game must be selected to add a block.");
        }
        if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can add a block.");
        }
        List<Block> blocks = game.getBlocks();
        boolean colorBlockExists = false;
        for (Block block : blocks){
        	if(block.getBlue() == blue && block.getGreen() == green && block.getRed() == red)
        		colorBlockExists = true;
        	
        }
        if (colorBlockExists) { //Question for teacher about getting the blocks with the same colors
            throw new InvalidInputException("A block with the same color already exists for the game.");
        }
        try {
            game.addBlock(red, green, blue, points); //Can I do it like this instead of "create(..)"?
        } catch (RuntimeException e) { //Do I need to make catch and rethrow statements individually?
            error = e.getMessage();     
        }
        if ((red < 0) || (red > 255)) {
            throw new InvalidInputException("Red must be between 0 and 255.");
        }

        if ((green < 0) || (green > 255)) {
            throw new InvalidInputException("Green must be between 0 and 255.");
        }

        if ((blue < 0) || (blue > 255)) {
            throw new InvalidInputException("Blue must be between 0 and 255.");
        }

        if ((points < 1) || (points > 1000)) {
            throw new InvalidInputException("Points must be between 1 and 1000.");}
    }


     /**
     * This method deletes a block from a game. Author: Imane Chafi
     *
     * @param id of block to be deleted
     * @throws InvalidInputException If the game is not selected
     * @throws InvalidInputException If the user is not an admin
     * @throws InvalidInputException If the user is not the admin who created
     * the game
     *
     *
     */
    public static void deleteBlock(int id) throws InvalidInputException {
        Game game = Block223Application.getCurrentGame();
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to delete a block.");
        }
        if(Block223Application.getCurrentGame() == null) {
    		throw new InvalidInputException("A game must be selected to delete a block.");
    	}
        if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can delete a block.");
        }

        Block block = findBlock(id); //Go to find block method for the declaration
        if (block != null) {
            block.delete();
        }
    }


    /**
     *
     * This method updates a block with new values. It requires a block ID,
     * color values (RGB) and the point value of the block.
     * 
     * @author Mathieu Bissonnette
     *
     * @param id	The ID of the desired block.
     * @param red	The red component of the block color.
     * @param green	The green component of the block color.
     * @param blue	The blue component of the block color.
     * @param points	The point value of the block.
     *
     * @throws InvalidInputException	if red, green, or blue < 0 or > 255 or if
     * points < 0 or > 1000
     * @throws InvalidInputException	if the block ID does not correspond to an
     * existing entity.
     */
    public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {

        // Verify that the user is an admin before proceeding.
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to update a block.");
        }
        
        // Verify that a game is selected.
        if (Block223Application.getCurrentGame() == null) {
        	throw new InvalidInputException("A game must be selected to update a block.");	
        }
        
        // Verify that the user is the admin that created the current game.
        if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
        	throw new InvalidInputException("Only the admin who created the game can update a block.");
        }
        
        // Get the desired block.
        Block foundBlock = findBlock(id);
        if (foundBlock == null) {
            throw new InvalidInputException("The block does not exist.");
        }
        
        // Verify the given values
        if (red > 255 || red < 0) {
        	throw new InvalidInputException("Red must be between 0 and 255.");
        }
        
        if (green > 255 || green < 0) {
        	throw new InvalidInputException("Green must be between 0 and 255.");
        }
        
        if (blue > 255 || blue < 0) {
        	throw new InvalidInputException("Blue must be between 0 and 255.");
        }
        
        if (points > 1000 || points < 1) {
        	throw new InvalidInputException("Points must be between 1 and 1000.");
        }
        
        // Verify that a block with this color doesn't already exists
        List<TOBlock> blocks = getBlocksOfCurrentDesignableGame();
        for (TOBlock block : blocks) {
        	if (red == block.getRed() &&  green == block.getGreen() && blue == block.getBlue()) {
        		throw new InvalidInputException("A block with the same color already exists for the game.");
        	}
        }

        // Update block data
        foundBlock.setRed(red);
        foundBlock.setGreen(green);
        foundBlock.setBlue(blue);
        foundBlock.setPoints(points);

    }

    /**
     *
     * This method assigns a block to a position in a game's level. It needs a
     * level index, a block ID and a x/y grid position.
     * 
     * @author Mathieu Bissonnette
     *
     * @param id The ID of the desired block.
     * @param level The index of the desired level.
     * @param gridHorizontalPosition The grid horizontal position where the
     * block will be positioned.
     * @param gridVerticalPosition The grid vertical position where the block
     * will be positioned.
     *
     * @throws InvalidInputException if the level index is < 0 or > 98.
     * @throws InvalidInputException if the level index or the block ID do not
     * correspond to an existing entity.
     *
     */
    public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition) throws InvalidInputException {

        // Verify that the user is an admin before proceeding.
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to position a block.");
        }
        
        // Get the current game and verify it's not null.
        Game game = Block223Application.getCurrentGame();
        if (game == null) {
        	throw new InvalidInputException("A game must be selected to position a block.");
        }
        
        // Verify that the user is the admin that created the current game.
        if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
        	throw new InvalidInputException("Only the admin who created the game can position a block.");
        }

        // Get the desired level.
        if (level < 1 || level > game.getLevels().size()) {
        	throw new InvalidInputException("Level " + level + " does not exist for the game.");
        }
        Level foundLevel = game.getLevel(level-1);
        
        // Verify the level is not a maximum block capacity.
        List<BlockAssignment> assignments = foundLevel.getBlockAssignments();
        // Verify the level is not a maximum block capacity.
     	if (game.getNrBlocksPerLevel() == game.numberOfBlocks())
     		throw new InvalidInputException("The number of blocks has reached the maximum number (" + Block223Application.getCurrentGame().getNrBlocksPerLevel() + ") allowed for this game.");
        
        // Verify if a block already exist at that position.
        for (BlockAssignment block : assignments) {
            int x = block.getGridHorizontalPosition();
            int y = block.getGridVerticalPosition();
            if (x == gridHorizontalPosition && y == gridVerticalPosition) {
            	throw new InvalidInputException("A block already exists at location " + x + "/" + y + ".");
            }
        }
        
        // Get the block.
        Block foundBlock = Block223Controller.findBlock(id);
        if (foundBlock == null) {
        	throw new InvalidInputException("The block does not exist.");
        }

        // Create a new BlockAssignment.
        try {
        	foundLevel.addBlockAssignment(gridHorizontalPosition, gridVerticalPosition, foundBlock, game);
        } catch (RuntimeException e) {
        	if (e.getMessage().equals("X out of bounds.")) {
        		throw new InvalidInputException("The horizontal position must be between 1 and " + Game.GRID_DIMENSIONS + ".");
        	} else {
        		throw new InvalidInputException("The vertical position must be between 1 and " + Game.GRID_DIMENSIONS + ".");
        	}
        }

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
	    	if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
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
			
			if(Block223Application.getCurrentUserRole() != null) {
				throw new InvalidInputException("Cannot register a new user while a user is logged in.");
			}
			if (playerPassword == null) throw new InvalidInputException("The player password needs to be specified.");

			if(playerPassword.equals(adminPassword)) {
				throw new InvalidInputException("The passwords have to be different.");
			}
	        if (playerPassword.isEmpty()) throw new InvalidInputException("The player password needs to be specified.");
			// Check for empty name
	        if (username == null) throw new InvalidInputException("The username must be specified.");
	        if (username.isEmpty()) throw new InvalidInputException("The username must be specified.");
	     
	       
	        Block223 block223 = Block223Application.getBlock223();
	        // Check for uniqueness of game name
	        boolean unique = true;
	        for (User user1 : block223.getUsers()) {
	   	     	if (user1.getUsername() == username) {
	   	     		unique = false;
	   	     	}
	   	  	}
	        
	        if (!unique) throw new InvalidInputException("The username has already been taken.");
	         
	        //Create player
	        Player player;//Declare Player instance for scope
			try {
				player = new Player(playerPassword, block223);//Create new Player instance
			} catch (RuntimeException e){
				throw new InvalidInputException(e.getMessage());
			}
			
			
			//Create user
			User user;//Declare User instance for scope
			try {
				user = new User(username, block223, player);//Create User instance
			} catch(RuntimeException e){
				player = null;
				throw new InvalidInputException(e.getMessage());
			}
			
			if((adminPassword!= null) && (!(adminPassword.equals("")))) {
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
				if(rolePassword.equals(password)) {
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

         /**
	 * This method selects a block and sets a new position
	 * @param level
	 * @param oldGridHorizontalPosition
	 * @param oldGridVerticalPosition
	 * @param newGridHorizontalPosition
	 * @param newGridVerticalPosition
	 * @throws InvalidInputException
	 */
	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {

		//invalid input exception if the user isn't an admin
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to move a block.");
		}

		// invalid input exception if the current game isn't selected
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to move a block.");
		}

		//invalid input exception is the user isn't current admin of the game
		if(Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole()) {
			throw new InvalidInputException("Only the admin who created the game can move a block.");
		}

		// get the current game
		Game game = Block223Application.getCurrentGame();

		// get the selected level and check if the level is within the bounds
		Level selectedLevel;
		try{
			selectedLevel = game.getLevel(level-1);
		}
		catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
		}

		// find the block assignment
		BlockAssignment assignment = selectedLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
		if((assignment == null)) {
			throw new InvalidInputException("A block does not exist at location " + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".");
		}

		BlockAssignment assignmentTaken = selectedLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition);
		if((assignmentTaken != null)) {
			throw new InvalidInputException("A block already exists at location " + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".");
		}
		// set the new horizontal position for the block and check if the position is available
		
		assignment.setGridHorizontalPosition(newGridHorizontalPosition);
		
		if((assignment.getGridHorizontalPosition() <=0) || (assignment.getGridHorizontalPosition() >= 16) )
			throw new InvalidInputException("The horizontal position must be between 1 and 15.");
		
			// set the new vertical position for the block and check if the position is available
		
		assignment.setGridVerticalPosition(newGridVerticalPosition);
		
	
		if(assignment.getGridVerticalPosition() <=0 || assignment.getGridVerticalPosition() > 15 )
			throw new InvalidInputException("The vertical position must be between 1 and 15.");
			

	}
	
    /**
    *
    * This method removes a block from a position in a game's level. It needs a
    * level index and a x/y grid position.
    * 
    * @author Mathieu Bissonnette
    *
    * @param level The index of the desired level.
    * @param gridHorizontalPosition The grid horizontal position where the
    * block will be positioned.
    * @param gridVerticalPosition The grid vertical position where the block
    * will be positioned.
    *
    * @throws InvalidInputException if the user isn't the admin that created the game.
    * @throws InvalidInputException if no game is selected.
    * @throws InvalidInputException if the level index is invalid.
    *
    */
	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {

		// Verify that the user is an admin before proceeding.
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to remove a block.");
        }
        
        // Get the current game and verify it's not null.
        Game game = Block223Application.getCurrentGame();
        if (game == null) {
        	throw new InvalidInputException("A game must be selected to remove a block.");
        }
        
        // Verify that the user is the admin that created the current game.
        if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
        	throw new InvalidInputException("Only the admin who created the game can remove a block.");
        }

        // Get the desired level.
        if (level < 1 || level > game.getLevels().size()) {
        	throw new InvalidInputException("Level " + level + " does not exist for the game.");
        }
        Level foundLevel = game.getLevel(level-1);
        
        // Verify if a block already exist at that position.
        List<BlockAssignment> assignments = foundLevel.getBlockAssignments();
        BlockAssignment foundAssignment = null;
        for (BlockAssignment block : assignments) {
            int x = block.getGridHorizontalPosition();
            int y = block.getGridVerticalPosition();
            if (x == gridHorizontalPosition && y == gridVerticalPosition) {
            	foundAssignment = block;
            }
        }
        
        // Delete the block assignment.
        if (foundAssignment != null) {
	        	foundAssignment.delete();
        }

    }

	

	/**
	  * @author Imane Chafi
	  * @param name of the playable game
	  * @param id of the playable game
	  * @throws InvalidInputException If the user is not a player.
	  * @throws InvalidInputException If the game does not exist.
	  * @throws InvalidInputException If the player is not the one who started the game.
	 	 */
	// Play mode controller methods

	public static void selectPlayableGame(String name, int id) throws InvalidInputException  {
		
		if(!(Block223Application.getCurrentUserRole() instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}	
	Game game = findGame(name);
	Block223 block223 = Block223Application.getBlock223();
	PlayedGame pgame;
	
	if(game != null) {
		Player player = (Player) Block223Application.getCurrentUserRole();
		String username = User.findUsername(player);
		
		PlayedGame result = new PlayedGame(username, game, block223);
		pgame = result;
		pgame.setPlayer(player);
	}
	else {
		pgame = block223.findPlayableGame(id);
		
	}
	if((game == null) && (pgame == null))
		throw new InvalidInputException("The game does not exist.");
	
	if((game == null) && (Block223Application.getCurrentUserRole() != pgame.getPlayer()))
		throw new InvalidInputException("Only the player that started a game can continue the game.");
	
	Block223Application.setCurrentPlayableGame(pgame);
	}

	/** StartGame method
	  * @author Imane Chafi
	  * @param Block223PlayModeInterface ui
	  * @throws InvalidInputException If the user is not a player
	 	 */
	public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {
	
		if ((Block223Application.getCurrentUserRole() == null)) {
            throw new InvalidInputException("Player privileges are required to play a game.");
        }
		if ((Block223Application.getCurrentPlayableGame() == null)) {
            throw new InvalidInputException("A game must be selected to play it.");
        }
		if ((Block223Application.getCurrentUserRole() instanceof Admin) && (Block223Application.getCurrentPlayableGame().getPlayer() !=null)) {
            throw new InvalidInputException("Player privileges are required to play a game.");
        }
		if ((Block223Application.getCurrentUserRole() instanceof Admin) && (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin())) {//Check for the admin of the function
            throw new InvalidInputException("Only the admin of a game can test the game.");
        }
		if ((Block223Application.getCurrentUserRole() instanceof Player) && (Block223Application.getCurrentPlayableGame().getPlayer() == null)) {
            throw new InvalidInputException("Admin privileges are required to test a game.");
        }
		
	PlayedGame game = Block223Application.getCurrentPlayableGame();
	game.play();
	ui.takeInputs(); //Method to be implemented, ask why they need to be static.
	
	if(game.getPlayStatus() == PlayStatus.Moving) {
		String userInputs = ui.takeInputs(); 
		Block223Controller.updatePaddlePosition(userInputs); 
		game.move();
		
		if(userInputs.contains(""))
			game.pause();
		
		//Waiting time for game.getWaitTime
		try
		{
		    Thread.sleep((long)game.getWaitTime());
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		ui.refresh();
	}
	if(game.getPlayStatus() == PlayStatus.GameOver) {
		Block223Application.setCurrentPlayableGame(null);
	}
	else if(game.getPlayer()!= null) {
		Block223 block223 = Block223Application.getBlock223();
		
		Block223Persistence.save(block223);
	}
		
	
	}

	/**
     * testGame method allows an admin to test their game before officially publishing it.
     * @author Sofia Dieguez
     * @param Block223PlayModeInterface user interface 
     * @throws InvalidInputException if the currentUserRole isn't set to an admin role. 
     * @throws InvalidInputException if a game hasn't been selected yet.
     * @throws InvalidInputException if the admin who wants to test the game wasn't the game's creator.
     * */
    public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {
    	//If there isn't a game selected
    	if(Block223Application.getCurrentGame() == null) {
    		throw new InvalidInputException("A game must be selected to test it.");
    	}
    	Game game = Block223Application.getCurrentGame();
    	
    	//If the current UserRole isn't set to and AdminRole 
    	if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
    		throw new InvalidInputException("Admin privileges are required to test a game.");
    	}
    	UserRole admin = Block223Application.getCurrentUserRole();
    	
    	//If the current UserRole is not the admin of the game
    	if(Block223Application.getCurrentGame().getAdmin() != admin) {
    		throw new InvalidInputException("Only the admin who created the game can test it.");
    	}
    	
    	String username = User.findUsername(admin);
    	
    	Block223 block223 = Block223Application.getBlock223();
    	
    	PlayedGame pgame = new PlayedGame(username, game, block223);
    	
    	pgame.setPlayer(null); 
    	
    	Block223Application.setCurrentPlayableGame(pgame);
    	
    	Block223Controller.startGame(ui);
    	
    }//End of testGame method

    /**
     * publishGame method allows an admin officially publish their game for players to play it.
     * @author Sofia Dieguez
     * This method does not take any arguments. 
     * @throws InvalidInputException if the currentUserRole isn't set to an admin role. 
     * @throws InvalidInputException if a game hasn't been selected yet.
     * @throws InvalidInputException if the admin who wants to test the game wasn't the game's creator.
     * @throws InvalidInputException if there isn't at least one block defined in the game
     * */
	public static void publishGame () throws InvalidInputException {
		
		//If a game hasn't been selected
		if(Block223Application.getCurrentGame() == null) {
    		throw new InvalidInputException("A game must be selected to publish it.");
    	}
		//If the current UserRole is not and AdminRole
    	if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
    		throw new InvalidInputException("Admin privileges are required to publish a game.");
    	}
    	//If the current UserRole is not the admin of this game
    	if(Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole()) {
    		throw new InvalidInputException("Only the admin who created the game can publish it.");
    	}
    	//If there are less than 1 number of blocks defined for the game
    	if(Block223Application.getCurrentGame().getBlocks().size() < 1) {
    		throw new InvalidInputException("At least one block must be defined for a game to be published.");
    	}
    	
    	Game game = Block223Application.getCurrentGame();
    	game.setPublished(true);
    	
	}//End of publishGame method

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
            throw new InvalidInputException("Admin privileges are required to access game information.");
        }

        // create transfer object list
        List<TOGame> result = new ArrayList();

        // get games list
        List<Game> games = block.getGames();

        for (Game game : games) {
            // get game admin
            Admin gameAdmin = game.getAdmin();
            // if current admin is game admin, allow game to be added to list
            if (gameAdmin.equals(admin) && !game.isPublished()) {
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
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to access game information.");
        }
        if (Block223Application.getCurrentGame() == null) {
            throw new InvalidInputException("A game must be selected to access its information.");
        }
        if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can access its information.");
        }
        // return game as transfer object
        return new TOGame(game.getName(), game.numberOfLevels(),
                game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(),
                game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(),
                game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
    }

    public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
        Game game = Block223Application.getCurrentGame();
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to access game information.");
        }
        if (Block223Application.getCurrentGame() == null) {
            throw new InvalidInputException("A game must be selected to access its information.");
        }
        if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can access its information.");
        }

        List<TOBlock> result = new ArrayList<TOBlock>();

        for (Block block : game.getBlocks()) {
            TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
            result.add(to);
        }
        return result;
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
    
    /**
     *
     * This method returns a list of GridCells associated to a level. It needs a
     * level index.
     *
     * @author Mathieu Bissonnette
     *
     * @param level The index of the desired level.
     *
     * @return A list of the GridCells transfer objects associated to a level.
     *
     * @throws InvalidInputException if the level doesn't exists.
     *
     */
  
    public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {

        // Verify that the user is an admin before proceeding.
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to access game information.");
        }
        
        // Get the current game and verify it's not null.
        Game game = Block223Application.getCurrentGame();
        if (game == null) {
        	throw new InvalidInputException("A game must be selected to access its information.");
        }
        
        // Verify that the user is the admin that created the current game.
        if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
        	throw new InvalidInputException("Only the admin who created the game can access its information.");
        }

        // Get the desired level.
        if (level < 1 || level > game.getLevels().size()) {
        	throw new InvalidInputException("Level " + level + " does not exist for the game.");
        }
        Level foundLevel = game.getLevel(level-1);

        // Get the list of block assignments of the level
        List<BlockAssignment> assignments = foundLevel.getBlockAssignments();

        // Create a list of TOGridCell objects and populate it.
        List<TOGridCell> result = new ArrayList<TOGridCell>();
        for (BlockAssignment assignment : assignments) {
            Block block = assignment.getBlock();
            TOGridCell cell = new TOGridCell(assignment.getGridHorizontalPosition(),
                    assignment.getGridVerticalPosition(),
                    block.getId(),
                    block.getRed(),
                    block.getGreen(),
                    block.getBlue(),
                    block.getPoints());
            result.add(cell);
        }

        // Return the result.
        return result;
      
    }
    
  /**
  * @author Imane Chafi
  * @return A TOPlayableGame with the list of playable games
  * @throws InvalidInputException If the user is not a player
 	 */
    // Play mode query methods

 	public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
 		
 		if (!(Block223Application.getCurrentUserRole() instanceof Player)) {
            throw new InvalidInputException("Player privileges are required to play a game.");
        }
 		
 		Block223 block223 = Block223Application.getBlock223();
 		 Player player = (Player) Block223Application.getCurrentUserRole();
 		 List<TOPlayableGame> result = new ArrayList<TOPlayableGame>();//Check implementation
 		 List<Game> games = block223.getGames();
 		 
         for (Game game : games) {

             boolean published = game.isPublished();
             if (published) {
                 TOPlayableGame to = new TOPlayableGame(game.getName(), -1, 0);
             
                 result.add(to);
             }
         }
         
       List<PlayedGame> playedGames =  player.getPlayedGames();//I renamed this variable for the played games
       
       for (PlayedGame game : playedGames) {

          TOPlayableGame to = new TOPlayableGame(game.getGame().getName(), game.getId(), game.getCurrentLevel());
          result.add(to);
          
       }
       
       return result;  
     }
 	
 	/**
 	 * @author Kelly Ma
 	 * @return A transfer object containing all information for a currently played game
 	 * @throws InvalidInputException If the UserRole is not set
 	 * @throws InvalidInputException If a PlayedGame is not selected
 	 * @throws InvalidInputException If the currentUserRole is an AdminRole, but the currentPlayer is a PlayerRole
 	 * @throws InvalidInputException If the currentAdmin is the not the game's admin
 	 * @throws InvalidInputException If the currentUserRole is a player, but the player is not set
 	 */
 	public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {
 		
 		UserRole currentUserRole = Block223Application.getCurrentUserRole();
 		if (currentUserRole == null) throw new // Verifies that the user role is set
			InvalidInputException("Player privileges are required to play a game.");
 		
 		PlayedGame pgame = Block223Application.getCurrentPlayableGame(); // Obtain current played game
 		if (pgame == null) throw new InvalidInputException("A game must be selected to play it."); // Throws exception if no game set
 		
 		Player currentPlayer = pgame.getPlayer();
 		if (currentUserRole instanceof Admin && currentPlayer != null) throw new // Verifies that the user role is the player themself
 			InvalidInputException("Player privileges are required to play a game.");
 		
 		Admin gameAdmin = pgame.getGame().getAdmin();
 		if (currentUserRole instanceof Admin && currentUserRole != gameAdmin) throw new // Verifies the current admin is the one who created the game
			InvalidInputException("Only the admin of a game can test the game.");
		
 		if (currentUserRole instanceof Player && currentPlayer == null) throw new // Verifies the current admin is the one who created the game
 			InvalidInputException("Admin privileges are required to test a game.");
 		
 		// Create a boolean to check the status of the game (whether or not we should play)
 		boolean paused = pgame.getPlayStatus() == PlayStatus.Ready || pgame.getPlayStatus() == PlayStatus.Paused; // Checks if game is paused

 		// Create a transfer object for the currently played game
 		TOCurrentlyPlayedGame result = new TOCurrentlyPlayedGame(pgame.getGame().getName(), paused, pgame.getScore(), pgame.getLives(), 
 				pgame.getCurrentLevel(), pgame.getPlayername(), pgame.getCurrentBallX(), pgame.getCurrentBallY(), 
 				pgame.getCurrentPaddleLength(), pgame.getCurrentPaddleX());
 		
 		List <PlayedBlockAssignment> blocks = pgame.getBlocks(); // Obtain all block assignments from playedGame
 		
 		for (PlayedBlockAssignment pblock : blocks) { // Add each pblock to result via transfer object
 			new TOCurrentBlock(pblock.getBlock().getRed(), pblock.getBlock().getGreen(), pblock.getBlock().getBlue(),
 					pblock.getBlock().getPoints(), pblock.getX(), pblock.getY(), result);
 		}
 		
 		return result;
 	}

 	/**
 	 * @author Kelly Ma
 	 * @param start The first index of entries to be viewed
 	 * @param end The last index of entries to be viewed
 	 * @return A TOHallOfFame with HallOfFameEntries
 	 * @throws InvalidInputException If the user is not a player
 	 * @throws InvalidInputException If a game is not selected 
 	 */
 	public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
 		
 		if (!(Block223Application.getCurrentUserRole() instanceof Player)) throw new // Verifies that the user is a Player
 			InvalidInputException("Player privileges are required to access a game's hall of fame.");
 		PlayedGame pgame = Block223Application.getCurrentPlayableGame(); // Obtain current played game
 		if (pgame == null) throw new InvalidInputException("A game must be selected to view its hall of fame."); // Throws exception if no game set
 		
 		Game game = pgame.getGame(); // From current played game, get game
 		TOHallOfFame result = new TOHallOfFame(game.getName()); // Create the HOF with name of the current game
 		
 		if (start < 1) start = 1; // Ensure that start index is >= 1
 		if (end > game.numberOfHallOfFameEntries()) end = game.numberOfHallOfFameEntries(); // End cannot exceed total # of entries
 		start = game.numberOfHallOfFameEntries() - start; // Resets index to largest index
 		end = game.numberOfHallOfFameEntries() - end;
 		
 		for (int index = start; index >= end; index--) { 
 			// String username = User.findUsername(game.getHallOfFameEntry(i).getPlayer()); // Old method to find username
 			new TOHallOfFameEntry(index+1, game.getHallOfFameEntry(index).getPlayername(), game.getHallOfFameEntry(index).getScore(), result); // Create transfer object
 			
 		}
 		
 		return result; // Returns HOF as a transfer object
 	}

 	/**
 	 * @author Kelly Ma
 	 * @param numberOfEntries The number of entries the user would like to view
 	 * @return A TOHallOfFame with HallOfFameEntries
 	 * @throws InvalidInputException If the user is not a player
 	 * @throws InvalidInputException If a game is not selected 
 	 */
 	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
 		
 		if (Block223Application.getCurrentUserRole() instanceof Admin) throw new InvalidInputException("Player privileges are required to access a game�s hall of fame.");
		PlayedGame pgame = Block223Application.getCurrentPlayableGame(); // Obtain current played game
		if (pgame == null) throw new InvalidInputException("A game must be selected to view its hall of fame."); // Throws exception if no game set
		
		Game game = pgame.getGame(); // From current played game, get game
		TOHallOfFame result = new TOHallOfFame(game.getName()); // Create the HOF with name of the current game
		
		HallOfFameEntry mostRecent = game.getMostRecentEntry(); // Obtain mostRecentEntry for the game being played
		int indexR = game.indexOfHallOfFameEntry(mostRecent);
		
		int start = indexR + numberOfEntries/2;
		if (start > game.numberOfHallOfFameEntries() - 1) start = game.numberOfHallOfFameEntries() - 1;
		int end = start - numberOfEntries + 1;
		if (end < 0) end = 0;
		
		for (int index = start; index >= end; index--) {
			// String username = User.findUsername(game.getHallOfFameEntry(index).getPlayer());
 			new TOHallOfFameEntry(index+1, game.getHallOfFameEntry(index).getPlayername(), game.getHallOfFameEntry(index).getScore(), result); // Create transfer object
		}
 		
 		return result; // Returns HOF as an object
 	}

    // ****************************
    // Private Helper Methods
    // ****************************
 	
 	/**
	 * This method moves the paddle one pixel to the left or right
	 * @author Kelly Ma
	 * @author Imane Chafi
	 * @param direction Describes left, right, or pause
	 * Assume that all other checks are performed in the controller method that uses updatePaddlePosition
	 */
	private static void updatePaddlePosition(String direction) {
		
		PlayedGame playedGame = Block223Application.getCurrentPlayableGame(); // Obtain the current playedGame
		double currentPaddleLength = playedGame.getCurrentPaddleLength(); // Obtain current paddle length
		double currentPaddleX = playedGame.getCurrentPaddleX(); // Current X-Position of paddle
		
		switch(direction) {
			case "l": // Represents moving left
				if (currentPaddleX - currentPaddleLength == 0) playedGame.setCurrentPaddleX(currentPaddleX); // Paddle already at leftmost side
				else playedGame.setCurrentPaddleX(--currentPaddleX); // Otherwise, increment paddle position 1 unit to the left
				break;
			case "r": // Represents moving right
				if (currentPaddleX == 390) playedGame.setCurrentPaddleX(currentPaddleX); // Paddle already at rightmost side
				else playedGame.setCurrentPaddleX(++currentPaddleX); // Otherwise, increment paddle position by 1 unit to the right
				break;
			case "s": // Represents pausing the game
				playedGame.pause();
				break;
			default:
		}
	}
 	
    /**
     * This method does what Umple's Game.getWithName(â¦) method would do if it
     * worked properly aka get the game using the name. 
	 * @author Kelly Ma
	 * @author Georges Mourant
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

    /**
     * This method finds a block inside a list of blocks depending on its ID.
     * Author : Imane Chafi
     */

    public static Block findBlock(int id) { //Here, this is how the method was written in the solution document. 
        //I've emailed the teacher about this to have clarification, and whether the "." is necessary. 
        Game game = Block223Application.getCurrentGame();
        List<Block> blocks = game.getBlocks();//Here, I would need to get the current game first, put I need to ask the teacher about the Game.find to understand what it means.

        for (Block block : blocks) {

            int blockId = block.getId(); //Here, the type of blockID should be integer
            if (id == blockId) {
                return block;
            }
        }
        return null;
    }
	/**
	 * Private helper method to find the block assignment at a specific level
	 * author: Sabrina Chan 
	 * @param theLevel
	 * @param gridHorizontalPosition
	 * @param gridVerticalPosition
	 * @return
	 */

	private static BlockAssignment findBlockAssignment(Level theLevel, int gridHorizontalPosition, int gridVerticalPosition) {

		List<BlockAssignment> assignments = theLevel.getBlockAssignments();
		for(BlockAssignment assignment: assignments) {
			int h = assignment.getGridHorizontalPosition();
			int v = assignment.getGridVerticalPosition();

			if((h==gridHorizontalPosition)&&(v==gridVerticalPosition)){ 
				return assignment;
			}

		}
		return null;
	}
	
    /**
    *
    * This method return a TOBlock corresponding to the Block with a specific ID
    * within the current designable game.
    *
    * @author Mathieu Bissonnette
    *
    * @param id The id of the desired block.
    *
    * @return A TOBlock transfer object corresponding to the desired block.
    *
    * @throws InvalidInputException If the user is not an admin.
    * 
    * @throws InvalidInputException If no game is selected.
    * 
    * @throws InvalidInputException If the user is not the admin that created the game.
    * 
    * @throws InvalidInputException If the block doesn't exist.
    *
    */

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		
		// Verify that the user is an admin before proceeding.
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to access game information.");
        }
        
        // Get the current game and verify it's not null.
        Game game = Block223Application.getCurrentGame();
        if (game == null) {
        	throw new InvalidInputException("A game must be selected to access its information.");
        }
        
        // Verify that the user is the admin that created the current game.
        if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
        	throw new InvalidInputException("Only the admin who created the game can access its information.");
        }
        
        // Find the blocks and create the TOBlock.
		List<Block> blocks = game.getBlocks();
		TOBlock result = null;
		for (Block block : blocks) {
			if (block.getId() == id) {
				result = new TOBlock(id, block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
			}
		}
		
		// Throw an exception if the block is not found.
		if (result == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		
		// Return the result.
		return result;
	}
}
