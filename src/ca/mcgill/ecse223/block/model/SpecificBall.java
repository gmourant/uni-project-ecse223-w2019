/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 27 "../../../../../Block223PlayGame.ump"
public class SpecificBall
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificBall Attributes
  private boolean isOutBounds;
  private int positionX;
  private int positionY;

  //SpecificBall Associations
  private GameSession gameSession;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBall(boolean aIsOutBounds, int aPositionX, int aPositionY, GameSession aGameSession)
  {
    isOutBounds = aIsOutBounds;
    positionX = aPositionX;
    positionY = aPositionY;
    if (aGameSession == null || aGameSession.getSpecificBall() != null)
    {
      throw new RuntimeException("Unable to create SpecificBall due to aGameSession");
    }
    gameSession = aGameSession;
  }

  public SpecificBall(boolean aIsOutBounds, int aPositionX, int aPositionY, boolean aOfTestModeForGameSession, Game aGameForGameSession, User aUserForGameSession, Block223 aBlock223ForGameSession, SpecificPaddle aSpecificPaddleForGameSession)
  {
    isOutBounds = aIsOutBounds;
    positionX = aPositionX;
    positionY = aPositionY;
    gameSession = new GameSession(aOfTestModeForGameSession, aGameForGameSession, aUserForGameSession, aBlock223ForGameSession, this, aSpecificPaddleForGameSession);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsOutBounds(boolean aIsOutBounds)
  {
    boolean wasSet = false;
    isOutBounds = aIsOutBounds;
    wasSet = true;
    return wasSet;
  }

  public boolean setPositionX(int aPositionX)
  {
    boolean wasSet = false;
    positionX = aPositionX;
    wasSet = true;
    return wasSet;
  }

  public boolean setPositionY(int aPositionY)
  {
    boolean wasSet = false;
    positionY = aPositionY;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsOutBounds()
  {
    return isOutBounds;
  }

  public int getPositionX()
  {
    return positionX;
  }

  public int getPositionY()
  {
    return positionY;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsOutBounds()
  {
    return isOutBounds;
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
            "isOutBounds" + ":" + getIsOutBounds()+ "," +
            "positionX" + ":" + getPositionX()+ "," +
            "positionY" + ":" + getPositionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameSession = "+(getGameSession()!=null?Integer.toHexString(System.identityHashCode(getGameSession())):"null");
  }
}