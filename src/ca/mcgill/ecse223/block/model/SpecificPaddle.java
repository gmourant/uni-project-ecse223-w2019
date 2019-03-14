/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 24 "../../../../../Block223PlayGame.ump"
public class SpecificPaddle
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int id;

  //SpecificPaddle Associations
  private Paddle paddle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificPaddle(Paddle aPaddle)
  {
    id = nextId++;
    boolean didAddPaddle = setPaddle(aPaddle);
    if (!didAddPaddle)
    {
      throw new RuntimeException("Unable to create specificPaddle due to paddle");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPaddle(Paddle aPaddle)
  {
    boolean wasSet = false;
    if (aPaddle == null)
    {
      return wasSet;
    }

    Paddle existingPaddle = paddle;
    paddle = aPaddle;
    if (existingPaddle != null && !existingPaddle.equals(aPaddle))
    {
      existingPaddle.removeSpecificPaddle(this);
    }
    paddle.addSpecificPaddle(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Paddle placeholderPaddle = paddle;
    this.paddle = null;
    if(placeholderPaddle != null)
    {
      placeholderPaddle.removeSpecificPaddle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null");
  }
}