package ca.mcgill.ecse223.block.tests.f07;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLUE;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLUE_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.GREEN;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.GREEN_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.POINTS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.POINTS_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.RED;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.RED_2;
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

public class UpdateBlockTests {

	private Block testBlock;

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		Game game = Block223TestUtil.initializeTestGame(block223, admin);
		testBlock = new Block(RED, GREEN, BLUE, POINTS, game);
	}

	// getBlockOfCurrentDesignableGame

	@Test
	public void testGetBlockOfCurrentDesignableGameSuccess() throws InvalidInputException {
		TOBlock block = Block223Controller.getBlockOfCurrentDesignableGame(testBlock.getId());

		assertEquals(testBlock.getRed(), block.getRed());
		assertEquals(testBlock.getGreen(), block.getGreen());
		assertEquals(testBlock.getBlue(), block.getBlue());
		assertEquals(testBlock.getPoints(), block.getPoints());
		assertEquals(testBlock.getId(), block.getId());
	}

	@Test
	public void testGetBlockOfCurrentDesignableGameNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to access game information.";
		try {
			Block223Controller.getBlockOfCurrentDesignableGame(testBlock.getId());
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testGetBlockOfCurrentDesignableGameNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to access its information.";
		try {
			Block223Controller.getBlockOfCurrentDesignableGame(testBlock.getId());
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testGetBlockOfCurrentDesignableGameDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can access its information.";
		try {
			Block223Controller.getBlockOfCurrentDesignableGame(testBlock.getId());
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	@Test
	public void testGetBlockOfCurrentDesignableGameNonExistingBlock() {
		String errorNoSuchBlock = "The block does not exist.";
		try {
			Block223Controller.getBlockOfCurrentDesignableGame(-1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSuchBlock);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSuchBlock, e.getMessage().trim());
		}
	}

	// udateBlock

	@Test
	public void testUpdateBlockSuccess() throws InvalidInputException {
		Block223Controller.updateBlock(testBlock.getId(), RED_2, GREEN_2, BLUE_2, POINTS_2);
		Block block = Block223Application.getCurrentGame().getBlocks().stream()
				.filter(b -> b.getId() == testBlock.getId()).iterator().next();
		assertEquals(testBlock.getId(), block.getId());
		assertEquals(RED_2, block.getRed());
		assertEquals(GREEN_2, block.getGreen());
		assertEquals(BLUE_2, block.getBlue());
		assertEquals(POINTS_2, block.getPoints());
	}

	@Test
	public void testUpdateBlockNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to update a block.";
		try {
			Block223Controller.updateBlock(testBlock.getId(), RED_2, GREEN_2, BLUE_2, POINTS_2);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testUpdateBlockNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to update a block.";
		try {
			Block223Controller.updateBlock(testBlock.getId(), RED_2, GREEN_2, BLUE_2, POINTS_2);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testUpdateBlockDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can update a block.";
		try {
			Block223Controller.updateBlock(testBlock.getId(), RED_2, GREEN_2, BLUE_2, POINTS_2);
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	@Test
	public void testUpdateBlockDuplicate() throws InvalidInputException {
		String errorDuplicateBlock = "A block with the same color already exists for the game.";
		Block223Controller.addBlock(RED_2, GREEN_2, BLUE_2, POINTS_2);
		try {
			Block223Controller.updateBlock(testBlock.getId(), RED_2, GREEN_2, BLUE_2, POINTS_2);
			fail(MISSING_EXPECTED_EXCEPTION + errorDuplicateBlock);
		} catch (InvalidInputException e) {
			assertEquals(errorDuplicateBlock, e.getMessage().trim());
		}
	}

	@Test
	public void testUpdateBlockNonExisting() throws InvalidInputException {
		String errorDuplicateBlock = "The block does not exist.";
		try {
			Block223Controller.updateBlock(-1, RED_2, GREEN_2, BLUE_2, POINTS_2);
			fail(MISSING_EXPECTED_EXCEPTION + errorDuplicateBlock);
		} catch (InvalidInputException e) {
			assertEquals(errorDuplicateBlock, e.getMessage().trim());
		}
	}

	@Test
	public void testUpdateBlockNegativeRed() {
		doInvalidUpdateBlock(-1, GREEN, BLUE, POINTS, "Red", 0, 255);
	}

	@Test
	public void testUpdateBlock256Red() {
		doInvalidUpdateBlock(256, GREEN, BLUE, POINTS, "Red", 0, 255);
	}

	@Test
	public void testUpdateBlockNegativeGreen() {
		doInvalidUpdateBlock(RED, -1, BLUE, POINTS, "Green", 0, 255);
	}

	@Test
	public void testUpdateBlock256Green() {
		doInvalidUpdateBlock(RED, 256, BLUE, POINTS, "Green", 0, 255);
	}

	@Test
	public void testUpdateBlockNegativeBlue() {
		doInvalidUpdateBlock(RED, GREEN, -1, POINTS, "Blue", 0, 255);
	}

	@Test
	public void testUpdateBlock256Blue() {
		doInvalidUpdateBlock(RED, GREEN, 256, POINTS, "Blue", 0, 255);
	}

	@Test
	public void testUpdateBlock0Points() {
		doInvalidUpdateBlock(RED, GREEN, BLUE, 0, "Points", 1, 1000);
	}

	@Test
	public void testUpdateBlock1001Points() {
		doInvalidUpdateBlock(RED, GREEN, BLUE, 1001, "Points", 1, 1000);
	}

	// extracted methods for better readability

	private void doInvalidUpdateBlock(int red, int green, int blue, int points, String subject, int lowerBound,
			int upperBound) {
		String errorIllegalBlock = subject + " must be between " + lowerBound + " and " + upperBound + ".";
		try {
			Block223Controller.updateBlock(testBlock.getId(), red, green, blue, points);
			fail(MISSING_EXPECTED_EXCEPTION + errorIllegalBlock);
		} catch (InvalidInputException e) {
			assertEquals(errorIllegalBlock, e.getMessage().trim());
		}
	}

}
