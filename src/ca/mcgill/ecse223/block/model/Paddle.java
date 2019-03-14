/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 88 "../../../../../Block223Persistence.ump"
// line 115 "../../../../../Block223.ump"
public class Paddle implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int PADDLE_WIDTH = 5;
  public static final int VERTICAL_DISTANCE = 30;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private int maxPaddleLength;
  private int minPaddleLength;

  //Paddle Associations
  private List<SpecificPaddle> specificPaddles;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(int aMaxPaddleLength, int aMinPaddleLength, Game aGame)
  {
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    specificPaddles = new ArrayList<SpecificPaddle>();
    if (aGame == null || aGame.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aGame");
    }
    game = aGame;
  }

  public Paddle(int aMaxPaddleLength, int aMinPaddleLength, boolean aIsPublishedForGame, boolean aInTestModeForGame, String aNameForGame, int aNrBlocksPerLevelForGame, Admin aAdminForGame, Ball aBallForGame, Block223 aBlock223ForGame)
  {
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    specificPaddles = new ArrayList<SpecificPaddle>();
    game = new Game(aIsPublishedForGame, aInTestModeForGame, aNameForGame, aNrBlocksPerLevelForGame, aAdminForGame, aBallForGame, this, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMaxPaddleLength(int aMaxPaddleLength)
  {
    boolean wasSet = false;
    // line 120 "../../../../../Block223.ump"
    if (aMaxPaddleLength > 390 || aMaxPaddleLength <= 0) throw new RuntimeException("The maximum length of the paddle must be greater than zero and less than or equal to 390.");
    // END OF UMPLE BEFORE INJECTION
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinPaddleLength(int aMinPaddleLength)
  {
    boolean wasSet = false;
    // line 124 "../../../../../Block223.ump"
    if (aMinPaddleLength <= 0) throw new RuntimeException("The minimum length of the paddle must be greater than zero.");
    // END OF UMPLE BEFORE INJECTION
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public int getMaxPaddleLength()
  {
    return maxPaddleLength;
  }

  public int getMinPaddleLength()
  {
    return minPaddleLength;
  }
  /* Code from template association_GetMany */
  public SpecificPaddle getSpecificPaddle(int index)
  {
    SpecificPaddle aSpecificPaddle = specificPaddles.get(index);
    return aSpecificPaddle;
  }

  public List<SpecificPaddle> getSpecificPaddles()
  {
    List<SpecificPaddle> newSpecificPaddles = Collections.unmodifiableList(specificPaddles);
    return newSpecificPaddles;
  }

  public int numberOfSpecificPaddles()
  {
    int number = specificPaddles.size();
    return number;
  }

  public boolean hasSpecificPaddles()
  {
    boolean has = specificPaddles.size() > 0;
    return has;
  }

  public int indexOfSpecificPaddle(SpecificPaddle aSpecificPaddle)
  {
    int index = specificPaddles.indexOf(aSpecificPaddle);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificPaddles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificPaddle addSpecificPaddle()
  {
    return new SpecificPaddle(this);
  }

  public boolean addSpecificPaddle(SpecificPaddle aSpecificPaddle)
  {
    boolean wasAdded = false;
    if (specificPaddles.contains(aSpecificPaddle)) { return false; }
    Paddle existingPaddle = aSpecificPaddle.getPaddle();
    boolean isNewPaddle = existingPaddle != null && !this.equals(existingPaddle);
    if (isNewPaddle)
    {
      aSpecificPaddle.setPaddle(this);
    }
    else
    {
      specificPaddles.add(aSpecificPaddle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificPaddle(SpecificPaddle aSpecificPaddle)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificPaddle, as it must always have a paddle
    if (!this.equals(aSpecificPaddle.getPaddle()))
    {
      specificPaddles.remove(aSpecificPaddle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificPaddleAt(SpecificPaddle aSpecificPaddle, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificPaddle(aSpecificPaddle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificPaddles()) { index = numberOfSpecificPaddles() - 1; }
      specificPaddles.remove(aSpecificPaddle);
      specificPaddles.add(index, aSpecificPaddle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificPaddleAt(SpecificPaddle aSpecificPaddle, int index)
  {
    boolean wasAdded = false;
    if(specificPaddles.contains(aSpecificPaddle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificPaddles()) { index = numberOfSpecificPaddles() - 1; }
      specificPaddles.remove(aSpecificPaddle);
      specificPaddles.add(index, aSpecificPaddle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificPaddleAt(aSpecificPaddle, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=specificPaddles.size(); i > 0; i--)
    {
      SpecificPaddle aSpecificPaddle = specificPaddles.get(i - 1);
      aSpecificPaddle.delete();
    }
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 91 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 10L ;

  
}