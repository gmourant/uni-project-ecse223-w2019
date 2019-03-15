/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 76 "../../../../../Block223Persistence.ump"
// line 40 "../../../../../Block223.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<GameSession> gameSessions;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223)
  {
    super(aPassword, aBlock223);
    gameSessions = new ArrayList<GameSession>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public GameSession getGameSession(int index)
  {
    GameSession aGameSession = gameSessions.get(index);
    return aGameSession;
  }

  public List<GameSession> getGameSessions()
  {
    List<GameSession> newGameSessions = Collections.unmodifiableList(gameSessions);
    return newGameSessions;
  }

  public int numberOfGameSessions()
  {
    int number = gameSessions.size();
    return number;
  }

  public boolean hasGameSessions()
  {
    boolean has = gameSessions.size() > 0;
    return has;
  }

  public int indexOfGameSession(GameSession aGameSession)
  {
    int index = gameSessions.indexOf(aGameSession);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGameSessions()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GameSession addGameSession(boolean aOfTestMode, Game aGame, SpecificBall aSpecificBall, SpecificPaddle aSpecificPaddle)
  {
    return new GameSession(aOfTestMode, aGame, this, aSpecificBall, aSpecificPaddle);
  }

  public boolean addGameSession(GameSession aGameSession)
  {
    boolean wasAdded = false;
    if (gameSessions.contains(aGameSession)) { return false; }
    Player existingPlayer = aGameSession.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aGameSession.setPlayer(this);
    }
    else
    {
      gameSessions.add(aGameSession);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGameSession(GameSession aGameSession)
  {
    boolean wasRemoved = false;
    //Unable to remove aGameSession, as it must always have a player
    if (!this.equals(aGameSession.getPlayer()))
    {
      gameSessions.remove(aGameSession);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameSessionAt(GameSession aGameSession, int index)
  {  
    boolean wasAdded = false;
    if(addGameSession(aGameSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameSessions()) { index = numberOfGameSessions() - 1; }
      gameSessions.remove(aGameSession);
      gameSessions.add(index, aGameSession);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameSessionAt(GameSession aGameSession, int index)
  {
    boolean wasAdded = false;
    if(gameSessions.contains(aGameSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameSessions()) { index = numberOfGameSessions() - 1; }
      gameSessions.remove(aGameSession);
      gameSessions.add(index, aGameSession);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameSessionAt(aGameSession, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=gameSessions.size(); i > 0; i--)
    {
      GameSession aGameSession = gameSessions.get(i - 1);
      aGameSession.delete();
    }
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 79 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8L ;

  
}