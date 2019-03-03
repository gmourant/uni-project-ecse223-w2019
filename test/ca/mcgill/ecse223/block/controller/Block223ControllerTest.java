package ca.mcgill.ecse223.block.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;

import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.controller.BtmsController;
import ca.mcgill.ecse.btms.controller.InvalidInputException;
import ca.mcgill.ecse.btms.model.BTMS;
import ca.mcgill.ecse.btms.model.BusVehicle;
import ca.mcgill.ecse.btms.model.Route;
import ca.mcgill.ecse.btms.persistence.BtmsPersistence;
import ca.mcgill.ecse223.block.model.*;

public class Block223ControllerTest {
	
	// Set up the test environment.
	
	@BeforeClass
	public static void setUpOnce() {
		String filename = "testdata.block";
		BtmsPersistence.setFilename(filename);
		File f = new File(filename);
		f.delete();
	}
	
	@Before
	public void setUp() {
		// clear all data
		Block223 block223 = Block223Application.getBlock223();
		block233.delete();
	}
	

	// updateBlock tests
	
	@Test
	public void testUpdateBlockSuccess {
		// TODO
	}
	
	@Test
	public void testUpdateBlockTooBigSmallVal {
		// TODO
	}
	
	@Test
	public void testUpdateBlockNullGame {

	// positionBlock tests
	
	@Test
	public void testCreateBlockAssignmentSuccess {
		Block223 block223 = Block223Application.getBlock223();
		
		Game newGame = new Game("game", 100, null, null, null);
		block223.addGame(newGame);
		
		Block newBlock = new Block(0, 0, 0, 0, newGame);
		Level newLevel = new Level(newGame);

		try {
			Block223Controller.positionBlock(1, 0, 10, 10);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultBlockAssignment(newGame.getBlockAssignment(0), 0, newLevel, newBlock, 10, 10;
	}
	
	@Test
	public void testCreateBlockAssignmentInvalidLevel {
		// TODO
	}
	
	@Test
	public void testUpdateBlockInvalidGame {
		// TODO
	}
	
	@Test
	public void testUpdateBlock0ID {
		// TODO
	}

	public void testCreateBlockAssignmentInvalidBlock {
		// TODO
	}
	
	private void checkResultBlockAssignment(BlockAssignment aBlockAssignment, Level aLevel, Block aBlock, int x, int y) {
		if (aBlockAssignment != null) {
			assertEquals(aLevel, aBlockAssignment.getLevel());
			assertEquals(aBlock, aBlockAssignment.getBlock());
			assertEquals(x, aBlockAssignment.getGridHorizontalPosition());
			assertEquals(y, aBlockAssignment.getGridVerticalPosition());
		} else {
			fail();
		}
	}
}
