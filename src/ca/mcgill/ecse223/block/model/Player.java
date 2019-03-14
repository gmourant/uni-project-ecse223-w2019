/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 76 "../../../../../Block223Persistence.ump"
// line 39 "../../../../../Block223.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<GameOffering> gameOfferings;
  private List<Score> scores;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223)
  {
    super(aPassword, aBlock223);
    gameOfferings = new ArrayList<GameOffering>();
    scores = new ArrayList<Score>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public GameOffering getGameOffering(int index)
  {
    GameOffering aGameOffering = gameOfferings.get(index);
    return aGameOffering;
  }

  public List<GameOffering> getGameOfferings()
  {
    List<GameOffering> newGameOfferings = Collections.unmodifiableList(gameOfferings);
    return newGameOfferings;
  }

  public int numberOfGameOfferings()
  {
    int number = gameOfferings.size();
    return number;
  }

  public boolean hasGameOfferings()
  {
    boolean has = gameOfferings.size() > 0;
    return has;
  }

  public int indexOfGameOffering(GameOffering aGameOffering)
  {
    int index = gameOfferings.indexOf(aGameOffering);
    return index;
  }
  /* Code from template association_GetMany */
  public Score getScore(int index)
  {
    Score aScore = scores.get(index);
    return aScore;
  }

  public List<Score> getScores()
  {
    List<Score> newScores = Collections.unmodifiableList(scores);
    return newScores;
  }

  public int numberOfScores()
  {
    int number = scores.size();
    return number;
  }

  public boolean hasScores()
  {
    boolean has = scores.size() > 0;
    return has;
  }

  public int indexOfScore(Score aScore)
  {
    int index = scores.indexOf(aScore);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGameOfferings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GameOffering addGameOffering(Game aGame)
  {
    return new GameOffering(aGame, this);
  }

  public boolean addGameOffering(GameOffering aGameOffering)
  {
    boolean wasAdded = false;
    if (gameOfferings.contains(aGameOffering)) { return false; }
    Player existingPlayer = aGameOffering.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aGameOffering.setPlayer(this);
    }
    else
    {
      gameOfferings.add(aGameOffering);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGameOffering(GameOffering aGameOffering)
  {
    boolean wasRemoved = false;
    //Unable to remove aGameOffering, as it must always have a player
    if (!this.equals(aGameOffering.getPlayer()))
    {
      gameOfferings.remove(aGameOffering);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameOfferingAt(GameOffering aGameOffering, int index)
  {  
    boolean wasAdded = false;
    if(addGameOffering(aGameOffering))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameOfferings()) { index = numberOfGameOfferings() - 1; }
      gameOfferings.remove(aGameOffering);
      gameOfferings.add(index, aGameOffering);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameOfferingAt(GameOffering aGameOffering, int index)
  {
    boolean wasAdded = false;
    if(gameOfferings.contains(aGameOffering))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameOfferings()) { index = numberOfGameOfferings() - 1; }
      gameOfferings.remove(aGameOffering);
      gameOfferings.add(index, aGameOffering);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameOfferingAt(aGameOffering, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfScores()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Score addScore(Game aGame)
  {
    return new Score(aGame, this);
  }

  public boolean addScore(Score aScore)
  {
    boolean wasAdded = false;
    if (scores.contains(aScore)) { return false; }
    Player existingPlayer = aScore.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aScore.setPlayer(this);
    }
    else
    {
      scores.add(aScore);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeScore(Score aScore)
  {
    boolean wasRemoved = false;
    //Unable to remove aScore, as it must always have a player
    if (!this.equals(aScore.getPlayer()))
    {
      scores.remove(aScore);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addScoreAt(Score aScore, int index)
  {  
    boolean wasAdded = false;
    if(addScore(aScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScores()) { index = numberOfScores() - 1; }
      scores.remove(aScore);
      scores.add(index, aScore);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScoreAt(Score aScore, int index)
  {
    boolean wasAdded = false;
    if(scores.contains(aScore))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScores()) { index = numberOfScores() - 1; }
      scores.remove(aScore);
      scores.add(index, aScore);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScoreAt(aScore, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=gameOfferings.size(); i > 0; i--)
    {
      GameOffering aGameOffering = gameOfferings.get(i - 1);
      aGameOffering.delete();
    }
    for(int i=scores.size(); i > 0; i--)
    {
      Score aScore = scores.get(i - 1);
      aScore.delete();
    }
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 79 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8L ;

  
}