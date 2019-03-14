/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 76 "../../../../../Block223Persistence.ump"
// line 39 "../../../../../Block223.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<GameOffering> gameOfferings;
  private List<HallOfFameEntry> hallOfFameEntries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223)
  {
    super(aPassword, aBlock223);
    gameOfferings = new ArrayList<GameOffering>();
    hallOfFameEntries = new ArrayList<HallOfFameEntry>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public GameOffering getGameOffering(int index)
  {
    GameOffering aGameOffering = gameOfferings.get(index);
    return aGameOffering;
  }

  public List<GameOffering> getGameOfferings()
  {
    List<GameOffering> newGameOfferings = Collections.unmodifiableList(gameOfferings);
    return newGameOfferings;
  }

  public int numberOfGameOfferings()
  {
    int number = gameOfferings.size();
    return number;
  }

  public boolean hasGameOfferings()
  {
    boolean has = gameOfferings.size() > 0;
    return has;
  }

  public int indexOfGameOffering(GameOffering aGameOffering)
  {
    int index = gameOfferings.indexOf(aGameOffering);
    return index;
  }
  /* Code from template association_GetMany */
  public HallOfFameEntry getHallOfFameEntry(int index)
  {
    HallOfFameEntry aHallOfFameEntry = hallOfFameEntries.get(index);
    return aHallOfFameEntry;
  }

  public List<HallOfFameEntry> getHallOfFameEntries()
  {
    List<HallOfFameEntry> newHallOfFameEntries = Collections.unmodifiableList(hallOfFameEntries);
    return newHallOfFameEntries;
  }

  public int numberOfHallOfFameEntries()
  {
    int number = hallOfFameEntries.size();
    return number;
  }

  public boolean hasHallOfFameEntries()
  {
    boolean has = hallOfFameEntries.size() > 0;
    return has;
  }

  public int indexOfHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    int index = hallOfFameEntries.indexOf(aHallOfFameEntry);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGameOfferings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GameOffering addGameOffering(Game aGame)
  {
    return new GameOffering(aGame, this);
  }

  public boolean addGameOffering(GameOffering aGameOffering)
  {
    boolean wasAdded = false;
    if (gameOfferings.contains(aGameOffering)) { return false; }
    Player existingPlayer = aGameOffering.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aGameOffering.setPlayer(this);
    }
    else
    {
      gameOfferings.add(aGameOffering);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGameOffering(GameOffering aGameOffering)
  {
    boolean wasRemoved = false;
    //Unable to remove aGameOffering, as it must always have a player
    if (!this.equals(aGameOffering.getPlayer()))
    {
      gameOfferings.remove(aGameOffering);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameOfferingAt(GameOffering aGameOffering, int index)
  {  
    boolean wasAdded = false;
    if(addGameOffering(aGameOffering))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameOfferings()) { index = numberOfGameOfferings() - 1; }
      gameOfferings.remove(aGameOffering);
      gameOfferings.add(index, aGameOffering);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameOfferingAt(GameOffering aGameOffering, int index)
  {
    boolean wasAdded = false;
    if(gameOfferings.contains(aGameOffering))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameOfferings()) { index = numberOfGameOfferings() - 1; }
      gameOfferings.remove(aGameOffering);
      gameOfferings.add(index, aGameOffering);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameOfferingAt(aGameOffering, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHallOfFameEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public HallOfFameEntry addHallOfFameEntry(int aScore, Game aGame)
  {
    return new HallOfFameEntry(aScore, aGame, this);
  }

  public boolean addHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    boolean wasAdded = false;
    if (hallOfFameEntries.contains(aHallOfFameEntry)) { return false; }
    Player existingPlayer = aHallOfFameEntry.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aHallOfFameEntry.setPlayer(this);
    }
    else
    {
      hallOfFameEntries.add(aHallOfFameEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aHallOfFameEntry, as it must always have a player
    if (!this.equals(aHallOfFameEntry.getPlayer()))
    {
      hallOfFameEntries.remove(aHallOfFameEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHallOfFameEntryAt(HallOfFameEntry aHallOfFameEntry, int index)
  {  
    boolean wasAdded = false;
    if(addHallOfFameEntry(aHallOfFameEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFameEntries()) { index = numberOfHallOfFameEntries() - 1; }
      hallOfFameEntries.remove(aHallOfFameEntry);
      hallOfFameEntries.add(index, aHallOfFameEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHallOfFameEntryAt(HallOfFameEntry aHallOfFameEntry, int index)
  {
    boolean wasAdded = false;
    if(hallOfFameEntries.contains(aHallOfFameEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFameEntries()) { index = numberOfHallOfFameEntries() - 1; }
      hallOfFameEntries.remove(aHallOfFameEntry);
      hallOfFameEntries.add(index, aHallOfFameEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHallOfFameEntryAt(aHallOfFameEntry, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=gameOfferings.size(); i > 0; i--)
    {
      GameOffering aGameOffering = gameOfferings.get(i - 1);
      aGameOffering.delete();
    }
    for(int i=hallOfFameEntries.size(); i > 0; i--)
    {
      HallOfFameEntry aHallOfFameEntry = hallOfFameEntries.get(i - 1);
      aHallOfFameEntry.delete();
    }
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 79 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8L ;

  
}