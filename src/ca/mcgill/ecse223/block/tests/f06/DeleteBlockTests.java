package ca.mcgill.ecse223.block.tests.f06;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLUE;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.GREEN;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.POINTS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.RED;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class DeleteBlockTests {

	private Block testBlock;

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		Game game = Block223TestUtil.initializeTestGame(block223, admin);
		testBlock = new Block(RED, GREEN, BLUE, POINTS, game);
	}

	// getBlocksOfCurrentDesignableGame

	@Test
	public void testGetBlocksOfCurrentDesignableGameSuccess() throws InvalidInputException {

		TOBlock toBlock = Block223Controller.getBlocksOfCurrentDesignableGame().get(0);

		assertEquals(testBlock.getId(), toBlock.getId());
		assertEquals(RED, toBlock.getRed());
		assertEquals(GREEN, toBlock.getGreen());
		assertEquals(BLUE, toBlock.getBlue());
		assertEquals(POINTS, toBlock.getPoints());
	}

	@Test
	public void testGetBlocksOfCurrentDesignableGameNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to access game information.";
		try {
			Block223Controller.getBlocksOfCurrentDesignableGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testGetBlocksOfCurrentDesignableGameNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to access its information.";
		try {
			Block223Controller.getBlocksOfCurrentDesignableGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testGetBlocksOfCurrentDesignableGameDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can access its information.";
		try {
			Block223Controller.getBlocksOfCurrentDesignableGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	// deleteBlock

	@Test
	public void testDeleteBlockSuccess() throws InvalidInputException {
		int originalSize = Block223Application.getCurrentGame().getBlocks().size();

		Block223Controller.deleteBlock(testBlock.getId());

		assertEquals(1, originalSize - Block223Application.getCurrentGame().getBlocks().size());
	}

	@Test
	public void testDeleteBlockNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to delete a block.";
		try {
			Block223Controller.deleteBlock(testBlock.getId());
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testDeleteBlockNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to delete a block.";
		try {
			Block223Controller.deleteBlock(testBlock.getId());
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testDeleteBlockDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can delete a block.";
		try {
			Block223Controller.deleteBlock(testBlock.getId());
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}
	
	@Test
	public void testDeleteBlockNoSuchBlock() throws InvalidInputException {
		try {
			Block223Controller.deleteBlock(-1);
		} catch (InvalidInputException e) {
			fail("There was an exception while deleting a non-existing block"
					+ ".");
		}
	}

}
