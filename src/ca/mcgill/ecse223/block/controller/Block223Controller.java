package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;

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

	//Methods to implement : Add Block and Delete Block
	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
		String error = "";
		if(Block223Application.currentUserRole != UserRole.Admin) {
			throw new InvalidInputException("Admin privileges are required to add a block.");
		}
		if(Block223Application.currentGame == null) {
			throw new InvalidInputException("A game must be selected to add a block");
		}
		if(Block223Application.currentUserRole != getCurrentDesignableGame.Admin ) {
			throw new InvalidInputException("A game must be selected to add a block");
		}
		//Needs if statement for the check of another block
		Game game = Block223Application.getCurrentGame();
		try {
			game.addBlock(red, green, blue, points); //Can I do it like this instead of "create(..)"?
			Block223Persistence.save(block223);//Should this be here if we save from the current game?
		}
		catch (RuntimeException e) { //Do I need to make catch and rethrow statements individually?
			error = e.getMessage();
			if ((red < 0) || (red > 255))
				throw new InvalidInputException("Red must be between 0 and 255.");
			
			if ((green < 0) || (green > 255))
				throw new InvalidInputException("Green must be between 0 and 255.");
			
			if ((blue < 0) || (blue > 255))
				throw new InvalidInputException("Blue must be between 0 and 255.");
			
			if ((points < 1) || (red > 1000))
				throw new InvalidInputException("Points must be between 1 and 1000.");
		}
		//block223 = Block223Persistence.load(); Where should this be put if we want to load the game after we have created the block? 
	}

	public static void deleteBlock(int id) throws InvalidInputException {
		if(Block223Application.currentUserRole != getUserMode()) {
			throw new InvalidInputException("Admin privileges are required to delete a block.");
		}
		if(Block223Application.currentGame == null) {
			throw new InvalidInputException("A game must be selected to delete a block");
		}
		if(Block223Application.currentUserRole != currentGame.Admin) {
			throw new InvalidInputException("Only the admin who created the block can delete the block");
		}
		
		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		
		if(block != null)
			block.delete();
	}
	
	public Block Game.findBlock(int id) { //Here, should we get the Game field beforehand or does the Game. do it for us?
		blocks = getBlocks();
		
		for (Block block : game.getBlocks()) {
			
			int blockId = block.getId(); //Here, the type of blockID should be integer
			if(id == blockId)
				return block;
		}
		return null;	
	}
	//End of method implementation
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
		if(Block223Application.currentUserRole != getUserMode())
			throw new InvalidInputException("Admin privileges are required to access game information.");
		if(Block223Application.currentGame != setCurrentGame())
			throw new InvalidInputException("A game must be selected to access its information");
		if(Block223Application.currentUSerRole != currentGame.Admin())
			throw new InvalidInputException("Only the admin who created the game can acess its information");
		
		Game game = Block223Application.getCurrentGame();
		List<TOBlock> result = new ArrayList<TOBlock>();
		//Block blocks = (Block) game.getBlocks(); Ask teacher how to implement this without casting it. 
		
		for (Block block : game.getBlocks()) {
			TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
			result.add(to);}
		return result;
		}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
	}

	public static TOUserMode getUserMode() {
	}

}
