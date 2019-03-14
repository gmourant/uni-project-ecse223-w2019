/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 29 "../../../../../Block223PlayGame.ump"
public class SpecificBall
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificBall Attributes
  private boolean isWithinBounds;

  //Autounique Attributes
  private int id;

  //SpecificBall Associations
  private Ball ball;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBall(boolean aIsWithinBounds, Ball aBall)
  {
    isWithinBounds = aIsWithinBounds;
    id = nextId++;
    boolean didAddBall = setBall(aBall);
    if (!didAddBall)
    {
      throw new RuntimeException("Unable to create specificBall due to ball");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsWithinBounds(boolean aIsWithinBounds)
  {
    boolean wasSet = false;
    isWithinBounds = aIsWithinBounds;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsWithinBounds()
  {
    return isWithinBounds;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsWithinBounds()
  {
    return isWithinBounds;
  }
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBall(Ball aBall)
  {
    boolean wasSet = false;
    if (aBall == null)
    {
      return wasSet;
    }

    Ball existingBall = ball;
    ball = aBall;
    if (existingBall != null && !existingBall.equals(aBall))
    {
      existingBall.removeSpecificBall(this);
    }
    ball.addSpecificBall(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Ball placeholderBall = ball;
    this.ball = null;
    if(placeholderBall != null)
    {
      placeholderBall.removeSpecificBall(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "isWithinBounds" + ":" + getIsWithinBounds()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null");
  }
}