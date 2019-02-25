package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.view.Block223Page;

public class Block223Application {
	
private static Block223 block223;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Block223Page().setVisible(true); //This method will need to be changed to get the setVisible from the view java file
            }
        });
	}

	public static Block223 getBlock223() {
		if (block223 == null) {
			// for now, we are just creating an empty block223app
			block223 = new Block223();
		}
 		return block223;
	}

}
