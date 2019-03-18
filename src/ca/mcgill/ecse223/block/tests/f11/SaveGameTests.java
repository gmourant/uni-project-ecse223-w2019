package ca.mcgill.ecse223.block.tests.f11;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BALL_SPEED_INCREASE_FACTOR;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLOCKS_PER_LEVEL;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLOCK_LEVEL;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLUE;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.GREEN;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.LEVELS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MAX_PADDLE_LENGTH;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_BALL_SPEED_X;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_BALL_SPEED_Y;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_PADDLE_LENGTH;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.POINTS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.RED;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.TEST_GAME_NAME_1;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.TEST_GAME_NAME_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_NAME;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_PASS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class SaveGameTests {

	private Block223 block223;

	@Before
	public void setUp() {
		block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		Game game = Block223TestUtil.initializeTestGame(block223, admin);
		Block testBlock = new Block(RED, GREEN, BLUE, POINTS, game);
		new BlockAssignment(1, 1, game.getLevel(BLOCK_LEVEL - 1), testBlock, game);
	}

	@Test
	public void testSaveGameSuccess() throws InvalidInputException {
		Block223Controller.saveGame();
		block223.delete();
		Block223Application.setCurrentGame(null);
		Block223Application.setCurrentUserRole(null);

		Block223 loadedBlock223 = Block223Persistence.load();
		assertEquals(1, loadedBlock223.getGames().size());
		Game loadedGame = loadedBlock223.getGame(0);
		assertEquals(LEVELS, loadedGame.getLevels().size());
		List<Block> loadedBlocks = loadedGame.getBlocks();
		assertEquals(1, loadedBlocks.size());
		List<BlockAssignment> loadedBlockAssignments = loadedGame.getBlockAssignments();
		assertEquals(1, loadedBlockAssignments.size());
		assertEquals(loadedBlocks.get(0), loadedBlockAssignments.get(0).getBlock());
		assertEquals(1, loadedBlockAssignments.get(0).getGridVerticalPosition());
		assertEquals(1, loadedBlockAssignments.get(0).getGridVerticalPosition());
	}

	@Test
	public void testSaveGameReinitializedGame() throws InvalidInputException {
		persistAndReload();

		Game newGame = new Game(TEST_GAME_NAME_2, BLOCKS_PER_LEVEL, new Admin(ADMIN_PASS, block223), MIN_BALL_SPEED_X,
				MIN_BALL_SPEED_Y, BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH, block223);
		assertFalse(newGame.setName(TEST_GAME_NAME_1));
	}

	@Test
	public void testSaveGameReinitializedUser() throws InvalidInputException {
		new User(USER_NAME, block223, new Player(USER_PASS, block223), new Player(USER_PASS, block223));
		persistAndReload();

		String errorNonUniqueUserName = "Cannot create due to duplicate username";
		try {
			new User(USER_NAME, block223, new Player(USER_PASS, block223));
			fail(MISSING_EXPECTED_EXCEPTION + errorNonUniqueUserName);
		} catch (RuntimeException e) {
			assertEquals(errorNonUniqueUserName, e.getMessage().trim());
		}
	}

	@Test
	public void testSaveGameReinitializedBlocks() throws InvalidInputException {
		persistAndReload();

		String errorBlockIdsNonUnique = "Non unique block IDs - block IDs were not reinitialized when loaded?";
		try {
			new Block(RED + 10, GREEN + 10, BLUE + 10, POINTS + 10, block223.getGame(0));
			List<Block> blocks = block223.getGame(0).getBlocks();
			assertNotEquals(blocks.get(0).getId(), blocks.get(1).getId());
		} catch (RuntimeException e) {
			fail(errorBlockIdsNonUnique);
		}
	}

	@Test
	public void testSaveGameNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to save a game.";
		try {
			Block223Controller.saveGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testSaveGameNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to save it.";
		try {
			Block223Controller.saveGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testSaveGameDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can save it.";
		try {
			Block223Controller.saveGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	// extracted methods for better readability

	private void persistAndReload() throws InvalidInputException {
		Block223Persistence.save(block223);

		block223.delete();
		Block223Application.setCurrentGame(null);
		Block223Application.setCurrentUserRole(null);

		block223 = Block223Persistence.load();
	}

}
