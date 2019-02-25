package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223Page;

public class Block223Application {

    private static Block223 block223;
    private static UserRole currentUser;
    private static Game currentGame;

    public static void main(String[] args) {
        // start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Block223Page().setVisible(true);
            }
        });
    }

    public static Block223 resetBlock223() {
        if (block223 == null) {
            // load model
            block223 = Block223Persistence.load();
        }
        return block223;
    }

    public static Block223 getBlock223() {
        return block223;
    }
    
    public static void setCurrentUser(UserRole user){
        currentUser = user;
    }
    
    public static UserRole getCurrentUser(){
        return currentUser;
    }
    
    public static void setCurrentGame(Game game){
        currentGame = game;
    }
    
    public static Game getCurrentGame(){
        return currentGame;
    }
}
