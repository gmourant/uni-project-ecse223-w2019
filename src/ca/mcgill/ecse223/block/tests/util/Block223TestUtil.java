package ca.mcgill.ecse223.block.tests.util;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BALL_SPEED_INCREASE_FACTOR;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.BLOCKS_PER_LEVEL;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.LEVELS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MAX_PADDLE_LENGTH;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_BALL_SPEED_X;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_BALL_SPEED_Y;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MIN_PADDLE_LENGTH;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.TEST_GAME_NAME_1;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;

public class Block223TestUtil {

	public static Block223 initializeTestBlock223() {
		Block223 block223 = Block223Application.getBlock223();
		block223.delete();
		return block223;
	}
	
	public static Admin createAndAssignAdminRoleToBlock223(Block223 block223) {
		Admin testAdminRole = new Admin(ADMIN_PASS, block223);
		Block223Application.setCurrentUserRole(testAdminRole);
		return testAdminRole;
	}
	
	public static Game initializeTestGame(Block223 block223, Admin admin) {
		Game aGame = new Game(TEST_GAME_NAME_1, BLOCKS_PER_LEVEL, admin, MIN_BALL_SPEED_X, MIN_BALL_SPEED_Y, BALL_SPEED_INCREASE_FACTOR, MAX_PADDLE_LENGTH, MIN_PADDLE_LENGTH, block223);
		for (int i = 0; i < LEVELS; i++) {
			aGame.addLevel();
		}
		Block223Application.setCurrentGame(aGame);
		return aGame;
	}
	
}