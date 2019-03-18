package ca.mcgill.ecse223.block.tests.f01;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.EMPTY_NAME;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.TEST_GAME_NAME_1;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.TEST_GAME_NAME_2;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class AddGameTests {

	private Admin testAdminRole;

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		testAdminRole = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
	}

	@Test
	public void testCreateGameSuccess() throws InvalidInputException {
		Block223Controller.createGame(TEST_GAME_NAME_1);
		List<TOGame> games = Block223Controller.getDesignableGames();
		assertEquals(1, games.size());
		assertEquals(TEST_GAME_NAME_1, games.get(0).getName());
		assertEquals("The admin role does not match the one provided.", testAdminRole,
				Block223Application.getBlock223().getGames().get(0).getAdmin());
	}

	@Test
	public void testCreateTwoGamesWithDifferentName() throws InvalidInputException {
		Block223Controller.createGame(TEST_GAME_NAME_1);
		Block223Controller.createGame(TEST_GAME_NAME_2);
		assertEquals("Insufficient number of designable games.", 2, Block223Controller.getDesignableGames().size());
		Object[] games = Block223Controller.getDesignableGames().stream()
				.filter(it -> it.getName().contentEquals(TEST_GAME_NAME_2)).toArray();
		assertEquals("There should be only a single game with the name " + TEST_GAME_NAME_2 + ".", 1, games.length);
	}

	@Test
	public void testCreateGameNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to create a game.";
		try {
			Block223Controller.createGame(TEST_GAME_NAME_1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertEquals(errorNoAdminRights, e.getMessage().trim());
		}
	}

	@Test
	public void testCreateGameNull() {
		String errorNullGame = "The name of a game must be specified.";
		try {
			Block223Controller.createGame(null);
			fail(MISSING_EXPECTED_EXCEPTION + errorNullGame);
		} catch (InvalidInputException e) {
			String message = e.getMessage();
			assertEquals(errorNullGame, message.trim());
		}
	}

	@Test
	public void testCreateGameEmpty() {
		String errorEmptyGame = "The name of a game must be specified.";
		try {
			Block223Controller.createGame(EMPTY_NAME);
			fail(MISSING_EXPECTED_EXCEPTION + errorEmptyGame);
		} catch (InvalidInputException e) {
			assertEquals(errorEmptyGame, e.getMessage().trim());
		}
	}

	@Test
	public void testCreateTwoGamesWithSameName() {
		String errorNonUniqueGameName = "The name of a game must be unique.";
		try {
			Block223Controller.createGame(TEST_GAME_NAME_1);
			Block223Controller.createGame(TEST_GAME_NAME_1);
			fail(MISSING_EXPECTED_EXCEPTION + errorNonUniqueGameName);
		} catch (InvalidInputException e) {
			assertEquals(errorNonUniqueGameName, e.getMessage().trim());
		}
	}

}
