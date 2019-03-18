package ca.mcgill.ecse223.block.tests.f10;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLOCK_LEVEL;
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


public class RemoveBlockTests {

	private Block testBlock;
	private Game game;

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		game = Block223TestUtil.initializeTestGame(block223, admin);
		testBlock = new Block(RED, GREEN, BLUE, POINTS, game);
		new BlockAssignment(HORIZONTAL_POS, VERTICAL_POS, game.getLevel(BLOCK_LEVEL - 1), testBlock, game);
	}

	@Test
	public void testRemoveBlockSuccess() throws InvalidInputException {
		Block223Controller.removeBlock(BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
		List<BlockAssignment> blockAssignments = game.getLevel(BLOCK_LEVEL - 1).getBlockAssignments();
		assertEquals(0, blockAssignments.size());
	}

	@Test
	public void testRemoveBlockSuccessNoSuchAssignment() {
		try {
			Block223Controller.removeBlock(BLOCK_LEVEL + 1, 1, 1);
		} catch (InvalidInputException e) {
			fail("There was an exception while deleting a non-existing block assignment.");
		}
	}

	@Test
	public void testRemoveBlockNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to remove a block.";
		try {
			Block223Controller.removeBlock(BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testRemoveBlockNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to remove a block.";
		try {
			Block223Controller.removeBlock(BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testRemoveBlockDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can remove a block.";
		try {
			Block223Controller.removeBlock(BLOCK_LEVEL, HORIZONTAL_POS, VERTICAL_POS);
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

}
