/*Author: 				Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 			May 7, 2015
* Course: 				CSC243
* Professor Name: Dr. Spiegel
* Assignment: 		#3 - Racko GUI
* Filename: 			Player.java
* Purpose:		  	This represents a Player in the game. It's an abstract class, so
                  Human and Computer will be subclasses to implement the class. Player
                  handles any decisions that are made by anyone playing the game.
*/
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
  /**
  Panel that contains the name of the Player and notifies if it is their turn.
  */
  protected InfoPanel Info;
  /**
  *ID of the player, to set the owner of the Cards as they pick them up.
  */
  protected int id;
  //methods
  /**
  *@param name    Desired name of the Player
  */
  public Player(String name,int identity)
  {
	  this.setName(name);
    this.id = identity;
    this.Hand = new Rack();
    this.Info = new InfoPanel(name);
  }
  //sets
  /**
  *Sets the score of the Player
  *@param s       Desired score
  */
  public void setScore(int s)
  {
	  this.score = s;
  }
  /**
  *Sets the current score of the Player
  *@param s       Desired current score
  */
  public void setCurrentScore(int s)
  {
	  this.currentScore = s;
  }
  /**
  *Sets the name of the Player
  *@param s       Name to give the Player
  */
  public void setName(String s)
  {
    this.name = s;
  }
  //gets
  /**
  *Gets the score of the Player
  *@return        Score of the Player
  */
  public int getScore()
  {
	  return this.score;
  }
  /**
  *Gets the running score of the Player
  *@return        Running score
  */
  public int getCurrentScore()
  {
	  return this.currentScore;
  }
  /**
  *Gets the name of the Player
  *@return        Name of the Player
  */
  public String getName()
  {
    return this.name;
  }
  //other methods
  /**
  *Player recieves a Card to add to their Rack
  *@param C       Card to be added to the Rack
  *@param index   Where the Card belongs in the Rack
  */
  public void getCard(Card C,int index)
  {
    C.setState(true);
    this.Hand.addCard(C,index);
  }
  /**
  *Gets the reference of the Rack that the Player has
  *@return        reference of the Rack the Player has
  */
  public Rack getRack()
  {
    return this.Hand;
  }
  /**
  Gets the reference to the InfoPanel that the Player has
  */
  public InfoPanel getInfoPane()
  {
    return this.Info;
  }
  /**
  *Adds the Card as an extra Card, until the Player decides to discard it
  * or add it to their Rack
  *@param C       Card that is picked up
  */
  public abstract void pickupCard(Card C);
  /**
  *Gets the Card that isn't in the Rack, but isn't discarded yet
  *@return        Card that was just picked up
  */
  public Card getExtraCard()
  {
    return this.Hand.getExtra();
  }
  /**
  *Prints the contents of the Player's Rack to the command line
  */
  public void printHand()
  {
    System.out.println(this.Hand);
  }
  /**
  *Decides what Deck to pick up from based on the Card that is on
  *top.
  *@param Top     The top of the deck that is being looked at.
  *@return        The choice of the deck, <code>1</code> for the discard deck
  *and <code>0</code> for the draw deck
  */
  public abstract int choosePile(Card Top);
  /**
  *Chooses what Card to discard, either the extra Card or a Card in the Rack.
  *The chosen Card is passed to the Rack to adjust itself based on the choice.
  *@param D       The chosen Card to discard
  *@return        Card that was discarded
  */
  public abstract Card chooseDiscard(Card D);
  /**
  *Handles a Player's turn and their decisions that they make for their turn.
  *@param C       The Card that is picked up at the beginning of the turn
  *@return        The Card that is discarded
  */
  public Card takeTurn(Card C)
	{
		this.pickupCard(C);
		Card result = chooseDiscard(C);
		return result;
	}
  /**
  *Prints the contents of the Rack to the command line
  */
  public void printRack()
  {
    System.out.println(this.Hand);
  }
}
