package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.view.Block223Page;

public class Block223Application {

	private static Block223 block223;
	private static User currentUser;

	public static void main(String[] args) {
		// UI Implementation to be added
	}

	public static Block223 getBlock223() {
		if (block223 == null) {
			block223 = new Block223();
		}
 		return block223;
	}

	public static UserRole getCurrentUserRole() {
		return currentUser.getRole(0); // let 0 be the index of the current role
	}

}
