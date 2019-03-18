package ca.mcgill.ecse223.block.tests.f04;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BALL_SPEED_INCREASE_FACTOR;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLOCKS_PER_LEVEL;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.LEVELS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MAX_PADDLE_LENGTH;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_BALL_SPEED_X;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_BALL_SPEED_Y;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_PADDLE_LENGTH;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.TEST_GAME_NAME_1;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.TEST_GAME_NAME_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class UpdateGameTests {

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		Block223TestUtil.initializeTestGame(block223, admin);
	}

	// selectGame

	@Test
	public void testSelectGameSuccess() throws InvalidInputException {
		Block223Controller.selectGame(TEST_GAME_NAME_1);
		String currentGameName = Block223Application.getCurrentGame().getName();
		assertEquals(TEST_GAME_NAME_1, currentGameName);
	}

	@Test
	public void testSelectGameNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to select a game.";
		try {
			Block223Controller.selectGame(TEST_GAME_NAME_1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertEquals(errorNoAdminRights, e.getMessage().trim());
		}
	}

	@Test
	public void testSelectGameDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can select the game.";
		try {
			Block223Controller.selectGame(TEST_GAME_NAME_1);
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorDifferentAdmin));
		}
	}

	@Test
	public void testSelectGameNonExistingGame() {
		String errorGameNonExisting = "A game with name " + TEST_GAME_NAME_2 + " does not exist.";
		try {
			Block223Controller.selectGame(TEST_GAME_NAME_2);
			fail(MISSING_EXPECTED_EXCEPTION + errorGameNonExisting);
		} catch (InvalidInputException e) {
			assertEquals(errorGameNonExisting, e.getMessage().trim());
		}
	}

	// getCurrentDesignableGame

	@Test
	public void testGetCurrentDesignableSuccess() throws InvalidInputException {
		TOGame currentDesignableGame = Block223Controller.getCurrentDesignableGame();
		assertEquals(TEST_GAME_NAME_1, currentDesignableGame.getName());
	}

	@Test
	public void testGetCurrentDesignableGameNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to access game information.";
		try {
			Block223Controller.getCurrentDesignableGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testGetCurrentDesignableGameNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to access its information.";
		try {
			Block223Controller.getCurrentDesignableGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testGetCurrentDesignableGameDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can access its information.";
		try {
			Block223Controller.getCurrentDesignableGame();
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	// updateGame

	@Test
	public void testUpdateGameSuccessLevelsIncreased() throws InvalidInputException {
		List<Level> allOldLevels = new ArrayList<Level>(Block223Application.getCurrentGame().getLevels());
		int nrLevels = LEVELS + 1;
		int nrBlocksPerLevel = BLOCKS_PER_LEVEL + 1;
		int minBallSpeedX = MIN_BALL_SPEED_X + 1;
		int minBallSpeedY = MIN_BALL_SPEED_Y + 1;
		double ballSpeedIncreaseFactor = BALL_SPEED_INCREASE_FACTOR + 1.0;
		int maxPaddleLength = MAX_PADDLE_LENGTH + 1;
		int minPaddleLength = MIN_PADDLE_LENGTH + 1;

		Block223Controller.updateGame(TEST_GAME_NAME_2, nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
				ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);

		TOGame cdg = Block223Controller.getCurrentDesignableGame();

		assertEquals(TEST_GAME_NAME_2, cdg.getName());
		assertEquals(nrLevels, cdg.getNrLevels());
		assertEquals(nrBlocksPerLevel, cdg.getNrBlocksPerLevel());
		assertEquals(minBallSpeedX, cdg.getMinBallSpeedX());
		assertEquals(minBallSpeedY, cdg.getMinBallSpeedY());
		assertEquals(ballSpeedIncreaseFactor, cdg.getBallSpeedIncreaseFactor(), 0.01);
		assertEquals(maxPaddleLength, cdg.getMaxPaddleLength());
		assertEquals(minPaddleLength, cdg.getMinPaddleLength());
		assertLevels(allOldLevels);
	}

	@Test
	public void testUpdateGameSuccessLevelsUnchanged() throws InvalidInputException {
		int nrLevels = LEVELS;
		List<Level> allOldLevels = new ArrayList<Level>(Block223Application.getCurrentGame().getLevels());
		
		Block223Controller.updateGame(TEST_GAME_NAME_2, nrLevels, BLOCKS_PER_LEVEL, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y,
				BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);

		TOGame cdg = Block223Controller.getCurrentDesignableGame();

		assertEquals(TEST_GAME_NAME_2, cdg.getName());
		assertEquals(nrLevels, cdg.getNrLevels());
		
		assertLevels(allOldLevels);
	}

	@Test
	public void testUpdateGameSuccessLevelsDecreased() throws InvalidInputException {
		int nrLevels = LEVELS - 1;

		List<Level> allOldLevels = new ArrayList<Level>(Block223Application.getCurrentGame().getLevels());

		Block223Controller.updateGame(TEST_GAME_NAME_2, nrLevels, BLOCKS_PER_LEVEL, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y,
				BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);

		TOGame cdg = Block223Controller.getCurrentDesignableGame();

		assertEquals(TEST_GAME_NAME_2, cdg.getName());
		assertEquals(nrLevels, cdg.getNrLevels());
		assertLevels(allOldLevels);
	}

	@Test
	public void testUpdateGameNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to define game settings.";
		try {
			doUpdateGame(TEST_GAME_NAME_1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testUpdateGameNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to define game settings.";
		try {
			doUpdateGame(TEST_GAME_NAME_1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}

	@Test
	public void testUpdateGameDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can define its game settings.";
		try {
			doUpdateGame(TEST_GAME_NAME_1);
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	@Test
	public void testUpdateGameNameNonUnique() throws InvalidInputException {
		Block223Controller.createGame(TEST_GAME_NAME_2);
		String errorNameNonUnique = "The name of a game must be unique.";
		try {
			doUpdateGame(TEST_GAME_NAME_2);
			fail(MISSING_EXPECTED_EXCEPTION + errorNameNonUnique);
		} catch (InvalidInputException e) {
			assertEquals(errorNameNonUnique, e.getMessage().trim());
		}
	}

	@Test
	public void testUpdateGameNameNull() throws InvalidInputException {
		String errorNameNull = "The name of a game must be specified.";
		try {
			doUpdateGame(null);
			fail(MISSING_EXPECTED_EXCEPTION + errorNameNull);
		} catch (InvalidInputException e) {
			assertEquals(errorNameNull, e.getMessage().trim());
		}
	}

	@Test
	public void testUpdateGameNameEmpty() throws InvalidInputException {
		String errorNameNull = "The name of a game must be specified.";
		try {
			doUpdateGame("");
			fail(MISSING_EXPECTED_EXCEPTION + errorNameNull);
		} catch (InvalidInputException e) {
			assertEquals(errorNameNull, e.getMessage().trim());
		}
	}

	// extracted methods for better readability

	private void doUpdateGame(String name) throws InvalidInputException {
		int nrLevels = LEVELS + 1;
		int nrBlocksPerLevel = BLOCKS_PER_LEVEL + 1;
		int minBallSpeedX = MIN_BALL_SPEED_X + 1;
		int minBallSpeedY = MIN_BALL_SPEED_Y + 1;
		double ballSpeedIncreaseFactor = BALL_SPEED_INCREASE_FACTOR + 1.0;
		int maxPaddleLength = MAX_PADDLE_LENGTH + 1;
		int minPaddleLength = MIN_PADDLE_LENGTH + 1;
		Block223Controller.updateGame(name, nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
				ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
	}
	
	private void assertLevels(List<Level> allOldLevels) {
		List<Level> allLevels = Block223Application.getCurrentGame().getLevels();
		if (allOldLevels.size() > allLevels.size()) {
			assertTrue(allOldLevels.containsAll(allLevels));
		} else {
			assertTrue(allLevels.containsAll(allOldLevels));
		}
	}
	
}
