/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

// line 112 "../../../../../Block223Persistence.ump"
// line 25 "../../../../../Block223PlayMode.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 1000;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to PlayedGame, Game, and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to PlayedGame, Game, and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 5;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -5;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 79 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top right corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 15 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 16 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 17 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 19 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 20 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 21 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 22 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 10 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 28 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 117 "../../../../../Block223Persistence.ump"
   public static  void reinitializeAutouniqueID(List<PlayedGame> playedGames){
    nextId = 0; 
    for (PlayedGame playedGame : playedGames) {
      if (playedGame.getId() > nextId) {
        nextId = playedGame.getId();
      }
    }
    nextId++;
  }


  /**
   * Author: Kelly Ma
   */
  // line 61 "../../../../../Block223PlayMode.ump"
   public HallOfFameEntry getMostRecentEntry(){
    // Returns a game's most recent HallOfFameEntry
		// Obtain game associated with this PlayedGame
		return this.getGame().getMostRecentEntry();
  }


  /**
   * Author: Kelly Ma
   */
  // line 67 "../../../../../Block223PlayMode.ump"
   public int indexOfHallOfFameEntry(){
    // Returns the index of a game's mostRecentEntry
		HallOfFameEntry mostRecentEntry = this.getMostRecentEntry(); // Obtain most recent entry
		List<HallOfFameEntry> entries = this.getGame().getHallOfFameEntries(); // Get list of all entries
		int index = 0; // Start index at 0
		for (HallOfFameEntry entry : entries) {
			if (entry == mostRecentEntry) break; // Break if mostRecentEntry is found
			index++; // Increase index after each iteration of loop
		}
		return index; // Return index of most recent entry
  }


  /**
   * Guards
   * 
   * This returns true if the ball hits the paddle.
   * @author Kelly Ma
   * @return Whether or not the ball hits paddle
   */
  // line 40 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
    setBounce(bp);
    if (bp != null) return true;
    return false;
  }


  /**
   * 
   * This returns true if the ball is out of bounds and is last life.
   * @author Georges Mourant
   * @return if ball is out of bounds and last life
   */
  // line 52 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    if(lives == 1) outOfBounds = isBallOutOfBounds();
    return outOfBounds;
  }


  /**
   * 
   * This returns true if the ball is out of bounds.
   * @author Georges Mourant
   * @return if ball is out of bounds and last life
   */
  // line 63 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    return isBallOutOfBounds();
  }

  // line 67 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    Game game = this.getGame();
	int nrLevels = game.numberOfLevels();

	this.setBounce(null);

	if(nrLevels == currentLevel) {
		int nrBlocks = numberOfBlocks();
		if(nrBlocks == 1) {
			PlayedBlockAssignment block = this.getBlock(0);
			BouncePoint bp = calculateBouncePointBlock(block);
			setBounce(bp);
			return (bp!=null);
		}
	}
    return false;
  }


  /**
   * 
   * This method returns true if the ball hits the last block.
   * @author Mathieu Bissonnette
   */
  // line 90 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    int nrBlocks = numberOfBlocks();
    setBounce(null);
    if (nrBlocks == 1) {
        PlayedBlockAssignment block = getBlock(0);
        BouncePoint bp = calculateBouncePointBlock(block);
        setBounce(bp);
        return true;
    }
    return false;
  }


  /**
   * 
   * This method returns true if the ball hits a block.
   * @author Mathieu Bissonnette
   */
  // line 107 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    int nrBlocks = numberOfBlocks();
    setBounce(null);
    for (int i = 0; i <= (nrBlocks - 1); i++) {
        PlayedBlockAssignment block = getBlock(i);
        BouncePoint bp = calculateBouncePointBlock(block);
        bounce = getBounce();
        boolean closer = isCloser(bp, bounce);
        if (closer)
            setBounce(bp);
    }
    return (getBounce() != null);
  }


  /**
   * 
   * This returns true if the ball hits a wall.
   * @author Kelly Ma
   * @return Whether or not the ball hits wall
   */
  // line 126 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
    setBounce(bp);
    if (bp != null) return true;
    return false;
  }


  /**
   * Actions
   * 
   * Public method used to get a random number
   * @author https://dzone.com/articles/random-number-generation-in-java
   * @return double value
   * 
   */
  // line 140 "../../../../../Block223States.ump"
   public static  int getRandomInt(){
    Random rand = new Random();
 		//obtain number between 0-49
 	    int x = rand.nextInt(50);
 	    return x;
  }

  // line 146 "../../../../../Block223States.ump"
   public static  int getRandomInt2(){
    Random rand = new Random();
 		//obtain number between 0-4
 	    int x = rand.nextInt(4);
 	    return x;
  }


  /**
   * 
   * This private method returns the setup for the Played Game
   * by resetting the ball positions and paddle positions and by
   * adding random block positions if there is less blocks than the 
   * set number of blocks/level.
   * @author Imane Chafi 
   * 
   */
  // line 160 "../../../../../Block223States.ump"
   private void doSetup(){
    this.resetCurrentBallX();
		this.resetCurrentBallY();
		this.resetBallDirectionX();
		this.resetBallDirectionY();
		this.resetCurrentPaddleX();
		this.getGame();
		Level level = game.getLevel(getCurrentLevel() - 1);
		List<BlockAssignment> assignments = level.getBlockAssignments();

		for (BlockAssignment a : assignments) {
			new PlayedBlockAssignment(
					Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (a.getGridHorizontalPosition() - 1),
					Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (a.getGridVerticalPosition() - 1),
					a.getBlock(), this);

		}
		// Initializing the x and y variables before the while loop
		int x = getRandomInt();
		int y = getRandomInt();
		while (numberOfBlocks() < game.getNrBlocksPerLevel()) {
			// if chosen, try next position starting from randomly chosen position
			BlockAssignment taken = level.findBlockAssignment(x, y);
			if (taken != null) {
				x++;
				if (x > 15) {
					y++;
					x = 1;
				}
				if (y > 15)
					y = 1;

			}
			// going to the right, then next row until last row
			// then 1/1 until empty position found.
			// convert to x/y coordinates
			x = Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (x - 1);
			y = Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (y - 1);

			new PlayedBlockAssignment(x, y, game.getRandomBlock(), this);
		}
  }

  // line 203 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    bounceBall();
  }

  // line 207 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    setLives(lives-1);
    resetCurrentBallX();
    resetCurrentBallY();
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentPaddleX();
  }

  // line 216 "../../../../../Block223States.ump"
   private void doHitBlock(){
    int score = getScore();
    BouncePoint bounce = getBounce();
    PlayedBlockAssignment pblock = bounce.getHitBlock();
    Block block = pblock.getBlock();
    int bscore = block.getPoints();
    setScore(score + bscore);
    pblock.delete();
    bounceBall();
  }

  // line 227 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    doHitBlock();
		   int level = getCurrentLevel();
		   setCurrentLevel(level+1);
		   setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength() -
				   (getGame().getPaddle().getMaxPaddleLength() - getGame().getPaddle().getMinPaddleLength())/
				   (getGame().numberOfLevels() - 1) * (getCurrentLevel() - 1));
		   setWaitTime(INITIAL_WAIT_TIME * Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(),
				   (getCurrentLevel() - 1)));
  }


  /**
   * 
   * This method is performed when the ball is bounced after hitting the paddle, block, or wall.
   * @author Kelly Ma
   */
  // line 242 "../../../../../Block223States.ump"
   private void bounceBall(){
    BouncePoint bp = getBounce(); // Calculate bounce point
		double newBallDirectionX = ballDirectionX; // Create variables to store new X & Y directions
		double newBallDirectionY = ballDirectionY;
		if (bp.getDirection().equals(BouncePoint.BounceDirection.FLIP_Y)) { // Check direction at bounce point
			newBallDirectionY = -1 * ballDirectionY; // Flip Y direction
			if (ballDirectionX * ballDirectionY < 0) { // Check if product of X and Y direction is < 0
				newBallDirectionX = ballDirectionX + 0.1 * newBallDirectionY;
			} else {
				newBallDirectionX = ballDirectionX + 0.1 * ballDirectionY;
			}
		} else if (bp.getDirection().equals(BouncePoint.BounceDirection.FLIP_X)) { // Check direction at bounce point
			newBallDirectionX = -1 * ballDirectionX; // Flip X direction
			if (ballDirectionX * ballDirectionY < 0) { // Check if product of X and Y direction is < 0
				newBallDirectionY = ballDirectionY + 0.1 * newBallDirectionX;
			} else {
				newBallDirectionY = ballDirectionY + 0.1 * ballDirectionX;
			}
		} else if (bp.getDirection().equals(BouncePoint.BounceDirection.FLIP_BOTH)) { // Check if both are flipped
			newBallDirectionX = -1 * ballDirectionX;
			newBallDirectionY = -1 * ballDirectionY;
		}

		setCurrentBallY(bp.getY()); // Set current ball positions
		setCurrentBallX(bp.getX());
		setBallDirectionX(newBallDirectionX); // Set new ball directions
		setBallDirectionY(newBallDirectionY);

		if (bp.hasHitBlock()) { // Check if the bounce point has a block
			bounce.setHitBlock(null);
		}
  }


  /**
   * Method calculateBouncePointPaddle sets the flip states of the ball 
   * when it hits the paddle.
   * @author Imane Chafi
   * @return BouncePoint
   */
  // line 280 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    /* STEP 1 : Check  if  the  ball  segment (the  segment  determined  by 
	   the  current  position  of  the  ball  and  the  new position of the 
	   ball) intersects with the full box (A, B, C, E,F)*/
	   
	   int radius = Ball.BALL_DIAMETER/2; // Get ball radius
	   BouncePoint bp = null; // Initialize ball's bounce point
	   double currentBallX = this.getCurrentBallX(); // Find ball coordinates
	   double currentBallY = this.getCurrentBallY();
	   double nextBallX = currentBallX + this.getBallDirectionX();
	   double nextBallY = currentBallY + this.getBallDirectionY();
	   
	   // Find rectangles for calculation purposes
	 	Rectangle2D.Double rectA = new Rectangle2D.Double(currentPaddleX, Paddle.VERTICAL_DISTANCE - radius, Paddle.PADDLE_WIDTH, radius ); // Left wall of play area
	 	Rectangle2D.Double rectB = new Rectangle2D.Double(currentPaddleX - radius, Paddle.VERTICAL_DISTANCE, radius, radius); // Top wall of play area
	 	Rectangle2D.Double rectC = new Rectangle2D.Double(currentPaddleX + Paddle.PADDLE_WIDTH, Paddle.VERTICAL_DISTANCE, radius, radius); // Right wall of play area
	 	Rectangle2D.Double rectE = new Rectangle2D.Double(currentPaddleX - radius, Paddle.VERTICAL_DISTANCE - radius, radius, radius); // Right wall of play area
	 	Rectangle2D.Double rectF = new Rectangle2D.Double(currentPaddleX + Paddle.PADDLE_WIDTH, Paddle.VERTICAL_DISTANCE - radius, radius, radius); // Right wall of play area
	 // create new QuadCurve2D.Float for E 
		QuadCurve2D curveE = new QuadCurve2D.Float();
		curveE.setCurve(currentPaddleX - radius, Paddle.VERTICAL_DISTANCE, currentPaddleX - ((int)(radius/(Math.sqrt(2)))), Paddle.VERTICAL_DISTANCE - ((int)(radius/(Math.sqrt(2)))), currentPaddleX, Paddle.VERTICAL_DISTANCE - radius);
	 // create new QuadCurve2D.Float for F
		QuadCurve2D curveF = new QuadCurve2D.Float();
		curveF.setCurve(currentPaddleX + Paddle.PADDLE_WIDTH + radius, Paddle.VERTICAL_DISTANCE, currentPaddleX + Paddle.PADDLE_WIDTH + ((int)(radius/(Math.sqrt(2)))), Paddle.VERTICAL_DISTANCE - ((int)(radius/(Math.sqrt(2)))), currentPaddleX + Paddle.PADDLE_WIDTH, Paddle.VERTICAL_DISTANCE - radius);
		// Check points of intersection between ball and lines
	 	boolean intersectionA = rectA.intersectsLine(currentBallX, currentBallY, nextBallX, nextBallY); // Ball intersects rectA
	 	boolean intersectionB = rectB.intersectsLine(currentBallX, currentBallY, nextBallX, nextBallY); // Ball intersects rectB
	 	boolean intersectionC = rectC.intersectsLine(currentBallX, currentBallY, nextBallX, nextBallY); // Ball intersects rectC
	 	boolean intersectionE = rectE.intersectsLine(currentBallX, currentBallY, nextBallX, nextBallY); // Ball intersects rectB
	 	boolean intersectionF = rectF.intersectsLine(currentBallX, currentBallY, nextBallX, nextBallY); // Ball intersects rectC
	 	boolean intersectionECurve = curveE.intersects(currentBallX, currentBallY, nextBallX, nextBallY); // Ball intersects rectB
	 	boolean intersectionFCurve = curveF.intersects(currentBallX, currentBallY, nextBallX, nextBallY); // Ball intersects rectC
	   
	 	/*STEP 2 : For A, B, C, determine the bounce point by intersecting the
	    ball segment with each of the yellow line segments of A, B,*/
	 // Create a collection of bounce points to determine which is closest
	 		List <BouncePoint> bouncePoints = new ArrayList<BouncePoint>();
	 		
	 		if (!intersectionA && !intersectionB && !intersectionC) { // Does not hit a hall at all
				return null;
			} else if (intersectionA) { // Check intersection of A 
				bp = new BouncePoint(nextBallX, nextBallY, BouncePoint.BounceDirection.FLIP_Y);//X and Y position are set to the same nextBallX and nextBallY
				bouncePoints.add(bp);
			} else if (intersectionB) { // Check intersection of B 
				bp = new BouncePoint(nextBallX, nextBallY, BouncePoint.BounceDirection.FLIP_X);
				bouncePoints.add(bp);
			} else if (intersectionC) { // Check intersection of C
				bp = new BouncePoint(nextBallX, nextBallY, BouncePoint.BounceDirection.FLIP_X);
				bouncePoints.add(bp);
			}
	 		
	 		/*STEP  3:  For  E,  F  determine  the 
	    bounce  point  by  intersecting  the  ball  segment  
	    with  each  of the  circle  segments of E, */
			else if (intersectionE || intersectionECurve) { // Check intersection of C
				
				//Setting the curve for E : 
				bp = new BouncePoint(nextBallX, nextBallY, BouncePoint.BounceDirection.FLIP_X);
				bouncePoints.add(bp);
			}
			else if (intersectionF || intersectionFCurve) { // Check intersection of C
				bp = new BouncePoint(nextBallX, nextBallY, BouncePoint.BounceDirection.FLIP_X);
				bouncePoints.add(bp);
			}
	 		
	    /*: If more than one bounce point are found, 
	    take the bounce point that is the closest to the 
	    current position of the ball. 
	    The box of the bounce point determines the bounce behavior */
	 	// Iterate through all points to find the closest one
			BouncePoint closestPoint = null;
	        for (BouncePoint bouncePoint : bouncePoints) {
	        	if (isCloser(bouncePoint, closestPoint))
	            	closestPoint = bouncePoint;
	        }

			return closestPoint; // From collection of all bounce points
  }


  /**
   * 
   * This method calculates the bounce point when the ball hits the wall
   * @author Kelly Ma
   * @return the bounce point of the wall or null
   */
  // line 365 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointWall(){
    // Get current ball location and direction
		double currentBallX = getCurrentBallX();
		double currentBallY = getCurrentBallY();
		double dX = getBallDirectionX();
		double dY = getBallDirectionY();

		// Create a list to store all bounce points
		ArrayList<BouncePoint> bps = new ArrayList<BouncePoint>();

		// Calculate ball radius
		int radius = Ball.BALL_DIAMETER / 2;

		// Create rectangles for all the areas to be tested
		Rectangle2D.Double rectA = new Rectangle2D.Double(0, 0, radius, Game.PLAY_AREA_SIDE);
		Rectangle2D.Double rectB = new Rectangle2D.Double(0, 0, Game.PLAY_AREA_SIDE, radius);
		Rectangle2D.Double rectC = new Rectangle2D.Double(Game.PLAY_AREA_SIDE - radius, 0, radius, Game.PLAY_AREA_SIDE);

		// Check all intersections
		boolean intersectionA = rectA.intersectsLine(currentBallX, currentBallY, dX, dY);
		boolean intersectionB = rectB.intersectsLine(currentBallX, currentBallY, dX, dY);
		boolean intersectionC = rectC.intersectsLine(currentBallX, currentBallY, dX, dY);

		if (intersectionA && !intersectionB) { // Intersection A only
			bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_X));
		}
		if (intersectionA && intersectionB) { // Intersects A and B
			bps.add(new BouncePoint(radius, radius, BouncePoint.BounceDirection.FLIP_BOTH)); // Flip both directions
		}
		if (intersectionC && intersectionB) { // Intersects C and B
			bps.add(new BouncePoint(Game.PLAY_AREA_SIDE - radius, radius, BouncePoint.BounceDirection.FLIP_BOTH)); // Flip both directions
		}
		if (intersectionB && !intersectionA && !intersectionC) { // Intersects B only
			bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_Y));
		}
		if (intersectionC && !intersectionB) { // Intersects C only 
			bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_X));
		}

		// Iterate through all points to find the closest one
		// If closestPoint is not found, returns null
		BouncePoint closestPoint = null;
		for (BouncePoint bouncePoint : bps) {
			if (isCloser(bouncePoint, closestPoint))
				closestPoint = bouncePoint;
		}

		return closestPoint; // From collection of all bounce points
  }


  /**
   * 
   * This method returns the bounce point that is the closest to the ball. 
   * If there is no bouncePoint, it returns null.
   * @author Mathieu Bissonnette
   * @author Kelly Ma
   * @return the bounce point after hitting a block
   */
  // line 425 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
    // Calculate ball radius
		int radius = Ball.BALL_DIAMETER / 2;

		// Get current ball coordinates and directions
		double currentBallX = this.getCurrentBallX();
		double currentBallY = this.getCurrentBallY();
		double dX = currentBallX + this.getBallDirectionX();
		double dY = currentBallY + this.getBallDirectionY();

		// Add rectangles for testing
		Rectangle2D.Double rectA = new Rectangle2D.Double(block.getX(), block.getY() - radius, Block.SIZE, radius);
		Rectangle2D.Double rectB = new Rectangle2D.Double(block.getX() - radius, block.getY(), radius, Block.SIZE);
		Rectangle2D.Double rectC = new Rectangle2D.Double(block.getX() + Block.SIZE, block.getY(), radius, Block.SIZE);
		Rectangle2D.Double rectD = new Rectangle2D.Double(block.getX(), block.getY() + Block.SIZE, Block.SIZE, radius);

		// Create new QuadCurve2D.Float for E
		QuadCurve2D curveE = new QuadCurve2D.Float();
		curveE.setCurve(block.getX() - Block.SIZE - radius, block.getY(),
				block.getX() - Block.SIZE - ((int) (radius / (Math.sqrt(2)))),
				block.getY() - ((int) (radius / (Math.sqrt(2)))), block.getX() - Block.SIZE, block.getY() - radius);

		// Create new QuadCurve2D.Float for F
		QuadCurve2D curveF = new QuadCurve2D.Float();
		curveF.setCurve(block.getX() + radius, block.getY(), block.getX() - ((int) (radius / (Math.sqrt(2)))),
				block.getY() - ((int) (radius / (Math.sqrt(2)))), block.getX(), block.getY() - radius);

		// Create new QuadCurve2D.Float for G
		QuadCurve2D curveG = new QuadCurve2D.Float();
		curveG.setCurve(block.getX() - Block.SIZE - radius, block.getY() - Block.SIZE,
				block.getX() - Block.SIZE + ((int) (radius / (Math.sqrt(2)))),
				block.getY() - Block.SIZE + ((int) (radius / (Math.sqrt(2)))), block.getX() - Block.SIZE,
				block.getY() - Block.SIZE - radius);

		// Create new QuadCurve2D.Float for H
		QuadCurve2D curveH = new QuadCurve2D.Float();
		curveH.setCurve(block.getX() + radius, block.getY() + Block.SIZE,
				block.getX() + radius - ((int) (radius / (Math.sqrt(2)))),
				block.getY() + Block.SIZE + ((int) (radius / (Math.sqrt(2)))), block.getX(),
				block.getY() + Block.SIZE + radius);

		// All intersections are listed below
		boolean intersectionA = rectA.intersectsLine(currentBallX, currentBallY, dX, dY);
		boolean intersectionB = rectB.intersectsLine(currentBallX, currentBallY, dX, dY);
		boolean intersectionC = rectC.intersectsLine(currentBallX, currentBallY, dX, dY);
		boolean intersectionD = rectD.intersectsLine(currentBallX, currentBallY, dX, dY);
		boolean intersectionECurve = curveE.intersects(currentBallX, currentBallY, dX, dY);
		boolean intersectionFCurve = curveF.intersects(currentBallX, currentBallY, dX, dY);
		boolean intersectionGCurve = curveE.intersects(currentBallX, currentBallY, dX, dY);
		boolean intersectionHCurve = curveF.intersects(currentBallX, currentBallY, dX, dY);

		// Create a list to store all bounce points found
		ArrayList<BouncePoint> bps = new ArrayList<BouncePoint>();

		if (intersectionA) { // Check intersection A
			bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_Y));
		}
		if (intersectionB) { // Check intersection B
			bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_X));
		}
		if (intersectionC) { // Check intersection CLine2D.Double ballPath = new Line2D.Double(currentBallX, currentBallY, dX, dY);
			bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_X));
		}
		if (intersectionD) { // Check intersection D
			bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_Y));
		}
		if (intersectionECurve) { // Check curve intersection E
			if (dX < 0) { // Ball approaches from the right
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_Y));
			} else if (dX > 0) { // Ball approaches from the left
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_X));
			} else { // Ball approaches dead-on
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_BOTH));
			}
		}
		if (intersectionFCurve) { // Check curve intersection F
			if (dX < 0) { // Ball approaches from the right
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_X));
			} else if (dX > 0) { // Ball approaches from the left
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_Y));
			} else { // Ball approaches dead-on
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_BOTH));
			}
		}
		if (intersectionGCurve) { // Check curve intersection G
			if (dX < 0) { // Ball approaches from the right
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_Y));
			} else if (dX > 0) { // Ball approaches from the left
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_X));
			} else { // Ball approaches dead-on
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_BOTH));
			}
		}
		if (intersectionHCurve) { // Check curve intersection H
			if (dX < 0) { // Ball approaches from the right
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_X));
			} else if (dX > 0) { // Ball approaches from the left
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_Y));
			} else { // Ball approaches dead-on
				bps.add(new BouncePoint(dX, dY, BouncePoint.BounceDirection.FLIP_BOTH));
			}
		}
	
		// Iterate through all points to find the closest one
		// If closestPoint is not found, returns null
		BouncePoint closestPoint = null;
		for (BouncePoint bouncePoint : bps) {
			if (isCloser(bouncePoint, closestPoint))
				closestPoint = bouncePoint;
		}

		return closestPoint; // From collection of all bounce points
  }


  /**
   * 
   * This method returns true if pointA exists and is closer to the ball than pointB.
   * @author Mathieu Bissonnette
   */
  // line 544 "../../../../../Block223States.ump"
   private boolean isCloser(BouncePoint pointA, BouncePoint pointB){
    // Verify if one of the point is null.
       if (pointA == null) {
           return false;
       }
       if (pointB == null) {
           return true;
       }   
       // Calculate the distances and check for the smallest one.
       double distanceA = Math.sqrt(Math.pow(currentBallX-pointA.getX(), 2) + Math.pow(currentBallY-pointA.getY(), 2));
       double distanceB = Math.sqrt(Math.pow(currentBallX-pointB.getX(), 2) + Math.pow(currentBallY-pointB.getY(), 2));
       return (distanceA < distanceB);
  }


  /**
   * Method if the ball doesn't hit anything and stays in bounds
   * @author Imane Chafi
   */
  // line 562 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    double x = this.getCurrentBallX();
    double y = this.getCurrentBallY();
    double dx = this.getBallDirectionX();
    double dy = this.getBallDirectionY();
    this.setCurrentBallX(x+dx);
    this.setCurrentBallY(y+dy);
  }


  /**
   * 
   * This performs all the required actions for ending the game.
   * @author Georges Mourant
   */
  // line 575 "../../../../../Block223States.ump"
   private void doGameOver(){
    block223 = getBlock223();
    Player p = getPlayer();
    if(p != null){
      game = getGame();
      HallOfFameEntry hofe = new HallOfFameEntry(score, playername, p, game, block223);
      game.setMostRecentEntry(hofe);
    }
    delete();
  }


  /**
   * Guard Helper methods
   * 
   * This returns true if the ball is out of bounds.
   * @author Georges Mourant
   * @return if ball is out of bounds
   */
  // line 593 "../../../../../Block223States.ump"
   private boolean isBallOutOfBounds(){
    double ballBottomY = getCurrentBallY() + Ball.BALL_DIAMETER;
    double paddleTopY = getCurrentPaddleY();
    return (paddleTopY < ballBottomY);
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 115 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}