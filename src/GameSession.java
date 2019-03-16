/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 1 "Block223States.ump"
public class GameSession
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameSession State Machines
  public enum GameStatus { Idle, Play, Paused, Complete }
  private GameStatus gameStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameSession()
  {
    setGameStatus(GameStatus.Idle);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getGameStatusFullName()
  {
    String answer = gameStatus.toString();
    return answer;
  }

  public GameStatus getGameStatus()
  {
    return gameStatus;
  }

  public boolean startGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Idle:
        if (isGameAdmin()&&ofTestMode()&&hasEnoughBlocks())
        {
        // line 9 "Block223States.ump"
          
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (!(ofTestMode())&&hasEnoughBlocks())
        {
        // line 11 "Block223States.ump"
          
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isGameAdmin()&&ofTestMode()&&!(hasEnoughBlocks()))
        {
        // line 13 "Block223States.ump"
          addRandomBlocks(); // Must reach required number of blocks
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (!(ofTestMode())&&!(hasEnoughBlocks()))
        {
        // line 16 "Block223States.ump"
          addRandomBlocks();
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move(SpecificBall aSpecificBall)
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Play:
        if (isBlockHit()&&!(isLastBlock()))
        {
          exitGameStatus();
        // line 24 "Block223States.ump"
          // move() updates SpecificBall position
				doBlockHit(SpecificBall aSpecificBall);
				deleteBlock(SpecificBlock aBlock); 
				increaseScore();
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit()&&isLastBlock()&&!(isLastLevel()))
        {
          exitGameStatus();
        // line 29 "Block223States.ump"
          doBlockHit(SpecificBall aSpecificBall);
				deleteBlock(SpecificBlock aBlock);
				resetPaddlePosition(SpecificPaddle aPaddle);
				resetBallPosition(SpecificBall aSpecificBall);
				increaseScore();
				currentLevelNr++;
          setGameStatus(GameStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit()&&isLastBlock()&&isLastLevel())
        {
          exitGameStatus();
        // line 37 "Block223States.ump"
          doBlockHit(SpecificBall aSpecificBall);
				deleteBlock(SpecificBlock aBlock);
				increaseScore();
          setGameStatus(GameStatus.Complete);
          wasEventProcessed = true;
          break;
        }
        if (isWallHit())
        {
          exitGameStatus();
        // line 42 "Block223States.ump"
          doWallHit(SpecificBall aSpecificBall);
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isPaddleHit())
        {
          exitGameStatus();
        // line 45 "Block223States.ump"
          doPaddleHit(SpecificBall aSpecificBall);
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds()&&!(isLastLife()))
        {
          exitGameStatus();
        // line 48 "Block223States.ump"
          doOutOfBounds(SpecificBall aSpecificBall);
				resetPaddlePosition(SpecificPaddle aPaddle);
				resetBallPosition(SpecificBall aBall);
				currentLife--;
          setGameStatus(GameStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds()&&isLastLife())
        {
          exitGameStatus();
        // line 54 "Block223States.ump"
          doOutOfBounds(SpecificBall aSpecificBall);
				currentLife--;
          setGameStatus(GameStatus.Complete);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pauseGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Play:
        if (!(isOutOfBounds()))
        {
          exitGameStatus();
        // line 58 "Block223States.ump"
          stopBall(SpecificBall aSpecificBall); // Stop SpecificBall in its current location
				stopPaddle(SpecificPaddle aSpecificPaddle); // Stop SpecificPaddle in its current location
          setGameStatus(GameStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean resumeGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Paused:
        if (hasNoBlocks())
        {
        // line 67 "Block223States.ump"
          // Represents completing a level
				initializeBlocks(); // initializeBlocks() checks if there are blocks left (if no, start from scratch)
				addRandomBlocks();
				initializeBall(); 
				initializePaddle();
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (!(hasNoBlocks()))
        {
        // line 73 "Block223States.ump"
          // Level not yet complete
				initializeBlocks(); // Add blocks that admin has created from save, those not deleted
				initializeBall(); // Add a SpecificBall from save
				initializePaddle(); // Add a SpecificPaddle & position it from save
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitGameStatus()
  {
    switch(gameStatus)
    {
      case Play:
        // line 62 "Block223States.ump"
        stopBall(SpecificBall aSpecificBall);
        break;
    }
  }

  private void setGameStatus(GameStatus aGameStatus)
  {
    gameStatus = aGameStatus;

    // entry actions and do activities
    switch(gameStatus)
    {
      case Idle:
        // line 4 "Block223States.ump"
        initializeBlocks(); // Add blocks that admin has created
				initializeBall(); // Add a SpecificBall
				initializePaddle(); // Add a SpecificPaddle & position it
        break;
      case Play:
        // line 21 "Block223States.ump"
        move(SpecificBall aSpecificBall);
        break;
    }
  }

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 90 "Block223States.ump"
  private void initializeBlocks() ;
// line 91 "Block223States.ump"
  private void initializeBall() ;
// line 92 "Block223States.ump"
  private void initializePaddle() ;
// line 93 "Block223States.ump"
  private void addRandomBlocks() ;
// line 94 "Block223States.ump"
  private void doBlockHit(specificBall) ;
// line 95 "Block223States.ump"
  private void deleteBlock() ;
// line 96 "Block223States.ump"
  private void increaseScore() ;
// line 97 "Block223States.ump"
  private void resetPaddlePosition(specificPaddle) ;
// line 98 "Block223States.ump"
  private void resetBallPosition(specificBall) ;
// line 99 "Block223States.ump"
  private void doWallHit(specificBall) ;
// line 100 "Block223States.ump"
  private void doPaddleHit(specificBall) ;
// line 101 "Block223States.ump"
  private void doOutOfBounds(specificBall) ;
// line 102 "Block223States.ump"
  private void stopBall(specificBall) ;
// line 103 "Block223States.ump"
  private void stopPaddle(specificPaddle) ;
// line 104 "Block223States.ump"
  private void addHallOfFameEntry() ;
// line 105 "Block223States.ump"
  private void displayGameOver() ;
// line 106 "Block223States.ump"
  private void isOutOfBounds(specificBall) ;
// line 110 "Block223States.ump"
  private boolean isGameAdmin() ;
// line 111 "Block223States.ump"
  private boolean ofTestMode() ;
// line 112 "Block223States.ump"
  private boolean hasEnoughBlocks() ;
// line 113 "Block223States.ump"
  private boolean hasNoBlocks() ;
// line 114 "Block223States.ump"
  private boolean isBlockHit() ;
// line 115 "Block223States.ump"
  private boolean isWallHit() ;
// line 116 "Block223States.ump"
  private boolean isPaddleHit() ;
// line 117 "Block223States.ump"
  private boolean isOutOfBounds() ;
// line 118 "Block223States.ump"
  private boolean isLastBlock() ;
// line 119 "Block223States.ump"
  private boolean isLastLife() ;
// line 120 "Block223States.ump"
  private boolean hasLifeLeft() ;
// line 121 "Block223States.ump"
  private boolean isLastLevel() ;

  
}