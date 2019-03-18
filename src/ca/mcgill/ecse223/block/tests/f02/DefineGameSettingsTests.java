package ca.mcgill.ecse223.block.tests.f02;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BALL_SPEED_INCREASE_FACTOR;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLOCKS_PER_LEVEL;
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
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class DefineGameSettingsTests {

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		block223.addGame(new Game(TEST_GAME_NAME_1, 2, admin, 1, 1, 1, 10, 10, block223));
		Block223Application.setCurrentGame(block223.getGames().get(0));
	}

	@Test
	public void testSetGameDetailsSuccess() throws InvalidInputException {
		List<Level> allOldLevels = new ArrayList<Level>(Block223Application.getCurrentGame().getLevels());

		Block223Controller.setGameDetails(LEVELS, BLOCKS_PER_LEVEL, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y,
				BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
		Game currentGame = Block223Application.getCurrentGame();

		assertEquals(LEVELS, currentGame.getLevels().size());
		assertEquals(BLOCKS_PER_LEVEL, currentGame.getNrBlocksPerLevel());
		assertEquals(MIN_BALL_SPEED_X, currentGame.getBall().getMinBallSpeedX());
		assertEquals(MIN_BALL_SPEED_Y, currentGame.getBall().getMinBallSpeedY());
		assertEquals(BALL_SPEED_INCREASE_FACTOR, currentGame.getBall().getBallSpeedIncreaseFactor(), 0.01);
		assertEquals(MAX_PADDLE_LENGTH, currentGame.getPaddle().getMaxPaddleLength());
		assertEquals(MIN_PADDLE_LENGTH, currentGame.getPaddle().getMinPaddleLength());

		assertEquals(TEST_GAME_NAME_1, currentGame.getName());

		List<Level> allLevels = Block223Application.getCurrentGame().getLevels();
		if (allOldLevels.size() > allLevels.size()) {
			assertTrue(allOldLevels.containsAll(allLevels));
		} else {
			assertTrue(allLevels.containsAll(allOldLevels));
		}
	}

	@Test
	public void testSetGameDetailsNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to create a game.";
		try {
			Block223Controller.createGame(TEST_GAME_NAME_1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}

	@Test
	public void testSetGameDetailsNoCurrentGame() {
		Block223Application.setCurrentGame(null);
		String errorNoCurrentGame = "A game must be selected to define game settings.";
		try {
			Block223Controller.setGameDetails(LEVELS, BLOCKS_PER_LEVEL, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y,
					BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoCurrentGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoCurrentGame, e.getMessage().trim());
		}
	}

	@Test
	public void testSetGameDetailsDifferentAdmin() {
		Block223Application.setCurrentUserRole(new Admin(ADMIN_PASS, new Block223()));
		String errorDifferentAdmin = "Only the admin who created the game can define its game settings.";
		try {
			Block223Controller.setGameDetails(LEVELS, BLOCKS_PER_LEVEL, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y,
					BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	@Test
	public void testSetGameDetails0Levels() {
		int levels = 0;
		setInvalidNumberOfLevels(levels);
	}

	@Test
	public void testSetGameDetails100Levels() {
		int levels = 100;
		setInvalidNumberOfLevels(levels);
	}

	private void setInvalidNumberOfLevels(int levels) {
		String errorInvalidNrOfBlocks = "The number of levels must be between 1 and 99.";
		try {
			Block223Controller.setGameDetails(levels, BLOCKS_PER_LEVEL, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y,
					BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
			fail(MISSING_EXPECTED_EXCEPTION + errorInvalidNrOfBlocks);
		} catch (InvalidInputException e) {
			assertEquals(errorInvalidNrOfBlocks, e.getMessage().trim());
		}
	}

	@Test
	public void testSetGameDetails0BlocksPerLevel() {
		String errorInvalidNrOfBlocks = "The number of blocks per level must be greater than zero.";
		try {
			Block223Controller.setGameDetails(LEVELS, 0, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y, BALL_SPEED_INCREASE_FACTOR,
					MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
			fail(MISSING_EXPECTED_EXCEPTION + errorInvalidNrOfBlocks);
		} catch (InvalidInputException e) {
			assertEquals(errorInvalidNrOfBlocks, e.getMessage().trim());
		}
	}

	@Test
	public void testSetGameDetailsBlocksPerLevelLowerThanExistingBlocks() {
		Game currentGame = Block223Application.getCurrentGame();
		String errorBlockLimitTooLow = "The maximum number of blocks per level cannot be less than the number of existing blocks in a level.";
		Block block = new Block(RED, GREEN, BLUE, POINTS, currentGame);
		Level level = currentGame.addLevel();
		new BlockAssignment(1, 1, level, block, currentGame);
		new BlockAssignment(1, 2, level, block, currentGame);
		try {
			Block223Controller.setGameDetails(LEVELS, 1, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y, BALL_SPEED_INCREASE_FACTOR,
					MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
			fail(MISSING_EXPECTED_EXCEPTION + errorBlockLimitTooLow);
		} catch (InvalidInputException e) {
			assertEquals(errorBlockLimitTooLow, e.getMessage().trim());
		}
	}

	@Test
	public void testSetGameDetails0MinBallSpeedX() {
		int xSpeed = 0;
		int ySpeed = MIN_BALL_SPEED_Y;
		setBallSpeedOneComponentZero(xSpeed, ySpeed);
	}

	@Test
	public void testSetGameDetails0MinBallSpeedY() {
		int xSpeed = MIN_BALL_SPEED_X;
		int ySpeed = 0;
		setBallSpeedOneComponentZero(xSpeed, ySpeed);
	}

	private void setBallSpeedOneComponentZero(int xSpeed, int ySpeed) {
		try {
			Block223Controller.setGameDetails(LEVELS, BLOCKS_PER_LEVEL, xSpeed, ySpeed, BALL_SPEED_INCREASE_FACTOR,
					MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
		} catch (InvalidInputException e) {
			fail("Unexpected exception while setting ball speed to (" + xSpeed + "," + ySpeed + ")");
		}
	}

	@Test
	public void testSetGameDetails0MinBallSpeed() {
		int xSpeed = 0;
		int ySpeed = 0;
		String errorInvalidBallSpeed = "The minimum speed of the ball must be greater than zero.";
		try {
			Block223Controller.setGameDetails(LEVELS, BLOCKS_PER_LEVEL, xSpeed, ySpeed, BALL_SPEED_INCREASE_FACTOR,
					MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
			fail(MISSING_EXPECTED_EXCEPTION + errorInvalidBallSpeed);
		} catch (InvalidInputException e) {
			assertEquals(errorInvalidBallSpeed, e.getMessage().trim());
		}
	}

	@Test
	public void testSetGameDetailsNegativeSpeedIncrease() {
		String errorInvalidSpeedIncrease = "The speed increase factor of the ball must be greater than zero.";
		try {
			Block223Controller.setGameDetails(LEVELS, BLOCKS_PER_LEVEL, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y, -0.1,
					MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH);
			fail(MISSING_EXPECTED_EXCEPTION + errorInvalidSpeedIncrease);
		} catch (InvalidInputException e) {
			assertEquals(errorInvalidSpeedIncrease, e.getMessage().trim());
		}
	}

	@Test
	public void testSetGameDetails0MaxPaddleLength() {
		String errorInvalidMaxPaddleLength = "The maximum length of the paddle must be greater than zero and less than or equal to 390.";
		int paddleMaxLength = 0;
		int paddleMinLength = MIN_PADDLE_LENGTH;
		setInvalidPaddleLength(paddleMaxLength, paddleMinLength, errorInvalidMaxPaddleLength);
	}

	@Test
	public void testSetGameDetails391MaxPaddleLength() {
		String errorInvalidMaxPaddleLength = "The maximum length of the paddle must be greater than zero and less than or equal to 390.";
		int paddleMaxLength = 391;
		int paddleMinLength = MIN_PADDLE_LENGTH;
		setInvalidPaddleLength(paddleMaxLength, paddleMinLength, errorInvalidMaxPaddleLength);
	}

	@Test
	public void testSetGameDetails0MinPaddleLength() {
		String errorInvalidMinPaddleLength = "The minimum length of the paddle must be greater than zero.";
		int paddleMaxLength = MAX_PADDLE_LENGTH;
		int paddleMinLength = 0;
		setInvalidPaddleLength(paddleMaxLength, paddleMinLength, errorInvalidMinPaddleLength);
	}

	private void setInvalidPaddleLength(int paddleMaxLength, int paddleMinLength, String error) {
		try {
			Block223Controller.setGameDetails(LEVELS, BLOCKS_PER_LEVEL, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y,
					BALL_SPEED_INCREASE_FACTOR, paddleMaxLength, paddleMinLength);
			fail(MISSING_EXPECTED_EXCEPTION + error);
		} catch (InvalidInputException e) {
			assertEquals(error, e.getMessage().trim());
		}
	}

}
