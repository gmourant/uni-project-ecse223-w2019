package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 72 "Block223App.ump"
public class PlayArea
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayArea Attributes
  private int width;
  private int height;

  //PlayArea Associations
  private Block223 block223;
  private Game game;
  private Paddle paddle;
  private Ball ball;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayArea(int aWidth, int aHeight, Block223 aBlock223, Game aGame, Paddle aPaddle, Ball aBall)
  {
    width = aWidth;
    height = aHeight;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playArea due to block223");
    }
    if (aGame == null || aGame.getPlayArea() != null)
    {
      throw new RuntimeException("Unable to create PlayArea due to aGame");
    }
    game = aGame;
    if (aPaddle == null || aPaddle.getPlayArea() != null)
    {
      throw new RuntimeException("Unable to create PlayArea due to aPaddle");
    }
    paddle = aPaddle;
    if (aBall == null || aBall.getPlayArea() != null)
    {
      throw new RuntimeException("Unable to create PlayArea due to aBall");
    }
    ball = aBall;
  }

  public PlayArea(int aWidth, int aHeight, Block223 aBlock223, String aNameForGame, Block223 aBlock223ForGame, Admin aAdminForGame, int aMaximumLengthForPaddle, int aMinimumLengthForPaddle, int aMinimumSpeedForBall, int aSpeedIncreaseFactorForBall)
  {
    width = aWidth;
    height = aHeight;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playArea due to block223");
    }
    game = new Game(aNameForGame, this, aBlock223ForGame, aAdminForGame);
    paddle = new Paddle(aMaximumLengthForPaddle, aMinimumLengthForPaddle, this);
    ball = new Ball(aMinimumSpeedForBall, aSpeedIncreaseFactorForBall, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWidth(int aWidth)
  {
    boolean wasSet = false;
    width = aWidth;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(int aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
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
      existingBlock223.removePlayArea(this);
    }
    block223.addPlayArea(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayArea(this);
    }
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
    Paddle existingPaddle = paddle;
    paddle = null;
    if (existingPaddle != null)
    {
      existingPaddle.delete();
    }
    Ball existingBall = ball;
    ball = null;
    if (existingBall != null)
    {
      existingBall.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "width" + ":" + getWidth()+ "," +
            "height" + ":" + getHeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null");
  }
}