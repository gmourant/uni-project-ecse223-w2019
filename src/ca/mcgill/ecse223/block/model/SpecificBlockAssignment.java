/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 17 "../../../../../Block223PlayGame.ump"
public class SpecificBlockAssignment
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int id;

  //SpecificBlockAssignment Associations
  private BlockAssignment blockAssignment;
  private GameSession gameSession;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBlockAssignment(BlockAssignment aBlockAssignment, GameSession aGameSession)
  {
    id = nextId++;
    boolean didAddBlockAssignment = setBlockAssignment(aBlockAssignment);
    if (!didAddBlockAssignment)
    {
      throw new RuntimeException("Unable to create specificBlockAssignment due to blockAssignment");
    }
    boolean didAddGameSession = setGameSession(aGameSession);
    if (!didAddGameSession)
    {
      throw new RuntimeException("Unable to create specificBlockAssignment due to gameSession");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public BlockAssignment getBlockAssignment()
  {
    return blockAssignment;
  }
  /* Code from template association_GetOne */
  public GameSession getGameSession()
  {
    return gameSession;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasSet = false;
    if (aBlockAssignment == null)
    {
      return wasSet;
    }

    BlockAssignment existingBlockAssignment = blockAssignment;
    blockAssignment = aBlockAssignment;
    if (existingBlockAssignment != null && !existingBlockAssignment.equals(aBlockAssignment))
    {
      existingBlockAssignment.removeSpecificBlockAssignment(this);
    }
    blockAssignment.addSpecificBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGameSession(GameSession aGameSession)
  {
    boolean wasSet = false;
    if (aGameSession == null)
    {
      return wasSet;
    }

    GameSession existingGameSession = gameSession;
    gameSession = aGameSession;
    if (existingGameSession != null && !existingGameSession.equals(aGameSession))
    {
      existingGameSession.removeSpecificBlockAssignment(this);
    }
    gameSession.addSpecificBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BlockAssignment placeholderBlockAssignment = blockAssignment;
    this.blockAssignment = null;
    if(placeholderBlockAssignment != null)
    {
      placeholderBlockAssignment.removeSpecificBlockAssignment(this);
    }
    GameSession placeholderGameSession = gameSession;
    this.gameSession = null;
    if(placeholderGameSession != null)
    {
      placeholderGameSession.removeSpecificBlockAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "blockAssignment = "+(getBlockAssignment()!=null?Integer.toHexString(System.identityHashCode(getBlockAssignment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gameSession = "+(getGameSession()!=null?Integer.toHexString(System.identityHashCode(getGameSession())):"null");
  }
}