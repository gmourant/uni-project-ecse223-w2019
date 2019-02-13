package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 1 "Block223App.ump"
public class Block223
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block223 Associations
  private List<PlayArea> playAreas;
  private List<User> users;
  private List<Game> games;
  private List<Level> levels;
  private List<UserRole> userRoles;
  private List<BlockType> blockTypes;
  private List<SpecificBlock> specificBlocks;
  private List<LeaderboardEntry> leaderboardEntries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block223()
  {
    playAreas = new ArrayList<PlayArea>();
    users = new ArrayList<User>();
    games = new ArrayList<Game>();
    levels = new ArrayList<Level>();
    userRoles = new ArrayList<UserRole>();
    blockTypes = new ArrayList<BlockType>();
    specificBlocks = new ArrayList<SpecificBlock>();
    leaderboardEntries = new ArrayList<LeaderboardEntry>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public PlayArea getPlayArea(int index)
  {
    PlayArea aPlayArea = playAreas.get(index);
    return aPlayArea;
  }

  public List<PlayArea> getPlayAreas()
  {
    List<PlayArea> newPlayAreas = Collections.unmodifiableList(playAreas);
    return newPlayAreas;
  }

  public int numberOfPlayAreas()
  {
    int number = playAreas.size();
    return number;
  }

  public boolean hasPlayAreas()
  {
    boolean has = playAreas.size() > 0;
    return has;
  }

  public int indexOfPlayArea(PlayArea aPlayArea)
  {
    int index = playAreas.indexOf(aPlayArea);
    return index;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
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
  /* Code from template association_GetMany */
  public UserRole getUserRole(int index)
  {
    UserRole aUserRole = userRoles.get(index);
    return aUserRole;
  }

  public List<UserRole> getUserRoles()
  {
    List<UserRole> newUserRoles = Collections.unmodifiableList(userRoles);
    return newUserRoles;
  }

  public int numberOfUserRoles()
  {
    int number = userRoles.size();
    return number;
  }

  public boolean hasUserRoles()
  {
    boolean has = userRoles.size() > 0;
    return has;
  }

  public int indexOfUserRole(UserRole aUserRole)
  {
    int index = userRoles.indexOf(aUserRole);
    return index;
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
  public static int minimumNumberOfPlayAreas()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayArea addPlayArea(int aWidth, int aHeight, Game aGame, Paddle aPaddle, Ball aBall)
  {
    return new PlayArea(aWidth, aHeight, this, aGame, aPaddle, aBall);
  }

  public boolean addPlayArea(PlayArea aPlayArea)
  {
    boolean wasAdded = false;
    if (playAreas.contains(aPlayArea)) { return false; }
    Block223 existingBlock223 = aPlayArea.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aPlayArea.setBlock223(this);
    }
    else
    {
      playAreas.add(aPlayArea);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayArea(PlayArea aPlayArea)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayArea, as it must always have a block223
    if (!this.equals(aPlayArea.getBlock223()))
    {
      playAreas.remove(aPlayArea);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayAreaAt(PlayArea aPlayArea, int index)
  {  
    boolean wasAdded = false;
    if(addPlayArea(aPlayArea))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayAreas()) { index = numberOfPlayAreas() - 1; }
      playAreas.remove(aPlayArea);
      playAreas.add(index, aPlayArea);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayAreaAt(PlayArea aPlayArea, int index)
  {
    boolean wasAdded = false;
    if(playAreas.contains(aPlayArea))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayAreas()) { index = numberOfPlayAreas() - 1; }
      playAreas.remove(aPlayArea);
      playAreas.add(index, aPlayArea);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayAreaAt(aPlayArea, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aUserName)
  {
    return new User(aUserName, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    Block223 existingBlock223 = aUser.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aUser.setBlock223(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a block223
    if (!this.equals(aUser.getBlock223()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Game addGame(String aName, PlayArea aPlayArea, Admin aAdmin)
  {
    return new Game(aName, aPlayArea, this, aAdmin);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Block223 existingBlock223 = aGame.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aGame.setBlock223(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a block223
    if (!this.equals(aGame.getBlock223()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Level addLevel(int aNumber, boolean aIsRandom, int aNumberOfBlocks, Game aGame)
  {
    return new Level(aNumber, aIsRandom, aNumberOfBlocks, this, aGame);
  }

  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    Block223 existingBlock223 = aLevel.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aLevel.setBlock223(this);
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
    //Unable to remove aLevel, as it must always have a block223
    if (!this.equals(aLevel.getBlock223()))
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
  public static int minimumNumberOfUserRoles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public UserRole addUserRole(String aPassword, User aUser)
  {
    return new UserRole(aPassword, this, aUser);
  }

  public boolean addUserRole(UserRole aUserRole)
  {
    boolean wasAdded = false;
    if (userRoles.contains(aUserRole)) { return false; }
    Block223 existingBlock223 = aUserRole.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aUserRole.setBlock223(this);
    }
    else
    {
      userRoles.add(aUserRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserRole(UserRole aUserRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserRole, as it must always have a block223
    if (!this.equals(aUserRole.getBlock223()))
    {
      userRoles.remove(aUserRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserRoleAt(UserRole aUserRole, int index)
  {  
    boolean wasAdded = false;
    if(addUserRole(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserRoleAt(UserRole aUserRole, int index)
  {
    boolean wasAdded = false;
    if(userRoles.contains(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserRoleAt(aUserRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockType addBlockType(int aPointValue, String aColor, Game aGame)
  {
    return new BlockType(aPointValue, aColor, this, aGame);
  }

  public boolean addBlockType(BlockType aBlockType)
  {
    boolean wasAdded = false;
    if (blockTypes.contains(aBlockType)) { return false; }
    Block223 existingBlock223 = aBlockType.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aBlockType.setBlock223(this);
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
    //Unable to remove aBlockType, as it must always have a block223
    if (!this.equals(aBlockType.getBlock223()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificBlock addSpecificBlock(int aXCoordinate, int aYCoordinate, BlockType aBlockType, Level aLevel)
  {
    return new SpecificBlock(aXCoordinate, aYCoordinate, this, aBlockType, aLevel);
  }

  public boolean addSpecificBlock(SpecificBlock aSpecificBlock)
  {
    boolean wasAdded = false;
    if (specificBlocks.contains(aSpecificBlock)) { return false; }
    Block223 existingBlock223 = aSpecificBlock.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aSpecificBlock.setBlock223(this);
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
    //Unable to remove aSpecificBlock, as it must always have a block223
    if (!this.equals(aSpecificBlock.getBlock223()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLeaderboardEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LeaderboardEntry addLeaderboardEntry(Player aPlayer, Game aGame)
  {
    return new LeaderboardEntry(aPlayer, aGame, this);
  }

  public boolean addLeaderboardEntry(LeaderboardEntry aLeaderboardEntry)
  {
    boolean wasAdded = false;
    if (leaderboardEntries.contains(aLeaderboardEntry)) { return false; }
    Block223 existingBlock223 = aLeaderboardEntry.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aLeaderboardEntry.setBlock223(this);
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
    //Unable to remove aLeaderboardEntry, as it must always have a block223
    if (!this.equals(aLeaderboardEntry.getBlock223()))
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
    while (playAreas.size() > 0)
    {
      PlayArea aPlayArea = playAreas.get(playAreas.size() - 1);
      aPlayArea.delete();
      playAreas.remove(aPlayArea);
    }
    
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (games.size() > 0)
    {
      Game aGame = games.get(games.size() - 1);
      aGame.delete();
      games.remove(aGame);
    }
    
    while (levels.size() > 0)
    {
      Level aLevel = levels.get(levels.size() - 1);
      aLevel.delete();
      levels.remove(aLevel);
    }
    
    while (userRoles.size() > 0)
    {
      UserRole aUserRole = userRoles.get(userRoles.size() - 1);
      aUserRole.delete();
      userRoles.remove(aUserRole);
    }
    
    while (blockTypes.size() > 0)
    {
      BlockType aBlockType = blockTypes.get(blockTypes.size() - 1);
      aBlockType.delete();
      blockTypes.remove(aBlockType);
    }
    
    while (specificBlocks.size() > 0)
    {
      SpecificBlock aSpecificBlock = specificBlocks.get(specificBlocks.size() - 1);
      aSpecificBlock.delete();
      specificBlocks.remove(aSpecificBlock);
    }
    
    while (leaderboardEntries.size() > 0)
    {
      LeaderboardEntry aLeaderboardEntry = leaderboardEntries.get(leaderboardEntries.size() - 1);
      aLeaderboardEntry.delete();
      leaderboardEntries.remove(aLeaderboardEntry);
    }
    
  }

}