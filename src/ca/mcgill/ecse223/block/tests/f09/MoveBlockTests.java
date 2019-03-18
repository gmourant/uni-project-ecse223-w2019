package ca.mcgill.ecse223.block.tests.f09;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLOCK_LEVEL;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.LEVELS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLUE;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.GREEN;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.HORIZONTAL_POS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.POINTS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.RED;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_NAME;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.VERTICAL_POS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class MoveBlockTests {

	private Block testBlock;
	private int aGridHorizontalPosition = 1;
	private int aGridVerticalPosition = 1;
	private Game game;

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		game = Block223TestUtil.initializeTestGame(block223, admin);
		testBlock = new Block(RED, GREEN, BLUE, POINTS, game);
		new BlockAssignment(aGridHorizontalPosition, aGridVerticalPosition,
				game.getLevel(BLOCK_LEVEL - 1), testBlock, game);
	}

	// moveBlock

	@Test
	public void testMoveBlockSuccess() throws InvalidInputException {
		Block223Controller.moveBlock(BLOCK_LEVEL, aGridHorizontalPosition, aGridVerticalPosition, HORIZONTAL_POS,
				VERTICAL_POS);
		List<BlockAssignment> blockAssignments = game.getLevel(BLOCK_LEVEL - 1).getBlockAssignments();
		assertEquals(1, blockAssignments.size());
		BlockAssignment blockAssignment = blockAssignments.get(0);
		assertEquals(testBlock, blockAssignment.getBlock());
		assertEquals(HORIZONTAL_POS, blockAssignment.getGridHorizontalPosition());
		assertEquals(VERTICAL_POS, blockAssignment.getGridVerticalPosition());
	}

	@Test
	public void testMoveBlockNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to move a block.";
		try {
			Block223Controller.moveBlock(BLOCK_LEVEL, aGridHorizontalPosition, aGridVerticalPosition, HORIZONTAL_POS,
					VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testMoveBlockNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to move a block.";
		try {
			Block223Controller.moveBlock(BLOCK_LEVEL, aGridHorizontalPosition, aGridVerticalPosition, HORIZONTAL_POS,
					VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testMoveBlockDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can move a block.";
		try {
			Block223Controller.moveBlock(BLOCK_LEVEL, aGridHorizontalPosition, aGridVerticalPosition, HORIZONTAL_POS,
					VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	@Test
	public void testMoveBlockFromNonExistentLocation() {
		int horizontalEmptyLocation = 3;
		int verticalEmptyLocation = 3;
		String errorMoveFromNonExistingLocation = "A block does not exist at location " + horizontalEmptyLocation + "/"
				+ verticalEmptyLocation + ".";
		try {
			Block223Controller.moveBlock(BLOCK_LEVEL, horizontalEmptyLocation, verticalEmptyLocation, HORIZONTAL_POS,
					VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorMoveFromNonExistingLocation);
		} catch (InvalidInputException e) {
			assertEquals(errorMoveFromNonExistingLocation, e.getMessage().trim());
		}
	}

	@Test
	public void testMoveBlockToTakenLocation() {
		int horizontalTakenLocation = 3;
		int verticalTakenLocation = 3;
		new BlockAssignment(horizontalTakenLocation, verticalTakenLocation, game.getLevel(BLOCK_LEVEL - 1), testBlock,
				game);
		String errorMoveToTakenLocation = "A block already exists at location " + horizontalTakenLocation + "/"
				+ verticalTakenLocation + ".";
		try {
			Block223Controller.moveBlock(BLOCK_LEVEL, aGridHorizontalPosition, aGridVerticalPosition,
					horizontalTakenLocation, verticalTakenLocation);
			fail(MISSING_EXPECTED_EXCEPTION + errorMoveToTakenLocation);
		} catch (InvalidInputException e) {
			assertEquals(errorMoveToTakenLocation, e.getMessage().trim());
		}
	}

	@Test
	public void testMoveBlockHorizontalLowerLimitExceeded() {
		int limit = 15;
		doMoveToIllegalLocation(limit, 0, VERTICAL_POS, "horizontal");
	}

	@Test
	public void testMoveBlockHorizontalUpperLimitExceeded() {
		int limit = 15;
		doMoveToIllegalLocation(limit, limit + 1, VERTICAL_POS, "horizontal");
	}

	@Test
	public void testMoveBlockVerticalLowerLimitExceeded() {
		int limit = 15;
		doMoveToIllegalLocation(limit, HORIZONTAL_POS, 0, "vertical");
	}

	@Test
	public void testMoveBlockVerticalUpperLimitExceeded() {
		int limit = 15;
		doMoveToIllegalLocation(limit, HORIZONTAL_POS, limit + 1, "vertical");
	}

	@Test
	public void testMoveBlockToLevel0() {
		String errorVerticalLimitExceeded = "Level 0 does not exist for the game.";
		try {
			Block223Controller.moveBlock(0, aGridHorizontalPosition, aGridVerticalPosition, HORIZONTAL_POS,
					VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorVerticalLimitExceeded);
		} catch (InvalidInputException e) {
			assertEquals(errorVerticalLimitExceeded, e.getMessage().trim());
		}
	}

	@Test
	public void testMoveBlockToLevelOverMax() {
		String errorVerticalLimitExceeded = "Level " + (LEVELS + 1) + " does not exist for the game.";
		try {
			Block223Controller.moveBlock(LEVELS + 1, aGridHorizontalPosition, aGridVerticalPosition, HORIZONTAL_POS,
					VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorVerticalLimitExceeded);
		} catch (InvalidInputException e) {
			assertEquals(errorVerticalLimitExceeded, e.getMessage().trim());
		}
	}

	// extracted methods for better readability

	private void doMoveToIllegalLocation(int limit, int hPos, int vPos, String posType) {
		String errorVerticalLimitExceeded = "The " + posType + " position must be between 1 and " + limit + ".";
		try {
			Block223Controller.moveBlock(BLOCK_LEVEL, aGridHorizontalPosition, aGridVerticalPosition, hPos, vPos);
			fail(MISSING_EXPECTED_EXCEPTION + errorVerticalLimitExceeded);
		} catch (InvalidInputException e) {
			assertEquals(errorVerticalLimitExceeded, e.getMessage().trim());
		}
	}

}
