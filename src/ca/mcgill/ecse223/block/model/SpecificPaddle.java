/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 32 "../../../../../Block223PlayGame.ump"
public class SpecificPaddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificPaddle Attributes
  private int positionX;
  private int positionY;

  //SpecificPaddle Associations
  private GameSession gameSession;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificPaddle(int aPositionX, int aPositionY, GameSession aGameSession)
  {
    positionX = aPositionX;
    positionY = aPositionY;
    if (aGameSession == null || aGameSession.getSpecificPaddle() != null)
    {
      throw new RuntimeException("Unable to create SpecificPaddle due to aGameSession");
    }
    gameSession = aGameSession;
  }

  public SpecificPaddle(int aPositionX, int aPositionY, boolean aOfTestModeForGameSession, Game aGameForGameSession, Player aPlayerForGameSession, Block223 aBlock223ForGameSession, SpecificBall aSpecificBallForGameSession)
  {
    positionX = aPositionX;
    positionY = aPositionY;
    gameSession = new GameSession(aOfTestModeForGameSession, aGameForGameSession, aPlayerForGameSession, aBlock223ForGameSession, aSpecificBallForGameSession, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPositionX(int aPositionX)
  {
    boolean wasSet = false;
    positionX = aPositionX;
    wasSet = true;
    return wasSet;
  }

  public int getPositionX()
  {
    return positionX;
  }

  public int getPositionY()
  {
    return positionY;
  }
  /* Code from template association_GetOne */
  public GameSession getGameSession()
  {
    return gameSession;
  }

  public void delete()
  {
    GameSession existingGameSession = gameSession;
    gameSession = null;
    if (existingGameSession != null)
    {
      existingGameSession.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "positionX" + ":" + getPositionX()+ "," +
            "positionY" + ":" + getPositionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameSession = "+(getGameSession()!=null?Integer.toHexString(System.identityHashCode(getGameSession())):"null");
  }
}