/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 19 "../../../../../Block223PlayGame.ump"
public class SpecificBlock
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

  //SpecificBlock Associations
  private Block block;
  private GameSession gameSession;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBlock(Block aBlock, GameSession aGameSession)
  {
    id = nextId++;
    boolean didAddBlock = setBlock(aBlock);
    if (!didAddBlock)
    {
      throw new RuntimeException("Unable to create specificBlock due to block");
    }
    boolean didAddGameSession = setGameSession(aGameSession);
    if (!didAddGameSession)
    {
      throw new RuntimeException("Unable to create specificBlock due to gameSession");
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
  public Block getBlock()
  {
    return block;
  }
  /* Code from template association_GetOne */
  public GameSession getGameSession()
  {
    return gameSession;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock(Block aBlock)
  {
    boolean wasSet = false;
    if (aBlock == null)
    {
      return wasSet;
    }

    Block existingBlock = block;
    block = aBlock;
    if (existingBlock != null && !existingBlock.equals(aBlock))
    {
      existingBlock.removeSpecificBlock(this);
    }
    block.addSpecificBlock(this);
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
      existingGameSession.removeSpecificBlock(this);
    }
    gameSession.addSpecificBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block placeholderBlock = block;
    this.block = null;
    if(placeholderBlock != null)
    {
      placeholderBlock.removeSpecificBlock(this);
    }
    GameSession placeholderGameSession = gameSession;
    this.gameSession = null;
    if(placeholderGameSession != null)
    {
      placeholderGameSession.removeSpecificBlock(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gameSession = "+(getGameSession()!=null?Integer.toHexString(System.identityHashCode(getGameSession())):"null");
  }
}