package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 78 "Block223App.ump"
public class Paddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private int maximumLength;
  private int minimumLength;
  private int width;

  //Paddle Associations
  private PlayArea playArea;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(int aMaximumLength, int aMinimumLength, PlayArea aPlayArea)
  {
    maximumLength = aMaximumLength;
    minimumLength = aMinimumLength;
    width = 5;
    if (aPlayArea == null || aPlayArea.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aPlayArea");
    }
    playArea = aPlayArea;
  }

  public Paddle(int aMaximumLength, int aMinimumLength, int aWidthForPlayArea, int aHeightForPlayArea, Block223 aBlock223ForPlayArea, Game aGameForPlayArea, Ball aBallForPlayArea)
  {
    maximumLength = aMaximumLength;
    minimumLength = aMinimumLength;
    width = 5;
    playArea = new PlayArea(aWidthForPlayArea, aHeightForPlayArea, aBlock223ForPlayArea, aGameForPlayArea, this, aBallForPlayArea);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMaximumLength(int aMaximumLength)
  {
    boolean wasSet = false;
    maximumLength = aMaximumLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinimumLength(int aMinimumLength)
  {
    boolean wasSet = false;
    minimumLength = aMinimumLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setWidth(int aWidth)
  {
    boolean wasSet = false;
    width = aWidth;
    wasSet = true;
    return wasSet;
  }

  public int getMaximumLength()
  {
    return maximumLength;
  }

  public int getMinimumLength()
  {
    return minimumLength;
  }

  public int getWidth()
  {
    return width;
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
            "maximumLength" + ":" + getMaximumLength()+ "," +
            "minimumLength" + ":" + getMinimumLength()+ "," +
            "width" + ":" + getWidth()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "playArea = "+(getPlayArea()!=null?Integer.toHexString(System.identityHashCode(getPlayArea())):"null");
  }
}