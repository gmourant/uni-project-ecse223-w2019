/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 52 "../../../../../Block223Persistence.ump"
// line 13 "../../../../../Block223.ump"
public class User implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByUsername = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;

  //User Associations
  private List<UserRole> roles;
  private List<GameSession> gameSessions;
  private List<HallOfFameEntry> hallOfFameEntries;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUsername, Block223 aBlock223, UserRole... allRoles)
  {
    // line 18 "../../../../../Block223.ump"
    if(aUsername.equals("") || aUsername == null){
       			throw new RuntimeException("The username has already been taken.");
       		}
    // END OF UMPLE BEFORE INJECTION
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username");
    }
    roles = new ArrayList<UserRole>();
    boolean didAddRoles = setRoles(allRoles);
    if (!didAddRoles)
    {
      throw new RuntimeException("Unable to create User, must have 1 to 2 roles");
    }
    gameSessions = new ArrayList<GameSession>();
    hallOfFameEntries = new ArrayList<HallOfFameEntry>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create user due to block223");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    // line 18 "../../../../../Block223.ump"
    if(aUsername.equals("") || aUsername == null){
       			throw new RuntimeException("The username has already been taken.");
       		}
    // END OF UMPLE BEFORE INJECTION
    String anOldUsername = getUsername();
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      usersByUsername.remove(anOldUsername);
    }
    usersByUsername.put(aUsername, this);
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithUsername(String aUsername)
  {
    return usersByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }
  /* Code from template association_GetMany */
  public UserRole getRole(int index)
  {
    UserRole aRole = roles.get(index);
    return aRole;
  }

  public List<UserRole> getRoles()
  {
    List<UserRole> newRoles = Collections.unmodifiableList(roles);
    return newRoles;
  }

  public int numberOfRoles()
  {
    int number = roles.size();
    return number;
  }

  public boolean hasRoles()
  {
    boolean has = roles.size() > 0;
    return has;
  }

  public int indexOfRole(UserRole aRole)
  {
    int index = roles.indexOf(aRole);
    return index;
  }
  /* Code from template association_GetMany */
  public GameSession getGameSession(int index)
  {
    GameSession aGameSession = gameSessions.get(index);
    return aGameSession;
  }

  public List<GameSession> getGameSessions()
  {
    List<GameSession> newGameSessions = Collections.unmodifiableList(gameSessions);
    return newGameSessions;
  }

  public int numberOfGameSessions()
  {
    int number = gameSessions.size();
    return number;
  }

  public boolean hasGameSessions()
  {
    boolean has = gameSessions.size() > 0;
    return has;
  }

  public int indexOfGameSession(GameSession aGameSession)
  {
    int index = gameSessions.indexOf(aGameSession);
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
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoles()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfRoles()
  {
    return 2;
  }
  /* Code from template association_AddUnidirectionalMN */
  public boolean addRole(UserRole aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    if (numberOfRoles() < maximumNumberOfRoles())
    {
      roles.add(aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeRole(UserRole aRole)
  {
    boolean wasRemoved = false;
    if (!roles.contains(aRole))
    {
      return wasRemoved;
    }

    if (numberOfRoles() <= minimumNumberOfRoles())
    {
      return wasRemoved;
    }

    roles.remove(aRole);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMN */
  public boolean setRoles(UserRole... newRoles)
  {
    boolean wasSet = false;
    ArrayList<UserRole> verifiedRoles = new ArrayList<UserRole>();
    for (UserRole aRole : newRoles)
    {
      if (verifiedRoles.contains(aRole))
      {
        continue;
      }
      verifiedRoles.add(aRole);
    }

    if (verifiedRoles.size() != newRoles.length || verifiedRoles.size() < minimumNumberOfRoles() || verifiedRoles.size() > maximumNumberOfRoles())
    {
      return wasSet;
    }

    roles.clear();
    roles.addAll(verifiedRoles);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoleAt(UserRole aRole, int index)
  {  
    boolean wasAdded = false;
    if(addRole(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoleAt(UserRole aRole, int index)
  {
    boolean wasAdded = false;
    if(roles.contains(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoleAt(aRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGameSessions()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GameSession addGameSession(boolean aOfTestMode, Game aGame, Block223 aBlock223, SpecificBall aSpecificBall, SpecificPaddle aSpecificPaddle)
  {
    return new GameSession(aOfTestMode, aGame, this, aBlock223, aSpecificBall, aSpecificPaddle);
  }

  public boolean addGameSession(GameSession aGameSession)
  {
    boolean wasAdded = false;
    if (gameSessions.contains(aGameSession)) { return false; }
    User existingUser = aGameSession.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aGameSession.setUser(this);
    }
    else
    {
      gameSessions.add(aGameSession);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGameSession(GameSession aGameSession)
  {
    boolean wasRemoved = false;
    //Unable to remove aGameSession, as it must always have a user
    if (!this.equals(aGameSession.getUser()))
    {
      gameSessions.remove(aGameSession);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameSessionAt(GameSession aGameSession, int index)
  {  
    boolean wasAdded = false;
    if(addGameSession(aGameSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameSessions()) { index = numberOfGameSessions() - 1; }
      gameSessions.remove(aGameSession);
      gameSessions.add(index, aGameSession);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameSessionAt(GameSession aGameSession, int index)
  {
    boolean wasAdded = false;
    if(gameSessions.contains(aGameSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGameSessions()) { index = numberOfGameSessions() - 1; }
      gameSessions.remove(aGameSession);
      gameSessions.add(index, aGameSession);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameSessionAt(aGameSession, index);
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
    User existingUser = aHallOfFameEntry.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aHallOfFameEntry.setUser(this);
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
    //Unable to remove aHallOfFameEntry, as it must always have a user
    if (!this.equals(aHallOfFameEntry.getUser()))
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
      existingBlock223.removeUser(this);
    }
    block223.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByUsername.remove(getUsername());
    roles.clear();
    for(int i=gameSessions.size(); i > 0; i--)
    {
      GameSession aGameSession = gameSessions.get(i - 1);
      aGameSession.delete();
    }
    for(int i=hallOfFameEntries.size(); i > 0; i--)
    {
      HallOfFameEntry aHallOfFameEntry = hallOfFameEntries.get(i - 1);
      aHallOfFameEntry.delete();
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeUser(this);
    }
  }

  // line 57 "../../../../../Block223Persistence.ump"
   public static  void reinitializeUniqueUsername(List<User> users){
    usersByUsername = new HashMap<String, User>();
	    for (User user : users) {
	      	usersByUsername.put(user.getUsername(), user);
    	}
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 55 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 5L ;

  
}