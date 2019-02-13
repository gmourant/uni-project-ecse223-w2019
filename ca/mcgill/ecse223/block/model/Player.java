package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 24 "Block223App.ump"
public class Player extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<LeaderboardEntry> leaderboardEntries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223, User aUser)
  {
    super(aPassword, aBlock223, aUser);
    leaderboardEntries = new ArrayList<LeaderboardEntry>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  public static int minimumNumberOfLeaderboardEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LeaderboardEntry addLeaderboardEntry(Game aGame, Block223 aBlock223)
  {
    return new LeaderboardEntry(this, aGame, aBlock223);
  }

  public boolean addLeaderboardEntry(LeaderboardEntry aLeaderboardEntry)
  {
    boolean wasAdded = false;
    if (leaderboardEntries.contains(aLeaderboardEntry)) { return false; }
    Player existingPlayer = aLeaderboardEntry.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aLeaderboardEntry.setPlayer(this);
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
    //Unable to remove aLeaderboardEntry, as it must always have a player
    if (!this.equals(aLeaderboardEntry.getPlayer()))
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
    for(int i=leaderboardEntries.size(); i > 0; i--)
    {
      LeaderboardEntry aLeaderboardEntry = leaderboardEntries.get(i - 1);
      aLeaderboardEntry.delete();
    }
    super.delete();
  }

}