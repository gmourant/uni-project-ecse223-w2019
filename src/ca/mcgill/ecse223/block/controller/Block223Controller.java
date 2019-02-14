package ca.mcgill.ecse223.block.controller;
import ca.mcgill.ecse223.block.model.*;

/**
 * This is a controller interface for our app that implements
 * the concepts of MVC: Model, View, Controller.
 */
public class Block223Controller {

    /**
     * Constructor for controller.
     */
    public Block223Controller(){
    }

    /**
     * This method deletes a game in the Block223 application.
     * @param name is used to find the instance of Game
     */
    public static void deleteGame(String name){
        // Method body to be written
    }

    /**
     * This method updates the game settings for an instance of Game.
     * @param name name of the game used to identify the game
     * @param newName the new game name
     * @param nrBlocksPerLevel sets the number of blocks for an instance of Game
     * @param minBallSpeedX sets the minimum x-axis ball speed for an instance of Game
     * @param minBallSpeedY sets the minimum y-axis ball speed for an instance of Game
     * @param ballSpeedIncreaseFactor sets the speed increase factor of the ball for an instance of Game
     * @param maxPaddleLength sets the maximum paddle length for an instance of Game
     * @param minPaddleLength sets the minimum paddle length for an instance of Game
     * @param heightPlayArea sets the height of the PlayArea for an instance of Game
     * @param widthPlayArea sets the width of the PlayArea for an instance of Game\
     */
    public static void updateGame(String name, String newName, 
            int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY, 
            double ballSpeedIncreaseFactor, int maxPaddleLength, 
            int minPaddleLength, int heightPlayArea, int widthPlayArea){
        // Method body to be written
    }
    
    /**
     * This method returns a list of the names of all the games.
     */
    public static void getGames(){
        // Method body to be written
    }

}