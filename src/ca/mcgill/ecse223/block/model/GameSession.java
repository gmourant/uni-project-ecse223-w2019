/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 10 "../../../../../Block223PlayGame.ump"
// line 1 "../../../../../Block223States.ump"
public class GameSession
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameSession Attributes
  private boolean ofTestMode;
  private int currentLevelNr;
  private int currentLife;
  private int score;

  //Autounique Attributes
  private int id;

  //GameSession State Machines
  public enum GameStatus { Idle, Play, Paused, Complete }
  private GameStatus gameStatus;

  //GameSession Associations
  private Game game;
  private Player player;
  private Block223 block223;
  private List<SpecificBlock> specificBlocks;
  private SpecificBall specificBall;
  private SpecificPaddle specificPaddle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameSession(boolean aOfTestMode, Game aGame, Player aPlayer, Block223 aBlock223, SpecificBall aSpecificBall, SpecificPaddle aSpecificPaddle)
  {
    ofTestMode = aOfTestMode;
    currentLevelNr = 1;
    currentLife = 3;
    score = 0;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create gameSession due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create gameSession due to player");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create gameSession due to block223");
    }
    specificBlocks = new ArrayList<SpecificBlock>();
    if (aSpecificBall == null || aSpecificBall.getGameSession() != null)
    {
      throw new RuntimeException("Unable to create GameSession due to aSpecificBall");
    }
    specificBall = aSpecificBall;
    if (aSpecificPaddle == null || aSpecificPaddle.getGameSession() != null)
    {
      throw new RuntimeException("Unable to create GameSession due to aSpecificPaddle");
    }
    specificPaddle = aSpecificPaddle;
    setGameStatus(GameStatus.Idle);
  }

  public GameSession(boolean aOfTestMode, Game aGame, Player aPlayer, Block223 aBlock223, boolean aIsOutBoundsForSpecificBall, int aPositionXForSpecificBall, int aPositionYForSpecificBall, int aPositionXForSpecificPaddle, int aPositionYForSpecificPaddle)
  {
    ofTestMode = aOfTestMode;
    currentLevelNr = 1;
    currentLife = 3;
    score = 0;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create gameSession due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create gameSession due to player");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create gameSession due to block223");
    }
    specificBlocks = new ArrayList<SpecificBlock>();
    specificBall = new SpecificBall(aIsOutBoundsForSpecificBall, aPositionXForSpecificBall, aPositionYForSpecificBall, this);
    specificPaddle = new SpecificPaddle(aPositionXForSpecificPaddle, aPositionYForSpecificPaddle, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentLevelNr(int aCurrentLevelNr)
  {
    boolean wasSet = false;
    currentLevelNr = aCurrentLevelNr;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLife(int aCurrentLife)
  {
    boolean wasSet = false;
    currentLife = aCurrentLife;
    wasSet = true;
    return wasSet;
  }

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean getOfTestMode()
  {
    return ofTestMode;
  }

  /**
   * This represents current number of the level
   */
  public int getCurrentLevelNr()
  {
    return currentLevelNr;
  }

  public int getCurrentLife()
  {
    return currentLife;
  }

  public int getScore()
  {
    return score;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isOfTestMode()
  {
    return ofTestMode;
  }

  public String getGameStatusFullName()
  {
    String answer = gameStatus.toString();
    return answer;
  }

  public GameStatus getGameStatus()
  {
    return gameStatus;
  }

  public boolean startGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Idle:
        if (isGameAdmin()&&ofTestMode()&&hasEnoughBlocks())
        {
        // line 9 "../../../../../Block223States.ump"
          
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (!(ofTestMode())&&hasEnoughBlocks())
        {
        // line 11 "../../../../../Block223States.ump"
          displayScore(HallOfFameEntry aHallOfFameEntry);
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isGameAdmin()&&ofTestMode()&&!(hasEnoughBlocks()))
        {
        // line 14 "../../../../../Block223States.ump"
          addRandomBlocks(); // Must reach required number of blocks
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (!(ofTestMode())&&!(hasEnoughBlocks()))
        {
        // line 17 "../../../../../Block223States.ump"
          addRandomBlocks();
				displayScore(HallOfFameEntry aHallOfFameEntry);
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move(SpecificBall aSpecificBall)
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Play:
        if (isBlockHit()&&!(isLastBlock()))
        {
          exitGameStatus();
        // line 26 "../../../../../Block223States.ump"
          // move() updates SpecificBall position
				doBlockHit(SpecificBall aSpecificBall);
				deleteBlock(SpecificBlock aBlock); 
				increaseScore(HallOfFameEntry aHallOfFameEntry);
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit()&&isLastBlock()&&!(isLastLevel()))
        {
          exitGameStatus();
        // line 31 "../../../../../Block223States.ump"
          doBlockHit(SpecificBall aSpecificBall);
				deleteBlock(SpecificBlock aBlock);
				resetPaddlePosition(SpecificPaddle aPaddle);
				resetBallPosition(SpecificBall aSpecificBall);
				increaseScore(HallOfFameEntry aHallOfFameEntry);
				currentLevelNr++;
          setGameStatus(GameStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit()&&isLastBlock()&&isLastLevel())
        {
          exitGameStatus();
        // line 39 "../../../../../Block223States.ump"
          doBlockHit(SpecificBall aSpecificBall);
				deleteBlock(SpecificBlock aBlock);
				increaseScore(HallOfFameEntry aHallOfFameEntry);
          setGameStatus(GameStatus.Complete);
          wasEventProcessed = true;
          break;
        }
        if (isWallHit())
        {
          exitGameStatus();
        // line 44 "../../../../../Block223States.ump"
          doWallHit(SpecificBall aSpecificBall);
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isPaddleHit())
        {
          exitGameStatus();
        // line 47 "../../../../../Block223States.ump"
          doPaddleHit(SpecificBall aSpecificBall);
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds()&&!(isLastLife()))
        {
          exitGameStatus();
        // line 50 "../../../../../Block223States.ump"
          doOutOfBounds(SpecificBall aSpecificBall);
				resetPaddlePosition(SpecificPaddle aPaddle);
				resetBallPosition(SpecificBall aBall);
				currentLife--;
          setGameStatus(GameStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds()&&isLastLife())
        {
          exitGameStatus();
        // line 56 "../../../../../Block223States.ump"
          doOutOfBounds(SpecificBall aSpecificBall);
				currentLife--;
          setGameStatus(GameStatus.Complete);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pauseGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Play:
        if (!(isOutOfBounds()))
        {
          exitGameStatus();
        // line 60 "../../../../../Block223States.ump"
          stopBall(SpecificBall aSpecificBall); // Stop SpecificBall in its current location
				stopPaddle(SpecificPaddle aSpecificPaddle); // Stop SpecificPaddle in its current location
          setGameStatus(GameStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean resumeGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Paused:
        if (hasNoBlocks())
        {
        // line 69 "../../../../../Block223States.ump"
          // Represents completing a level
				initializeBlocks(); // initializeBlocks() checks if there are blocks left (if no, start from scratch)
				addRandomBlocks();
				initializeBall(); 
				initializePaddle();
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (!(hasNoBlocks()))
        {
        // line 75 "../../../../../Block223States.ump"
          // Level not yet complete
				initializeBlocks(); // Add blocks that admin has created from save, those not deleted
				initializeBall(); // Add a SpecificBall from save
				initializePaddle(); // Add a SpecificPaddle & position it from save
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitGameStatus()
  {
    switch(gameStatus)
    {
      case Play:
        // line 64 "../../../../../Block223States.ump"
        stopBall(SpecificBall aSpecificBall);
        break;
    }
  }

  private void setGameStatus(GameStatus aGameStatus)
  {
    gameStatus = aGameStatus;

    // entry actions and do activities
    switch(gameStatus)
    {
      case Idle:
        // line 4 "../../../../../Block223States.ump"
        initializeBlocks(); // Add blocks that admin has created
				initializeBall(); // Add a SpecificBall
				initializePaddle(); // Add a SpecificPaddle & position it
        break;
      case Play:
        // line 23 "../../../../../Block223States.ump"
        move(SpecificBall aSpecificBall);
        break;
    }
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetMany */
  public SpecificBlock getSpecificBlock(int index)
  {
    SpecificBlock aSpecificBlock = specificBlocks.get(index);
    return aSpecificBlock;
  }

  public List<SpecificBlock> getSpecificBlocks()
  {
    List<SpecificBlock> newSpecificBlocks = Collections.unmodifiableList(specificBlocks);
    return newSpecificBlocks;
  }

  public int numberOfSpecificBlocks()
  {
    int number = specificBlocks.size();
    return number;
  }

  public boolean hasSpecificBlocks()
  {
    boolean has = specificBlocks.size() > 0;
    return has;
  }

  public int indexOfSpecificBlock(SpecificBlock aSpecificBlock)
  {
    int index = specificBlocks.indexOf(aSpecificBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public SpecificBall getSpecificBall()
  {
    return specificBall;
  }
  /* Code from template association_GetOne */
  public SpecificPaddle getSpecificPaddle()
  {
    return specificPaddle;
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
      existingGame.removeGameSession(this);
    }
    game.addGameSession(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    if (aPlayer == null)
    {
      return wasSet;
    }

    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removeGameSession(this);
    }
    player.addGameSession(this);
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
      existingBlock223.removeGameSession(this);
    }
    block223.addGameSession(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificBlock addSpecificBlock(Block aBlock)
  {
    return new SpecificBlock(aBlock, this);
  }

  public boolean addSpecificBlock(SpecificBlock aSpecificBlock)
  {
    boolean wasAdded = false;
    if (specificBlocks.contains(aSpecificBlock)) { return false; }
    GameSession existingGameSession = aSpecificBlock.getGameSession();
    boolean isNewGameSession = existingGameSession != null && !this.equals(existingGameSession);
    if (isNewGameSession)
    {
      aSpecificBlock.setGameSession(this);
    }
    else
    {
      specificBlocks.add(aSpecificBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificBlock(SpecificBlock aSpecificBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificBlock, as it must always have a gameSession
    if (!this.equals(aSpecificBlock.getGameSession()))
    {
      specificBlocks.remove(aSpecificBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificBlockAt(SpecificBlock aSpecificBlock, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificBlock(aSpecificBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBlocks()) { index = numberOfSpecificBlocks() - 1; }
      specificBlocks.remove(aSpecificBlock);
      specificBlocks.add(index, aSpecificBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificBlockAt(SpecificBlock aSpecificBlock, int index)
  {
    boolean wasAdded = false;
    if(specificBlocks.contains(aSpecificBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBlocks()) { index = numberOfSpecificBlocks() - 1; }
      specificBlocks.remove(aSpecificBlock);
      specificBlocks.add(index, aSpecificBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificBlockAt(aSpecificBlock, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeGameSession(this);
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeGameSession(this);
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeGameSession(this);
    }
    while (specificBlocks.size() > 0)
    {
      SpecificBlock aSpecificBlock = specificBlocks.get(specificBlocks.size() - 1);
      aSpecificBlock.delete();
      specificBlocks.remove(aSpecificBlock);
    }
    
    SpecificBall existingSpecificBall = specificBall;
    specificBall = null;
    if (existingSpecificBall != null)
    {
      existingSpecificBall.delete();
    }
    SpecificPaddle existingSpecificPaddle = specificPaddle;
    specificPaddle = null;
    if (existingSpecificPaddle != null)
    {
      existingSpecificPaddle.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "ofTestMode" + ":" + getOfTestMode()+ "," +
            "currentLevelNr" + ":" + getCurrentLevelNr()+ "," +
            "currentLife" + ":" + getCurrentLife()+ "," +
            "score" + ":" + getScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificBall = "+(getSpecificBall()!=null?Integer.toHexString(System.identityHashCode(getSpecificBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificPaddle = "+(getSpecificPaddle()!=null?Integer.toHexString(System.identityHashCode(getSpecificPaddle())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 92 "../../../../../Block223States.ump"
  private boolean isGameAdmin() ;
// line 93 "../../../../../Block223States.ump"
  private boolean ofTestMode() ;
// line 94 "../../../../../Block223States.ump"
  private boolean hasEnoughBlocks() ;
// line 95 "../../../../../Block223States.ump"
  private boolean hasNoBlocks() ;
// line 96 "../../../../../Block223States.ump"
  private boolean isBlockHit() ;
// line 97 "../../../../../Block223States.ump"
  private boolean isWallHit() ;
// line 98 "../../../../../Block223States.ump"
  private boolean isPaddleHit() ;
// line 99 "../../../../../Block223States.ump"
  private boolean isOutOfBounds() ;
// line 100 "../../../../../Block223States.ump"
  private boolean isLastBlock() ;
// line 101 "../../../../../Block223States.ump"
  private boolean isLastLife() ;
// line 102 "../../../../../Block223States.ump"
  private boolean hasLifeLeft() ;
// line 103 "../../../../../Block223States.ump"
  private boolean isLastLevel() ;

  
}