import java.util.Scanner;
/**
*Player is an abstract class and is designed to be used in the game Racko.
*It is responsible for everything a Player would have as if playing right
*on a table.
*@author Ruben Swyers
*/
abstract public class Player
{
  //members
  /**
  *Name of the Player
  */
  protected String name;
  /**
  *Rack that the Player has
  */
  protected Rack Hand = new Rack();
  /**
  *Running score that the Player has accumulated
  */
  protected int score;				//this score is going to be the actual score
  /**
  *Score that the Player has in their hand
  */
  protected int currentScore;			//this will be the calculated score at the end of each turn
  /**
  *Card that is picked up, but not in the Rack
  */
  protected Card ExtraCard;
  //methods
  /**
  *@param name    Desired name of the Player
  */
  public Player(String name)
  {
	  this.setName(name);
    this.Hand = new Rack();
  }
  //sets
  public void setScore(int s)
  {
	  this.score = s;
  }
  public void setCurrentScore(int s)
  {
	  this.currentScore = s;
  }
  public void setName(String s)
  {
    this.name = s;
  }
  //gets
  public int getScore()
  {
	  return this.score;
  }
  public int getCurrentScore()
  {
	  return this.currentScore;
  }
  public String getName()
  {
    return this.name;
  }
  //other methods
  public void getCard(Card C,int index)
  {
    C.setState(true);
    this.Hand.addCard(C,index);
  }
  public Rack getRack()
  {
    return this.Hand;
  }
  public abstract void pickupCard(Card C);
  public Card getExtraCard()
  {
    return this.Hand.getExtra();
  }
  public void printHand()
  {
    System.out.println(this.Hand);
  }
  public abstract int choosePile(Card Top);
  public abstract Card chooseDiscard(Card D);
  public Card takeTurn(Card C)
	{
		this.pickupCard(C);
		Card result = chooseDiscard(C);
		return result;
	}
  public void printRack()
  {
    System.out.println(this.Hand);
  }
/*  public void updateScore()
  {
    this.setCurrentScore(this.Hand.score());
  }*/
}
