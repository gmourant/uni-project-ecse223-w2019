package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 86 "Block223App.ump"
public class Ball
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int minimumSpeed;
  private int speedIncreaseFactor;
  private int diameter;

  //Ball Associations
  private PlayArea playArea;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aMinimumSpeed, int aSpeedIncreaseFactor, PlayArea aPlayArea)
  {
    minimumSpeed = aMinimumSpeed;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    diameter = 10;
    if (aPlayArea == null || aPlayArea.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aPlayArea");
    }
    playArea = aPlayArea;
  }

  public Ball(int aMinimumSpeed, int aSpeedIncreaseFactor, int aWidthForPlayArea, int aHeightForPlayArea, Block223 aBlock223ForPlayArea, Game aGameForPlayArea, Paddle aPaddleForPlayArea)
  {
    minimumSpeed = aMinimumSpeed;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    diameter = 10;
    playArea = new PlayArea(aWidthForPlayArea, aHeightForPlayArea, aBlock223ForPlayArea, aGameForPlayArea, aPaddleForPlayArea, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMinimumSpeed(int aMinimumSpeed)
  {
    boolean wasSet = false;
    minimumSpeed = aMinimumSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpeedIncreaseFactor(int aSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiameter(int aDiameter)
  {
    boolean wasSet = false;
    diameter = aDiameter;
    wasSet = true;
    return wasSet;
  }

  public int getMinimumSpeed()
  {
    return minimumSpeed;
  }

  public int getSpeedIncreaseFactor()
  {
    return speedIncreaseFactor;
  }

  public int getDiameter()
  {
    return diameter;
  }
  /* Code from template association_GetOne */
  public PlayArea getPlayArea()
  {
    return playArea;
  }

  public void delete()
  {
    PlayArea existingPlayArea = playArea;
    playArea = null;
    if (existingPlayArea != null)
    {
      existingPlayArea.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "minimumSpeed" + ":" + getMinimumSpeed()+ "," +
            "speedIncreaseFactor" + ":" + getSpeedIncreaseFactor()+ "," +
            "diameter" + ":" + getDiameter()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "playArea = "+(getPlayArea()!=null?Integer.toHexString(System.identityHashCode(getPlayArea())):"null");
  }
}