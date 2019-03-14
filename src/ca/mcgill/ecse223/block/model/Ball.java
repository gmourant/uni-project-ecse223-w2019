/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 94 "../../../../../Block223Persistence.ump"
// line 99 "../../../../../Block223.ump"
public class Ball implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int BALL_DIAMETER = 10;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int minBallSpeedX;
  private int minBallSpeedY;
  private double ballSpeedIncreaseFactor;

  //Ball Associations
  private List<SpecificBall> specificBalls;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, Game aGame)
  {
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    specificBalls = new ArrayList<SpecificBall>();
    if (aGame == null || aGame.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aGame");
    }
    game = aGame;
  }

  public Ball(int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, boolean aIsPublishedForGame, boolean aInTestModeForGame, String aNameForGame, int aNrBlocksPerLevelForGame, Admin aAdminForGame, Paddle aPaddleForGame, Block223 aBlock223ForGame)
  {
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    specificBalls = new ArrayList<SpecificBall>();
    game = new Game(aIsPublishedForGame, aInTestModeForGame, aNameForGame, aNrBlocksPerLevelForGame, aAdminForGame, this, aPaddleForGame, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMinBallSpeedX(int aMinBallSpeedX)
  {
    boolean wasSet = false;
    // line 103 "../../../../../Block223.ump"
    if (aMinBallSpeedX <= 0) throw new RuntimeException("The minimum speed of the ball must be greater than zero.");
    // END OF UMPLE BEFORE INJECTION
    minBallSpeedX = aMinBallSpeedX;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeedY(int aMinBallSpeedY)
  {
    boolean wasSet = false;
    // line 107 "../../../../../Block223.ump"
    if (aMinBallSpeedY <= 0) throw new RuntimeException("The minimum speed of the ball must be greater than zero.");
    // END OF UMPLE BEFORE INJECTION
    minBallSpeedY = aMinBallSpeedY;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallSpeedIncreaseFactor(double aBallSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    // line 111 "../../../../../Block223.ump"
    if (aBallSpeedIncreaseFactor <= 0) throw new RuntimeException("The speed increase factor of the ball must be greater than zero.");
    // END OF UMPLE BEFORE INJECTION
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    wasSet = true;
    return wasSet;
  }

  public int getMinBallSpeedX()
  {
    return minBallSpeedX;
  }

  public int getMinBallSpeedY()
  {
    return minBallSpeedY;
  }

  public double getBallSpeedIncreaseFactor()
  {
    return ballSpeedIncreaseFactor;
  }
  /* Code from template association_GetMany */
  public SpecificBall getSpecificBall(int index)
  {
    SpecificBall aSpecificBall = specificBalls.get(index);
    return aSpecificBall;
  }

  public List<SpecificBall> getSpecificBalls()
  {
    List<SpecificBall> newSpecificBalls = Collections.unmodifiableList(specificBalls);
    return newSpecificBalls;
  }

  public int numberOfSpecificBalls()
  {
    int number = specificBalls.size();
    return number;
  }

  public boolean hasSpecificBalls()
  {
    boolean has = specificBalls.size() > 0;
    return has;
  }

  public int indexOfSpecificBall(SpecificBall aSpecificBall)
  {
    int index = specificBalls.indexOf(aSpecificBall);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificBalls()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificBall addSpecificBall(boolean aIsWithinBounds)
  {
    return new SpecificBall(aIsWithinBounds, this);
  }

  public boolean addSpecificBall(SpecificBall aSpecificBall)
  {
    boolean wasAdded = false;
    if (specificBalls.contains(aSpecificBall)) { return false; }
    Ball existingBall = aSpecificBall.getBall();
    boolean isNewBall = existingBall != null && !this.equals(existingBall);
    if (isNewBall)
    {
      aSpecificBall.setBall(this);
    }
    else
    {
      specificBalls.add(aSpecificBall);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificBall(SpecificBall aSpecificBall)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificBall, as it must always have a ball
    if (!this.equals(aSpecificBall.getBall()))
    {
      specificBalls.remove(aSpecificBall);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificBallAt(SpecificBall aSpecificBall, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificBall(aSpecificBall))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBalls()) { index = numberOfSpecificBalls() - 1; }
      specificBalls.remove(aSpecificBall);
      specificBalls.add(index, aSpecificBall);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificBallAt(SpecificBall aSpecificBall, int index)
  {
    boolean wasAdded = false;
    if(specificBalls.contains(aSpecificBall))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBalls()) { index = numberOfSpecificBalls() - 1; }
      specificBalls.remove(aSpecificBall);
      specificBalls.add(index, aSpecificBall);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificBallAt(aSpecificBall, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=specificBalls.size(); i > 0; i--)
    {
      SpecificBall aSpecificBall = specificBalls.get(i - 1);
      aSpecificBall.delete();
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
            "minBallSpeedX" + ":" + getMinBallSpeedX()+ "," +
            "minBallSpeedY" + ":" + getMinBallSpeedY()+ "," +
            "ballSpeedIncreaseFactor" + ":" + getBallSpeedIncreaseFactor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 97 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 11L ;

  
}