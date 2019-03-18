package ca.mcgill.ecse223.block.tests.f12;

import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.ADMIN_PASS;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.MISSING_EXPECTED_EXCEPTION;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_NAME;
import static ca.mcgill.ecse223.block.tests.util.Block223TestConstants.USER_PASS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.tests.util.Block223TestUtil;

public class LogInLogOutTests {

	private Block223 block223;

	@Before
	public void setUp() {
		block223 = Block223TestUtil.initializeTestBlock223();
		Block223Application.setCurrentUserRole(null);
	}

	// register

	@Test
	public void testRegisterUserAsAdmin() throws InvalidInputException {
		Block223Controller.register(USER_NAME, USER_PASS, ADMIN_PASS);
		List<User> users = Block223Application.getBlock223().getUsers();
		assertEquals(1, users.size());
		User user = users.get(0);
		assertEquals(USER_NAME, user.getUsername());
		List<UserRole> roles = user.getRoles();
		assertEquals(2, roles.size());
		if (roles.get(0) instanceof Admin) {
			if (!(roles.get(1) instanceof Player)) {
				fail("User is missing player role");
			}
			assertEquals(ADMIN_PASS, roles.get(0).getPassword());
			assertEquals(USER_PASS, roles.get(1).getPassword());
		} else {
			if (!(roles.get(1) instanceof Admin)) {
				fail("User is missing admin role");
			}
			assertEquals(USER_PASS, roles.get(0).getPassword());
			assertEquals(ADMIN_PASS, roles.get(1).getPassword());
		}
	}

	@Test
	public void testRegisterUserAsNotAdminEmptyPass() throws InvalidInputException {
		Block223Controller.register(USER_NAME, USER_PASS, "");
		assertPlayerOnly();
	}

	@Test
	public void testRegisterUserAsNotAdminNullPass() throws InvalidInputException {
		Block223Controller.register(USER_NAME, USER_PASS, null);
		assertPlayerOnly();
	}

	@Test
	public void testRegisterUserIsSet() throws InvalidInputException {
		Player player = new Player(USER_PASS, Block223Application.getBlock223());
		Block223Application.setCurrentUserRole(player);
		String errorCurrentUserIsSet = "Cannot register a new user while a user is logged in.";
		try {
			Block223Controller.register(USER_NAME, USER_PASS, null);
			fail(MISSING_EXPECTED_EXCEPTION + errorCurrentUserIsSet);
		} catch (InvalidInputException e) {
			assertEquals(errorCurrentUserIsSet, e.getMessage().trim());
		}
	}

	@Test
	public void testRegisterSamePasswords() throws InvalidInputException {
		String errorSamePasswords = "The passwords have to be different.";
		try {
			Block223Controller.register(USER_NAME, USER_PASS, USER_PASS);
			fail(MISSING_EXPECTED_EXCEPTION + errorSamePasswords);
		} catch (InvalidInputException e) {
			assertEquals(errorSamePasswords, e.getMessage().trim());
		}
	}

	@Test
	public void testRegisterNullUserPassword() throws InvalidInputException {
		String errorSamePasswords = "The player password needs to be specified.";
		try {
			Block223Controller.register(USER_NAME, null, ADMIN_PASS);
			fail(MISSING_EXPECTED_EXCEPTION + errorSamePasswords);
		} catch (InvalidInputException e) {
			assertEquals(errorSamePasswords, e.getMessage().trim());
		}
	}

	@Test
	public void testRegisterEmptyUserPassword() throws InvalidInputException {
		String errorSamePasswords = "The player password needs to be specified.";
		try {
			Block223Controller.register(USER_NAME, "", ADMIN_PASS);
			fail(MISSING_EXPECTED_EXCEPTION + errorSamePasswords);
		} catch (InvalidInputException e) {
			assertEquals(errorSamePasswords, e.getMessage().trim());
		}
	}

	@Test
	public void testRegisterUserNameIsTaken() throws InvalidInputException {
		Block223Controller.register(USER_NAME, USER_PASS, ADMIN_PASS);
		String errorCurrentUserIsSet = "The username has already been taken.";
		try {
			Block223Controller.register(USER_NAME, USER_PASS, ADMIN_PASS);
			fail(MISSING_EXPECTED_EXCEPTION + errorCurrentUserIsSet);
		} catch (InvalidInputException e) {
			assertEquals(errorCurrentUserIsSet, e.getMessage().trim());
		}
	}

	@Test
	public void testRegisterUserNameIsNull() throws InvalidInputException {
		String errorCurrentUserIsSet = "The username must be specified.";
		try {
			Block223Controller.register(null, USER_PASS, ADMIN_PASS);
			fail(MISSING_EXPECTED_EXCEPTION + errorCurrentUserIsSet);
		} catch (InvalidInputException e) {
			assertEquals(errorCurrentUserIsSet, e.getMessage().trim());
		}
	}

	@Test
	public void testRegisterUserNameIsEmpty() throws InvalidInputException {
		String errorCurrentUserIsSet = "The username must be specified.";
		try {
			Block223Controller.register("", USER_PASS, ADMIN_PASS);
			fail(MISSING_EXPECTED_EXCEPTION + errorCurrentUserIsSet);
		} catch (InvalidInputException e) {
			assertEquals(errorCurrentUserIsSet, e.getMessage().trim());
		}
	}

	// login

	@Test
	public void testLogInSuccessUser() throws InvalidInputException {
		new User(USER_NAME, Block223Application.getBlock223(),
				new Player(USER_PASS, Block223Application.getBlock223()));
		Block223Persistence.save(block223);
		block223.delete();
		block223 = null;
		Block223Controller.login(USER_NAME, USER_PASS);
		assertTrue(Block223Application.getCurrentUserRole() instanceof Player);
	}

	@Test
	public void testLogInFailUser() throws InvalidInputException {
		String errorCredentialsMismatch = "The username and password do not match.";
		try {
			Block223Controller.login(USER_NAME, "");
			fail(MISSING_EXPECTED_EXCEPTION + "The username and password do not match");
		} catch (InvalidInputException e) {
			assertEquals(errorCredentialsMismatch, e.getMessage().trim());
		}
	}
	
	@Test
	public void testLogInFailNonExistingUser() throws InvalidInputException {
		String errorCredentialsMismatch = "The username and password do not match.";
		try {
			Block223Controller.login("Not a user", "");
			fail(MISSING_EXPECTED_EXCEPTION + "The username and password do not match");
		} catch (InvalidInputException e) {
			assertEquals(errorCredentialsMismatch, e.getMessage().trim());
		}
	}

	@Test
	public void testLogInSuccessAdmin() throws InvalidInputException {
		new User(USER_NAME, Block223Application.getBlock223(),
				new Admin(ADMIN_PASS, Block223Application.getBlock223()));
		Block223Persistence.save(block223);
		block223.delete();
		block223 = null;
		Block223Controller.login(USER_NAME, ADMIN_PASS);
		assertTrue(Block223Application.getCurrentUserRole() instanceof Admin);
	}

	// getUserMode

	@Test
	public void testGetUserModeSuccessNull() {
		TOUserMode userMode = Block223Controller.getUserMode();
		Mode mode = userMode.getMode();
		assertEquals(Mode.None, mode);
	}

	@Test
	public void testGetUserModeSuccessUser() {
		Block223Application.setCurrentUserRole(new Player(USER_PASS, Block223Application.getBlock223()));
		TOUserMode userMode = Block223Controller.getUserMode();
		Mode mode = userMode.getMode();
		assertEquals(Mode.Play, mode);
	}

	@Test
	public void testGetUserModeSuccessAdmin() {
		Block223Application.setCurrentUserRole(new Admin(ADMIN_PASS, Block223Application.getBlock223()));
		TOUserMode userMode = Block223Controller.getUserMode();
		Mode mode = userMode.getMode();
		assertEquals(Mode.Design, mode);
	}

	// logout

	@Test
	public void testLogOutSuccess() {
		Block223Application.setCurrentUserRole(new Player(USER_PASS, Block223Application.getBlock223()));
		Block223Controller.logout();
		UserRole currentUserRole = Block223Application.getCurrentUserRole();
		assertNull(currentUserRole);
	}

	@Test
	public void testLogOutSuccessNoLoggedInUser() {
		Block223Controller.logout();
		UserRole currentUserRole = Block223Application.getCurrentUserRole();
		assertNull(currentUserRole);
	}

	// extracted methods for better readability

	private void assertPlayerOnly() {
		List<User> users = Block223Application.getBlock223().getUsers();
		assertEquals(1, users.size());
		User user = users.get(0);
		assertEquals(USER_NAME, user.getUsername());
		List<UserRole> roles = user.getRoles();
		assertEquals(1, roles.size());
		if (!(roles.get(0) instanceof Player)) {
			fail("User does not have player role");
		}
		assertEquals(USER_PASS, roles.get(0).getPassword());
	}

}
