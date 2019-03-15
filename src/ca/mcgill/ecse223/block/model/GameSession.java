/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 8 "../../../../../Block223PlayGame.ump"
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

  //GameSession Associations
  private Game game;
  private Player player;
  private List<SpecificBlock> specificBlocks;
  private SpecificBall specificBall;
  private SpecificPaddle specificPaddle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameSession(boolean aOfTestMode, Game aGame, Player aPlayer, SpecificBall aSpecificBall, SpecificPaddle aSpecificPaddle)
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
  }

  public GameSession(boolean aOfTestMode, Game aGame, Player aPlayer, boolean aIsOutBoundsForSpecificBall, int aPositionXForSpecificBall, int aPositionYForSpecificBall, int aPositionXForSpecificPaddle, int aPositionYForSpecificPaddle)
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
    for(int i=specificBlocks.size(); i > 0; i--)
    {
      SpecificBlock aSpecificBlock = specificBlocks.get(i - 1);
      aSpecificBlock.delete();
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
            "  " + "specificBall = "+(getSpecificBall()!=null?Integer.toHexString(System.identityHashCode(getSpecificBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificPaddle = "+(getSpecificPaddle()!=null?Integer.toHexString(System.identityHashCode(getSpecificPaddle())):"null");
  }
}