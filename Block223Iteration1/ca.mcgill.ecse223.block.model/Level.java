package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 51 "Block223App.ump"
public class Level
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Attributes
  private int number;
  private boolean isRandom;
  private int numberOfBlocks;

  //Level Associations
  private List<SpecificBlock> specificBlocks;
  private Block223 block223;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(int aNumber, boolean aIsRandom, int aNumberOfBlocks, Block223 aBlock223, Game aGame)
  {
    number = aNumber;
    isRandom = aIsRandom;
    numberOfBlocks = aNumberOfBlocks;
    specificBlocks = new ArrayList<SpecificBlock>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create level due to block223");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create level due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsRandom(boolean aIsRandom)
  {
    boolean wasSet = false;
    isRandom = aIsRandom;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfBlocks(int aNumberOfBlocks)
  {
    boolean wasSet = false;
    numberOfBlocks = aNumberOfBlocks;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public boolean getIsRandom()
  {
    return isRandom;
  }

  public int getNumberOfBlocks()
  {
    return numberOfBlocks;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsRandom()
  {
    return isRandom;
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
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificBlock addSpecificBlock(int aXCoordinate, int aYCoordinate, Block223 aBlock223, BlockType aBlockType)
  {
    return new SpecificBlock(aXCoordinate, aYCoordinate, aBlock223, aBlockType, this);
  }

  public boolean addSpecificBlock(SpecificBlock aSpecificBlock)
  {
    boolean wasAdded = false;
    if (specificBlocks.contains(aSpecificBlock)) { return false; }
    Level existingLevel = aSpecificBlock.getLevel();
    boolean isNewLevel = existingLevel != null && !this.equals(existingLevel);
    if (isNewLevel)
    {
      aSpecificBlock.setLevel(this);
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
    //Unable to remove aSpecificBlock, as it must always have a level
    if (!this.equals(aSpecificBlock.getLevel()))
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
      existingBlock223.removeLevel(this);
    }
    block223.addLevel(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to level
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (99)
    if (aGame.numberOfLevels() >= Game.maximumNumberOfLevels())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeLevel(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addLevel(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=specificBlocks.size(); i > 0; i--)
    {
      SpecificBlock aSpecificBlock = specificBlocks.get(i - 1);
      aSpecificBlock.delete();
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeLevel(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeLevel(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "isRandom" + ":" + getIsRandom()+ "," +
            "numberOfBlocks" + ":" + getNumberOfBlocks()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}