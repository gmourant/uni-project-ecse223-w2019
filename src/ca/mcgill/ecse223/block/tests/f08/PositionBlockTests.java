package ca.mcgill.ecse223.block.tests.f08;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BALL_SPEED_INCREASE_FACTOR;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLUE;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLUE_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.GREEN;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.GREEN_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.LEVELS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MAX_PADDLE_LENGTH;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_BALL_SPEED_X;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_BALL_SPEED_Y;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_PADDLE_LENGTH;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.POINTS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.POINTS_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.RED;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.RED_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_NAME;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.HORIZONTAL_POS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.VERTICAL_POS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLOCK_LEVEL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class PositionBlockTests {

	private Block testBlock;
	private Game game;

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		game = Block223TestUtil.initializeTestGame(block223, admin);
		testBlock = new Block(RED, GREEN, BLUE, POINTS, game);
		BlockAssignment blockAssignment = new BlockAssignment(1, 1, game.getLevel(BLOCK_LEVEL - 1), testBlock, game);
		game.getLevel(BLOCK_LEVEL - 1).addBlockAssignment(blockAssignment);
	}

	// getBlocksAtLevelOfCurrentDesignableGame

	@Test
	public void testGetBlocksAtLevelOfCurrentDesignableGameSuccess() throws InvalidInputException {
		List<TOGridCell> cells = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(1);
		assertEquals(1, cells.size());
		TOGridCell toGridCell = cells.get(0);

		assertEquals(testBlock.getId(), toGridCell.getId());
		assertEquals(testBlock.getRed(), toGridCell.getRed());
		assertEquals(testBlock.getGreen(), toGridCell.getGreen());
		assertEquals(testBlock.getBlue(), toGridCell.getBlue());
		assertEquals(testBlock.getPoints(), toGridCell.getPoints());
	}

	@Test
	public void testGetBlocksAtLevelOfCurrentDesignableGameNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to access game information.";
		try {
			Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testGetBlocksAtLevelOfCurrentDesignableGameNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to access its information.";
		try {
			Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testGetBlocksAtLevelOfCurrentDesignableGameDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can access its information.";
		try {
			Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(1);
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	@Test
	public void testGetBlocksAtLevelOfCurrentDesignableGameLevelBelowMin() {
		doGetIllegalLevel(0);
	}

	@Test
	public void testGetBlocksAtLevelOfCurrentDesignableGameLevelAboveMax() {
		doGetIllegalLevel(LEVELS + 1);
	}

	// positionBlock

	@Test
	public void testPositionBlockSuccess() throws InvalidInputException {
		Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
		BlockAssignment blockAssignment = testBlock.getBlockAssignments().get(1);

		assertEquals(testBlock.getId(), blockAssignment.getBlock().getId());
		assertEquals(testBlock.getGame(), blockAssignment.getGame());
		assertEquals(testBlock.getGame().getLevel(BLOCK_LEVEL - 1), blockAssignment.getLevel());
		assertEquals(HORIZONTAL_POS, blockAssignment.getGridHorizontalPosition());
		assertEquals(VERTICAL_POS, blockAssignment.getGridVerticalPosition());
	}

	@Test
	public void testPositionBlockNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to position a block.";
		try {
			Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testPositionBlockNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to position a block.";
		try {
			Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testPositionBlockDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can position a block.";
		try {
			Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	@Test
	public void testPositionBlockLevelBelowMin() {
		doPositionBlockIllegalLevel(0);
	}

	@Test
	public void testPositionBlockLevelAboveMax() {
		doPositionBlockIllegalLevel(LEVELS + 1);
	}

	@Test
	public void testPositionBlockMaxBlocksExceeded() throws InvalidInputException {
		int restrictedMaxBlocksPerLevel = 1;
		Block223Controller.setGameDetails(LEVELS, restrictedMaxBlocksPerLevel, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y,
				BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);

		String errorMaxNrBlocksExceeded = "The number of blocks has reached the maximum number ("
				+ restrictedMaxBlocksPerLevel + ") allowed for this game.";
		try {
			Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, 1, 2);
			fail(MISSING_EXPECTED_EXCEPTION + errorMaxNrBlocksExceeded);
		} catch (InvalidInputException e) {
			assertEquals(errorMaxNrBlocksExceeded, e.getMessage().trim());
		}
	}

	@Test
	public void testPositionBlockBlockAlreadyExistsAtLocation() throws InvalidInputException {
		Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
		Block223Controller.addBlock(RED_2, GREEN_2, BLUE_2, POINTS_2);
		TOBlock testTOBlock2 = Block223Controller.getBlocksOfCurrentDesignableGame().get(1);
		String errorBlockAlreadyExistsAtLocation = "A block already exists at location 1/1.";
		try {
			Block223Controller.positionBlock(testTOBlock2.getId(), BLOCK_LEVEL, 1, 1);
			fail(MISSING_EXPECTED_EXCEPTION + errorBlockAlreadyExistsAtLocation);
		} catch (InvalidInputException e) {
			assertEquals(errorBlockAlreadyExistsAtLocation, e.getMessage().trim());
		}
	}

	@Test
	public void testPositionBlockNonExisting() throws InvalidInputException {
		String errorBlockDoesNotExist = "The block does not exist.";
		try {
			Block223Controller.positionBlock(-1, BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorBlockDoesNotExist);
		} catch (InvalidInputException e) {
			assertEquals(errorBlockDoesNotExist, e.getMessage().trim());
		}
	}

	public void testPositionBlock0HorizontalPos() throws InvalidInputException {
		int maxNrOfHorizontalBlocks = 15;
		String errorBlockHorizontalPosition = "The horizontal position must be between 1 and " + maxNrOfHorizontalBlocks
				+ ".";
		try {
			Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, 0, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorBlockHorizontalPosition);
		} catch (InvalidInputException e) {
			assertEquals(errorBlockHorizontalPosition, e.getMessage().trim());
		}
	}

	public void testPositionBlockHorizontalPosLimitExceeded() throws InvalidInputException {
		int maxNrOfHorizontalBlocks = 15;
		String errorBlockHorizontalPosition = "The horizontal position must be between 1 and " + maxNrOfHorizontalBlocks
				+ ".";
		try {
			Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, maxNrOfHorizontalBlocks + 1, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorBlockHorizontalPosition);
		} catch (InvalidInputException e) {
			assertEquals(errorBlockHorizontalPosition, e.getMessage().trim());
		}
	}

	public void testPositionBlock0VerticalPos() throws InvalidInputException {
		int maxNrOfVerticalBlocks = 15;
		String errorBlockVerticalPosition = "The vertical position must be between 1 and " + maxNrOfVerticalBlocks
				+ ".";
		try {
			Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, HORIZONTAL_POS, 0);
			fail(MISSING_EXPECTED_EXCEPTION + errorBlockVerticalPosition);
		} catch (InvalidInputException e) {
			assertEquals(errorBlockVerticalPosition, e.getMessage().trim());
		}
	}

	public void testPositionBlockVerticalPosLimitExceeded() throws InvalidInputException {
		int maxNrOfVerticalBlocks = 15;
		String errorBlockVerticalPosition = "The vertical position must be between 1 and " + maxNrOfVerticalBlocks
				+ ".";
		try {
			Block223Controller.positionBlock(testBlock.getId(), BLOCK_LEVEL, maxNrOfVerticalBlocks + 1, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorBlockVerticalPosition);
		} catch (InvalidInputException e) {
			assertEquals(errorBlockVerticalPosition, e.getMessage().trim());
		}
	}

	// extracted methods for better readability

	private void doGetIllegalLevel(int level) {
		String errorNoSuchLevel = "Level " + level + " does not exist for the game.";
		try {
			Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSuchLevel);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSuchLevel, e.getMessage().trim());
		}
	}

	private void doPositionBlockIllegalLevel(int level) {
		String errorNoSuchLevel = "Level " + level + " does not exist for the game.";
		try {
			Block223Controller.positionBlock(testBlock.getId(), level, HORIZONTAL_POS, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSuchLevel);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSuchLevel, e.getMessage().trim());
		}
	}

}
