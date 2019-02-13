package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 35 "Block223App.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private String name;

  //Game Associations
  private List<Level> levels;
  private PlayArea playArea;
  private List<BlockType> blockTypes;
  private Block223 block223;
  private Admin admin;
  private List<LeaderboardEntry> leaderboardEntries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aName, PlayArea aPlayArea, Block223 aBlock223, Admin aAdmin)
  {
    name = aName;
    levels = new ArrayList<Level>();
    if (aPlayArea == null || aPlayArea.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aPlayArea");
    }
    playArea = aPlayArea;
    blockTypes = new ArrayList<BlockType>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create game due to block223");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
    leaderboardEntries = new ArrayList<LeaderboardEntry>();
  }

  public Game(String aName, int aWidthForPlayArea, int aHeightForPlayArea, Block223 aBlock223ForPlayArea, Paddle aPaddleForPlayArea, Ball aBallForPlayArea, Block223 aBlock223, Admin aAdmin)
  {
    name = aName;
    levels = new ArrayList<Level>();
    playArea = new PlayArea(aWidthForPlayArea, aHeightForPlayArea, aBlock223ForPlayArea, this, aPaddleForPlayArea, aBallForPlayArea);
    blockTypes = new ArrayList<BlockType>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create game due to block223");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
    leaderboardEntries = new ArrayList<LeaderboardEntry>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Level getLevel(int index)
  {
    Level aLevel = levels.get(index);
    return aLevel;
  }

  public List<Level> getLevels()
  {
    List<Level> newLevels = Collections.unmodifiableList(levels);
    return newLevels;
  }

  public int numberOfLevels()
  {
    int number = levels.size();
    return number;
  }

  public boolean hasLevels()
  {
    boolean has = levels.size() > 0;
    return has;
  }

  public int indexOfLevel(Level aLevel)
  {
    int index = levels.indexOf(aLevel);
    return index;
  }
  /* Code from template association_GetOne */
  public PlayArea getPlayArea()
  {
    return playArea;
  }
  /* Code from template association_GetMany */
  public BlockType getBlockType(int index)
  {
    BlockType aBlockType = blockTypes.get(index);
    return aBlockType;
  }

  public List<BlockType> getBlockTypes()
  {
    List<BlockType> newBlockTypes = Collections.unmodifiableList(blockTypes);
    return newBlockTypes;
  }

  public int numberOfBlockTypes()
  {
    int number = blockTypes.size();
    return number;
  }

  public boolean hasBlockTypes()
  {
    boolean has = blockTypes.size() > 0;
    return has;
  }

  public int indexOfBlockType(BlockType aBlockType)
  {
    int index = blockTypes.indexOf(aBlockType);
    return index;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_GetMany */
  public LeaderboardEntry getLeaderboardEntry(int index)
  {
    LeaderboardEntry aLeaderboardEntry = leaderboardEntries.get(index);
    return aLeaderboardEntry;
  }

  public List<LeaderboardEntry> getLeaderboardEntries()
  {
    List<LeaderboardEntry> newLeaderboardEntries = Collections.unmodifiableList(leaderboardEntries);
    return newLeaderboardEntries;
  }

  public int numberOfLeaderboardEntries()
  {
    int number = leaderboardEntries.size();
    return number;
  }

  public boolean hasLeaderboardEntries()
  {
    boolean has = leaderboardEntries.size() > 0;
    return has;
  }

  public int indexOfLeaderboardEntry(LeaderboardEntry aLeaderboardEntry)
  {
    int index = leaderboardEntries.indexOf(aLeaderboardEntry);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfLevels()
  {
    return 99;
  }
  /* Code from template association_AddOptionalNToOne */
  public Level addLevel(int aNumber, boolean aIsRandom, int aNumberOfBlocks, Block223 aBlock223)
  {
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return null;
    }
    else
    {
      return new Level(aNumber, aIsRandom, aNumberOfBlocks, aBlock223, this);
    }
  }

  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return wasAdded;
    }

    Game existingGame = aLevel.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aLevel.setGame(this);
    }
    else
    {
      levels.add(aLevel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    //Unable to remove aLevel, as it must always have a game
    if (!this.equals(aLevel.getGame()))
    {
      levels.remove(aLevel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLevelAt(Level aLevel, int index)
  {  
    boolean wasAdded = false;
    if(addLevel(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLevelAt(Level aLevel, int index)
  {
    boolean wasAdded = false;
    if(levels.contains(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLevelAt(aLevel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockType addBlockType(int aPointValue, String aColor, Block223 aBlock223)
  {
    return new BlockType(aPointValue, aColor, aBlock223, this);
  }

  public boolean addBlockType(BlockType aBlockType)
  {
    boolean wasAdded = false;
    if (blockTypes.contains(aBlockType)) { return false; }
    Game existingGame = aBlockType.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aBlockType.setGame(this);
    }
    else
    {
      blockTypes.add(aBlockType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockType(BlockType aBlockType)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockType, as it must always have a game
    if (!this.equals(aBlockType.getGame()))
    {
      blockTypes.remove(aBlockType);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockTypeAt(BlockType aBlockType, int index)
  {  
    boolean wasAdded = false;
    if(addBlockType(aBlockType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockTypes()) { index = numberOfBlockTypes() - 1; }
      blockTypes.remove(aBlockType);
      blockTypes.add(index, aBlockType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockTypeAt(BlockType aBlockType, int index)
  {
    boolean wasAdded = false;
    if(blockTypes.contains(aBlockType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockTypes()) { index = numberOfBlockTypes() - 1; }
      blockTypes.remove(aBlockType);
      blockTypes.add(index, aBlockType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockTypeAt(aBlockType, index);
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
      existingBlock223.removeGame(this);
    }
    block223.addGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeGame(this);
    }
    admin.addGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLeaderboardEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LeaderboardEntry addLeaderboardEntry(Player aPlayer, Block223 aBlock223)
  {
    return new LeaderboardEntry(aPlayer, this, aBlock223);
  }

  public boolean addLeaderboardEntry(LeaderboardEntry aLeaderboardEntry)
  {
    boolean wasAdded = false;
    if (leaderboardEntries.contains(aLeaderboardEntry)) { return false; }
    Game existingGame = aLeaderboardEntry.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aLeaderboardEntry.setGame(this);
    }
    else
    {
      leaderboardEntries.add(aLeaderboardEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLeaderboardEntry(LeaderboardEntry aLeaderboardEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aLeaderboardEntry, as it must always have a game
    if (!this.equals(aLeaderboardEntry.getGame()))
    {
      leaderboardEntries.remove(aLeaderboardEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLeaderboardEntryAt(LeaderboardEntry aLeaderboardEntry, int index)
  {  
    boolean wasAdded = false;
    if(addLeaderboardEntry(aLeaderboardEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLeaderboardEntries()) { index = numberOfLeaderboardEntries() - 1; }
      leaderboardEntries.remove(aLeaderboardEntry);
      leaderboardEntries.add(index, aLeaderboardEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLeaderboardEntryAt(LeaderboardEntry aLeaderboardEntry, int index)
  {
    boolean wasAdded = false;
    if(leaderboardEntries.contains(aLeaderboardEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLeaderboardEntries()) { index = numberOfLeaderboardEntries() - 1; }
      leaderboardEntries.remove(aLeaderboardEntry);
      leaderboardEntries.add(index, aLeaderboardEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLeaderboardEntryAt(aLeaderboardEntry, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=levels.size(); i > 0; i--)
    {
      Level aLevel = levels.get(i - 1);
      aLevel.delete();
    }
    PlayArea existingPlayArea = playArea;
    playArea = null;
    if (existingPlayArea != null)
    {
      existingPlayArea.delete();
    }
    for(int i=blockTypes.size(); i > 0; i--)
    {
      BlockType aBlockType = blockTypes.get(i - 1);
      aBlockType.delete();
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeGame(this);
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeGame(this);
    }
    for(int i=leaderboardEntries.size(); i > 0; i--)
    {
      LeaderboardEntry aLeaderboardEntry = leaderboardEntries.get(i - 1);
      aLeaderboardEntry.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "playArea = "+(getPlayArea()!=null?Integer.toHexString(System.identityHashCode(getPlayArea())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}