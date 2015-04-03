/*Author: 				Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 			April 3, 2015
* Course: 				CSC243
* Professor Name: Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 			Human.java
* Purpose:		  	This represents a player that is controlled by a human. Human handles
* 								the decisions that a person takes throughout the game.
*/
import java.util.Scanner;
/**
*Represents a Human Player, processes the choices that the user makes.
*@author	Ruben Swyers
*/
public class Human extends Player
{
	/**
	*@param name			Desired name of the Player
	*/
	public Human(String name)
	{
		super(name);
	}
	/**
	*Adds a Card to the Rack's extra slot
	*@param	C					Card to be added to the extra slot
	*/
	public void pickupCard(Card C)
	{
		C.setState(true); //flips the card up so the value can be seen
		C.setOwner(0);
		this.Hand.setExtra(C);
	}
	/**
	*Removes a Card from their Rack. This was rewritten for use in the GUI. This also
	* updates the current score
	*@param C							Card to remove from the Rack (including the extra slot)
	*@return							The Card once it's removed from the Rack.
	*/
  public Card chooseDiscard(Card D)
  {
    Card Result;
    Result = this.Hand.discard(D);
		this.currentScore = this.Hand.score();
    return Result;

  }
	/**
	*Have the user choose what Deck they would like to draw from.
	*@param Top					The top Card of the Discard Deck
	*@int								0 if the user chose to pick up from the Draw Deck,
	* 1 if the user chose to draw from the Discard Deck
	*/
  public int choosePile(Card Top)
  {
    Scanner keyboard = new Scanner(System.in);
    int choice;
		System.out.println("My Rack:");
		this.printHand();
		System.out.println("The top of the discard pile is: " + Top);
    System.out.println("What pile would you like to pick up from? (0 for Draw, 1 for Discard)");
    choice = keyboard.nextInt();
    while(choice != 0 && choice != 1)
    {
      System.out.println("You did not enter 0 or 1. Please enter 0 for the Draw pile or 1 for the Discard pile:");
      choice = keyboard.nextInt();
    }
    return choice;
  }
}
