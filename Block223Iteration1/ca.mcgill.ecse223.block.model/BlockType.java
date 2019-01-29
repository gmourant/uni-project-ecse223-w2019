package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 43 "Block223App.ump"
public class BlockType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockType Attributes
  private int pointValue;
  private String color;
  private int sideLength;

  //BlockType Associations
  private Block223 block223;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockType(int aPointValue, String aColor, Block223 aBlock223, Game aGame)
  {
    pointValue = aPointValue;
    color = aColor;
    sideLength = 20;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create blockType due to block223");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create blockType due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPointValue(int aPointValue)
  {
    boolean wasSet = false;
    pointValue = aPointValue;
    wasSet = true;
    return wasSet;
  }

  public boolean setColor(String aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setSideLength(int aSideLength)
  {
    boolean wasSet = false;
    sideLength = aSideLength;
    wasSet = true;
    return wasSet;
  }

  public int getPointValue()
  {
    return pointValue;
  }

  public String getColor()
  {
    return color;
  }

  public int getSideLength()
  {
    return sideLength;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
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
      existingBlock223.removeBlockType(this);
    }
    block223.addBlockType(this);
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
      existingGame.removeBlockType(this);
    }
    game.addBlockType(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeBlockType(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlockType(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "pointValue" + ":" + getPointValue()+ "," +
            "color" + ":" + getColor()+ "," +
            "sideLength" + ":" + getSideLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}