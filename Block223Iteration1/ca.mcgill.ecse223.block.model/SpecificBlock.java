package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 59 "Block223App.ump"
public class SpecificBlock
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificBlock Attributes
  private int xCoordinate;
  private int yCoordinate;

  //SpecificBlock Associations
  private Block223 block223;
  private BlockType blockType;
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBlock(int aXCoordinate, int aYCoordinate, Block223 aBlock223, BlockType aBlockType, Level aLevel)
  {
    xCoordinate = aXCoordinate;
    yCoordinate = aYCoordinate;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create specificBlock due to block223");
    }
    if (!setBlockType(aBlockType))
    {
      throw new RuntimeException("Unable to create SpecificBlock due to aBlockType");
    }
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create specificBlock due to level");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setXCoordinate(int aXCoordinate)
  {
    boolean wasSet = false;
    xCoordinate = aXCoordinate;
    wasSet = true;
    return wasSet;
  }

  public boolean setYCoordinate(int aYCoordinate)
  {
    boolean wasSet = false;
    yCoordinate = aYCoordinate;
    wasSet = true;
    return wasSet;
  }

  public int getXCoordinate()
  {
    return xCoordinate;
  }

  public int getYCoordinate()
  {
    return yCoordinate;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetOne */
  public BlockType getBlockType()
  {
    return blockType;
  }
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
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
      existingBlock223.removeSpecificBlock(this);
    }
    block223.addSpecificBlock(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBlockType(BlockType aNewBlockType)
  {
    boolean wasSet = false;
    if (aNewBlockType != null)
    {
      blockType = aNewBlockType;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLevel(Level aLevel)
  {
    boolean wasSet = false;
    if (aLevel == null)
    {
      return wasSet;
    }

    Level existingLevel = level;
    level = aLevel;
    if (existingLevel != null && !existingLevel.equals(aLevel))
    {
      existingLevel.removeSpecificBlock(this);
    }
    level.addSpecificBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeSpecificBlock(this);
    }
    blockType = null;
    Level placeholderLevel = level;
    this.level = null;
    if(placeholderLevel != null)
    {
      placeholderLevel.removeSpecificBlock(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "xCoordinate" + ":" + getXCoordinate()+ "," +
            "yCoordinate" + ":" + getYCoordinate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "blockType = "+(getBlockType()!=null?Integer.toHexString(System.identityHashCode(getBlockType())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}