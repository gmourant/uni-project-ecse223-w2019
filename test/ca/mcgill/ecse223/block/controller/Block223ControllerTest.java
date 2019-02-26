package ca.mcgill.ecse223.block.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;

import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.model.BTMS;
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

}
