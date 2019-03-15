package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import javax.management.RuntimeErrorException;

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

        // Get block223 and admin
        Block223 block223 = Block223Application.getBlock223();
        Admin admin = (Admin) Block223Application.getCurrentUserRole();
        
        // Check for empty name
        if (name.isEmpty()) throw new InvalidInputException("The name of a game must be specified.");
        
        // Check for uniqueness of game name
        boolean unique = true;
        for (Game aGame : block223.getGames()) {
   	     	if (aGame.getName().equals(name)) {
   	     		unique = false;
   	     	}
   	  	}
        if (!unique) throw new InvalidInputException("The name of a game must be unique.");
        
        // Create then add game
        Game game = new Game(name, 1, admin, 1, 1, 1, 10, 10, block223);
        try {
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
        if (!currentName.equals(name)) {
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
            if ((red < 0) || (red > 255)) {
                throw new InvalidInputException("Red must be between 0 and 255.");
            }

            if ((green < 0) || (green > 255)) {
                throw new InvalidInputException("Green must be between 0 and 255.");
            }

            if ((blue < 0) || (blue > 255)) {
                throw new InvalidInputException("Blue must be between 0 and 255.");
            }

            if ((points < 1) || (red > 1000)) {
                throw new InvalidInputException("Points must be between 1 and 1000.");
            }
        }
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
        if (Block223Application.getCurrentGame() == null) {
            throw new InvalidInputException("A game must be selected to delete a block");
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
            throw new InvalidInputException("Admin privileges are required to access game information.");
        }
        
        // Verify that a game is selected.
        if (Block223Application.getCurrentGame() == null) {
        	throw new InvalidInputException("A game must be selected to access its information.");	
        }
        
        // Verify that the user is the admin that created the current game.
        if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
        	throw new InvalidInputException("Only the admin who created the game can access its information.");
        }
        
        // Get the desired block.
        Block foundBlock = findBlock(id);
        if (foundBlock == null) {
            throw new InvalidInputException("The block does not exist.");
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
        Level foundLevel = null;
        try {
        	foundLevel = game.getLevel(level);
        } catch (IndexOutOfBoundsException e) {
        	throw new InvalidInputException("Level " + level + " does not exist for this game.");
        }
        
        // Verify the level is not a maximum block capacity.
        List<BlockAssignment> assignments = foundLevel.getBlockAssignments();
        if (assignments.size() == game.getNrBlocksPerLevel()) {
        	throw new InvalidInputException("The number of blocks has reached the maximum number (" + game.getNrBlocksPerLevel() +") allowed for this game.");
        }
        
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
        	throw new InvalidInputException("The block does not exist");
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
				throw new InvalidInputException("The passwords have to be different.");
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
				player = null;
				if(e.getMessage().equals("The username has already been taken.")) {//check for generic error message
					// delete player instance 
					throw new InvalidInputException("The username must be specified.");//specific error message
				}
				throw new InvalidInputException("The username has already been taken.");
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
	 * Author: Sabrina Chan
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
			throw new InvalidInputException("Admin privileges are required to remove a block.");
		}

		// invalid input exception if the current game isn't selected
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to remove a block.");
		}

		//invalid input exception is the user isn't current admin of the game
		if(!(Block223Application.getCurrentGame().getAdmin().equals(Block223Application.getCurrentUserRole()) )) {
			throw new InvalidInputException("Only the admin who created the game can remove a block.");
		}

		// get the current game
		Game game = Block223Application.getCurrentGame();

		// get the selected level and check if the level is within the bounds
		Level selectedLevel;
		try{
			selectedLevel = game.getLevel(level);
		}
		catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
		}

		// find the block assignment
		BlockAssignment assignment = findBlockAssignment(selectedLevel, oldGridHorizontalPosition, oldGridVerticalPosition);
		if((assignment == null)) {
			throw new InvalidInputException("A block does not exist at location" + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".");
		}

		// set the new horizontal position for the block and check if the position is available
		try{
			assignment.setGridHorizontalPosition(newGridHorizontalPosition);
		}
		catch (RuntimeErrorException e) {
			throw new InvalidInputException(e.getLocalizedMessage());
		}	
			// set the new vertical position for the block and check if the position is available
		try{
			assignment.setGridVerticalPosition(newGridVerticalPosition);
		}
		catch (RuntimeErrorException e) {
			throw new InvalidInputException(e.getLocalizedMessage());
		}	

	}
	/**
	 * Author: Sabrina Chan
	 * @param level
	 * @param gridHorizontalPosition
	 * @param gridVerticalPosition
	 * @throws InvalidInputException
	 */
	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {

		// invalid input exception statements
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to remove a block.");
		}
		// checks if a game is selected
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to remove a block.");
		}
		// checks if the user is an admin
		if(!(Block223Application.getCurrentUserRole() instanceof Admin )) {
			throw new InvalidInputException("Only the admin who created the game can remove a block.");
		}

		// get the current game
		Game game = Block223Application.getCurrentGame();

		// get the selected level
		Level selectedLevel =  game.getLevel(level);

		// find the block assignment
		BlockAssignment assignment = findBlockAssignment(selectedLevel, gridHorizontalPosition, gridVerticalPosition);

		// deleting the block
		if(assignment != null) {
			assignment.delete();
		}

	}
	
	/**
	 * This method pauses a game
	 */
	public static void pauseGame() {
		
	}
	
	/**
	 * This method moves the paddle one pixel to the left or right
	 * @author Kelly Ma
	 * @param direction Describes left or right
	 */
	public static void movePaddle(String direction) {
		
		// Obtain current paddle and its positions
		GameSession game = Block223Application.getCurrentGameSession();
		SpecificPaddle paddle = game.getSpecificPaddle();
		int currentPositionX = paddle.getPositionX();
		
		switch(direction) {
			case "l":
				// move to the left one pixel
				paddle.setPositionX(--currentPositionX);
				break;
			case "r":
				// move to the right one pixel
				paddle.setPositionX(++currentPositionX);
				break;
			default:
		}
	}

    // ****************************
    // Query methods
    // ****************************
	
	/**
	 * This method returns a list of transfer objects containing hall of fame entries
	 * @author Kelly Ma
	 * @return A list of hall of fame entries
	 */
	public static List<TOHallOfFameEntry> getHallOfFameEntries() throws InvalidInputException{
		
		Game game = Block223Application.getCurrentGame(); // Get current game
		if (game == null) throw new InvalidInputException("A game must be selected to view Hall Of Fame.");
		
        List<TOHallOfFameEntry> hallOfFame = new ArrayList(); // Create TO list
        List<HallOfFameEntry> entries = game.getHallOfFameEntries(); // Obtain list of entries
        
        for (HallOfFameEntry hallOfFameEntry : entries) {
        	// Get username and score for an entry
        	String username = hallOfFameEntry.getUser().getUsername();
        	int score = hallOfFameEntry.getScore();
        	
        	TOHallOfFameEntry to = new TOHallOfFameEntry(username, score);
        	hallOfFame.add(to); // Add entry to hallOfFame
        }
        
        return hallOfFame;
	}
	
	
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
        if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
            throw new InvalidInputException("Admin privileges are required to access game information.");
        }
        if (Block223Application.getCurrentGame() == null) {
            throw new InvalidInputException("A game must be selected to access its information.");
        }
        if (Block223Application.getCurrentUserRole() != game.getAdmin()) {
            throw new InvalidInputException("Only the admin who created the game can acess its information.");
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
        Level foundLevel = null;
        try {
        	foundLevel = game.getLevel(level);
        } catch (IndexOutOfBoundsException e) {
        	throw new InvalidInputException("Level " + level + " does not exist for this game.");
        }

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

    // ****************************
    // Private Helper Methods
    // ****************************
    /**
     * This method does what Umple's Game.getWithName(…) method would do if it
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
}
