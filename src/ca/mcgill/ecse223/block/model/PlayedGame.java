/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import ca.mcgill.ecse223.block.model.BouncePoint.BounceDirection;
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
        // line 18 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 19 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 20 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 21 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 22 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 23 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 24 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 25 "../../../../../Block223States.ump"
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
        // line 13 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 31 "../../../../../Block223States.ump"
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
   * 
   * @author Kelly Ma
   * @return Whether or not the ball hits paddle
   */
  // line 44 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
		setBounce(bp);
		return bp != null;
  }


  /**
   * 
   * This returns true if the ball is out of bounds and is last life.
   * 
   * @author Georges Mourant
   * @return if ball is out of bounds and last life
   */
  // line 56 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
		if (this.getLives() == 1)
			outOfBounds = isBallOutOfBounds();
		return outOfBounds;
  }


  /**
   * 
   * This returns true if the ball is out of bounds.
   * 
   * @author Georges Mourant
   * @return if ball is out of bounds and last life
   */
  // line 69 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    return isBallOutOfBounds();
  }

  // line 74 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    // Obtain current game, get number of levels
		Game game = this.getGame();
		int nrLevels = game.numberOfLevels();

		// Remove bouncepoint
		this.setBounce(null);

		// Check if game is at last level
		if (nrLevels == currentLevel) {
			int nrBlocks = numberOfBlocks();
			// Check if game is at the last block
			if (nrBlocks == 1) {
				PlayedBlockAssignment pblock = this.getBlock(0);
				BouncePoint bp = calculateBouncePointBlock(pblock);
				setBounce(bp);
				return (bp != null);
			}
		}
		return false;
  }


  /**
   * 
   * This method returns true if the ball hits the last block.
   * 
   * @author Mathieu Bissonnette
   */
  // line 103 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    // Check # of blocks and set bouncepoint to null
		int nrBlocks = numberOfBlocks();
		setBounce(null);

		// Check if there's only one block left (last block)
		if (nrBlocks == 1) {
			PlayedBlockAssignment block = this.getBlock(0);
			BouncePoint bp = calculateBouncePointBlock(block);
			setBounce(bp);
			return (bp != null);
		}
		return false;
  }


  /**
   * 
   * This method returns true if the ball hits a block.
   * 
   * @author Mathieu Bissonnette
   */
  // line 126 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    // Check # blocks, set bouncepoint to null
		int nrBlocks = numberOfBlocks();
		setBounce(null);

		// Calculate bouncepoint for block in game
		for (PlayedBlockAssignment block : getBlocks()) {
			BouncePoint bp = calculateBouncePointBlock(block);
			bounce = getBounce(); // Get their respective bounce point
			boolean bpIsCloser = isCloser(bp, bounce); // Check if the bouncepoint is closer
			if (bpIsCloser) {
				bp.setHitBlock(block);
				setBounce(bp);
			}
		}
		return getBounce() != null;
  }


  /**
   * 
   * This returns true if the ball hits a wall.
   * 
   * @author Kelly Ma
   * @return Whether or not the ball hits wall
   */
  // line 151 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
		setBounce(bp);
		return bp != null;
  }


  /**
   * Actions
   * 
   * This private method returns the setup for the Played Game by resetting the
   * ball positions and paddle positions and by adding random block positions if
   * there is less blocks than the set number of blocks/level.
   * 
   * @author Imane Chafi
   */
  // line 167 "../../../../../Block223States.ump"
   private void doSetup(){
    // Reset current ball locations, ball directions, and paddle
		resetCurrentBallX();
		resetCurrentBallY();
		resetBallDirectionX();
		resetBallDirectionY();
		resetCurrentPaddleX();

		// Obtain game, level, and all block assignments
		Game game = getGame();
		Level level = game.getLevel(currentLevel - 1);
		List<BlockAssignment> assignments = level.getBlockAssignments();

		// Create a new PlayedBlockAssignment for each assignment in the list of
		// assignments
		for (BlockAssignment assignment : assignments) {
			new PlayedBlockAssignment(
					Game.WALL_PADDING
							+ (Block.SIZE + Game.COLUMNS_PADDING) * (assignment.getGridHorizontalPosition() - 1),
					Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (assignment.getGridVerticalPosition() - 1),
					assignment.getBlock(), this);
		}

		// Create random locations if the number of blocks is less than the desired
		// number
		while (numberOfBlocks() < game.getNrBlocksPerLevel()) {
			// Instantiate a randomizer
			Random rand = new Random();
			// Generate a random position
			int x = Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * rand.nextInt(15);
			int y = Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * rand.nextInt(15);
			// Check if an assignemnt already exists in the location
			boolean blockFound = false;
			for (PlayedBlockAssignment pbassignment : getBlocks()) {
				if (pbassignment.getX() == x && pbassignment.getY() == y) {
					blockFound = true;
					break;
				}
			}
			// If no block currently exists, add the random block
			if (!blockFound)
				new PlayedBlockAssignment(x, y, game.getRandomBlock(), this);
		}
  }

  // line 213 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    bounceBall(); // Method described later
  }

  // line 218 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    // # of lives decreases
		this.setLives(lives - 1);

		// Reset ball location, directions, and paddle position
		this.resetCurrentBallX();
		this.resetCurrentBallY();
		this.resetBallDirectionX();
		this.resetBallDirectionY();
		this.resetCurrentPaddleX();
  }

  // line 231 "../../../../../Block223States.ump"
   private void doHitBlock(){
    // Get score
		int score = getScore();
		bounce = getBounce();
		PlayedBlockAssignment pblock = bounce.getHitBlock();
		Block block = pblock.getBlock();

		// Get number of points for the block hit
		// Add this to the score
		int bscore = block.getPoints();
		this.setScore(score + bscore);

		// Delete the specific block assignment and bounce the ball
		pblock.delete();
		bounceBall();
  }

  // line 249 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    // Block is hit
		doHitBlock();
		int level = getCurrentLevel();

		// Obtain the next level
		setCurrentLevel(level + 1);

		// Set the next paddle length
		setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength()
				- (getGame().getPaddle().getMaxPaddleLength() - getGame().getPaddle().getMinPaddleLength())
						/ (getGame().numberOfLevels() - 1) * (getCurrentLevel() - 1));

		// Add wait time
		setWaitTime(INITIAL_WAIT_TIME
				* Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), (getCurrentLevel() - 1)));
  }


  /**
   * 
   * This method is performed when the ball is bounced after hitting the paddle,
   * block, or wall.
   * 
   * @author Kelly Ma
   */
  // line 275 "../../../../../Block223States.ump"
   private void bounceBall(){
    // Obtain the bounce points
		BouncePoint bp = getBounce();

		// Get current ball coordinates
		double currentBallX = getCurrentBallX();
		double currentBallY = getCurrentBallY();

		// Get upcoming ball coordinates
		double nextBallX = bp.getX() - currentBallX;
		double nextBallY = bp.getY() - currentBallY;

		// Get remaining distance left
		double distanceLeftX = getBallDirectionX() - nextBallX;
		double distanceLeftY = getBallDirectionY() - nextBallY;

		if (distanceLeftX == 0 && distanceLeftY == 0) {
			// If there is no distance left, set the ball positions as current ones
			setCurrentBallX(bp.getX());
			setCurrentBallY(bp.getY());
		} else {
			// Create variables to store new ball positions and directions
			double newDirX, newDirY, newCurrentBallX, newCurrentBallY;
			// First, check if the direction change is in X
			if (bp.getDirection() == BounceDirection.FLIP_X) {
				newDirX = -getBallDirectionX();
				newDirY = getBallDirectionY() + getUnitValue(getBallDirectionY()) * 0.1 * Math.abs(newDirX);
				if (getBallDirectionX() != 0) {
					newCurrentBallX = bp.getX() + distanceLeftX * newDirX / getBallDirectionX();
				} else {
					newCurrentBallX = bp.getX() + distanceLeftY * newDirX / getBallDirectionY();
				}
				if (getBallDirectionY() != 0) {
					newCurrentBallY = bp.getY() + distanceLeftY * newDirY / getBallDirectionY();
				} else {
					newCurrentBallY = bp.getY() + distanceLeftX * newDirY / getBallDirectionX();
				}
			}
			// Check if the direction change is in Y
			else if (bp.getDirection() == BounceDirection.FLIP_Y) {
				newDirY = -getBallDirectionY();
				newDirX = getBallDirectionX() + getUnitValue(getBallDirectionX()) * 0.1 * Math.abs(newDirY);
				if (getBallDirectionX() != 0) {
					newCurrentBallX = bp.getX() + distanceLeftX * newDirX / getBallDirectionX();
				} else {
					newCurrentBallX = bp.getX() + distanceLeftY * newDirX / getBallDirectionY();
				}
				if (getBallDirectionY() != 0) {
					newCurrentBallY = bp.getY() + distanceLeftY * newDirY / getBallDirectionY();
				} else {
					newCurrentBallY = bp.getY() + distanceLeftX * newDirY / getBallDirectionX();
				}
			}
			// Check if the direction change is in both
			else {
				newDirX = -getBallDirectionX();
				newDirY = -getBallDirectionY();
				if (getBallDirectionX() != 0) {
					newCurrentBallX = bp.getX() + distanceLeftX * newDirX / getBallDirectionX();
				} else {
					newCurrentBallX = bp.getX() + distanceLeftY * newDirX / getBallDirectionY();
				}
				if (getBallDirectionY() != 0) {
					newCurrentBallY = bp.getY() + distanceLeftY * newDirY / getBallDirectionY();
				} else {
					newCurrentBallY = bp.getY() + distanceLeftX * newDirY / getBallDirectionX();
				}
			}
			// Set ball positions and directions
			setCurrentBallX(newCurrentBallX);
			setCurrentBallY(newCurrentBallY);
			setBallDirectionX(newDirX);
			setBallDirectionY(newDirY);
		}
		// Remove bounce point
		setBounce(null);
  }


  /**
   * 
   * This method returns a unit vector with value of either -1 or 1
   * 
   * @param val The current value
   * @return A unit vector
   */
  // line 359 "../../../../../Block223States.ump"
   private int getUnitValue(double val){
    if (val >= 0) {
			return 1;
		}
		return -1;
  }


  /**
   * Method calculateBouncePointPaddle sets the flip states of the ball when it
   * hits the paddle.
   * 
   * @author Imane Chafi
   * 
   * @author Kelly Ma
   * 
   * @return The bounce point after hitting paddle
   */
  // line 378 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    // Obtain current ball radius, ball location, and direction
		int radius = Ball.BALL_DIAMETER / 2;
		double currentBallX = getCurrentBallX();
		double currentBallY = getCurrentBallY();
		double dX = getBallDirectionX();
		double dY = getBallDirectionY();

		// Create a line segment to check for intersection using ball's current location
		// and next location
		Line2D segment = new Line2D.Double(currentBallX, currentBallY, currentBallX + dX, currentBallY + dY);

		// Get current paddle position, length, and width
		double currentPaddleX = getCurrentPaddleX();
		double currentPaddleY = getCurrentPaddleY();
		double paddleLength = getCurrentPaddleLength();
		int paddleWidth = Paddle.PADDLE_WIDTH;

		// Create rectangles to represent the different possible bounce locations
		Rectangle2D rectangleA = new Rectangle2D.Double(currentPaddleX, currentPaddleY - radius, paddleLength,
				paddleWidth);
		Rectangle2D rectangleB = new Rectangle2D.Double(currentPaddleX - radius, currentPaddleY, radius, paddleWidth);
		Rectangle2D rectangleC = new Rectangle2D.Double(currentPaddleX + paddleLength, currentPaddleY, radius,
				paddleWidth);
		Rectangle2D rectangleE = new Rectangle2D.Double(currentPaddleX - radius, currentPaddleY - radius, radius,
				radius);
		Rectangle2D rectangleF = new Rectangle2D.Double(currentPaddleX + paddleLength, currentPaddleY - radius, radius,
				radius);

		// Create boolean values to check for intersection
		boolean intersectionA = segment.intersects(rectangleA);
		boolean intersectionB = segment.intersects(rectangleB);
		boolean intersectionC = segment.intersects(rectangleC);
		boolean intersectionE = segment.intersects(rectangleE);
		boolean intersectionF = segment.intersects(rectangleF);

		// Declare variables to store calculation values
		double a, b, A, B, C, D, checkX, checkY, Xa, Xb, X, Y;

		/*
		 * Check for line segment's intersections with the rectangles and appropriate
		 * edges, for each intersection
		 */

		// Intersection A
		if (intersectionA) {
			if (dX == 0) { // No X direction
				return new BouncePoint(currentBallX, currentPaddleY - radius, BounceDirection.FLIP_Y);
			} else {
				a = dY / dX;
				b = currentBallY - a * currentBallX;
				checkY = currentPaddleY - radius;
				checkX = (checkY - b) / a;
				// Check that the next x and y coordinates are not equal to checkX and checkY
				if (!((currentBallX + dX) == checkX && (currentBallY + dY) == checkY))
					return new BouncePoint(checkX, checkY, BounceDirection.FLIP_Y);
			}
		}

		// Intersection B
		if (intersectionB) {
			if (dX != 0) {
				a = dY / dX;
				b = currentBallY - a * currentBallX;
				checkX = currentPaddleX - radius;
				checkY = a * checkX + b;
				// Check that the next x and y coordinates are not equal to checkX and checkY
				if (!((currentBallX + dX) == checkX && (currentBallY + dY) == checkY))
					return new BouncePoint(checkX, checkY, BounceDirection.FLIP_X);
			}
		}

		// Intersection C
		if (intersectionC) {
			if (dX != 0) {
				a = dY / dX;
				b = currentBallY - a * currentBallX;
				checkX = currentPaddleX + paddleLength + radius;
				checkY = a * checkX + b;
				// Check that the next x and y coordinates are not equal to checkX and checkY
				if (!((currentBallX + dX) == checkX && (currentBallY + dY) == checkY))
					return new BouncePoint(checkX, checkY, BounceDirection.FLIP_X);
			}
		}

		// Intersection E
		// Ensure that one of dX or dY are positive
		if (intersectionE && !(dX < 0 && dY < 0)) {
			a = 0;
			if (dX != 0)
				a = dY / dX;
			b = currentBallY - a * currentBallX;
			// Perform curve calculations here
			A = 1 + Math.pow(a, 2);
			B = 2 * a * (b - currentPaddleY) - 2 * currentPaddleX;
			C = Math.pow(currentPaddleX, 2) + Math.pow(b - currentPaddleY, 2) - Math.pow(radius, 2);
			D = Math.sqrt(Math.pow(B, 2) - 4 * A * C);
			Xa = (-B - D) / (2 * A);
			Xb = (-B + D) / (2 * A);
			// Assign X and Y values for checking
			X = Xb;
			if (Math.abs(currentBallX - Xa) < Math.abs(currentBallX - Xb))
				X = Xa;
			Y = a * X + b;
			// Check that the next x and y coordinates are not equal to X and Y
			// Check that X and Y are not asymptotic
			if (!(((currentBallX + dX) == X && (currentBallY + dY) == Y)) && !(Double.isNaN(X) || Double.isNaN(Y))) {
				if (dX < 0) {
					// Flip Y if ball is move left
					return new BouncePoint(X, Y, BounceDirection.FLIP_Y);
				} else {
					return new BouncePoint(X, Y, BounceDirection.FLIP_X);
				}
			}
		}

		// Intersection E
		// Ensure that dX is negative or dY is positive
		if (intersectionF && !(dX > 0 && dY < 0)) {
			a = 0;
			if (dX != 0)
				a = dY / dX;
			b = currentBallY - a * currentBallX;
			// Perform curve calculations here
			A = 1 + Math.pow(a, 2);
			B = 2 * a * (b - currentPaddleY) - 2 * (currentPaddleX + paddleLength);
			C = Math.pow((currentPaddleX + paddleLength), 2) + Math.pow(b - currentPaddleY, 2) - Math.pow(radius, 2);
			D = Math.sqrt(Math.pow(B, 2) - 4 * A * C);
			Xa = (-B - D) / (2 * A);
			Xb = (-B + D) / (2 * A);
			// Assign X and Y values for checking
			X = Xb;
			if (Math.abs(currentBallX - Xa) < Math.abs(currentBallX - Xb))
				X = Xa;
			Y = a * X + b;
			// Check that the next x and y coordinates are not equal to X and Y
			// Check that X and Y are not asymptotic
			if (!(((currentBallX + dX) == X && (currentBallY + dY) == Y)) && !(Double.isNaN(X) || Double.isNaN(Y))) {
				if (dX < 0) {
					return new BouncePoint(X, Y, BounceDirection.FLIP_X);
				} else {
					return new BouncePoint(X, Y, BounceDirection.FLIP_Y);
				}
			}
		}
		// If no intersections are found, return null
		return null;
  }


  /**
   * 
   * This method calculates the bounce point when the ball hits the wall
   * 
   * @author Kelly Ma
   * @return the bounce point of the wall or null
   */
  // line 535 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointWall(){
    // Get ball radius
		double radius = (double) Ball.BALL_DIAMETER / 2;

		// Obtain current ball coordinates and directions
		double currentBallX = getCurrentBallX();
		double currentBallY = getCurrentBallY();
		double dX = getBallDirectionX();
		double dY = getBallDirectionY();

		// Declare variables to store calculation values
		double a = dY / dX;
		double b = currentBallY - a * currentBallX;
		double checkX, checkY;

		// Check intersection with upper wall, side B
		if (currentBallY + dY <= radius) {
			// Check if top-left corner is hit
			if (currentBallX + dX <= radius)
				return new BouncePoint(radius, radius, BounceDirection.FLIP_BOTH);
			// Check if top-right corner is hit
			if (currentBallX + dX >= Game.PLAY_AREA_SIDE - radius)
				return new BouncePoint(Game.PLAY_AREA_SIDE - radius, radius, BounceDirection.FLIP_BOTH);
			// At this point, the ball will not hit the corners
			// Check that the ball's X direction is not 0
			if (dX != 0) {
				checkY = radius;
				checkX = (checkY - b) / a;
				return new BouncePoint(checkX, checkY, BounceDirection.FLIP_Y);
			} else {
				// The ball has no X direction
				return new BouncePoint(currentBallX, radius, BounceDirection.FLIP_Y);
			}
		}

		// Check intersection with side A
		if (currentBallX + dX <= radius) {
			if (dX != 0) {
				checkX = radius;
				checkY = a * checkX + b;
				return new BouncePoint(checkX, checkY, BounceDirection.FLIP_X);
			}
		}

		// Check intersection with side C
		if (currentBallX + dX >= Game.PLAY_AREA_SIDE - radius) {
			if (dX != 0) {
				checkX = Game.PLAY_AREA_SIDE - radius;
				checkY = a * checkX + b;
				return new BouncePoint(checkX, checkY, BounceDirection.FLIP_X);
			}
		}

		// Return null if no bouncepoints are found
		return null;
  }


  /**
   * 
   * This method returns the bounce point that is the closest to the ball. If
   * there is no bouncePoint, it returns null.
   * 
   * @author Mathieu Bissonnette
   * @author Kelly Ma
   * @return the bounce point after hitting a block
   */
  // line 601 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
    // Get current ball radius
		int radius = Ball.BALL_DIAMETER / 2;

		// Get current ball location and direction
		double currentBallX = getCurrentBallX();
		double currentBallY = getCurrentBallY();
		double dX = getBallDirectionX();
		double dY = getBallDirectionY();

		// Store segment to be used for intersections
		Line2D segment = new Line2D.Double(currentBallX, currentBallY, currentBallX + dX, currentBallY + dY);

		// Get current block length (same as width)
		int blockSide = Block.SIZE;

		// Get current block positions
		int currentBlockX = block.getX();
		int currentBlockY = block.getY();

		// Create rectangles for intersection calculations
		Rectangle2D rectangleA = new Rectangle2D.Double(currentBlockX, currentBlockY - radius, blockSide, radius);
		Rectangle2D rectangleB = new Rectangle2D.Double(currentBlockX - radius, currentBlockY, radius, blockSide);
		Rectangle2D rectangleC = new Rectangle2D.Double(currentBlockX + blockSide, currentBlockY, radius, blockSide);
		Rectangle2D rectangleD = new Rectangle2D.Double(currentBlockX, currentBlockY + blockSide, blockSide, radius);
		Rectangle2D rectangleE = new Rectangle2D.Double(currentBlockX - radius, currentBlockY - radius, radius, radius);
		Rectangle2D rectangleF = new Rectangle2D.Double(currentBlockX + blockSide, currentBlockY - radius, radius,
				radius);
		Rectangle2D rectangleG = new Rectangle2D.Double(currentBlockX - radius, currentBlockY + blockSide, radius,
				radius);
		Rectangle2D rectangleH = new Rectangle2D.Double(currentBlockX + blockSide, currentBlockY + blockSide, radius,
				radius);

		// Create boolean values to check for intersection
		boolean intersectionA = segment.intersects(rectangleA);
		boolean intersectionB = segment.intersects(rectangleB);
		boolean intersectionC = segment.intersects(rectangleC);
		boolean intersectionD = segment.intersects(rectangleD);
		boolean intersectionE = segment.intersects(rectangleE);
		boolean intersectionF = segment.intersects(rectangleF);
		boolean intersectionG = segment.intersects(rectangleG);
		boolean intersectionH = segment.intersects(rectangleH);

		// Declare variables to store calculation values
		double a, b, A, B, C, D, checkX, checkY, Xa, Xb, X, Y;

		// Intersection A
		if (intersectionA) {
			if (dX == 0) { // No X direction
				BouncePoint bp = new BouncePoint(currentBallX, currentBlockY - radius, BounceDirection.FLIP_Y);
				bp.setHitBlock(block);
				return bp;
			}
			a = dY / dX;
			b = currentBallY - a * currentBallX;
			checkY = currentBlockY - radius;
			checkX = (checkY - b) / a;
			// Check that the next x and y coordinates are not equal to checkX and checkY
			if (!((currentBallX + dX) == checkX && (currentBallY + dY) == checkY)) {
				BouncePoint bp = new BouncePoint(checkX, checkY, BounceDirection.FLIP_Y);
				bp.setHitBlock(block);
				return bp;
			}
		}

		// Intersection B
		if (intersectionB) {
			if (dX != 0) { // Has X direction
				a = dY / dX;
				b = currentBallY - a * currentBallX;
				checkX = currentBlockX - radius;
				checkY = a * checkX + b;
				// Check that the next x and y coordinates are not equal to checkX and checkY
				if (!((currentBallX + dX) == checkX && (currentBallY + dY) == checkY)) {
					BouncePoint bp = new BouncePoint(checkX, checkY, BounceDirection.FLIP_X);
					bp.setHitBlock(block);
					return bp;
				}
			}
		}

		// IntersectionC
		if (intersectionC) {
			if (dX != 0) { // Has X direction
				a = dY / dX;
				b = currentBallY - a * currentBallX;
				checkX = currentBlockX + radius + blockSide;
				checkY = a * checkX + b;
				// Check that the next x and y coordinates are not equal to checkX and checkY
				if (!((currentBallX + dX) == checkX && (currentBallY + dY) == checkY)) {
					BouncePoint bp = new BouncePoint(checkX, checkY, BounceDirection.FLIP_X);
					bp.setHitBlock(block);
					return bp;
				}
			}
		}

		// Intersection D
		if (intersectionD) {
			if (dX != 0) { // Has X direction
				a = dY / dX;
				b = currentBallY - a * currentBallX;
				checkY = currentBlockY + blockSide + radius;
				checkX = (checkY - b) / a;
				// Check that the next x and y coordinates are not equal to checkX and checkY
				if (!((currentBallX + dX) == checkX && (currentBallY + dY) == checkY)) {
					BouncePoint bp = new BouncePoint(checkX, checkY, BounceDirection.FLIP_Y);
					bp.setHitBlock(block);
					return bp;
				}
			}
			if (dX == 0) {
				BouncePoint bp = new BouncePoint(currentBallX, currentBlockY + blockSide + radius,
						BounceDirection.FLIP_Y);
				bp.setHitBlock(block);
				return bp;
			}
		}

		// Intersection E
		// Check that one of dX or dY is positive
		if (intersectionE && !(dX < 0 && dY < 0)) {
			a = 0;
			if (dX != 0) // Has X direction
				a = dY / dX;
			b = currentBallY - a * currentBallX;
			// Perform curve calculations here
			A = 1 + Math.pow(a, 2);
			B = 2 * a * (b - currentBlockY) - 2 * currentBlockX;
			C = Math.pow(currentBlockX, 2) + Math.pow(b - currentBlockY, 2) - Math.pow(radius, 2);
			D = Math.sqrt(Math.pow(B, 2) - 4 * A * C);
			Xa = (-B - D) / (2 * A);
			Xb = (-B + D) / (2 * A);
			// Assign X and Y values for checking
			X = Xb;
			if (Math.abs(currentBallX - Xa) < Math.abs(currentBallX - Xb))
				X = Xa;
			Y = a * X + b;
			// Check that the next x and y coordinates are not equal to X and Y
			// Check that X and Y are not asymptotic
			if (!(((currentBallX + dX) == X && (currentBallY + dY) == Y)) && !(Double.isNaN(X) || Double.isNaN(Y))) {
				if (dX < 0) { // Ball is moving towards the left
					BouncePoint bp = new BouncePoint(X, Y, BounceDirection.FLIP_Y);
					bp.setHitBlock(block);
					return bp;
				} else { // Ball is moving towards the right
					BouncePoint bp = new BouncePoint(X, Y, BounceDirection.FLIP_X);
					bp.setHitBlock(block);
					return bp;
				}
			}
		}

		// Intersection F
		// Ensure that dX is negative or dY is positive
		if (intersectionF && !(dX > 0 && dY < 0)) {
			a = 0;
			if (dX != 0) // Has X direction
				a = dY / dX;
			b = currentBallY - a * currentBallX;
			// Perform curve calculations here
			A = 1 + Math.pow(a, 2);
			B = 2 * a * (b - currentBlockY) - 2 * (currentBlockX + blockSide);
			C = Math.pow((currentBlockX + blockSide), 2) + Math.pow(b - currentBlockY, 2) - Math.pow(radius, 2);
			D = Math.sqrt(Math.pow(B, 2) - 4 * A * C);
			Xa = (-B - D) / (2 * A);
			Xb = (-B + D) / (2 * A);
			// Assign X and Y values for checking
			X = Xb;
			if (Math.abs(currentBallX - Xa) < Math.abs(currentBallX - Xb))
				X = Xa;
			Y = a * X + b;
			// Check that the next x and y coordinates are not equal to X and Y
			// Check that X and Y are not asymptotic
			if (!(((currentBallX + dX) == X && (currentBallY + dY) == Y)) && !(Double.isNaN(X) || Double.isNaN(Y))) {
				if (dX < 0) { // Ball is moving towards the left
					BouncePoint bp = new BouncePoint(X, Y, BounceDirection.FLIP_X);
					bp.setHitBlock(block);
					return bp;
				} else { // Ball is moving towards the right
					BouncePoint bp = new BouncePoint(X, Y, BounceDirection.FLIP_Y);
					bp.setHitBlock(block);
					return bp;
				}
			}
		}

		// Intersection G
		if (intersectionG && !(dX < 0 && dY > 0)) {
			a = 0;
			if (dX != 0) // Has X direction
				a = dY / dX;
			b = currentBallY - a * currentBallX;
			// Perform curve calculations here
			A = 1 + Math.pow(a, 2);
			B = 2 * a * (b - currentBlockY - blockSide) - 2 * currentBlockX;
			C = Math.pow(currentBlockX, 2) + Math.pow(b - currentBlockY - blockSide, 2) - Math.pow(radius, 2);
			D = Math.sqrt(Math.pow(B, 2) - 4 * A * C);
			Xa = (-B - D) / (2 * A);
			Xb = (-B + D) / (2 * A);
			// Assign X and Y values for checking
			X = Xb;
			if (Math.abs(currentBallX - Xa) < Math.abs(currentBallX - Xb))
				X = Xa;
			Y = a * X + b;
			// Check that the next x and y coordinates are not equal to X and Y
			// Check that X and Y are not asymptotic
			if (!(((currentBallX + dX) == X && (currentBallY + dY) == Y)) && !(Double.isNaN(X) || Double.isNaN(Y))) {
				if (dX < 0) { // Ball is moving towards the left
					BouncePoint bp = new BouncePoint(X, Y, BounceDirection.FLIP_Y);
					bp.setHitBlock(block);
					return bp;
				} else { // Ball is moving towards the right
					BouncePoint bp = new BouncePoint(X, Y, BounceDirection.FLIP_X);
					bp.setHitBlock(block);
					return bp;
				}
			}

		}

		// Intersection H
		// Check that dX is negative or dY is negative
		if (intersectionH && !(dX > 0 && dY > 0)) {
			a = 0;
			if (dX != 0) // Has X direction
				a = dY / dX;
			b = currentBallY - a * currentBallX;
			// Perform curve calculations here
			A = 1 + Math.pow(a, 2);
			B = 2 * a * (b - currentBlockY - blockSide) - 2 * (currentBlockX + blockSide);
			C = Math.pow((currentBlockX + blockSide), 2) + Math.pow(b - currentBlockY - blockSide, 2)
					- Math.pow(radius, 2);
			D = Math.sqrt(Math.pow(B, 2) - 4 * A * C);
			Xa = (-B - D) / (2 * A);
			Xb = (-B + D) / (2 * A);
			// Assign X and Y values for checking
			X = Xb;
			if (Math.abs(currentBallX - Xa) < Math.abs(currentBallX - Xb))
				X = Xa;
			Y = a * X + b;
			// Check that the next x and y coordinates are not equal to X and Y
			// Check that X and Y are not asymptotic
			if (!(((currentBallX + dX) == X && (currentBallY + dY) == Y)) && !(Double.isNaN(X) || Double.isNaN(Y))) {
				if (dX < 0) { // Ball is moving towards the left
					BouncePoint bp = new BouncePoint(X, Y, BounceDirection.FLIP_X);
					bp.setHitBlock(block);
					return bp;
				} else { // Ball is moving towards the right
					BouncePoint bp = new BouncePoint(X, Y, BounceDirection.FLIP_Y);
					bp.setHitBlock(block);
					return bp;
				}
			}
		}
		// No bouncepoints found
		return null;
  }


  /**
   * 
   * This method returns true if pointA exists and is closer to the ball than
   * pointB.
   * 
   * @author Mathieu Bissonnette
   */
  // line 868 "../../../../../Block223States.ump"
   private boolean isCloser(BouncePoint A, BouncePoint B){
    // Obtain current ball position
		double currentBallX = getCurrentBallX();
		double currentBallY = getCurrentBallY();

		// Before performing any calculations, check if A and B exist
		if (A == null)
			return false;
		if (B == null)
			return true;

		// Calculate distance to A and B
		// Verify which one is closest
		double distanceA = Math.sqrt(Math.pow((A.getX() - currentBallX), 2) + Math.pow(A.getY() - currentBallY, 2));
		double distanceB = Math.sqrt(Math.pow((B.getX() - currentBallX), 2) + Math.pow(B.getY() - currentBallY, 2));

		// Return true is distanceA is bigger
		// In all other cases, return false
		if (distanceB > distanceA)
			return true;
		return false;
  }


  /**
   * Method for when the ball doesn't hit anything and stays inside the bounds
   * @author Sofia Dieguez
   */
  // line 894 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    double x = getCurrentBallX();
		double y = getCurrentBallY();
		double dx = getBallDirectionX();
		double dy = getBallDirectionY();
		setCurrentBallX(x + dx);
		setCurrentBallY(y + dy);
  }


  /**
   * End of doHitNothingAndNotOutOfBounds() method
   * 
   * This performs all the required actions for ending the game.
   * 
   * @author Georges Mourant
   */
  // line 908 "../../../../../Block223States.ump"
   private void doGameOver(){
    Block223 block223 = this.getBlock223();
		Player p = this.getPlayer();

		if (p != null) {
			Game game = this.getGame();
			HallOfFameEntry hof = new HallOfFameEntry(this.score, this.getPlayername(), p, game, block223);
			game.setMostRecentEntry(hof);
		}
		this.delete();
  }


  /**
   * Guard Helper methods
   * 
   * This returns true if the ball is out of bounds.
   * 
   * @author Georges Mourant
   * @return if ball is out of bounds
   */
  // line 928 "../../../../../Block223States.ump"
   private boolean isBallOutOfBounds(){
    boolean outofbounds = false;
		if (this.getCurrentBallY() + this.getBallDirectionY() > Game.PLAY_AREA_SIDE - 2 * Game.GRID_DIMENSIONS) {
			outofbounds = true;
		}
		return outofbounds;
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