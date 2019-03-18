package ca.mcgill.ecse223.block.tests.f05;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class AddBlockTests {

	@Before
	public void setUp() {
		Block223 block223 = Block223TestUtil.initializeTestBlock223();
		Admin admin = Block223TestUtil.createAndAssignAdminRoleToBlock223(block223);
		Block223TestUtil.initializeTestGame(block223, admin);
	}

	@Test
	public void testAddBlockSuccess() throws InvalidInputException {
		doAddBlock();

		Block block = Block223Application.getCurrentGame().getBlocks().get(0);

		assertEquals(1, block.getRed());
		assertEquals(1, block.getGreen());
		assertEquals(1, block.getBlue());
		assertEquals(1, block.getPoints());
	}
	
	@Test
	public void testAddBlockIDsAreUnique() throws InvalidInputException {
		Block223Controller.addBlock(1, 1, 1, 1);
		Block block1 = Block223Application.getCurrentGame().getBlocks().get(0);
		Block223Controller.addBlock(2, 2, 2, 2);
		Block block2 = Block223Application.getCurrentGame().getBlocks().get(1);

		assertNotEquals(block1.getId(), block2.getId());
	}

	@Test
	public void testAddBlockNoRights() {
		Player playerRole = new Player(USER_NAME, new Block223());
		Block223Application.setCurrentUserRole(playerRole);
		String errorNoAdminRights = "Admin privileges are required to add a block.";
		try {
			doAddBlock();
			fail(MISSING_EXPECTED_EXCEPTION + errorNoAdminRights);
		} catch (InvalidInputException e) {
			assertTrue(e.getMessage().trim().contains(errorNoAdminRights));
		}
	}
	
	@Test
	public void testAddBlockNoSelectedGame() {
		Block223Application.setCurrentGame(null);
		String errorNoSelectedGame = "A game must be selected to add a block.";
		try {
			doAddBlock();
			fail(MISSING_EXPECTED_EXCEPTION + errorNoSelectedGame);
		} catch (InvalidInputException e) {
			assertEquals(errorNoSelectedGame, e.getMessage().trim());
		}
	}
	
	@Test
	public void testAddBlockDifferentAdmin() {
		Admin adminRole = new Admin(ADMIN_PASS, new Block223());
		Block223Application.setCurrentUserRole(adminRole);
		String errorDifferentAdmin = "Only the admin who created the game can add a block.";
		try {
			doAddBlock();
			fail(MISSING_EXPECTED_EXCEPTION + errorDifferentAdmin);
		} catch (InvalidInputException e) {
			assertEquals(errorDifferentAdmin, e.getMessage().trim());
		}
	}

	@Test
	public void testAddBlockTwice() throws InvalidInputException {
		String errorDuplicateBlock = "A block with the same color already exists for the game.";
		doAddBlock();
		try {
			doAddBlock();
			fail(MISSING_EXPECTED_EXCEPTION + errorDuplicateBlock);
		} catch (InvalidInputException e) {
			assertEquals(errorDuplicateBlock, e.getMessage().trim());
		}
	}
	
	@Test
	public void testAddBlockNegativeRed() {
		int red = -1;
		int green = 1;
		int blue = 1;
		int points = 1;
		String subject = "Red";
		int lowerBound = 0;
		int upperBound = 255;
		
		doAddInvalidBlock(red, green, blue, points, subject, lowerBound, upperBound);
	}

	@Test
	public void testAddBlock256Red() {
		int red = 256;
		int green = 1;
		int blue = 1;
		int points = 1;
		String subject = "Red";
		int lowerBound = 0;
		int upperBound = 255;
		
		doAddInvalidBlock(red, green, blue, points, subject, lowerBound, upperBound);
	}
	
	@Test
	public void testAddBlockNegativeGreen() {
		int red = 1;
		int green = -1;
		int blue = 1;
		int points = 1;
		String subject = "Green";
		int lowerBound = 0;
		int upperBound = 255;
		
		doAddInvalidBlock(red, green, blue, points, subject, lowerBound, upperBound);
	}
	
	@Test
	public void testAddBlock256Green() {
		int red = 1;
		int green = 256;
		int blue = 1;
		int points = 1;
		String subject = "Green";
		int lowerBound = 0;
		int upperBound = 255;
		
		doAddInvalidBlock(red, green, blue, points, subject, lowerBound, upperBound);
	}
	
	@Test
	public void testAddBlockNegativeBlue() {
		int red = 1;
		int green = 1;
		int blue = -1;
		int points = 1;
		String subject = "Blue";
		int lowerBound = 0;
		int upperBound = 255;
		
		doAddInvalidBlock(red, green, blue, points, subject, lowerBound, upperBound);
	}
	
	@Test
	public void testAddBlock256Blue() {
		int red = 1;
		int green = 1;
		int blue = 256;
		int points = 1;
		String subject = "Blue";
		int lowerBound = 0;
		int upperBound = 255;
		
		doAddInvalidBlock(red, green, blue, points, subject, lowerBound, upperBound);
	}
	
	@Test
	public void testAddBlock0Points() {
		int red = 1;
		int green = 1;
		int blue = 1;
		int points = 0;
		String subject = "Points";
		int lowerBound = 1;
		int upperBound = 1000;
		
		doAddInvalidBlock(red, green, blue, points, subject, lowerBound, upperBound);
	}
	
	@Test
	public void testAddBlock1001Points() {
		int red = 1;
		int green = 1;
		int blue = 1;
		int points = 1001;
		String subject = "Points";
		int lowerBound = 1;
		int upperBound = 1000;
		
		doAddInvalidBlock(red, green, blue, points, subject, lowerBound, upperBound);
	}
	
	// extracted methods for better readability
	
	private void doAddInvalidBlock(int red, int green, int blue, int points, String subject, int lowerBound, int upperBound) {
		String errorIllegalBlock = subject + " must be between " + lowerBound + " and " + upperBound + ".";
		try {
			Block223Controller.addBlock(red, green, blue, points);
			fail(MISSING_EXPECTED_EXCEPTION + errorIllegalBlock);
		} catch (InvalidInputException e) {
			assertEquals(errorIllegalBlock, e.getMessage().trim());
		}
	}

	private void doAddBlock() throws InvalidInputException {
		int red = 1;
		int green = 1;
		int blue = 1;
		int points = 1;
		Block223Controller.addBlock(red, green, blue, points);
	}

}
