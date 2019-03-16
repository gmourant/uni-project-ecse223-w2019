/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 42 "../../../../../Block223TransferObjects.ump"
public class TOScore
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOScore Attributes
  private int score;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOScore(int aScore)
  {
    score = aScore;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public int getScore()
  {
    return score;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "score" + ":" + getScore()+ "]";
  }
}