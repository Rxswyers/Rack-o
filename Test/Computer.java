/*Author: 				Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 			May 7, 2015
* Course: 				CSC243
* Professor Name: Dr. Spiegel
* Assignment: 		#3 - Racko GUI
* Filename: 			Computer.java
* Purpose:		  	This represents a computer player in the GUI, it will make decisions
on what actions to take based on a rudementary AI
*/
/**
*Computer Player to play Racko, it uses a rudementary AI to make it's choices
*for now.
*@author	Ruben Swyers
*/
public class Computer extends Player
{
	/**
	*Decides if the rack can be seen or not
	*/
	boolean see;
	/**
	*Number of players, held for the AI
	*/
	int numPlayers;
	/**
	*@param name			Computer's desired name
	*/
	public Computer(String name, int identity)
	{
		super(name,identity);
		this.see = false;
	}
	/**
	*Set if the Computer's rack can be seen or not
	*/
	public void setSight(boolean s)
	{
		this.see = s;
	}
	/**
	*Adds a Card to the Rack's extra slot
	*@param	C					Card to be added to the extra slot
	*/
	public void pickupCard(Card C) // set this to abstract
	{
		C.setState(this.see); //flips the card up so the value can be seen
		C.setOwner(this.id);
		this.Hand.setExtra(C);
	}
	/**
	*Chooses what pile to draw from, either the Draw or Discard
	*@param	Top				The top of the Discard pile
	*@return					<code>0</code> if it needs the Card from the Discard
	*deck, or <code>1</code> to draw from the Draw deck.
	*/
	public int choosePile(Card Top)
	{
		System.out.println("Position wanted in choosepile " + this.positionWanted(Top));
		if(this.positionWanted(Top) < 0)//the Discard pile is emtpy
		{
			return 0;
		}
		else if(!this.checkPosition(this.positionWanted(Top)))
		{
			return 0; //draw from the discard
			//CHANGED Line 61
		}
		else
		{
			return 1; //draw from the draw pile
		}
	}
	/**
	*Gets the desired position of a certain Card
	*@param C					Card to get the desired position of
	*@return					The desired position of the Card
	*/
	public int positionWanted(Card C)
	{
		int value = C.getValue();
		switch(numPlayers)
		{
			case 2:
				return (int)Math.floor((value-1)/4);
			case 3:
				return (int)Math.floor((value-1)/5);
			case 4:
				return (int)Math.floor((value-1)/6);
			default:
				return -1;
		}
	}
	/**
	*Checks to see if a certain position of the Rack is taken by a desired Card.
	*It will use positionWanted to check to see if it is desired or not.
	*@param	position				Position to check
	*@return								<code>true</code> if the position is filled with a desired slot or
	*<code>false</code> if it is not.
	*/
	public boolean checkPosition(int position)
	{
		System.out.println("Position wanted " + positionWanted(this.Hand.search(position)) + " Position "+ position);
		if(positionWanted(this.Hand.search(position)) == position)
		{
			return true; //position is filled with a card that is desired
		}
		else
		{
			return false; //the position doesn't have a card that is desired
		}
	}
	/**
	*Removes a Card from their Rack. This was rewritten for use in the GUI. This
	* also updates the current score.
	*@param C							Card to remove from the Rack (including the extra slot)
	*@return							The Card once it's removed from the Rack.
	*/
	public Card chooseDiscard(Card C)
	{
		int pos = positionWanted(this.Hand.getExtra());
		if(pos == -1)
		{
			this.currentScore = this.Hand.score();
			return this.Hand.getExtra();
		}
		else if(checkPosition(pos))
		{
			this.currentScore = this.Hand.score();
			return this.Hand.getExtra();
		}
		else
		{
			this.currentScore = this.Hand.score();
			return this.Hand.discard(this.Hand.search(pos));
		}
	}
	/**
	*Gets the Card that is wanted to be discarded, it doesn't actually
	*remove it from anything.
	*@return			Card that is chosen to discard.
	*/
	public Card getDiscard()
	{
		int pos = positionWanted(this.Hand.getExtra());
		if(pos == -1)
		{
			return this.Hand.getExtra();
		}
		else if(checkPosition(pos))
		{
			return this.Hand.getExtra();
		}
		else
		{
			return this.Hand.search(pos);
		}
	}
	/**
  *Sets the number of players
	*@param	n		Number of players
  */
	public void setNumPlayers(int n)
	{
		this.numPlayers = n;
	}
}
