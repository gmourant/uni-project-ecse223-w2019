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
  private String ofTestMode;
  private int currentLevelNr;
  private int currentLife;
  private int score;

  //Autounique Attributes
  private int id;

  //GameSession Associations
  private Game game;
  private Player player;
  private List<SpecificBlockAssignment> specificBlockAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameSession(String aOfTestMode, Game aGame, Player aPlayer)
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
    specificBlockAssignments = new ArrayList<SpecificBlockAssignment>();
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

  public String getOfTestMode()
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
  public SpecificBlockAssignment getSpecificBlockAssignment(int index)
  {
    SpecificBlockAssignment aSpecificBlockAssignment = specificBlockAssignments.get(index);
    return aSpecificBlockAssignment;
  }

  public List<SpecificBlockAssignment> getSpecificBlockAssignments()
  {
    List<SpecificBlockAssignment> newSpecificBlockAssignments = Collections.unmodifiableList(specificBlockAssignments);
    return newSpecificBlockAssignments;
  }

  public int numberOfSpecificBlockAssignments()
  {
    int number = specificBlockAssignments.size();
    return number;
  }

  public boolean hasSpecificBlockAssignments()
  {
    boolean has = specificBlockAssignments.size() > 0;
    return has;
  }

  public int indexOfSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    int index = specificBlockAssignments.indexOf(aSpecificBlockAssignment);
    return index;
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
  public static int minimumNumberOfSpecificBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificBlockAssignment addSpecificBlockAssignment(BlockAssignment aBlockAssignment)
  {
    return new SpecificBlockAssignment(aBlockAssignment, this);
  }

  public boolean addSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    boolean wasAdded = false;
    if (specificBlockAssignments.contains(aSpecificBlockAssignment)) { return false; }
    GameSession existingGameSession = aSpecificBlockAssignment.getGameSession();
    boolean isNewGameSession = existingGameSession != null && !this.equals(existingGameSession);
    if (isNewGameSession)
    {
      aSpecificBlockAssignment.setGameSession(this);
    }
    else
    {
      specificBlockAssignments.add(aSpecificBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificBlockAssignment, as it must always have a gameSession
    if (!this.equals(aSpecificBlockAssignment.getGameSession()))
    {
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificBlockAssignmentAt(SpecificBlockAssignment aSpecificBlockAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificBlockAssignment(aSpecificBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBlockAssignments()) { index = numberOfSpecificBlockAssignments() - 1; }
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      specificBlockAssignments.add(index, aSpecificBlockAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificBlockAssignmentAt(SpecificBlockAssignment aSpecificBlockAssignment, int index)
  {
    boolean wasAdded = false;
    if(specificBlockAssignments.contains(aSpecificBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBlockAssignments()) { index = numberOfSpecificBlockAssignments() - 1; }
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      specificBlockAssignments.add(index, aSpecificBlockAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificBlockAssignmentAt(aSpecificBlockAssignment, index);
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
    for(int i=specificBlockAssignments.size(); i > 0; i--)
    {
      SpecificBlockAssignment aSpecificBlockAssignment = specificBlockAssignments.get(i - 1);
      aSpecificBlockAssignment.delete();
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
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}